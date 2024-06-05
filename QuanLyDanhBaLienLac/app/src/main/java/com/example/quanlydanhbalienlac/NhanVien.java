package com.example.quanlydanhbalienlac;

public class NhanVien {
    private int MaNhanVien;

    private String Hoten;

    private String ChucVu;

    private String Email;

    private String sdt;

    private String AnhDaiDien;

    private int MaDonVi;

    public NhanVien(int maNhanVien, String hoten, String chucVu, String email, String sdt, String anhDaiDien, int maDonVi) {
        MaNhanVien = maNhanVien;
        Hoten = hoten;
        ChucVu = chucVu;
        Email = email;
        this.sdt = sdt;
        AnhDaiDien = anhDaiDien;
        MaDonVi = maDonVi;
    }

    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        MaNhanVien = maNhanVien;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getAnhDaiDien() {
        return AnhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        AnhDaiDien = anhDaiDien;
    }

    public int getMaDonVi() {
        return MaDonVi;
    }

    public void setMaDonVi(int maDonVi) {
        MaDonVi = maDonVi;
    }
}
