package vnua.qlsv.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.qlsv.model.LopHocDAO;
import vnua.qlsv.model.SinhVienDao;

@WebServlet(urlPatterns = {"/deleteLopHoc"})
public class DeleteLopHocServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private LopHocDAO lopHocDAO;

	public DeleteLopHocServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
//		bookDAO = new BookDAO("jdbc:mysql://localhost:3306/bookstore", "root", "123456");
		lopHocDAO = new LopHocDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String lhId = request.getParameter("maLopHoc");
		boolean check = lopHocDAO.deleteLopHoc(lhId);
		response.sendRedirect(request.getContextPath() + "/QLLopHoc");
	}
}
