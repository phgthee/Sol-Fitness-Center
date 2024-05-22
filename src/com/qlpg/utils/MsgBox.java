/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlpg.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author titob
 */
public class MsgBox {
    // cửa số thông báo
    public static void alert(Component parent, String message) { // đưa thông báo
        JOptionPane.showMessageDialog(parent, message,
                "Hệ thống quản lý phòng gym", JOptionPane.INFORMATION_MESSAGE);
    }
        // cửa số Xác nhận
    public static boolean confirm(Component parent, String message) {  // yêu cầu người dùng xác nhận
        int result = JOptionPane.showConfirmDialog(parent, message,
                "Hệ thống quản lý phòng gym",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
        // cửa sổ nhập thông tin 
    public static String prompt (Component parent, String message)  // yêu cầu người dùng nhập data
    {
        return JOptionPane.showInputDialog(parent, message,
                "Hệ thống quản lý phòng gym", JOptionPane.INFORMATION_MESSAGE);
    }
}
