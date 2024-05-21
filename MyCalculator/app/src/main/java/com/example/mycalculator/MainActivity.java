package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText edtNumberA, edtNumberB, edtResult;
    private Button btnPlus, btnSubtract, btnMutiple, btnDivine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        edtNumberA = (EditText) findViewById(R.id.edtNumberA);
        edtNumberB = (EditText) findViewById(R.id.edtNumberB);
        edtResult = (EditText) findViewById(R.id.edtResult);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnSubtract = (Button) findViewById(R.id.btnSub);
        btnMutiple = (Button) findViewById(R.id.btnMutiple);
        btnDivine = (Button) findViewById(R.id.btnDivine);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    int a = Integer.parseInt("0"+edtNumberA.getText());
                    int b = Integer.parseInt("0"+edtNumberB.getText());

                    edtResult.setText("a + b =" +(a+b));

            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt("0"+edtNumberA.getText());
                int b = Integer.parseInt("0"+edtNumberB.getText());
                // TODO Auto-generated method stub
                edtResult.setText("a - b =" +(a-b));
            }
        });

        btnMutiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt("0"+edtNumberA.getText());
                int b = Integer.parseInt("0"+edtNumberB.getText());
                // TODO Auto-generated method stub
                edtResult.setText("a * b =" +(a*b));
            }
        });

        btnDivine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float a = Float.parseFloat("0"+edtNumberA.getText());
                float b = Float.parseFloat("0"+edtNumberB.getText());

                if(b == 0){
                    edtResult.setText("Số b phải khác 0");
                }else {
                    edtResult.setText("a / b =" +a/b);
                }

            }
        });
    }
}