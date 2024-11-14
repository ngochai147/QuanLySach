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
	hinhAnh NVARCHAR(255) NOT NULL,
	trangThai NVARCHAR(20) NOT NULL
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
	hinhAnh NVARCHAR(255) NOT NULL,
	trangThai NVARCHAR(20) NOT NULL
)
go
CREATE TABLE ChiTietKhoHang(
	maChiTietKhoHang NVARCHAR(10) NOT NULL PRIMARY KEY,
	ISBN NVARCHAR(17) NOT NULL FOREIGN KEY REFERENCES Sach(ISBN),
	maKhoHang NVARCHAR(10) NOT NULL FOREIGN KEY REFERENCES KhoHang(maKhoHang),
	soLuong INT NOT NULL
)
go
CREATE TABLE PhieuNhapKho(
	maPhieuNhapKho NVARCHAR(10) NOT NULL PRIMARY KEY,
	ngayLap date NOT NULL,
	maNV NVARCHAR(10) NOT NULL FOREIGN KEY REFERENCES NhanVien(maNV),
	maKhoHangNhap NVARCHAR(10) NOT NULL FOREIGN KEY REFERENCES KhoHang(maKhoHang),
	soLuong INT NOT NULL
)
go
CREATE TABLE ChiTietPhieuNhapKho(
	maChiTietPhieuNhapKho NVARCHAR(10) NOT NULL PRIMARY KEY,
	maPhieuNhapKho NVARCHAR(10) NOT NULL FOREIGN KEY REFERENCES PhieuNhapKho(maPhieuNhapKho),
	soLuong INT,
	ISBN NVARCHAR(17) NOT NULL FOREIGN KEY REFERENCES Sach(ISBN)
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
	--ngayNhapKho date NOT NULL,
	ISBN NVARCHAR(17) NOT NULL FOREIGN KEY REFERENCES Sach(ISBN),
	soLuong INT NOT NULL,
	donGia MONEY NOT NULL
)
go
INSERT NhanVien (maNV, hoTen, soDienThoai, diaChi,email,gioiTinh,ngaySinh,chucVu,hinhAnh, trangThai)
VALUES
	('22687251', N'Nguyễn Huỳnh Ngọc Hải', '0982301311', N'quận Bình Thạnh, Thành phố Hồ Chí Minh', 'ngochai@gmail.com',0, convert(datetime,'24-08-2004',105),N'Quản lý','../ServiceImage/NhanVien_IMG', N'Đang làm'),
	('22690761', N'Nguyễn Huỳnh Thế Bảo', '1234567891', N'Quận 10, Thành phố Hồ Chí Minh', 'tudien1@gmail.com',0, convert(datetime,'29-03-2004',105),N'Nhân viên','../ServiceImage/NhanVien_IMG/Anh_TheBao.png', N'Đang làm'),
	('22685411', N'Lê Văn Hửu Thái', '1234567891', N'Quận Gò Vấp, Thành phố Hồ Chí Minh', 'tudien2@gmail.com',0, convert(datetime,'31-10-2004',105),N'Quản lý','../ServiceImage/NhanVien_IMG/Anh_HuuThai.png', N'Đang làm'),
	('22685461', N'Nguyễn Tấn Nghị', '1234567891', N'Quận 12, Thành phố Hồ Chí Minh', 'tudien3@gmail.com',0, convert(datetime,'18-08-1890',105),N'Nhân viên','../ServiceImage/NhanVien_IMG/Anh_TanNghi.png', N'Đang làm'),
	('22691361', N'Phạm Đắc Thịnh', '1234567891', N'Quận 12, Thành phố Hồ Chí Minh', 'tudien4@gmail.com',0, convert(datetime,'23-01-2004',105),N'Thủ kho','../ServiceImage/NhanVien_IMG/Anh_DacThinh.png', N'Đang làm'),

	('22691362', N'Nguyễn Hoài Nhân', '1234567892', N'Quận 12, Thành phố Hồ Chí Minh', 'tudien4@gmail.com',0,convert(datetime,'18-05-2000',105),N'Nhân viên','../ServiceImage/NhanVien_IMG/Anh_HoaiNhan.png', N'Đang làm'),
	('22691363', N'Phạm Thị Kim Yến', '1234567893', N'Quận 1, Thành phố Hồ Chí Minh', 'tudien14@gmail.com',1,convert(datetime,'18-05-2000',105),N'Nhân viên','../ServiceImage/NhanVien_IMG/Anh_KimYen.png', N'Đang làm'),
	('22691364', N'Lê Thị Yến Nhi', '1234567894', N'Quận 4, Thành phố Hồ Chí Minh', 'tudien24@gmail.com',1,convert(datetime,'18-05-2000',105),N'Nhân viên','../ServiceImage/NhanVien_IMG/Anh_YenNhi.png', N'Đang làm'),
	('22691365', N'Nguyễn Hoàng Sang', '1234567895', N'Quận 5, Thành phố Hồ Chí Minh', 'tudien34@gmail.com',0,convert(datetime,'18-05-2000',105),N'Nhân viên','../ServiceImage/NhanVien_IMG/Anh_HoangSang.png', N'Đang làm'),
	('22691366', N'Trần Ngọc Phát', '1234567896', N'Quận 6, Thành phố Hồ Chí Minh', 'tudien44@gmail.com',0, convert(datetime,'18-05-2000',105),N'Nhân viên','../ServiceImage/NhanVien_IMG/Anh_NgocPhat.png', N'Đang làm'),
	('22691367', N'Nguyễn Trường Duy', '1234567897', N'Quận 7, Thành phố Hồ Chí Minh', 'tudien54@gmail.com',0,convert(datetime,'18-05-2000',105),N'Nhân viên','../ServiceImage/NhanVien_IMG/Anh_TruongDuy.png', N'Đang làm')

