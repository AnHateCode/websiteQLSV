package vnua.qlsv.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import vnua.qlsv.formbean.ChuNhiemForm;
import vnua.qlsv.bean.NienKhoa;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.ChuNhiem;
import vnua.qlsv.model.ChuNhiemDAO;
import vnua.qlsv.model.LopDAO;
import vnua.qlsv.model.NienKhoaDAO;
import vnua.qlsv.model.GiangVienDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/editGVCN"})
@MultipartConfig
public class EditGVCNServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private LopDAO lopDAO;
	private ChuNhiemDAO chuNhiemDAO;
	private NienKhoaDAO nienKhoaDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		chuNhiemDAO = new ChuNhiemDAO(jdbcURL, jdbcUsername, jdbcPassword);
		lopDAO = new LopDAO(jdbcURL, jdbcUsername, jdbcPassword);
		nienKhoaDAO = new NienKhoaDAO(jdbcURL, jdbcUsername, jdbcPassword);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
		List<String> errors = new ArrayList<String>();
		Lop lop;
		
		String maLH = (String) request.getParameter("maLH");
		String maGV = (String) request.getParameter("maGV");
		ChuNhiem chuNhiem;
		lop = lopDAO.getLop(maLH);
		
		chuNhiem = chuNhiemDAO.getChuNhiemGV(maGV);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ngayBatDauFormatted = sdf.format(chuNhiem.getNamBatDau());
	    String ngayKetThucFormatted = sdf.format(chuNhiem.getNamKetThuc());
	    
	    request.setAttribute("ngayBatDau", ngayBatDauFormatted);
        request.setAttribute("ngayKetThuc", ngayKetThucFormatted);
        request.setAttribute("lop", lop);
        request.setAttribute("maGV", maGV);
		
		RequestDispatcher rd = request.getServletContext()
				.getRequestDispatcher("/views/editGVCNView.jsp");
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
	    GiangVien giangVien = new GiangVien();
	    giangVien.setMaGV(request.getParameter("maGV"));

	    NienKhoa nienKhoa = nienKhoaDAO.getNienKhoa(request.getParameter("maNienKhoa"));

	    String ngayBatDauString = request.getParameter("ngayBatDauValue");
	    String ngayKetThucString = request.getParameter("ngayKetThucValue");

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
	    
	    ChuNhiemForm chuNhiemForm = new ChuNhiemForm(lop, giangVien, ngayBatDau, ngayKetThuc, nienKhoa);
	    List<String> errors = chuNhiemForm.validateEditBookForm(nienKhoa);
	    

	    if (errors.isEmpty()) {
	        ChuNhiem cn = new ChuNhiem(lop, giangVien, ngayBatDau, ngayKetThuc, nienKhoa);

	        boolean insertResult = chuNhiemDAO.updateChuNhiem(cn);
	        if (!insertResult) {
	            errors.add("Update hvcn không thành công");
	        } else {
	        	response.sendRedirect(request.getContextPath() + "/QLChuNhiem?maLH=" + lop.getMaLop());
	        }
	    }

	    if (!errors.isEmpty()) {
	        request.setAttribute("errors", String.join(", ", errors));
	        request.setAttribute("lop", lop);
	        request.setAttribute("maGV", giangVien.getMaGV());
	        request.setAttribute("chuNhiem", chuNhiemForm);
	        RequestDispatcher rd = request.getServletContext()
	                .getRequestDispatcher("/views/createGVCNView.jsp");
	        rd.forward(request, response);
	    }
	}
	
}
