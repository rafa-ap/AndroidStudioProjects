package dam.rafaaguilera.temporizadorv2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

class AdaptadorSonidos extends RecyclerView.Adapter<SonidoViewHolder> implements View.OnClickListener {

    // Matriz dinámica para guardar los datos
    private final ArrayList<Sonido> listaSonidos;

    // Variable para guardar la referencia al método onClick para llamarlo cuando sea necesario
    private View.OnClickListener listener;

    // Contructor del adaptador usando una matriz dinámica del tipo Opcion
    AdaptadorSonidos(ArrayList<Sonido> listaSonidos) {
        // ¡OJO! NO es necesario invocar al constructor de la clase superior
        // Guardamos estas variables de la aplicación principal para usarlas luego
        this.listaSonidos = listaSonidos;
    }
    @NonNull
    @Override
    public SonidoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        // Inflamos la opción con el layout correspondiente
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sonido_view, viewGroup, false);

        // Establecemos el evento onClick de la opción
        itemView.setOnClickListener(this);

        // Definimos la nueva opción a partir del elemento anterior
        SonidoViewHolder ovh = new SonidoViewHolder(itemView);

        // Devolvemos ya la opción dentro la clase OpcionViewHolder
        return ovh;
    }

    @Override
    public void onBindViewHolder(@NonNull SonidoViewHolder viewHolder, int position) {
        // Simplemente optenemos los datos en esa posición
        Sonido sonido = listaSonidos.get(position);

        // Añadimos (bind=ligamos) al ViewHolder los datos
        viewHolder.bindOpcion(sonido);
    }

    @Override
    public int getItemCount() {
        return listaSonidos.size();
    }

    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        // ¡Sólo se invoca si está establecido previamente!
        if(listener != null)
            listener.onClick(v);

    }
}

class SonidoViewHolder extends RecyclerView.ViewHolder {
    private TextView nombre;
    private ImageView icono;


    public SonidoViewHolder(@NonNull View itemView) {
        super(itemView);

        icono = (ImageView) itemView.findViewById(R.id.imageView);
        nombre = (TextView) itemView.findViewById(R.id.nombre);
    }

    public void bindOpcion(Sonido s) {
        icono.setImageResource(s.getIcono());
        nombre.setText(s.getNombre());

    }
}
