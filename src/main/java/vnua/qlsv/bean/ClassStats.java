package vnua.qlsv.bean;

public class ClassStats {

	private String maLop;
    private String tenLop;
    private int nghiHocCount;
    private int baoLuuCount;
    private int duoiHocCount;
    
    public ClassStats(String maLop, String tenLop, int nghiHocCount, int baoLuuCount, int duoiHocCount) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.nghiHocCount = nghiHocCount;
        this.baoLuuCount = baoLuuCount;
        this.duoiHocCount = duoiHocCount;
    }

    
	public String getMaLop() {
		return maLop;
	}

	public String getTenLop() {
		return tenLop;
	}

	public int getNghiHocCount() {
		return nghiHocCount;
	}

	public int getBaoLuuCount() {
		return baoLuuCount;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public void setNghiHocCount(int nghiHocCount) {
		this.nghiHocCount = nghiHocCount;
	}

	public void setBaoLuuCount(int baoLuuCount) {
		this.baoLuuCount = baoLuuCount;
	}


	public int getDuoiHocCount() {
		return duoiHocCount;
	}


	public void setDuoiHocCount(int duoiHocCount) {
		this.duoiHocCount = duoiHocCount;
	}


	
	
    
    
}
