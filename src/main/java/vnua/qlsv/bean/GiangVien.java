package vnua.qlsv.bean;

public class GiangVien {
	private String maGV;
	private String hoTen;
	private String ngaySinh;
	private String diaChi;
	private String email;
	private String soDienThoai;
	private String avatar;
	
	private String hocVan;
	private String chuyenMon;
	private String maKhoa1;
	private int trangThai;
	
	
	
	public String getMaGV() {
		return maGV;
	}
	public String getHoTen() {
		return hoTen;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public String getEmail() {
		return email;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public String getAvatar() {
		return avatar;
	}
	
	public String getHocVan() {
		return hocVan;
	}
	public String getChuyenMon() {
		return chuyenMon;
	}
	public void setMaGV(String maGV) {
		this.maGV = maGV;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getMaKhoa1() {
		return maKhoa1;
	}
	public void setMaKhoa1(String maKhoa1) {
		this.maKhoa1 = maKhoa1;
	}
	public void setHocVan(String hocVan) {
		this.hocVan = hocVan;
	}
	public void setChuyenMon(String chuyenMon) {
		this.chuyenMon = chuyenMon;
	}
	
	
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	public GiangVien() {
		
	}
	public GiangVien(String maGV, String hoTen, String ngaySinh, String diaChi, String email, String soDienThoai,
			String avatar, String maKhoa1, String hocVan, String chuyenMon) {
		super();
		this.maGV = maGV;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.avatar = avatar;
		this.maKhoa1 = maKhoa1;
		this.hocVan = hocVan;
		this.chuyenMon = chuyenMon;
	}
	public GiangVien(String maGV, String hoTen, String ngaySinh, String diaChi, String email, String soDienThoai) {
		super();
		this.maGV = maGV;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.email = email;
		this.soDienThoai = soDienThoai;
	}


	
	
	
	
	
}
