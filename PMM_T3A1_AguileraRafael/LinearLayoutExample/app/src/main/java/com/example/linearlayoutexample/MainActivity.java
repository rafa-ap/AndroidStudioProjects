package com.example.linearlayoutexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView lblEtiqueta = (TextView) findViewById(R.id.textoplano);
        String texto = lblEtiqueta.getText().toString();
        texto += "nuevo";
        lblEtiqueta.setText(texto);

        final EditText editText = (EditText) findViewById(R.id.editText);

        final Button btn1 = (Button) findViewById(R.id.boton);

        btn1.setOnClickListener(e->{
            //lblEtiqueta.setText(editText.getText().toString());
            System.out.println("Pulsado");
        });

        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        toggleButton.setOnClickListener(e->{
            if(toggleButton.isChecked()) lblEtiqueta.setText("Encendido");
            else lblEtiqueta.setText(R.string.app_name);
        });



    }
}