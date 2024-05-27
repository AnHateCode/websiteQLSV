package vnua.qlsv.bean;

public class KyHoc {

	private String maKiHoc;
	private String namHoc;
	private Integer hocKy;
	
	public String getMaKiHoc() {
		return maKiHoc;
	}
	public String getNamHoc() {
		return namHoc;
	}
	public Integer getHocKy() {
		return hocKy;
	}
	public void setMaKiHoc(String maKiHoc) {
		this.maKiHoc = maKiHoc;
	}
	public void setNamHoc(String namHoc) {
		this.namHoc = namHoc;
	}
	public void setHocKy(Integer hocKy) {
		this.hocKy = hocKy;
	}
	public KyHoc() {
		super();
	}
	public KyHoc(String maKiHoc, String namHoc, Integer hocKy) {
		super();
		this.maKiHoc = maKiHoc;
		this.namHoc = namHoc;
		this.hocKy = hocKy;
	}
	
	
}
