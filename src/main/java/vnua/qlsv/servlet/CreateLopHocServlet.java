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
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.formbean.GiangVienForm;
import vnua.qlsv.formbean.LopHocForm;

import vnua.qlsv.model.GiangVienDAO;
import vnua.qlsv.model.LopDAO;
import vnua.qlsv.model.KyHocDAO;
import vnua.qlsv.model.MonHocDAO;
import vnua.qlsv.model.LopHocDAO;
import vnua.qlsv.utils.MyUtil;

@WebServlet(urlPatterns = {"/createLopHoc"})
@MultipartConfig
public class CreateLopHocServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private GiangVienDAO gvDAO;
	private LopDAO lopDAO;
	private KyHocDAO kyHocDAO;
	private MonHocDAO monHocDAO;
	private LopHocDAO lopHocDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		gvDAO = new GiangVienDAO(jdbcURL, jdbcUsername, jdbcPassword);
		lopDAO = new LopDAO(jdbcURL, jdbcUsername, jdbcPassword);
		kyHocDAO = new KyHocDAO(jdbcURL, jdbcUsername, jdbcPassword);
		monHocDAO = new MonHocDAO(jdbcURL, jdbcUsername, jdbcPassword);
		lopHocDAO = new LopHocDAO(jdbcURL, jdbcUsername, jdbcPassword);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
		List<String> errors = new ArrayList<String>();
		 String maLH = (String) request.getParameter("maLH");
		List<MonHoc> listMonHoc = monHocDAO.getAllMonHoc();

        // Lấy danh sách các giảng viên
        List<GiangVien> listGiangVien = gvDAO.listAllGiangViens();

        // Lấy danh sách các lớp học
        List<Lop> listLop = lopDAO.getAllLop();
        
        List<KyHoc> listKyHoc = kyHocDAO.getAllKyHoc();
        
        request.setAttribute("listMonHoc", listMonHoc);
        request.setAttribute("listGiangVien", listGiangVien);
        request.setAttribute("listLop", listLop);
        request.setAttribute("listKyHoc", listKyHoc);
		
		RequestDispatcher rd = request.getServletContext()
				.getRequestDispatcher("/views/createLopHocView.jsp");
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
		    
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
		    String maLopHoc = request.getParameter("maLopHoc");
		    Lop lop  = new Lop();
		    lop.setMaLop(request.getParameter("maLop"));
		    
		    KyHoc kyHoc = new KyHoc();
		    kyHoc.setMaKiHoc(request.getParameter("maKiHoc"));
		    
		    MonHoc monHoc = new MonHoc();
		    monHoc.setMaMonHoc("maMonHoc");
		    
		    GiangVien giangVien = new GiangVien();
		    giangVien.setMaGV("maGV");
		    
		    String ngayBatDauString = request.getParameter("ngayBatDau"); 
		    Date ngayBatDau = null;
		    try {
		        ngayBatDau = sdf.parse(ngayBatDauString);
		    } catch (ParseException e) {
		        e.printStackTrace();
		        // Xử lý ngoại lệ khi parse ngày bắt đầu
		    }
		    
		    String ngayKetThucString = request.getParameter("ngayKetThuc");
		    Date ngayKetThuc = null;
		    try {
		        ngayKetThuc = sdf.parse(ngayKetThucString);
		    } catch (ParseException e) {
		        e.printStackTrace();
		        // Xử lý ngoại lệ khi parse ngày kết thúc
		    }
		    
		    String ngayTrongTuan = request.getParameter("ngayTrongTuan");
		    
		    String soTietString = request.getParameter("soTiet");
		    Integer soTiet = null;
		    try {
		        soTiet = Integer.parseInt(soTietString);
		    } catch (NumberFormatException e) {
		        e.printStackTrace();
		        // Xử lý ngoại lệ khi parse số tiết
		    }
		    
		    String tietBatDauString = request.getParameter("tietBatDau"); 
		    Time tietBatDau = null;
		    try {
		        java.util.Date parsedDate = sdf1.parse(tietBatDauString);
		        // Tạo đối tượng Time từ đối tượng Date đã phân tích
		        tietBatDau = new Time(parsedDate.getTime());
		    } catch (ParseException e) {
		        e.printStackTrace();
		        // Xử lý ngoại lệ khi parse tiết bắt đầu
		    }
		    
		    String phongHoc = request.getParameter("phongHoc");
		    
		    LopHocForm lopHocForm = new LopHocForm(maLopHoc, lop, kyHoc, monHoc, giangVien, ngayBatDau, ngayKetThuc, ngayTrongTuan, soTiet, tietBatDau, phongHoc);
		    
		    List<String> errors = lopHocForm.validateCreateBookForm();
		    if (errors.isEmpty()) {
		        LopHoc lopHoc = new LopHoc(maLopHoc, lop, kyHoc, monHoc, giangVien, ngayBatDau, ngayKetThuc, ngayTrongTuan, soTiet, tietBatDau, phongHoc);
		        
		        boolean insertResult = lopHocDAO.insertLopHoc(lopHoc);
		        if (!insertResult) {
		            errors.add("Thêm sách không thành công");
		        } else {
		            response.sendRedirect(request.getContextPath() + "/QLLopHoc");
		        }
		    }

		    if (!errors.isEmpty()) {
		        request.setAttribute("errors", String.join(", ", errors));
		        request.setAttribute("lh", lopHocForm);
		        RequestDispatcher rd = request.getServletContext()
		                .getRequestDispatcher("/views/createLopHoc.jsp");
		        rd.forward(request, response);
		    }
		}

}
