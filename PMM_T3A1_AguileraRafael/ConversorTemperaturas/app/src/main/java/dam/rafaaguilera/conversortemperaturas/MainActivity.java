package dam.rafaaguilera.conversortemperaturas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    public EditText tempFinalET;
    public EditText tempInicialET;



    public RadioButton iniC;
    public RadioButton iniF;
    public RadioButton iniK;
    public RadioButton finC;
    public RadioButton finF;
    public RadioButton finK;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView termometros = (ImageView) findViewById(R.id.termometros);
        termometros.setOnClickListener(this);

        tempInicialET = (EditText) findViewById(R.id.tempInicialET);
        tempFinalET = (EditText) findViewById(R.id.tempFinalET);



        iniC = (RadioButton) findViewById(R.id.iniC);
        iniF = (RadioButton) findViewById(R.id.iniF);
        iniK = (RadioButton) findViewById(R.id.iniK);

        finC = (RadioButton) findViewById(R.id.finC);
        finF = (RadioButton) findViewById(R.id.finF);
        finK = (RadioButton) findViewById(R.id.finK);



    }


    @Override
    public void onClick(View v) {

        try {
            Integer.parseInt(tempInicialET.getText().toString());
        }
        catch (NumberFormatException e) {return;}


        if (iniC.isChecked()) {
            if (finC.isChecked()) {
                tempFinalET.setText(tempInicialET.getText() + " ºC");
            }
            if (finF.isChecked()) {
                tempFinalET.setText(Math.round(Double.parseDouble(tempInicialET.getText().toString()) * 9 / 5 + 32) + " ºF");
            }
            if (finK.isChecked()) {
                tempFinalET.setText(Math.round(Double.parseDouble(tempInicialET.getText().toString()) + 273.15) + " K");
            }

        }
        if (iniF.isChecked()) {
            if (finC.isChecked()) {
                tempFinalET.setText(Math.round((Double.parseDouble(tempInicialET.getText().toString()) - 32) * 5 / 9) + " ºC");

            }
            if (finF.isChecked()) {
                tempFinalET.setText(tempInicialET.getText() + " ºF");

            }
            if (finK.isChecked()) {
                tempFinalET.setText(Math.round((Double.parseDouble(tempInicialET.getText().toString()) - 32) * 5 / 9 + 273.15) + " K");
            }

        }

        if (iniK.isChecked()) {
            if (finC.isChecked()) {
                tempFinalET.setText(Math.round(Double.parseDouble(tempInicialET.getText().toString()) - 273.15) + " ºC");

            }
            if (finF.isChecked()) {
                tempFinalET.setText(Math.round((Double.parseDouble(tempInicialET.getText().toString()) - 273.15) * 9 / 5 + 32) + " ºF");

            }
            if (finK.isChecked()) {
                tempFinalET.setText(tempInicialET.getText() + " K");
            }

        }


    }


}