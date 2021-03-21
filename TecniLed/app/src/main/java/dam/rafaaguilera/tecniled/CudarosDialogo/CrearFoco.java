package dam.rafaaguilera.tecniled.CudarosDialogo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.List;

import dam.rafaaguilera.tecniled.DatosLocales.Foco;
import dam.rafaaguilera.tecniled.DatosLocales.Grupo;
import dam.rafaaguilera.tecniled.MainActivity;
import dam.rafaaguilera.tecniled.R;
import dam.rafaaguilera.tecniled.RecyclerViews.FocoViewAdapter;
import dam.rafaaguilera.tecniled.RecyclerViews.GrupoViewAdapter;

public class CrearFoco extends DialogFragment implements DialogInterface.OnClickListener {
    private EditText nombreEditText;
    private EditText marcaEditText;
    private EditText direccionEditText;
    private EditText canalesEditText;
    private EditText dmxEditText;
    private List<Foco> listaFocos;
    private FocoViewAdapter focoViewAdapter;
    int idGrupo;

    public CrearFoco(List f, FocoViewAdapter fvh, int idGrupo) {
        this.listaFocos = f;
        this.focoViewAdapter = fvh;
        this.idGrupo = idGrupo;


    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(getActivity());

        String info = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(info);

        View crearProyectoView = layoutInflater.inflate(R.layout.crear_foco_view, null);

        //Enlaza con los componentes
        nombreEditText = (EditText) crearProyectoView.findViewById(R.id.nombreEditText);
        marcaEditText = (EditText) crearProyectoView.findViewById(R.id.marcaEditText);
        direccionEditText = (EditText) crearProyectoView.findViewById(R.id.direccionEditText);
        canalesEditText = (EditText) crearProyectoView.findViewById(R.id.canalesEditText);
        dmxEditText = (EditText) crearProyectoView.findViewById(R.id.dmxEditText);


        dialogo.setView(crearProyectoView);

        dialogo.setNegativeButton("Cancelar", this);
        dialogo.setPositiveButton("Aceptar", this);

        return dialogo.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            if (nombreEditText.getText().toString().equals("")) {
                Toast.makeText(getContext(), "Error, el campo nombre no puede estar vacio", Toast.LENGTH_LONG).show();
                return;
            }
            if (marcaEditText.getText().toString().equals("")) {
                Toast.makeText(getContext(), "Error, el campo marca no puede estar vacio", Toast.LENGTH_LONG).show();
                return;
            }
            if (direccionEditText.getText().toString().equals("")) {
                Toast.makeText(getContext(), "Error, el campo direccion no puede estar vacio", Toast.LENGTH_LONG).show();
                return;
            }
            if (canalesEditText.getText().toString().equals("")) {
                Toast.makeText(getContext(), "Error, el campo canales no puede estar vacio", Toast.LENGTH_LONG).show();
                return;
            }
            if (dmxEditText.getText().toString().equals("")) {
                Toast.makeText(getContext(), "Error, el campo dmx no puede estar vacio", Toast.LENGTH_LONG).show();
                return;
            }


            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("nombre", nombreEditText.getText().toString());
            nuevoRegistro.put("direccion", direccionEditText.getText().toString());
            nuevoRegistro.put("marca", marcaEditText.getText().toString());
            nuevoRegistro.put("canal", canalesEditText.getText().toString());
            nuevoRegistro.put("dmx", dmxEditText.getText().toString());

            nuevoRegistro.put("idGrupo", idGrupo);
            MainActivity.getTecniLedDB().insert("Foco", null, nuevoRegistro);

            Cursor c = MainActivity.getTecniLedDB().query("Foco", null, null, null, null, null, null);

            c.moveToLast();

            Foco foco = new Foco(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
            listaFocos.add(foco);

            focoViewAdapter.notifyItemInserted(listaFocos.indexOf(foco));



        }

        if (which == DialogInterface.BUTTON_NEGATIVE) {
            Toast.makeText(getContext(), "Operación cancelada", Toast.LENGTH_LONG).show();
        }

    }
}
