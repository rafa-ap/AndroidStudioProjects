package dam.rafaaguilera.tecniled.RecyclerViews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dam.rafaaguilera.tecniled.DatosLocales.Grupo;
import dam.rafaaguilera.tecniled.MainActivity;
import dam.rafaaguilera.tecniled.R;

public class GrupoViewAdapter extends RecyclerView.Adapter<GrupoViewHolder> implements View.OnClickListener {


    // Matriz dinámica para guardar los datos
    public final List<Grupo> listaGrupos;


    // Variable para guardar la referencia al método onClick para llamarlo cuando sea necesario
    private View.OnClickListener listener;

    // Contructor del adaptador usando una matriz dinámica del tipo Opcion

    public GrupoViewAdapter(List list) {
        // ¡OJO! NO es necesario invocar al constructor de la clase superior
        // Guardamos estas variables de la aplicación principal para usarlas luego
        this.listaGrupos = list;
    }

    @NonNull
    @Override
    public GrupoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        // Inflamos la opción con el layout correspondiente
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_grupo_view, viewGroup, false);

        // Establecemos el evento onClick de la opción
        itemView.setOnClickListener(this);

        // Definimos la nueva opción a partir del elemento anterior
        GrupoViewHolder gvh = new GrupoViewHolder(itemView);

        // Devolvemos ya la opción dentro la clase OpcionViewHolder
        return gvh;
    }

    @Override
    public void onBindViewHolder(@NonNull GrupoViewHolder viewHolder, int position) {
        // Simplemente optenemos los datos en esa posición
        Grupo grupo = listaGrupos.get(position);

        // Añadimos (bind=ligamos) al ViewHolder los datos
        viewHolder.bindGrupo(grupo);
        viewHolder.eliminarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.getTecniLedDB().delete("grupo", "_id=?", new String[] {String.valueOf(listaGrupos.get(position).getId())});

                listaGrupos.remove(listaGrupos.get(position));
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaGrupos.size();
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

class GrupoViewHolder extends RecyclerView.ViewHolder {
    private TextView nombreText;
    private TextView ubicacionText;

    public ImageButton eliminarBtn;


    public GrupoViewHolder(@NonNull View itemView) {
        super(itemView);

        nombreText = (TextView) itemView.findViewById(R.id.nombreText);
        ubicacionText = (TextView) itemView.findViewById(R.id.ubicacionText);
        eliminarBtn = (ImageButton) itemView.findViewById(R.id.eliminarBtn);


    }

    public void bindGrupo(Grupo g) {

        nombreText.setText(g.getNombre());
        ubicacionText.setText(g.getUbicacion());


    }

}
