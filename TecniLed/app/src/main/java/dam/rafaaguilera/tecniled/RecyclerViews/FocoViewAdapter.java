package dam.rafaaguilera.tecniled.RecyclerViews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dam.rafaaguilera.tecniled.DatosLocales.Foco;
import dam.rafaaguilera.tecniled.MainActivity;
import dam.rafaaguilera.tecniled.R;

public class FocoViewAdapter extends RecyclerView.Adapter<FocoViewHolder> implements View.OnClickListener {


    // Matriz dinámica para guardar los datos
    public final List<Foco> listaFocos;


    // Variable para guardar la referencia al método onClick para llamarlo cuando sea necesario
    private View.OnClickListener listener;

    // Contructor del adaptador usando una matriz dinámica del tipo Opcion

    public FocoViewAdapter(List list) {
        // ¡OJO! NO es necesario invocar al constructor de la clase superior
        // Guardamos estas variables de la aplicación principal para usarlas luego
        this.listaFocos = list;
    }

    @NonNull
    @Override
    public FocoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        // Inflamos la opción con el layout correspondiente
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_foco_view, viewGroup, false);

        // Establecemos el evento onClick de la opción
        itemView.setOnClickListener(this);

        // Definimos la nueva opción a partir del elemento anterior
        FocoViewHolder fvh = new FocoViewHolder(itemView);

        // Devolvemos ya la opción dentro la clase OpcionViewHolder
        return fvh;
    }

    @Override
    public void onBindViewHolder(@NonNull FocoViewHolder viewHolder, int position) {
        // Simplemente optenemos los datos en esa posición
        Foco foco = listaFocos.get(position);

        // Añadimos (bind=ligamos) al ViewHolder los datos
        viewHolder.bindFoco(foco);

        viewHolder.eliminarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getTecniLedDB().delete("Foco", "_id=?", new String[] {String.valueOf(listaFocos.get(position).getId())});

                listaFocos.remove(listaFocos.get(position));
                notifyDataSetChanged();

            }
        });


    }

    @Override
    public int getItemCount() {
        return listaFocos.size();
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

class FocoViewHolder extends RecyclerView.ViewHolder {
    private TextView nombreText;
    private TextView marcaText;
    private TextView direccionText;
    private TextView canalesText;
    private TextView dmxText;

    public ImageButton eliminarBtn;


    public FocoViewHolder(@NonNull View itemView) {
        super(itemView);

        nombreText = (TextView) itemView.findViewById(R.id.nombreText);
        marcaText = (TextView) itemView.findViewById(R.id.marcaText);
        direccionText = (TextView) itemView.findViewById(R.id.direccionText);
        canalesText = (TextView) itemView.findViewById(R.id.canalesText);
        dmxText = (TextView) itemView.findViewById(R.id.dmxText);

        eliminarBtn = (ImageButton) itemView.findViewById(R.id.eliminarBtn);


    }

    public void bindFoco(Foco f) {

        nombreText.setText(f.getNombre());
        marcaText.setText(f.getMarca());
        direccionText.setText(f.getDireccion());
        canalesText.setText(f.getCanal());
        dmxText.setText(f.getDmx());


    }

}
