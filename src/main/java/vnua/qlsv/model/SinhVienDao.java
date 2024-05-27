package vnua.qlsv.model;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import vnua.qlsv.utils.MyUtil;
import vnua.qlsv.bean.ClassStats;
import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.model.DBConnection;

public class SinhVienDao {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;

	public SinhVienDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	public List<SinhVien> listAllStudents() {
		// danh sach chua ket qua tra ve
		List<SinhVien> listSV = new ArrayList<SinhVien>();

		// cau lenh sql
		String sql = "SELECT * FROM	sinh_vien";

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
				String maSV = resultSet.getString("ma_sinh_vien");
				String hoTen = resultSet.getString("ho_ten");
				String ngaySinh = resultSet.getString("ngay_sinh");
				String gioiTinh = resultSet.getString("gioi_tinh");
				String diaChi = resultSet.getString("dia_chi");
				String email = resultSet.getString("email");
				String soDienThoai = resultSet.getString("so_dien_thoai");
				String maKhoa = resultSet.getString("ma_khoa");
				String maChuyenNganh = resultSet.getString("ma_chuyen_nganh");
				String maLop = resultSet.getString("ma_lop");
				String avatar = resultSet.getString("avatar");
				
				
				// đóng gói các giá trị thuộc tính vào đối tượng Bean(Book)
				SinhVien sv = new SinhVien(maSV,hoTen,ngaySinh, gioiTinh,diaChi,email);
				sv.setMa_khoa(maKhoa);
				sv.setSoDienThoai(soDienThoai);
				sv.setMa_chuyen_nganh(maChuyenNganh);
				sv.setMa_lop(maLop);
				sv.setAvatar(avatar);
			

				// Thêm đối tượng Bean vào danh sách
				listSV.add(sv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return listSV;
	}
	
	public List<SinhVien> listAllStudents(int offset, int noOfRecords, String keyword) {
		List<SinhVien> listSV = new ArrayList<SinhVien>();
		String sql = "SELECT * FROM sinh_vien ";
		if (keyword != null && !keyword.isEmpty()) {
			sql += "WHERE ma_sinh_vien LIKE ? ";
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
				String maSV = resultSet.getString("ma_sinh_vien");
				String hoTen = resultSet.getString("ho_ten");
				String ngaySinh = resultSet.getString("ngay_sinh");
				String gioiTinh = resultSet.getString("gioi_tinh");
				String diaChi = resultSet.getString("dia_chi");
				String email = resultSet.getString("email");
				String soDienThoai = resultSet.getString("so_dien_thoai");
				String maKhoa = resultSet.getString("ma_khoa");
				String maChuyenNganh = resultSet.getString("ma_chuyen_nganh");
				String maLop = resultSet.getString("ma_lop");
				String avatar = resultSet.getString("avatar");
				
				
				// đóng gói các giá trị thuộc tính vào đối tượng Bean(Book)
				SinhVien sv = new SinhVien(maSV,hoTen,ngaySinh, gioiTinh,diaChi,email);
				sv.setMa_khoa(maKhoa);
				sv.setSoDienThoai(soDienThoai);
				sv.setMa_chuyen_nganh(maChuyenNganh);
				sv.setMa_lop(maLop);
				sv.setAvatar(avatar);
			

				// Thêm đối tượng Bean vào danh sách
				listSV.add(sv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return listSV;
	}
	
	public List<SinhVien> listAllStudents(String maLop1) {
		List<SinhVien> listSV = new ArrayList<SinhVien>();
		String sql = "SELECT * FROM sinh_vien WHERE ma_lop = ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, maLop1);
			resultSet = preStatement.executeQuery();
			while (resultSet.next()) {
				String maSV = resultSet.getString("ma_sinh_vien");
				String hoTen = resultSet.getString("ho_ten");
				String ngaySinh = resultSet.getString("ngay_sinh");
				String gioiTinh = resultSet.getString("gioi_tinh");
				String diaChi = resultSet.getString("dia_chi");
				String email = resultSet.getString("email");
				String soDienThoai = resultSet.getString("so_dien_thoai");
				String maKhoa = resultSet.getString("ma_khoa");
				String maChuyenNganh = resultSet.getString("ma_chuyen_nganh");
				String maLop = resultSet.getString("ma_lop");
				String avatar = resultSet.getString("avatar");
				Integer trangThai = resultSet.getInt("trang_thai");
				
				// đóng gói các giá trị thuộc tính vào đối tượng Bean(Book)
				SinhVien sv = new SinhVien(maSV,hoTen,ngaySinh, gioiTinh,diaChi,email);
				sv.setMa_khoa(maKhoa);
				sv.setSoDienThoai(soDienThoai);
				sv.setMa_chuyen_nganh(maChuyenNganh);
				sv.setMa_lop(maLop);
				sv.setAvatar(avatar);
				sv.setTrangThai(trangThai);
				

				// Thêm đối tượng Bean vào danh sách
				listSV.add(sv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return listSV;
	}
	public SinhVien getStudent(String id) {
		SinhVien sv = null;
		String sql = "SELECT * FROM sinh_vien WHERE ma_sinh_vien = ?";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, id);
			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
				String maSV = resultSet.getString("ma_sinh_vien");
				String hoTen = resultSet.getString("ho_ten");
				String ngaySinh = resultSet.getString("ngay_sinh");
				String gioiTinh = resultSet.getString("gioi_tinh");
				String diaChi = resultSet.getString("dia_chi");
				String email = resultSet.getString("email");
				String soDienThoai = resultSet.getString("so_dien_thoai");
				String avatar = resultSet.getString("avatar");
				String maKhoa = resultSet.getString("ma_khoa");
				String maChuyenNganh = resultSet.getString("ma_chuyen_nganh");
				String maLop = resultSet.getString("ma_lop");
				

				// đóng gói các giá trị thuộc tính vào đối tượng Bean(Book)
				sv = new SinhVien(maSV, hoTen,ngaySinh,gioiTinh,diaChi,email,soDienThoai,avatar,maKhoa,maChuyenNganh,maLop);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return sv;
	}
	
	
	public boolean insertStudent(SinhVien sv) {
		boolean insertResult = false;
		String sql = "INSERT INTO sinh_vien(ma_sinh_vien, ho_ten, ngay_sinh, gioi_tinh, dia_chi, email,so_dien_thoai,avatar,ma_khoa,ma_chuyen_nganh,ma_lop) VALUE (?,?,?,?,?,?,?,?,?,?,?)";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1,sv.getMaSV() );
			preStatement.setString(2,sv.getHoTen() );
			preStatement.setString(3, sv.getNgaySinh() );
			preStatement.setString(4,sv.getGioiTinh() );
			preStatement.setString(5, sv.getDiaChi());
			preStatement.setString(6, sv.getEmail());
			preStatement.setString(7,sv.getSoDienThoai() );
			preStatement.setString(8,sv.getAvatar());
			preStatement.setString(9,sv.getMa_khoa() );
			preStatement.setString(10,sv.getMa_chuyen_nganh() );
			preStatement.setString(11,sv.getMa_lop() );
			insertResult = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return insertResult;
	}
	public boolean insertStudent2(SinhVien sv) {
		boolean insertResult = false;
		String sql = "INSERT INTO sinh_vien(ma_sinh_vien, ho_ten, ngay_sinh, gioi_tinh, dia_chi, email,so_dien_thoai,ma_khoa,ma_chuyen_nganh,ma_lop) VALUE (?,?,?,?,?,?,?,?,?,?,?)";
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1,sv.getMaSV() );
			preStatement.setString(2,sv.getHoTen() );
			preStatement.setString(3, sv.getNgaySinh() );
			preStatement.setString(4,sv.getGioiTinh() );
			preStatement.setString(5, sv.getDiaChi());
			preStatement.setString(6, sv.getEmail());
			preStatement.setString(7,sv.getSoDienThoai() );
			preStatement.setString(8,sv.getMa_khoa() );
			preStatement.setString(9,sv.getMa_chuyen_nganh() );
			preStatement.setString(10,sv.getMa_lop() );
			insertResult = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return insertResult;
	}
	
	public boolean updateStudent(SinhVien sv) {
	    boolean result = false;
	    String sql = "UPDATE sinh_vien SET ho_ten = ?, ngay_sinh = ?, gioi_tinh = ?, dia_chi = ?, "
	            + "email = ?, so_dien_thoai = ?, ma_khoa = ?, avatar = ?, ma_chuyen_nganh = ?, ma_lop = ? WHERE ma_sinh_vien = ?";
	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);

	    try {
	        preStatement = jdbcConnection.prepareStatement(sql);
	        preStatement.setString(1, sv.getHoTen());
	        preStatement.setString(2, sv.getNgaySinh());
	        preStatement.setString(3, sv.getGioiTinh());
	        preStatement.setString(4, sv.getDiaChi());
	        preStatement.setString(5, sv.getEmail());
	        preStatement.setString(6, sv.getSoDienThoai());
	        preStatement.setString(7, sv.getMa_khoa());
	        preStatement.setString(8, sv.getAvatar());
	        preStatement.setString(9, sv.getMa_chuyen_nganh());
	        preStatement.setString(10, sv.getMa_lop());
	        
	        preStatement.setString(11, sv.getMaSV());
	        result = preStatement.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.closePreparedStatement(preStatement);
	        DBConnection.closeConnect(jdbcConnection);
	    }

	    return result;
	}

	public boolean deleteStudent(String svId) {
		boolean result = false;
		// Cau lenh sql
		String sql = "DELETE FROM sinh_vien WHERE ma_sinh_vien = ?";

		// Tao ket noi
		jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);

		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, svId);
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
		String sql = "SELECT count(ma_sinh_vien) FROM sinh_vien ";
		int result = 0;
		if (keyword != null && !keyword.isEmpty()) {
			sql += "WHERE ma_sinh_vien LIKE ? ";
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
	
	public boolean changeStudentClass(String maSinhVien, String maLopMoi) {
	    boolean result = false;
	    String sql = "UPDATE sinh_vien SET ma_lop = ? WHERE ma_sinh_vien = ?";
	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);

	    try {
	        preStatement = jdbcConnection.prepareStatement(sql);
	        preStatement.setString(1, maLopMoi);
	        preStatement.setString(2, maSinhVien);
	        
	        result = preStatement.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.closePreparedStatement(preStatement);
	        DBConnection.closeConnect(jdbcConnection);
	    }

	    return result;
	}

