package vnua.qlsv.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServlet;

import vnua.qlsv.bean.ClassInfo;

public class ClassInfoDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public ClassInfoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    private void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    private void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public List<ClassInfo> getClassInfoList() throws SQLException {
        List<ClassInfo> classInfoList = new ArrayList<>();

        String sql = "SELECT k.ten_khoa, l.ma_lop, l.ten_lop, gv.ho_ten AS giang_vien_chu_nhiem, " +
                     "sv_lop_truong.ho_ten AS lop_truong, sv_lop_pho.ho_ten AS lop_pho, sv_bi_thu.ho_ten AS bi_thu, " +
                     "COUNT(sv.ma_sinh_vien) AS si_so, " +
                     "SUM(CASE WHEN sv.gioi_tinh = 'Nam' THEN 1 ELSE 0 END) AS so_luong_nam, " +
                     "SUM(CASE WHEN sv.gioi_tinh = 'Nu' THEN 1 ELSE 0 END) AS so_luong_nu " +
                     "FROM lops l " +
                     "JOIN khoas k ON l.ma_khoa = k.ma_khoa " +
                     "LEFT JOIN chu_nhiem cn ON l.ma_lop = cn.ma_lop " +
                     "LEFT JOIN giang_vien gv ON cn.ma_giang_vien = gv.ma_giang_vien " +
                     "LEFT JOIN ban_can_su_lop bcs_lop_truong ON l.ma_lop = bcs_lop_truong.ma_lop AND bcs_lop_truong.role = 'Lop truong' " +
                     "LEFT JOIN sinh_vien sv_lop_truong ON bcs_lop_truong.ma_sinh_vien = sv_lop_truong.ma_sinh_vien " +
                     "LEFT JOIN ban_can_su_lop bcs_lop_pho ON l.ma_lop = bcs_lop_pho.ma_lop AND bcs_lop_pho.role = 'Lop pho' " +
                     "LEFT JOIN sinh_vien sv_lop_pho ON bcs_lop_pho.ma_sinh_vien = sv_lop_pho.ma_sinh_vien " +
                     "LEFT JOIN ban_can_su_lop bcs_bi_thu ON l.ma_lop = bcs_bi_thu.ma_lop AND bcs_bi_thu.role = 'Bi thu' " +
                     "LEFT JOIN sinh_vien sv_bi_thu ON bcs_bi_thu.ma_sinh_vien = sv_bi_thu.ma_sinh_vien " +
                     "LEFT JOIN sinh_vien sv ON l.ma_lop = sv.ma_lop " +
                     "GROUP BY k.ten_khoa, l.ma_lop, l.ten_lop, gv.ho_ten, sv_lop_truong.ho_ten, sv_lop_pho.ho_ten, sv_bi_thu.ho_ten";

        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String tenKhoa = resultSet.getString("ten_khoa");
            String maLop = resultSet.getString("ma_lop");
            String tenLop = resultSet.getString("ten_lop");
            String giangVienChuNhiem = resultSet.getString("giang_vien_chu_nhiem");
            String lopTruong = resultSet.getString("lop_truong");
            String lopPho = resultSet.getString("lop_pho");
            String biThu = resultSet.getString("bi_thu");
            int siSo = resultSet.getInt("si_so");
            int soLuongNam = resultSet.getInt("so_luong_nam");
            int soLuongNu = resultSet.getInt("so_luong_nu");

            ClassInfo classInfo = new ClassInfo(tenKhoa, maLop, tenLop, giangVienChuNhiem, lopTruong, lopPho, biThu, siSo, soLuongNam, soLuongNu);
            classInfoList.add(classInfo);
        }

        resultSet.close();
        statement.close();
        disconnect();

        return classInfoList;
    }
}
