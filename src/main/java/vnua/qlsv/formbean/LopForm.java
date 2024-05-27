package vnua.qlsv.formbean;

import java.util.ArrayList;
import java.util.List;

import vnua.qlsv.bean.ChuyenNganh;
import vnua.qlsv.bean.Khoa;
import vnua.qlsv.bean.NienKhoa;

public class LopForm {

	private String maLop;
	private String tenLop;
	
	private ChuyenNganh chuyenNganh;
	private Khoa khoa;

	private NienKhoa nienKhoa;
	
	
	
	public String getMaLop() {
		return maLop;
	}
	public String getTenLop() {
		return tenLop;
	}
	public ChuyenNganh getChuyenNganh() {
		return chuyenNganh;
	}
	public Khoa getKhoa() {
		return khoa;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}
	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}
	public void setChuyenNganh(ChuyenNganh chuyenNganh) {
		this.chuyenNganh = chuyenNganh;
	}
	public void setKhoa(Khoa khoa) {
		this.khoa = khoa;
	}
	
	
	
	public NienKhoa getNienKhoa() {
		return nienKhoa;
	}
	public void setNienKhoa(NienKhoa nienKhoa) {
		this.nienKhoa = nienKhoa;
	}
	public LopForm(String maLop, String tenLop, ChuyenNganh chuyenNganh, Khoa khoa, NienKhoa nienKhoa) {
		super();
		this.maLop = maLop;
		this.tenLop = tenLop;
		this.chuyenNganh = chuyenNganh;
		this.khoa = khoa;
		this.nienKhoa = nienKhoa;
	}
	public LopForm() {
		super();
	}
	public List<String> validate() {
		List<String> errors = new ArrayList<String>();
		if (tenLop == null || tenLop.trim().isEmpty()) {
			errors.add("Nhập tên lớp");
		}
		if (maLop == null || maLop.trim().isEmpty()) {
			errors.add("Nhap ma lop");
		}
		return errors;
	}
	
}
