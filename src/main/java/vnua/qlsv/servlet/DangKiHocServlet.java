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

import vnua.qlsv.bean.DangKiHoc;
import vnua.qlsv.bean.Khoa;
import vnua.qlsv.bean.LopHoc;
import vnua.qlsv.bean.User;
import vnua.qlsv.model.DangKiHocDAO;
import vnua.qlsv.model.KhoaDAO;
import vnua.qlsv.model.LopHocDAO;

import vnua.qlsv.utils.MyUtil;

@WebServlet(urlPatterns = {"/dangKiMonHoc"})
public class DangKiHocServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private LopHocDAO lopHocDAO;
	private DangKiHocDAO dkDAO;
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		lopHocDAO = new LopHocDAO(jdbcURL, jdbcUsername, jdbcPassword);
		dkDAO = new DangKiHocDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errors = null;
		List<LopHoc> lopHocList;
		List<DangKiHoc> DKH;
		 User loginedUser = (User) request.getSession().getAttribute("loginedUser");
         int loaiNguoiDung = loginedUser.getLoaiNguoiDung();
         String userId = loginedUser.getMaDangNhap();
	
		
		lopHocList = lopHocDAO.getLopHocFromChuyenNganh(userId);
		DKH = dkDAO.getLopHocFromChuyenNganh(userId);
		if (lopHocList.isEmpty()) {
			errors = "Không có lớp học nào";
		}
		request.setAttribute("errors", errors);
		request.setAttribute("lopHocList", lopHocList);
		request.setAttribute("lopHocList1", DKH);
		
		RequestDispatcher rd;
	
			rd = this.getServletContext()
					.getRequestDispatcher("/views/dangKiMonHocView.jsp");
		
		rd.forward(request, response);
		
	}
}
