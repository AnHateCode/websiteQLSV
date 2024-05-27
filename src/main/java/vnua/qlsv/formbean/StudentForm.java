package vnua.qlsv.formbean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

public class StudentForm {
	private String maSV;
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
	private Part filePath;
	
	
	
	

	public StudentForm(String maSV, String hoTen, String ngaySinh, String gioiTinh, String diaChi, String email,
			String soDienThoai, String avatar, String ma_khoa, String ma_chuyen_nganh, String ma_lop) {
		super();
		this.maSV = maSV;
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



	public StudentForm(String maSV, String hoTen, String ngaySinh, String gioiTinh, String diaChi, String email,
			String soDienThoai) {
		super();
		this.maSV = maSV;
		HoTen = hoTen;
		NgaySinh = ngaySinh;
		GioiTinh = gioiTinh;
		DiaChi = diaChi;
		this.email = email;
		SoDienThoai = soDienThoai;
	}



	public StudentForm(String maSV, String hoTen, String ngaySinh, String gioiTinh, String diaChi, String email,
			String soDienThoai, String ma_khoa, String ma_chuyen_nganh, String ma_lop, Part filePath) {
		super();
		this.maSV = maSV;
		HoTen = hoTen;
		NgaySinh = ngaySinh;
		GioiTinh = gioiTinh;
		DiaChi = diaChi;
		this.email = email;
		SoDienThoai = soDienThoai;
		this.ma_khoa = ma_khoa;
		this.ma_chuyen_nganh = ma_chuyen_nganh;
		this.ma_lop = ma_lop;
		this.filePath = filePath;
	}



	public String getMaSV() {
		return maSV;
	}



	public void setMaSV(String maSV) {
		this.maSV = maSV;
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



	public List<String> validateEditBookForm(){
		List<String> errors = new ArrayList<String>();
		if(maSV == null || maSV.trim().isEmpty()) {
			errors.add("Mã sinh viên không được để trống");
		}
		if(HoTen == null || HoTen.trim().isEmpty()) {
			errors.add("Họ Tên không được để trống");
		}
		
		if(NgaySinh == null || NgaySinh.trim().isEmpty()) {
			errors.add("Ngày sinh không được để trống");
		}else {
			try {
				Integer.parseInt(SoDienThoai);
			} catch (NumberFormatException e) {
				errors.add("sđt không hợp lệ");
			}
		}
		
		if(GioiTinh == null || GioiTinh.trim().isEmpty()) {
			errors.add("Giới tính không được để trống");
		}
		if(DiaChi == null || DiaChi.trim().isEmpty()) {
			errors.add("Nhập địa chỉ");
		}
		if(email == null || email.trim().isEmpty()) {
			errors.add("email không được trống");
		}
		if(ma_khoa == null) {
			errors.add("Ma khoa ko dc trong");
		}
		return errors;
	}
	
	public List<String> validateCreateBookForm(){
		List<String> errors = new ArrayList<String>();
		if(maSV == null || maSV.trim().isEmpty()) {
			errors.add("Mã sinh viên không được để trống");
		}
		if(HoTen == null || HoTen.trim().isEmpty()) {
			errors.add("Họ Tên không được để trống");
		}
		
		if(NgaySinh == null || NgaySinh.trim().isEmpty()) {
			errors.add("Ngày sinh không được để trống");
		}else {
			try {
				Integer.parseInt(SoDienThoai);
			} catch (NumberFormatException e) {
				errors.add("sđt không hợp lệ");
			}
		}
		
		if(GioiTinh == null || GioiTinh.trim().isEmpty()) {
			errors.add("Giới tính không được để trống");
		}
		if(DiaChi == null || DiaChi.trim().isEmpty()) {
			errors.add("Nhập địa chỉ");
		}
		if(email == null || email.trim().isEmpty()) {
			errors.add("email không được trống");
		}
		if(ma_khoa == null) {
			errors.add("Không có mã bộ môn");
		}
		return errors;
		
	}
}
