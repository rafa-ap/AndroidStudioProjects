package dam.rafaaguilera.tablamultiplicar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int n = Integer.parseInt(parent.getSelectedItem().toString());
        final TextView tablaTV[] = new TextView[11];

        tablaTV[0] = (TextView) findViewById(R.id.num0);
        tablaTV[1] = (TextView) findViewById(R.id.num1);
        tablaTV[2] = (TextView) findViewById(R.id.num2);
        tablaTV[3] = (TextView) findViewById(R.id.num3);
        tablaTV[4] = (TextView) findViewById(R.id.num4);
        tablaTV[5] = (TextView) findViewById(R.id.num5);
        tablaTV[6] = (TextView) findViewById(R.id.num6);
        tablaTV[7] = (TextView) findViewById(R.id.num7);
        tablaTV[8] = (TextView) findViewById(R.id.num8);
        tablaTV[9] = (TextView) findViewById(R.id.num9);
        tablaTV[10] = (TextView) findViewById(R.id.num10);

        for(int i = 0; i<=10;i++){
            tablaTV[i].setText(n+" x " + i + " = " + (n*i));
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


