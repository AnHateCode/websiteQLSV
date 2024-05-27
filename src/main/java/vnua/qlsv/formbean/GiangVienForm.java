package vnua.qlsv.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

public class GiangVienForm {

	private String maGV;
	private String hoTen;
	private String ngaySinh;
	private String diaChi;
	private String email;
	private String soDienThoai;
	private String avatar;
	private String hocVan;
	private String chuyenMon;
	private String maKhoa;
	private Part filePath;
	
	
	public GiangVienForm(String maGV, String hoTen, String ngaySinh, String diaChi, String email, String soDienThoai,
			String avatar, String hocVan, String chuyenMon, String maKhoa) {
		this.maGV = maGV;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.avatar = avatar;
		this.hocVan = hocVan;
		this.chuyenMon = chuyenMon;
		this.maKhoa = maKhoa;
	}


	public GiangVienForm(String maGV, String hoTen, String ngaySinh, String diaChi, String email, String soDienThoai) {
		super();
		this.maGV = maGV;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.email = email;
		this.soDienThoai = soDienThoai;
	}


	public GiangVienForm(String maGV, String hoTen, String ngaySinh, String diaChi, String email, String soDienThoai,
			String hocVan, String chuyenMon, String maKhoa, Part filePath) {
		super();
		this.maGV = maGV;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.hocVan = hocVan;
		this.chuyenMon = chuyenMon;
		this.maKhoa = maKhoa;
		this.filePath = filePath;
	}


	public String getMaGV() {
		return maGV;
	}


	public String getHoTen() {
		return hoTen;
	}


	public String getNgaySinh() {
		return ngaySinh;
	}


	public String getDiaChi() {
		return diaChi;
	}


	public String getEmail() {
		return email;
	}


	public String getSoDienThoai() {
		return soDienThoai;
	}


	public String getAvatar() {
		return avatar;
	}


	public String getHocVan() {
		return hocVan;
	}


	public String getChuyenMon() {
		return chuyenMon;
	}


	public String getmaKhoa() {
		return maKhoa;
	}


	public Part getFilePath() {
		return filePath;
	}


	public void setMaGV(String maGV) {
		this.maGV = maGV;
	}


	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}


	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}


	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public void setHocVan(String hocVan) {
		this.hocVan = hocVan;
	}


	public void setChuyenMon(String chuyenMon) {
		this.chuyenMon = chuyenMon;
	}


	public void setmaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}


	public void setFilePath(Part filePath) {
		this.filePath = filePath;
	}
	
	
	public List<String> validateEditBookForm(){
		List<String> errors = new ArrayList<String>();
		if(maGV == null || maGV.trim().isEmpty()) {
			errors.add("Mã giang viên viên không được để trống");
		}
		if(hoTen == null || hoTen.trim().isEmpty()) {
			errors.add("Họ Tên không được để trống");
		}
		
		if(ngaySinh == null || ngaySinh.trim().isEmpty()) {
			errors.add("Ngày sinh không được để trống");
		}else {
			try {
				Integer.parseInt(soDienThoai);
			} catch (NumberFormatException e) {
				errors.add("sđt không hợp lệ");
			}
		}
		
		if(hocVan == null || hocVan.trim().isEmpty()) {
			errors.add("Giới tính không được để trống");
		}
		if(diaChi == null || diaChi.trim().isEmpty()) {
			errors.add("Nhập địa chỉ");
		}
		if(email == null || email.trim().isEmpty()) {
			errors.add("email không được trống");
		}
		if(maKhoa == null) {
			errors.add("Ma khoa ko dc trong");
		}
		if(chuyenMon == null) {
			errors.add("Chuyên môn không được để trống");
		}
		return errors;
	}
	
	public List<String> validateCreateBookForm(){
		List<String> errors = new ArrayList<String>();
		if(maGV == null || maGV.trim().isEmpty()) {
			errors.add("Mã giang viên viên không được để trống");
		}
		if(hoTen == null || hoTen.trim().isEmpty()) {
			errors.add("Họ Tên không được để trống");
		}
		
		if(ngaySinh == null || ngaySinh.trim().isEmpty()) {
			errors.add("Ngày sinh không được để trống");
		}else {
			try {
				Integer.parseInt(soDienThoai);
			} catch (NumberFormatException e) {
				errors.add("sđt không hợp lệ");
			}
		}
		
		if(hocVan == null || hocVan.trim().isEmpty()) {
			errors.add("Giới tính không được để trống");
		}
		if(diaChi == null || diaChi.trim().isEmpty()) {
			errors.add("Nhập địa chỉ");
		}
		if(email == null || email.trim().isEmpty()) {
			errors.add("email không được trống");
		}
		if(maKhoa == null) {
			errors.add("Ma khoa ko dc trong");
		}
		if(chuyenMon == null) {
			errors.add("Chuyên môn không được để trống");
		}
		return errors;
		}
	
	
	
}
