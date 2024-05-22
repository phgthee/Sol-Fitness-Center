/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlpg.dao;

import com.qlpg.entity.GoiTap;
import com.qlpg.utils.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author titob
 */
public class GoiTapDAO extends qlpgDAO<GoiTap, String>{
  final String INSERT_SQL = "INSERT INTO GoiTap(GoiTapID, TenGoiTap, Gia, ThoiLuong) values(?,?,?,?)";
    final String UPDATE_SQL = "Update GoiTap set TenGoiTap=?,Gia=?,ThoiLuong=? where GoiTapID=?";
    final String DELETE_SQL = "Delete from GoiTap where GoiTapID=?";
    final String SELECT_ALL_SQL = "Select*from GoiTap";
    final String SELECT_ByID_SQL = "Select*from GoiTap where GoiTapID=?";
    @Override
    public void insert(GoiTap entity) {
        JdbcHelper.update(INSERT_SQL, entity.getGoiTapID(), entity.getTenGoiTap(),
                entity.getGia(),entity.getThoiLuong());
    }

    @Override
    public void update(GoiTap entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenGoiTap(),
                entity.getGia(),entity.getThoiLuong(),entity.getGoiTapID());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<GoiTap> selectAll() {
        return selectBySql(SELECT_ALL_SQL); // select all thì không có tham số

    }

    @Override
    public GoiTap selectById(String id) {
         List<GoiTap> list = selectBySql(SELECT_ByID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<GoiTap> selectBySql(String sql, Object... args) {
         List<GoiTap> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                GoiTap entity = new GoiTap();
                entity.setGoiTapID(rs.getString("GoiTapID"));
                entity.setTenGoiTap(rs.getString("TenGoiTap"));
                entity.setGia(rs.getFloat("Gia"));
                entity.setThoiLuong(rs.getString("ThoiLuong"));
                list.add(entity);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);

        }
        return list;
    }    
}
