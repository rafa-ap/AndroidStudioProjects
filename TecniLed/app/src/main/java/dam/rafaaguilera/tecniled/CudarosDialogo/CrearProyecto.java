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

import dam.rafaaguilera.tecniled.DatosLocales.Proyecto;
import dam.rafaaguilera.tecniled.MainActivity;
import dam.rafaaguilera.tecniled.R;
import dam.rafaaguilera.tecniled.RecyclerViews.ProyectoViewAdapter;

public class CrearProyecto extends DialogFragment implements DialogInterface.OnClickListener {
    private EditText nombreEditText;
    private EditText fechaEditText;
    private EditText observacionesEditText;
    private List<Proyecto> listaProyectos;
    private ProyectoViewAdapter proyectoViewAdapter;

    public CrearProyecto(List p, ProyectoViewAdapter pvh) {
        this.listaProyectos = p;
        this.proyectoViewAdapter = pvh;


    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(getActivity());

        String info = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(info);

        View crearProyectoView = layoutInflater.inflate(R.layout.crear_proyecto_view, null);

        //Enlaza con los componentes
        nombreEditText = (EditText) crearProyectoView.findViewById(R.id.nombreEditText);
        fechaEditText = (EditText) crearProyectoView.findViewById(R.id.ubicacionEditText);
        observacionesEditText = (EditText) crearProyectoView.findViewById(R.id.observacionesEditText);




        dialogo.setView(crearProyectoView);

        dialogo.setNegativeButton("Cancelar", this);
        dialogo.setPositiveButton("Aceptar", this);

        return dialogo.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            if(nombreEditText.getText().toString().equals("")){
                Toast.makeText(getContext(), "Error, el campo nombre no puede estar vacio", Toast.LENGTH_LONG).show();
                return;
            }

            if(fechaEditText.getText().toString().equals("")){
                Toast.makeText(getContext(), "Error, el campo fecha no puede estar vacio", Toast.LENGTH_LONG).show();
                return;
            }


            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("nombre", nombreEditText.getText().toString());
            nuevoRegistro.put("fecha", fechaEditText.getText().toString());
            nuevoRegistro.put("observaciones", observacionesEditText.getText().toString());
            MainActivity.getTecniLedDB().insert("Proyecto", null, nuevoRegistro);

            Cursor c = MainActivity.getTecniLedDB().query("Proyecto", null, null, null, null, null, null);

            c.moveToLast();

            Proyecto proyecto = new Proyecto(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
            listaProyectos.add(proyecto);

            proyectoViewAdapter.notifyItemInserted(listaProyectos.indexOf(proyecto));

        }

        if (which == DialogInterface.BUTTON_NEGATIVE) {
            Toast.makeText(getContext(), "Operación cancelada", Toast.LENGTH_LONG).show();
        }

    }
}
