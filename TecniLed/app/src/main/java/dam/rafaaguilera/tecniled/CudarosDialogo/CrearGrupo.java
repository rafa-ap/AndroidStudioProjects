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

import dam.rafaaguilera.tecniled.DatosLocales.Grupo;
import dam.rafaaguilera.tecniled.DatosLocales.Proyecto;
import dam.rafaaguilera.tecniled.MainActivity;
import dam.rafaaguilera.tecniled.R;
import dam.rafaaguilera.tecniled.RecyclerViews.GrupoViewAdapter;

public class CrearGrupo extends DialogFragment implements DialogInterface.OnClickListener {
    private EditText nombreEditText;
    private EditText ubicacionEditText;
    private List<Grupo> listaGrupos;
    private GrupoViewAdapter grupoViewAdapter;
    int idProyecto;

    public CrearGrupo(List g, GrupoViewAdapter gvh, int idProyecto) {
        this.listaGrupos = g;
        this.grupoViewAdapter = gvh;
        this.idProyecto = idProyecto;


    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(getActivity());

        String info = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(info);

        View crearProyectoView = layoutInflater.inflate(R.layout.crear_grupo_view, null);

        //Enlaza con los componentes
        nombreEditText = (EditText) crearProyectoView.findViewById(R.id.nombreEditText);
        ubicacionEditText = (EditText) crearProyectoView.findViewById(R.id.ubicacionEditText);


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

            if (ubicacionEditText.getText().toString().equals("")) {
                Toast.makeText(getContext(), "Error, el campo ubicacion no puede estar vacio", Toast.LENGTH_LONG).show();
                return;
            }



            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("nombre", nombreEditText.getText().toString());
            nuevoRegistro.put("ubicacion", ubicacionEditText.getText().toString());
            nuevoRegistro.put("idProyecto", idProyecto);
            MainActivity.getTecniLedDB().insert("Grupo", null, nuevoRegistro);

            Cursor c = MainActivity.getTecniLedDB().query("Grupo", null, null, null, null, null, null);

            c.moveToLast();

            Grupo grupo = new Grupo(c.getInt(0), c.getString(1), c.getString(2));
            listaGrupos.add(grupo);

            grupoViewAdapter.notifyItemInserted(listaGrupos.indexOf(grupo));



        }

        if (which == DialogInterface.BUTTON_NEGATIVE) {
            Toast.makeText(getContext(), "Operación cancelada", Toast.LENGTH_LONG).show();
        }

    }
}
