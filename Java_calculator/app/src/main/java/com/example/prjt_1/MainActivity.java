package com.example.prjt_1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    EditText number1, number2;
    Button  btnAdd, btnMns, btnMltp, btnDvd, btnEqual;
    TextView result;
    char operation;
    protected void onCreate(Bundle savedInstanceState){  //<= This bag might contain my app’s previous state if it was saved before.”
        super.onCreate(savedInstanceState); //“Hey Android, do your normal activity setup first — then I’ll add my custom code below.”
        setContentView(R.layout.activity_main); //“Now show this layout (the XML file) on the screen.”

        number1 = findViewById(R.id.input1);
        number2 = findViewById(R.id.input2);
        btnAdd  = findViewById(R.id.btn_plus);
        btnMns = findViewById(R.id.btn_minus);
        btnMltp = findViewById(R.id.btn_mlitp);
        btnDvd = findViewById(R.id.btn_div);
        result = findViewById(R.id.result);
        btnEqual = findViewById(R.id.btn_equal);

        // Setting up button listeners
        btnAdd.setOnClickListener(v -> operation = '+');
        btnMns.setOnClickListener(v -> operation = '-');
        btnMltp.setOnClickListener(v -> operation = '*');
        btnDvd.setOnClickListener(v -> operation = '/');
        btnEqual.setOnClickListener(v -> calculate());

    }
    public void calculate(){
        String n1 =number1.getText().toString().trim();
        String n2 = number2.getText().toString().trim();

        if (n1.isEmpty() || n2.isEmpty()){
            Toast.makeText(this, "please enter all values", Toast.LENGTH_SHORT).show();
            return;
        }
        double num1 = Double.parseDouble(n1);
        double num2 = Double.parseDouble(n2);
        double res = 0;

        switch (operation){
            case  '+':
                res = num1 + num2;
                break;
            case  '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                if (num2 == 0){
                    Toast.makeText(this, "Division by zero is not allowed", Toast.LENGTH_SHORT) .show();
                    return;
                }
                res = num1 / num2;
                break;
            default:
                Toast.makeText(this, "choose Operation first", Toast.LENGTH_SHORT).show();
                return;
        }
        result.setText("Result: " + res);
        }

    };

