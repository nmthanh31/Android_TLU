package com.example.tipcalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText editTextBill;
    TextView Percent, Tip, Total;

    Button Increase, Decrease;

    int percent = 15;
    String bill = "";

    float tip = 0;
    float total = 0;
    SharedPreferences savedValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        editTextBill = findViewById(R.id.editTextBill);
        Percent = findViewById(R.id.textViewPercent2);
        Tip=findViewById(R.id.textViewTip);
        Total=findViewById(R.id.textViewTotal);
        Increase=findViewById(R.id.buttonIncrease);
        Decrease=findViewById(R.id.buttonDecrease);





        Increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                percent++;
                String testPercent = percent+"%";
                Percent.setText(testPercent);
                if(!Objects.equals(bill, "")){
                    bill = editTextBill.getText().toString();
                    tip = Float.parseFloat(bill) * percent / 100 ;
                    total = Float.parseFloat(bill) + tip;
                    String textTip = "$" + tip;
                    String textTotal = "$" + total;
                    Tip.setText(textTip);
                    Total.setText(textTotal);
                }
            }
        });

        Decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (percent > 0 ){
                    percent--;
                    String testPercent = percent+"%";
                    Percent.setText(testPercent);
                    if(!Objects.equals(bill, "")){
                        bill = editTextBill.getText().toString();
                        tip = Float.parseFloat(bill) * percent / 100 ;
                        total = Float.parseFloat(bill) + tip;
                        String textTip = "$" + tip;
                        String textTotal = "$" + total;
                        Tip.setText(textTip);
                        Total.setText(textTotal);
                    }
                }
            }
        });

        editTextBill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                bill = editTextBill.getText().toString();
                String textTip = "$00.00";
                String textTotal = "$00.00";
                if(!bill.isEmpty()){
                    tip = Float.parseFloat(bill) * percent / 100 ;
                    total = Float.parseFloat(bill) + tip;
                    textTip = "$" + tip;
                    textTotal = "$" + total;
                }
                Tip.setText(textTip);
                Total.setText(textTotal);
            }
        });
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

    }
    @Override
    public void onPause() {
        // save the instance variables

        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("bill", bill);
        editor.putInt("percent",  percent);
        editor.apply();

        super.onPause();
    }
    @Override
    public void onResume() {
        super.onResume();


        bill = savedValues.getString("bill", "");
        percent = savedValues.getInt("percent", 15);


        editTextBill.setText(bill);



    }



}
