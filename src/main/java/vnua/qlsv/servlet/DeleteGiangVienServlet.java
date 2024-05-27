package vnua.qlsv.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.qlsv.model.GiangVienDAO;

@WebServlet(urlPatterns = {"/deleteGiangVien"})
public class DeleteGiangVienServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private GiangVienDAO gvDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		gvDAO = new GiangVienDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String gvId = request.getParameter("maGV");
		boolean check = gvDAO.deleteGiangVien(gvId);
		response.sendRedirect(request.getContextPath() + "/QLGiangVienHome");
	}
}
