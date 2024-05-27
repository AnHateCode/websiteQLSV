package vnua.qlsv.servlet;

import java.io.File;
import java.io.IOException;
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

import vnua.qlsv.bean.User;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.model.UserDAO;
import vnua.qlsv.model.GiangVienDAO;
import vnua.qlsv.model.SinhVienDao;
import vnua.qlsv.utils.MyUtil;
import vnua.qlsv.formbean.GiangVienForm;
import vnua.qlsv.formbean.UserForm;

@WebServlet(urlPatterns = {"/createUser"})
@MultipartConfig
public class CreateUserServlet extends HttpServlet {

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
		RequestDispatcher rd = request.getServletContext()
				.getRequestDispatcher("/views/createUserView.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Xử lý Tiếng việt cho request,reponse
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		String maDangNhap = request.getParameter("maDangNhap");
		String tenDangNhap = request.getParameter("tenDangNhap");
		String matKhau = request.getParameter("matKhau");
		String loaiNguoiDung = request.getParameter("loaiNguoiDung");
		
		int role = Integer.parseInt(loaiNguoiDung);
	
		UserForm userForm = new UserForm(maDangNhap, tenDangNhap, matKhau, role);
		
		List<String> errors = userForm.validate();
		if (errors.isEmpty()) {
			User user = new User(maDangNhap, tenDangNhap, matKhau, role);
			
			boolean insertResult = userDAO.insertUser(user);
			if (!insertResult) {
				errors.add("Thêm user không thành công");
			} else {
				response.sendRedirect(request.getContextPath() + "/QLUserHome");
			}
		}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", String.join(", ", errors));
			request.setAttribute("user", userForm);
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/views/createUserView.jsp");
			rd.forward(request, response);
		}
	}
	
}
