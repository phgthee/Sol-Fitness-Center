package UI;

import com.qlpg.utils.MsgBox;
import com.qlpg.utils.ToanCuc;
import com.qlpg.utils.XImage;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

public class frmGymerFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    ToanCuc tt = new ToanCuc();

    public frmGymerFrame() {
        initComponents();
        init();
    }

    public void OpenWelcome() {
        new ChaoJDialog(this, true).setVisible(true);
    }

    public void openHocVien() {
        if (lblVaiTro.getText().equalsIgnoreCase("Quản lý") || lblVaiTro.getText().equalsIgnoreCase("Nhân viên Sale")) {
            new DSHocVienJDialog(this, true).setVisible(true);
        } else {
            MsgBox.alert(this, "Bạn không có quyền truy cập mục này");
        }

    }

    public void openGoiTap() {
        if (lblVaiTro.getText().equalsIgnoreCase("Huấn luyện viên") || lblVaiTro.getText().equalsIgnoreCase("Quản lý")) {
            new GoiTapJDialog(this, true).setVisible(true);
        } else {
            MsgBox.alert(this, "Bạn không có quyền truy cập mục này");
        }
    }

    public void openHoaDon() {
        if (lblVaiTro.getText().equalsIgnoreCase("Nhân viên Sale") || lblVaiTro.getText().equalsIgnoreCase("Quản lý")) {
            new HoaDonJDialog(this, true).setVisible(true);
        } else {
            MsgBox.alert(this, "Bạn không có quyền truy cập mục này");
        }
    }

    public void openHuanLuyenVien() {
        if (lblVaiTro.getText().equalsIgnoreCase("Quản lý")) {
            new DSHuanLuyenVienJDialog(this, true).setVisible(true);
        } else {
            MsgBox.alert(this, "Bạn không có quyền truy cập mục này");
        }
    }

    public void openNhanVien() {
        if (lblVaiTro.getText().equalsIgnoreCase("Quản lý")) {
            new DSNhanVienJDialog(this, true).setVisible(true);
        } else {
            MsgBox.alert(this, "Bạn không có quyền truy cập mục này");
        }
    }

    public void openDangNhap() {
        new DangNhapJDialog(this, true).setVisible(true);
        lblNguoiDung.setText(tt.getMa());
        lblVaiTro.setText(tt.getVaitro());
    }
    public void openDoanhThu()
    {
         if (lblVaiTro.getText().equalsIgnoreCase("Quản lý")) {
            new DoanhThuJDialog(this, true).setVisible(true);
        } else {
            MsgBox.alert(this, "Bạn không có quyền truy cập mục này");
        }
    }
    public void openThietBi()
    {
         if (lblVaiTro.getText().equalsIgnoreCase("Quản lý")) {
            new QLThietBiJDialog(this, true).setVisible(true);
        } else {
            MsgBox.alert(this, "Bạn không có quyền truy cập mục này");
        }
    }
            public void openDangXuat() {
        if (tt.getMa().isEmpty()) {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        } else {
            tt.setMa("");
            tt.setTen("");
            tt.setVaitro("");
            lblNguoiDung.setText("Chưa đăng nhập");
            lblVaiTro.setText("Chưa đăng nhập");
            System.out.println("demo" + tt.getVaitro());
            MsgBox.alert(this, "Đã đăng xuất");
        }
    }

    public void openDoiMatKhau() {
        if (tt.getMa().isEmpty()) {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        } else {
            new DoiMatKhauJDialog(this, true).setVisible(true);

        }
    }

    private void init() {
       //setIconImage(XImage.getAppIcon());
        OpenWelcome();
        openDangNhap();
        System.out.println(tt.getMa());
        lblVaiTro.setText(tt.getVaitro());
        lblNguoiDung.setText(tt.getMa());
        System.out.println(tt.getMa());

        PNLOne();
        this.setLocationRelativeTo(null);
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                SimpleDateFormat formater = new SimpleDateFormat("hh:mm:s a");
                String text = formater.format(now);
                lblDongHo.setText(text);
            }
        }).start();
    }

    public void PNLOne() {
        pnlHT.setVisible(true);
        pnlGT.setVisible(false);
        pnlQL.setVisible(false);
        pnlTHTK.setVisible(false);
    }

    public boolean BooleanPnlQuanLy() {
        if (lblNguoiDung.getText().equals("Chưa đăng nhập") || lblVaiTro.getText().equals("Chưa đăng nhập")) {
            MsgBox.alert(this, "Vui lòng đăng nhập để sử dụng");
            return false;
        } else {
            pnlQL.setVisible(true);
            pnlGT.setVisible(false);
            pnlHT.setVisible(false);
            pnlTHTK.setVisible(false);
            pnlQuanLyGym.setBackground(Color.white);
            pnlGioiThieuGym.setBackground(new Color(0, 153, 0));
            pnlHeThongGym.setBackground(Color.red);
            pnlThongKeGym.setBackground(new Color(0, 153, 0));
//            pnlTroGiupGym.setBackground(new Color(0, 153, 0));
            return true;
        }
    }

    public boolean BooleanPnlThongKe() {
        if (lblNguoiDung.getText().equals("Chưa đăng nhập") || lblVaiTro.getText().equals("Chưa đăng nhập")) {
            MsgBox.alert(this, "Vui lòng đăng nhập để sử dụng");
            return false;
        } else {
            pnlTHTK.setVisible(true);
            pnlGT.setVisible(false);
            pnlHT.setVisible(false);
            pnlQL.setVisible(false);
            pnlThongKeGym.setBackground(Color.white);
            pnlGioiThieuGym.setBackground(new Color(0, 153, 0));
            pnlHeThongGym.setBackground(Color.red);
            pnlQuanLyGym.setBackground(new Color(0, 153, 0));
//            pnlTroGiupGym.setBackground(new Color(0, 153, 0));
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        lblNguoiDung = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnlHeThongGym = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        pnlGioiThieuGym = new javax.swing.JPanel();
        jLable5 = new javax.swing.JLabel();
        pnlQuanLyGym = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnlThongKeGym = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblVaiTro = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        pnlBanner = new javax.swing.JPanel();
        pnlGT = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtIntro1 = new javax.swing.JTextPane();
        lblText = new javax.swing.JLabel();
        pnlHT = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblChaoMung = new javax.swing.JLabel();
        btnDangNhap = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        btnDoiMatKhau = new javax.swing.JButton();
        lblCamOn = new javax.swing.JLabel();
        btnThoat = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        pnlQL = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        pnlNhanVien = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        pnlGoiTap = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        pnlPT = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        pnlLichSuTap = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        pnlHoiVien1 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        pnlHoaDon1 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pnlTHTK = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        pnlDoanhThu = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HỆ THỐNG QUẢN LÝ PHÒNG GYM");

        pnlMenu.setBackground(new java.awt.Color(204, 204, 255));

        lblNguoiDung.setBackground(new java.awt.Color(51, 255, 51));
        lblNguoiDung.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNguoiDung.setText("code");

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Pictures\\Sol_Logo.png")); // NOI18N

        pnlHeThongGym.setBackground(new java.awt.Color(255, 255, 153));
        pnlHeThongGym.setPreferredSize(new java.awt.Dimension(170, 50));
        pnlHeThongGym.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlHeThongMouseClicked(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 153));
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/GoiTap.png"))); // NOI18N
        jLabel9.setText("Trang chủ");
        jLabel9.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jLabel9ComponentHidden(evt);
            }
        });

        javax.swing.GroupLayout pnlHeThongGymLayout = new javax.swing.GroupLayout(pnlHeThongGym);
        pnlHeThongGym.setLayout(pnlHeThongGymLayout);
        pnlHeThongGymLayout.setHorizontalGroup(
            pnlHeThongGymLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeThongGymLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlHeThongGymLayout.setVerticalGroup(
            pnlHeThongGymLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlGioiThieuGym.setBackground(new java.awt.Color(255, 204, 153));
        pnlGioiThieuGym.setPreferredSize(new java.awt.Dimension(170, 50));
        pnlGioiThieuGym.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlGioiThieuMouseClicked(evt);
            }
        });

        jLable5.setBackground(new java.awt.Color(255, 0, 0));
        jLable5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLable5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/trg.png"))); // NOI18N
        jLable5.setText("Giới thiệu");

        javax.swing.GroupLayout pnlGioiThieuGymLayout = new javax.swing.GroupLayout(pnlGioiThieuGym);
        pnlGioiThieuGym.setLayout(pnlGioiThieuGymLayout);
        pnlGioiThieuGymLayout.setHorizontalGroup(
            pnlGioiThieuGymLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLable5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlGioiThieuGymLayout.setVerticalGroup(
            pnlGioiThieuGymLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLable5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlQuanLyGym.setBackground(new java.awt.Color(255, 204, 153));
        pnlQuanLyGym.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlQuanLyGymMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/baseline_donut_small_white_24dp.png"))); // NOI18N
        jLabel2.setText("Quản lý");
        jLabel2.setMaximumSize(new java.awt.Dimension(170, 50));
        jLabel2.setMinimumSize(new java.awt.Dimension(170, 50));
        jLabel2.setPreferredSize(new java.awt.Dimension(170, 50));
        jLabel2.setRequestFocusEnabled(false);

        javax.swing.GroupLayout pnlQuanLyGymLayout = new javax.swing.GroupLayout(pnlQuanLyGym);
        pnlQuanLyGym.setLayout(pnlQuanLyGymLayout);
        pnlQuanLyGymLayout.setHorizontalGroup(
            pnlQuanLyGymLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlQuanLyGymLayout.setVerticalGroup(
            pnlQuanLyGymLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuanLyGymLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlThongKeGym.setBackground(new java.awt.Color(255, 204, 153));
        pnlThongKeGym.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlThongKeGymMouseClicked(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(0, 153, 0));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/baseline_donut_small_white_24dp.png"))); // NOI18N
        jLabel4.setText("Thống kê");
        jLabel4.setMaximumSize(new java.awt.Dimension(170, 50));
        jLabel4.setMinimumSize(new java.awt.Dimension(170, 50));
        jLabel4.setPreferredSize(new java.awt.Dimension(170, 50));
        jLabel4.setRequestFocusEnabled(false);

        javax.swing.GroupLayout pnlThongKeGymLayout = new javax.swing.GroupLayout(pnlThongKeGym);
        pnlThongKeGym.setLayout(pnlThongKeGymLayout);
        pnlThongKeGymLayout.setHorizontalGroup(
            pnlThongKeGymLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlThongKeGymLayout.setVerticalGroup(
            pnlThongKeGymLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongKeGymLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblDongHo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDongHo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDongHo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/Alarm.png"))); // NOI18N
        lblDongHo.setText("jLabel15");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Người Dùng:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Vai Trò:");

        lblVaiTro.setBackground(new java.awt.Color(51, 255, 51));
        lblVaiTro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblVaiTro.setText("code");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Họ Tên:");

        lblHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHoTen.setText("code");

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlGioiThieuGym, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(pnlThongKeGym, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlQuanLyGym, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlHeThongGym, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addGroup(pnlMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(lblNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMenuLayout.createSequentialGroup()
                                .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlMenuLayout.createSequentialGroup()
                                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblHoTen)
                                    .addComponent(lblVaiTro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVaiTro)
                    .addComponent(jLabel10))
                .addGap(11, 11, 11)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblHoTen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNguoiDung)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(pnlHeThongGym, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlGioiThieuGym, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlQuanLyGym, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlThongKeGym, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(lblDongHo)
                .addContainerGap())
        );

        pnlBanner.setBackground(new java.awt.Color(255, 255, 255));

        pnlGT.setBackground(new java.awt.Color(255, 255, 255));

        txtIntro1.setEditable(false);
        txtIntro1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtIntro1.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        txtIntro1.setText("Trong thời đại công nghệ số phát triển mạnh mẽ, việc áp dụng công nghệ vào quản lý trở thành lợi thế cạnh tranh quyết định của doanh nghiệp. Phần mềm Quản Lý Sol Fitness Center ra đời nhằm hỗ trợ các trung tâm gym tối ưu hóa quy trình quản lý, gia tăng trải nghiệm khách hàng và tiết kiệm chi phí. Với những tính năng hiện đại và dễ sử dụng, phần mềm này không chỉ giúp quản lý thông tin học viên, nhân viên và thiết bị một cách hiệu quả mà còn cung cấp các báo cáo thống kê chi tiết, hỗ trợ các trung tâm trong việc ra quyết định kinh doanh thông minh hơn. Chúng tôi tin rằng, Sol Fitness Center sẽ là công cụ đắc lực giúp các phòng tập nâng cao hiệu suất quản lý và thúc đẩy hoạt động kinh doanh phát triển mạnh mẽ hơn.");
        txtIntro1.setCaretColor(new java.awt.Color(255, 255, 255));
        txtIntro1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIntro1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(txtIntro1);

        lblText.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/gioithieu.png"))); // NOI18N

        javax.swing.GroupLayout pnlGTLayout = new javax.swing.GroupLayout(pnlGT);
        pnlGT.setLayout(pnlGTLayout);
        pnlGTLayout.setHorizontalGroup(
            pnlGTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGTLayout.createSequentialGroup()
                .addGroup(pnlGTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblText, javax.swing.GroupLayout.PREFERRED_SIZE, 886, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlGTLayout.setVerticalGroup(
            pnlGTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGTLayout.createSequentialGroup()
                .addComponent(lblText, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pnlHT.setBackground(new java.awt.Color(255, 255, 255));
        pnlHT.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/icondn.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        pnlHT.add(jLabel1);
        jLabel1.setBounds(356, 101, 223, 220);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 51));
        jLabel3.setText("WELCOME TO SOL FITNESS CENTER");
        pnlHT.add(jLabel3);
        jLabel3.setBounds(300, 50, 450, 29);

        lblChaoMung.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblChaoMung.setForeground(new java.awt.Color(255, 255, 255));
        lblChaoMung.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChaoMung.setText("Hãy Tận Hưởng Và Trải Nghiệm");
        lblChaoMung.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlHT.add(lblChaoMung);
        lblChaoMung.setBounds(260, 330, 390, 29);

        btnDangNhap.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/dangnhap.png"))); // NOI18N
        btnDangNhap.setText("Đăng Nhập");
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        pnlHT.add(btnDangNhap);
        btnDangNhap.setBounds(40, 440, 190, 70);

        btnDangXuat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/dangxuat.png"))); // NOI18N
        btnDangXuat.setText("Đăng Xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        pnlHT.add(btnDangXuat);
        btnDangXuat.setBounds(460, 440, 200, 70);

        btnDoiMatKhau.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/doimatkhau.png"))); // NOI18N
        btnDoiMatKhau.setText("Đổi Mật Khẩu");
        btnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauActionPerformed(evt);
            }
        });
        pnlHT.add(btnDoiMatKhau);
        btnDoiMatKhau.setBounds(240, 440, 200, 70);

        lblCamOn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblCamOn.setForeground(new java.awt.Color(255, 255, 255));
        lblCamOn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCamOn.setText("Cảm ơn!");
        lblCamOn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlHT.add(lblCamOn);
        lblCamOn.setBounds(370, 370, 170, 29);

        btnThoat.setBackground(new java.awt.Color(0, 204, 255));
        btnThoat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/Stop.png"))); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        pnlHT.add(btnThoat);
        btnThoat.setBounds(670, 440, 190, 70);

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/hethonggym.jpg"))); // NOI18N
        pnlHT.add(jLabel23);
        jLabel23.setBounds(0, 0, 890, 560);

        pnlQL.setBackground(new java.awt.Color(204, 204, 204));
        pnlQL.setLayout(null);

        jLabel11.setBackground(new java.awt.Color(255, 204, 153));
        jLabel11.setFont(new java.awt.Font("Cambria Math", 1, 48)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 102, 102));
        jLabel11.setText("SOL FITNESS CENTER");
        pnlQL.add(jLabel11);
        jLabel11.setBounds(180, 50, 560, 57);

        pnlNhanVien.setBackground(new java.awt.Color(93, 192, 199));
        pnlNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        pnlNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNhanVienMouseClicked(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(204, 204, 255));
        jLabel7.setFont(new java.awt.Font("Cambria Math", 1, 36)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/Alarm.png"))); // NOI18N
        jLabel7.setText("NHÂN VIÊN");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlNhanVienLayout = new javax.swing.GroupLayout(pnlNhanVien);
        pnlNhanVien.setLayout(pnlNhanVienLayout);
        pnlNhanVienLayout.setHorizontalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        pnlNhanVienLayout.setVerticalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );

        pnlQL.add(pnlNhanVien);
        pnlNhanVien.setBounds(30, 130, 250, 190);

        pnlGoiTap.setBackground(new java.awt.Color(123, 209, 206));
        pnlGoiTap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlGoiTapMouseClicked(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(0, 204, 102));
        jLabel13.setFont(new java.awt.Font("Cambria Math", 1, 36)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/dn.png"))); // NOI18N
        jLabel13.setText("GÓI TẬP");
        jLabel13.setMaximumSize(new java.awt.Dimension(218, 96));
        jLabel13.setMinimumSize(new java.awt.Dimension(218, 96));
        jLabel13.setPreferredSize(new java.awt.Dimension(218, 96));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlGoiTapLayout = new javax.swing.GroupLayout(pnlGoiTap);
        pnlGoiTap.setLayout(pnlGoiTapLayout);
        pnlGoiTapLayout.setHorizontalGroup(
            pnlGoiTapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        pnlGoiTapLayout.setVerticalGroup(
            pnlGoiTapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlQL.add(pnlGoiTap);
        pnlGoiTap.setBounds(310, 130, 249, 190);

        pnlPT.setBackground(new java.awt.Color(222, 255, 246));
        pnlPT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlPTMouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Cambria Math", 1, 24)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/dn.png"))); // NOI18N
        jLabel16.setText("HUẤN LUYỆN VIÊN");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlPTLayout = new javax.swing.GroupLayout(pnlPT);
        pnlPT.setLayout(pnlPTLayout);
        pnlPTLayout.setHorizontalGroup(
            pnlPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
        );
        pnlPTLayout.setVerticalGroup(
            pnlPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
        );

        pnlQL.add(pnlPT);
        pnlPT.setBounds(30, 350, 253, 196);

        pnlLichSuTap.setBackground(new java.awt.Color(166, 237, 255));
        pnlLichSuTap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlLichSuTapMouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Cambria Math", 1, 36)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/dn.png"))); // NOI18N
        jLabel17.setText("THIẾT BỊ");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlLichSuTapLayout = new javax.swing.GroupLayout(pnlLichSuTap);
        pnlLichSuTap.setLayout(pnlLichSuTapLayout);
        pnlLichSuTapLayout.setHorizontalGroup(
            pnlLichSuTapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        pnlLichSuTapLayout.setVerticalGroup(
            pnlLichSuTapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlQL.add(pnlLichSuTap);
        pnlLichSuTap.setBounds(310, 350, 250, 196);

        pnlHoiVien1.setBackground(new java.awt.Color(150, 218, 211));
        pnlHoiVien1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlHoiVien1MouseClicked(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Cambria Math", 1, 36)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/dn.png"))); // NOI18N
        jLabel24.setText("HỌC VIÊN");
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlHoiVien1Layout = new javax.swing.GroupLayout(pnlHoiVien1);
        pnlHoiVien1.setLayout(pnlHoiVien1Layout);
        pnlHoiVien1Layout.setHorizontalGroup(
            pnlHoiVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        pnlHoiVien1Layout.setVerticalGroup(
            pnlHoiVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );

        pnlQL.add(pnlHoiVien1);
        pnlHoiVien1.setBounds(590, 130, 249, 190);

        pnlHoaDon1.setBackground(new java.awt.Color(135, 211, 248));
        pnlHoaDon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlHoaDon1MouseClicked(evt);
            }
        });

        jLabel25.setBackground(new java.awt.Color(0, 204, 204));
        jLabel25.setFont(new java.awt.Font("Cambria Math", 1, 36)); // NOI18N
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/dn.png"))); // NOI18N
        jLabel25.setText("HÓA ĐƠN");
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlHoaDon1Layout = new javax.swing.GroupLayout(pnlHoaDon1);
        pnlHoaDon1.setLayout(pnlHoaDon1Layout);
        pnlHoaDon1Layout.setHorizontalGroup(
            pnlHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        pnlHoaDon1Layout.setVerticalGroup(
            pnlHoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
        );

        pnlQL.add(pnlHoaDon1);
        pnlHoaDon1.setBounds(590, 350, 249, 196);

        jLabel8.setBackground(new java.awt.Color(204, 204, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/thtk.jpeg"))); // NOI18N
        pnlQL.add(jLabel8);
        jLabel8.setBounds(0, 0, 880, 560);

        pnlTHTK.setBackground(new java.awt.Color(204, 204, 255));
        pnlTHTK.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("THỐNG KÊ DOANH THU");
        pnlTHTK.add(jLabel12);
        jLabel12.setBounds(200, 120, 620, 90);

        pnlDoanhThu.setBackground(new java.awt.Color(255, 204, 204));
        pnlDoanhThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDoanhThuMouseClicked(evt);
            }
        });

        jLabel21.setBackground(new java.awt.Color(255, 204, 204));
        jLabel21.setFont(new java.awt.Font("Cambria Math", 1, 48)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PG/Icon/baseline_filter_alt_white_48dp.png"))); // NOI18N
        jLabel21.setText("Doanh Thu");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlDoanhThuLayout = new javax.swing.GroupLayout(pnlDoanhThu);
        pnlDoanhThu.setLayout(pnlDoanhThuLayout);
        pnlDoanhThuLayout.setHorizontalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        pnlDoanhThuLayout.setVerticalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTHTK.add(pnlDoanhThu);
        pnlDoanhThu.setBounds(210, 340, 450, 110);

        javax.swing.GroupLayout pnlBannerLayout = new javax.swing.GroupLayout(pnlBanner);
        pnlBanner.setLayout(pnlBannerLayout);
        pnlBannerLayout.setHorizontalGroup(
            pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 903, Short.MAX_VALUE)
            .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBannerLayout.createSequentialGroup()
                    .addComponent(pnlGT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(11, 11, 11)))
            .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlQL, javax.swing.GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE))
            .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBannerLayout.createSequentialGroup()
                    .addComponent(pnlTHTK, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBannerLayout.createSequentialGroup()
                    .addComponent(pnlHT, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlBannerLayout.setVerticalGroup(
            pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBannerLayout.createSequentialGroup()
                    .addComponent(pnlGT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBannerLayout.createSequentialGroup()
                    .addComponent(pnlQL, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBannerLayout.createSequentialGroup()
                    .addComponent(pnlTHTK, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBannerLayout.createSequentialGroup()
                    .addComponent(pnlHT, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlBanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBanner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlQuanLyGymMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlQuanLyGymMouseClicked
        BooleanPnlQuanLy();
    }//GEN-LAST:event_pnlQuanLyGymMouseClicked

    private void pnlThongKeGymMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlThongKeGymMouseClicked
        BooleanPnlThongKe();
    }//GEN-LAST:event_pnlThongKeGymMouseClicked

    private void pnlGioiThieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGioiThieuMouseClicked
        pnlGT.setVisible(true);
        pnlHT.setVisible(false);
        pnlQL.setVisible(false);
        pnlTHTK.setVisible(false);
        pnlGioiThieuGym.setBackground(Color.white);
        pnlHeThongGym.setBackground(Color.red);
        pnlQuanLyGym.setBackground(new Color(0, 153, 0));
        pnlThongKeGym.setBackground(new Color(0, 153, 0));
      //  pnlTroGiupGym.setBackground(new Color(0, 153, 0));
    }//GEN-LAST:event_pnlGioiThieuMouseClicked

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        openDangNhap();
    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        openDangXuat();    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
openDoiMatKhau();    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        if (MsgBox.confirm(this, "Bạn có chắn muốn thoát không")) {
            System.exit(0);
        };    }//GEN-LAST:event_btnThoatActionPerformed

    private void pnlHeThongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeThongMouseClicked
        pnlHT.setVisible(true);
        pnlGT.setVisible(false);
        pnlQL.setVisible(false);
        pnlTHTK.setVisible(false);
        pnlHeThongGym.setBackground(Color.white);
        pnlGioiThieuGym.setBackground(new Color(0, 153, 0));
        pnlQuanLyGym.setBackground(new Color(0, 153, 0));
        pnlThongKeGym.setBackground(new Color(0, 153, 0));
//        pnlTroGiupGym.setBackground(new Color(0, 153, 0));
    }//GEN-LAST:event_pnlHeThongMouseClicked

    private void pnlNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNhanVienMouseClicked
    }//GEN-LAST:event_pnlNhanVienMouseClicked

    private void pnlGoiTapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGoiTapMouseClicked
    }//GEN-LAST:event_pnlGoiTapMouseClicked

    private void pnlPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlPTMouseClicked
    }//GEN-LAST:event_pnlPTMouseClicked

    private void pnlHoiVien1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHoiVien1MouseClicked
    }//GEN-LAST:event_pnlHoiVien1MouseClicked

    private void pnlHoaDon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHoaDon1MouseClicked
    }//GEN-LAST:event_pnlHoaDon1MouseClicked

    private void pnlDoanhThuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDoanhThuMouseClicked
    }//GEN-LAST:event_pnlDoanhThuMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        openNhanVien();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        // TODO add your handling code here:
        openHocVien();
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        openGoiTap();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        openHuanLuyenVien();
    }//GEN-LAST:event_jLabel16MouseClicked

    private void txtIntro1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIntro1MouseClicked

    }//GEN-LAST:event_txtIntro1MouseClicked

    private void pnlLichSuTapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLichSuTapMouseClicked

    }//GEN-LAST:event_pnlLichSuTapMouseClicked

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        openHoaDon();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel25MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        openDoanhThu();
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        openThietBi();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel9ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jLabel9ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9ComponentHidden
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
            java.util.logging.Logger.getLogger(frmGymerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmGymerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmGymerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmGymerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmGymerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLable5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCamOn;
    private javax.swing.JLabel lblChaoMung;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblNguoiDung;
    private javax.swing.JLabel lblText;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JPanel pnlBanner;
    private javax.swing.JPanel pnlDoanhThu;
    private javax.swing.JPanel pnlGT;
    private javax.swing.JPanel pnlGioiThieuGym;
    private javax.swing.JPanel pnlGoiTap;
    private javax.swing.JPanel pnlHT;
    private javax.swing.JPanel pnlHeThongGym;
    private javax.swing.JPanel pnlHoaDon1;
    private javax.swing.JPanel pnlHoiVien1;
    private javax.swing.JPanel pnlLichSuTap;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlPT;
    private javax.swing.JPanel pnlQL;
    private javax.swing.JPanel pnlQuanLyGym;
    private javax.swing.JPanel pnlTHTK;
    private javax.swing.JPanel pnlThongKeGym;
    private javax.swing.JTextPane txtIntro1;
    // End of variables declaration//GEN-END:variables
}
