package vnua.qlsv.bean;

public class DangKiHoc {

	private  String maDangKiHoc;
	private SinhVien sinhVien;
	private LopHoc lopHoc;
	
	
	
	
	
	
	public DangKiHoc() {
		super();
	}



	public DangKiHoc(String maDangKiHoc, SinhVien sinhVien, LopHoc lopHoc) {
		this.maDangKiHoc = maDangKiHoc;
		this.sinhVien = sinhVien;
		this.lopHoc = lopHoc;
	}
	
	
	
	public String getMaDangKiHoc() {
		return maDangKiHoc;
	}
	public SinhVien getSinhVien() {
		return sinhVien;
	}
	public LopHoc getLopHoc() {
		return lopHoc;
	}
	public void setMaDangKiHoc(String maDangKiHoc) {
		this.maDangKiHoc = maDangKiHoc;
	}
	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}
	public void setLopHoc(LopHoc lopHoc) {
		this.lopHoc = lopHoc;
	}
	
	
}
