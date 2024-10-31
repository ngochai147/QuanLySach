use master
create database QL_NhaSachFahasa
go

use  QL_NhaSachFahasa

CREATE TABLE NhanVien(
	maNV NVARCHAR(10) NOT NULL PRIMARY KEY,
	hoTen NVARCHAR(30) NOT NULL,
	soDienThoai NVARCHAR(11) NOT NULL,
	diaChi NVARCHAR(255) NOT NULL,
	email NVARCHAR(30) NOT NULL,
	gioiTinh BIT NOT NULL,
	chucVu NVARCHAR(20) NOT NULL,
	ngaySinh date NOT NULL,
	hinhAnh NVARCHAR(255) NOT NULL
)
go
CREATE TABLE TaiKhoan(
	maTK NVARCHAR(10) NOT NULL PRIMARY KEY,
	tenDangNhap NVARCHAR(10) NOT NULL FOREIGN KEY REFERENCES NhanVien(maNV),
	matKhau NVARCHAR(50) NOT NULL
)
go
CREATE TABLE KhoHang(
	maKhoHang NVARCHAR(10) NOT NULL PRIMARY KEY,
	tenKho NVARCHAR(20) NOT NULL,
	sucChua INT NOT NULL,
	diaChi NVARCHAR(255) NOT NULL,
)
go
CREATE TABLE LoaiSach(
	maLoai NVARCHAR(10) NOT NULL PRIMARY KEY,
	tenLoai NVARCHAR(20) NOT NULL
)
go
CREATE TABLE Sach(
	ISBN NVARCHAR(17) NOT NULL PRIMARY KEY,
	tenSach NVARCHAR(255) NOT NULL,
	tacGia NVARCHAR(30) NOT NULL,
	namXB INT NOT NULL,
	nhaXB NVARCHAR(30) NOT NULL,
	soLuongTrongKho INT NOT NULL,
	giaGoc MONEY NOT NULL,
	maLoaiSach NVARCHAR(10) FOREIGN KEY REFERENCES LoaiSach(maLoai),
	hinhAnh NVARCHAR(255) NOT NULL
)
go
CREATE TABLE ChiTietKhoHang(
	maChiTietKhoHang NVARCHAR(10) NOT NULL PRIMARY KEY,
	ISBN NVARCHAR(17) NOT NULL FOREIGN KEY REFERENCES Sach(ISBN),
	maKhoHang NVARCHAR(10) NOT NULL FOREIGN KEY REFERENCES KhoHang(maKhoHang),
	soLuong INT NOT NULL,
	ngayNhapKho DATE NOT NULL
)
go
CREATE TABLE PhieuXuatKho(
	maPhieuXuatKho NVARCHAR(10) NOT NULL PRIMARY KEY,
	ngayLap date NOT NULL,
	maNV NVARCHAR(10) NOT NULL FOREIGN KEY REFERENCES NhanVien(maNV),
	maKhoHangXuat NVARCHAR(10) NOT NULL FOREIGN KEY REFERENCES KhoHang(maKhoHang),
	maKhoHangNhap NVARCHAR(10) NOT NULL FOREIGN KEY REFERENCES KhoHang(maKhoHang),
	soLuong INT NOT NULL
)
go
CREATE TABLE ChiTietPhieuXuatKho(
	maChiTietPhieuXuatKho NVARCHAR(10) NOT NULL PRIMARY KEY,
	maPhieuXuatKho NVARCHAR(10) NOT NULL FOREIGN KEY REFERENCES PhieuXuatKho(maPhieuXuatKho),
	soLuong INT,
	ISBN NVARCHAR(17) NOT NULL FOREIGN KEY REFERENCES Sach(ISBN)
)
go
CREATE TABLE HoaDon(
	maHoaDon NVARCHAR(10) NOT NULL PRIMARY KEY,
	ngayTaoDon date NOT NULL,
	maNV NVARCHAR(10) NOT NULL FOREIGN KEY REFERENCES NhanVien(maNV)
)
CREATE TABLE ChiTietHoaDon(
	maChiTietHoaDon NVARCHAR(10) NOT NULL PRIMARY KEY,
	maHoaDon NVARCHAR(10) NOT NULL FOREIGN KEY REFERENCES HoaDon(maHoaDon),
	ISBN NVARCHAR(17) NOT NULL FOREIGN KEY REFERENCES Sach(ISBN),
	soLuong INT NOT NULL,
	donGia MONEY NOT NULL
)
go
INSERT NhanVien (maNV, hoTen, soDienThoai, diaChi,email,gioiTinh,ngaySinh,chucVu,hinhAnh)
VALUES 
	('22687251', N'Nguyễn Huỳnh Ngọc Hải', '0982301311', N'Ung Văn Khiêm, quận Bình Thạnh, Thành phố Hồ Chí Minh', 'nguyenhuynhngochai@gmail.com',0, convert(datetime,'24-08-2004',105),N'Quản lý','../ServiceImage/NhanVien_IMG'),
	('22690761', N'Nguyễn Huỳnh Thế Bảo', '1234567891', N'Quận 10, Thành phố Hồ Chí Minh', 'tudien1@gmail.com',0, GETDATE(),N'Nhân Viên','../ServiceImage/NhanVien_IMG/Anh_TheBao.png'),
	('22685411', N'Lê Văn Hửu Thái', '1234567891', N'Quận Gò Vấp, Thành phố Hồ Chí Minh', 'tudien2@gmail.com',0, GETDATE(),N'Quản lý','../ServiceImage/NhanVien_IMG/Anh_HuuThai.png'),
	('22685461', N'Nguyễn Tấn Nghị', '1234567891', N'Quận 12, Thành phố Hồ Chí Minh', 'tudien3@gmail.com',0, GETDATE(),N'Nhân viên','../ServiceImage/NhanVien_IMG/Anh_TanNghi.png'),
	('22691361', N'Phạm Đắc Thịnh', '1234567891', N'Quận 12, Thành phố Hồ Chí Minh', 'tudien4@gmail.com',0, GETDATE(),N'Thủ kho','../ServiceImage/NhanVien_IMG/Anh_DacThinh.png')
