package dam.rafaaguilera.adivinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnIniciar;
    private Button btnAbortar;
    private Button btnJugador;

    private int rango;
    private int numeroAleatorio;
    private int numeroJugador;
    private boolean gameInProgress;

    private EditText rangoET;
    private EditText jugadorET;

    private TextView historialTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(this);

        btnAbortar = (Button) findViewById(R.id.btnAbortar);
        btnAbortar.setOnClickListener(this);

        btnJugador = (Button) findViewById(R.id.btnJugador);
        btnJugador.setOnClickListener(this);

        rangoET = (EditText) findViewById(R.id.rangoET);
        jugadorET = (EditText) findViewById(R.id.jugadorET);

        historialTV = (TextView) findViewById(R.id.historialTV);

        gameInProgress = false;

    }



    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.btnIniciar) {

            if (rangoET.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Introduce un rango...", Toast.LENGTH_SHORT).show();
                return;
            }

            if (gameInProgress){
                Toast.makeText(getApplicationContext(), "La partida ya ha empezado...", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                rango = Integer.parseInt(rangoET.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "El rango introducido no es valido...", Toast.LENGTH_SHORT).show();
                return;
            }

            gameInProgress = true;
            numeroAleatorio = (int) (Math.random() * rango + 1) ;
            System.out.println("" + numeroAleatorio);

        }

        if (v.getId()==R.id.btnJugador && gameInProgress) {


            try {
                numeroJugador = Integer.parseInt(jugadorET.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Error, introduce un numero entero", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if(numeroAleatorio==numeroJugador){
                Toast.makeText(getApplicationContext(), "¡Has ganado!", Toast.LENGTH_SHORT).show();
                gameInProgress=false;
            } else {
                Toast.makeText(getApplicationContext(), "Casi... Vuelve a intentarlo", Toast.LENGTH_SHORT).show();
                historialTV.setText(historialTV.getText().toString() + " "+ numeroJugador);
            }

        }

        if (v.getId()==R.id.btnAbortar && gameInProgress) {
            historialTV.setText("Historial: ");
            gameInProgress=false;
            numeroAleatorio=0;
            Toast.makeText(getApplicationContext(), "Partida abortada...", Toast.LENGTH_SHORT).show();
        }

    }
}