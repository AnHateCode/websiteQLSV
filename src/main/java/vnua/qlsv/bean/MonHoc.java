package vnua.qlsv.bean;

public class MonHoc {
	private String maMonHoc;
	private String tenMonHoc;
	private ChuyenNganh chuyenNganh;
	
	
	public String getMaMonHoc() {
		return maMonHoc;
	}
	public String getTenMonHoc() {
		return tenMonHoc;
	}
	public ChuyenNganh getChuyenNganh() {
		return chuyenNganh;
	}
	public void setMaMonHoc(String maMonHoc) {
		this.maMonHoc = maMonHoc;
	}
	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}
	public void setChuyenNganh(ChuyenNganh chuyenNganh) {
		this.chuyenNganh = chuyenNganh;
	}
	public MonHoc() {
		super();
	}
	public MonHoc(String maMonHoc, String tenMonHoc, ChuyenNganh chuyenNganh) {
		super();
		this.maMonHoc = maMonHoc;
		this.tenMonHoc = tenMonHoc;
		this.chuyenNganh = chuyenNganh;
	}
	
	
	
}
