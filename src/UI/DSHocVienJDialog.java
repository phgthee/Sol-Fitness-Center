/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI;

import com.qlpg.dao.GoiTapDAO;
import com.qlpg.dao.HoaDonDAO;
import com.qlpg.dao.HocVienDAO;
import com.qlpg.dao.NhanVienDAO;
import com.qlpg.dao.nguoiDungDAO;
import com.qlpg.entity.GoiTap;
import com.qlpg.entity.HoaDon;
import com.qlpg.entity.HocVien;
import com.qlpg.entity.NguoiDung;
import com.qlpg.entity.NhanVien;
import com.qlpg.utils.MsgBox;
import com.qlpg.utils.ToanCuc;
import com.qlpg.utils.XDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author titob
 */
public class DSHocVienJDialog extends javax.swing.JDialog {

    /**
     * Creates new form DSHocVienJDialog
     */
    public DSHocVienJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inti();
        soLuongUser();

    }

    int i;
    int u;
    int row = 0;
    int slHD;
    nguoiDungDAO ndDao = new nguoiDungDAO();
    HoaDonDAO hdDAO = new HoaDonDAO();
    HocVienDAO hvDAO = new HocVienDAO();
    GoiTapDAO gtDAO = new GoiTapDAO();
    NhanVienDAO nvDAO = new NhanVienDAO();
    ArrayList<HocVien> list = new ArrayList<HocVien>();
    ToanCuc tt;

    public void inti() {

        this.setLocationRelativeTo(null);
        fillTable();
        //  fillCBO();
        tabs.setSelectedIndex(1);
        txtMaHocVien.setEnabled(false);

    }

    public void fillTable() {
        int SoThuTu = 1;
        DefaultTableModel model = (DefaultTableModel) tblHoiVien.getModel();
        model.setRowCount(0);
        try {
            List<HocVien> list = hvDAO.selectAll();
            for (HocVien hv : list) {
                Object[] row = {
                    hv.getHocVienID(),
                    hv.getHoTenHV(),
                    XDate.toString(hv.getNgaySinhHV(), "dd-MM-yyyy"),
                    //hv.getNgaySinhHV(),
                    hv.getSdtHV(),
                    hv.getEmailHV(),
                    hv.getGoiTapID(),
                    SoThuTu++
                };
                model.addRow(row);
                System.out.println(row);
                
                
            }
            List<HocVien> listND = hvDAO.selectBy_ID(txtMaHocVien.getText());
            i = list.size() + 1;
            System.out.println("Số lượng học viên hiện tại: " + i);
            
            fillCBO();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void fillCBO() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboGoiTap.getModel();
        model.removeAllElements();
        try {
            List<GoiTap> list = gtDAO.selectAll();
            for (GoiTap gt : list) {
                model.addElement(gt.getGoiTapID().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        try {
            // if (isValidRowIndex(this.row) && this.row < tblHoiVien.getRowCount()) {
            String maHV = (String) tblHoiVien.getValueAt(this.row, 0);
            HocVien hv = hvDAO.selectById(maHV);
            this.setForm(hv);
            tabs.setSelectedIndex(0);
//            } else {
//                System.out.println("Chỉ số dòng không hợp lệ: " + this.row);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

//    private boolean isValidRowIndex(int rowIndex) {
//        return rowIndex >= 0 && rowIndex < tblHoiVien.getRowCount();
//    }
    public void soLuongUser() {
        try {
            List<NguoiDung> listND = ndDao.selectAll();
            for (NguoiDung nd : listND) {
                Object[] row = {
                    nd.getUserID()
                };
                // model.addRow(row);
            }
            u = listND.size() + 1;
            System.out.println("Số lượng người dùng hiện tại: " + u);
        } catch (Exception e) {
        }
    }

    public void soLuongHD() {
        try {
            List<HoaDon> listHD = hdDAO.selectAll();
            for (HoaDon hd : listHD) {
                Object[] row = {
                    hd.getHoaDonID()
                };
                // model.addRow(row);
            }
            slHD = listHD.size() + 1;
            System.out.println("Số lượng hóa đơn hiệ tại: " + slHD);
        } catch (Exception e) {
        }
    }

    public void clearForm() {
        HocVien hv = new HocVien();
        // String demo = "";
        // hv.setMaNhanVien;
        hv.setNgaySinhHV(new Date());
        setForm(hv);
        if (i >= 10) {
            txtMaHocVien.setText("HV0" + String.valueOf(i));
        } else {
            txtMaHocVien.setText("HV00" + String.valueOf(i));
        }

        txtNgaySinh.setText("");
        cboGoiTap.setSelectedIndex(0);

    }

    public HoaDon getFormHD() {
        HoaDon hd = new HoaDon();
        if (slHD >= 10) {
            hd.setHoaDonID("HD0" + String.valueOf(slHD));
        } else {
            hd.setHoaDonID("HD00" + String.valueOf(slHD));
        }
        Date now = new Date();
        hd.setNgaylap(now);
        GoiTap gt = gtDAO.selectById(cboGoiTap.getSelectedItem().toString());
        // HoaDon hd=new HoaDon();
        hd.setTongTien(gt.getGia());
        hd.setGoiTapID(gt.getGoiTapID());
        NhanVien nv = nvDAO.SELECT_NhanVienID_byUserID(tt.getMa());
        hd.setNhanVienID(nv.getNhanVienID());
        hd.setHocVienID(txtMaHocVien.getText());
        return hd;
    }

    public HocVien getForm() {
        HocVien hv = new HocVien();
        if (i >= 10) {
            hv.setHocVienID("HV0" + String.valueOf(i));
        } else {
            hv.setHocVienID("HV00" + String.valueOf(i));
        }
        hv.setHoTenHV(txtHoTen.getText());
        String ngaySinhString = txtNgaySinh.getText();
        hv.setNgaySinhHV(XDate.toDate(ngaySinhString, "dd-MM-yyyy"));
        hv.setSdtHV(txtSDT.getText());
        hv.setEmailHV(txtEmail.getText());
        hv.setUserID("User0" + String.valueOf(u));
        hv.setGoiTapID(cboGoiTap.getSelectedItem().toString());
        return hv;
    }

    public NguoiDung LayForm() {
        NguoiDung nd = new NguoiDung();
        nd.setUserID("User0" + String.valueOf(u));
        nd.setPassWord("123");
        nd.setVaiTro("Học viên");
        return nd;
    }

    public void setForm(HocVien hv) {
        if (hv != null) {
            txtMaHocVien.setText(String.valueOf(hv.getHocVienID()));
            txtHoTen.setText(hv.getHoTenHV());
            if (hv.getNgaySinhHV() != null) {
                txtNgaySinh.setText(XDate.toString(hv.getNgaySinhHV(), "dd-MM-yyyy"));
            } else {
                txtNgaySinh.setText("");
            }
            txtEmail.setText(hv.getEmailHV());
            txtSDT.setText(hv.getSdtHV());
            cboGoiTap.setSelectedItem(hv.getGoiTapID());
        } else {
            // Xử lý trường hợp khi đối tượng HocVien là null
            // Ví dụ: Xóa nội dung các trường trên form
            txtMaHocVien.setText("");
            txtHoTen.setText("");
            txtNgaySinh.setText("");
            txtEmail.setText("");
            txtSDT.setText("");
            cboGoiTap.setSelectedIndex(0);
        }
    }

    public void insert() {
        HocVien model = getForm();
        System.out.println(model.getHocVienID());
        NguoiDung mode = LayForm();
        HoaDon mod = getFormHD();
        try {
            ndDao.insert(mode);
            MsgBox.alert(this, "Thêm mới thành công ở bảng NGƯỜI DÙNG!");
            hvDAO.insert(model);
            MsgBox.alert(this, "Thêm mới thành công ở bảng HỌC VIÊN!");
            hdDAO.insert(mod);
            MsgBox.alert(this, "Thêm mới thành công ở bảng HÓA ĐƠN!");
            fillTable();
        } catch (Exception e) {
            System.out.println(e);
            MsgBox.alert(this, "Thêm mới thất bại");

        }
    }

    public HoaDon lapTongTien() {
        this.soLuongHD();
        GoiTap gt = new GoiTap();
        HoaDon hd = new HoaDon();

        gt = gtDAO.selectById(cboGoiTap.getSelectedItem().toString());

        hd.setTongTien(gt.getGia());
        System.out.println(gt.getGia());
        hd.setGoiTapID(gt.getGoiTapID());
        System.out.println(gt.getGoiTapID());

        hd.setHoaDonID("HD00" + String.valueOf(slHD - 1));
        System.out.println("HD00" + String.valueOf(slHD - 1));
        return hd;
    }

    public HocVien getFormCapNhat() {
        HocVien cnHV = new HocVien();
        cnHV.setHoTenHV(txtHoTen.getText());

        String ngaySinhString = txtNgaySinh.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date ngaySinhDate = dateFormat.parse(ngaySinhString);
            cnHV.setNgaySinhHV(ngaySinhDate);
        } catch (ParseException e) {
            e.printStackTrace(); // Xử lý lỗi khi chuỗi không hợp lệ
        }

        cnHV.setSdtHV(txtSDT.getText());
        cnHV.setEmailHV(txtEmail.getText());
        cnHV.setGoiTapID((String) cboGoiTap.getSelectedItem());
        cnHV.setHocVienID(txtMaHocVien.getText());
        return cnHV;
    }

    public void CapNhat() {

        try {
            HocVien model = getFormCapNhat();
            HoaDon mod = lapTongTien();
            hdDAO.UPDATE_GOITAP_TONGTINEN(mod);
            hvDAO.update(model);
            System.out.println(model);
            MsgBox.alert(this, "Cập nhật thành công ở tên bảng HỌC VIÊN!");
            this.fillTable();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void delete() {
        String row = null;
        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa người học này?")) {
            try {
                String maHV = txtMaHocVien.getText();
                System.out.println("Mã học viên: " + maHV);
                HocVien hv = hvDAO.selectById(maHV);
                if (hv != null) {
                    row = hv.getUserID();
                }
                hdDAO.delete(maHV);
                MsgBox.alert(this, "Xóa thành công ở bảng HÓA ĐƠN!");
                System.out.println("Mã người dùng: " + row);
                hvDAO.delete(maHV);
                MsgBox.alert(this, "Xóa thành công ở bảng HỌC VIÊN!");
                ndDao.delete(row);
                MsgBox.alert(this, "Xóa thành công ở bảng NGƯỜI DÙNG!");
                fillTable();

//                this.getForm();        
//               HocVien dm = new HocVien();
//                this.setForm(dm);
//                this.row = -1;
//                this.updateStatus(); 
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
                MsgBox.alert(this, "Xóa thất bại");

            }
        }
    }

    public void New() {
        HocVien hv = new HocVien();
        this.setForm(hv);
        this.row = -1;
        this.updateStatus();
    }

    void updateStatus() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblHoiVien.getRowCount() - 1);
        // Trạng thái form
        // txtMaGT.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btnCapNhat.setEnabled(edit);

        // Trạng thái điều hướng
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }

    public void timKiem() {
        String tk = txtTimKiem.getText();
        DefaultTableModel model = (DefaultTableModel) tblHoiVien.getModel();
        model.setRowCount(0);
        try {
            //   int intSearch = Integer.parseInt(searchText);
            HocVien hv = hvDAO.selectById(tk);
            if (hv != null) {
                if (hv.getHocVienID().equalsIgnoreCase(tk)) {
                    Object[] row = new Object[]{
                        hv.getHocVienID(), hv.getHoTenHV(), hv.getNgaySinhHV(), hv.getSdtHV(), hv.getEmailHV(), hv.getGoiTapID()
                    };
                    model.addRow(row);
                }

                System.out.println(list);
                MsgBox.alert(this, "Tìm kiếm thành công");
                tabs.setSelectedIndex(1);
            } else {
                MsgBox.alert(this, "Không có người dùng này");
                fillTable();
            }
        } catch (Exception e) {
            System.out.println(e);
            MsgBox.alert(this, "Lỗi!");
        }
    }

    void first() {
        this.row = 0;
        this.edit();
    }

    void prev() {
        if (this.row > 0) {
            this.row--;
            this.edit();
        }
    }

    void next() {
        if (this.row < tblHoiVien.getRowCount() - 1) {
            this.row++;
            this.edit();
        }
    }

    void last() {
        this.row = tblHoiVien.getRowCount() - 1;
        this.edit();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnDanhSach = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtHoTen = new javax.swing.JTextField();
        txtMaHocVien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cboGoiTap = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoiVien = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnTimKiem.setBackground(new java.awt.Color(0, 204, 255));
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon-24.png"))); // NOI18N
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnDanhSach.setBackground(new java.awt.Color(0, 204, 255));
        btnDanhSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Clien list.png"))); // NOI18N
        btnDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDanhSach, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DANH SÁCH HỌC VIÊN");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtMaHocVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHocVienActionPerformed(evt);
            }
        });

        jLabel6.setText("Mã Học Viên:");

        jLabel7.setText("Họ Tên:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaHocVien, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(33, 33, 33)
                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtMaHocVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("Ngày Sinh:");

        jLabel3.setText("SDT");

        jLabel4.setText("Email");

        jLabel5.setText("Gói Tập");

        cboGoiTap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboGoiTap, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)))
                .addGap(12, 12, 12))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(21, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cboGoiTap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnFirst.setBackground(new java.awt.Color(204, 204, 204));
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/firt24.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setBackground(new java.awt.Color(204, 204, 204));
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/prev24.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(204, 204, 204));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/next24.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(204, 204, 204));
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/last24.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(11, 11, 11)
                        .addComponent(btnXoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhat)
                        .addGap(18, 18, 18)
                        .addComponent(btnMoi)
                        .addGap(98, 98, 98)
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem)
                        .addComponent(btnCapNhat)
                        .addComponent(btnMoi)
                        .addComponent(btnXoa))
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        tabs.addTab("Cập Nhật", jPanel3);

        tblHoiVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Học Viên ID", "Họ tên", "Ngày sinh", "SĐT", "Email", "Gói tập", "STT"
            }
        ));
        tblHoiVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHoiVienMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoiVien);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(84, 84, 84)))
        );

        tabs.addTab("Danh Sách", jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tabs))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(260, 260, 260)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        this.timKiem();

    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhSachActionPerformed
        tabs.setSelectedIndex(1);
        fillTable();    }//GEN-LAST:event_btnDanhSachActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        // TODO add your handling code here:
        tabs.setSelectedIndex(1);

    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (Validate()) {
            this.soLuongUser();
            this.delete();
            this.clearForm();
        }

    }//GEN-LAST:event_btnXoaActionPerformed
    public boolean Validate() {
        if (txtHoTen.getText().isEmpty() || txtNgaySinh.getText().isEmpty() || txtSDT.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập đầy đủ thông tin");
            return false;
        }
        return true;

    }

    ;
    private void txtMaHocVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHocVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHocVienActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (Validate()) {
            this.soLuongUser();
            this.soLuongHD();
            insert();
            clearForm();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblHoiVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoiVienMousePressed

        if (evt.getClickCount() == 2) {
            this.row = tblHoiVien.getSelectedRow();
            // if (isValidRowIndex(this.row)) {
            this.edit();
            btnThem.setEnabled(false);
            btnCapNhat.setEnabled(true);
            btnXoa.setEnabled(true);
//            } else {
//                // Handle the case where the index is invalid
//                System.out.println("Chỉ số dòng không hợp lệ: " + this.row);
//            }
        }

    }//GEN-LAST:event_tblHoiVienMousePressed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        this.clearForm();
        btnThem.setEnabled(true);
        btnCapNhat.setEnabled(false);
        btnXoa.setEnabled(false);
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        if (Validate()) {
            this.soLuongUser();
            this.soLuongHD();
            CapNhat();

        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        this.first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        this.last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        this.next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        this.prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DSHocVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DSHocVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DSHocVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DSHocVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DSHocVienJDialog dialog = new DSHocVienJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnDanhSach;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboGoiTap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblHoiVien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaHocVien;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
