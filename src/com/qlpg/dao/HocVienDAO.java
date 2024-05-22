/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlpg.dao;

import com.qlpg.entity.HocVien;
import com.qlpg.entity.HocVien;
import com.qlpg.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khoitran
 */
public class HocVienDAO extends qlpgDAO<HocVien, String> {

    final String INSERT_SQL = "INSERT INTO HocVien(HocVienID, HoTen, NgaySinh,Sdt,Email,UserID,GoiTapID) values(?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "Update HocVien set HoTen=?, NgaySinh=?, Sdt=?, Email=?,GoiTapID=? where HocVienID=?";
    final String DELETE_SQL = "Delete from HocVien where HocVienID=?";
    final String SELECT_ALL_SQL = "Select*from HocVien";
    final String SELECT_ByID_SQL = "Select*from HocVien where HocVienID = ?";
    final String SELECT_USERID_BY_MAHOCVIEN = "SELECT UserID from HocVien where HocVienID=?";
    // final String UPDATE_DEMO = "Update HocVien set HoTen=? where HocVienID=?";

    @Override
    public void insert(HocVien entity) {
        JdbcHelper.update(INSERT_SQL, entity.getHocVienID(), entity.getHoTenHV(),
                entity.getNgaySinhHV(), entity.getSdtHV(), entity.getEmailHV(), entity.getUserID(), entity.getGoiTapID());
    }

    @Override
    public void update(HocVien entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getHoTenHV(), entity.getNgaySinhHV(),
                entity.getSdtHV(),
                entity.getEmailHV(), entity.getGoiTapID(),
                entity.getHocVienID()
        );
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<HocVien> selectAll() {
        return selectBySql(SELECT_ALL_SQL); // select all thì không có tham số

    }

    @Override
    public HocVien selectById(String id) {
        List<HocVien> list = selectBySql(SELECT_ByID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<HocVien> selectBy_ID(String maHV) {
        return selectBySql(SELECT_ByID_SQL, "%" + maHV + "%");
    }


    public List<HocVien> SELECT_USERID_BY_MAHOCVIEN(String id) {
        String sql = "SELECT UserID FROM HocVien WHERE HocVienID=?";
//        List<HocVien> list = selectBySql(sql, id);
//        if (list.isEmpty()) {
//            return null;
//        }
//        return list.get(0);
        return this.selectBySql(sql, id);
    }

    @Override
    public List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HocVien entity = new HocVien();
                entity.setHocVienID(rs.getString("HocVienID"));
                entity.setHoTenHV(rs.getString("HoTen"));
                entity.setEmailHV(rs.getString("Email"));
                entity.setNgaySinhHV(rs.getDate("NgaySinh"));
                entity.setSdtHV(rs.getString("Sdt"));
                entity.setGoiTapID(rs.getString("GoiTapID"));
                entity.setUserID(rs.getString("UserID"));
                list.add(entity);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);

        }
        return list;
    }
}
