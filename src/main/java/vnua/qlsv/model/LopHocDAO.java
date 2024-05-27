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

import vnua.qlsv.bean.ChuyenNganh;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.Khoa;
import vnua.qlsv.bean.KyHoc;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.LopHoc;
import vnua.qlsv.bean.MonHoc;

public class LopHocDAO extends HttpServlet {

	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	public LopHocDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	public List<LopHoc> getAllLopHoc() throws SQLException {
		// danh sach chua ket qua tra ve
		List<LopHoc> lopHocList = new ArrayList<>();

		// cau lenh sql
		String sql = "SELECT * FROM	lop_hocs";

		// tao ket noi
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			// tao doi tuong truy van CSDL
			statement = jdbcConnection.createStatement();

			// thuc hien truy van
			resultSet = statement.executeQuery(sql);
			// duyet qua danh sach ban ghi ket qua tra ve
			while (resultSet.next()) {
				 
				       
				       String maLopHoc = resultSet.getString("ma_lop_hoc");
				       Lop lop = new Lop();
				       lop.setMaLop( resultSet.getString("ma_lop"));
				       
				       KyHoc kyHoc = new KyHoc();
				       kyHoc.setMaKiHoc(resultSet.getString("ma_ky_hoc"));
				       
				       MonHoc monHoc = new MonHoc();
				       monHoc.setMaMonHoc(resultSet.getString("ma_mon_hoc"));
				       
				       GiangVien giangVien = new GiangVien();
				       giangVien.setMaGV(resultSet.getString("ma_giang_vien"));
				       
				       Date ngayBatDau = resultSet.getDate("ngay_bat_dau");
				       Date ngayKetThuc = resultSet.getDate("ngay_ket_thuc");
				       String ngayTrongTuan = resultSet.getString("ngay_trong_tuan");
				       Integer soTiet = resultSet.getInt("so_tiet");
				       Time tietBatDau = resultSet.getTime("tiet_bat_dau");
				       String phongHoc = resultSet.getString("phong_hoc");
				       
				       LopHoc lopHoc = new LopHoc(maLopHoc, lop, kyHoc, monHoc, giangVien, ngayBatDau, ngayKetThuc, ngayTrongTuan, soTiet, tietBatDau, phongHoc);
				        lopHocList.add(lopHoc);
				    
			}	 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return lopHocList;
	}
	
	public List<LopHoc> getAllLopHoc(int offset, int noOfRecords, String keyword) {
		// danh sach chua ket qua tra ve
		List<LopHoc> lopHocList = new ArrayList<>();

		// cau lenh sql
		String sql = "SELECT * FROM	lop_hocs ";
				
		if (keyword != null && !keyword.isEmpty()) {
			sql += "WHERE ma_lop_hoc LIKE ? ";
		}
		sql += "LIMIT ?, ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			int index = 0;
			preStatement = jdbcConnection.prepareStatement(sql);
			if (keyword != null && !keyword.isEmpty()) {
				preStatement.setString(++index, "%" + keyword + "%");
			}
			preStatement.setInt(++index, offset); // vị trí bắt đầu lấy
			preStatement.setInt(++index, noOfRecords); // số bản ghi lấy ra

			resultSet = preStatement.executeQuery();
			while (resultSet.next()) {
				 
			       
			       String maLopHoc = resultSet.getString("ma_lop_hoc");
			       Lop lop = new Lop();
			       lop.setMaLop( resultSet.getString("ma_lop"));
			       
			       KyHoc kyHoc = new KyHoc();
			       kyHoc.setMaKiHoc(resultSet.getString("ma_ky_hoc"));
			       
			       MonHoc monHoc = new MonHoc();
			       monHoc.setMaMonHoc(resultSet.getString("ma_mon_hoc"));
			       
			       GiangVien giangVien = new GiangVien();
			       giangVien.setMaGV(resultSet.getString("ma_giang_vien"));
			       
			       Date ngayBatDau = resultSet.getDate("ngay_bat_dau");
			       Date ngayKetThuc = resultSet.getDate("ngay_ket_thuc");
			       String ngayTrongTuan = resultSet.getString("ngay_trong_tuan");
			       Integer soTiet = resultSet.getInt("so_tiet");
			       Time tietBatDau = resultSet.getTime("tiet_bat_dau");
			       String phongHoc = resultSet.getString("phong_hoc");
			       
			       LopHoc lopHoc = new LopHoc(maLopHoc, lop, kyHoc, monHoc, giangVien, ngayBatDau, ngayKetThuc, ngayTrongTuan, soTiet, tietBatDau, phongHoc);
			        lopHocList.add(lopHoc);
			    
		}	 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return lopHocList;
	}
	
