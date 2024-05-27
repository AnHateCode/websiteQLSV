package vnua.qlsv.bean;

public class ChuyenNganh {
	private String maChuyenNganh;
	private String tenChuyenNganh;
	
	
	
	public String getMaChuyenNganh() {
		return maChuyenNganh;
	}
	public String getTenChuyenNganh() {
		return tenChuyenNganh;
	}
	public void setMaChuyenNganh(String maChuyenNganh) {
		this.maChuyenNganh = maChuyenNganh;
	}
	public void setTenChuyenNganh(String tenChuyenNganh) {
		this.tenChuyenNganh = tenChuyenNganh;
	}
	
	
	public ChuyenNganh() {
		super();
	}
	public ChuyenNganh(String maChuyenNganh, String tenChuyenNganh) {
		super();
		this.maChuyenNganh = maChuyenNganh;
		this.tenChuyenNganh = tenChuyenNganh;
	}
	
	
	
	
}
