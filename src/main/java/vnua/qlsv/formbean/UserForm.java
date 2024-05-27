package vnua.qlsv.formbean;

import java.util.ArrayList;
import java.util.List;

public class UserForm {

	private String maDangNhap;
	private String tenDangNhap;
	private String matKhau;
	private int loaiNguoiDung ;
	
	
	public String getMaDangNhap() {
		return maDangNhap;
	}
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public int getLoaiNguoiDung() {
		return loaiNguoiDung;
	}
	public void setMaDangNhap(String maDangNhap) {
		this.maDangNhap = maDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public void setLoaiNguoiDung(int loaiNguoiDung) {
		this.loaiNguoiDung = loaiNguoiDung;
	}
	
	public UserForm(String maDangNhap, String tenDangNhap, String matKhau, int loaiNguoiDung) {
		super();
		this.maDangNhap = maDangNhap;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.loaiNguoiDung = loaiNguoiDung;
	}
	
	public List<String> validate() {
		List<String> errors = new ArrayList<String>();
		if (tenDangNhap == null || tenDangNhap.trim().isEmpty()) {
			errors.add("Nhập tên đăng nhập");
		}
		if (matKhau == null || matKhau.trim().isEmpty()) {
			errors.add("Nhap password");
		}
		return errors;
	}
}
