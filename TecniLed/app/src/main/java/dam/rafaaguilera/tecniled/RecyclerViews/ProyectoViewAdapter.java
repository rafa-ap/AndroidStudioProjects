package dam.rafaaguilera.tecniled.RecyclerViews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dam.rafaaguilera.tecniled.DatosLocales.Proyecto;
import dam.rafaaguilera.tecniled.MainActivity;
import dam.rafaaguilera.tecniled.R;

public class ProyectoViewAdapter extends RecyclerView.Adapter<ProyectoViewHolder> implements View.OnClickListener {


    // Matriz dinámica para guardar los datos
    public final List<Proyecto> listaProyectos;


    // Variable para guardar la referencia al método onClick para llamarlo cuando sea necesario
    private View.OnClickListener listener;

    // Contructor del adaptador usando una matriz dinámica del tipo Opcion

    public ProyectoViewAdapter(List list) {
        // ¡OJO! NO es necesario invocar al constructor de la clase superior
        // Guardamos estas variables de la aplicación principal para usarlas luego
        this.listaProyectos = list;
    }

    @NonNull
    @Override
    public ProyectoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        // Inflamos la opción con el layout correspondiente
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_proyecto_view, viewGroup, false);

        // Establecemos el evento onClick de la opción
        itemView.setOnClickListener(this);

        // Definimos la nueva opción a partir del elemento anterior
        ProyectoViewHolder pvh = new ProyectoViewHolder(itemView);

        // Devolvemos ya la opción dentro la clase OpcionViewHolder
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProyectoViewHolder viewHolder, int position) {
        // Simplemente optenemos los datos en esa posición
        Proyecto proyecto = listaProyectos.get(position);

        // Añadimos (bind=ligamos) al ViewHolder los datos
        viewHolder.bindProyecto(proyecto);
        viewHolder.eliminarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getTecniLedDB().delete("proyecto", "_id=?", new String[] {String.valueOf(listaProyectos.get(position).getId())});

                listaProyectos.remove(listaProyectos.get(position));
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProyectos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        // ¡Sólo se invoca si está establecido previamente!
        if (listener != null)
            listener.onClick(v);

    }
}

class ProyectoViewHolder extends RecyclerView.ViewHolder {
    private TextView nombreText;
    private TextView fechaText;
    private TextView observacionesText;

    public ImageButton eliminarBtn;


    public ProyectoViewHolder(@NonNull View itemView) {
        super(itemView);

        nombreText = (TextView) itemView.findViewById(R.id.nombreText);
        fechaText = (TextView) itemView.findViewById(R.id.ubicacionText);
        observacionesText = (TextView) itemView.findViewById(R.id.observacionesText);
        eliminarBtn = (ImageButton) itemView.findViewById(R.id.eliminarBtn);


    }

    public void bindProyecto(Proyecto p) {

        nombreText.setText(p.getNombre());
        fechaText.setText(p.getFecha());
        observacionesText.setText(p.getObservaciones());


    }

}
