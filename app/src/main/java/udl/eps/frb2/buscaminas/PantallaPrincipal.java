package udl.eps.frb2.buscaminas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class PantallaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
    }

    public void mostrarAyuda(View clickedButton) {
        Intent in = new Intent(this, PantallaAyuda.class);
        startActivity(in);
    }

    public void iniciar(View clickedButton) {
        Intent in = new Intent(this, Juego.class);
        startActivity(in);
    }
}
