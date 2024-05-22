/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlpg.entity;

import java.util.Date;

/**
 *
 * @author truon
 */
public class HoaDon {
    String HoaDonID;
    Date Ngaylap;
    float TongTien;
    String GoiTapID;
    String NhanVienID;
    String HocVienID;

    public HoaDon() {
    }

    public HoaDon(String HoaDonID, Date Ngaylap, float TongTien, String GoiTapID, String NhanVienID, String HocVienID) {
        this.HoaDonID = HoaDonID;
        this.Ngaylap = Ngaylap;
        this.TongTien = TongTien;
        this.GoiTapID = GoiTapID;
        this.NhanVienID = NhanVienID;
        this.HocVienID = HocVienID;
    }

    
    public String getHoaDonID() {
        return HoaDonID;
    }

    public void setHoaDonID(String HoaDonID) {
        this.HoaDonID = HoaDonID;
    }

    public Date getNgaylap() {
        return Ngaylap;
    }

    public void setNgaylap(Date Ngaylap) {
        this.Ngaylap = Ngaylap;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

    public String getGoiTapID() {
        return GoiTapID;
    }

    public void setGoiTapID(String GoiTapID) {
        this.GoiTapID = GoiTapID;
    }

    public String getNhanVienID() {
        return NhanVienID;
    }

    public void setNhanVienID(String NhanVienID) {
        this.NhanVienID = NhanVienID;
    }

    public String getHocVienID() {
        return HocVienID;
    }

    public void setHocVienID(String HocVienID) {
        this.HocVienID = HocVienID;
    }
    
    
}
