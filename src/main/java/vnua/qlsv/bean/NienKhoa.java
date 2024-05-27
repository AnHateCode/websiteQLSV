package vnua.qlsv.bean;

import java.util.Date;

public class NienKhoa {

	private String maNienKhoa;
	private Date namBatDau;
	private Date namKetThuc;
	
	
	
	
	
	public NienKhoa() {
		super();
	}


	public NienKhoa(String maNienKhoa, Date namBatDau, Date namKetThuc) {
		super();
		this.maNienKhoa = maNienKhoa;
		this.namBatDau = namBatDau;
		this.namKetThuc = namKetThuc;
	}
	
	
	public String getMaNienKhoa() {
		return maNienKhoa;
	}
	public Date getNamBatDau() {
		return namBatDau;
	}
	public Date getNamKetThuc() {
		return namKetThuc;
	}
	public void setMaNienKhoa(String maNienKhoa) {
		this.maNienKhoa = maNienKhoa;
	}
	public void setNamBatDau(Date namBatDau) {
		this.namBatDau = namBatDau;
	}
	public void setNamKetThuc(Date namKetThuc) {
		this.namKetThuc = namKetThuc;
	} 
	
	
	
}
