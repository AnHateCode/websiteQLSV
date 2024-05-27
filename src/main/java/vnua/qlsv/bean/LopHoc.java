package vnua.qlsv.bean;

import java.sql.Time;
import java.util.Date;

public class LopHoc {

	private String maLopHoc;
	private Lop lop;
	private KyHoc kyHoc;
	private MonHoc monHoc;
	private GiangVien giangVien;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private String ngayTrongTuan;
	private Integer soTiet;
	private Time tietBatDau;
	private String phongHoc;
	
	
	
	
	public LopHoc() {
		super();
	}


	public LopHoc(String maLopHoc, Lop lop, KyHoc kyHoc, MonHoc monHoc, GiangVien giangVien, Date ngayBatDau,
			Date ngayKetThuc, String ngayTrongTuan, Integer soTiet, Time tietBatDau, String phongHoc) {
		super();
		this.maLopHoc = maLopHoc;
		this.lop = lop;
		this.kyHoc = kyHoc;
		this.monHoc = monHoc;
		this.giangVien = giangVien;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.ngayTrongTuan = ngayTrongTuan;
		this.soTiet = soTiet;
		this.tietBatDau = tietBatDau;
		this.phongHoc = phongHoc;
	}
	
	
	public String getMaLopHoc() {
		return maLopHoc;
	}
	public Lop getLop() {
		return lop;
	}
	public KyHoc getKyHoc() {
		return kyHoc;
	}
	public MonHoc getMonHoc() {
		return monHoc;
	}
	public GiangVien getGiangVien() {
		return giangVien;
	}
	public Date getNgayBatDau() {
		return ngayBatDau;
	}
	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}
	public String getNgayTrongTuan() {
		return ngayTrongTuan;
	}
	public Integer getSoTiet() {
		return soTiet;
	}
	public Time getTietBatDau() {
		return tietBatDau;
	}
	public String getPhongHoc() {
		return phongHoc;
	}
	public void setMaLopHoc(String maLopHoc) {
		this.maLopHoc = maLopHoc;
	}
	public void setLop(Lop lop) {
		this.lop = lop;
	}
	public void setKyHoc(KyHoc kyHoc) {
		this.kyHoc = kyHoc;
	}
	public void setMonHoc(MonHoc monHoc) {
		this.monHoc = monHoc;
	}
	public void setGiangVien(GiangVien giangVien) {
		this.giangVien = giangVien;
	}
	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public void setNgayTrongTuan(String ngayTrongTuan) {
		this.ngayTrongTuan = ngayTrongTuan;
	}
	public void setSoTiet(Integer soTiet) {
		this.soTiet = soTiet;
	}
	public void setTietBatDau(Time tietBatDau) {
		this.tietBatDau = tietBatDau;
	}
	public void setPhongHoc(String phongHoc) {
		this.phongHoc = phongHoc;
	}
	
	
}
