/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlpg.dao;

import com.qlpg.entity.NguoiDung;
import com.qlpg.entity.NhanVien;
import com.qlpg.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khoitran
 */
public class NhanVienDAO extends qlpgDAO<NhanVien, String> {

    final String INSERT_SQL = "INSERT INTO NhanVien(NhanVienID, HoTen, NgaySinh,Email,Sdt,UserID,VaiTro) values(?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "Update NhanVien set HoTen =?,NgaySinh=?,Email=?,Sdt=?,VaiTro=? where NhanVienID=?";
    final String DELETE_SQL = "Delete from NhanVien where NhanVienID=?";
    final String SELECT_ALL_SQL = "Select*from NhanVien";
    final String SELECT_ByID_SQL = "Select*from NhanVien where NhanVienID=?";
    final String select_only_HuanLuyenVien = "select * from NhanVien where VaiTro = N'Huấn luyện viên'";
    //  final String select_only_NhanVien = "select * from NhanVien";
    final String select_only_NhanVien = "select * from NhanVien where NhanVienID not in(select NhanVienID from NhanVien where NhanVienID like '%QL%')";
    final String SELECT_ByName_SQL = "final String SELECT_ByID_SQL =?";
    final String SELECT_NhanVienID_byUserID = "select * from NhanVien where UserID = ?";

    @Override
    public void insert(NhanVien entity) {
        JdbcHelper.update(INSERT_SQL, entity.getNhanVienID(), entity.getHoTen(),
                entity.getNgaySinh(), entity.getEmail(), entity.getSdt(), entity.getUserID(), entity.getVaitro());
    }

    @Override
    public void update(NhanVien entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getHoTen(),
                entity.getNgaySinh(), entity.getEmail(), entity.getSdt(), entity.getVaitro(),
                entity.getNhanVienID()
        );
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySql(SELECT_ALL_SQL); // select all thì không có tham số

    }
    //@Override

    public List<NhanVien> select_only_HuanLuyenVien() {
        return selectBySql(select_only_HuanLuyenVien); // select all thì không có tham số

    }

    public List<NhanVien> select_only_NhanVien() {
        return selectBySql(select_only_NhanVien); // select all thì không có tham số

    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = selectBySql(SELECT_ByID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public NhanVien SELECT_NhanVienID_byUserID(String id) {
        List<NhanVien> list = selectBySql(SELECT_NhanVienID_byUserID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setNhanVienID(rs.getString("NhanVienID"));
                entity.setHoTen(rs.getString("HoTen"));
                // entity.setVaiTro(rs.getString("Vaitro"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setEmail(rs.getString("Email"));
                entity.setSdt(rs.getString("Sdt"));
                entity.setUserID(rs.getString("UserID"));
                entity.setVaitro(rs.getString("VaiTro"));
                list.add(entity);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return list;
    }
}
