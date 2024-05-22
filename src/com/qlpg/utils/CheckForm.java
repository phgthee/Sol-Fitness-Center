/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlpg.utils;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author truon
 */
public class CheckForm {
    public static boolean DeTrong(JTextField field,StringBuilder sb,String msg){
        boolean ok= true;
        
        if(field.getText().equals("")){
            sb.append(msg).append("\n");
            field.setBackground(Color.yellow);
            ok=false;
        }else{
            field.setBackground(Color.white);
            ok=true;
        }
        return ok;
    }
    public static boolean ma(JTextField field,StringBuilder sb,String msg){
          boolean ok= true;
          if(!DeTrong(field, sb, "Không để trống mã ")){
              return false;
          }
          if(field.getText().length()>7){
             sb.append("Mã không quá 7 kí tự ");
             field.setBackground(Color.yellow);
                      ok=false;
           }
          if(ok){
              field.setBackground(Color.white);
          }
         return ok;
     }
    
    public static void matKhau(JPasswordField filed ,StringBuilder sb,String msg ){
       String pass=new String(filed.getPassword());
        if(pass.equals("")){
           sb.append(msg+"\n");
           filed.setBackground(Color.yellow);
           filed.requestFocus();
       }else{
           filed.setBackground(Color.white);
       }
    }
 
    public static boolean thoigianGT(JTextField field,StringBuilder sb,String msg){
         
          boolean ok= true;
          if(!DeTrong(field, sb, "Không để trống thời gian gói tập  ")){
              return false;
          }
          if((!field.getText().equals(""))){
                try{
                  int t= Integer.parseInt(field.getText());
                  if ((t<=0)){
                      sb.append("Thời gian phải >=1 ngày  \n");
                      field.setBackground(Color.yellow);
                      ok=false;
                  }
              }catch(Exception ex){
                  sb.append("Không đúng định dạng số \n");
                  field.setBackground(Color.yellow);
                  ok=false;
              }}
          if(ok){
              field.setBackground(Color.white);
          }
         return ok;
     }
      public static boolean DonGia(JTextField field,StringBuilder sb,String errorMessage){
         
          boolean ok= true;
          if(!DeTrong(field, sb, "Không để trống đơn giá gói tập  ")){
              return false;
          }
          if((!field.getText().equals(""))){
                try{
                  
                  Double t= Double.parseDouble(field.getText());
                  if (t<=10000 ){
                      sb.append("Đơn giá phải >= 10000  \n");
                      field.setBackground(Color.yellow);
                      ok=false;
                  }
              }catch(Exception ex){
                  sb.append("Không đúng định dạng số \n");
                  field.setBackground(Color.yellow);
                  ok=false;
              }}
          if(ok){
              field.setBackground(Color.white);
          }
         return ok;
     }
    
     public static boolean checkSDT(JTextField field,StringBuilder sb,String msg){
          boolean ok= true;
          if(!DeTrong(field, sb, "Không để trống SDT ")){
              field.setBackground(Color.yellow);
              return false;
          }
          else if(field.getText().length()>11||field.getText().length()<10){
              sb.append("Số điện thoại từ 10 đến 11 số \n");
              field.setBackground(Color.yellow);
              return false;
          }
            Pattern pattern = Pattern.compile("[0][1-9]{1}[0-9]{8,9}");
            String so1=field.getText();
            Matcher matcher1 = pattern.matcher(so1);
            if(!(matcher1.matches())){
                sb.append("không đúng định dạng số điện thoại\n");
                 field.setBackground(Color.yellow);
            }
          if(ok){
              field.setBackground(Color.white);
          }
         return ok;
     }
    
     public static boolean checkngaySinh(JTextField field,StringBuilder sb,String msg){
          boolean ok= true;
          SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
          try{
            if(!DeTrong(field, sb, "Không để trống ngày ")){
                field.setBackground(Color.yellow);
                return false;
            }
            else{
                df.parse(field.getText());
                field.setBackground(Color.white);
            }
          }
         catch(Exception e){
             sb.append("Không đúng định ngày tháng năm \n");
             field.setBackground(Color.yellow);
             return false;
         }
        return ok;  
     }
}
