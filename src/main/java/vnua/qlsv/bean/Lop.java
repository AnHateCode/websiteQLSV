package vnua.qlsv.bean;

public class Lop {
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
	
	
	public Lop(String maLop, String tenLop, ChuyenNganh chuyenNganh, Khoa khoa) {
		super();
		this.maLop = maLop;
		this.tenLop = tenLop;
		this.chuyenNganh = chuyenNganh;
		this.khoa = khoa;
		
	}
	
	public Lop(String maLop, String tenLop, ChuyenNganh chuyenNganh, Khoa khoa,
			NienKhoa nienKhoa) {
		super();
		this.maLop = maLop;
		this.tenLop = tenLop;
		this.chuyenNganh = chuyenNganh;
		this.khoa = khoa;
		this.nienKhoa = nienKhoa;
	}
	public Lop() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
