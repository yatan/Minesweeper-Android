package udl.eps.frb2.buscaminas;


import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class DetalleResultados extends Fragment {
    TextView detalle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.list_detail, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        detalle = (TextView) getView().findViewById(R.id.detalle_resultado);

        Fragment frag = getFragmentManager().findFragmentById(R.id.fragment_contenedor);
        if (frag != null && frag instanceof ListadoResultados == false) {
            Bundle saved = this.getArguments();
            int consulta = saved.getInt("consulta");

            ResultadosSQLiteHelper resultdb = new ResultadosSQLiteHelper(getActivity(), "baseDB", null, 1);
            SQLiteDatabase db = resultdb.getWritableDatabase();
            Cursor cursor = resultdb.getResult(db, consulta);

            detalle.setText("Alias: "+String.valueOf( cursor.getString(1).toString()));
            detalle.append("\nFecha: "+String.valueOf( cursor.getString(2).toString()));
            detalle.append("\nColumnas: "+String.valueOf( cursor.getString(3).toString()));
            detalle.append("\nTiempo: "+String.valueOf( cursor.getString(4).toString()));
        }
    }
    public void MostrarResultado(int id){

        ResultadosSQLiteHelper resultdb = new ResultadosSQLiteHelper(getActivity(), "baseDB", null, 1);
        SQLiteDatabase db = resultdb.getWritableDatabase();
        Cursor cursor = resultdb.getResult(db, id);

        detalle.setText("Alias: "+String.valueOf( cursor.getString(1).toString()));
        detalle.append("\nFecha: "+String.valueOf( cursor.getString(2).toString()));
        detalle.append("\nColumnas: "+String.valueOf( cursor.getString(3).toString()));
        detalle.append("\nTiempo: "+String.valueOf( cursor.getString(4).toString()));
    }
}
