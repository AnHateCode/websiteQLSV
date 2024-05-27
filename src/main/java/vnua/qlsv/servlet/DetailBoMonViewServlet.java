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

@WebServlet(urlPatterns = {"/detailBoMon"})
public class DetailBoMonViewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String maCN = request.getParameter("maCN");
			String tenCN = request.getParameter("tenCN");
			
			request.setAttribute("maCN", maCN);
			request.setAttribute("tenCN", tenCN);
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/views/detailBoMonView.jsp");
			rd.forward(request, response);
	}
}
