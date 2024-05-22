/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlpg.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author titob
 */
public class XDate {

    static SimpleDateFormat formater = new SimpleDateFormat();

    //chuyển đổi String sang Date
    public static Date toDate(String date, String pattern) {  // String d = "02-08-2000"
        // Date date = XDate.toDate(s,"dd-MM-yyyy")
        try {
            formater.applyPattern(pattern);
            return formater.parse(date);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    // chuyển đổi Date sang String
    public static String toString(Date date, String pattern) // Date now = new Date();
    // String s = XDate.toString(now,"dd-MM-yyyy")
    {
        formater.applyPattern(pattern);
        return formater.format(date);
    }

    // trả về thời gian hiện tại
    public static Date now() {
        return new Date();
    }

    // bổ sung số ngày vào thơi gian
    public static Date addDays(Date date, long days) // Date now = new Date();
    // Date after = XDate.addDays(now,10)   bổ sung 10 ngày vào 
    {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

}
