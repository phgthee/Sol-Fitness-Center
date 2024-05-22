/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlpg.dao;

import com.qlpg.entity.HoaDon;
import com.qlpg.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author truon
 */
public class HoaDonDAO extends qlpgDAO<HoaDon, String> {

    final String INSERT_SQL = "INSERT INTO HoaDon(HoaDonID, NgayLap, TongTien, GoiTapID, NhanVienID, HocVienID) values(?,?,?,?,?,?)";
    final String DELETE_SQL = "Delete from HoaDon where HocVienID=?";
    final String UPDATE_GOITAP_TONGTINEN = "update HoaDon set TongTien=?,GoiTapID=? where HoaDonID=?";

    @Override
    public void insert(HoaDon hd) {
        JdbcHelper.update(INSERT_SQL, hd.getHoaDonID(), hd.getNgaylap(), hd.getTongTien(), hd.getGoiTapID(), hd.getNhanVienID(),
                hd.getHocVienID());
    }

    @Override
    public void update(HoaDon entity) {
    }

    public void UPDATE_GOITAP_TONGTINEN(HoaDon hd) {
        JdbcHelper.update(UPDATE_GOITAP_TONGTINEN, hd.getTongTien(), hd.getGoiTapID(), hd.getHoaDonID());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<HoaDon> selectAll() {
        String sql = "SELECT * FROM HoaDon";
        return selectBySql(sql);
    }

    @Override
    public HoaDon selectById(String id) {
        String sql = "SELECT * FROM HoaDon WHERE HoaDonID=?";
        List<HoaDon> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HoaDon entity = new HoaDon();
                entity.setHoaDonID(rs.getString("HoaDonID"));
                entity.setNgaylap(rs.getDate("NgayLap"));
                entity.setTongTien(rs.getFloat("TongTien"));
                entity.setGoiTapID(rs.getString("GoiTapID"));
                entity.setNhanVienID(rs.getString("NhanVienID"));
                entity.setHocVienID(rs.getString("HocVienID"));
                list.add(entity);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);

        }
        return list;
    }

    public List<HoaDon> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM HoaDon WHERE HoaDonID LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }
}
