package com.example.quanlysinhvien;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtMaLop, edtTenLop, edtSiSo;

    private Button btnInsert, btnDelete, btnUpdate, btnQuery;

    private ListView lvSinhVien;

    private ArrayList<String> listSinhVien;
    private ArrayAdapter<String> sinhVienAdapter;

    private SQLiteDatabase mySql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtMaLop = findViewById(R.id.edtMaLop);
        edtTenLop = findViewById(R.id.edtTenLop);
        edtSiSo = findViewById(R.id.edtSiSo);

        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnQuery = findViewById(R.id.btnQuery);

        lvSinhVien = findViewById(R.id.lvSinhVien);

        listSinhVien = new ArrayList<>();
        sinhVienAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listSinhVien);

        lvSinhVien.setAdapter(sinhVienAdapter);

        mySql = openOrCreateDatabase("qlsinhvien.db",MODE_PRIVATE,null);

        try {
            String sql = "CREATE TABLE tbllop(malop TEXT primary key, tenlop TEXT, siso INTEGER)";
            mySql.execSQL(sql);
        }catch (Exception e){
            Log.e("Error","Table ko tồn tại");
        }

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String malop = edtMaLop.getText().toString();
                String tenlop = edtTenLop.getText().toString();

                int siso = Integer.parseInt(edtSiSo.getText().toString());

                ContentValues content = new ContentValues();
                content.put("malop",malop);
                content.put("tenlop",tenlop);
                content.put("siso",siso);

                if (mySql.insert("tbllop",null,content) == -1){
                    Toast.makeText(MainActivity.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}