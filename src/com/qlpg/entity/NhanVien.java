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
public class NhanVien {
    String NhanVienID, HoTen, Email, Sdt, UserID,Vaitro;
    Date NgaySinh;

    public NhanVien() {
    }
    public NhanVien(String NhanVienID, String HoTen, String Email, String Sdt, String UserID, Date NgaySinh) {
        this.NhanVienID = NhanVienID;
        this.HoTen = HoTen;
        this.Email = Email;
        this.Sdt = Sdt;
        this.UserID = UserID;
        this.NgaySinh = NgaySinh;
        this.Vaitro = Vaitro;
    }

    public String getVaitro() {
        return Vaitro;
    }

    public void setVaitro(String Vaitro) {
        this.Vaitro = Vaitro;
    }
    public String getNhanVienID() {
        return NhanVienID;
    }

    public void setNhanVienID(String NhanVienID) {
        this.NhanVienID = NhanVienID;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

   

   
}
