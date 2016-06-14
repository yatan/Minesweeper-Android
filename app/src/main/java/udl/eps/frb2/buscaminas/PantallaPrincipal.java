package udl.eps.frb2.buscaminas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class PantallaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_settings:
                startActivity(new Intent(this, OpcionesActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void mostrarAyuda(View clickedButton) {
        Intent in = new Intent(this, PantallaAyuda.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(in);
        finish();
    }

    public void botonSalir(View clickedButton) {
        finish();
    }

    public void iniciar(View clickedButton) {
        Intent in = new Intent(this, MainActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(in);
        finish();
    }
}
