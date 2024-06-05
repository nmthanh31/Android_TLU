package com.example.quanlydanhbalienlac;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

import java.util.ArrayList;
import java.util.List;

public class AddNhanVienActivity extends AppCompatActivity {

    private EditText editTextHoTen, editTextChucVu, editTextEmail, editTextSdt, editTextAnhDaiDien;

    private Button buttonAddNhanVien;

    private Spinner spnDonVi;

    private TextView tvTitle;

    private List<DonVi> donViList;

    private DatabaseHelper dbHelper;

    private String status;

    private int maDonVi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_nhan_vien);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        spnDonVi = findViewById(R.id.spnDonVi);
        editTextHoTen = findViewById(R.id.editTextHoTen);
        editTextChucVu = findViewById(R.id.editTextChucVu);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSdt = findViewById(R.id.editTextSdt);
        editTextAnhDaiDien =  findViewById(R.id.editTextAnhDaiDien);
        buttonAddNhanVien = findViewById(R.id.buttonAddNhanVien);
        tvTitle = findViewById(R.id.tvTitle);
        dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        status = intent.getStringExtra("status");

        donViList = new ArrayList<DonVi>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_DON_VI, null, null, null, null, null, null);
        donViList.clear(); // Xóa danh sách hiện tại trước khi tải dữ liệu mới
        try {
            if (cursor.moveToFirst()) {
                do {
                    int maDonVi = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DON_VI_ID));
                    String tenDonVi = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DON_VI_TEN));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DON_VI_EMAIL));
                    String website = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DON_VI_WEBSITE));
                    String logo = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DON_VI_LOGO));
                    String diaChi = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DON_VI_DIA_CHI));
                    String sdt = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DON_VI_SDT));
                    int maDonViCha = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DON_VI_ID_CHA));

                    DonVi donVi = new DonVi(maDonVi, tenDonVi, email, website, logo, diaChi, sdt, maDonViCha);

                    donViList.add(donVi);
                } while (cursor.moveToNext());
            }
        }catch (SQLException sqlException){
            Log.d("DB", sqlException.toString());
        }
        ArrayAdapter<DonVi> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, donViList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnDonVi.setAdapter(adapter);
        spnDonVi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DonVi selectedDonVi = (DonVi) adapterView.getItemAtPosition(i);
                maDonVi = selectedDonVi.getMaDonVi();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if (status.equals("Add")){
            Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
            tvTitle.setText("Thêm nhân viên");
            buttonAddNhanVien.setText("Thêm nhân viên");
        }else if (status.equals("Edit")){
            Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
            tvTitle.setText("Sửa nhân viên");
            buttonAddNhanVien.setText("Sửa nhân viên");

            String hoTen = intent.getStringExtra("HoTen");
            String email = intent.getStringExtra("Email");
            String chucVu = intent.getStringExtra("ChucVu");
            String anhDaiDien = intent.getStringExtra("AnhDaiDien");
            String sdt = intent.getStringExtra("SDT");
            int maDonVi = intent.getIntExtra("MaDonVi",0);

            editTextHoTen.setText(hoTen);
            editTextEmail.setText(email);
            editTextAnhDaiDien.setText(anhDaiDien);
            editTextChucVu.setText(chucVu);
            editTextSdt.setText(sdt);
        }

        buttonAddNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoTen = editTextHoTen.getText().toString();
                String email = editTextEmail.getText().toString();
                String chucVu = editTextChucVu.getText().toString();
                String anhDaiDien = editTextAnhDaiDien.getText().toString();
                String sdt = editTextSdt.getText().toString();


                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.COLUMN_NHAN_VIEN_TEN, hoTen);
                values.put(DatabaseHelper.COLUMN_NHAN_VIEN_EMAIL, email);
                values.put(DatabaseHelper.COLUMN_NHAN_VIEN_CHUC_VU, chucVu);
                values.put(DatabaseHelper.COLUMN_NHAN_VIEN_ANH_DAI_DIEN, anhDaiDien);
                values.put(DatabaseHelper.COLUMN_NHAN_VIEN_SDT, sdt);
                values.put(DatabaseHelper.COLUMN_NHAN_VIEN_MA_DON_VI, maDonVi);

                if (status.equals("Add")) {
                    db.insert(DatabaseHelper.TABLE_NHAN_VIEN, null, values);
                } else if (status.equals("Edit")) {
                    int maDonVi = intent.getIntExtra("MaDonVi", 0);
                    db.update(DatabaseHelper.TABLE_NHAN_VIEN, values, DatabaseHelper.COLUMN_NHAN_VIEN_ID + " = ?", new String[]{String.valueOf(maDonVi)});
                }

                db.close();

                Intent intent = new Intent(AddNhanVienActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                finish();
            }
        });

    }
}