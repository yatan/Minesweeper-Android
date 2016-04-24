package udl.eps.frb2.buscaminas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by yatan on 4/18/16.
 */
public class PantallaConfiguracion extends AppCompatActivity {

    EditText columnas;
    EditText nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracion);
        columnas = (EditText) findViewById(R.id.columnas);
        nick = (EditText) findViewById(R.id.editText2);
    }

    public void iniciarJuego(View clickedButton) {
        Intent in = new Intent(this, Juego.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        in.putExtra("columnas", columnas.getText().toString());
        in.putExtra("alias", nick.getText().toString());
        startActivityForResult(in, 1);
        finish();
    }
}
