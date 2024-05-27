package vnua.qlsv.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.qlsv.model.SinhVienDao;

/**
 * Servlet implementation class DeleteBookServlet
 */
@WebServlet(urlPatterns = {"/deleteStudent"})
public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SinhVienDao svDAO;

	public DeleteStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
//		bookDAO = new BookDAO("jdbc:mysql://localhost:3306/bookstore", "root", "123456");
		svDAO = new SinhVienDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String svId = request.getParameter("maLH");
		boolean check = svDAO.deleteStudent(svId);
		response.sendRedirect(request.getContextPath() + "/adminHome");
	}

}
