package dam.rafaaguilera.tecniled;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import dam.rafaaguilera.tecniled.CudarosDialogo.CrearProyecto;
import dam.rafaaguilera.tecniled.DatosLocales.Foco;
import dam.rafaaguilera.tecniled.DatosLocales.Grupo;
import dam.rafaaguilera.tecniled.DatosLocales.Proyecto;
import dam.rafaaguilera.tecniled.RecyclerViews.ProyectoViewAdapter;
import dam.rafaaguilera.tecniled.SQLiteDB.SQLiteDB;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static SQLiteDatabase tecniLedDB;
    private static List<Proyecto> listaProyectos;
    private RecyclerView recyclerView;
    public ProyectoViewAdapter proyectoViewAdapter;

    public static SQLiteDatabase getTecniLedDB() {
        return tecniLedDB;
    }

    public static void recargaDatos() {
        listaProyectos = new ArrayList<Proyecto>();
        Cursor consulta1 = tecniLedDB.query("Proyecto", null, null, null, null, null, null);
        if (consulta1.moveToFirst()) {
            do {
                Proyecto p = new Proyecto(consulta1.getInt(0), consulta1.getString(1), consulta1.getString(2), consulta1.getString(3));
                listaProyectos.add(p);
                Cursor consulta2 = tecniLedDB.query("Grupo", null, "idProyecto=?", new String[]{String.valueOf(p.getId())}, null, null, null);
                if (consulta2.moveToFirst()) {
                    do {
                        Grupo g = new Grupo(consulta2.getInt(0), consulta2.getString(1), consulta2.getString(2));
                        p.getListaGrupos().add(g);

                        Cursor consulta3 = tecniLedDB.query("Foco", null, "idGrupo=?", new String[]{String.valueOf(g.getId())}, null, null, null);

                        if (consulta3.moveToFirst()) {
                            do {
                                Foco f = new Foco(consulta3.getInt(0), consulta3.getString(1), consulta3.getString(2), consulta3.getString(3), consulta3.getString(4), consulta3.getString(5));
                                g.getListaFocos().add(f);
                            } while (consulta3.moveToNext());
                        }
                    } while (consulta2.moveToNext());
                }
            } while (consulta1.moveToNext());



        }
    }

    private void intencionGrupos(int pos) {
        Intent intent = new Intent(this, GruposIntent.class);
        intent.putExtra("proyecto", listaProyectos.get(pos));
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tecniLedDB = new SQLiteDB(this, "TecniLed.db", null, 1).getWritableDatabase();

        recargaDatos();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewProyectos);
        recyclerView.setHasFixedSize(true);


        proyectoViewAdapter = new ProyectoViewAdapter(listaProyectos);

        proyectoViewAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intencionGrupos(recyclerView.getChildAdapterPosition(v));

            }
        });


        recyclerView.setAdapter(proyectoViewAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Animador de la lista
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        proyectoViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        CrearProyecto crearProyecto = new CrearProyecto(listaProyectos, proyectoViewAdapter);
        crearProyecto.show(getSupportFragmentManager(), "");


    }

    @Override
    protected void onStart() {
        System.out.println("OnStart");
        super.onStart();
        if (tecniLedDB.isOpen()) {
            recargaDatos();
            System.out.println("OnStart");
            return;
        } else
            tecniLedDB = new SQLiteDB(this, "TecniLed.db", null, 1).getWritableDatabase();


    }

    @Override
    protected void onResume() {
        System.out.println("OnResume");
        super.onResume();
        if (tecniLedDB.isOpen()) {
            recargaDatos();
            return;
        } else
            tecniLedDB = new SQLiteDB(this, "TecniLed.db", null, 1).getWritableDatabase();


    }

    @Override
    protected void onPause() {
        System.out.println("OnPause");
        super.onPause();


    }

    @Override
    protected void onStop() {
        System.out.println("OnStop");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tecniLedDB.close();
    }
}