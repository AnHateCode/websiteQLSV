package vnua.qlsv.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.qlsv.bean.Khoa;
import vnua.qlsv.bean.LopHoc;
import vnua.qlsv.model.KhoaDAO;
import vnua.qlsv.model.LopHocDAO;

import vnua.qlsv.utils.MyUtil;

@WebServlet(urlPatterns = {"/QLLopHoc"})
public class QLLopHocHomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private LopHocDAO lopHocDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		lopHocDAO = new LopHocDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errors = null;
		List<LopHoc> lopHocList;
		int page = 1;
		int recordsPerPage = 2;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		String keyword = request.getParameter("keyword");
		
		lopHocList = lopHocDAO.getAllLopHoc((page - 1) * recordsPerPage, recordsPerPage, keyword);
		
		int noOfRecords = lopHocDAO.getNoOfRecords(keyword);
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
		request.setAttribute("keyword", keyword);
		
		if (lopHocList.isEmpty()) {
			errors = "Không có lớp học nào";
		}
		request.setAttribute("errors", errors);
		request.setAttribute("lopHocList", lopHocList);
		
		RequestDispatcher rd;
		if(keyword != null) {
			rd = this.getServletContext()
					.getRequestDispatcher("/views/lophocResultSearch.jsp");
		}else {
			rd = this.getServletContext()
					.getRequestDispatcher("/views/qlLopHocHomeView.jsp");
		}
		
		rd.forward(request, response);
		
	}
}
