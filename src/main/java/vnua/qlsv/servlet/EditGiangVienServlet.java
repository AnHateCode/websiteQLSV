package vnua.qlsv.servlet;

import java.io.File;
import java.io.IOException;
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

import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.bean.User;
import vnua.qlsv.formbean.StudentForm;
import vnua.qlsv.formbean.GiangVienForm;
import vnua.qlsv.model.GiangVienDAO;
import vnua.qlsv.model.SinhVienDao;
import vnua.qlsv.utils.MyUtil;

@WebServlet(urlPatterns = {"/editGV"})
@MultipartConfig
public class EditGiangVienServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private GiangVienDAO gvDAO;

	public EditGiangVienServlet() {
		super();
	}
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		gvDAO = new GiangVienDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> errors = new ArrayList<String>();

		String maGV = (String) request.getParameter("maGV");

		if (errors.isEmpty()) {
			GiangVien gv = gvDAO.getGiangVien(maGV);
			if (gv == null) {
				errors.add("Không lấy được sách");
			} else {
				request.setAttribute("gv", gv);
				RequestDispatcher rd = request.getServletContext()
						.getRequestDispatcher("/views/editGiangVienView.jsp");
				rd.forward(request, response);
			}
		}
		if (!errors.isEmpty()) {
			request.setAttribute("errors", String.join(", ", errors));
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/QLGiangVienHome");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Xử lý Tiếng việt cho request,reponse
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		
		String maGV = request.getParameter("maGV");
		String HoTen = request.getParameter("HoTen");
		String NgaySinh = request.getParameter("NgaySinh");
		String DiaChi = request.getParameter("DiaChi");
		String email = request.getParameter("email");
		String SoDienThoai = request.getParameter("SoDienThoai");
		String maKhoa = request.getParameter("ma_khoa");
		String hocVan = request.getParameter("hoc_van");
		String chuyenMon = request.getParameter("chuyen_mon");
		Part filePart = request.getPart("file");
		String avatar = request.getParameter("avatar");
		
		 User loginedUser = (User) request.getSession().getAttribute("loginedUser");
         int loaiNguoiDung = loginedUser.getLoaiNguoiDung();
         String userId = loginedUser.getMaDangNhap();
		
         GiangVienForm giangvienForm = new GiangVienForm(maGV,HoTen,NgaySinh,DiaChi,email,SoDienThoai,hocVan,chuyenMon,maKhoa,filePart);
		List<String> errors = giangvienForm.validateEditBookForm();
		if (errors.isEmpty()) {

			if (errors.isEmpty()) {

				if (filePart != null && filePart.getSize() > 0) {
					String fileName = maGV + "_" + MyUtil.getTimeLabel()
							+ MyUtil.extracFileExtension(filePart);
					String appPath = getServletContext().getRealPath("");
					filePart.write(
							MyUtil.getFolderUpload(appPath, "giangvien-img").getAbsolutePath()
									+ File.separator + fileName);
					avatar = "giangvien-img" + File.separator + fileName;

				}
		}
			
			GiangVien gv = new GiangVien(maGV,HoTen,NgaySinh,DiaChi,email,SoDienThoai,avatar,maKhoa,hocVan,chuyenMon);
			boolean resultUpdate = gvDAO.updateGiangVien(gv);
			if (!resultUpdate) {
				errors.add("Update thất bại");
			} else {
				if(loaiNguoiDung == 3) {
					response.sendRedirect(request.getContextPath() + "/userInfor");
				}else {
					response.sendRedirect(request.getContextPath() + "/adminHome");
				}
			}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", String.join(", ", errors));
			request.setAttribute("gv",giangvienForm);
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/views/editGiangVienView.jsp");
			rd.forward(request, response);
		}
	}
	}
}
