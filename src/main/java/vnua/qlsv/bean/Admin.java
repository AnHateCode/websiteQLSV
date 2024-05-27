package vnua.qlsv.bean;

public class Admin {

	private String ma_admin;
	private String email;
	private String ma_khoa;
	public String getMa_admin() {
		return ma_admin;
	}
	public void setMa_admin(String ma_admin) {
		this.ma_admin = ma_admin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMa_khoa() {
		return ma_khoa;
	}
	public void setMa_khoa(String ma_khoa) {
		this.ma_khoa = ma_khoa;
	}
	
	public Admin(String ma_admin, String email, String ma_khoa) {
		this.ma_admin = ma_admin;
		this.email = email;
		this.ma_khoa = ma_khoa;
	}
	
	
}
