package vnua.qlsv.formbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.NienKhoa;

public class BanCanSuLopForm {

	private Lop lop;
	private SinhVien sinhVien;
	private Date namBatDau;
	private Date namKetThuc;
	private NienKhoa nienKhoa;
	private String role;
	
	
	
	
	public BanCanSuLopForm(Lop lop, SinhVien sinhVien, Date namBatDau, Date namKetThuc, NienKhoa nienKhoa,
			String role) {
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
