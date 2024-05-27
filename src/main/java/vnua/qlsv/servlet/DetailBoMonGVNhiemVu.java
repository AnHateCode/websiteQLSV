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

import vnua.qlsv.bean.ChuNhiem;
import vnua.qlsv.bean.ChuyenNganh;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.Khoa;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.LopHoc;
import vnua.qlsv.bean.NienKhoa;
import vnua.qlsv.model.ChuNhiemDAO;
import vnua.qlsv.model.ChuyenNganhDAO;
import vnua.qlsv.model.GiangVienDAO;
import vnua.qlsv.model.KhoaDAO;
import vnua.qlsv.model.LopDAO;
import vnua.qlsv.model.LopHocDAO;
import vnua.qlsv.model.NienKhoaDAO;
import vnua.qlsv.utils.MyUtil;

@WebServlet(urlPatterns = {"/detailBoMonGVNhiemVu"})
public class DetailBoMonGVNhiemVu extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private LopDAO lopDAO;
	private NienKhoaDAO nienKhoaDAO;
	private KhoaDAO khoaDAO;
	private ChuyenNganhDAO chuyenNganhDAO;
	 private ChuNhiemDAO chuNhiemDao;
	 private GiangVienDAO giangVienDao;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		lopDAO = new LopDAO(jdbcURL, jdbcUsername, jdbcPassword);
		nienKhoaDAO = new NienKhoaDAO(jdbcURL, jdbcUsername, jdbcPassword);
		khoaDAO = new KhoaDAO(jdbcURL, jdbcUsername, jdbcPassword);
		chuyenNganhDAO = new ChuyenNganhDAO(jdbcURL, jdbcUsername, jdbcPassword);
		 chuNhiemDao = new ChuNhiemDAO(jdbcURL, jdbcUsername, jdbcPassword);
		  giangVienDao = new GiangVienDAO(jdbcURL, jdbcUsername, jdbcPassword);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errors = null;
		List<Lop> lopList;
		List<ChuyenNganh> cnList;
		List<Khoa> khoaList = null;
		List<NienKhoa> nkList;
		ChuNhiem chuNhiem;
		List<ChuNhiem> chuNhiemList;
		GiangVien giangVien;
		
		  String maGV = request.getParameter("maGV");
		  
		 chuNhiem = chuNhiemDao.getChuNhiemGV(maGV);
		 chuNhiemList = chuNhiemDao.getChuNhiemGVList(maGV);
		 giangVien = giangVienDao.getGiangVien(maGV);
		int page = 1;
		int recordsPerPage = 4;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
	
		String keyword = request.getParameter("keyword");
		String maKhoa = request.getParameter("maKhoa");
		String maChuyenNganh = request.getParameter("maChuyenNganh");
		String maNienKhoa = request.getParameter("maNienKhoa");
		lopList = lopDAO.getAllLop((page - 1) * recordsPerPage, recordsPerPage, keyword,maNienKhoa,maKhoa,maChuyenNganh);
		cnList = chuyenNganhDAO.listAllChuyenNganh();
		try {
			khoaList = khoaDAO.getKhoaChuyenNganhList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nkList = nienKhoaDAO.listAllNienKhoa();
		
		
		int noOfRecords = lopDAO.getNoOfRecords(keyword);
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
		request.setAttribute("keyword", keyword);
		
		if (lopList.isEmpty()) {
			errors = "Không có bài viết nào";
		}
		request.setAttribute("errors", errors);
		request.setAttribute("lopList", lopList);
		request.setAttribute("chuNhiemList", chuNhiemList);
		request.setAttribute("giangVien", giangVien);
		request.setAttribute("nkList", nkList);
		request.setAttribute("khoaList", khoaList);
		request.setAttribute("cnList", cnList);
		
		
		RequestDispatcher rd;
		if(keyword != null || maKhoa != null || maChuyenNganh != null || maNienKhoa != null) {
			rd = this.getServletContext()
					.getRequestDispatcher("/views/filterResult.jsp");
		}else {
			rd = this.getServletContext()
					.getRequestDispatcher("/views/detailBoMonGVNhiemVuView.jsp");
		}
		
		rd.forward(request, response);
		
	}
	
}
