package dam.rafaaguilera.temporizadorv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.progressindicator.*;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int COD_PETICION_SONIDO = 10;

    private SelectorTiempo selectorTiempo;
    private ImageButton btnPlayPause;
    private boolean play;

    //private MediaPlayer alarmaSound;
    private Reproductor reproductor;


    private TextView temporizadorText;

    private long pausedTime;
    private long startedTime;

    private Temporizador t;

    private CircularProgressIndicator circularProgressIndicator;

    private Button btnParar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectorTiempo = (SelectorTiempo) findViewById(R.id.SelectorTiempo);
        btnPlayPause = (ImageButton) findViewById(R.id.btnPlayPause);
        play = false;

        temporizadorText = (TextView) findViewById(R.id.temporizadorText);
        circularProgressIndicator = (CircularProgressIndicator) findViewById(R.id.circularProgressIndicator);
        circularProgressIndicator.setMax(100);
        circularProgressIndicator.setProgress(100);

        reproductor = new Reproductor(getApplicationContext());

        btnParar = (Button) findViewById(R.id.btnParar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        reproductor.release();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_ppal, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item1){
            Intent intent = new Intent(this, CambiarSonidoViewController.class);
            startActivityForResult(intent, COD_PETICION_SONIDO);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COD_PETICION_SONIDO){
            if(resultCode == RESULT_OK){
                if(data.hasExtra("idSonido")){
                    reproductor.setSound(data.getExtras().getInt("idSonido"));
                    Toast.makeText(getApplicationContext(), "Se ha cambiado el sonido de alarma", Toast.LENGTH_SHORT).show();
                }
            }
        }



    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnPlayPause){
            play = !play;
            if(play){
                t = new Temporizador();
                if (pausedTime == 0) {
                    startedTime = selectorTiempo.getnSeg();
                    if (startedTime > 0) {
                        btnPlayPause.setImageResource(R.drawable.pause_squared_96px);
                        circularProgressIndicator.setMax((int) startedTime);
                        circularProgressIndicator.setProgress((int) startedTime);
                        t.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, startedTime);
                    }
                } else{
                    btnPlayPause.setImageResource(R.drawable.pause_squared_96px);
                    t.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, pausedTime);

                }

            } else {
                btnPlayPause.setImageResource(R.drawable.start_96px);
                if(t.getStatus()== AsyncTask.Status.RUNNING || t.getStatus()== AsyncTask.Status.FINISHED){
                    t.cancel(true);
                }
            }
        }

        if (v.getId() == R.id.btnStop && t != null){
            if(t.getStatus()== AsyncTask.Status.RUNNING || t.getStatus()== AsyncTask.Status.FINISHED){
                t.cancel(true);
                temporizadorText.setText("00:00:00");
                pausedTime = 0;
                play = false;
                btnPlayPause.setImageResource(R.drawable.start_96px);
                circularProgressIndicator.setMax(100);
                circularProgressIndicator.setProgress(100);
                reproductor.stop();
            }
        }

        if (v.getId() == R.id.btnParar){
            if(t.getStatus()== AsyncTask.Status.FINISHED){
                temporizadorText.setText("00:00:00");
                pausedTime = 0;
                play = false;
                btnPlayPause.setImageResource(R.drawable.start_96px);
                circularProgressIndicator.setMax(100);
                circularProgressIndicator.setProgress(100);
                reproductor.stop();
                btnParar.setEnabled(false);
                btnParar.setVisibility(View.INVISIBLE);
            }
        }

    }

    private class Temporizador extends AsyncTask<Long, Long, Void> {
        private final DecimalFormat f = new DecimalFormat("00");
        @Override
        protected void onPostExecute(Void aVoid) {

            reproductor.start();
            btnParar.setVisibility(View.VISIBLE);
            btnParar.setEnabled(true);


        }

        @Override
        protected void onProgressUpdate(Long... longs) {
            temporizadorText.setText(f.format(longs[0]) + ":" + f.format(longs[1]) + ":" + f.format(longs[2]));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                circularProgressIndicator.setProgress(longs[3].intValue(), true);
            }
        }

        @Override
        protected Void doInBackground(Long... values) {

            long nSeg = values[0];

            while (nSeg > 0 && !isCancelled()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

                nSeg--;
                long horasTemp = nSeg / 3600;
                long minTemp = (nSeg-horasTemp*3600)/60;
                long segTemp = nSeg-(horasTemp*3600+minTemp*60);

                publishProgress(horasTemp, minTemp, segTemp, nSeg);
            }

            if (nSeg > 0) pausedTime = nSeg+1;
            return null;
        }
    }
}