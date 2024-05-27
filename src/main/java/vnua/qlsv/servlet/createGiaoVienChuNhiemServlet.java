package vnua.qlsv.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.qlsv.bean.GiangVien;

import vnua.qlsv.bean.Lop;

import vnua.qlsv.formbean.ChuNhiemForm;

import javax.servlet.http.HttpServlet;

import vnua.qlsv.bean.NienKhoa;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.ChuNhiem;
import vnua.qlsv.model.ChuNhiemDAO;
import vnua.qlsv.model.LopDAO;
import vnua.qlsv.model.NienKhoaDAO;
import vnua.qlsv.model.GiangVienDAO;

import vnua.qlsv.utils.MyUtil;

@WebServlet(urlPatterns = {"/createGVCN"})
@MultipartConfig
public class createGiaoVienChuNhiemServlet extends HttpServlet{
	
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
		
		lop = lopDAO.getLop(maLH);
		
		
        request.setAttribute("lop", lop);
        request.setAttribute("maGV", maGV);
		
		RequestDispatcher rd = request.getServletContext()
				.getRequestDispatcher("/views/createGVCNView.jsp");
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
	    System.out.println(ngayBatDau);
	    ChuNhiemForm chuNhiemForm = new ChuNhiemForm(lop, giangVien, ngayBatDau, ngayKetThuc, nienKhoa);
	    List<String> errors = chuNhiemForm.validateEditBookForm(nienKhoa);
	    
	    try {
	    	ChuNhiem chuNhiem = chuNhiemDAO.getChuNhiem(lop.getMaLop());
		    int compareResult = ngayBatDau.compareTo(chuNhiem.getNamBatDau());
		    int compareResult1 = ngayBatDau.compareTo(chuNhiem.getNamKetThuc());
		    int compareResult2 = ngayKetThuc.compareTo(chuNhiem.getNamKetThuc());
		    int compareResult3 = ngayKetThuc.compareTo(chuNhiem.getNamBatDau());
		    System.out.println(chuNhiem.getNamBatDau());
		    if ((compareResult <= 0 && compareResult1 >= 0) || compareResult2 <= 0 && compareResult3 >=0) {
		        errors.add("Lớp này có có giáo viên chủ nhiệm trong thời gian" + chuNhiem.getNamBatDau() + "tới " + chuNhiem.getNamKetThuc());
		        System.out.println(chuNhiem.getNamBatDau());
		    }
		  
		    
		    
		} catch (Exception e) {
			// TODO: handle exception
		}
	    

	    try {
	    	ChuNhiem chuNhiem1 = chuNhiemDAO.getChuNhiemGV(giangVien.getMaGV());
		    int compareResult0 = ngayBatDau.compareTo(chuNhiem1.getNamBatDau());
		    int compareResult1_1 = ngayBatDau.compareTo(chuNhiem1.getNamKetThuc());
		    int compareResult2_1 = ngayKetThuc.compareTo(chuNhiem1.getNamKetThuc());
		    int compareResult3_1 = ngayKetThuc.compareTo(chuNhiem1.getNamBatDau());
		    System.out.println(chuNhiem1.getNamBatDau());
		    if ((compareResult0 <= 0 && compareResult1_1 >= 0) || compareResult2_1 <= 0 && compareResult3_1 >=0) {
		        errors.add(" giáo viên đã chủ nhiệm trong thời gian" + chuNhiem1.getNamBatDau() + "tới " + chuNhiem1.getNamKetThuc());
		        System.out.println(chuNhiem1.getNamBatDau());
		    }
		  
		    
		    
		} catch (Exception e) {
			// TODO: handle exception
		}
	   
	    
	    
	    if (errors.isEmpty()) {
	        ChuNhiem cn = new ChuNhiem(lop, giangVien, ngayBatDau, ngayKetThuc, nienKhoa);

	        boolean insertResult = chuNhiemDAO.insertChuNhiem(cn);
	        if (!insertResult) {
	            errors.add("Thêm hvcn không thành công");
	        } else {
	        	response.sendRedirect(request.getContextPath() + "/QLChuNhiem?maLH=" + lop.getMaLop());
	        }
	    }

	    if (!errors.isEmpty()) {
	        request.setAttribute("errors", String.join(", ", errors));
	        request.setAttribute("lop", lop);
	        request.setAttribute("maGV", giangVien.getMaGV());
	        RequestDispatcher rd = request.getServletContext()
	                .getRequestDispatcher("/views/createGVCNView.jsp");
	        rd.forward(request, response);
	    }
	}
}
