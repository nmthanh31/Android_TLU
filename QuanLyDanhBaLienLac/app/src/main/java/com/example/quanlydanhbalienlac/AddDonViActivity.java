package com.example.quanlydanhbalienlac;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class AddDonViActivity extends AppCompatActivity {
    private EditText editTextTenDonVi, editTextEmail, editTextWebsite, editTextLogo, editTextDiaChi, editTextSDT, editTextMaDonViCha;
    private Button btnAddDonvi;

    private TextView tvTitle;
    private String Status;

    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_don_vi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        dbHelper = new DatabaseHelper(this);
        editTextTenDonVi = findViewById(R.id.editTextTenDonVi);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextWebsite = findViewById(R.id.editTextWebsite);
        editTextLogo = findViewById(R.id.editTextLogo);
        editTextDiaChi = findViewById(R.id.editTextDiaChi);
        editTextSDT = findViewById(R.id.editTextSDT);
        editTextMaDonViCha = findViewById(R.id.editTextMaDonViCha);
        tvTitle = findViewById(R.id.tvTitle);
        btnAddDonvi = findViewById(R.id.btnAddDonVi);

        Intent intent = getIntent();
        Status = intent.getStringExtra("status");

        assert Status != null;
        if (Status.equals("Add")) {
            tvTitle.setText("Thêm đơn vị");
            btnAddDonvi.setText("Thêm đơn vị");
        } else if (Status.equals("Edit")) {
            tvTitle.setText("Sửa đơn vị");
            btnAddDonvi.setText("Sửa đơn vị");
            String tenDonVi = intent.getStringExtra("TenDonVi");
            String email = intent.getStringExtra("Email");
            String website = intent.getStringExtra("Website");
            String logo = intent.getStringExtra("Logo");
            String diaChi = intent.getStringExtra("DiaChi");
            String sdt = intent.getStringExtra("SDT");
            int maDonViCha = intent.getIntExtra("MaDonViCha",0);

            editTextTenDonVi.setText(tenDonVi);
            editTextEmail.setText(email);
            editTextWebsite.setText(website);
            editTextLogo.setText(logo);
            editTextDiaChi.setText(diaChi);
            editTextSDT.setText(sdt);
            editTextMaDonViCha.setText(String.valueOf(maDonViCha));
        }
        btnAddDonvi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenDonvi = editTextTenDonVi.getText().toString();
                String email = editTextEmail.getText().toString();
                String website = editTextWebsite.getText().toString();
                String logo = editTextLogo.getText().toString();
                String diachi = editTextDiaChi.getText().toString();
                String sdt = editTextSDT.getText().toString();
                int maDonViCha = Integer.parseInt(editTextMaDonViCha.getText().toString());

//                if (!editTextMaDonViCha.getText().toString().isEmpty()) {
//                    maDonViCha = Integer.parseInt(editTextMaDonViCha.getText().toString());
//                }

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.COLUMN_DON_VI_TEN, tenDonvi);
                values.put(DatabaseHelper.COLUMN_DON_VI_EMAIL, email);
                values.put(DatabaseHelper.COLUMN_DON_VI_WEBSITE, website);
                values.put(DatabaseHelper.COLUMN_DON_VI_LOGO, logo);
                values.put(DatabaseHelper.COLUMN_DON_VI_DIA_CHI, diachi);
                values.put(DatabaseHelper.COLUMN_DON_VI_SDT, sdt);
                values.put(DatabaseHelper.COLUMN_DON_VI_ID_CHA, maDonViCha);

                if (Status.equals("Add")) {
                    db.insert(DatabaseHelper.TABLE_DON_VI, null, values);
                } else if (Status.equals("Edit")) {
                    int maDonVi = intent.getIntExtra("MaDonVi", 0);
                    db.update(DatabaseHelper.TABLE_DON_VI, values, DatabaseHelper.COLUMN_DON_VI_ID + " = ?", new String[]{String.valueOf(maDonVi)});
                }

                db.close();

                Intent intent = new Intent(AddDonViActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                finish();
            }
        });

        
    }
}