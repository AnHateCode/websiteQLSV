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
import vnua.qlsv.bean.KyHoc;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.LopHoc;
import vnua.qlsv.bean.MonHoc;
public class KyHocDAO extends HttpServlet {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	public KyHocDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	public List<KyHoc> getAllKyHoc() throws SQLException {
		// danh sach chua ket qua tra ve
		List<KyHoc> kyHocList = new ArrayList<>();

		// cau lenh sql
		String sql = "SELECT * FROM	ky_hocs";

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
				 
				       
				       String maKyHoc = resultSet.getString("ma_ky_hoc");
				       String namHoc = resultSet.getString("nam_hoc");
				       Integer hocKy = resultSet.getInt("hoc_ky");
				       KyHoc kyHoc = new KyHoc(maKyHoc, namHoc, hocKy);
				        kyHocList.add(kyHoc);
				    
			}	 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return kyHocList;
	}
}
