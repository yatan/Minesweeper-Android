package udl.eps.frb2.buscaminas;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;

/**
 * Created by yatan on 4/18/16.
 */
public class PantallaResultado extends AppCompatActivity{

    TextView textviu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);
        textviu = (TextView) findViewById(R.id.textView_Resultado);
        Intent inten = getIntent();
        String colum = inten.getStringExtra("columnas");
        String alias = inten.getStringExtra("alias");
        String tiempo = inten.getStringExtra("tiempo");
        textviu.append("Alias: " + alias);
        textviu.append("\nColumnas elegidas: " + colum);
        textviu.append("\nTiempo gastado: " + tiempo + " segundos.");

        ResultadosSQLiteHelper resultdb = new ResultadosSQLiteHelper(this, "baseDB", null, 1);
        SQLiteDatabase db = resultdb.getWritableDatabase();

        if(db != null)
        {
            ContentValues nuevoResultado = new ContentValues();
            nuevoResultado.put("alias", alias);
            nuevoResultado.put("fecha", "0/0/0");
            nuevoResultado.put("columnas", colum);
            nuevoResultado.put("tiempo", tiempo);

            db.insert("Resultados", null, nuevoResultado);
            db.close();
        }

    }

    public void volverPrincipal(View clickedButton) {
        Intent in = new Intent(this, PantallaPrincipal.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(in);
        finish();
    }

    public void enviarLOG(View clickedButton) {
        Toast.makeText(this, "Enviando mail de log", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
        intent.setData(Uri.parse("mailto:"));
        //intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Resultado buscaminas");
        intent.putExtra(Intent.EXTRA_EMAIL, "frb2@alumnes.udl.cat");
        intent.putExtra(Intent.EXTRA_TEXT, textviu.getText().toString());
        startActivity(intent);
    }
}
