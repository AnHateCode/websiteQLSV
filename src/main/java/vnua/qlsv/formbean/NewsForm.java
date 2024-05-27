package vnua.qlsv.formbean;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Part;

public class NewsForm {

	private String id;
	private String anhBia;
	private Date ngayTao;
	private String tieuDe;
	private String noiDung;
	private String loaiTin;
	private Part filePath;
					
	public NewsForm() {
		super();
	}
	public NewsForm(String id, String anhBia, String tieuDe, String noiDung, String loaiTin, Part filePath) {
		super();
		this.id = id;
		this.anhBia = anhBia;
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
		this.loaiTin = loaiTin;
		this.filePath = filePath;
	}
	public NewsForm(String id, String anhBia, Date ngayTao, String tieuDe, String noiDung, String loaiTin,
			Part filePath) {
		super();
		this.id = id;
		this.anhBia = anhBia;
		this.ngayTao = ngayTao;
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
		this.loaiTin = loaiTin;
		this.filePath = filePath;
	}
	
	
	public NewsForm(String tieuDe, String noiDung, String loaiTin, Part filePath) {
		super();
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
		this.loaiTin = loaiTin;
		this.filePath = filePath;
	}
	
	
	public NewsForm(String anhBia, String tieuDe, String noiDung, String loaiTin, Part filePath) {
		super();
		this.anhBia = anhBia;
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
		this.loaiTin = loaiTin;
		this.filePath = filePath;
	}
	public String getId() {
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
	public Part getFilePath() {
		return filePath;
	}
	public void setId(String id) {
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
	public void setFilePath(Part filePath) {
		this.filePath = filePath;
	}
	
	public List<String> validate(){
		List<String> errors = new ArrayList<String>();
		if(tieuDe == null || tieuDe.trim().isEmpty()) {
			errors.add("Tiêu đề không được để trống");
		}
		if(noiDung == null || noiDung.trim().isEmpty()) {
			errors.add("noiDung không được để trống");
		}
		
		if(loaiTin == null || loaiTin.trim().isEmpty()) {
			errors.add("loaiTin không được để trống");
		}
		if(filePath == null) {
			errors.add("Nen co anh");
		}
		return errors;
	}
	
	
}
