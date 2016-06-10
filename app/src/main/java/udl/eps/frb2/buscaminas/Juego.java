package udl.eps.frb2.buscaminas;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.os.Handler;

import android.app.Activity;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by yatan on 4/18/16.
 */
public class Juego extends FragmentActivity implements OnClickListener {
    static final String STATE_GRAELLA = "graella";
    static final String STATE_BOMBS = "bombcount";

    private Handler timer = new Handler();
    private int secondsPassed = 0;

    TextView txtTimer;


    ArrayList<Button> mButtons = new ArrayList<Button>();

    String alias = "";

    int columnes = 5;
    int bombcount = 0;
    int [][] graella;
    Random r = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        txtTimer = (TextView) findViewById(R.id.textView);

        Intent inten = getIntent();
        String colum = inten.getStringExtra("columnas");
        alias = inten.getStringExtra("alias");
        columnes = Integer.parseInt(colum);
        graella = new int[columnes][columnes];

        /*
        Creació del array
         */
/*
        if(savedInstanceState != null){
            graella = savedInstanceState.getIntArray(STATE_GRAELLA);
            bombcount = savedInstanceState.getInt(STATE_BOMBS);
        }
*/
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
        int contador = 0;
        for(int i=0; i<columnes;i++){
            for(int j=0 ; j<columnes; j++) {
                cb = new Button(this);

                //if(graella[i][j] == 0)
                    cb.setText(" ");
                //else if(graella[i][j] == 1)
                //    cb.setText("B");


                cb.setId(contador);
                contador += 1;
                cb.setOnClickListener(this);
                registerForContextMenu(cb);
                mButtons.add(cb);
            }
        }
        GridView gridView = (GridView)findViewById(R.id.gridView);
        gridView.setAdapter(new CustomAdapter(mButtons));
        gridView.setNumColumns(columnes);
        startTimer();
    }

    @Override
    public void onClick(View v){
        Button selection = (Button)v;
        int id = selection.getId();
        int fila = id / columnes;
        int columna = id % columnes;


        FragmentDetalle fgdet = (FragmentDetalle) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
        fgdet.mostrarDetalle("Hola");


        //Es bomba ?
        if(graella[fila][columna] == 1){
            Toast.makeText(getBaseContext(), "Booom", Toast.LENGTH_SHORT).show();
            DescobrirMines();
        }
        else
        {
            int numero = getValorCasilla(fila, columna);
            selection.setText(String.valueOf(numero));
            if(numero == 0)
                selection.setTextColor(Color.BLACK);
            else if(numero == 1)
                selection.setTextColor(Color.parseColor("blue"));
            else if(numero == 2)
                selection.setTextColor(Color.parseColor("green"));
            else if(numero == 3)
                selection.setTextColor(Color.parseColor("red"));
            else
                selection.setTextColor(Color.MAGENTA);

            //Toast.makeText(getBaseContext(), "Hay " + getValorCasilla(fila, columna), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getBaseContext(), selection.getText()+" was "+fila+"-"+columna, Toast.LENGTH_SHORT).show();

        }

    }

    public int getValorCasilla(int fila, int columna){
        //int fila = posicion / columnes;
        //int columna = posicion % columnes;
        int contadorBombas = 0;
        //Verificar si estamos en esquinas
        if(fila == 0 && columna == 0)
        {
            //Estamos en esquina superior-izq
            if(graella[fila+1][columna] == 1)
                contadorBombas += 1;
            if(graella[fila][columna+1] == 1)
                contadorBombas += 1;
            if(graella[fila+1][columna+1] == 1)
                contadorBombas += 1;
        }
        else if(fila == columnes-1 && columna == columnes-1)
        {
            //Esquina inferior derecha
            if(graella[fila-1][columna] == 1)
                contadorBombas += 1;
            if(graella[fila][columna-1] == 1)
                contadorBombas += 1;
            if(graella[fila-1][columna-1] == 1)
                contadorBombas += 1;
        }
        else if(fila == columnes-1 && columna == 0)
        {
            //Esquina inferior izquierda
            if(graella[fila-1][columna] == 1)
                contadorBombas += 1;
            if(graella[fila][columna+1] == 1)
                contadorBombas += 1;
            if(graella[fila-1][columna+1] == 1)
                contadorBombas += 1;
        }
        else if(fila == 0 && columna == columnes-1)
        {
            //Esquina superior derecha
            if(graella[fila+1][columna] == 1)
                contadorBombas += 1;
            if(graella[fila][columna-1] == 1)
                contadorBombas += 1;
            if(graella[fila+1][columna-1] == 1)
                contadorBombas += 1;
        }
        //Verificar limites
        //Fila 0
        else if(fila == 0)
        {
            //Cualquier otra posicion
            if(graella[fila+1][columna] == 1)
                contadorBombas += 1;
            if(graella[fila][columna+1] == 1)
                contadorBombas += 1;
            if(graella[fila][columna-1] == 1)
                contadorBombas += 1;
            //Diagonales
            if(graella[fila+1][columna+1] == 1)
                contadorBombas += 1;
            if(graella[fila+1][columna-1] == 1)
                contadorBombas += 1;
        }
        //Fila maxima
        else if(fila == columnes-1)
        {
            //Cualquier otra posicion
            if(graella[fila-1][columna] == 1)
                contadorBombas += 1;
            if(graella[fila][columna+1] == 1)
                contadorBombas += 1;
            if(graella[fila][columna-1] == 1)
                contadorBombas += 1;
            //Diagonales
            if(graella[fila-1][columna-1] == 1)
                contadorBombas += 1;
            if(graella[fila-1][columna+1] == 1)
                contadorBombas += 1;
        }
        //Columna 0
        else if(columna == 0)
        {
            //Cualquier otra posicion
            if(graella[fila+1][columna] == 1)
                contadorBombas += 1;
            if(graella[fila-1][columna] == 1)
                contadorBombas += 1;
            if(graella[fila][columna+1] == 1)
                contadorBombas += 1;
            //Diagonales
            if(graella[fila+1][columna+1] == 1)
                contadorBombas += 1;
            if(graella[fila-1][columna+1] == 1)
                contadorBombas += 1;
        }
        //Columna maxima
        else if(columna == columnes - 1)
        {
            //Cualquier otra posicion
            if(graella[fila+1][columna] == 1)
                contadorBombas += 1;
            if(graella[fila-1][columna] == 1)
                contadorBombas += 1;
            if(graella[fila][columna-1] == 1)
                contadorBombas += 1;
            //Diagonales
            if(graella[fila-1][columna-1] == 1)
                contadorBombas += 1;
            if(graella[fila+1][columna-1] == 1)
                contadorBombas += 1;
        }
        else {
            //Cualquier otra posicion
            if(graella[fila+1][columna] == 1)
                contadorBombas += 1;
            if(graella[fila-1][columna] == 1)
                contadorBombas += 1;
            if(graella[fila][columna+1] == 1)
                contadorBombas += 1;
            if(graella[fila][columna-1] == 1)
                contadorBombas += 1;
            //Diagonales
            if(graella[fila+1][columna+1] == 1)
                contadorBombas += 1;
            if(graella[fila-1][columna-1] == 1)
                contadorBombas += 1;
            if(graella[fila+1][columna-1] == 1)
                contadorBombas += 1;
            if(graella[fila-1][columna+1] == 1)
                contadorBombas += 1;
        }
        return contadorBombas;
    }

    public void Mensaje(String str){
        Toast.makeText(getBaseContext(), str.toString(), Toast.LENGTH_SHORT).show();
    }

    public void DescobrirMines(){
        stopTimer();
        for (Button casilla:mButtons) {
            int id = casilla.getId();
            int fila = id / columnes;
            int columna = id % columnes;
            if(graella[fila][columna]==1) {
                casilla.setText("MINA");
                //casilla.setBackground(getResources().getDrawable(R.drawable.mina));
            }

        }
        Intent in = new Intent(this, PantallaResultado.class);
        in.putExtra("columnas", String.valueOf(columnes));
        in.putExtra("alias", alias);
        in.putExtra("tiempo", txtTimer.getText().toString());
        startActivityForResult(in, 1);
        finish();
    }

    public void startTimer()
    {
        if (secondsPassed == 0)
        {
            timer.removeCallbacks(updateTimeElasped);
            // tell timer to run call back after 1 second
            timer.postDelayed(updateTimeElasped, 1000);
        }
    }

    public void stopTimer()
    {
        // disable call backs
        timer.removeCallbacks(updateTimeElasped);
    }

    // timer call back when timer is ticked
    private Runnable updateTimeElasped = new Runnable()
    {
        public void run()
        {
            long currentMilliseconds = System.currentTimeMillis();
            ++secondsPassed;
            txtTimer.setText("Tiempo " + Integer.toString(secondsPassed));

            // add notification
            timer.postAtTime(this, currentMilliseconds);
            // notify to call back after 1 seconds
            // basically to remain in the timer loop
            timer.postDelayed(updateTimeElasped, 1000);
        }
    };

}
