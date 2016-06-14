package udl.eps.frb2.buscaminas;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListadoResultados extends ListFragment implements AdapterView.OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ResultadosSQLiteHelper resultdb = new ResultadosSQLiteHelper(getActivity(), "baseDB", null, 1);
        SQLiteDatabase db = resultdb.getWritableDatabase();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_entry,
                resultdb.getAllResults(db),
                new String[] { "alias", "fecha" },
                new int[] {R.id.alias_entry, R.id.tiempo_entry}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.porcentaje, android.R.layout.simple_expandable_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        SQLiteCursor cosa = (SQLiteCursor) parent.getItemAtPosition(position);
        Bundle in = new Bundle();
        in.putInt("consulta",Integer.valueOf(cosa.getString(0)));

        Fragment newFragment = new DetalleResultados();
        newFragment.setArguments(in);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_contenedor, newFragment);
        transaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        //transaction.addToBackStack(null);
        transaction.commit();
        //startActivityForResult(in, 1);
        //Toast.makeText(getActivity(), "Item: " + cosa.getString(0), Toast.LENGTH_SHORT).show();
    }
}
