package com.example.controlnangcao;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NoteActivity extends AppCompatActivity {

    private TextView tvDate;
    private EditText edtWork,edtHour, edtMinute;

    private Button btnAddWork;

    private ListView lvWork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
//            return insets.consumeSystemWindowInsets();
//        });
        tvDate = findViewById(R.id.tvDate);
        edtHour = findViewById(R.id.edtHour);
        edtMinute = findViewById(R.id.edtMinute);
        edtWork = findViewById(R.id.edtWork);
        btnAddWork = findViewById(R.id.btnAddWork);
        lvWork = findViewById(R.id.lvWork);

        Date date = Calendar.getInstance().getTime();

        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        tvDate.setText("Hôm nay: "+simpleDateFormat.format(date));

        ArrayList<String> listWork = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listWork);

        lvWork.setAdapter(adapter);
        btnAddWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String work = edtWork.getText().toString();
                String hour = edtHour.getText().toString();
                String minute = edtMinute.getText().toString();

                if (work.isEmpty() || hour.isEmpty() || minute.isEmpty()){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(NoteActivity.this);
                    alertDialog.setTitle("Information Missing");
                    alertDialog.setMessage("Please enter all information of the work");
                    alertDialog.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    alertDialog.show();
                }else {
                    listWork.add("+ "+ work + " - "+hour+":"+minute);
                    adapter.notifyDataSetChanged();
                    edtHour.setText("");
                    edtMinute.setText("");
                    edtWork.setText("");
                }
            }
        });

    }
}