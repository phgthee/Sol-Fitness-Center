/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlpg.dao;

import com.qlpg.entity.VaiTro;
import com.qlpg.utils.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author titob
 */
public class VaitroDAO extends qlpgDAO<VaiTro, String> {

    final String INSERT_SQL = "INSERT INTO Vaitro(Vaitro,VaitroID) values(?,?)";
    final String UPDATE_SQL = "Update Vaitro set Vaitro =? where VaitroID=?";
    final String DELETE_SQL = "Delete from Vaitro where VaitroID=?";
    final String SELECT_ALL_SQL = "Select*from Vaitro";
    final String SELECT_ByID_SQL = "Select*from Vaitro where VaitroID=?";

    @Override
    public void insert(VaiTro entity) {
        JdbcHelper.update(INSERT_SQL, entity.getVaitro(), entity.getVaitroID());
    }

    @Override
    public void update(VaiTro entity) {
        JdbcHelper.update(INSERT_SQL, entity.getVaitro(), entity.getVaitroID());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<VaiTro> selectAll() {
        return selectBySql(SELECT_ALL_SQL); // select all thì không có tham số    
    }

    @Override
    public VaiTro selectById(String id) {
        List<VaiTro> list = selectBySql(SELECT_ByID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<VaiTro> selectBySql(String sql, Object... args) {
        List<VaiTro> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                VaiTro entity = new VaiTro();
                entity.setVaitro(rs.getString("Vaitro"));
                entity.setVaitroID(rs.getString("VaitroID"));
                list.add(entity);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return list;
    }
}
