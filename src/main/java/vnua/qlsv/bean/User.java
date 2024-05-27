package vnua.qlsv.bean;

public class User {
	private String MaDangNhap;
	private String TenDangNhap;
	private String MatKhau;
	private int LoaiNguoiDung ;
	
	
	public String getMaDangNhap() {
		return MaDangNhap;
	}
	public void setMaDangNhap(String maDangNhap) {
		this.MaDangNhap = maDangNhap;
	}
	public String getTenDangNhap() {
		return TenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.TenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return MatKhau;
	}
	public void setMatKhau(String matKhau) {
		this.MatKhau = matKhau;
	}
	public int getLoaiNguoiDung() {
		return LoaiNguoiDung;
	}
	public void setLoaiNguoiDung(int loaiNguoiDung) {
		LoaiNguoiDung = loaiNguoiDung;
	}
	
	public User(String TenDangNhap,String MatKhau,int LoaiNguoiDung) {
		this.TenDangNhap = TenDangNhap;
		this.MatKhau = MatKhau;
		this.LoaiNguoiDung = LoaiNguoiDung;
	}
	
	public User(String maDangNhap, String tenDangNhap, String matKhau, int loaiNguoiDung) {
		super();
		this.MaDangNhap = maDangNhap;
		this.TenDangNhap = tenDangNhap;
		this.MatKhau = matKhau;
		this.LoaiNguoiDung = loaiNguoiDung;
	}
	
	
	
}
