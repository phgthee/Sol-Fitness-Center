/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlpg.dao;

/**
 *
 * @author khoitran
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.qlpg.entity.NguoiDung;
import com.qlpg.entity.ThietBi;
import com.qlpg.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qlpg.entity.ThietBi;
import com.qlpg.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khoitran
 */
public class nguoiDungDAO extends qlpgDAO<NguoiDung, String> {

    final String INSERT_SQL = "INSERT INTO NGuoiDung(UserID, Password, VaiTro) values(?,?,?)";
    final String UPDATE_SQL = "Update NguoiDung set Password =?,Vaitro=? where UserID=?";
    final String DELETE_SQL = "Delete from NguoiDung where UserID=?";
    final String SELECT_ALL_SQL = "Select*from NguoiDung";
    final String SELECT_ByID_SQL = "Select*from NguoiDung where UserID=?";

    @Override
    public void insert(NguoiDung entity) {
        JdbcHelper.update(INSERT_SQL, entity.getUserID(), entity.getPassWord(),
                entity.getVaiTro());
    }

    @Override
    public void update(NguoiDung entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getPassWord(), entity.getVaiTro(), entity.getUserID()
        );
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NguoiDung> selectAll() {
        return selectBySql(SELECT_ALL_SQL); // select all thì không có tham số

    }

    @Override
    public NguoiDung selectById(String id) {
        List<NguoiDung> list = selectBySql(SELECT_ByID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NguoiDung> selectBySql(String sql, Object... args) {
        List<NguoiDung> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NguoiDung entity = new NguoiDung();
                entity.setUserID(rs.getString("UserID"));
                entity.setPassWord(rs.getString("Password"));
                entity.setVaiTro(rs.getString("Vaitro"));
                list.add(entity);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);

        }
        return list;
    }
}
