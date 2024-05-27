package vnua.qlsv.bean;

import java.util.Date;

public class News {
	private Integer id;
	private String anhBia;
	private Date ngayTao;
	private String tieuDe;
	private String noiDung;
	private String loaiTin;
	
	public News() {
		super();
	}
	public News(Integer id, String anhBia, Date ngayTao, String tieuDe, String noiDung, String loaiTin) {
		super();
		this.id = id;
		this.anhBia = anhBia;
		this.ngayTao = ngayTao;
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
		this.loaiTin = loaiTin;
	}
	
	
	public News(Integer id, String anhBia, String tieuDe, String noiDung, String loaiTin) {
		super();
		this.id = id;
		this.anhBia = anhBia;
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
		this.loaiTin = loaiTin;
	}
	public News(String anhBia, String tieuDe, String noiDung, String loaiTin) {
		super();

		this.anhBia = anhBia;
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
		this.loaiTin = loaiTin;
	}
	
	
	public News(String anhBia, Date ngayTao, String tieuDe, String noiDung, String loaiTin) {
		super();
		this.anhBia = anhBia;
		this.ngayTao = ngayTao;
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
		this.loaiTin = loaiTin;
	}
	
	public Integer getId() {
		return id;
	}
	public String getAnhBia() {
		return anhBia;
	}
	public Date getNgayTao() {
		return ngayTao;
	}
	public String getTieuDe() {
		return tieuDe;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public String getLoaiTin() {
		return loaiTin;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setAnhBia(String anhBia) {
		this.anhBia = anhBia;
	}
	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}
	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public void setLoaiTin(String loaiTin) {
		this.loaiTin = loaiTin;
	}	
}
