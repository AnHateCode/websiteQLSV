package vnua.qlsv.bean;

import java.util.Date;

public class SinhVien {
	private String MaSV;
	private String HoTen;
	private String NgaySinh;
	private String GioiTinh;
	private String DiaChi;
	private String email;
	private String SoDienThoai;
	private String avatar;
	private String ma_khoa;
	private String ma_chuyen_nganh;
	private String ma_lop;
	private Integer trangThai;
	
	
	
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getMa_khoa() {
		return ma_khoa;
	}
	public void setMa_khoa(String ma_khoa) {
		this.ma_khoa = ma_khoa;
	}
	public String getMa_chuyen_nganh() {
		return ma_chuyen_nganh;
	}
	public void setMa_chuyen_nganh(String ma_chuyen_nganh) {
		this.ma_chuyen_nganh = ma_chuyen_nganh;
	}
	public String getMa_lop() {
		return ma_lop;
	}
	public void setMa_lop(String ma_lop) {
		this.ma_lop = ma_lop;
	}
	public String getMaSV() {
		return MaSV;
	}
	public void setMaSV(String maSV) {
		this.MaSV = maSV;
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		this.HoTen = hoTen;
	}
	public String getNgaySinh() {
		return NgaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.NgaySinh = ngaySinh;
	}
	public String getGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.GioiTinh = gioiTinh;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		this.DiaChi = diaChi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSoDienThoai() {
		return SoDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.SoDienThoai = soDienThoai;
	}
	public Integer getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(Integer trangThai) {
		this.trangThai = trangThai;
	}
	public SinhVien() {
		super();
	}
	public SinhVien(String maSV, String hoTen, String ngaySinh, String gioiTinh, String diaChi, String email) {
		super();
		this.MaSV = maSV;
		this.HoTen = hoTen;
		this.NgaySinh = ngaySinh;
		this.GioiTinh = gioiTinh;
		this.DiaChi = diaChi;
		this.email = email;
	}
	public SinhVien(String maSV, String hoTen, String ngaySinh, String gioiTinh, String diaChi, String email,
			String soDienThoai, String maBoMon) {
		this.MaSV = maSV;
		this.HoTen = hoTen;
		this.NgaySinh = ngaySinh;
		this.GioiTinh = gioiTinh;
		this.DiaChi = diaChi;
		this.email = email;
		this.SoDienThoai = soDienThoai;
	
	}
	public SinhVien(String hoTen, String ngaySinh, String gioiTinh, String diaChi, String email, String soDienThoai,
			String ma_hoa ) {
		super();
		this.HoTen = hoTen;
		this.NgaySinh = ngaySinh;
		this.GioiTinh = gioiTinh;
		this.DiaChi = diaChi;
		this.email = email;
		this.SoDienThoai = soDienThoai;
		this.ma_khoa = ma_khoa;
	}
	public SinhVien(String maSV, String hoTen, String ngaySinh, String gioiTinh, String diaChi, String email,
			String soDienThoai, String avatar, String ma_khoa, String ma_chuyen_nganh, String ma_lop) {
		super();
		this.MaSV = maSV;
		this.HoTen = hoTen;
		this.NgaySinh = ngaySinh;
		this.GioiTinh = gioiTinh;
		this.DiaChi = diaChi;
		this.email = email;
		this.SoDienThoai = soDienThoai;
		this.avatar = avatar;
		this.ma_khoa = ma_khoa;
		this.ma_chuyen_nganh = ma_chuyen_nganh;
		this.ma_lop = ma_lop;
	}
	
	public SinhVien(String maSV, String hoTen, String ngaySinh, String gioiTinh, String diaChi, String email,
			String soDienThoai, String ma_khoa, String ma_chuyen_nganh, String ma_lop) {
		super();
		MaSV = maSV;
		HoTen = hoTen;
		NgaySinh = ngaySinh;
		GioiTinh = gioiTinh;
		DiaChi = diaChi;
		this.email = email;
		SoDienThoai = soDienThoai;
		this.ma_khoa = ma_khoa;
		this.ma_chuyen_nganh = ma_chuyen_nganh;
		this.ma_lop = ma_lop;
	}
	
	
	
	
	
	
}
