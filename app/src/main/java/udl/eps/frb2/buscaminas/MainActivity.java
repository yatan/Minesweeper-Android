package udl.eps.frb2.buscaminas;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by yatan on 6/10/16.
 */
public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego_fragment);

        /*FragmentListado frgListado = (FragmentListado)getSupportFragmentManager().findFragmentById(R.id.FrgListado);
        frgListado.setCorreosListener(this);*/
    }
}
