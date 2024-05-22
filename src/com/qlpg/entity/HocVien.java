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
public class HocVien {
        String HocVienID, HoTenHV, EmailHV, SdtHV, UserID, GoiTapID;
        Date NgaySinhHV;

    public String getHocVienID() {
        return HocVienID;
    }

    public void setHocVienID(String HocVienID) {
        this.HocVienID = HocVienID;
    }

    public String getHoTenHV() {
        return HoTenHV;
    }

    public void setHoTenHV(String HoTenHV) {
        this.HoTenHV = HoTenHV;
    }

    public String getEmailHV() {
        return EmailHV;
    }

    public void setEmailHV(String EmailHV) {
        this.EmailHV = EmailHV;
    }

    public String getSdtHV() {
        return SdtHV;
    }

    public void setSdtHV(String SdtHV) {
        this.SdtHV = SdtHV;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getGoiTapID() {
        return GoiTapID;
    }

    public void setGoiTapID(String GoiTapID) {
        this.GoiTapID = GoiTapID;
    }

    public Date getNgaySinhHV() {
        return NgaySinhHV;
    }

    public void setNgaySinhHV(Date NgaySinhHV) {
        this.NgaySinhHV = NgaySinhHV;
    }

    public HocVien(String HocVienID, String HoTenHV, String EmailHV, String SdtHV, String UserID, String GoiTapID, Date NgaySinhHV) {
        this.HocVienID = HocVienID;
        this.HoTenHV = HoTenHV;
        this.EmailHV = EmailHV;
        this.SdtHV = SdtHV;
        this.UserID = UserID;
        this.GoiTapID = GoiTapID;
        this.NgaySinhHV = NgaySinhHV;
    }

    public HocVien() {
    }
}
