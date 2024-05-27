package vnua.qlsv.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServlet;

import vnua.qlsv.bean.ChuNhiem;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.LopHoc;
import vnua.qlsv.bean.NienKhoa;
import vnua.qlsv.bean.SinhVien;


public class ChuNhiemDAO {
	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	public ChuNhiemDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public ChuNhiem getChuNhiem(String id) {
		 ChuNhiem chuNhiem = null;
		String sql = "SELECT * FROM chu_nhiem WHERE ma_lop = ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, id);
			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
			
				 Lop lop = new Lop();
			       lop.setMaLop( resultSet.getString("ma_lop"));	
			       GiangVien giangVien = new GiangVien();
			       
			       giangVien.setMaGV(resultSet.getString("ma_giang_vien"));
				// đóng gói các giá trị thuộc tính vào đối tượng Bean(Book)
			       Date ngayBatDau = resultSet.getDate("thoi_gian_bat_dau");
			       Date ngayKetThuc = resultSet.getDate("thoi_gian_ket_thuc");
			       
			       NienKhoa nienKhoa = new NienKhoa();
			       nienKhoa.setMaNienKhoa(resultSet.getString("ma_nien_khoa"));
			      chuNhiem = new ChuNhiem(lop, giangVien, ngayBatDau, ngayKetThuc,nienKhoa);
			      
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return chuNhiem ;
	}
	
	public ChuNhiem getChuNhiemGV(String id) {
		 ChuNhiem chuNhiem = null;
		String sql = "SELECT * FROM chu_nhiem WHERE ma_giang_vien = ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, id);
			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
			
				 Lop lop = new Lop();
			       lop.setMaLop( resultSet.getString("ma_lop"));	
			       GiangVien giangVien = new GiangVien();
			       
			       giangVien.setMaGV(resultSet.getString("ma_giang_vien"));
				// đóng gói các giá trị thuộc tính vào đối tượng Bean(Book)
			       Date ngayBatDau = resultSet.getDate("thoi_gian_bat_dau");
			       Date ngayKetThuc = resultSet.getDate("thoi_gian_ket_thuc");
			       
			       NienKhoa nienKhoa = new NienKhoa();
			       nienKhoa.setMaNienKhoa(resultSet.getString("ma_nien_khoa"));
			      chuNhiem = new ChuNhiem(lop, giangVien, ngayBatDau, ngayKetThuc,nienKhoa);
			      
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return chuNhiem ;
	}
	public List<ChuNhiem> getChuNhiemGVList(String id) {
		 ChuNhiem chuNhiem = null;
		 List<ChuNhiem> chuNhiemList = new ArrayList<ChuNhiem>();
		String sql = "SELECT * FROM chu_nhiem WHERE ma_giang_vien = ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, id);
			resultSet = preStatement.executeQuery();
			 while (resultSet.next()) {
			
				 Lop lop = new Lop();
			       lop.setMaLop( resultSet.getString("ma_lop"));	
			       GiangVien giangVien = new GiangVien();
			       
			       giangVien.setMaGV(resultSet.getString("ma_giang_vien"));
				// đóng gói các giá trị thuộc tính vào đối tượng Bean(Book)
			       Date ngayBatDau = resultSet.getDate("thoi_gian_bat_dau");
			       Date ngayKetThuc = resultSet.getDate("thoi_gian_ket_thuc");
			       
			       NienKhoa nienKhoa = new NienKhoa();
			       nienKhoa.setMaNienKhoa(resultSet.getString("ma_nien_khoa"));
			      chuNhiem = new ChuNhiem(lop, giangVien, ngayBatDau, ngayKetThuc,nienKhoa);
			      chuNhiemList.add(chuNhiem);
			      
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return chuNhiemList ;
	}
	
	public List<ChuNhiem> getChuNhiems(String id) {
	    List<ChuNhiem> litCN = new ArrayList<>(); // Khởi tạo danh sách
	    String sql = "SELECT * FROM chu_nhiem WHERE ma_lop = ?";
	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);
	    try {
	        preStatement = jdbcConnection.prepareStatement(sql);
	        preStatement.setString(1, id);
	        resultSet = preStatement.executeQuery();
	        while (resultSet.next()) {
	            Lop lop = new Lop();
	            lop.setMaLop(resultSet.getString("ma_lop"));
	            
	            GiangVien giangVien = new GiangVien();
	            giangVien.setMaGV(resultSet.getString("ma_giang_vien"));
	            
	            Date ngayBatDau = resultSet.getDate("thoi_gian_bat_dau");
	            Date ngayKetThuc = resultSet.getDate("thoi_gian_ket_thuc");
	            
	            NienKhoa nienKhoa = new NienKhoa();
	            nienKhoa.setMaNienKhoa(resultSet.getString("ma_nien_khoa"));
	            
	            ChuNhiem chuNhiem = new ChuNhiem(lop, giangVien, ngayBatDau, ngayKetThuc, nienKhoa);
	            litCN.add(chuNhiem); // Thêm vào danh sách
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.closeResultSet(resultSet);
	        DBConnection.closePreparedStatement(preStatement);
	        DBConnection.closeConnect(jdbcConnection);
	    }
	    return litCN;
	}

	
	public boolean insertChuNhiem(ChuNhiem cn) {
		boolean insertResult = false;
		String sql = "INSERT INTO chu_nhiem(ma_lop, ma_giang_vien, thoi_gian_bat_dau, thoi_gian_ket_thuc, ma_nien_khoa) VALUE (?,?,?,?,?)";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1,cn.getLop().getMaLop() );
			preStatement.setString(2,cn.getGiangVien().getMaGV() );
			preStatement.setDate(3, new java.sql.Date(cn.getNamBatDau().getTime()));
			preStatement.setDate(4, new java.sql.Date(cn.getNamKetThuc().getTime()));
			preStatement.setString(5, cn.getNienKhoa().getMaNienKhoa());
		
			insertResult = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return insertResult;
	}
	
	public boolean updateChuNhiem(ChuNhiem cn) {
	    boolean result = false;
	    String sql = "UPDATE chu_nhiem SET ma_giang_vien = ?,thoi_gian_bat_dau = ?, thoi_gian_ket_thuc = ?,ma_nien_khoa = ? "
	            + "WHERE ma_lop = ?";
	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);

	    try {
	    	preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1,cn.getGiangVien().getMaGV() );
			preStatement.setDate(2, new java.sql.Date(cn.getNamBatDau().getTime()));
			preStatement.setDate(3, new java.sql.Date(cn.getNamKetThuc().getTime()));
			preStatement.setString(4, cn.getNienKhoa().getMaNienKhoa());
			preStatement.setString(5,cn.getLop().getMaLop() );
	        result = preStatement.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.closePreparedStatement(preStatement);
	        DBConnection.closeConnect(jdbcConnection);
	    }

	    return result;
	}
	
	public boolean deleteGVCN(String gvId) {
		boolean result = false;
		// Cau lenh sql
		String sql = "DELETE FROM chu_nhiem WHERE ma_giang_vien = ?";

		// Tao ket noi
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);

		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, gvId);
			int check = preStatement.executeUpdate();
			if (check > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return result;
	}
}
