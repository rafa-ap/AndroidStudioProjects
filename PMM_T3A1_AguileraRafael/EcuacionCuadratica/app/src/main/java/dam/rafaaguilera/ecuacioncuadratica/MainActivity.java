package dam.rafaaguilera.ecuacioncuadratica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClikCalcula (View v) {

        final EditText aET = (EditText) findViewById(R.id.aET);
        final EditText bET = (EditText) findViewById(R.id.bET);
        final EditText cET = (EditText) findViewById(R.id.cET);

        int a, b, c;

        final TextView resultado = (TextView) findViewById(R.id.resultado);

        if (aET.getText().toString().equals("") || bET.getText().toString().equals("") || cET.getText().toString().equals("")) {
            resultado.setText("Rellana todos los campos.");
            return;
        }

        try {
            a = Integer.parseInt(aET.getText().toString());
            b = Integer.parseInt(bET.getText().toString());
            c = Integer.parseInt(cET.getText().toString());



        }
        catch (NumberFormatException e) {
            resultado.setText("Los valores introducidos no son correctos");
            return;
        }

        final double sqrt = Math.sqrt(Math.pow(b, 2) - 4 * a * c);

        double x1=(-b+ sqrt)/2*a;
        double x2=(-b- sqrt)/2*a;

        resultado.setText("Resultado 1:  " + x1 + "\nResultado 2:  " + x2);







    }
}