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

import vnua.qlsv.bean.User;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.model.UserDAO;
import vnua.qlsv.model.GiangVienDAO;
import vnua.qlsv.model.SinhVienDao;
import vnua.qlsv.utils.MyUtil;

@WebServlet(urlPatterns = {"/QLUserHome"})
public class QLUserHomeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errors = null;
		List<User> list = null;
		int page = 1;
		int recordsPerPage = 2;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		String keyword = request.getParameter("keyword");
		
		list = userDAO.listAllUsers((page - 1) * recordsPerPage, recordsPerPage, keyword);
		
		int noOfRecords = userDAO.getNoOfRecords(keyword);
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
		request.setAttribute("keyword", keyword);
		
		if (list.isEmpty()) {
			errors = "Không có giang viên nào";
		}
		request.setAttribute("errors", errors);
		request.setAttribute("userList", list);
		
		RequestDispatcher rd;
		if(keyword != null) {
			rd = this.getServletContext()
					.getRequestDispatcher("/views/userResultSearch.jsp");
		}else {
			rd = this.getServletContext()
					.getRequestDispatcher("/views/qlUserHomeView.jsp");
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
