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
import vnua.qlsv.bean.Khoa;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.NienKhoa;


public class NienKhoaDAO {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	public NienKhoaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	
	public List<NienKhoa> listAllNienKhoa() {
		// danh sach chua ket qua tra ve
		List<NienKhoa> listNK = new ArrayList<NienKhoa>();

		// cau lenh sql
		String sql = "SELECT * FROM	nien_khoa";

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
				String maNienKhoa = resultSet.getString("ma_nien_khoa");
				Date namBatDau = resultSet.getDate("nam_bat_dau");
			    Date namKetThuc = resultSet.getDate("nam_ket_thuc");
				
				// đóng gói các giá trị thuộc tính vào đối tượng Bean(Book)
				
			    NienKhoa nk = new NienKhoa(maNienKhoa,namBatDau,namKetThuc);

				// Thêm đối tượng Bean vào danh sách
				listNK.add(nk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return listNK;
	}
	
	public NienKhoa getNienKhoa(String id) {
		 NienKhoa nienKhoa = null;
		String sql = "SELECT * FROM nien_khoa WHERE ma_nien_khoa = ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, id);
			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
			
				 String maNienKhoa = resultSet.getString("ma_nien_khoa");
				 Date ngayBatDau = resultSet.getDate("nam_bat_dau");
			     Date ngayKetThuc = resultSet.getDate("nam_ket_thuc");
			     
			     nienKhoa = new NienKhoa(maNienKhoa, ngayBatDau, ngayKetThuc);
			      
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return nienKhoa ;
	}
}
