package vnua.qlsv.formbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.NienKhoa;

public class ChuNhiemForm {

	private Lop lop;
	private GiangVien giangVien;
	private Date namBatDau;
	private Date namKetThuc;
	private NienKhoa nienKhoa;
	
	
	public ChuNhiemForm(Lop lop, GiangVien giangVien, Date namBatDau, Date namKetThuc, NienKhoa nienKhoa) {
		super();
		this.lop = lop;
		this.giangVien = giangVien;
		this.namBatDau = namBatDau;
		this.namKetThuc = namKetThuc;
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


	public NienKhoa getNienKhoa() {
		return nienKhoa;
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


	public void setNienKhoa(NienKhoa nienKhoa) {
		this.nienKhoa = nienKhoa;
	}
	
	public List<String> validateEditBookForm(NienKhoa nienkhoa){
		List<String> errors = new ArrayList<String>();
		
		int compareResult = namBatDau.compareTo(nienkhoa.getNamBatDau());
		
		if(compareResult<0) {
			errors.add("Năm bắt đầu phải lớn hơn thời gian bắt đầu của niên khóa");
		}
		int compareResult1 = namKetThuc.compareTo(nienkhoa.getNamKetThuc());
		if(compareResult1>0) {
			errors.add("Năm kết thúc phải nhỏ hơn thời gian kết thúc niên khóa ");
		}
		
		return errors;
	}
}
