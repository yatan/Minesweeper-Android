package udl.eps.frb2.buscaminas;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class FragmentJuego extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.game, container, false);
    }
    /*
    public void mostrarDetalle(String texto) {

        TextView txtDetalle;
        txtDetalle = (TextView) getView().findViewById(R.id.TxtDetalle);
        txtDetalle.setText(texto);
    }
    */
}