package vnua.qlsv.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import vnua.qlsv.model.SinhVienDao;
import vnua.qlsv.bean.SinhVien;

@WebServlet("/UploadStudentFileServlet")
@MultipartConfig
public class UploadStudentFileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private SinhVienDao svDAO;

    @Override
    public void init() throws ServletException {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        svDAO = new SinhVienDao(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String filePath = getServletContext().getRealPath("/uploads") + File.separator + fileName;
        filePart.write(filePath);
        String maLH = request.getParameter("maLH");

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                String maSV = getCellValue(row.getCell(0));
                String hoTen = getCellValue(row.getCell(1));
                String ngaySinh = getCellValue(row.getCell(2));
                String gioiTinh = getCellValue(row.getCell(3));
                String diaChi = getCellValue(row.getCell(4));
                String email = getCellValue(row.getCell(5));
                String soDienThoai = getCellValue(row.getCell(6));
                String maKhoa = getCellValue(row.getCell(7));
                String maChuyenNganh = getCellValue(row.getCell(8));
                String maLop = getCellValue(row.getCell(9));

                SinhVien sinhVien = new SinhVien(maSV, hoTen, ngaySinh, gioiTinh, diaChi, email, soDienThoai, maKhoa, maChuyenNganh, maLop);
                svDAO.insertStudent(sinhVien);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/viewStudentByClass?maLH=" + maLH);
    }

    private String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            default:
                return "";
        }
    }
}
