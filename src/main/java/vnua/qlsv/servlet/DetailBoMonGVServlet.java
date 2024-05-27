package vnua.qlsv.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.Khoa;
import vnua.qlsv.bean.ChuNhiem;
import vnua.qlsv.bean.ChuyenNganh;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.NienKhoa;
import vnua.qlsv.model.GiangVienDAO;
import vnua.qlsv.model.KhoaDAO;
import vnua.qlsv.model.LopDAO;
import vnua.qlsv.model.NienKhoaDAO;
import vnua.qlsv.model.ChuNhiemDAO;
import vnua.qlsv.model.ChuyenNganhDAO;

@WebServlet(urlPatterns = {"/detailBoMonGV"})
public class DetailBoMonGVServlet extends HttpServlet{

	 private static final long serialVersionUID = 1L;

	    private GiangVienDAO giangVienDao;
	    private ChuNhiemDAO chuNhiemDao;

	    public void init() {
	        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
	        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
	        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
	        giangVienDao = new GiangVienDAO(jdbcURL, jdbcUsername, jdbcPassword);
	        chuNhiemDao = new ChuNhiemDAO(jdbcURL, jdbcUsername, jdbcPassword);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String errors = null;
	        String errors1 = null;
	        List<GiangVien> gvList;



	        String maCN = request.getParameter("maCN");

	

	        gvList = giangVienDao.listAllGiangVienByCN(maCN);

	      

	        if (gvList.isEmpty()) {
	            errors1 = "Không có giảng viên phù hợp";
	        }

	        request.setAttribute("errors", errors);
	        request.setAttribute("errors1", errors1);
	        request.setAttribute("gvList", gvList);
	        request.setAttribute("maCN", maCN);
	        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/views/detailBoMonGVView.jsp");
	        rd.forward(request, response);
	    }
	    

}
