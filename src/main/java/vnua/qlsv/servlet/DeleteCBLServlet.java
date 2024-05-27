package vnua.qlsv.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.qlsv.bean.Lop;
import vnua.qlsv.model.BanCanSuLopDAO;
import vnua.qlsv.model.ChuNhiemDAO;
import vnua.qlsv.model.LopDAO;

@WebServlet(urlPatterns = {"/deleteCBL"})
public class DeleteCBLServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private LopDAO lopDAO;
	private BanCanSuLopDAO banCanSuLopDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		banCanSuLopDAO = new BanCanSuLopDAO(jdbcURL, jdbcUsername, jdbcPassword);
		lopDAO = new LopDAO(jdbcURL, jdbcUsername, jdbcPassword);
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String svId = request.getParameter("maSV");
		Lop lop = new Lop(); 
	    lop = lopDAO.getLop(request.getParameter("maLH"));
		boolean check = banCanSuLopDAO.deleteCBL(svId);
		response.sendRedirect(request.getContextPath() + "/viewStudentByClass?maLH=" + lop.getMaLop());
	}
}
