package vnua.qlsv.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.model.SinhVienDao;

/**
 * Servlet implementation class DetailBookServlet
 */
@WebServlet(urlPatterns = {"/detailStudent"})
public class DetailStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SinhVienDao svDAO;


	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
//		bookDAO = new BookDAO("jdbc:mysql://localhost:3306/bookstore", "root", "123456");
		svDAO = new SinhVienDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> errors = new ArrayList<String>();
		String maSV = request.getParameter("maSV");
		
	
		if (errors.isEmpty()) {
			SinhVien sv = svDAO.getStudent(maSV);
			System.out.println(sv.getAvatar());
			if (sv == null) {
				errors.add("Không có cuốn sách nào");
			} else {
				request.setAttribute("sv", sv);
				RequestDispatcher rd = request.getServletContext()
						.getRequestDispatcher("/views/detailStudentView.jsp");
				rd.forward(request, response);
			}
		}
		if(!errors.isEmpty()) {
			request.setAttribute("errors", String.join(", ", errors));
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/clientHome");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}