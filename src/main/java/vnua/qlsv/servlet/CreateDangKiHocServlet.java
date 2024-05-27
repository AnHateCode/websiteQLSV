package vnua.qlsv.servlet;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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

import vnua.qlsv.bean.DangKiHoc;
import vnua.qlsv.bean.LopHoc;
import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.bean.User;
import vnua.qlsv.model.DangKiHocDAO;
import vnua.qlsv.model.GiangVienDAO;
import vnua.qlsv.model.LopHocDAO;

@WebServlet(urlPatterns = {"/createDangKiHoc"})
@MultipartConfig
public class CreateDangKiHocServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private DangKiHocDAO dkDAO;
	private LopHocDAO lopHocDAO;
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		dkDAO = new DangKiHocDAO(jdbcURL, jdbcUsername, jdbcPassword);
		lopHocDAO = new LopHocDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errors = "";
		
		User loginedUser = (User) request.getSession().getAttribute("loginedUser");
         int loaiNguoiDung = loginedUser.getLoaiNguoiDung();
         String userId = loginedUser.getMaDangNhap();
         String maLopHoc = request.getParameter("maLH");
         List<LopHoc> lopHocList;
         Boolean isExist = dkDAO.isDangKi(userId, maLopHoc);
         
         lopHocList = lopHocDAO.getLopHocFromChuyenNganh(userId);
         if(isExist) {
        	 
        	 errors ="Đã đăng kí môn này";
        	 request.setAttribute("error", errors);
         }
         else {
        	 DangKiHoc dkh = new DangKiHoc();
        	 
        	 SinhVien sv = new SinhVien();
        	 sv.setMaSV(userId);
        	 
        	 LopHoc lh = new LopHoc();
        	 lh.setMaLopHoc(maLopHoc);
        	 
        	 dkh.setSinhVien(sv);
        	 dkh.setLopHoc(lh);
        	 
        	 dkDAO.insertDangKiHoc(dkh);
        	 response.sendRedirect(request.getContextPath() + "/dangKiMonHoc");
         }
         
         if (!errors.isEmpty()) {
 			request.setAttribute("errors", errors);
 			request.setAttribute("lopHocList", lopHocList);
 			RequestDispatcher rd = request.getServletContext()
 					.getRequestDispatcher("/views/dangKiMonHocView.jsp");
 			rd.forward(request, response);
 		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
		String errors = null;
		
		User loginedUser = (User) request.getSession().getAttribute("loginedUser");
         int loaiNguoiDung = loginedUser.getLoaiNguoiDung();
         String userId = loginedUser.getMaDangNhap();
         String maLopHoc = request.getParameter("maLH");
         List<LopHoc> lopHocList;
         Boolean isExist = dkDAO.isDangKi(userId, maLopHoc);
         
         lopHocList = lopHocDAO.getLopHocFromChuyenNganh(userId);
         if(!isExist) {
        	 
        	 errors ="Đã đăng kí môn này";
        	 request.setAttribute("error", errors);
         }
         else {
        	 DangKiHoc dkh = new DangKiHoc();
        	 
        	 SinhVien sv = new SinhVien();
        	 sv.setMaSV(userId);
        	 
        	 LopHoc lh = new LopHoc();
        	 lh.setMaLopHoc(maLopHoc);
        	 
        	 dkh.setSinhVien(sv);
        	 dkh.setLopHoc(lh);
        	 
        	 dkDAO.insertDangKiHoc(dkh);
        	 response.sendRedirect(request.getContextPath() + "/dangKiMonHocHome");
         }
         
         if (!errors.isEmpty()) {
 			request.setAttribute("errors", String.join(", ", errors));
 			request.setAttribute("lopHocList", lopHocList);
 			RequestDispatcher rd = request.getServletContext()
 					.getRequestDispatcher("/views/dangKiMonHocView.jsp");
 			rd.forward(request, response);
 		}
         
}
}
