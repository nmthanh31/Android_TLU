package com.example.quanlydanhbalienlac;

import androidx.annotation.NonNull;

public class DonVi {
    private int MaDonVi;
    private String TenDonVi;

    private String Email;

    private String Website;

    private String Logo;

    private String DiaChi;

    private String Sdt;

    private int MaDonViCha;


    public DonVi(int maDonVi, String tenDonVi, String email, String website, String logo, String diaChi, String sdt, int maDonViCha) {
        MaDonVi = maDonVi;
        TenDonVi = tenDonVi;
        Email = email;
        Website = website;
        Logo = logo;
        DiaChi = diaChi;
        Sdt = sdt;
        MaDonViCha = maDonViCha;
    }

    public int getMaDonVi() {
        return MaDonVi;
    }

    public void setMaDonVi(int maDonVi) {
        MaDonVi = maDonVi;
    }

    public String getTenDonVi() {
        return TenDonVi;
    }

    public void setTenDonVi(String tenDonVi) {
        TenDonVi = tenDonVi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public int getMaDonViCha() {
        return MaDonViCha;
    }

    public void setMaDonViCha(int maDonViCha) {
        MaDonViCha = maDonViCha;
    }

    @NonNull
    @Override
    public String toString() {
        return TenDonVi; // Điều này giúp hiển thị tên trong Spinner
    }
}
