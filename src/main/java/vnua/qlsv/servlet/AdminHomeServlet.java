package vnua.qlsv.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.model.SinhVienDao;
import vnua.qlsv.utils.MyUtil;


@WebServlet(urlPatterns = {"/adminHome"})
public class AdminHomeServlet extends HttpServlet {
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
		String errors = null;
		List<SinhVien> list = null;
		int page = 1;
		int recordsPerPage = 3;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		String keyword = request.getParameter("keyword");
		
	
		
		list = sinhVienDAO.listAllStudents((page - 1) * recordsPerPage, recordsPerPage, keyword);
		
		int noOfRecords = sinhVienDAO.getNoOfRecords(keyword);
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		
		
		// Lưu thông tin vào request attribute trước khi forward sang views
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				request.setAttribute("keyword", keyword);
				
		if (list.isEmpty()) {
			errors = "Không có sinh viên nào";
		}
		
		request.setAttribute("errors", errors);
		request.setAttribute("svList", list);
		
		RequestDispatcher rd;
		if(keyword != null) {
			rd = this.getServletContext()
					.getRequestDispatcher("/views/studentResultSearch.jsp");
		}else {
			rd = this.getServletContext()
					.getRequestDispatcher("/views/adminHomeView.jsp");
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private boolean validateDate(String fromDate, String toDate) {
		if (fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()) {
			return true;
		}
		return false;
	}
}