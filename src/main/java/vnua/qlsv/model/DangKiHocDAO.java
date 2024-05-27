package vnua.qlsv.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServlet;

import vnua.qlsv.bean.DangKiHoc;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.KyHoc;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.LopHoc;
import vnua.qlsv.bean.MonHoc;
import vnua.qlsv.utils.MyUtil;
public class DangKiHocDAO extends HttpServlet {
	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;

	public DangKiHocDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	

	public boolean isDangKi(String maLopHoc, String mSV) {
	    boolean isExist = false;
	    String sql = "SELECT COUNT(*) FROM dang_ki_hocs WHERE ma_sinh_vien = ? AND ma_lop_hoc = ?";
	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);
	    try {
	        preStatement = jdbcConnection.prepareStatement(sql);
	        preStatement.setString(1, maLopHoc);
	        preStatement.setString(2, mSV);
	        resultSet = preStatement.executeQuery();
	        if (resultSet.next()) {
	            int count = resultSet.getInt(1);
	            if (count > 0) {
	                isExist = true;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.closeResultSet(resultSet);
	        DBConnection.closePreparedStatement(preStatement);
	        DBConnection.closeConnect(jdbcConnection);
	    }
	    return isExist;
	}
	
	public boolean insertDangKiHoc(DangKiHoc dkh) {
		boolean insertResult = false;
		String sql = "INSERT INTO dang_ki_hocs(ma_dang_ki_hoc,ma_lop_hoc,ma_sinh_vien) VALUE (?,?,?)";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1,MyUtil.createOrderNo(10));
			preStatement.setString(2,dkh.getLopHoc().getMaLopHoc());
			preStatement.setString(3, dkh.getSinhVien().getMaSV() );
			
			insertResult = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return insertResult;
	}

	public List<DangKiHoc> getLopHocFromChuyenNganh(String id) {
		List<DangKiHoc> DKH =  new ArrayList<>();
		String sql = "SELECT lop_hocs.ma_lop_hoc, lop_hocs.ma_lop, lop_hocs.ma_ky_hoc,lop_hocs.ma_mon_hoc,lop_hocs.ma_giang_vien,lop_hocs.ngay_trong_tuan, lop_hocs.ngay_bat_dau, lop_hocs.ngay_ket_thuc, lop_hocs.so_tiet, lop_hocs.tiet_bat_dau, lop_hocs.phong_hoc, mon_hocs.ten_mon_hoc " +
				"FROM dang_ki_hocs " +
	            "INNER JOIN lop_hocs ON dang_ki_hocs.ma_lop_hoc = lop_hocs.ma_lop_hoc " +
	            "INNER JOIN mon_hocs ON lop_hocs.ma_mon_hoc = mon_hocs.ma_mon_hoc " +
	            "WHERE dang_ki_hocs.ma_sinh_vien = ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, id);
			resultSet = preStatement.executeQuery();
			while (resultSet.next()) {
				String maLopHoc = resultSet.getString("ma_lop_hoc");
			       Lop lop = new Lop();
			       lop.setMaLop( resultSet.getString("ma_lop"));
			       
			       KyHoc kyHoc = new KyHoc();
			       kyHoc.setMaKiHoc(resultSet.getString("ma_ky_hoc"));
			       
			       MonHoc monHoc = new MonHoc();
			       monHoc.setMaMonHoc(resultSet.getString("ma_mon_hoc"));
			       monHoc.setTenMonHoc(resultSet.getString("ten_mon_hoc"));
			       GiangVien giangVien = new GiangVien();
			       giangVien.setMaGV(resultSet.getString("ma_giang_vien"));
			       
			       Date ngayBatDau = resultSet.getDate("ngay_bat_dau");
			       Date ngayKetThuc = resultSet.getDate("ngay_ket_thuc");
			       String ngayTrongTuan = resultSet.getString("ngay_trong_tuan");
			       Integer soTiet = resultSet.getInt("so_tiet");
			       Time tietBatDau = resultSet.getTime("tiet_bat_dau");
			       String phongHoc = resultSet.getString("phong_hoc");
				

			
			       LopHoc lh= new LopHoc(maLopHoc, lop, kyHoc, monHoc, giangVien, ngayBatDau, ngayKetThuc, ngayTrongTuan, soTiet, tietBatDau, phongHoc);	
			       DangKiHoc dkh = new DangKiHoc();
			       dkh.setLopHoc(lh);
			       DKH.add(dkh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return DKH;
	}
}