	public boolean insertLopHoc(LopHoc lh) {
		boolean insertResult = false;
		String sql = "INSERT INTO lop_hocs(ma_lop_hoc,ma_lop, ma_ky_hoc, ma_mon_hoc, ma_giang_vien, ngay_bat_dau,ngay_ket_thuc,ngay_trong_tuan,so_tiet,tiet_bat_dau,phong_hoc) VALUE (?,?,?,?,?,?,?,?,?,?,?)";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1,lh.getMaLopHoc() );
			preStatement.setString(2,lh.getLop().getMaLop());
			preStatement.setString(3, lh.getKyHoc().getMaKiHoc() );
			preStatement.setString(4, lh.getMonHoc().getMaMonHoc());
			preStatement.setString(5, lh.getGiangVien().getMaGV());
			preStatement.setDate(6, new java.sql.Date(lh.getNgayBatDau().getTime())) ;
			preStatement.setDate(7, new java.sql.Date(lh.getNgayKetThuc().getTime()));
			preStatement.setString(8,lh.getNgayTrongTuan());
			preStatement.setInt(9,lh.getSoTiet());
			preStatement.setTime(10,lh.getTietBatDau());
			preStatement.setString(11,lh.getPhongHoc());
			insertResult = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return insertResult;
	}
	
	public LopHoc getLopHoc(String id) {
		LopHoc lopHoc = null ;
		String sql = "SELECT * FROM lop_hocs WHERE ma_lop_hoc= ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, id);
			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
				String maLopHoc = resultSet.getString("ma_lop_hoc");
			       Lop lop = new Lop();
			       lop.setMaLop( resultSet.getString("ma_lop"));
			       
			       KyHoc kyHoc = new KyHoc();
			       kyHoc.setMaKiHoc(resultSet.getString("ma_ky_hoc"));
			       
			       MonHoc monHoc = new MonHoc();
			       monHoc.setMaMonHoc(resultSet.getString("ma_mon_hoc"));
			       
			       GiangVien giangVien = new GiangVien();
			       giangVien.setMaGV(resultSet.getString("ma_giang_vien"));
			       
			       Date ngayBatDau = resultSet.getDate("ngay_bat_dau");
			       Date ngayKetThuc = resultSet.getDate("ngay_ket_thuc");
			       String ngayTrongTuan = resultSet.getString("ngay_trong_tuan");
			       Integer soTiet = resultSet.getInt("so_tiet");
			       Time tietBatDau = resultSet.getTime("tiet_bat_dau");
			       String phongHoc = resultSet.getString("phong_hoc");
				

			
			       lopHoc = new LopHoc(maLopHoc, lop, kyHoc, monHoc, giangVien, ngayBatDau, ngayKetThuc, ngayTrongTuan, soTiet, tietBatDau, phongHoc);									
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return lopHoc;
	}
	
	public List<LopHoc> getLopHocFromChuyenNganh(String id) {
		List<LopHoc> lopHoc =  new ArrayList<>();
		String sql = "SELECT lop_hocs.ma_lop_hoc, lop_hocs.ma_lop, lop_hocs.ma_ky_hoc,lop_hocs.ma_mon_hoc,lop_hocs.ma_giang_vien,lop_hocs.ngay_trong_tuan, lop_hocs.ngay_bat_dau, lop_hocs.ngay_ket_thuc, lop_hocs.so_tiet, lop_hocs.tiet_bat_dau, lop_hocs.phong_hoc, mon_hocs.ten_mon_hoc " +
	             "FROM lop_hocs " +
	             "INNER JOIN mon_hocs ON lop_hocs.ma_mon_hoc = mon_hocs.ma_mon_hoc " +
	             "INNER JOIN chuyen_nganhs ON mon_hocs.ma_chuyen_nganh = chuyen_nganhs.ma_chuyen_nganh " +
	             "INNER JOIN sinh_vien ON chuyen_nganhs.ma_chuyen_nganh = sinh_vien.ma_chuyen_nganh " +
	             "WHERE sinh_vien.ma_sinh_vien= ?";
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
			       lopHoc.add(lh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return lopHoc;
	}
	
	public boolean updateLopHoc(LopHoc lh) {
	    boolean result = false;
	    String sql = "UPDATE lop_hocs SET ma_lop = ?, ma_ky_hoc = ?, ma_mon_hoc = ?, "
	            + "ma_giang_vien = ?, ngay_bat_dau = ?, ngay_ket_thuc = ?, ngay_trong_tuan = ?, so_tiet = ?, tiet_bat_dau = ? ,phong_hoc = ? WHERE ma_lop_hoc = ?";
	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);

	    try {
	    	preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1,lh.getLop().getMaLop() );
			preStatement.setString(2,lh.getLop().getMaLop());
			preStatement.setString(2, lh.getKyHoc().getMaKiHoc() );
			preStatement.setString(3, lh.getMonHoc().getMaMonHoc());
			preStatement.setString(4, lh.getGiangVien().getMaGV());
			preStatement.setDate(5, new java.sql.Date(lh.getNgayBatDau().getTime())) ;
			preStatement.setDate(6, new java.sql.Date(lh.getNgayKetThuc().getTime()));
			preStatement.setString(7,lh.getNgayTrongTuan());
			preStatement.setInt(8,lh.getSoTiet());
			preStatement.setTime(9,lh.getTietBatDau());
			preStatement.setString(10,lh.getPhongHoc());  
	        preStatement.setString(11,lh.getMaLopHoc());
	        result = preStatement.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.closePreparedStatement(preStatement);
	        DBConnection.closeConnect(jdbcConnection);
	    }

	    return result;
	}
	
	public boolean deleteLopHoc(String Id) {
		boolean result = false;
		// Cau lenh sql
		String sql = "DELETE FROM lop_hocs WHERE ma_lop_hoc = ?";

		// Tao ket noi
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);

		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, Id);
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
	
	public int getNoOfRecords(String keyword) {
		String sql = "SELECT count(ma_lop_hoc) FROM lop_hocs ";
		int result = 0;
		if (keyword != null && !keyword.isEmpty()) {
			sql += "WHERE ma_lop_hoc LIKE ? ";
		}
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			if (keyword != null && !keyword.isEmpty()) {
				preStatement.setString(1, "%" + keyword + "%");
			}

			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return result;
	}
}
