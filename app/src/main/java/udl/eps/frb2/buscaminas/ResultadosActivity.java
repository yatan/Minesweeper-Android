package udl.eps.frb2.buscaminas;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ResultadosActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_fragment);
    }

    public void volverPrincipal(View clickedButton) {
        Intent in = new Intent(this, PantallaPrincipal.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(in);
        finish();
    }
}
