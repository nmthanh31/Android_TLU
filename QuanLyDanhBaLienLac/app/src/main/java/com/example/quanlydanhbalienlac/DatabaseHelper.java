    package com.example.quanlydanhbalienlac;

    import android.content.Context;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    public class DatabaseHelper extends SQLiteOpenHelper  {
        private static final String DATABASE_NAME = "qldb.db";
        private static final int DATABASE_VERSION = 3;

        public static final String TABLE_DON_VI = "donvi";
        public static final String COLUMN_DON_VI_ID = "MaDonVi";
        public static final String COLUMN_DON_VI_TEN = "TenDonVi";
        public static final String COLUMN_DON_VI_EMAIL = "Email";
        public static final String COLUMN_DON_VI_WEBSITE = "Website";
        public static final String COLUMN_DON_VI_LOGO = "Logo";
        public static final String COLUMN_DON_VI_DIA_CHI = "DiaChi";
        public static final String COLUMN_DON_VI_SDT = "SDT";
        public static final String COLUMN_DON_VI_ID_CHA = "MaDonViCha";

        private static final String TABLE_CREATE_DON_VI =
                "CREATE TABLE " + TABLE_DON_VI + " (" +
                        COLUMN_DON_VI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_DON_VI_TEN + " TEXT, " +
                        COLUMN_DON_VI_EMAIL + " TEXT, " +
                        COLUMN_DON_VI_WEBSITE + " TEXT, " +
                        COLUMN_DON_VI_LOGO + " TEXT, " +
                        COLUMN_DON_VI_DIA_CHI + " TEXT, " +
                        COLUMN_DON_VI_SDT + " TEXT, " +
                        COLUMN_DON_VI_ID_CHA + " INTEGER " +

                        ");";

        public static final String TABLE_NHAN_VIEN = "nhanvien";
        public static final String COLUMN_NHAN_VIEN_ID = "MaNhanVien";
        public static final String COLUMN_NHAN_VIEN_TEN = "TenNhanVien";
        public static final String COLUMN_NHAN_VIEN_CHUC_VU = "ChucVu";
        public static final String COLUMN_NHAN_VIEN_EMAIL = "Email";
        public static final String COLUMN_NHAN_VIEN_SDT = "SDT";
        public static final String COLUMN_NHAN_VIEN_ANH_DAI_DIEN = "AnhDaiDien";
        public static final String COLUMN_NHAN_VIEN_MA_DON_VI = "MaDonVi";

        private static final String TABLE_CREATE_NHAN_VIEN =
                "CREATE TABLE " + TABLE_NHAN_VIEN + " (" +
                        COLUMN_NHAN_VIEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NHAN_VIEN_TEN + " TEXT, " +
                        COLUMN_NHAN_VIEN_CHUC_VU + " TEXT, " +
                        COLUMN_NHAN_VIEN_EMAIL + " TEXT, " +
                        COLUMN_NHAN_VIEN_SDT + " TEXT, " +
                        COLUMN_NHAN_VIEN_ANH_DAI_DIEN + " TEXT, " +
                        COLUMN_NHAN_VIEN_MA_DON_VI + " INTEGER, " +
                        "FOREIGN KEY(" + COLUMN_NHAN_VIEN_MA_DON_VI + ") REFERENCES " + TABLE_DON_VI + "(" + COLUMN_DON_VI_ID + ")" +
                        ");";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(TABLE_CREATE_DON_VI);
            sqLiteDatabase.execSQL(TABLE_CREATE_NHAN_VIEN);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NHAN_VIEN);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DON_VI);
            onCreate(sqLiteDatabase);
        }
    }
