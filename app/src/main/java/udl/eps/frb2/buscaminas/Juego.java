package udl.eps.frb2.buscaminas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import android.app.Activity;
import android.view.View.OnClickListener;

import java.util.ArrayList;

/**
 * Created by yatan on 4/18/16.
 */
public class Juego extends Activity implements OnClickListener {
    ArrayList<Button> mButtons = new ArrayList<Button>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        Button cb = null;

        for(int i=0; i<9;i++){
            cb = new Button(this);
            cb.setText("B");
            cb.setId(i);
            cb.setOnClickListener(this);
            registerForContextMenu(cb);
            mButtons.add(cb);
        }
        GridView gridView = (GridView)findViewById(R.id.gridView);
        gridView.setAdapter(new CustomAdapter(mButtons));
    }

    @Override
    public void onClick(View v){
        Button selection = (Button)v;
        Toast.makeText(getBaseContext(), selection.getText()+" was pressed!", Toast.LENGTH_SHORT).show();
    }
}
