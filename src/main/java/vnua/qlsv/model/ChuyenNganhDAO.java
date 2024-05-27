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

import vnua.qlsv.bean.ChuyenNganh;
import vnua.qlsv.bean.NienKhoa;

public class ChuyenNganhDAO {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	public ChuyenNganhDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	public List<ChuyenNganh> listAllChuyenNganh() {
		// danh sach chua ket qua tra ve
		List<ChuyenNganh> listCN = new ArrayList<ChuyenNganh>();

		// cau lenh sql
		String sql = "SELECT * FROM	chuyen_nganhs";

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
				String maChuyenNganh = resultSet.getString("ma_chuyen_nganh");
				String tenChuyenNganh = resultSet.getString("ten_chuyen_nganh");
				
				// đóng gói các giá trị thuộc tính vào đối tượng Bean(Book)
				ChuyenNganh cn = new ChuyenNganh(maChuyenNganh, tenChuyenNganh);
			    

				// Thêm đối tượng Bean vào danh sách
				listCN.add(cn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return listCN;
	}
	
	public List<ChuyenNganh> listAllChuyenNganhBymaKhoa(String maKhoa) {
	    List<ChuyenNganh> listCN = new ArrayList<ChuyenNganh>();
	    String sql = "SELECT * FROM chuyen_nganhs WHERE ma_khoa = ?";

	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);
	    try {
	        preStatement = jdbcConnection.prepareStatement(sql);
	        preStatement.setString(1, maKhoa);
	        resultSet = preStatement.executeQuery();

	        while (resultSet.next()) {
	            String maChuyenNganh = resultSet.getString("ma_chuyen_nganh");
	            String tenChuyenNganh = resultSet.getString("ten_chuyen_nganh");

	            ChuyenNganh cn = new ChuyenNganh(maChuyenNganh, tenChuyenNganh);
	            listCN.add(cn);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.closeResultSet(resultSet);
	        DBConnection.closePreparedStatement(preStatement);
	        DBConnection.closeConnect(jdbcConnection);
	    }
	    return listCN;
	}

}
