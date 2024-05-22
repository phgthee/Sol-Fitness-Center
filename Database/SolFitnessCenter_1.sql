create database qqq
drop database qqq
use  qqq
-- Tạo bảng Vai trò
CREATE TABLE Vaitro (
    Vaitro nvarchar(100) PRIMARY KEY,
	VaitroID nvarchar(10),
    --Thêm các trường khác nếu cần
);
-- Tạo bảng User
CREATE TABLE NguoiDung (
    UserID nvarchar(100) PRIMARY KEY,
    Password NVARCHAR(255),
	VaiTro nvarchar(100),
	FOREIGN KEY (Vaitro) REFERENCES Vaitro(Vaitro),
    --Thêm các trường khác nếu cần
);
-- Tạo bảng NhanVien
CREATE TABLE NhanVien (
    NhanVienID nvarchar(100) PRIMARY KEY,
    HoTen NVARCHAR(255),
    NgaySinh DATE,	
    Email NVARCHAR(255),
	Sdt Nvarchar(50),
	UserID nvarchar(100),
	VaiTro nvarchar(100),
	FOREIGN KEY (Vaitro) REFERENCES Vaitro(Vaitro),
	FOREIGN KEY (UserID) REFERENCES NguoiDung(UserID),
    --Thêm các trường khác nếu cần
);

-- Tạo bảng GoiTap
CREATE TABLE GoiTap (
    GoiTapID nvarchar(50) PRIMARY KEY,
    TenGoiTap NVARCHAR(255),
    Gia Float,
	ThoiLuong nvarchar(200) 
    --Thêm các trường khác nếu cần
);
-- Tạo bảng HocVien
CREATE TABLE HocVien (
    HocVienID nvarchar(100) PRIMARY KEY,
    HoTen NVARCHAR(255),
    NgaySinh DATE,
	Sdt nvarchar(50),
	Email Nvarchar(50),
	UserID nvarchar(100),
	GoiTapID nvarchar(50),
	FOREIGN KEY (UserID) REFERENCES NguoiDung(UserID),
	FOREIGN KEY (GoiTapID) REFERENCES GoiTap(GoiTapID),

    --Thêm các trường khác nếu cần
);

-- Tạo bảng HoaDon
CREATE TABLE HoaDon (
    HoaDonID nvarchar(50) PRIMARY KEY,
    NgayLap DATE,
    TongTien FLoat,
	GoiTapID nvarchar(50),
    NhanVienID nvarchar(100),	
	HocVienID nvarchar(100),
    FOREIGN KEY (GoiTapID) REFERENCES GoiTap(GoiTapID),
	FOREIGN KEY (HocVienID) REFERENCES HocVien(HocVienID),
	FOREIGN KEY (NhanVienID) REFERENCES NhanVien(NhanVienID)
    --Thêm các trường khác nếu cần
);

