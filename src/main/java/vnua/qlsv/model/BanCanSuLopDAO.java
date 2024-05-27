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

import vnua.qlsv.bean.BanCanSuLop;
import vnua.qlsv.bean.ChuNhiem;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.LopHoc;
import vnua.qlsv.bean.NienKhoa;
import vnua.qlsv.bean.SinhVien;


public class BanCanSuLopDAO {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	public BanCanSuLopDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	public List<BanCanSuLop> getBanCanSuLops(String id) {
	    List<BanCanSuLop> litCN = new ArrayList<>(); // Khởi tạo danh sách
	    String sql = "SELECT * FROM ban_can_su_lop WHERE ma_lop = ?";
	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);
	    try {
	        preStatement = jdbcConnection.prepareStatement(sql);
	        preStatement.setString(1, id);
	        resultSet = preStatement.executeQuery();
	        while (resultSet.next()) {
	            Lop lop = new Lop();
	            lop.setMaLop(resultSet.getString("ma_lop"));
	            
	            SinhVien sinhVien = new SinhVien();
	            sinhVien.setMaSV(resultSet.getString("ma_sinh_vien"));
	            
	            Date ngayBatDau = resultSet.getDate("thoi_gian_bat_dau");
	            Date ngayKetThuc = resultSet.getDate("thoi_gian_ket_thuc");
	            
	            NienKhoa nienKhoa = new NienKhoa();
	            nienKhoa.setMaNienKhoa(resultSet.getString("ma_nien_khoa"));
	            
	            String role = resultSet.getString("role");
	            
	            
	            BanCanSuLop bcsl = new BanCanSuLop(lop, sinhVien, ngayBatDau, ngayKetThuc, nienKhoa, role);
	            litCN.add(bcsl); // Thêm vào danh sách
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

	public BanCanSuLop getBCSLSV(String id) {
		BanCanSuLop banCanSuLop = null;
		String sql = "SELECT * FROM ban_can_su_lop WHERE ma_sinh_vien = ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, id);
			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
			
				 Lop lop = new Lop();
			       lop.setMaLop( resultSet.getString("ma_lop"));	
			       SinhVien sinhVien = new SinhVien();
			       
			       sinhVien.setMaSV(resultSet.getString("ma_sinh_vien"));
				// đóng gói các giá trị thuộc tính vào đối tượng Bean(Book)
			       Date ngayBatDau = resultSet.getDate("thoi_gian_bat_dau");
			       Date ngayKetThuc = resultSet.getDate("thoi_gian_ket_thuc");
			       
			       
			       NienKhoa nienKhoa = new NienKhoa();
			       nienKhoa.setMaNienKhoa(resultSet.getString("ma_nien_khoa"));
			       String role = resultSet.getString("role");
			      banCanSuLop = new BanCanSuLop(lop, sinhVien, ngayBatDau, ngayKetThuc, nienKhoa, role);
			      
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return banCanSuLop ;
	}
	public boolean insertBanCanSuLop(BanCanSuLop cn) {
		boolean insertResult = false;
		String sql = "INSERT INTO ban_can_su_lop(ma_lop, ma_sinh_vien, thoi_gian_bat_dau, thoi_gian_ket_thuc, ma_nien_khoa,role) VALUE (?,?,?,?,?,?)";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1,cn.getLop().getMaLop() );
			preStatement.setString(2,cn.getSinhVien().getMaSV() );
			preStatement.setDate(3, new java.sql.Date(cn.getNamBatDau().getTime()));
			preStatement.setDate(4, new java.sql.Date(cn.getNamKetThuc().getTime()));
			preStatement.setString(5, cn.getNienKhoa().getMaNienKhoa());
			preStatement.setString(6, cn.getRole());
			insertResult = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return insertResult;
	}
	
	public boolean updateBanCanSuLop(BanCanSuLop cn) {
	    boolean result = false;
	    String sql = "UPDATE ban_can_su_lop SET ma_sinh_vien = ?,thoi_gian_bat_dau = ?, thoi_gian_ket_thuc = ?,ma_nien_khoa = ?, role = ?"
	            + "WHERE ma_lop = ?";
	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);

	    try {
	    	preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1,cn.getSinhVien().getMaSV());
			preStatement.setDate(2, new java.sql.Date(cn.getNamBatDau().getTime()));
			preStatement.setDate(3, new java.sql.Date(cn.getNamKetThuc().getTime()));
			preStatement.setString(4, cn.getNienKhoa().getMaNienKhoa());
			preStatement.setString(5, cn.getRole());
			preStatement.setString(6,cn.getLop().getMaLop() );
	        result = preStatement.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.closePreparedStatement(preStatement);
	        DBConnection.closeConnect(jdbcConnection);
	    }

	    return result;
	}
	
	public boolean deleteCBL(String svId) {
		boolean result = false;
		// Cau lenh sql
		String sql = "DELETE FROM ban_can_su_lop WHERE ma_sinh_vien = ?";

		// Tao ket noi
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);

		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, svId);
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
	
	public boolean checkRoleExistence(String maLop, Date ngayBatDau, Date ngayKetThuc, String role) {
	    boolean roleExists = false;
	    String sql = "SELECT COUNT(*) AS count FROM ban_can_su_lop WHERE ma_lop = ? "
	               + "AND thoi_gian_bat_dau <= ? AND thoi_gian_ket_thuc >= ? AND role = ?";
	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);

	    try {
	        preStatement = jdbcConnection.prepareStatement(sql);
	        preStatement.setString(1, maLop);
	        preStatement.setDate(2, new java.sql.Date(ngayKetThuc.getTime())); // Thời gian bắt đầu của lớp này
	        preStatement.setDate(3, new java.sql.Date(ngayBatDau.getTime())); // Thời gian kết thúc của lớp này
	        preStatement.setString(4, role);
	        resultSet = preStatement.executeQuery();
	        if (resultSet.next()) {
	            int count = resultSet.getInt("count");
	            if (count > 0) {
	                roleExists = true; // Nếu có ít nhất một bản ghi tồn tại, đánh dấu role đã tồn tại trong khoảng thời gian của lớp này
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.closeResultSet(resultSet);
	        DBConnection.closePreparedStatement(preStatement);
	        DBConnection.closeConnect(jdbcConnection);
	    }

	    return roleExists;
	}

	public boolean checkStudentAsClassLeader(String maSinhVien, Date ngayBatDau, Date ngayKetThuc) {
	    boolean isClassLeader = false;
	    String sql = "SELECT COUNT(*) AS count FROM ban_can_su_lop WHERE ma_sinh_vien = ? "
	               + "AND thoi_gian_bat_dau <= ? AND thoi_gian_ket_thuc >= ?";
	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);

	    try {
	        preStatement = jdbcConnection.prepareStatement(sql);
	        preStatement.setString(1, maSinhVien);
	        preStatement.setDate(2, new java.sql.Date(ngayKetThuc.getTime())); // Thời gian bắt đầu của khoảng thời gian
	        preStatement.setDate(3, new java.sql.Date(ngayBatDau.getTime())); // Thời gian kết thúc của khoảng thời gian
	        resultSet = preStatement.executeQuery();
	        if (resultSet.next()) {
	            int count = resultSet.getInt("count");
	            if (count > 0) {
	                isClassLeader = true; // Nếu có ít nhất một bản ghi tồn tại, đánh dấu sinh viên đã là cán bộ lớp trong khoảng thời gian đó
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.closeResultSet(resultSet);
	        DBConnection.closePreparedStatement(preStatement);
	        DBConnection.closeConnect(jdbcConnection);
	    }

	    return isClassLeader;
	}

}
