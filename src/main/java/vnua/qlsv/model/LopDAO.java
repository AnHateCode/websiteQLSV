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

import vnua.qlsv.bean.ChuNhiem;
import vnua.qlsv.bean.ChuyenNganh;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.Khoa;
import vnua.qlsv.bean.KyHoc;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.LopHoc;
import vnua.qlsv.bean.MonHoc;
import vnua.qlsv.bean.NienKhoa;
import vnua.qlsv.bean.SinhVien;

public class LopDAO extends HttpServlet{

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	public LopDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	public List<Lop> getAllLop() throws SQLException {
		// danh sach chua ket qua tra ve
		List<Lop> lopList = new ArrayList<>();

		// cau lenh sql
		String sql = "SELECT * FROM	lops";

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
				 
				       
				       String maLop = resultSet.getString("ma_lop");
				       String tenLop = resultSet.getString("ten_lop");
				       
				  
				       
				       Khoa khoa = new Khoa();
				       khoa.setMaKhoa(resultSet.getString("ma_khoa"));
				       
				      ChuyenNganh chuyenNganh = new ChuyenNganh();
				      chuyenNganh.setMaChuyenNganh(resultSet.getString("ma_chuyen_nganh"));
				       
				       GiangVien giangVien = new GiangVien();
				       giangVien.setMaGV(resultSet.getString("ma_giang_vien"));
				       
				       Lop lop = new Lop(maLop, tenLop, chuyenNganh, khoa);
				        lopList.add(lop);
				    
			}	 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return lopList;
	}
	
	public List<Lop> getAllLopByNienKhoaAndKhoa(String maNienKhoa, String maKhoa) {
	    // Danh sách chứa kết quả trả về
	    List<Lop> lopList = new ArrayList<>();

	    // Câu lệnh SQL
	    String sql = "SELECT * FROM lops WHERE ma_nien_khoa = ? AND ma_khoa = ?";

	    // Tạo kết nối
	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);
	    try {
	        preStatement = jdbcConnection.prepareStatement(sql);
	        preStatement.setString(1, maNienKhoa);
	        preStatement.setString(2, maKhoa);

	        resultSet = preStatement.executeQuery();
	        while (resultSet.next()) {
	            String maLop = resultSet.getString("ma_lop");
	            String tenLop = resultSet.getString("ten_lop");

	            Khoa khoa = new Khoa();
	            khoa.setMaKhoa(resultSet.getString("ma_khoa"));

	            ChuyenNganh chuyenNganh = new ChuyenNganh();
	            chuyenNganh.setMaChuyenNganh(resultSet.getString("ma_chuyen_nganh"));

	            NienKhoa nienKhoa = new NienKhoa();
	            nienKhoa.setMaNienKhoa(resultSet.getString("ma_nien_khoa"));

	            Lop lop = new Lop(maLop, tenLop, chuyenNganh, khoa, nienKhoa);
	            lopList.add(lop);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.closeResultSet(resultSet);
	        DBConnection.closePreparedStatement(preStatement);
	        DBConnection.closeConnect(jdbcConnection);
	    }
	    return lopList;
	}

	
	public Lop getLop(String id) {
		 Lop lop = null;
		String sql = "SELECT * FROM lops WHERE ma_lop = ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, id);
			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
			
				 String maLop = resultSet.getString("ma_lop");
			       String tenLop = resultSet.getString("ten_lop");
			       
			  
			       
			       Khoa khoa = new Khoa();
			       khoa.setMaKhoa(resultSet.getString("ma_khoa"));
			       
			       ChuyenNganh chuyenNganh = new ChuyenNganh();
				      chuyenNganh.setMaChuyenNganh(resultSet.getString("ma_chuyen_nganh"));
				      
			      NienKhoa nienKhoa = new NienKhoa();
			      nienKhoa.setMaNienKhoa(resultSet.getString("ma_nien_khoa"));
			       
			     
			       
			     lop = new Lop(maLop, tenLop, chuyenNganh, khoa,nienKhoa);
			      
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return lop ;
	}
	
	public List<Lop> getAllLop(int offset, int noOfRecords, String keyword) {
		// danh sach chua ket qua tra ve
		List<Lop> lopList = new ArrayList<>();

		// cau lenh sql
		String sql = "SELECT * FROM	lops ";
				
		if (keyword != null && !keyword.isEmpty()) {
			sql += "WHERE ma_lop LIKE ? ";
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
				 
			       
				  String maLop = resultSet.getString("ma_lop");
			       String tenLop = resultSet.getString("ten_lop");
			       
			  
			       
			       Khoa khoa = new Khoa();
			       khoa.setMaKhoa(resultSet.getString("ma_khoa"));
			       
			      ChuyenNganh chuyenNganh = new ChuyenNganh();
			      chuyenNganh.setMaChuyenNganh(resultSet.getString("ma_chuyen_nganh"));
			       
			     
			       
			       NienKhoa nienKhoa = new NienKhoa();
			       nienKhoa.setMaNienKhoa(resultSet.getString("ma_nien_khoa"));
			      
			       Lop lop = new Lop(maLop, tenLop, chuyenNganh, khoa,nienKhoa);
			        lopList.add(lop);
			    
		}	 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return lopList;
	}
	
	
	public List<Lop> getAllLop(int offset, int noOfRecords, String keyword,String maNienKhoa,String maKhoa , String maChuyenNganh) {
		// danh sach chua ket qua tra ve
		List<Lop> lopList = new ArrayList<>();

		// cau lenh sql
		String sql = "SELECT * FROM lops WHERE 1=1"; // WHERE 1=1 để bắt đầu câu lệnh SQL với điều kiện luôn đúng

	    if (keyword != null && !keyword.isEmpty()) {
	        sql += " AND ma_lop LIKE ?";
	    }

	    if (maNienKhoa != null && !maNienKhoa.isEmpty()) {
	        sql += " AND ma_nien_khoa LIKE ?";
	    }

	    if (maKhoa != null && !maKhoa.isEmpty()) {
	        sql += " AND ma_khoa LIKE ?";
	    }
	    
	    if (maChuyenNganh != null && !maChuyenNganh.isEmpty()) {
	        sql += " AND ma_chuyen_nganh LIKE ?";
	    }

		sql += " LIMIT ?, ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			int index = 0;
			preStatement = jdbcConnection.prepareStatement(sql);
			if (keyword != null && !keyword.isEmpty()) {
				preStatement.setString(++index, "%" + keyword + "%");
			}
			if (maNienKhoa != null && !maNienKhoa.isEmpty()) {
	            preStatement.setString(++index, "%" + maNienKhoa + "%");
	        }
	        if (maKhoa != null && !maKhoa.isEmpty()) {
	            preStatement.setString(++index, "%" + maKhoa + "%");
	        }
	        if (maChuyenNganh != null && !maChuyenNganh.isEmpty()) {
	            preStatement.setString(++index, "%" + maChuyenNganh + "%");
	        }
			preStatement.setInt(++index, offset); // vị trí bắt đầu lấy
			preStatement.setInt(++index, noOfRecords); // số bản ghi lấy ra

			resultSet = preStatement.executeQuery();
			while (resultSet.next()) {
				 
			       
				  String maLop = resultSet.getString("ma_lop");
			       String tenLop = resultSet.getString("ten_lop");
			       
			  
			       
			       Khoa khoa = new Khoa();
			       khoa.setMaKhoa(resultSet.getString("ma_khoa"));
			       
			      ChuyenNganh chuyenNganh = new ChuyenNganh();
			      chuyenNganh.setMaChuyenNganh(resultSet.getString("ma_chuyen_nganh"));
			       
			     
			       
			       NienKhoa nienKhoa = new NienKhoa();
			       nienKhoa.setMaNienKhoa(resultSet.getString("ma_nien_khoa"));
			      
			       Lop lop = new Lop(maLop, tenLop, chuyenNganh, khoa,nienKhoa);
			        lopList.add(lop);
			    
		}	 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return lopList;
	}
	
	public int getNoOfRecords(String keyword) {
		String sql = "SELECT count(ma_lop) FROM lops ";
		int result = 0;
		if (keyword != null && !keyword.isEmpty()) {
			sql += "WHERE ma_lop LIKE ? ";
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
	
	public boolean insertLop(Lop lop) {
		boolean insertResult = false;
		String sql = "INSERT INTO lops(ma_lop,ten_lop,ma_khoa, ma_chuyen_nganh, ma_nien_khoa) VALUE (?,?,?,?,?)";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1,lop.getMaLop() );
			preStatement.setString(2,lop.getTenLop());
			preStatement.setString(3, lop.getKhoa().getMaKhoa() );
			preStatement.setString(4,lop.getChuyenNganh().getMaChuyenNganh());
			preStatement.setString(5, lop.getNienKhoa().getMaNienKhoa());
			insertResult = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return insertResult;
	}
	
}
