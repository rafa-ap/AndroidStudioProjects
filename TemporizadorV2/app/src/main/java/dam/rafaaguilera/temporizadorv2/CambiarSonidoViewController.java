package dam.rafaaguilera.temporizadorv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CambiarSonidoViewController  extends Activity {

    private RecyclerView recView;
    private ArrayList<Sonido> listaSonidos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cambiar_sonido);

        recView = (RecyclerView) findViewById(R.id.recView);
        recView.setHasFixedSize(true);

        listaSonidos = new ArrayList<Sonido>();

        listaSonidos.add(new Sonido("Beep", R.drawable.audio_wave_96px, R.raw.beep));
        listaSonidos.add(new Sonido("Beep-Beep", R.drawable.audio_wave_96px, R.raw.beep_beep));
        listaSonidos.add(new Sonido("Beep-Beep-Beep", R.drawable.audio_wave_96px, R.raw.beep_beep_beep));
        listaSonidos.add(new Sonido("Beep-Beep Beep-Beep", R.drawable.audio_wave_96px, R.raw.beep_beep__beep_beep));
        listaSonidos.add(new Sonido("Nokia Beep", R.drawable.audio_wave_96px, R.raw.nokia_beep));
        listaSonidos.add(new Sonido("Beep Remix", R.drawable.audio_wave_96px, R.raw.beep_remix));

        final AdaptadorSonidos adaptadorSonidos = new AdaptadorSonidos(listaSonidos);

        adaptadorSonidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pulsado: " + listaSonidos.get(recView.getChildAdapterPosition(v)).getNombre());
                Intent i = new Intent();
                i.putExtra("idSonido", listaSonidos.get(recView.getChildAdapterPosition(v)).getSonidoId());
                setResult(RESULT_OK, i);

                finish();
            }
        });

        // Asignamos el adaptador al RecyclerView para que sepa cómo dibujar el listado de opciones
        recView.setAdapter(adaptadorSonidos);

        // Muy importante indicar el tipo de Layout. ¡Obligatorio!!!
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        // Animador de la lista
        recView.setItemAnimator(new DefaultItemAnimator());

    }



    public void finish(){super.finish();}


}
