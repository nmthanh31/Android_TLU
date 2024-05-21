package com.example.personalinformationapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText edtFullName, edtID, edtMoreInfo;

    private CheckBox cbDocSach, cbDocBao, cbDocCoding;

    private RadioGroup rgDegree;

    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        edtFullName = (EditText) findViewById(R.id.edtFullName);
        edtID = (EditText) findViewById(R.id.edtID);
        edtMoreInfo = (EditText) findViewById(R.id.edtMoreInfo);

        rgDegree = (RadioGroup) findViewById(R.id.rgDegree);


        cbDocBao = (CheckBox) findViewById(R.id.cbDocBao);
        cbDocSach = (CheckBox) findViewById(R.id.cbDocSach);
        cbDocCoding = (CheckBox) findViewById(R.id.cbDocCoding);

        btnSend = (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = edtFullName.getText().toString().trim();
                if (fullName.length()<3){
                    edtFullName.requestFocus();

                    edtFullName.selectAll();
                    Toast.makeText(MainActivity.this, "Tên phải nhiều hơn 3 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }

                String cccd = edtID.getText().toString().trim();
                if (cccd.length() != 12){
                    edtID.requestFocus();

                    edtID.selectAll();
                    Toast.makeText(MainActivity.this, "CCCD phải có 12 số", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton rad = (RadioButton) findViewById(rgDegree.getCheckedRadioButtonId());
                String degree = rad.getText().toString();

                String hobbies = "";

                if(cbDocBao.isChecked()){
                    hobbies+=cbDocBao.getText().toString()+" - ";
                }
                if (cbDocSach.isChecked()){
                    hobbies+=cbDocSach.getText().toString() + " - ";
                }
                if (cbDocCoding.isChecked()){
                    hobbies+=cbDocCoding.getText().toString() + " - ";
                }
                hobbies = hobbies.substring(0,hobbies.length()-3);
                String moreInfo=edtMoreInfo.getText()+"";

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông tin cá nhân");
                builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                String message = "";
                message+=fullName+"\n";
                message+=cccd+"\n";
//                message+=degree+"\n";
                message+=hobbies+"\n"+"-----------------------\n";
                message+="Thông tin bố sung\n";
                message+=moreInfo;

                builder.setMessage(message);
                builder.create().show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder b =new
                AlertDialog.Builder(MainActivity.this);
        b.setTitle("Question");
        b.setMessage("Are you sure you want to exit?");
        b.setIcon(R.drawable.ic_launcher_foreground);
        b.setPositiveButton("Yes", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }});
        b.setNegativeButton("No", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
        b.create().show();
    }

}