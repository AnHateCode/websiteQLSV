package vnua.qlsv.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.qlsv.model.GiangVienDAO;
import vnua.qlsv.model.UserDAO;

@WebServlet(urlPatterns = {"/deleteUser"})
public class DeleteUserServlet extends HttpServlet {

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

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("maUser");
		boolean check = userDAO.deleteUser(userId);
		response.sendRedirect(request.getContextPath() + "/QLUserHome");
	}
}
