package vnua.qlsv.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vnua.qlsv.model.DBConnection;
import vnua.qlsv.bean.BanCanSuLop;
import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.bean.User;

public class UserDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;

	public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public User findUser(String username, String password) {
		String sql = "SELECT * FROM users WHERE TenDangNhap = ? AND MatKhau = ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, username);
			preStatement.setString(2, password);
			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
				return new User(resultSet.getString("id"),resultSet.getString("TenDangNhap"),resultSet.getString("MatKhau"),resultSet.getInt("LoaiNguoiDung")) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<User> findUser() {
		List<User> listUser = new ArrayList<User>();
		String sql = "SELECT * FROM users ";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			statement = jdbcConnection.createStatement();

			// thuc hien truy van
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				String maNguoiDung = resultSet.getString("id");
				String tenDangNhap = resultSet.getString("ten_dang_nhap");
				String matKhau = resultSet.getString("mat_khau");
				Integer loaiNguoiDung = resultSet.getInt("loại_nguoi_dung");
				// đóng gói các giá trị thuộc tính vào đối tượng Bean(Book)
				User user = new User(maNguoiDung, tenDangNhap, matKhau, loaiNguoiDung);
				// Thêm đối tượng Bean vào danh sách
				listUser.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUser;
	}
	
	public List<User> listAllUsers(int offset, int noOfRecords, String keyword) {
		List<User> listUser = new ArrayList<User>();
		String sql = "SELECT * FROM users ";
		if (keyword != null && !keyword.isEmpty()) {
			sql += "WHERE id LIKE ? ";
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
				String maNguoiDung = resultSet.getString("id");
				String tenDangNhap = resultSet.getString("TenDangNhap");
				String matKhau = resultSet.getString("MatKhau");
				Integer loaiNguoiDung = resultSet.getInt("LoaiNguoiDung");
				// đóng gói các giá trị thuộc tính vào đối tượng Bean(Book)
				User user = new User(maNguoiDung, tenDangNhap, matKhau, loaiNguoiDung);

				// Thêm đối tượng Bean vào danh sách
				listUser.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return listUser;
	}

	public User findUser(String username) {
		String sql = "SELECT * FROM users WHERE TenDangNhap = ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, username);
			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
				return new User(resultSet.getString("TenDangNhap"),
						resultSet.getString("MatKhau"),resultSet.getInt("LoaiNguoiDung"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public User findUserByID(String id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, id);
			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
				return new User(resultSet.getString("id"),resultSet.getString("TenDangNhap"),
						resultSet.getString("MatKhau"),resultSet.getInt("LoaiNguoiDung"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean insertUser(User user) {
		boolean insertResult = false;
		String sql = "INSERT INTO users(id,TenDangNhap, MatKhau, LoaiNguoiDung) VALUE (?,?,?,?)";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1,user.getMaDangNhap());
			preStatement.setString(2,user.getTenDangNhap());
			preStatement.setString(3,user.getMatKhau());
			preStatement.setInt(4,user.getLoaiNguoiDung());
			insertResult = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return insertResult;
	}
	
	public boolean updateUser(User user) {
	    boolean result = false;
	    String sql = "UPDATE users SET id = ?,TenDangNhap = ?, MatKhau = ?,LoaiNguoiDung = ? "
	            + "WHERE id = ?";
	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);

	    try {
	    	preStatement = jdbcConnection.prepareStatement(sql);
	    	preStatement.setString(1,user.getMaDangNhap());
			preStatement.setString(2,user.getTenDangNhap());
			preStatement.setString(3,user.getMatKhau());
			preStatement.setInt(4,user.getLoaiNguoiDung());
			preStatement.setString(5,user.getMaDangNhap());
	        result = preStatement.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.closePreparedStatement(preStatement);
	        DBConnection.closeConnect(jdbcConnection);
	    }

	    return result;
	}
	
	public boolean deleteUser(String Id) {
		boolean result = false;
		// Cau lenh sql
		String sql = "DELETE FROM users WHERE id = ?";

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
		String sql = "SELECT count(id) FROM users ";
		int result = 0;
		if (keyword != null && !keyword.isEmpty()) {
			sql += "WHERE id LIKE ? ";
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