go
INSERT TaiKhoan(maTK,tenDangNhap,matKhau) 
VALUES
			('TK001','22687251','123456'),
			('TK002','22685461','123456'),
			('TK003','22691361','123456'),		
			('TK004','22685411','123456'),
			('TK005','22690761','123456')
go
INSERT LoaiSach(maLoai,tenLoai)
VALUES
	('L0001',N'Tiểu thuyết'),
	('L0002',N'Lịch sử'),
	('L0003',N'Thiếu nhi'),
	('L0004',N'Văn hóa xã hội'),
	('L0005',N'Tâm lý, tình cảm')
go
INSERT KhoHang(maKhoHang,tenKho,sucChua,diaChi)
VALUES
	('KH0001',N'FAHASA Tân Định',500,N'Hai Bà Trưng, Phường 8, Quận 3, Hồ Chí Minh 700000'),
	('KH0002',N'FAHASA Nguyễn Huệ',500,N'40 Đ. Nguyễn Huệ, Bến Nghé, Quận 1, Hồ Chí Minh 700000'),
	('KH0003',N'FAHASA Phú Nhuận',750,N'201 Phan Đình Phùng, Phường 17, Phú Nhuận, Hồ Chí Minh 700000'),
	('KH0004',N'FAHASA Lạc Xuân',500,N'220 Đ.Nguyễn Văn Nghi, Phường 7, Gò Vấp, Hồ Chí Minh 700000'),
	('KH0005',N'FAHASA Gò Vấp',1500,N'366 Đ. Phan Văn Trị, Phường 5, Gò Vấp, Hồ Chí Minh 700000')
go
INSERT Sach(ISBN,tenSach,tacGia,namXB,nhaXB,soLuongTrongKho,giaGoc,maLoaiSach,hinhAnh)
VALUES
	('9786044916934',N'Trúng Số Độc Đắc',N'Vũ Trọng Phụng',2024,N'Văn Học',100,80000,'L0001','../ServiceImage/Sach_IMG/Sach_TrungSoDocDac.png'),
	('8935212358095',N'100++ Kỹ Năng Tự Bảo Vệ Bản Thân Cho Trẻ - Ứng Phó Với Nguy Hiểm',N'Tiểu Mạch Đông Thái',2022,N'NXB Hà Nội',50,70000,'L0003','../ServiceImage/Sach_IMG/Sach_100KyNangBaoVeBanThan.png'),
	('8935235242449',N'Kết Thúc Của Chúng Ta',N'Colleen Hoover',2024,N'Hội nhà văn',60,55000,'L0005','../ServiceImage/Sach_IMG/Sach_NhatKyCuaChungTa.png'),
	('8935095632053',N'Nhật Ký Trong Tù (Tái Bản)',N'Hồ Chí Minh',2021,N'NXB Văn Học',50,45000,'L0002','../ServiceImage/Sach_IMG/Sach_NhatKyTrongTu.png.jpg'),
	('9786044731483',N'Neo Chữ',N'Nguyễn Hoài Nam',2023,N'Hội Nhà Văn',50,140000,'L0004','../ServiceImage/Sach_IMG/Sach_NeoChu.png')
go
INSERT ChiTietKhoHang(maChiTietKhoHang,soLuong,ngayNhapKho,ISBN,maKhoHang)
VALUES
	('CTKH000001',100,GETDATE(),'9786044916934','KH0001'),
	('CTKH000002',50,GETDATE(),'8935212358095','KH0001'),
	('CTKH000003',60,GETDATE(),'8935235242449','KH0002'),
	('CTKH000004',50,GETDATE(),'8935095632053','KH0003'),
	('CTKH000005',50,GETDATE(),'9786044731483','KH0003')
go
INSERT PhieuXuatKho(maPhieuXuatKho,maKhoHangXuat,maKhoHangNhap,maNV,ngayLap,soLuong)
VALUES
	('PXK0001','KH0001','KH0002','22687251',GETDATE(),20),
	('PXK0002','KH0002','KH0003','22687251',GETDATE(),5),
	('PXK0003','KH0003','KH0003','22687251',GETDATE(),5)
go
INSERT ChiTietPhieuXuatKho(maChiTietPhieuXuatKho,maPhieuXuatKho,soLuong,ISBN)
VALUES
	('CTPXK00001','PXK0001',20,'8935212358095'),
	('CTPXK00002','PXK0002',5,'8935235242449'),
	('CTPXK00003','PXK0003',5,'8935095632053')
go
INSERT HoaDon(maHoaDon,maNV,ngayTaoDon)
VALUES
	('HD0001','22687251',GETDATE()),
	('HD0002','22687251',GETDATE()),
	('HD0003','22687251',GETDATE())
go
INSERT ChiTietHoaDon(maChiTietHoaDon,maHoaDon,soLuong,donGia,ISBN)
VALUES
	('CTHD00001','HD0001',5,55000,'8935235242449'),
	('CTHD00002','HD0002',1,55000,'8935235242449'),
	('CTHD00003','HD0003',1,45000,'8935095632053')