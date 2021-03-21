package dam.rafaaguilera.tecniled;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dam.rafaaguilera.tecniled.CudarosDialogo.CrearFoco;
import dam.rafaaguilera.tecniled.DatosLocales.Foco;
import dam.rafaaguilera.tecniled.DatosLocales.Grupo;
import dam.rafaaguilera.tecniled.DatosLocales.Proyecto;
import dam.rafaaguilera.tecniled.RecyclerViews.FocoViewAdapter;

public class FocosIntent extends AppCompatActivity {

    private Button crearFocosBtn;
    private TextView tituloFocos;

    private List<Foco> listaFocos;
    private RecyclerView recyclerView;
    private FocoViewAdapter focoViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.focos_view);

        Grupo g = (Grupo) getIntent().getSerializableExtra("grupo");
        listaFocos = g.getListaFocos();



        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewFocos);
        recyclerView.setHasFixedSize(true);


        focoViewAdapter = new FocoViewAdapter(listaFocos);

        focoViewAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        recyclerView.setAdapter(focoViewAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Animador de la lista
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        crearFocosBtn = (Button) findViewById(R.id.crearFocosBtn);
        crearFocosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrearFoco crearFoco = new CrearFoco(listaFocos, focoViewAdapter, g.getId());
                crearFoco.show(getSupportFragmentManager(), "");


            }
        });

        tituloFocos = (TextView) findViewById(R.id.tituloFocos);
        tituloFocos.setText("Grupo " + g.getNombre());



    }

    public void finish(){super.finish();}

    @Override
    protected void onStart() {
        super.onStart();
        if (MainActivity.getTecniLedDB().isOpen()) {
            MainActivity.recargaDatos();
        }
            System.out.println("OnStart");
    }
}
