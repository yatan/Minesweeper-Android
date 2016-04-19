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
import java.util.Random;

/**
 * Created by yatan on 4/18/16.
 */
public class Juego extends Activity implements OnClickListener {
    static final String STATE_GRAELLA = "graella";
    static final String STATE_BOMBS = "bombcount";

    ArrayList<Button> mButtons = new ArrayList<Button>();
    int columnes = 3;
    int bombcount = 0;
    int [][] graella = new int[columnes][columnes];
    Random r = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        /*
        Creació del array
         */

        if(savedInstanceState != null){
            graella = savedInstanceState.getIntArray(STATE_GRAELLA);
            bombcount = savedInstanceState.getInt(STATE_BOMBS);
        }

        if(bombcount == 0) {
            for (int i = 0; i < columnes; i++) {
                for (int j = 0; j < columnes; j++) {
                    if (i != 0 || i != columnes) {
                        //Bomba random
                        if (r.nextInt(3) == 1) {
                            graella[i][j] = 1;
                            bombcount += 1;
                        } else
                            graella[i][j] = 0;
                    } else
                        graella[i][j] = 0;
                }
            }
        }
        Button cb = null;

        for(int i=0; i<columnes;i++){
            for(int j=0 ; j<columnes; j++) {
                cb = new Button(this);

                if(graella[i][j] == 0)
                    cb.setText(" ");
                else if(graella[i][j] == 1)
                    cb.setText("B");

                cb.setId(i);
                cb.setOnClickListener(this);
                registerForContextMenu(cb);
                mButtons.add(cb);
            }
        }
        GridView gridView = (GridView)findViewById(R.id.gridView);
        gridView.setAdapter(new CustomAdapter(mButtons));
    }

    @Override
    public void onClick(View v){
        Button selection = (Button)v;
        Toast.makeText(getBaseContext(), selection.getText()+" was pressed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putIntArray(STATE_GRAELLA, graella);
        savedInstanceState.putInt(STATE_BOMBS, bombcount);

        super.onSaveInstanceState(savedInstanceState);
    }
}
