/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlpg.entity;

import java.util.Date;

/**
 *
 * @author titob
 */
public class ThietBi {
    String ThietBiID, TenThietBi, NhaCungCap;
    int SoLuong;
    Date NgayNhap;

    public String getThietBiID() {
        return ThietBiID;
    }

    public void setThietBiID(String ThietBiID) {
        this.ThietBiID = ThietBiID;
    }

    public String getTenThietBi() {
        return TenThietBi;
    }

    public void setTenThietBi(String TenThietBi) {
        this.TenThietBi = TenThietBi;
    }

    public String getNhaCungCap() {
        return NhaCungCap;
    }

    public void setNhaCungCap(String NhaCungCap) {
        this.NhaCungCap = NhaCungCap;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public ThietBi(String ThietBiID, String TenThietBi, String NhaCungCap, int SoLuong, Date NgayNhap) {
        this.ThietBiID = ThietBiID;
        this.TenThietBi = TenThietBi;
        this.NhaCungCap = NhaCungCap;
        this.SoLuong = SoLuong;
        this.NgayNhap = NgayNhap;
    }

    public ThietBi() {
    }
}
