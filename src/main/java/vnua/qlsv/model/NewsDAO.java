package vnua.qlsv.model;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vnua.qlsv.bean.ChuyenNganh;
import vnua.qlsv.bean.Khoa;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.News;
import vnua.qlsv.bean.NienKhoa;
public class NewsDAO {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	
	public NewsDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	public List<News> getAllLop(int offset, int noOfRecords, String keyword,String loaiTinTuc) {
		// danh sach chua ket qua tra ve
		List<News> newsList = new ArrayList<>();

		// cau lenh sql
		String sql = "SELECT * FROM news WHERE 1=1"; // WHERE 1=1 để bắt đầu câu lệnh SQL với điều kiện luôn đúng

	    if (keyword != null && !keyword.isEmpty()) {
	        sql += " AND tieu_de LIKE ?";
	    }

	    if (loaiTinTuc != null && !loaiTinTuc.isEmpty()) {
	        sql += " AND loai_tin LIKE ?";
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
			if (loaiTinTuc != null && !loaiTinTuc.isEmpty()) {
	            preStatement.setString(++index, "%" + loaiTinTuc + "%");
	        }
	        
			preStatement.setInt(++index, offset); // vị trí bắt đầu lấy
			preStatement.setInt(++index, noOfRecords); // số bản ghi lấy ra

			resultSet = preStatement.executeQuery();
			while (resultSet.next()) {
				 Integer id = resultSet.getInt("id");
	                String anhBia = resultSet.getString("anh_bia");
	                java.util.Date ngayTao = resultSet.getDate("ngay_tao");
	                String tieuDe = resultSet.getString("tieu_de");
	                String noiDung = resultSet.getString("noi_dung");
	                String loaiTin = resultSet.getString("loai_tin");
			       News news = new News(id, anhBia, ngayTao, tieuDe, noiDung, loaiTin);
			        newsList.add(news);
			    
		}	 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeResultSet(resultSet);
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
		return newsList;
	}
	
	 // Phương thức thêm một tin tức mới
    public boolean addNews(News news) throws SQLException {
    	boolean rowInserted = false;
        String sql = "INSERT INTO news (anh_bia, ngay_tao, tieu_de, noi_dung, loai_tin) VALUES (?, ?, ?, ?, ?)";
        jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername,
				jdbcPassword);
        try {
        	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, news.getAnhBia());
            statement.setDate(2, new java.sql.Date(news.getNgayTao().getTime()));
            statement.setString(3, news.getTieuDe());
            statement.setString(4, news.getNoiDung());
            statement.setString(5, news.getLoaiTin());
            rowInserted = statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.closePreparedStatement(preStatement);
			DBConnection.closeConnect(jdbcConnection);
		}
        return rowInserted;
    }

 // Phương thức cập nhật tin tức theo id
    public boolean updateNews(News news) {
        boolean rowUpdated = false;
        String sql = "UPDATE news SET anh_bia=?, tieu_de=?, noi_dung=?, loai_tin=? WHERE id=?";
        try {
            jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, news.getAnhBia());
            statement.setString(2, news.getTieuDe());
            statement.setString(3, news.getNoiDung());
            statement.setString(4, news.getLoaiTin());
            statement.setInt(5, news.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(jdbcConnection);
        }
        return rowUpdated;
    }

    // Phương thức xóa tin tức theo id
    public boolean deleteNews(String id) {
        boolean rowDeleted = false;
        String sql = "DELETE FROM news WHERE id=?";
        try {
            jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(jdbcConnection);
        }
        return rowDeleted;
    }
    
    public int getNoOfRecords(String keyword) {
		String sql = "SELECT count(tieu_de) FROM news ";
		int result = 0;
		if (keyword != null && !keyword.isEmpty()) {
			sql += "WHERE tieu_de LIKE ? ";
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
    
    public News getNewsById(Integer id) {
        News news = null;
        String sql = "SELECT * FROM news WHERE id = ?";
        try {
            jdbcConnection = DBConnection.createConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String anhBia = resultSet.getString("anh_bia");
                java.util.Date ngayTao = resultSet.getDate("ngay_tao");
                String tieuDe = resultSet.getString("tieu_de");
                String noiDung = resultSet.getString("noi_dung");
                String loaiTin = resultSet.getString("loai_tin");
                news = new News(id, anhBia, ngayTao, tieuDe, noiDung, loaiTin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnect(jdbcConnection);
        }
        return news;
    }

}