go
INSERT TaiKhoan(maTK,tenDangNhap,matKhau)
VALUES
			('TK001','22687251','123456'),
			('TK002','22685461','123456'),
			('TK003','22691361','123456'),
			('TK004','22685411','123456'),
			('TK005','22690761','123456'),

			('TK006','22691362','123456'),
			('TK007','22691363','123456'),
			('TK008','22691364','123456'),
			('TK009','22691365','123456'),
			('TK010','22691366','123456'),
			('TK011','22691367','123456')

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
INSERT Sach(ISBN,tenSach,tacGia,namXB,nhaXB,soLuongTrongKho,giaGoc,maLoaiSach,hinhAnh, trangThai)
VALUES
	('9786044916934',N'Trúng Số Độc Đắc',N'Vũ Trọng Phụng',2024,N'Văn Học',100,80000,'L0001','../ServiceImage/Sach_IMG/Sach_TrungSoDocDac.png', N'Đang bán'),
	('8935212358095',N'100++ Kỹ Năng Tự Bảo Vệ Bản Thân Cho Trẻ',N'Tiểu Mạch Đông Thái',2022,N'NXB Hà Nội',50,70000,'L0003','../ServiceImage/Sach_IMG/Sach_100KyNangBaoVeBanThan.png', N'Đang bán'),
	('8935235242449',N'Kết Thúc Của Chúng Ta',N'Colleen Hoover',2024,N'Hội nhà văn',60,55000,'L0005','../ServiceImage/Sach_IMG/Sach_NhatKyCuaChungTa.png', N'Đang bán'),
	('8935095632053',N'Nhật Ký Trong Tù (Tái Bản)',N'Hồ Chí Minh',2021,N'NXB Văn Học',50,45000,'L0002','../ServiceImage/Sach_IMG/Sach_NhatKyTrongTu.png.jpg', N'Đang bán'),
	('9786044731483',N'Neo Chữ',N'Nguyễn Hoài Nam',2023,N'Hội Nhà Văn',50,140000,'L0004','../ServiceImage/Sach_IMG/Sach_NeoChu.png', N'Đang bán'),

	('9786044731484',N'Giải Mã Hành Vi Đọc Vị Cảm Xúc',N'Patrick King',2019,N'1980 Books',60,135000,'L0005','../ServiceImage/Sach_IMG/Sach_GiaiMaHanhViDocViCamXuc.png', N'Đang bán'),
	('9786044731485',N'Phân Tích Tâm Lý Hành Vi',N'Gillian Tett',2015,N'1980 Books',67,138000,'L0005','../ServiceImage/Sach_IMG/Sach_PhamTichHanhViCamXucConNguoi.png', N'Đang bán'),
	('9786044731486',N'Thao Túng Tâm Lý Trong Tình Yêu',N'Michelle Moore',2021,N'1980 Books',23,120000,'L0005','../ServiceImage/Sach_IMG/Sach_ThaoTungTamLyTrongTinhYeu.png', N'Đang bán'),
	('9786044731487',N'Tâm Lý Học Căn Bản',N'Roberts Feldman',2020,N'Giáo dục Việt Nam',100,155000,'L0005','../ServiceImage/Sach_IMG/Sach_TamLyHocCanBan.png', N'Đang bán'),
	('9786044731488',N'Tâm Lý Học Tính Cách',N'Trâu Hoành Minh',2021,N'MintBooks',88,115000,'L0005','../ServiceImage/Sach_IMG/Sach_TamLyHocTinhCach.png', N'Đang bán'),
	('9786044731489',N'Tâm Lý Tính Nữ',N'Trương Tử Cầm',2019,N'MintBooks',45,157000,'L0005','../ServiceImage/Sach_IMG/Sach_TamLyTinhNu.png', N'Đang bán'),
	('1786044731480',N'Đi Tìm Lẽ Sống',N'Viktor E. Frankl',2020,N'1980 Books',77,195000,'L0005','../ServiceImage/Sach_IMG/Sach_DiTimLeSong.png', N'Đang bán'),

	('9186044731480',N'Mùa Hè Thơ Ấu',N'Nguyễn Thị Giang',2022,N'1980 Books',136,105000,'L0003','../ServiceImage/Sach_IMG/Sach_MuaHeThoAu.png', N'Đang bán'),
	('9286044731480',N'Khu Rừng Kỳ Diệu',N'Evelyn Sasamoto',2023,N'Kim Đồng',145,95000,'L0003','../ServiceImage/Sach_IMG/Sach_KhuRungKyDieu.png', N'Đang bán'),
	('9386044731480',N'Những Ngôi Sao Trên Bầu Trời',N'Nguyễn Phan Quế Mai',2023,N'Kim Đồng',32,116000,'L0003','../ServiceImage/Sach_IMG/Sach_NhungNgoiSaoTrenBauTroi.png', N'Đang bán'),
	('9486044731480',N'30 Ngày Rèn Luyện Phẩm Chất',N'Đậu Thị Nhung',2022,N'Kim Đồng',142,88000,'L0003','../ServiceImage/Sach_IMG/Sach_30KyNangRenLuyenPhamChat.png', N'Đang bán'),

	('9586044731480',N'Đại Cương Văn Hóa Phương Đông',N'Nguyễn Văn Nam',2020,N'Kim Đồng',136,80000,'L0004','../ServiceImage/Sach_IMG/Sach_DaiCuongVanHoa.png', N'Đang bán'),
	('9686044731480',N'Khía Cạnh Văn Hóa - Xã Hội Của Kiến Trúc',N'Colleen Hoover',2021,N'Kim Đồng',142,88000,'L0004','../ServiceImage/Sach_IMG/Sach_KhiaCanhKienTruc.png', N'Đang bán'),
	('9786044731480',N'Văn Hóa Hà Giang',N'Patrick King',2023,N'Kim Đồng',112,89000,'L0004','../ServiceImage/Sach_IMG/Sach_VanHoaHaGiang.png', N'Đang bán'),
	('9886044731480',N'Hồ Xuân Hương',N'Nguyễn Thế Quang',2022,N'Kim Đồng',199,125000,'L0004','../ServiceImage/Sach_IMG/Sach_HoXuanHuong.png', N'Đang bán'),
	('9986044731480',N'Những Cuộc Phiêu Lưu Của Huckleberry Finn',N'Mark Twain',2022,N'Kim Đồng',200,200000,'L0004','../ServiceImage/Sach_IMG/Sach_NhungCuocPhieuLuu.png', N'Đang bán'),
	('4716044731480',N'Đàng Trong Thời Chúa Nguyễn',N'Đỗ Bang ',2019,N'Kim Đồng',212,219000,'L0004','../ServiceImage/Sach_IMG/Sach_ThoiChuaNguyen.png', N'Đang bán'),
	('9726044731480',N'Yếu Tố Văn Hóa, Xã Hội Trong Đời Sống Sinh Kế ',N'Bùi Thị Bích Lan',2018,N'Viện Hàn Lâm',182,95000,'L0004','../ServiceImage/Sach_IMG/Sach_YeuToVanHoa.png', N'Đang bán'),
	('9736044731480',N'Các Nền Văn Hóa Thế Giới',N'Đặng Hữu',2022,N'Khoa Học Xã Hội',142,188000,'L0004','../ServiceImage/Sach_IMG/Sach_CacNenVanHoaTheGioi.png', N'Đang bán'),

	('9746044731480',N'Những Người Đàn Bà',N'Etaf Rum',2022,N'Nhã Nam',143,198000,'L0001','../ServiceImage/Sach_IMG/Sach_NhungNguoiDanBa.png', N'Đang bán'),
	('9756044731480',N'Cứu Tinh Xứ Cát',N'Frank Herbert',2023,N'Kim Đồng',252,288000,'L0001','../ServiceImage/Sach_IMG/Sach_CuuTinhXuCat.png', N'Đang bán'),
	('9766044731480',N'The Lord of the Rings',N'J.R.R. Tolkien',2021,N'Khoa Học Xã Hội',192,188000,'L0001','../ServiceImage/Sach_IMG/Sach_Lord.png', N'Đang bán'),
	('9776044731480',N'Khải Huyền Muộn',N'Nguyễn Việt Hà',2020,N'Bản Trẻ',143,88000,'L0001','../ServiceImage/Sach_IMG/Sach_KhaiHuyenMuon.png', N'Đang bán'),
	('9788044731480',N'Số Đỏ',N'Vũ Trọng Phụng',2023,N'Văn Học',178,170000,'L0001','../ServiceImage/Sach_IMG/Sach_SoDo.png', N'Đang bán'),
	('9789044731480',N'Từ Bến Sông Nhung',N'Nguyễn Văn Nam',2021,N'Bản Việt',162,138000,'L0001','../ServiceImage/Sach_IMG/Sach_TuBenSongNhung.png', N'Đang bán'),
	('9781044731480',N'Hồn Ma Đêm Giáng Sinh',N'Charles Dickens',2023,N'Nhã Nam',142,188000,'L0001','../ServiceImage/Sach_IMG/Sach_HonMaDemGiangSinh.png', N'Đang bán'),
	('9712044731480',N'Chiến Tranh và Hòa Bình',N'Lev Tolstoy',2022,N'Văn Học',192,118000,'L0001','../ServiceImage/Sach_IMG/Sach_ChienTranhVaHoaBinh.png', N'Đang bán'),
	('9713044731480',N'Cây Cam Ngọt Của Tôi',N'Vasconcelos',2023,N'Khoa Học Xã Hội',144,128000,'L0001','../ServiceImage/Sach_IMG/Sach_CayCamNgotCuaToi.png', N'Đang bán'),
	('9714044731480',N'Harry Potter and the Deathly Hallows',N'J.K. Rowling',2020,N'Kim Đồng',252,220000,'L0001','../ServiceImage/Sach_IMG/Sach_HarryPotter.png', N'Đang bán'),
	('9715044731480',N'Ghi Chép Pháp Y',N'Đặng Hữu',2021,N'Khoa Học Xã Hội',173,180000,'L0001','../ServiceImage/Sach_IMG/Sach_GhiChepPhapY.png', N'Đang bán'),
	('9716044731480',N'Dữ Liệu Tử Thần ',N'Jeffery Deaver',2023,N'1980 Books',122,190000,'L0001','../ServiceImage/Sach_IMG/Sach_DuLieuTuThan.png', N'Đang bán'),
	('9717044731480',N'Sherlock Holmes',N'Arthur Conan',2022,N'Khoa Học Xã Hội',112,200000,'L0001','../ServiceImage/Sach_IMG/Sach_Conan.png', N'Đang bán'),
	('9718044731480',N'Trinh Thám Việt Nam',N'Mauro',2020,N'Nhã Nam',142,100000,'L0001','../ServiceImage/Sach_IMG/Sach_TrinhThamVietNam.png', N'Đang bán'),

	('9718044732480',N'Lịch Sử Chữ Quốc Ngữ 1620 - 1659','Đỗ Quang Chính',2020,N'Kim Đồng',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_LichSuChuQuocNgu.png', N'Đang bán'),
	('9718044733480',N'Điện Biên Phủ - Điểm Hẹn Lịch Sử','Trần Mạnh Thường',2020,N'Kim Đồng',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_LichSuDienBienPhu.png', N'Đang bán'),
	('9718044734480',N'Vua Gia Long và Người Pháp','Hà Hữu Đức',2020,N'Kim Đồng',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_VuaGiaLongVaNguoiPhap.png', N'Đang bán'),
	('9718044735480',N'Lịch Sử Thế Giới Qua 100 Hiện Vật','Neil MacGregor',2023,N'Kim Đồng',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_LichSuTheGioiQua100HienVat.png', N'Đang bán'),
	('9718044736480',N'Nexus',N'Yuval Noah Harari',2023,N'Kim Đồng',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_Nexus.png', N'Đang bán'),
	('9718044737480',N'Sự Trỗi Dậy và Suy Tàn Của Đế Chế Thứ Ba',N'William L. Shirer',2023,N'Kim Đồng',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_SuTroiDayVaSuyTanCuaTheGioiThuBa.png', N'Đang bán'),
	('9718044738480',N'Đại Việt Sử Ký Toàn Thư',N'Mauro',2023,N'Khoa Học Xã Hội',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_DaiVietSuKyToanThu.png', N'Đang bán'),
	('9718044739480',N'10 Trận Đánh Nổi Tiếng Trong Lịch Sử',N'Đặng Việt Thụy',2023,N'Khoa Học Xã Hội',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_10TranDanhNoiTiengTrongLichSuVietNam.png', N'Đang bán'),
	('9718044710480',N'Một Chuyến Du Hành Đến Xứ Nam Hà',N'John Barrow',2022,N'Khoa Học Xã Hội',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_DuHanhDenXuNamHa.png', N'Đang bán'),
	('9718044711480',N'Hồn Sử Việt',N'Minh Châu',2022,N'Khoa Học Xã Hội',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_HonSuViet.png', N'Đang bán'),
	('9718044712480',N'Lịch Sử Quân Sự Việt Namm',N'Viện Lịch Sử Quân Sự',2022,N'Khoa Học Xã Hội',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_LichSuQuanSuVietNam.png', N'Đang bán'),
	('9718044714480',N'Việt Nam Thế Kỷ X',N'Trần Trọng Dương',2022,N'Khoa Học Xã Hội',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_VietNamTheKyX.png', N'Đang bán'),
	('9718044713480',N'Những Bài Học Lịch Sử',N'Will Ariel Durant',2022,N'Khoa Học Xã Hội',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_NhungBaiHocLichSu.png', N'Đang bán'),
	('9718044715480',N'Lịch Sử Hà Nội',N'Philippe Papin',2022,N'Nhã Nam',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_LichSuHaNoi.png', N'Đang bán'),
	('9718044716480',N'Lịch Sử Thế Giới',N'Nguyễn Hiến Lê',2021,N'Nhã Nam',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_LichSuTheGioi.png', N'Đang bán'),
	('9718044717480',N'Những Danh Tướng Trong Lịch Sử Việt Nam',N'Nhóm Tri Thức Việt',2021,N'Nhã Nam',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_NhungDanhTuongTrongLichSu.png', N'Đang bán'),
	('9718044718480',N'Các Triều Đại Việt Nam',N'Đỗ Đức Hùng',2021,N'Nhã Nam',142,100000,'L0002','../ServiceImage/Sach_IMG/Sach_SachTrieuDaiVietNam.png', N'Đang bán')


go
INSERT ChiTietKhoHang(maChiTietKhoHang, soLuong, ISBN, maKhoHang)
VALUES
	('CTKH01',100,'9786044916934','KH0001'),
	('CTKH02',50,'8935212358095','KH0001'),
	('CTKH03',60,'8935235242449','KH0002'),
	('CTKH04',50,'8935095632053','KH0003'),
	('CTKH05',50,'9786044731483','KH0003'),
	('CTKH06', 60,'9786044731484', 'KH0001'),
    ('CTKH07', 45,'9786044731489', 'KH0001'),
    ('CTKH08', 67,'9786044731485', 'KH0002'),
    ('CTKH09', 77,'1786044731480', 'KH0002'),
    ('CTKH10', 23,'9786044731486', 'KH0003'),
    ('CTKH11', 32,'9786044731487', 'KH0003'),
    ('CTKH12', 88,'9786044731488', 'KH0004'),
    ('CTKH13', 100,'9786044731483', 'KH0004'),
	('CTKH14', 136, '9186044731480', 'KH0001'),
    ('CTKH15', 145, '9286044731480', 'KH0002'),
    ('CTKH16', 32, '9386044731480', 'KH0003'),
    ('CTKH17', 142, '9486044731480', 'KH0004'),
    ('CTKH18', 136, '9586044731480', 'KH0005'),
    ('CTKH19', 142, '9686044731480', 'KH0001'),
    ('CTKH20', 112, '9786044731480', 'KH0002'),
	('CTKH21', 199, '9886044731480', 'KH0004'),
    ('CTKH22', 200, '9986044731480', 'KH0004'),
    ('CTKH23', 212, '4716044731480', 'KH0004'),
    ('CTKH24', 182, '9726044731480', 'KH0004'),
    ('CTKH25', 142, '9736044731480', 'KH0004'),
    ('CTKH26', 143, '9746044731480', 'KH0001'),
    ('CTKH27', 252, '9756044731480', 'KH0005'),
	('CTKH28', 192, '9766044731480', 'KH0005'),
    ('CTKH29', 143, '9776044731480', 'KH0005'),
    ('CTKH30', 178, '9788044731480', 'KH0005'),
    ('CTKH31', 162, '9789044731480', 'KH0005'),
    ('CTKH32', 142, '9781044731480', 'KH0005'),
    ('CTKH33', 192, '9712044731480', 'KH0005'),
	('CTKH34', 144, '9713044731480', 'KH0002'),
    ('CTKH35', 252, '9714044731480', 'KH0002'),
    ('CTKH36', 173, '9715044731480', 'KH0002'),
    ('CTKH37', 122, '9716044731480', 'KH0002'),
    ('CTKH38', 112, '9717044731480', 'KH0002'),
    ('CTKH39', 142, '9718044731480', 'KH0002'),
	('CTKH40', 142, '9718044732480', 'KH0003'),
    ('CTKH41', 142, '9718044733480', 'KH0003'),
    ('CTKH42', 142, '9718044734480', 'KH0003'),
    ('CTKH43', 142, '9718044735480', 'KH0003'),
    ('CTKH44', 142, '9718044736480', 'KH0003'),
    ('CTKH45', 142, '9718044737480', 'KH0003'),
	('CTKH46', 142, '9718044738480', 'KH0004'),
    ('CTKH47', 142, '9718044739480', 'KH0004'),
    ('CTKH48', 142, '9718044710480', 'KH0004'),
    ('CTKH49', 142, '9718044711480', 'KH0004'),
    ('CTKH50', 142, '9718044712480', 'KH0004'),
    ('CTKH51', 142, '9718044714480', 'KH0004'),
	('CTKH52', 142, '9718044713480', 'KH0005'),
    ('CTKH53', 142, '9718044715480', 'KH0005'),
    ('CTKH54', 142, '9718044716480', 'KH0005'),
    ('CTKH55', 142, '9718044717480', 'KH0005'),
    ('CTKH56', 142, '9718044718480', 'KH0005')

go
INSERT PhieuNhapKho(maPhieuNhapKho,maKhoHangNhap,maNV,ngayLap,soLuong)
VALUES
	('PNK0001','KH0001','22687251',GETDATE(),150),
	('PNK0002','KH0002','22687251',GETDATE(),60),
	('PNK0003','KH0003','22687251',GETDATE(),100),
	('PNK0004', 'KH0001', '22687251', GETDATE(), 105),
    ('PNK0005', 'KH0002', '22687251', GETDATE(), 144),
    ('PNK0006', 'KH0003', '22687251', GETDATE(), 55),
    ('PNK0007', 'KH0004', '22687251', GETDATE(), 188),
    ('PNK0008', 'KH0005', '22687251', GETDATE(), 75),
	('PNK0009', 'KH0001', '22687251', GETDATE(), 278),
    ('PNK0010', 'KH0002', '22687251', GETDATE(), 257),
    ('PNK0011', 'KH0003', '22687251', GETDATE(), 32),
    ('PNK0012', 'KH0004', '22687251', GETDATE(), 142),
    ('PNK0013', 'KH0005', '22687251', GETDATE(), 136),
	('PNK0014', 'KH0004', '22687251', GETDATE(), 935),
    ('PNK0015', 'KH0001', '22687251', GETDATE(), 395),
	('PNK0016', 'KH0005', '22687251', GETDATE(), 1009),
	('PNK0017', 'KH0002', '22687251', GETDATE(), 1003),
	('PNK0018', 'KH0003', '22687251', GETDATE(), 852),
	('PNK0019', 'KH0004', '22687251', GETDATE(), 852),
	('PNK0020', 'KH0005', '22687251', GETDATE(), 710)
go
INSERT ChiTietPhieuNhapKho(maChiTietPhieuNhapKho,maPhieuNhapKho,soLuong,ISBN)
VALUES
	('CTPNK00001','PNK0001',100,'9786044916934'),
	('CTPNK00002','PNK0001',50,'8935212358095'),
	('CTPNK00003','PNK0002',60,'8935235242449'),
	('CTPNK00004','PNK0003',50,'8935095632053'),
	('CTPNK00005','PNK0003',50,'9786044731483'),
	('CTPNK00006', 'PNK0004', 60, '9786044731484'),
    ('CTPNK00007', 'PNK0004', 45, '9786044731489'),
    ('CTPNK00008', 'PNK0005', 67, '9786044731485'),
    ('CTPNK00009', 'PNK0005', 77, '1786044731480'),
    ('CTPNK00010', 'PNK0006', 23, '9786044731486'),
    ('CTPNK00011', 'PNK0006', 32, '9786044731487'),
    ('CTPNK00012', 'PNK0007', 88, '9786044731488'),
    ('CTPNK00013', 'PNK0007', 100, '9786044731483'),
	('CTPNK00014', 'PNK0008', 30, '9786044731483'),
	('CTPNK00015', 'PNK0008', 45, '9786044731483'),
	('CTPNK00016', 'PNK0009', 136, '9186044731480'),
    ('CTPNK00017', 'PNK0009', 142, '9686044731480'),
    ('CTPNK00018', 'PNK0010', 145, '9286044731480'),
    ('CTPNK00019', 'PNK0010', 112, '9786044731480'),
    ('CTPNK00020', 'PNK0011', 32, '9386044731480'),
    ('CTPNK00021', 'PNK0012', 142, '9486044731480'),
    ('CTPNK00022', 'PNK0013', 136, '9586044731480'),
	('CTPNK00023', 'PNK0014', 199, '9886044731480'),
    ('CTPNK00024', 'PNK0014', 200, '9986044731480'),
    ('CTPNK00025', 'PNK0014', 212, '4716044731480'),
    ('CTPNK00026', 'PNK0014', 182, '9726044731480'),
    ('CTPNK00027', 'PNK0014', 142, '9736044731480'),
    ('CTPNK00028', 'PNK0015', 143, '9746044731480'),
    ('CTPNK00029', 'PNK0015', 252, '9756044731480'),
	('CTPNK00030', 'PNK0016', 192, '9766044731480'),
    ('CTPNK00031', 'PNK0016', 143, '9776044731480'),
    ('CTPNK00032', 'PNK0016', 178, '9788044731480'),
    ('CTPNK00033', 'PNK0016', 162, '9789044731480'),
    ('CTPNK00034', 'PNK0016', 142, '9781044731480'),
    ('CTPNK00035', 'PNK0016', 192, '9712044731480'),
	('CTPNK00036', 'PNK0017', 144, '9713044731480'),
    ('CTPNK00037', 'PNK0017', 252, '9714044731480'),
    ('CTPNK00038', 'PNK0017', 173, '9715044731480'),
    ('CTPNK00039', 'PNK0017', 122, '9716044731480'),
    ('CTPNK00040', 'PNK0017', 112, '9717044731480'),
    ('CTPNK00041', 'PNK0017', 142, '9718044731480'),
	('CTPNK00042', 'PNK0018', 142, '9718044732480'),
    ('CTPNK00043', 'PNK0018', 142, '9718044733480'),
    ('CTPNK00044', 'PNK0018', 142, '9718044734480'),
    ('CTPNK00045', 'PNK0018', 142, '9718044735480'),
    ('CTPNK00046', 'PNK0018', 142, '9718044736480'),
    ('CTPNK00047', 'PNK0018', 142, '9718044737480'),
	('CTPNK00048', 'PNK0019', 142, '9718044738480'),
    ('CTPNK00049', 'PNK0019', 142, '9718044739480'),
    ('CTPNK00050', 'PNK0019', 142, '9718044710480'),
    ('CTPNK00051', 'PNK0019', 142, '9718044711480'),
    ('CTPNK00052', 'PNK0019', 142, '9718044712480'),
    ('CTPNK00053', 'PNK0019', 142, '9718044714480'),
	('CTPNK00054', 'PNK0020', 142, '9718044713480'),
    ('CTPNK00055', 'PNK0020', 142, '9718044715480'),
    ('CTPNK00056', 'PNK0020', 142, '9718044716480'),
    ('CTPNK00057', 'PNK0020', 142, '9718044717480'),
    ('CTPNK00058', 'PNK0020', 142, '9718044718480')

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
	('HD0003','22691364',GETDATE()),
	('HD0004', '22687251', DATEADD(day, -10, GETDATE())),
    ('HD0005', '22687251', DATEADD(day, -20, GETDATE())),
    ('HD0006', '22691367', DATEADD(day, -30, GETDATE())),
    ('HD0007', '22691367', DATEADD(day, -40, GETDATE())),
    ('HD0008', '22691364', DATEADD(day, -20, GETDATE())),
    ('HD0009', '22687251', DATEADD(day, -10, GETDATE())),
    ('HD0010', '22691364', DATEADD(day, -20, GETDATE())),
    ('HD0011', '22691367', DATEADD(day, -30, GETDATE())),
    ('HD0012', '22691367', DATEADD(day, -10, GETDATE())),
    ('HD0013', '22687251', DATEADD(day, -20, GETDATE())),
	('HD0014', '22687251', DATEADD(day, -15, GETDATE())),
    ('HD0015', '22691367', DATEADD(day, -25, GETDATE())),
    ('HD0016', '22687251', DATEADD(day, -35, GETDATE())),
    ('HD0017', '22691367', DATEADD(day, -45, GETDATE())),
    ('HD0018', '22687251', DATEADD(day, -35, GETDATE())),
    ('HD0019', '22691367', DATEADD(day, -12, GETDATE())),
    ('HD0020', '22691367', DATEADD(day, -25, GETDATE())),
    ('HD0021', '22691364', DATEADD(day, -35, GETDATE())),
    ('HD0022', '22687251', DATEADD(day, -15, GETDATE())),
    ('HD0023', '22691367', DATEADD(day, -25, GETDATE()))
go
INSERT ChiTietHoaDon(maChiTietHoaDon,maHoaDon,soLuong,donGia,ISBN)
VALUES
	('CTHD00001','HD0001',5,55000,'8935235242449'),
	('CTHD00002','HD0002',1,55000,'8935235242449'),
	('CTHD00003','HD0003',1,45000,'8935095632053'),
	('CTHD00004', 'HD0004', 3, 55000, '8935235242449'),
    ('CTHD00005', 'HD0005', 2, 45000, '8935095632053'),
    ('CTHD00006', 'HD0006', 1, 75000, '9786044731484'),
    ('CTHD00007', 'HD0007', 4, 135000, '9786044731485'),
    ('CTHD00008', 'HD0008', 5, 120000, '9786044731486'),
    ('CTHD00009', 'HD0009', 2, 155000, '9786044731487'),
    ('CTHD00010', 'HD0010', 6, 115000, '9786044731488'),
    ('CTHD00011', 'HD0011', 3, 157000, '9786044731489'),
    ('CTHD00012', 'HD0012', 4, 195000, '1786044731480'),
    ('CTHD00013', 'HD0013', 1, 105000, '9186044731480'),
    ('CTHD00014', 'HD0014', 2, 100000, '9718044738480'),
    ('CTHD00015', 'HD0014', 1, 100000, '9718044739480'),
    ('CTHD00016', 'HD0014', 3, 100000, '9718044710480'),
    ('CTHD00017', 'HD0015', 1, 100000, '9718044711480'),
    ('CTHD00018', 'HD0015', 4, 100000, '9718044712480'),
    ('CTHD00019', 'HD0015', 2, 100000, '9718044714480'),
    ('CTHD00020', 'HD0016', 5, 100000, '9718044713480'),
    ('CTHD00021', 'HD0016', 3, 100000, '9718044715480'),
    ('CTHD00022', 'HD0017', 2, 100000, '9718044716480'),
    ('CTHD00023', 'HD0017', 1, 100000, '9718044717480'),
    ('CTHD00024', 'HD0017', 4, 100000, '9718044718480'),
    ('CTHD00025', 'HD0018', 3, 100000, '9718044711480'),
    ('CTHD00026', 'HD0018', 2, 100000, '9718044738480'),
    ('CTHD00027', 'HD0019', 6, 100000, '9718044710480'),
    ('CTHD00028', 'HD0019', 2, 100000, '9718044712480'),
    ('CTHD00029', 'HD0020', 1, 100000, '9718044739480'),
    ('CTHD00030', 'HD0020', 3, 100000, '9718044715480'),
    ('CTHD00031', 'HD0021', 2, 100000, '9718044713480'),
    ('CTHD00032', 'HD0021', 1, 100000, '9718044716480'),
    ('CTHD00033', 'HD0022', 5, 100000, '9718044738480'),
    ('CTHD00034', 'HD0022', 2, 100000, '9718044717480'),
    ('CTHD00035', 'HD0023', 4, 100000, '9718044715480'),
    ('CTHD00036', 'HD0023', 3, 100000, '9718044718480');