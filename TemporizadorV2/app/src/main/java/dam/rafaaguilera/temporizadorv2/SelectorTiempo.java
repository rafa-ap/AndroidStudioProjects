package dam.rafaaguilera.temporizadorv2;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SelectorTiempo extends ConstraintLayout {


    private TextView horasText;
    private TextView minText;
    private TextView segText;

    private Button btnSumHoras;
    private Button btnSumMin;
    private Button btnSumSeg;

    private Button btnRestHoras;
    private Button btnRestMin;
    private Button btnRestSeg;

    private TouchListener touchListener;

    private long nSeg;


    public SelectorTiempo(Context context) {
        super(context);
        inicializar();
    }

    public SelectorTiempo(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        touchListener = new TouchListener();

        inicializar();

    }

    private void inicializar() {

        //Utilizamos el diseño layout 'selector_tiempo' como interfaz del componente
        // Primero obtener el inflater
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);


        // Inflamos el componente compuesto definido en el XML
        li.inflate(R.layout.selector_tiempo, this, true);

        //Referencias de componentes

        horasText = (TextView) findViewById(R.id.horasText);
        minText = (TextView) findViewById(R.id.minText);
        segText = (TextView) findViewById(R.id.segText);

        btnSumHoras = (Button) findViewById(R.id.btnSumHoras);
        btnSumMin = (Button) findViewById(R.id.btnSumMin);
        btnSumSeg = (Button) findViewById(R.id.btnSumSeg);
        btnRestHoras = (Button) findViewById(R.id.btnRestHoras);
        btnRestMin = (Button) findViewById(R.id.btnRestmin);
        btnRestSeg = (Button) findViewById(R.id.btnRestSeg);

        btnSumHoras.setOnTouchListener(touchListener);
        btnSumMin.setOnTouchListener(touchListener);
        btnSumSeg.setOnTouchListener(touchListener);
        btnRestHoras.setOnTouchListener(touchListener);
        btnRestMin.setOnTouchListener(touchListener);
        btnRestSeg.setOnTouchListener(touchListener);


    }

    public long getnSeg() {
        return nSeg;
    }

    private class TouchListener implements View.OnTouchListener {
        TimeChangerThread timeChangerThread;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                timeChangerThread = new TimeChangerThread();
                timeChangerThread.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR ,v.getId());
            }

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (timeChangerThread != null) timeChangerThread.cancel(true);
            }

            return false;
        }

        private class TimeChangerThread extends AsyncTask<Integer, Integer, Void> {

            @Override
            protected void onProgressUpdate(Integer... values) {
                timeChanger(values[0]);

            }

            @Override
            protected Void doInBackground(Integer... integers) {
                int id = integers[0];
                long startTime = System.currentTimeMillis();
                long lifeTime = 0;

                publishProgress(id);

                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                }

                while (!isCancelled()) {
                    if (lifeTime < 2300){
                        lifeTime = System.currentTimeMillis() - startTime;
                        publishProgress(id);
                        try {
                            Thread.sleep(80);
                        } catch (InterruptedException e) {
                        }


                    } else {
                        publishProgress(id);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                        }
                    }

                }
                return null;
            }


            private void timeChanger(int id) {

                    if(id == R.id.btnSumHoras){
                        nSeg+=3600;
                    }

                    if(id == R.id.btnSumMin){
                        nSeg+=60;
                    }

                    if(id == R.id.btnSumSeg){
                        nSeg+=1;
                    }

                    if(id == R.id.btnRestHoras){
                        if(nSeg<3600) return;
                        nSeg-=3600;

                    }

                    if(id == R.id.btnRestmin){
                        if(nSeg<60) return;
                        nSeg-=60;
                    }

                    if(id == R.id.btnRestSeg){
                        if(nSeg<=0) return;
                        nSeg-=1;
                    }

                long horasTemp = nSeg / 3600;
                long minTemp = (nSeg-horasTemp*3600)/60;
                long segTemp = nSeg-(horasTemp*3600+minTemp*60);

                horasText.setText(String.valueOf(horasTemp));
                minText.setText(String.valueOf(minTemp));
                segText.setText(String.valueOf(segTemp));

            }

        }
    }
}