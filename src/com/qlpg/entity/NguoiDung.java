/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlpg.entity;

/**
 *
 * @author titob
 */
public class NguoiDung {
    String UserID, PassWord, VaiTro;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    public String getVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(String VaiTro) {
        this.VaiTro = VaiTro;
    }

    public NguoiDung(String UserID, String PassWord, String VaiTro) {
        this.UserID = UserID;
        this.PassWord = PassWord;
        this.VaiTro = VaiTro;
    }

    public NguoiDung() {
    }
}