-- Tạo bảng ThietBi
CREATE TABLE ThietBi (
    ThietBiID nvarchar(50) PRIMARY KEY,
    TenThietBi NVARCHAR(255),
    SoLuong INT,
	NhaCungCap nvarchar(255),
	NgayNhapThietBi Date
   
-- Thêm dữ liệu vào bảng Vaitro
INSERT INTO Vaitro VALUES 
(N'Quản lý','QL01'),
(N'Nhân viên Sale','NV01'),
(N'Huấn luyện viên','HLV01'),
(N'Học viên','HV01');
-- Thêm dữ liệu vào bảng ThietBi
INSERT INTO ThietBi (ThietBiID, TenThietBi, SoLuong, NhaCungCap, NgayNhapThietBi) VALUES
('TB001', N'Xe đạp tập', 5, 'ABC Company', '2023-01-05'),
('TB002', N'Găng tay tập luyện', 20, 'XYZ Supplier', '2023-02-10'),
('TB003', N'Dumbbell 10kg', 10, 'Fit World', '2023-03-15'),
('TB004', N'Yoga mat', 15, 'Healthy Living', '2023-04-20'),
('TB005', N'Bình nước thể thao', 30, 'Hydrate Co.', '2023-05-25');
SELECT * FROM ThietBi;

-- Thêm dữ liệu vào bảng NguoiDung
INSERT INTO NguoiDung (UserID, Password, VaiTro) VALUES
('User001', 'password123', N'Quản lý'),
('User002', 'pass456', N'Nhân viên Sale'),
('User003', 'pass789', N'Huấn luyện viên'),
('User004', 'pass321', N'Quản lý'),
('User005', 'pass654', N'Nhân viên Sale'),
('User006', 'pass789', N'Học viên'),
('User007', 'pass321', N'Học viên'),
('User008', 'pass654', N'Học viên'),
('User009', 'pass987', N'Học viên'),
('User010', 'pass321', N'Học viên');
SELECT * FROM NguoiDung;

-- Thêm dữ liệu vào bảng NhanVien
INSERT INTO NhanVien  VALUES
('NV001', N'Nguyen Van A', '1990-01-01', 'nv_a@example.com', '123456789', 'User001',N'Quản lý'),
('NV002', N'Tran Thi B', '1995-05-15', 'nv_b@example.com', '987654321', 'User002',N'Nhân viên Sale'),
('NV003', N'Le Van C', '1988-10-10', 'nv_c@example.com', '555666777', 'User003',N'Huấn luyện viên'),
('NV004', N'Pham Van D', '1992-03-20', 'nv_d@example.com', '111222333', 'User004',N'Quản lý'),
('NV005', N'Hoang Thi E', '1998-12-05', 'nv_e@example.com', '999888777', 'User005',N'Nhân viên Sale');
SELECT * FROM NhanVien;

INSERT INTO GoiTap (GoiTapID, TenGoiTap, Gia, ThoiLuong) VALUES
('GT001', N'Gói Silver', 500000, N'1 tháng'),
('GT002', N'Gói Gold', 800000, N'3 tháng'),
('GT003', N'Gói Platinum', 1200000, N'6 tháng'),
('GT004', N'Gói Diamond', 1500000, N'12 tháng'),
('GT005', N'Gói Basic', 300000, N'1 tháng');
SELECT * FROM GoiTap;

-- Thêm dữ liệu vào bảng HocVien
INSERT INTO HocVien (HocVienID, HoTen, NgaySinh, Sdt, Email, UserID, GoiTapID)
VALUES
('HV001', N'Nguyen Van H', '1991-08-15', '123456789', 'hv_h@example.com', 'User006', 'GT001'),
('HV002', N'Tran Thi K', '1996-04-22', '987654321', 'hv_k@example.com', 'User007', 'GT002'),
('HV003', N'Le Van L', '1989-12-18', '555666777', 'hv_l@example.com', 'User008', 'GT003'),
('HV004', N'Pham Van M', '1993-07-30', '111222333', 'hv_m@example.com', 'User009', 'GT004'),
('HV005', N'Hoang Thi N', '1999-02-10', '999888777', 'hv_n@example.com', 'User010', 'GT005');
SELECT * FROM HocVien;

-- Thêm dữ liệu vào bảng HoaDon
INSERT INTO HoaDon (HoaDonID, NgayLap, TongTien, GoiTapID, NhanVienID, HocVienID)
VALUES
('HD001', '2023-01-10', 500000, 'GT001', 'NV001', 'HV001'),
('HD002', '2023-02-15', 800000, 'GT002', 'NV002', 'HV002'),
('HD003', '2023-03-20', 1200000, 'GT003', 'NV003', 'HV003'),
('HD004', '2023-04-25', 1500000, 'GT004', 'NV004', 'HV004'),
('HD005', '2023-05-30', 300000, 'GT005', 'NV005', 'HV005');
SELECT * FROM HoaDon;

go
  create proc [dbo].[DoanhThuu]
as
begin
     select 
		TenGoiTap GoiTap,
		count(distinct nv.NhanVienID) soNV,
		count(distinct hv.HocVienID) soHV,
		sum(hd.TongTien) DoanhThu,
		count(distinct hd.HoaDonID) TongHD
	 from HoaDon hd
	 join NhanVien nv  on hd.NhanVienID=nv.NhanVienID
	 join HocVien hv on hd.HocVienID=hv.HocVienID
	 join GoiTap gt on hd.GoiTapID=gt.GoiTapID
	 group by TenGoiTap

end;
