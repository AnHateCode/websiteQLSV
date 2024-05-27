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
import vnua.qlsv.model.LopHocDAO;
import vnua.qlsv.utils.MyUtil;

@WebServlet(urlPatterns = {"/createLop"})
@MultipartConfig
public class CreateLopServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private NienKhoaDAO nienKhoaDAO;
	private LopDAO lopDAO;
	private KhoaDAO khoaDAO;
	private ChuyenNganhDAO chuyenNganhDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		nienKhoaDAO = new NienKhoaDAO(jdbcURL, jdbcUsername, jdbcPassword);
		lopDAO = new LopDAO(jdbcURL, jdbcUsername, jdbcPassword);
		chuyenNganhDAO = new ChuyenNganhDAO(jdbcURL, jdbcUsername, jdbcPassword);
		khoaDAO = new KhoaDAO(jdbcURL, jdbcUsername, jdbcPassword);	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
		List<String> errors = new ArrayList<String>();
		 String maLH = (String) request.getParameter("maLop");
		 String tenLop = (String) request.getParameter("tenLop");
		List<NienKhoa> listNienKhoa = nienKhoaDAO.listAllNienKhoa();

        // Lấy danh sách khoa
        List<Khoa> listKhoa = khoaDAO.getKhoaChuyenNganhList();

        // Lấy danh sách các lớp học
        List<ChuyenNganh> listCN = chuyenNganhDAO.listAllChuyenNganh();
        
        request.setAttribute("listKhoa", listKhoa);
        request.setAttribute("listNienKhoa", listNienKhoa);
        request.setAttribute("listChuyenNganh", listCN);
		
		RequestDispatcher rd = request.getServletContext()
				.getRequestDispatcher("/views/createLopView.jsp");
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
		    
		   
		    String maLop = request.getParameter("maLop");
		    NienKhoa nienKhoa  = new NienKhoa();
		    nienKhoa.setMaNienKhoa(request.getParameter("maNienKhoa"));
		    
		    Khoa khoa = new Khoa();
		    khoa.setMaKhoa(request.getParameter("maKhoa"));
		    
		    ChuyenNganh chuyenNganh = new ChuyenNganh();
		    chuyenNganh.setMaChuyenNganh(request.getParameter("maCN"));    		
		    String tenLop = request.getParameter("tenLop"); 

		    LopForm lopForm = new LopForm(maLop, tenLop, chuyenNganh, khoa, nienKhoa);
		    
		    List<String> errors = lopForm.validate();
		    if (errors.isEmpty()) {
		        Lop lop = new Lop(maLop, tenLop, chuyenNganh, khoa, nienKhoa);
		        
		        boolean insertResult = lopDAO.insertLop(lop);
		        if (!insertResult) {
		            errors.add("Thêm lớp thành công");
		        } else {
		            response.sendRedirect(request.getContextPath() + "/QLLop");
		        }
		    }

		    if (!errors.isEmpty()) {
		        request.setAttribute("errors", String.join(", ", errors));
		        request.setAttribute("lh", lopForm);
		        RequestDispatcher rd = request.getServletContext()
		                .getRequestDispatcher("/views/createLop.jsp");
		        rd.forward(request, response);
		    }
		}
}
