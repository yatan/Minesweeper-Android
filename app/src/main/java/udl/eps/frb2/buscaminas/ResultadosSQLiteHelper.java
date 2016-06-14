package udl.eps.frb2.buscaminas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ResultadosSQLiteHelper extends SQLiteOpenHelper {
    String sqlCreate = "CREATE TABLE Resultados " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "alias TEXT, " +
            "fecha TEXT, " +
            "columnas TEXT, " +
            "tiempo INTEGER )";
    public ResultadosSQLiteHelper(Context context, String nombre,
                                  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS Resultados");
        db.execSQL(sqlCreate);
    }
}
