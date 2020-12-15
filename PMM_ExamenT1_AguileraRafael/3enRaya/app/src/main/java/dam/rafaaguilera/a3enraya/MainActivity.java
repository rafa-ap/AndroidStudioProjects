package dam.rafaaguilera.a3enraya;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , CompoundButton.OnCheckedChangeListener {

    private ToggleButton startbtn;
    private GridLayout tablero;
    private ImageButton boton;
    private RadioGroup modoJuego;
    private RadioButton modoFacilRB;
    private RadioButton modoDificilRB;


    private int androidMove;
    private boolean modoDificil;

    private LinearLayout turnoAndroid;
    private LinearLayout turnoJugador;

    private Arbitro arbitro;

    private CountDownTimer cdt;

    private MediaPlayer userSound;
    private MediaPlayer androidSound;
    private MediaPlayer gameStart;
    private MediaPlayer gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turnoJugador = (LinearLayout) findViewById(R.id.turnoJugador);
        turnoAndroid = (LinearLayout) findViewById(R.id.turnoAndroid);

        startbtn = (ToggleButton) findViewById(R.id.startbtn);
        startbtn.setOnClickListener(this);

        modoJuego = (RadioGroup) findViewById(R.id.modoJuego);

        modoFacilRB = (RadioButton) findViewById(R.id.modoFacilRB);
        modoFacilRB.setOnCheckedChangeListener(this);
        modoDificilRB = (RadioButton) findViewById(R.id.modoDificilRB);
        modoDificilRB.setOnCheckedChangeListener(this);

        arbitro = new Arbitro();

        gameStart = MediaPlayer.create(getApplicationContext(), R.raw.gamestart);
        gameOver = MediaPlayer.create(getApplicationContext(), R.raw.gameover);
        userSound = MediaPlayer.create(getApplicationContext(), R.raw.usermove);
        androidSound = MediaPlayer.create(getApplicationContext(), R.raw.androidmove);



        tablero = findViewById(R.id.tablero);
        for (int i=0;i<9;i++){
            tablero.getChildAt(i).setOnClickListener(this);
            tablero.getChildAt(i).setClickable(false);

        }
    }

    @Override
    public void onClick(View v) {

        //Codigo para empezar el juego
        if(v.getId()==R.id.startbtn) {
            if (modoJuego.getCheckedRadioButtonId() != -1) {
                if (startbtn.isChecked()) {
                    Toast.makeText(getApplicationContext(), "The game is running...", Toast.LENGTH_SHORT).show();
                    modoJuego.getChildAt(0).setClickable(false);
                    modoJuego.getChildAt(1).setClickable(false);

                    for (int i = 0; i < 9; i++) {
                        tablero.getChildAt(i).setTag("v");
                        tablero.getChildAt(i).setClickable(true);
                    }
                    gameStart.start();
                    turnoJugador.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(getApplicationContext(), "The game is stopped!", Toast.LENGTH_SHORT).show();
                    modoJuego.getChildAt(0).setClickable(true);
                    modoJuego.getChildAt(1).setClickable(true);
                    for (int i = 0; i < 9; i++) {
                        tablero.getChildAt(i).setClickable(false);
                        boton = (ImageButton) findViewById(tablero.getChildAt(i).getId());
                        boton.setImageResource(0);
                    }
                    turnoJugador.setVisibility(View.INVISIBLE);
                    turnoAndroid.setVisibility(View.INVISIBLE);
                }

            }
            else{
                Toast.makeText(getApplicationContext(), "Selecciona un modo de juego antes...", Toast.LENGTH_SHORT).show();
                startbtn.setChecked(false);
            }
        }

        //Codigo de ejecucion durante el juego
        else {
            //Comienzo turno jugador
            userSound.start();
            boton = (ImageButton) findViewById(v.getId());
            boton.setImageResource(R.drawable.usericon);
            boton.setTag("j");
            boton.setClickable(false);
            turnoJugador.setVisibility(View.INVISIBLE);

            if (testWinner(arbitro.testWinner(tablero, "j"))) return;

            //Comienzo turno android, con un temporizador de espera para que se vea el icono turnoAndroid

            turnoAndroid.setVisibility(View.VISIBLE);
            for (int i = 0; i < 9; i++) {
                tablero.getChildAt(i).setClickable(false);
            }

            cdt = new CountDownTimer(1500, 1000) {
                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    androidSound.start();
                    for (int i = 0; i < 9; i++) {
                        if (tablero.getChildAt(i).getTag().equals("v")) tablero.getChildAt(i).setClickable(true);
                    }
                    if (!modoDificil) androidMove = new JugadaAleatoria().play(tablero);
                    else androidMove = new JugadaDirecta().play(tablero);

                    if(androidMove != -1) {

                        boton = (ImageButton) findViewById(tablero.getChildAt(androidMove).getId());
                        boton.setImageResource(R.drawable.androidicon);
                        boton.setTag("a");
                        boton.setClickable(false);
                        if (testWinner(arbitro.testWinner(tablero, "a"))) return;
                        turnoAndroid.setVisibility(View.INVISIBLE);
                        turnoJugador.setVisibility(View.VISIBLE);
                    }
                }
            }.start();




            for (int i=0;i<9;i++) System.out.println("Pos " + (i+1) + tablero.getChildAt(i).getTag());
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(modoFacilRB.isChecked()) modoDificil=false;
        if(modoDificilRB.isChecked()) modoDificil=true;

    }

    public boolean testWinner(char caso) {
        switch (caso) {
            case 'n':
                return false;
            case 'j':
                Toast.makeText(getApplicationContext(), "You Win!", Toast.LENGTH_SHORT).show();
                break;
            case 'a':
                Toast.makeText(getApplicationContext(), "Android Win!", Toast.LENGTH_SHORT).show();
                gameOver.start();
                break;
            case 't':
                Toast.makeText(getApplicationContext(), "Ooops!, nobody Wins.", Toast.LENGTH_SHORT).show();
                break;
        }

        startbtn.setChecked(false);
        cdt.cancel();
        modoJuego.getChildAt(0).setClickable(true);
        modoJuego.getChildAt(1).setClickable(true);
        for (int i = 0; i < 9; i++) {
            tablero.getChildAt(i).setClickable(false);
            boton = (ImageButton) findViewById(tablero.getChildAt(i).getId());
            boton.setImageResource(0);
        }
        turnoJugador.setVisibility(View.INVISIBLE);
        turnoAndroid.setVisibility(View.INVISIBLE);
        return true;

    }
}