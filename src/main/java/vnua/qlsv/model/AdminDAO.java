package vnua.qlsv.model;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.bean.Admin;

public class AdminDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	
	public AdminDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public Admin getAdmin(String id) {
		Admin admin = null;
		String sql = "SELECT * FROM admin WHERE ma_admin = ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, id);
			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
				String maAdmin = resultSet.getString("ma_admin");
				String	email = resultSet.getString("email");
				String ngaySinh = resultSet.getString("ma_khoa");
			
				

				// đóng gói các giá trị thuộc tính vào đối tượng Bean(Book)
				admin = new Admin(maAdmin,email,ngaySinh);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return admin;
	}
	
}
