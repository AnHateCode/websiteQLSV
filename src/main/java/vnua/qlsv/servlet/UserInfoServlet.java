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

import vnua.qlsv.bean.Admin;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.bean.User;
import vnua.qlsv.formbean.LoginForm;
import vnua.qlsv.model.AdminDAO;
import vnua.qlsv.model.DBConnection;
import vnua.qlsv.model.GiangVienDAO;
import vnua.qlsv.model.SinhVienDao;
import vnua.qlsv.model.UserDAO;
import vnua.qlsv.utils.Constant;
import vnua.qlsv.utils.MyUtil;


@WebServlet(urlPatterns = {"/userInfor"})
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private SinhVienDao svDAO;
	private GiangVienDAO gvDAO;
	private AdminDAO adminDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
//		bookDAO = new BookDAO("jdbc:mysql://localhost:3306/bookstore", "root", "123456");
		svDAO = new SinhVienDao(jdbcURL, jdbcUsername, jdbcPassword);
		gvDAO = new GiangVienDAO(jdbcURL, jdbcUsername, jdbcPassword);
		adminDAO = new AdminDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	 User loginedUser = (User) request.getSession().getAttribute("loginedUser");
         int loaiNguoiDung = loginedUser.getLoaiNguoiDung();
         String userId = loginedUser.getMaDangNhap();
         
         if (loaiNguoiDung == 2) {
           Admin admin =  adminDAO.getAdmin(userId);
           request.setAttribute("userInfo", admin);
         } else if(loaiNguoiDung == 1) {
        	 SinhVien cc = svDAO.getStudent(userId);
            request.setAttribute("userInfo",cc);
         } else {
            GiangVien gv = gvDAO.getGiangVien(userId);
            request.setAttribute("userInfo", gv);
         } 
       
         RequestDispatcher dispatcher = request.getRequestDispatcher("/views/userInfor.jsp");
         dispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
       
}
}

	
	

