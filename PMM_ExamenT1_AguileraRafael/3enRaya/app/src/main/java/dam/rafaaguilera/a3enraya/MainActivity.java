package dam.rafaaguilera.a3enraya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GridLayout tablero;
    private ImageButton boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       tablero = findViewById(R.id.tablero);
        for (int i=0;i<9;i++){
            tablero.getChildAt(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // do something after 1s
            }

            @Override
            public void onFinish() {
                boton = (ImageButton) findViewById(v.getId());
                System.out.println("pulsado" + boton.getId());
                boton.setImageResource(R.drawable.usericon);
            }

        }.start();



    }
}