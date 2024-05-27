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

import vnua.qlsv.bean.MonHoc;
import vnua.qlsv.bean.ChuyenNganh;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.KyHoc;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.LopHoc;
public class MonHocDAO extends HttpServlet{
	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	public MonHocDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	public List<MonHoc> getAllMonHoc() throws SQLException {
		// danh sach chua ket qua tra ve
		List<MonHoc> monHocList = new ArrayList<>();

		// cau lenh sql
		String sql = "SELECT * FROM	mon_hocs";

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
				 
				       
				       String maMonHoc = resultSet.getString("ma_mon_hoc");
				       String tenMonHoc = resultSet.getString("ten_mon_hoc");
				       ChuyenNganh chuyenNganh = new ChuyenNganh();
				       chuyenNganh.setMaChuyenNganh(resultSet.getString("ma_chuyen_nganh"));
				       
				      MonHoc monHoc = new MonHoc(maMonHoc, tenMonHoc, chuyenNganh);
				      monHocList.add(monHoc);
				    
			}	 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return monHocList;
	}
	
	public List<MonHoc> getAllMonHoc(String id) throws SQLException {
		// danh sach chua ket qua tra ve
		List<MonHoc> monHocList = new ArrayList<>();

		// cau lenh sql
		String sql = "SELECT * FROM mon_hocs WHERE ma_chuyen_nganh = ?";

		// tao ket noi
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			// tao doi tuong truy van CSDL
			statement = jdbcConnection.createStatement();
			
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, id);
			// thuc hien truy van
			resultSet = preStatement.executeQuery();
			// duyet qua danh sach ban ghi ket qua tra ve
			while (resultSet.next()) {
				 
				       
				       String maMonHoc = resultSet.getString("ma_mon_hoc");
				       String tenMonHoc = resultSet.getString("ten_mon_hoc");
				       ChuyenNganh chuyenNganh = new ChuyenNganh();
				       chuyenNganh.setMaChuyenNganh(resultSet.getString("ma_chuyen_nganh"));
				       
				      MonHoc monHoc = new MonHoc(maMonHoc, tenMonHoc, chuyenNganh);
				      monHocList.add(monHoc);
				    
			}	 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return monHocList;
	}
}
