package vnua.qlsv.model;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vnua.qlsv.bean.GiangVien;


public class GiangVienDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	public GiangVienDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	public List<GiangVien> listAllGiangViens() {
		// danh sach chua ket qua tra ve
		List<GiangVien> listGV = new ArrayList<GiangVien>();

		// cau lenh sql
		String sql = "SELECT * FROM	giang_vien";

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
				String maGV = resultSet.getString("ma_giang_vien");
				String hoTen = resultSet.getString("ho_ten");
				String ngaySinh = resultSet.getString("ngay_sinh");
				String diaChi = resultSet.getString("dia_chi");
				String email = resultSet.getString("email");
				String soDienThoai = resultSet.getString("so_dien_thoai");
				String maKhoa = resultSet.getString("ma_khoa");
				String hocVan = resultSet.getString("hoc_van");
				String chuyenMon = resultSet.getString("chuyen_nganh");
				String avatar = resultSet.getString("avatar");
				
				
				// đóng gói các giá trị thuộc tính vào đối tượng Bean(Book)
				GiangVien gv = new GiangVien(maGV,hoTen,ngaySinh, diaChi,email,soDienThoai);
				
				gv.setAvatar(avatar);
				gv.setMaKhoa1(maKhoa);
				gv.setChuyenMon(chuyenMon);
				gv.setHocVan(hocVan);
				// Thêm đối tượng Bean vào danh sách
				listGV.add(gv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return listGV;
	}
	
	public List<GiangVien> listAllGiangViens(String maCN) {
		// danh sach chua ket qua tra ve
		List<GiangVien> listGV = new ArrayList<GiangVien>();

		// cau lenh sql
		String sql = "SELECT giang_vien.* " +
	             "FROM giang_vien " +
	             "LEFT JOIN Chu_nhiem ON giang_vien.ma_giang_vien = Chu_nhiem.ma_giang_vien " +
	             "WHERE Chu_nhiem.ma_lop IS NULL AND giang_vien.chuyen_nganh = ?";


		// tao ket noi
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			// tao doi tuong truy van CSDL
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, maCN);
			resultSet = preStatement.executeQuery();

