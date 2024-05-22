/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlpg.entity;

/**
 *
 * @author titob
 */
public class GoiTap {
    String GoiTapID, TenGoiTap, ThoiLuong;
    float Gia;

    public GoiTap(String GoiTapID, String TenGoiTap, String ThoiLuong, float Gia) {
        this.GoiTapID = GoiTapID;
        this.TenGoiTap = TenGoiTap;
        this.ThoiLuong = ThoiLuong;
        this.Gia = Gia;
    }

    public GoiTap() {
    }

    public String getGoiTapID() {
        return GoiTapID;
    }

    public void setGoiTapID(String GoiTapID) {
        this.GoiTapID = GoiTapID;
    }

    public String getTenGoiTap() {
        return TenGoiTap;
    }

    public void setTenGoiTap(String TenGoiTap) {
        this.TenGoiTap = TenGoiTap;
    }

    public String getThoiLuong() {
        return ThoiLuong;
    }

    public void setThoiLuong(String ThoiLuong) {
        this.ThoiLuong = ThoiLuong;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }
}
