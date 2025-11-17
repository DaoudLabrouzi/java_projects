package com.example.calculator_upgrade;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText inputbox;
    TextView textview;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0,btn_clr,
            btn_plus, btn_minus, btn_mlp, btn_dvd, btn_eql;
    char operation;
    GridLayout calckeyboard;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //Finding the buttons in the Java code
        inputbox = findViewById(R.id.inputbox);
        textview = findViewById(R.id.textView);
        calckeyboard = findViewById(R.id.calckeyboard);
        //numbers_btns---------------------------------------/
        btn0 = findViewById(R.id.btn0);  btn4 = findViewById(R.id.btn4);
        btn1 = findViewById(R.id.btn1);  btn5 = findViewById(R.id.btn5);
        btn2 =  findViewById(R.id.btn2); btn6 = findViewById(R.id.btn6);
        btn3 = findViewById(R.id.btn3);  btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);  btn9 = findViewById(R.id.btn9);
        //operators_btns---------------------------------------/
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_mlp = findViewById(R.id.btn_mlp);
        btn_dvd = findViewById(R.id.btn_dvd);
        btn_eql = findViewById(R.id.btn_eql);
        btn_clr =findViewById(R.id.btn_clr);

    //Seting up each button’s click action---------------------------------------/
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                inputbox.append("0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputbox.append("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputbox.append("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputbox.append("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputbox.append("4");
            }

        });
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputbox.append("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputbox.append("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputbox.append("7");
            }

        });
        btn8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputbox.append("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputbox.append("9");
            }
        });

        //setting up Oprbuttons
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputbox.append("+");
                operation = '+';
            }
        });
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputbox.append("-");
                operation = '-';
            }
        });
        btn_mlp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputbox.append("*");
                operation = '*';
            }
        });
        btn_dvd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputbox.append("/");
                operation = '/';
            }
        });
        btn_clr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputbox.setText("");
            }
        });
        btn_eql.setOnClickListener(v -> calculate());



    }
    protected void calculate(){
        // get input text
        String fullOperation = inputbox.getText().toString();


        // 2) basic checks
        if (fullOperation.isEmpty()) {
            Toast.makeText(this, "Please Enter a Value", Toast.LENGTH_SHORT).show();
            return;
        }
        if (operation == '\0') {
            Toast.makeText(this, "Choose an operation please!", Toast.LENGTH_SHORT).show();
            return;
        }
        //separating the numbers from the operation
        try {
            // 3) split safely using Pattern.quote -> treats + * . etc. as normal characters
            String[] parts = fullOperation.split(Pattern.quote(String.valueOf(operation))); //“Treat + as a normal character, not a special regex symbol.”

            // 4) validate split result
            if (parts.length < 2) {
                Toast.makeText(this, "Enter a second number after the operator", Toast.LENGTH_SHORT).show();
                return;
            };
            // 5) parse numbers (allow decimals)
            double num1 = Double.parseDouble(parts[0]);
            double num2 = Double.parseDouble(parts[1]);
            double result = 0;

        //setting up the operations
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    Toast.makeText(this, "Cannot divide on 0!", Toast.LENGTH_SHORT).show();
                    return;
                };
                result = num1 / num2;
                break;
            default:
                Toast.makeText(this, "Choose an operation pleas!", Toast.LENGTH_SHORT).show();
                return;
        };

// ------Replace the operation with the result in the EditText---------/
        // If result is whole number like 5.0, show "5" instead of "5.0"
        if (result == (long) result) {
            inputbox.setText(String.valueOf((long) result));
        } else {
            inputbox.setText(String.valueOf(result));
        }

        // Reseting the operator so user can start a new calculation
        operation = '\0';

    }
        catch (NumberFormatException nfe) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // general safety net - shows a friendly message instead of crashing
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

}
}
