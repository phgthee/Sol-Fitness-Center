/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlpg.dao;

import com.qlpg.entity.ThietBi;
import com.qlpg.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khoitran
 */
public class thietBiDAO extends qlpgDAO<ThietBi, String> {

    final String INSERT_SQL = "INSERT INTO ThietBi(ThietBiID, TenThietBi, SoLuong, NhaCungCap, NgayNhapThietBi) values(?,?,?,?,?)";
    final String UPDATE_SQL = "Update ThietBi set TenThietBi =?, SoLuong=?,NhaCungCap=?,NgayNhapThietBi=? where ThietBiID=?";
    final String DELETE_SQL = "Delete from ThietBi where ThietBiID=?";
    final String SELECT_ALL_SQL = "Select*from ThietBi";
    final String SELECT_ByID_SQL = "Select*from ThietBi where ThietBiID=?";

    @Override
    public void insert(ThietBi entity) {
        JdbcHelper.update(INSERT_SQL, entity.getThietBiID(), entity.getTenThietBi(),
                entity.getSoLuong(), entity.getNhaCungCap(), entity.getNgayNhap());
    }

    @Override
    public void update(ThietBi entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenThietBi(),
                entity.getSoLuong(), entity.getNhaCungCap(), entity.getNgayNhap(), entity.getThietBiID());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<ThietBi> selectAll() {
        return selectBySql(SELECT_ALL_SQL); // select all thì không có tham số

    }

    @Override
    public ThietBi selectById(String id) {
        List<ThietBi> list = selectBySql(SELECT_ByID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ThietBi> selectBySql(String sql, Object... args) {
        List<ThietBi> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                ThietBi entity = new ThietBi();
                entity.setThietBiID(rs.getString("ThietBiID"));
                entity.setTenThietBi(rs.getString("TenThietBi"));
                entity.setSoLuong(rs.getInt("Soluong"));
                entity.setNhaCungCap(rs.getString("NhaCungCap"));
                entity.setNgayNhap(rs.getDate("NgayNhapThietBi"));

                list.add(entity);
            }
        } catch (Exception e) {
            System.out.println(e);

            throw new RuntimeException(e);

        }
        return list;
    }
}
