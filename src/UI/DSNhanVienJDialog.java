/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI;

import com.qlpg.dao.NhanVienDAO;
import com.qlpg.dao.VaitroDAO;
import com.qlpg.dao.nguoiDungDAO;
import com.qlpg.entity.GoiTap;
import com.qlpg.entity.HocVien;
import com.qlpg.entity.NguoiDung;
import com.qlpg.entity.NhanVien;
import com.qlpg.entity.VaiTro;
import com.qlpg.utils.MsgBox;
import static com.qlpg.utils.MsgBox.confirm;
import com.qlpg.utils.XDate;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabSet;
/**
 *
 * @author titob
 */
public class DSNhanVienJDialog extends javax.swing.JDialog {

    /**
     * Creates new form DSHuanLuyenVienJDialog
     */
    NhanVienDAO nvDAO = new NhanVienDAO();
    nguoiDungDAO ndDAO = new nguoiDungDAO();
    int u;
    int i;
    int row = 0;
    VaitroDAO vtDAO = new VaitroDAO();
    public DSNhanVienJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        tabs.setSelectedIndex(0);
        txtNhanVienID.setEnabled(false);
        txtEmail.setEnabled(false);
        fillTable();
        fillCBO();
    }
    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        int dem=1;
        try {
            List<NhanVien> list = nvDAO.selectAll();
            for (NhanVien nv : list) {
                Object[] row = {
                    nv.getNhanVienID(),
                    nv.getHoTen(),
                    XDate.toString(nv.getNgaySinh(), "dd-MM-yyyy"),
                    nv.getEmail(),
                    nv.getSdt(),
                    nv.getUserID(),
                    nv.getVaitro(),
                    dem++
                };
                System.out.println(nv.getHoTen());
                System.out.println(nv.getSdt());
                System.out.println(nv.getUserID());
                System.out.println(nv.getVaitro());
                model.addRow(row);
            }
            System.out.println(list);
            i = list.size() + 1;
            System.out.println("So luong nhan vien hien co" + i);
            fillCBO();
        } catch (Exception e) {
            System.out.println(e);
            MsgBox.alert(this, "Lỗi truy cập");
        }
    }
    public void fillCBO() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboVaitro.getModel();
        model.removeAllElements();
        try {
            List<VaiTro> list = vtDAO.selectAll();
            for (VaiTro gt : list) {
                model.addElement(gt.getVaitro().toString());
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra chi tiết lỗi
        }
    }

    public void soLuongUser() {
        try {
            List<NguoiDung> listND = ndDAO.selectAll();
            for (NguoiDung nd : listND) {
                Object[] row = {
                    nd.getUserID()
                };
                // model.addRow(row);
            }
            u = listND.size() + 1;
            System.out.println("So Luong Nguoi Dung Hien Co " + u);
        } catch (Exception e) {
        }
    }
    public void Update() {
        NhanVien nv = getFormUD();
        try {
            nvDAO.update(nv);
            fillTable();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            System.out.println(e);
            MsgBox.alert(this, "Cập nhật thất bại!");
        }
    }
    public void setForm(NhanVien nv) {
        if (nv != null) {
            txtNhanVienID.setText(nv.getNhanVienID());
            txtHoTen.setText(nv.getHoTen());
            txtNgaySinh.setText(XDate.toString(nv.getNgaySinh(), "dd-MM-yyyy"));
            txtEmail.setText(nv.getEmail());
            txtSDT.setText(nv.getSdt());
            cboVaitro.setSelectedItem(nv.getVaitro());
        } else {
            txtNhanVienID.setText("");
            txtHoTen.setText("");
            txtNgaySinh.setText("");
            txtEmail.setText("");
            txtSDT.setText("");
            cboVaitro.setSelectedItem(null);
        }
    }
    public NhanVien getForm() {
        NhanVien nv = new NhanVien();
        nv.setNhanVienID(txtNhanVienID.getText());
        nv.setHoTen(txtHoTen.getText());
        nv.setEmail(txtEmail.getText());
        nv.setSdt(txtSDT.getText());
        String ngaySinhString = txtNgaySinh.getText();
        nv.setNgaySinh(XDate.toDate(ngaySinhString, "dd-MM-yyyy"));
        nv.setUserID("User" + String.valueOf(u));
        nv.setVaitro((String) cboVaitro.getSelectedItem());
        System.out.println((String)cboVaitro.getSelectedItem());
        System.out.println(nv);
        return nv;
    }
     public NhanVien getFormUD() {
        NhanVien nv = new NhanVien();
        nv.setNhanVienID(txtNhanVienID.getText());
        nv.setHoTen(txtHoTen.getText());
        nv.setEmail(txtEmail.getText());
        nv.setSdt(txtSDT.getText());
        String ngaySinhString = txtNgaySinh.getText();
        nv.setNgaySinh(XDate.toDate(ngaySinhString, "dd-MM-yyyy"));
     // nv.setUserID("User" + String.valueOf(u));
        nv.setVaitro((String) cboVaitro.getSelectedItem());
        System.out.println((String)cboVaitro.getSelectedItem());
        System.out.println(nv);
        return nv;
    }
    public NguoiDung LayForm() {
        NguoiDung nd = new NguoiDung();
        nd.setUserID("User" + String.valueOf(u));
        nd.setPassWord("123");
        nd.setVaiTro((String) cboVaitro.getSelectedItem());
        return nd;
    }
    public boolean validate(String maNV) {
        NhanVienDAO dao = new NhanVienDAO();
        try {
            NhanVien nv = dao.selectById(maNV);
            return nv != null; // Trả về true nếu mã nhân viên đã tồn tại, ngược lại trả về false
        } catch (Exception e) {
            // Xử lý exception nếu cần thiết
            e.printStackTrace();
            return false;
        }
    }
    private boolean validateSDT(String sdt) {
        // Sử dụng biểu thức chính quy để kiểm tra xem chuỗi chỉ chứa số hay không
        String regex = "^[0-9]+$";
        return Pattern.matches(regex, sdt);
    }

    //hàm tạo random UserID
    private String generateRandomUserID() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(900000) + 100000; // Sinh số ngẫu nhiên từ 100000 đến 999999
        return "User" + randomNumber;
    }
    // hàm tự động chuyển đổi chuỗi có dấu thành ko dấu
    private static String removeAccents(String input) {
        String regex = "\\p{InCombiningDiacriticalMarks}+";
        String temp = Normalizer.normalize(input, Normalizer.Form.NFD);
        return temp.replaceAll(regex, "");
    }
