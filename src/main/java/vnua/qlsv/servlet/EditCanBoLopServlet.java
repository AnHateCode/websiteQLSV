package vnua.qlsv.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;

import java.util.Date;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.NienKhoa;
import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.formbean.BanCanSuLopForm;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.BanCanSuLop;
import vnua.qlsv.bean.ChuNhiem;
import vnua.qlsv.model.BanCanSuLopDAO;
import vnua.qlsv.model.LopDAO;
import vnua.qlsv.model.NienKhoaDAO;
import vnua.qlsv.model.GiangVienDAO;
import java.text.SimpleDateFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/editCBL"})
@MultipartConfig
public class EditCanBoLopServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private LopDAO lopDAO;
	private BanCanSuLopDAO banCanSuLopDAO;
	private NienKhoaDAO nienKhoaDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		banCanSuLopDAO = new BanCanSuLopDAO(jdbcURL, jdbcUsername, jdbcPassword);
		lopDAO = new LopDAO(jdbcURL, jdbcUsername, jdbcPassword);
		nienKhoaDAO = new NienKhoaDAO(jdbcURL, jdbcUsername, jdbcPassword);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
		List<String> errors = new ArrayList<String>();
		Lop lop;
		
		String maLH = (String) request.getParameter("maLH");
		String maSV = (String) request.getParameter("maSV");
		BanCanSuLop banCanSuLop ;
		lop = lopDAO.getLop(maLH);
		
		
		
		banCanSuLop = banCanSuLopDAO.getBCSLSV(maSV);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ngayBatDauFormatted = sdf.format(banCanSuLop.getNamBatDau());
	    String ngayKetThucFormatted = sdf.format(banCanSuLop.getNamKetThuc());
	    request.setAttribute("ngayBatDau", ngayBatDauFormatted);
        request.setAttribute("ngayKetThuc", ngayKetThucFormatted);
        request.setAttribute("lop", lop);
        request.setAttribute("maSV", maSV);
		RequestDispatcher rd = request.getServletContext()
				.getRequestDispatcher("/views/editCBLView.jsp");
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

	    Lop lop = new Lop(); 
	    lop = lopDAO.getLop(request.getParameter("maLop"));
	    SinhVien sinhVien = new SinhVien();
	    sinhVien.setMaSV(request.getParameter("maSV"));

	    NienKhoa nienKhoa = nienKhoaDAO.getNienKhoa(request.getParameter("maNienKhoa"));

	    String ngayBatDauString = request.getParameter("ngayBatDauValue");
	    String ngayKetThucString = request.getParameter("ngayKetThucValue");
	    String role = request.getParameter("role");
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date ngayBatDau = null;
	    Date ngayKetThuc = null;

	    try {
	        ngayBatDau = sdf.parse(ngayBatDauString);
	        ngayKetThuc = sdf.parse(ngayKetThucString);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        // Handle parse exception
	    }
	    
	    System.out.println(ngayBatDau);
	   BanCanSuLopForm banCanSuLopForm = new BanCanSuLopForm(lop, sinhVien, ngayBatDau, ngayKetThuc, nienKhoa, role);
	    List<String> errors = banCanSuLopForm.validateEditBookForm(nienKhoa);
	    
	  
	   boolean isExist = banCanSuLopDAO.checkRoleExistence(lop.getMaLop(), ngayBatDau, ngayKetThuc, role);
	   if(isExist) {
		   errors.add("Đã có " + role + "trong khoảng thời gian từ "+ngayBatDau+" tới " +ngayKetThuc);
	   }
	    if (errors.isEmpty()) {
	        BanCanSuLop cn = new BanCanSuLop(lop, sinhVien, ngayBatDau, ngayKetThuc, nienKhoa,role);

	        boolean insertResult = banCanSuLopDAO.updateBanCanSuLop(cn);
	        if (!insertResult) {
	            errors.add("Cập nhập Ban cán sự không thành công");
	        } else {
	        	response.sendRedirect(request.getContextPath() + "/viewStudentByClass?maLH=" + lop.getMaLop());
	        }
	    }
	    
	    String ngayBatDauFormatted = sdf.format(banCanSuLopForm.getNamBatDau());
	    String ngayKetThucFormatted = sdf.format(banCanSuLopForm.getNamKetThuc());
	    System.out.println(ngayBatDauFormatted);
	    if (!errors.isEmpty()) {
	        request.setAttribute("errors", String.join(", ", errors));
	        request.setAttribute("lop", lop);
	        request.setAttribute("maSV", sinhVien.getMaSV());
	        request.setAttribute("ngayBatDau", ngayBatDauFormatted);
	        request.setAttribute("ngayKetThuc", ngayKetThucFormatted);
	        RequestDispatcher rd = request.getServletContext()
	                .getRequestDispatcher("/views/editCBLView.jsp");
	        rd.forward(request, response);
	    }
	}
	
}
