package com.example.mybmi;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtHeight, edtWeight, edtBMI, edtPredict;

    private Button btnBMI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        edtName = (EditText) findViewById(R.id.edtName);
        edtHeight = (EditText) findViewById(R.id.edtHeight);
        edtWeight = (EditText) findViewById(R.id.edtWeight);
        edtBMI = (EditText) findViewById(R.id.edtBMI);
        edtPredict = (EditText) findViewById(R.id.edtPredict);
        btnBMI = (Button) findViewById(R.id.btnBMI);

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DecimalFormat dcmf = new DecimalFormat("#.0");
                    double height = Double.parseDouble(edtHeight.getText().toString());
                    double weight = Double.parseDouble(edtWeight.getText().toString());

                    double bmi = weight/Math.pow(height,2);
                    edtBMI.setText(String.valueOf(dcmf.format(bmi)));
                    String predict = "";
                    if (bmi<18){
                        predict = "Người gầy";
                    } else if (bmi >=18 && bmi < 24.9) {
                        predict = "Người bình thường";
                    } else if (bmi >=25 && bmi < 29.9) {
                        predict = "Người béo phì cấp độ 1";
                    } else if (bmi >=30 && bmi < 34.9) {
                        predict = "Người béo phì cấp độ 2";
                    } else {
                        predict = "Người béo phì cấp độ 3";
                    }

                    edtPredict.setText(predict);

                }catch (Exception e){
                    System.out.println(e);
                }


            }
        });
    }
    @Override
    public void onBackPressed() {

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Question");
        alert.setMessage("Are you sure you want to exit?");
        alert.setIcon(R.drawable.ic_launcher_foreground);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alert.create().show();
    }

}