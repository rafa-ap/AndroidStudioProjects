package dam.rafaaguilera.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int nIntentos = 0;
    public final String intentosString= "Intentos: ";
    public final String logueadoString= "Logueado: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }

    public void onClickBtnCacel(View v){
        final EditText user = (EditText) findViewById(R.id.user);
        final EditText pass = (EditText) findViewById(R.id.pass);




        user.setText("");
        pass.setText("");


    }

    public void onClickBtnLogin(View v){
        final EditText user = (EditText) findViewById(R.id.user);
        final EditText pass = (EditText) findViewById(R.id.pass);

        final TextView intentos = (TextView) findViewById(R.id.intentos);
        final TextView logueado = (TextView) findViewById(R.id.logueado);


        nIntentos++;
        intentos.setText(intentosString + "" + nIntentos);


        if (user.getText().toString().equals("rafa") && pass.getText().toString().equals("rafa"))
            logueado.setText(logueadoString + "OK");


    }
}