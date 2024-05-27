package vnua.qlsv.bean;

import java.util.Date;

public class ChuNhiem {
	private Lop lop;
	private GiangVien giangVien;
	private Date namBatDau;
	private Date namKetThuc;
	private NienKhoa nienKhoa;
	
	
	
	
	public ChuNhiem() {
		super();
	}
	
	
	public ChuNhiem(Lop lop, GiangVien giangVien, Date namBatDau, Date namKetThuc, NienKhoa nienKhoa) {
		super();
		this.lop = lop;
		this.giangVien = giangVien;
		this.namBatDau = namBatDau;
		this.namKetThuc = namKetThuc;
		this.nienKhoa = nienKhoa;
	}

	public ChuNhiem(Lop lop, GiangVien giangVien, Date namBatDau, Date namKetThuc) {
		super();
		this.lop = lop;
		this.giangVien = giangVien;
		this.namBatDau = namBatDau;
		this.namKetThuc = namKetThuc;
	}
	
	
	public NienKhoa getNienKhoa() {
		return nienKhoa;
	}


	public void setNienKhoa(NienKhoa nienKhoa) {
		this.nienKhoa = nienKhoa;
	}


	public Lop getLop() {
		return lop;
	}
	public GiangVien getGiangVien() {
		return giangVien;
	}
	public Date getNamBatDau() {
		return namBatDau;
	}
	public Date getNamKetThuc() {
		return namKetThuc;
	}
	public void setLop(Lop lop) {
		this.lop = lop;
	}
	public void setGiangVien(GiangVien giangVien) {
		this.giangVien = giangVien;
	}
	public void setNamBatDau(Date namBatDau) {
		this.namBatDau = namBatDau;
	}
	public void setNamKetThuc(Date namKetThuc) {
		this.namKetThuc = namKetThuc;
	}
	
	
	
}
