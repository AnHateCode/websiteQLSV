package vnua.qlsv.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.qlsv.bean.ClassStats;
import vnua.qlsv.model.SinhVienDao;

@WebServlet(urlPatterns = {"/thongKeTrangThaiSinhVien"})
public class ThongKeTrangThaiSinhVienServlet extends HttpServlet{

	 private static final long serialVersionUID = 1L;
	    private SinhVienDao sinhVienDAO;

	    public void init() {
	        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
	        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
	        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
	        sinhVienDAO = new SinhVienDao(jdbcURL, jdbcUsername, jdbcPassword);
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String maNienKhoa = request.getParameter("maNienKhoa");

	        try {
	            List<ClassStats> classStatsList = sinhVienDAO.getClassStatsByNienKhoa(maNienKhoa);

	            request.setAttribute("classStatsList", classStatsList);

	            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/thongKeTrangThaiSinhVien.jsp");
	            dispatcher.forward(request, response);
	        } catch (SQLException e) {
	            throw new ServletException(e);
	        }
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }
}
