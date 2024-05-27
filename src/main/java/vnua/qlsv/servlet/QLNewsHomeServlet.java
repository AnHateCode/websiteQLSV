package vnua.qlsv.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import vnua.qlsv.bean.ChuyenNganh;
import vnua.qlsv.bean.Khoa;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.News;
import vnua.qlsv.bean.NienKhoa;
import vnua.qlsv.model.NewsDAO;

@WebServlet(urlPatterns = {"/QLNewsHome"})
public class QLNewsHomeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private NewsDAO newsDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		newsDAO = new NewsDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errors = null;
		List<News> newsList;
		
		
		int page = 1;
		int recordsPerPage = 4;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		String keyword = request.getParameter("keyword");
		String loaiTin = request.getParameter("loaiTin");
		newsList = newsDAO.getAllLop((page - 1) * recordsPerPage, recordsPerPage, keyword,loaiTin);
		int noOfRecords = newsDAO.getNoOfRecords(keyword);
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
		request.setAttribute("keyword", keyword);
		
		if (newsList.isEmpty()) {
			errors = "Không tìm được bài đăng nào";
		}
		request.setAttribute("errors", errors);
		request.setAttribute("newsList", newsList);
		
		RequestDispatcher rd;
		if(keyword != null || loaiTin != null) {
			rd = this.getServletContext()
					.getRequestDispatcher("/views/filterNewsResult.jsp");
		}else {
			rd = this.getServletContext()
					.getRequestDispatcher("/views/qlNewsHomeView.jsp");
		}
		
		rd.forward(request, response);
		
	}
}
