package dam.rafaaguilera.tecniled.SQLiteDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDB extends SQLiteOpenHelper {

    // Sentencia SQL para crear la tabla Ejemplares
    static String createTableFoco = "CREATE TABLE Foco(_id integer primary key autoincrement ,nombre TEXT ,marca TEXT ,direccion TEXT ,canal TEXT,dmx TEXT, idGrupo INTEGER, FOREIGN KEY(idGrupo) REFERENCES Grupo(_id));";
    static String createTableGrupo = "CREATE TABLE Grupo(_id integer primary key autoincrement ,nombre TEXT, ubicacion TEXT, idProyecto INTEGER, FOREIGN KEY(idProyecto) REFERENCES Proyecto(_id));";
    static String createTableProyecto = "CREATE TABLE Proyecto(_id integer primary key autoincrement ,nombre TEXT, fecha TEXT, observaciones TEXT);";


    // Define el constructor indicando el contexto de la aplicacion ,
    // el nombre de la base de datos y la version de la BD
    public SQLiteDB(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    // Si la BD no existe , Android llama a este metodo
    public void onCreate(SQLiteDatabase db) {
        // Crea la tabla
        db.execSQL(createTableProyecto);
        db.execSQL(createTableGrupo);
        db.execSQL(createTableFoco);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
 /* NOTA : para simplificar este ejemplo eliminamos
directamente la tabla
 anterior y la creamos de nuevo .
 Sin embargo , lo normal seria migrar los datos de la tabla
antigua
 a la nueva estructura de campos , por lo que las sentencias
podrian
 ser del estilo ALTER TABLE .
 */

        // Se elimina la version anterior de la tabla
        //db.execSQL(" DROP TABLE IF EXISTS Ejemplares ");

        // Se crea la nueva version de la tabla
        //db.execSQL(createBDSQL);
    }
}