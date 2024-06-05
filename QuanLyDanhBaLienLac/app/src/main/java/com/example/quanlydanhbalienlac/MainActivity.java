package com.example.quanlydanhbalienlac;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnDonVi, btnNhanVien, btnTimKiem, btnThem;

    private EditText edtTimKiem;

    private TextView tvTitle;

    private RecyclerView rcvDanhba;

    private List<DonVi> donViList;

    private DonViAdapter   donViAdapter;

    private List<NhanVien> nhanVienList;

    private NhanVienAdapter nhanVienAdapter;
    private DatabaseHelper dbHelper;

    private int Status = 0;

    private OnItemActionListener onItemActionListener;

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
        dbHelper = new DatabaseHelper(this);
        btnDonVi = findViewById(R.id.btnDonVi);
        btnNhanVien = findViewById(R.id.btnNhanVien);
        btnThem = findViewById(R.id.btnThem);
        btnTimKiem = findViewById(R.id.btnTimKiem);

        edtTimKiem = findViewById(R.id.edtTimKiem);

        tvTitle = findViewById(R.id.tvTitle);

        rcvDanhba = findViewById(R.id.rcvDanhba);
        rcvDanhba.setLayoutManager(new LinearLayoutManager(this));


        donViList = new ArrayList<DonVi>();
        nhanVienList = new ArrayList<NhanVien>();

        onItemActionListener = new OnItemActionListener() {
            @Override
            public void onEditDonVi(DonVi donVi) {
                Intent intent = new Intent(MainActivity.this, AddDonViActivity.class);
                intent.putExtra("status","Edit");
                intent.putExtra("MaDonVi", donVi.getMaDonVi());
                intent.putExtra("TenDonVi", donVi.getTenDonVi());
                intent.putExtra("Email", donVi.getEmail());
                intent.putExtra("Logo", donVi.getLogo());
                intent.putExtra("DiaChi", donVi.getDiaChi());
                intent.putExtra("SDT", donVi.getSdt());
                intent.putExtra("MaDonViCha", donVi.getMaDonViCha());
                startActivity(intent);
            }

            @Override
            public void onDeleteDonVi(DonVi donVi) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete(DatabaseHelper.TABLE_DON_VI, DatabaseHelper.COLUMN_DON_VI_ID + " = ?", new String[]{String.valueOf(donVi.getMaDonVi())});
                loadDonVi();
            }

            @Override
            public void onEditNhanVien(NhanVien nhanVien) {
                Intent intent = new Intent(MainActivity.this, AddNhanVienActivity.class);
                intent.putExtra("status","Edit");
                intent.putExtra("MaNhanVien", nhanVien.getMaNhanVien());
                intent.putExtra("HoTen", nhanVien.getHoten());
                intent.putExtra("Email", nhanVien.getEmail());
                intent.putExtra("ChucVu", nhanVien.getChucVu());
                intent.putExtra("SDT", nhanVien.getSdt());
                intent.putExtra("AnhDaiDien", nhanVien.getAnhDaiDien());
                intent.putExtra("MaDonVi", nhanVien.getMaDonVi());
                startActivity(intent);
            }

            @Override
            public void onDeleteNhanVien(NhanVien nhanVien) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete(DatabaseHelper.TABLE_NHAN_VIEN, DatabaseHelper.COLUMN_NHAN_VIEN_ID + " = ?", new String[]{String.valueOf(nhanVien.getMaNhanVien())});
                loadNhanVien();
            }


        };

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status == 0){
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    Cursor cursor = db.query(DatabaseHelper.TABLE_DON_VI, null, DatabaseHelper.COLUMN_DON_VI_TEN+" = ?", new String[]{String.valueOf(edtTimKiem.getText().toString())}, null, null, null);
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
//
                                DonVi donVi = new DonVi(maDonVi, tenDonVi, email, website, logo, diaChi, sdt, maDonViCha);

                                donViList.add(donVi);
                            } while (cursor.moveToNext());
                        }
                    }catch (SQLException sqlException){
                        Log.d("DB", sqlException.toString());
                    }

                    donViAdapter = new DonViAdapter(donViList, MainActivity.this, dbHelper, onItemActionListener);
                    rcvDanhba.setAdapter(donViAdapter);
                    cursor.close();
                    db.close();
                } else if (Status == 1) {
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    Cursor cursor = db.query(DatabaseHelper.TABLE_NHAN_VIEN, null, DatabaseHelper.COLUMN_NHAN_VIEN_TEN+" = ?", new String[]{String.valueOf(edtTimKiem.getText().toString())}, null, null, null);
                    nhanVienList.clear();
                    if (cursor.moveToFirst()) {
                        do {
                            int maNhanVien = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NHAN_VIEN_ID));
                            String tenNhanVien = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NHAN_VIEN_TEN));
                            String chucVu = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NHAN_VIEN_CHUC_VU));
                            String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NHAN_VIEN_EMAIL));
                            String sdt = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NHAN_VIEN_SDT));
                            String anhDaiDien = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NHAN_VIEN_ANH_DAI_DIEN));
                            int maDonVi = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NHAN_VIEN_MA_DON_VI));

                            NhanVien nhanVien = new NhanVien(maNhanVien, tenNhanVien, chucVu, email, sdt, anhDaiDien, maDonVi);
                            nhanVienList.add(nhanVien);
                        } while (cursor.moveToNext());
                    }
                    nhanVienAdapter = new NhanVienAdapter(nhanVienList, MainActivity.this, onItemActionListener, dbHelper);
                    rcvDanhba.setAdapter(nhanVienAdapter);
                    cursor.close();
                    db.close();
                }
            }
        });


        if (Status == 0){
            tvTitle.setText("Danh sách đơn vị");
            loadDonVi();
        }
        else {
            tvTitle.setText("Danh sách nhân viên");
            loadNhanVien();
        }
        btnDonVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Status = 0;
                tvTitle.setText("Danh sách đơn vị");
                loadDonVi();
            }
        });
        btnNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Status = 1;
                tvTitle.setText("Danh sách nhân viên");
                loadNhanVien();
            }
        });


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status == 0){
                    Intent inten =new Intent(MainActivity.this, AddDonViActivity.class);
                    inten.putExtra("status","Add");
                    startActivity(inten);
                }
                else {
                    Intent inten =new Intent(MainActivity.this, AddNhanVienActivity.class);
                    inten.putExtra("status","Add");
                    startActivity(inten);
                }
            }
        });



    }
    private void loadNhanVien() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_NHAN_VIEN, null, null, null, null, null, null);
        nhanVienList.clear();
        if (cursor.moveToFirst()) {
            do {
                int maNhanVien = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NHAN_VIEN_ID));
                String tenNhanVien = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NHAN_VIEN_TEN));
                String chucVu = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NHAN_VIEN_CHUC_VU));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NHAN_VIEN_EMAIL));
                String sdt = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NHAN_VIEN_SDT));
                String anhDaiDien = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NHAN_VIEN_ANH_DAI_DIEN));
                int maDonVi = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NHAN_VIEN_MA_DON_VI));

                NhanVien nhanVien = new NhanVien(maNhanVien, tenNhanVien, chucVu, email, sdt, anhDaiDien, maDonVi);
                nhanVienList.add(nhanVien);
            } while (cursor.moveToNext());
        }
        nhanVienAdapter = new NhanVienAdapter(nhanVienList, MainActivity.this, onItemActionListener, dbHelper);
        rcvDanhba.setAdapter(nhanVienAdapter);
        cursor.close();
        db.close();
    }

    private void loadDonVi() {
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
//
                    DonVi donVi = new DonVi(maDonVi, tenDonVi, email, website, logo, diaChi, sdt, maDonViCha);

                    donViList.add(donVi);
                } while (cursor.moveToNext());
            }
        }catch (SQLException sqlException){
            Log.d("DB", sqlException.toString());
        }

        donViAdapter = new DonViAdapter(donViList, MainActivity.this, dbHelper, onItemActionListener);
        rcvDanhba.setAdapter(donViAdapter);
        cursor.close();
        db.close();
    }
}