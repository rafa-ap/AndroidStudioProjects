package dam.rafaaguilera.calculadoraandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ParseException;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }

    public void OnClickBtn(View v){
        final TextView prompt= findViewById(R.id.prompt);
        if (prompt.getText().toString().equals("Operación invalida")) prompt.setText("");
        if (prompt.getText().toString().equals("Vuelve al cole")) prompt.setText("");

        switch (v.getId()){
            case R.id.btn0:
                prompt.setText(prompt.getText()+"0");
                break;
            case R.id.btn1:
                if (prompt.getText().toString().equals("0")) prompt.setText("");
                prompt.setText(prompt.getText()+"1");
                break;
            case R.id.btn2:
                if (prompt.getText().toString().equals("0")) prompt.setText("");
                prompt.setText(prompt.getText()+"2");
                break;
            case R.id.btn3:
                if (prompt.getText().toString().equals("0")) prompt.setText("");
                prompt.setText(prompt.getText()+"3");
                break;
            case R.id.btn4:
                if (prompt.getText().toString().equals("0")) prompt.setText("");
                prompt.setText(prompt.getText()+"4");
                break;
            case R.id.btn5:
                if (prompt.getText().toString().equals("0")) prompt.setText("");
                prompt.setText(prompt.getText()+"5");
                break;
            case R.id.btn6:
                if (prompt.getText().toString().equals("0")) prompt.setText("");
                prompt.setText(prompt.getText()+"6");
                break;
            case R.id.btn7:
                prompt.setText(prompt.getText()+"7");
                break;
            case R.id.btn8:
                if (prompt.getText().toString().equals("0")) prompt.setText("");
                prompt.setText(prompt.getText()+"8");
                break;
            case R.id.btn9:
                if (prompt.getText().toString().equals("0")) prompt.setText("");
                prompt.setText(prompt.getText()+"9");
                break;
            case R.id.btnPunto:
                prompt.setText(prompt.getText()+".");
                break;
            case R.id.btnC:
                if(prompt.length()>0)
                    prompt.setText(prompt.getText().subSequence(0,prompt.length()-1));
                break;
            case R.id.btnCE:
                prompt.setText("0");
                break;
            case R.id.btnMas:
                prompt.setText(prompt.getText()+"+");
                break;
            case R.id.btnMen:
                prompt.setText(prompt.getText()+"-");
                break;
            case R.id.btnMul:
                prompt.setText(prompt.getText()+"*");
                break;
            case R.id.btnDiv:
                prompt.setText(prompt.getText()+"/");
                break;
            case R.id.btnIgual:
                String op = (String) prompt.getText();
                int posOP;
                char operador=' ';
                double op1=0;
                double op2=0;

                if ((posOP = op.indexOf('+')) == -1 ){
                    if ((posOP = op.indexOf('-')) == -1 ){
                        if ((posOP = op.indexOf('*')) == -1 ){
                            if ((posOP = op.indexOf('/')) == -1 ) {
                                try {
                                    Double.parseDouble(op);
                                } catch (NumberFormatException nfe) {
                                    prompt.setText("Operación invalida");

                                }
                            }
                        }

                    }

                }


                if(posOP!=-1){
                    operador = op.charAt(posOP);
                    try {
                        op1 = Double.parseDouble(op.substring(0,posOP));
                        op2 = Double.parseDouble(op.substring(posOP+1,op.length()));

                        switch (operador){
                            case '+':
                                op=String.valueOf(op1+op2);
                                break;
                            case '-':
                                op=String.valueOf(op1-op2);
                                break;
                            case '*':
                                op=String.valueOf(op1*op2);
                                break;
                            case '/':
                                if(op2!=0.0)
                                    op=String.valueOf(op1/op2);
                                else
                                    op=("Vuelve al cole");
                                break;
                        }

                        if(op.endsWith(".0")) op=op.substring(0,op.length()-2);

                        prompt.setText(op);


                    } catch (NumberFormatException nfe){
                        prompt.setText("Operación invalida");
                    }

                }

                break;
        }

    }
}