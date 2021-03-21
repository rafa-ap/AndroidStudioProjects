package dam.rafaaguilera.tecniled;

import android.content.Intent;
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

import dam.rafaaguilera.tecniled.CudarosDialogo.CrearGrupo;
import dam.rafaaguilera.tecniled.CudarosDialogo.CrearProyecto;
import dam.rafaaguilera.tecniled.DatosLocales.Grupo;
import dam.rafaaguilera.tecniled.DatosLocales.Proyecto;
import dam.rafaaguilera.tecniled.RecyclerViews.GrupoViewAdapter;

public class GruposIntent extends AppCompatActivity {

    private Button crearGruposBtn;
    private TextView tituloGrupos;

    private List<Grupo> listaGrupos;
    private RecyclerView recyclerView;
    private GrupoViewAdapter grupoViewAdapter;

    private void intencionFocos(int pos) {
        Intent intent = new Intent(this, FocosIntent.class);
        intent.putExtra("grupo", listaGrupos.get(pos));
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.grupos_view);

        Proyecto p = (Proyecto) getIntent().getSerializableExtra("proyecto");
        listaGrupos = p.getListaGrupos();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewGrupos);
        recyclerView.setHasFixedSize(true);


        grupoViewAdapter = new GrupoViewAdapter(listaGrupos);

        grupoViewAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intencionFocos(recyclerView.getChildAdapterPosition(v));

            }
        });


        recyclerView.setAdapter(grupoViewAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Animador de la lista
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        crearGruposBtn = (Button) findViewById(R.id.crearGruposBtn);
        crearGruposBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrearGrupo crearGrupo = new CrearGrupo(listaGrupos, grupoViewAdapter, p.getId());
                crearGrupo.show(getSupportFragmentManager(), "");


            }
        });

        tituloGrupos = (TextView) findViewById(R.id.tituloGrupos);
        tituloGrupos.setText("Proyecto " + p.getNombre());


    }

    public void finish() {
        super.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (MainActivity.getTecniLedDB().isOpen()) {
            MainActivity.recargaDatos();
        }

        System.out.println("OnStart");
    }
}