//    public void Insert() {
//        NhanVien model = getForm();
//        NguoiDung mode = LayForm();
//        if (model.getHoTen().isEmpty() || model.getEmail().isEmpty() || model.getSdt().isEmpty()) {
//            MsgBox.alert(this, "Vui lòng nhập đầy đủ thông tin!");
//            return;
//        }
//        if (validate(model.getNhanVienID())) {
//            MsgBox.alert(this, "Mã nhân viên đã tồn tại!");
//        } else {
//            try {
//                ndDAO.insert(mode);
//                System.out.println(mode);
//                MsgBox.alert(this, "Thêm mới thành công Người Dùng!");
//                nvDAO.insert(model);
//                fillTable();
//                MsgBox.alert(this, "Thêm mới thành công Nhân viên!");
//            } catch (Exception e) {
//                MsgBox.alert(this, "Thêm mới thất bại!");
//                e.printStackTrace(); // In ra chi tiết lỗi
//            }
//        }
//    }
    public void Insert() {
        NhanVien model = getForm();
        NguoiDung mode = LayForm();
        if (model.getHoTen().isEmpty()|| model.getSdt().isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        if (!validateSDT(model.getSdt())) {
            MsgBox.alert(this, "Số điện thoại phải là số!");
            return;
        }
        if (validate(model.getNhanVienID())) {
            MsgBox.alert(this, "Mã nhân viên đã tồn tại!");
        } else {
            try {
                // Tạo userID ngẫu nhiên
                String userID = generateRandomUserID();
                mode.setUserID(userID);
                // Tạo tự động email không dấu(họ tên NV+@gmail.com)
                String emailWithoutAccents = removeAccents(model.getHoTen().toLowerCase().replace(" ", "")) + "@gmail.com";
                model.setEmail(emailWithoutAccents);
                ndDAO.insert(mode);
                System.out.println(mode);
                MsgBox.alert(this, "Thêm mới thành công Người Dùng!");
                model.setUserID(userID); // Gán cùng một userID cho Nhân viên
                nvDAO.insert(model);
                
                fillTable();
                MsgBox.alert(this, "Thêm mới thành công Nhân viên!");
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm mới thất bại!");
                e.printStackTrace(); // In ra chi tiết lỗi
            }
        }
    }
//    public void delete() {
//        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa nhân viên này?")) {
//            String manv = txtNhanVienID.getText();
//            try {
//                nvDAO.delete(manv);
//                System.out.println(manv);
//                fillTable();
//                this.getForm();
//                MsgBox.alert(this, "Xóa thành công!");
//            } catch (Exception e) {
//                MsgBox.alert(this, "Xóa thất bại!");
//            }
//        }
//    }

    public void delete() {
        String row = null;
        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa nhân viên này")) {
            NhanVien model = getForm();
            NguoiDung mode = LayForm();
            try {
                String maHV = txtNhanVienID.getText();
                System.out.println("Ma hoc vien la " + maHV);
                NhanVien hv = nvDAO.selectById(maHV);
                if (hv != null) {
                    row = hv.getUserID();
                }
                System.out.println("ma Nguoi Dung la" + row);
                nvDAO.delete(maHV);
                MsgBox.alert(this, "Xóa thành công ở bản nhân viên!");
                ndDAO.delete(row);
                MsgBox.alert(this, "Xóa thành công ở bản người dùng!");
                fillTable();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
                MsgBox.alert(this, "Xóa thất bại");

            }
        }
    }
    public void clearForm() {
        NhanVien nv = new NhanVien();
        // String demo = "";
        // hv.setMaNhanVien;
        nv.setNgaySinh(new Date());
        setForm(nv);
        txtNhanVienID.setText("NV" + String.valueOf(i));
        txtNgaySinh.setText("");
        cboVaitro.setSelectedIndex(1);
    }
    public void edit() {
        if (this.row >= 0 && this.row < tblNhanVien.getRowCount()) {
            String maNV = (String) tblNhanVien.getValueAt(this.row, 0);
            NhanVien nv = nvDAO.selectById(maNV);
            this.setForm(nv);
            // this.updateStatus();
            tabs.setSelectedIndex(1);
        } else {
            // Xử lý khi chỉ số dòng không hợp lệ
            System.out.println("Chỉ số dòng không hợp lệ: " + this.row);
        }
    }

    public boolean isValidRowIndex(int rowIndex) {
        return rowIndex >= 0 && rowIndex < tblNhanVien.getRowCount();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNhanVienID = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        cboVaitro = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setBackground(new java.awt.Color(153, 204, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Thông Tin Nhân Viên");

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nhân Viên ID", "Họ tên", "Ngày Sinh", "Email", "SĐT", "UserID", "Vai trò", "STT"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNhanVienMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        btnTimKiem.setBackground(new java.awt.Color(0, 204, 255));
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon-24.png"))); // NOI18N
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(0, 204, 255));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Create.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Danh sách ", jPanel3);

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnMoi.setText("Làm mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Nhân Viên ID:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Họ và Tên:");

        txtNhanVienID.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNhanVienID, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHoTen)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2)
                    .addComponent(txtNhanVienID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Email:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Số điện thoại:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Ngày sinh:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Chức vụ:");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cboVaitro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboVaitro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEmail)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel8)
                        .addGap(19, 19, 19)
                        .addComponent(cboVaitro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboVaitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhat)
                        .addGap(18, 18, 18)
                        .addComponent(btnMoi)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnXoa)
                    .addComponent(btnCapNhat)
                    .addComponent(btnMoi))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        tabs.addTab("Nhân viên", jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(204, 204, 204))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked

    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
    }//GEN-LAST:event_btnTimKiemActionPerformed


    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        this.soLuongUser();
        Insert();
        System.out.println("so luong user hien co " + String.valueOf(u));
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblNhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMousePressed
        // TODO add your handling code here:
//        if (evt.getClickCount() == 2) {
//            this.row = tblNhanVien.rowAtPoint(evt.getPoint());
//            edit();
//        }
        if (evt.getClickCount() == 2) {
            int selectedRow = tblNhanVien.rowAtPoint(evt.getPoint());
            if (selectedRow >= 1 && selectedRow < tblNhanVien.getRowCount()) {
                this.row = selectedRow;
                edit();
            }
        }
    }//GEN-LAST:event_tblNhanVienMousePressed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
        clearForm();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:'
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        Update();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        clearForm();
        tabs.setSelectedIndex(1);
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

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
            java.util.logging.Logger.getLogger(DSNhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DSNhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DSNhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DSNhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DSNhanVienJDialog dialog = new DSNhanVienJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboVaitro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtNhanVienID;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
