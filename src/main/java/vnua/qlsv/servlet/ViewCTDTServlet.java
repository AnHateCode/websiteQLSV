package vnua.qlsv.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.bean.User;
import vnua.qlsv.formbean.LoginForm;
import vnua.qlsv.model.AdminDAO;
import vnua.qlsv.model.DBConnection;
import vnua.qlsv.model.GiangVienDAO;
import vnua.qlsv.bean.Admin;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.MonHoc;
import vnua.qlsv.model.MonHocDAO;

import vnua.qlsv.model.SinhVienDao;
import vnua.qlsv.model.UserDAO;
import vnua.qlsv.utils.Constant;
import vnua.qlsv.utils.MyUtil;

@WebServlet(urlPatterns = {"/viewCTDT"})
public class ViewCTDTServlet extends HttpServlet{

	private SinhVienDao svDAO;
	private MonHocDAO mhDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
//		bookDAO = new BookDAO("jdbc:mysql://localhost:3306/bookstore", "root", "123456");
		svDAO = new SinhVienDao(jdbcURL, jdbcUsername, jdbcPassword);
		mhDAO = new MonHocDAO(jdbcURL, jdbcUsername, jdbcPassword);
		
	}
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
		 					List<MonHoc> list = null;	 
		 					User loginedUser = (User) request.getSession().getAttribute("loginedUser");
		 					int loaiNguoiDung = loginedUser.getLoaiNguoiDung();
		 					String userId = loginedUser.getMaDangNhap();
		 					SinhVien sv = svDAO.getStudent(userId);
		 					System.out.println(sv.getMa_chuyen_nganh());
		 					try {
								list = mhDAO.getAllMonHoc(sv.getMa_chuyen_nganh());
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		 					
		 					
		 					request.setAttribute("sv",sv);
		 					request.setAttribute("listMonHoc",list);
	  
	       
	         RequestDispatcher dispatcher = request.getRequestDispatcher("/views/ctdtView.jsp");
	         dispatcher.forward(request, response);
	    }
}