	public boolean updateStudentStatus(String maSinhVien, int trangThaiMoi) {
	    boolean result = false;
	    String sql = "UPDATE sinh_vien SET trang_thai = ? WHERE ma_sinh_vien = ?";
	    jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);

	    try {
	        preStatement = jdbcConnection.prepareStatement(sql);
	        preStatement.setInt(1, trangThaiMoi);
	        preStatement.setString(2, maSinhVien);
	        
	        result = preStatement.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.closePreparedStatement(preStatement);
	        DBConnection.closeConnect(jdbcConnection);
	    }

	    return result;
	}

	  public List<ClassStats> getClassStatsByNienKhoa(String maNienKhoa) throws SQLException {
	        List<ClassStats> classStatsList = new ArrayList<>();

	        String sql = "SELECT l.ma_lop, l.ten_lop, " +
	                     "SUM(CASE WHEN sv.trang_thai = 1 THEN 1 ELSE 0 END) as nghi_hoc_count, " +
	                     "SUM(CASE WHEN sv.trang_thai = 2 THEN 1 ELSE 0 END) as bao_luu_count, " +
	                     "SUM(CASE WHEN sv.trang_thai = 3 THEN 1 ELSE 0 END) as duoi_hoc_count " +
	                     "FROM lops l " +
	                     "LEFT JOIN sinh_vien sv ON l.ma_lop = sv.ma_lop " +
	                     "WHERE l.ma_nien_khoa = ? " +
	                     "GROUP BY l.ma_lop, l.ten_lop";

	        jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);
	        try {
	            preStatement = jdbcConnection.prepareStatement(sql);
	            preStatement.setString(1, maNienKhoa);

	            resultSet = preStatement.executeQuery();
	            while (resultSet.next()) {
	                String maLop = resultSet.getString("ma_lop");
	                String tenLop = resultSet.getString("ten_lop");
	                int nghiHocCount = resultSet.getInt("nghi_hoc_count");
	                int baoLuuCount = resultSet.getInt("bao_luu_count");
	                int duoiHocCount = resultSet.getInt("duoi_hoc_count");

	                ClassStats classStats = new ClassStats(maLop, tenLop, nghiHocCount, baoLuuCount,duoiHocCount);
	                classStatsList.add(classStats);
	            }
	        } finally {
	            DBConnection.closeResultSet(resultSet);
	            DBConnection.closePreparedStatement(preStatement);
	            DBConnection.closeConnect(jdbcConnection);
	        }
	        return classStatsList;
	    }
	
	
}
