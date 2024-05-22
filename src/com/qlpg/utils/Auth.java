/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlpg.utils;

import com.qlpg.entity.NguoiDung;

/**
 *
 * @author titob
 */
public class Auth {

    public static NguoiDung user = null;

    public static void Clear() {
        Auth.user = null;
    }

    public static boolean isLogin() {
        return Auth.user != null;
    }
}
