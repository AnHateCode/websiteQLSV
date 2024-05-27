package vnua.qlsv.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import vnua.qlsv.bean.ChuyenNganh;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.Khoa;

public class KhoaDAO extends HttpServlet {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	public KhoaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	public List<Khoa> getKhoaChuyenNganhList() throws SQLException {
		// danh sach chua ket qua tra ve
		List<Khoa> khoaList = new ArrayList<>();

		// cau lenh sql
		String sql = "SELECT khoas.ma_khoa, khoas.ten_khoa, chuyen_nganhs.ma_chuyen_nganh, chuyen_nganhs.ten_chuyen_nganh " +
                "FROM khoas " +
                "INNER JOIN chuyen_nganhs ON khoas.ma_khoa = chuyen_nganhs.ma_khoa";

		// tao ket noi
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			// tao doi tuong truy van CSDL
			statement = jdbcConnection.createStatement();

			// thuc hien truy van
			resultSet = statement.executeQuery(sql);
			
			 String currentMaKhoa = null;
	            Khoa currentKhoa = null;
			// duyet qua danh sach ban ghi ket qua tra ve
			while (resultSet.next()) {
				String maKhoa = resultSet.getString("ma_khoa");
                if (!maKhoa.equals(currentMaKhoa)) {
                    // Nếu mã khoa mới khác với mã khoa hiện tại, tạo một khoa mới
                    currentKhoa = new Khoa();
                    currentKhoa.setMaKhoa(maKhoa);
                    currentKhoa.setTenKhoa(resultSet.getString("ten_khoa"));
                    currentKhoa.setChuyenNganhList(new ArrayList<>());
                    khoaList.add(currentKhoa);
                    currentMaKhoa = maKhoa;
			}
                ChuyenNganh chuyenNganh = new ChuyenNganh();
                chuyenNganh.setMaChuyenNganh(resultSet.getString("ma_chuyen_nganh"));
                chuyenNganh.setTenChuyenNganh(resultSet.getString("ten_chuyen_nganh"));
                currentKhoa.getChuyenNganhList().add(chuyenNganh);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return khoaList;
	}
	public  List<Khoa> getKhoaChuyenNganhList(int offset, int noOfRecords, String keyword) {
		// danh sach chua ket qua tra ve
		List<Khoa> khoaList = new ArrayList<>();

		// cau lenh sql
		String sql = "SELECT khoas.ma_khoa, khoas.ten_khoa, chuyen_nganhs.ma_chuyen_nganh, chuyen_nganhs.ten_chuyen_nganh " +
		              "FROM khoas " +
		              "INNER JOIN chuyen_nganhs ON khoas.ma_khoa = chuyen_nganhs.ma_khoa ";
		if (keyword != null && !keyword.isEmpty()) {
			sql += "WHERE khoas.ma_khoa LIKE ? ";
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

			String currentMaKhoa = null;
	        Khoa currentKhoa = null;
			while (resultSet.next()) {
				String maKhoa = resultSet.getString("ma_khoa");
                if (!maKhoa.equals(currentMaKhoa)) {
                    // Nếu mã khoa mới khác với mã khoa hiện tại, tạo một khoa mới
                    currentKhoa = new Khoa();
                    currentKhoa.setMaKhoa(maKhoa);
                    currentKhoa.setTenKhoa(resultSet.getString("ten_khoa"));
                    currentKhoa.setChuyenNganhList(new ArrayList<>());
                    khoaList.add(currentKhoa);
                    currentMaKhoa = maKhoa;
			}
                ChuyenNganh chuyenNganh = new ChuyenNganh();
                chuyenNganh.setMaChuyenNganh(resultSet.getString("ma_chuyen_nganh"));
                chuyenNganh.setTenChuyenNganh(resultSet.getString("ten_chuyen_nganh"));
                currentKhoa.getChuyenNganhList().add(chuyenNganh);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return khoaList;
	}
	public int getNoOfRecords(String keyword) {
		String sql = "SELECT count(ma_khoa) FROM khoas ";
		int result = 0;
		if (keyword != null && !keyword.isEmpty()) {
			sql += "WHERE ma_khoa LIKE ? ";
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
