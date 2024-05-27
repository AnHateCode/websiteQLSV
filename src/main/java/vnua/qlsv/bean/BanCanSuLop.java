package vnua.qlsv.bean;

import java.util.Date;

public class BanCanSuLop {
	private Lop lop;
	private SinhVien sinhVien;
	private Date namBatDau;
	private Date namKetThuc;
	private NienKhoa nienKhoa;
	private String role;
	
	
	
	
	public BanCanSuLop() {
		super();
	}


	public BanCanSuLop(Lop lop, SinhVien sinhVien, Date namBatDau, Date namKetThuc, NienKhoa nienKhoa, String role) {
		super();
		this.lop = lop;
		this.sinhVien = sinhVien;
		this.namBatDau = namBatDau;
		this.namKetThuc = namKetThuc;
		this.nienKhoa = nienKhoa;
		this.role = role;
	}
	
	
	public Lop getLop() {
		return lop;
	}
	public SinhVien getSinhVien() {
		return sinhVien;
	}
	public Date getNamBatDau() {
		return namBatDau;
	}
	public Date getNamKetThuc() {
		return namKetThuc;
	}
	public NienKhoa getNienKhoa() {
		return nienKhoa;
	}
	public String getRole() {
		return role;
	}
	public void setLop(Lop lop) {
		this.lop = lop;
	}
	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}
	public void setNamBatDau(Date namBatDau) {
		this.namBatDau = namBatDau;
	}
	public void setNamKetThuc(Date namKetThuc) {
		this.namKetThuc = namKetThuc;
	}
	public void setNienKhoa(NienKhoa nienKhoa) {
		this.nienKhoa = nienKhoa;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
}
