package vnua.qlsv.formbean;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.KyHoc;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.MonHoc;

public class LopHocForm {

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
	
	public LopHocForm(String maLopHoc, Lop lop, KyHoc kyHoc, MonHoc monHoc, GiangVien giangVien, Date ngayBatDau,
			Date ngayKetThuc, String ngayTrongTuan, Integer soTiet, Time tietBatDau, String phongHoc) {
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
	
	public List<String> validateCreateBookForm(){
		List<String> errors = new ArrayList<String>();
		if(maLopHoc == null || maLopHoc.trim().isEmpty()) {
			errors.add("Mã giang viên viên không được để trống");
		}
		if(lop.getMaLop() == null || lop.getMaLop().trim().isEmpty()) {
			errors.add("Chọn lớp");
		}
		
		if(kyHoc.getMaKiHoc() == null || kyHoc.getMaKiHoc().trim().isEmpty()) {
			errors.add("Chọn kì học");
		}
		
		if(monHoc.getMaMonHoc() == null || monHoc.getMaMonHoc().trim().isEmpty()) {
			errors.add("Chọn mã môn học");
		}
		if(giangVien.getMaGV() == null ||giangVien.getMaGV().trim().isEmpty()) {
			errors.add("Chọn giảng viên");
		}
		if(ngayBatDau == null ) {
			errors.add("Ngày bắt đầu không được trống");
		}
		if(ngayKetThuc == null) {
			errors.add("Ngày kết thúc ko dc trong");
		}
		if(ngayTrongTuan == null) {
			errors.add("Ngày trong tuần không được để trống");
		}
		if(soTiet == null) {
			errors.add("So tiết ko được để trống");
		}
		if(tietBatDau == null) {
			errors.add(" Tiết bắt đầu ko được để trống");
		}
		if(phongHoc == null) {
			errors.add("phòng học không được để trống");
		}
		return errors;
		}
	
	
}
