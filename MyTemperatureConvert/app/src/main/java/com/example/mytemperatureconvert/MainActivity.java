package com.example.mytemperatureconvert;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText edtF, edtC;

    private Button btnToC, btnToF, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        edtC = (EditText) findViewById(R.id.edtC);
        edtF = (EditText) findViewById(R.id.edtF);
        btnToC = (Button) findViewById(R.id.btnToC);
        btnToF = (Button) findViewById(R.id.btnToF);
        btnClear = (Button) findViewById(R.id.btnClear);

        btnToF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtF.getText().toString().isEmpty()){
                    edtF.setText("Please enter Fahrenheit");
                }else{
                    DecimalFormat dcf = new DecimalFormat("#.00");
                    float C = Float.parseFloat(edtC.getText().toString()+"");
                    edtF.setText(""+dcf.format(C*1.8+32));
                }
            }
        });
        btnToC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DecimalFormat dcf = new DecimalFormat("#");
                    float F = Float.parseFloat(edtF.getText().toString()+"");
                    edtC.setText(""+dcf.format((F-32)/1.8));
                }catch (Exception e){
                    edtC.setText("Please enter Celsius");
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtF.setText("");
                edtC.setText("");
            }
        });
    }
}