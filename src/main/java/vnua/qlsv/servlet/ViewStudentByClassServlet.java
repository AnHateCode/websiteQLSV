package vnua.qlsv.servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.qlsv.bean.BanCanSuLop;
import vnua.qlsv.bean.ChuNhiem;
import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.Lop;
import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.model.BanCanSuLopDAO;
import vnua.qlsv.model.LopDAO;
import vnua.qlsv.model.SinhVienDao;
import vnua.qlsv.utils.MyUtil;

@WebServlet(urlPatterns = {"/viewStudentByClass"})
public class ViewStudentByClassServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private SinhVienDao sinhVienDAO;
	private LopDAO lopDAO;
	private BanCanSuLopDAO banCanSuLopDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		sinhVienDAO = new SinhVienDao(jdbcURL, jdbcUsername, jdbcPassword);
		lopDAO = new LopDAO(jdbcURL, jdbcUsername, jdbcPassword);
		banCanSuLopDAO = new BanCanSuLopDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errors = null;
	    String errors1 = null;	        	     
		List<SinhVien> list = null;
		List<SinhVien> sinhVienList = new ArrayList<SinhVien>();
		List<BanCanSuLop> banCanSuList;
		Lop lop;
		
		
		String maLH = request.getParameter("maLH");
	
		
		list = sinhVienDAO.listAllStudents(maLH);
		banCanSuList = banCanSuLopDAO.getBanCanSuLops(maLH);
		lop = lopDAO.getLop(maLH);
		
		 for (BanCanSuLop banCanSuLop: banCanSuList) {
	            try {
	                SinhVien sv = sinhVienDAO.getStudent(banCanSuLop.getSinhVien().getMaSV());
	                sinhVienList.add(sv);
	            } catch (Exception e) {
	               if(sinhVienList.isEmpty()) {
	            	   errors = "Không có Ban cán sự lớp";
	               }	         
	            }
	        }
		 if(sinhVienList.isEmpty()) {
      	   errors = "Không có Ban cán sự lớp nào";
         }			
		if (list.isEmpty()) {
			errors1 = "Không có sinh viên nào";
		}
		
		request.setAttribute("errors", errors);
		 request.setAttribute("errors1", errors1);
		request.setAttribute("svList", list);
		request.setAttribute("lop", lop);
		request.setAttribute("svList1", sinhVienList);
		 request.setAttribute("chuNhiemList", banCanSuList);
		
		RequestDispatcher rd;
			rd = this.getServletContext()
					.getRequestDispatcher("/views/studentByClassView.jsp");
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