			// duyet qua danh sach ban ghi ket qua tra ve
			while (resultSet.next()) {
				String maGV = resultSet.getString("ma_giang_vien");
				String hoTen = resultSet.getString("ho_ten");
				String ngaySinh = resultSet.getString("ngay_sinh");
				String diaChi = resultSet.getString("dia_chi");
				String email = resultSet.getString("email");
				String soDienThoai = resultSet.getString("so_dien_thoai");
				String maKhoa = resultSet.getString("ma_khoa");
				String hocVan = resultSet.getString("hoc_van");
				String chuyenMon = resultSet.getString("chuyen_nganh");
				String avatar = resultSet.getString("avatar");
				Integer trangThai = resultSet.getInt("trangThai");
				
				
				// đóng gói các giá trị thuộc tính vào đối tượng Bean(Book)
				GiangVien gv = new GiangVien(maGV,hoTen,ngaySinh, diaChi,email,soDienThoai);
				
				gv.setAvatar(avatar);
				gv.setMaKhoa1(maKhoa);
				gv.setChuyenMon(chuyenMon);
				gv.setHocVan(hocVan);
				gv.setTrangThai(trangThai);
				// Thêm đối tượng Bean vào danh sách
				listGV.add(gv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return listGV;
	}
	
	 public List<GiangVien> listAllGiangVienByCN(String maCN) {
	        List<GiangVien> listGV = new ArrayList<>();

	        String sql = "SELECT * FROM giang_vien WHERE chuyen_nganh = ?";

	        jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);

	        try {
	            preStatement = jdbcConnection.prepareStatement(sql);
	            preStatement.setString(1, maCN);
	            resultSet = preStatement.executeQuery();

	            while (resultSet.next()) {
	                String maGV = resultSet.getString("ma_giang_vien");
	                String hoTen = resultSet.getString("ho_ten");
	                String ngaySinh = resultSet.getString("ngay_sinh");
	                String diaChi = resultSet.getString("dia_chi");
	                String email = resultSet.getString("email");
	                String soDienThoai = resultSet.getString("so_dien_thoai");
	                String maKhoa = resultSet.getString("ma_khoa");
	                String hocVan = resultSet.getString("hoc_van");
	                String chuyenMon = resultSet.getString("chuyen_nganh");
	                String avatar = resultSet.getString("avatar");
	                Integer trangThai = resultSet.getInt("trangThai");

	                GiangVien gv = new GiangVien(maGV, hoTen, ngaySinh, diaChi, email, soDienThoai);
	                gv.setAvatar(avatar);
	                gv.setMaKhoa1(maKhoa);
	                gv.setChuyenMon(chuyenMon);
	                gv.setHocVan(hocVan);
	                gv.setTrangThai(trangThai);

	                listGV.add(gv);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBConnection.closeResultSet(resultSet);
	            DBConnection.closePreparedStatement(preStatement);
	            DBConnection.closeConnect(jdbcConnection);
	        }
	        return listGV;
	    }
	
	public List<GiangVien> listAllGiangViens(int offset, int noOfRecords, String keyword) {
		// danh sach chua ket qua tra ve
		List<GiangVien> listGV = new ArrayList<GiangVien>();
		String sql = "SELECT * FROM giang_vien ";
		if (keyword != null && !keyword.isEmpty()) {
			sql += "WHERE ma_giang_vien LIKE ? ";
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
				String maGV = resultSet.getString("ma_giang_vien");
				String hoTen = resultSet.getString("ho_ten");
				String ngaySinh = resultSet.getString("ngay_sinh");
				String diaChi = resultSet.getString("dia_chi");
				String email = resultSet.getString("email");
				String soDienThoai = resultSet.getString("so_dien_thoai");
				String maKhoa = resultSet.getString("ma_khoa");
				String hocVan = resultSet.getString("hoc_van");
				String chuyenMon = resultSet.getString("chuyen_nganh");
				String avatar = resultSet.getString("avatar");
				
				
				// đóng gói các giá trị thuộc tính vào đối tượng Bean(Book)
				GiangVien gv = new GiangVien(maGV,hoTen,ngaySinh, diaChi,email,soDienThoai);
				
				gv.setAvatar(avatar);
				gv.setMaKhoa1(maKhoa);
				gv.setChuyenMon(chuyenMon);
				gv.setHocVan(hocVan);
				// Thêm đối tượng Bean vào danh sách
				listGV.add(gv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return listGV;
	}
	
	
	
	public GiangVien getGiangVien(String id) {
		GiangVien gv = null;
		String sql = "SELECT * FROM giang_vien WHERE ma_giang_vien= ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, id);
			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
				String maGV = resultSet.getString("ma_giang_vien");
				String hoTen = resultSet.getString("ho_ten");
				String ngaySinh = resultSet.getString("ngay_sinh");
				String diaChi = resultSet.getString("dia_chi");
				String email = resultSet.getString("email");
				String soDienThoai = resultSet.getString("so_dien_thoai");
				String avatar = resultSet.getString("avatar");
				String maKhoa = resultSet.getString("ma_khoa");
				String hoc_van = resultSet.getString("hoc_van");
				String chuyen_mon = resultSet.getString("chuyen_nganh");
				

			
				gv = new GiangVien(maGV,hoTen,ngaySinh,diaChi,email,soDienThoai,avatar,maKhoa,hoc_van,chuyen_mon);
				
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return gv;
	}
	
	public boolean insertGiangVien(GiangVien gv) {
		boolean insertResult = false;
		String sql = "INSERT INTO giang_vien(ma_giang_vien, ho_ten, ngay_sinh, dia_chi, email,so_dien_thoai,avatar,ma_khoa,hoc_van,chuyen_nganh) VALUE (?,?,?,?,?,?,?,?,?,?)";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1,gv.getMaGV() );
			preStatement.setString(2,gv.getHoTen() );
			preStatement.setString(3, gv.getNgaySinh() );
			preStatement.setString(4, gv.getDiaChi());
			preStatement.setString(5, gv.getEmail());
			preStatement.setString(6,gv.getSoDienThoai() );
			preStatement.setString(7,gv.getAvatar());
			preStatement.setString(8,gv.getMaKhoa1() );
			preStatement.setString(9,gv.getHocVan());
			preStatement.setString(10,gv.getChuyenMon());
			insertResult = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return insertResult;
	}
	
	public boolean updateGiangVien(GiangVien gv) {
	    boolean result = false;
	    String sql = "UPDATE giang_vien SET ho_ten = ?, ngay_sinh = ?, dia_chi = ?, "
	            + "email = ?, so_dien_thoai = ?, ma_khoa = ?, avatar = ?, hoc_van = ?, chuyen_nganh = ? WHERE ma_giang_vien = ?";
	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);

	    try {
	    	preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1,gv.getHoTen() );
			preStatement.setString(2, gv.getNgaySinh() );
			preStatement.setString(3, gv.getDiaChi());
			preStatement.setString(4, gv.getEmail());
			preStatement.setString(5,gv.getSoDienThoai() );
			preStatement.setString(6,gv.getMaKhoa1() );
			preStatement.setString(7,gv.getAvatar());
			preStatement.setString(8,gv.getHocVan());
			preStatement.setString(9,gv.getChuyenMon());
	        
	        preStatement.setString(10,gv.getMaGV() );
	        result = preStatement.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.closePreparedStatement(preStatement);
	        DBConnection.closeConnect(jdbcConnection);
	    }

	    return result;
	}
	
	public boolean deleteGiangVien(String gvId) {
		boolean result = false;
		// Cau lenh sql
		String sql = "DELETE FROM giang_vien WHERE ma_giang_vien = ?";

		// Tao ket noi
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);

		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, gvId);
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
		String sql = "SELECT count(ma_giang_vien) FROM giang_vien ";
		int result = 0;
		if (keyword != null && !keyword.isEmpty()) {
			sql += "WHERE ma_giang_vien LIKE ? ";
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

	public boolean updateTrangThai(String maGV, int trangThai) {
	    boolean result = false;
	    String sql = "UPDATE giang_vien SET trangThai = ? WHERE ma_giang_vien = ?";
	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);

	    try {
	        preStatement = jdbcConnection.prepareStatement(sql);
	        preStatement.setInt(1, trangThai);
	        preStatement.setString(2, maGV);
	        result = preStatement.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.closePreparedStatement(preStatement);
	        DBConnection.closeConnect(jdbcConnection);
	    }

	    return result;
	}
	public boolean updateChuyenNganh(String maGV, String chuyenNganh) {
	    boolean result = false;
	    String sql = "UPDATE giang_vien SET chuyen_nganh = ? WHERE ma_giang_vien = ?";
	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);

	    try {
	        preStatement = jdbcConnection.prepareStatement(sql);
	        preStatement.setString(1, chuyenNganh);
	        preStatement.setString(2, maGV);
	        result = preStatement.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.closePreparedStatement(preStatement);
	        DBConnection.closeConnect(jdbcConnection);
	    }

	    return result;
	}

}
