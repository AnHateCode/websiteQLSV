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
import vnua.qlsv.model.KhoaDAO;

import vnua.qlsv.utils.MyUtil;

@WebServlet(urlPatterns = {"/QLKhoaHome"})
public class QLKhoaHomeServlet  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private KhoaDAO khoaDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		khoaDAO = new KhoaDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 List<Khoa> khoaList;
		 String errors = null;
		 int page = 1;
			int recordsPerPage = 5;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}

			String keyword = request.getParameter("keyword");
			
			
			khoaList = khoaDAO.getKhoaChuyenNganhList((page - 1) * recordsPerPage, recordsPerPage, keyword);
			int noOfRecords = khoaDAO.getNoOfRecords(keyword);
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("keyword", keyword);
		
			 if (khoaList.isEmpty()) {
				errors = "Không có giang viên nào";
	}
			request.setAttribute("errors", errors);
			request.setAttribute("khoaList", khoaList);
			
			RequestDispatcher rd;
			if(keyword != null) {
				rd = this.getServletContext()
						.getRequestDispatcher("/views/khoaResultSearch.jsp");
			}else {
				rd = this.getServletContext()
						.getRequestDispatcher("/views/qlKhoaHomeView.jsp");
			}
			
			rd.forward(request, response);
				
	}
	
}
