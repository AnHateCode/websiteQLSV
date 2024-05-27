package vnua.qlsv.bean;

import java.util.List;

public class Khoa {
	private String maKhoa;
	private String tenKhoa;
	private List<ChuyenNganh> chuyenNganhList;
	
	
	
	
	public String getMaKhoa() {
		return maKhoa;
	}
	public String getTenKhoa() {
		return tenKhoa;
	}
	public List<ChuyenNganh> getChuyenNganhList() {
		return chuyenNganhList;
	}
	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}
	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}
	public void setChuyenNganhList(List<ChuyenNganh> chuyenNganhList) {
		this.chuyenNganhList = chuyenNganhList;
	}
	
	
	
	
}
