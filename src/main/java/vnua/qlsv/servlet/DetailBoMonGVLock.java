package vnua.qlsv.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

import vnua.qlsv.bean.ChuyenNganh;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.LopHoc;
import vnua.qlsv.bean.KyHoc;
import vnua.qlsv.bean.MonHoc;
import vnua.qlsv.bean.NienKhoa;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.Khoa;
import vnua.qlsv.formbean.GiangVienForm;
import vnua.qlsv.formbean.LopForm;
import vnua.qlsv.formbean.LopHocForm;
import vnua.qlsv.model.ChuyenNganhDAO;
import vnua.qlsv.model.GiangVienDAO;
import vnua.qlsv.model.KhoaDAO;
import vnua.qlsv.model.LopDAO;
import vnua.qlsv.model.KyHocDAO;
import vnua.qlsv.model.MonHocDAO;
import vnua.qlsv.model.NienKhoaDAO;
import vnua.qlsv.model.SinhVienDao;
import vnua.qlsv.model.LopHocDAO;
import vnua.qlsv.utils.MyUtil;

@WebServlet(urlPatterns = {"/detailBoMonGVLock"})
@MultipartConfig
public class DetailBoMonGVLock extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private GiangVienDAO giangVienDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		giangVienDAO = new GiangVienDAO(jdbcURL, jdbcUsername, jdbcPassword);	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
		List<String> errors = new ArrayList<String>();
		 String maGV = (String) request.getParameter("maGV");
		 
        request.setAttribute("maGV", maGV);
		RequestDispatcher rd = request.getServletContext()
				.getRequestDispatcher("/views/detailBoMonGVLockView.jsp");
		rd.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		    // Xử lý Tiếng việt cho request,reponse
		    request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    
		   
		    String maGV = (String) request.getParameter("maGV");
		    String trangThai = request.getParameter("trangThai");
		    int trangThai1 = Integer.parseInt(trangThai);
		 
		    GiangVien giangVien = giangVienDAO.getGiangVien(maGV);
		    String errors = null;
		    String succes = null;
		        
		        boolean insertResult = giangVienDAO.updateTrangThai(maGV, trangThai1);
		        if (!insertResult) {
		            errors="Chuyển trạng thái không thành công";
		        } else {
		        	succes="Đã huyển  trạng thái";
		        	request.setAttribute("errors2",succes);
		        	response.sendRedirect(request.getContextPath() + "/detailBoMonGV?maCN=" + giangVien.getChuyenMon());
		        }
		   
		    if (errors == "Chuyển lớp không thành công") {
		        request.setAttribute("errors2", String.join(", ", errors));
		
		        RequestDispatcher rd = request.getServletContext()
		                .getRequestDispatcher("/views/detailBoMonGVLockView.jsp");
		        rd.forward(request, response);
		    }
		}
}
