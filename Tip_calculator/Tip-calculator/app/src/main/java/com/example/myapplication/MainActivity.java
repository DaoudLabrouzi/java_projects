package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //setting the variables
    TextView    tipAmount, ttlAmount, theShare;
    EditText billAmount, tipPrcentage, numberOfPeople;
    Button calculate_btn, reset_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //-----Finding the buttons in the Java code--------/
        tipAmount = findViewById(R.id.tipAmount);
        ttlAmount = findViewById(R.id.ttlAmount);
        billAmount = findViewById(R.id.billAmount);
        theShare = findViewById(R.id.theShare);
        numberOfPeople = findViewById(R.id.numberOfPeople);
        tipPrcentage = findViewById(R.id.tipPrcentage);

        calculate_btn = findViewById(R.id.calcualte_btn);
        reset_btn = findViewById(R.id.reset);

     //Seting up each button’s click action---------------------------------------/
        calculate_btn.setOnClickListener(v -> calculate());
        reset_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                billAmount.setText("");
                tipPrcentage.setText("");
                numberOfPeople.setText("");
                tipAmount.setText("Tip: ");
                ttlAmount.setText("Total Amount: ");
                theShare.setText("Share: ");
            }
        } );


    }
    @SuppressLint("DefaultLocale")
    protected void calculate(){
        String billing = billAmount.getText().toString(); //getting the values from the fields &
        String tipping = tipPrcentage.getText().toString();
        String people_nbr = numberOfPeople.getText().toString();


        // 2) basic checks
        if(billing.isEmpty() || tipping.isEmpty() || people_nbr.isEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            double bill = Double.parseDouble(billing);
            double nbrPpl = Double.parseDouble(people_nbr);
            double tip = Double.parseDouble(tipping);

            double share;
            double ttlBill;
            if(nbrPpl != 0){
                tip = bill * (tip/100);
                ttlBill = bill + tip;
                share = ttlBill  / nbrPpl;

//            inputbox.setText(String.valueOf((long) result));
                tipAmount.setText("Tip: " + String.format("%.2f", tip) + " £");

                ttlAmount.setText("Total Amount: " + String.format("%.2f", ttlBill) + " £");

                theShare.setText("Share: " + String.format("%.2f", share) + " £");
            }
            else {
                Toast.makeText(this, "Number of people can not be zero", Toast.LENGTH_SHORT).show();

            }
        }






    }
}
