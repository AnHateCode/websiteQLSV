package vnua.qlsv.servlet;

import java.awt.print.Printable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.cj.x.protobuf.MysqlxResultset.FetchSuspendedOrBuilder;


import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.bean.User;
import vnua.qlsv.formbean.StudentForm;
import vnua.qlsv.model.SinhVienDao;
import vnua.qlsv.utils.MyUtil;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 20 // 20MB
)
@WebServlet(urlPatterns = {"/editStudent"})
public class EditStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SinhVienDao svDAO;

	public EditStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
//		bookDAO = new BookDAO("jdbc:mysql://localhost:3306/bookstore", "root", "123456");
		svDAO = new SinhVienDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> errors = new ArrayList<String>();

		String maSV = (String) request.getParameter("maSV");

		if (errors.isEmpty()) {
			SinhVien sv = svDAO.getStudent(maSV);
			if (sv == null) {
				errors.add("Không lấy được sách");
			} else {
				request.setAttribute("student", sv);
				RequestDispatcher rd = request.getServletContext()
						.getRequestDispatcher("/views/editStudentView.jsp");
				rd.forward(request, response);
			}
		}
		if (!errors.isEmpty()) {
			request.setAttribute("errors", String.join(", ", errors));
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/adminHome");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Xử lý Tiếng việt cho request,reponse
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		
		String maSV = request.getParameter("maSV");
		String HoTen = request.getParameter("HoTen");
		String NgaySinh = request.getParameter("NgaySinh");
		String GioiTinh = request.getParameter("GioiTinh");
		String DiaChi = request.getParameter("DiaChi");
		String email = request.getParameter("email");
		String SoDienThoai = request.getParameter("SoDienThoai");
		String maKhoa = request.getParameter("ma_khoa");
		String maChuyenNganh = request.getParameter("ma_chuyen_nganh");
		String maLop = request.getParameter("ma_lop");
		Part filePart = request.getPart("file");
		String avatar = request.getParameter("avatar");
		
		 User loginedUser = (User) request.getSession().getAttribute("loginedUser");
         int loaiNguoiDung = loginedUser.getLoaiNguoiDung();
         String userId = loginedUser.getMaDangNhap();
		
		StudentForm studentForm = new StudentForm(maSV,HoTen,NgaySinh,GioiTinh,DiaChi,email,SoDienThoai,maKhoa,maChuyenNganh,maLop,filePart);
		List<String> errors = studentForm.validateEditBookForm();
		if (errors.isEmpty()) {

			if (errors.isEmpty()) {

				if (filePart != null && filePart.getSize() > 0) {
					String fileName = maSV + "_" + MyUtil.getTimeLabel()
							+ MyUtil.extracFileExtension(filePart);
					String appPath = getServletContext().getRealPath("");
					filePart.write(
							MyUtil.getFolderUpload(appPath, "student-img").getAbsolutePath()
									+ File.separator + fileName);
					avatar = "student-img" + File.separator + fileName;

				}
		}
			
			SinhVien sv = new SinhVien(maSV,HoTen,NgaySinh,GioiTinh,DiaChi,email,SoDienThoai,avatar,maKhoa,maChuyenNganh,maLop);
			boolean resultUpdate = svDAO.updateStudent(sv);
			if (!resultUpdate) {
				errors.add("Update thất bại");
			} else {
				
				if(loaiNguoiDung == 1) {
					response.sendRedirect(request.getContextPath() + "/userInfor");
				}else {
					response.sendRedirect(request.getContextPath() + "/adminHome");
				}
				
			}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", String.join(", ", errors));
			request.setAttribute("student", studentForm);
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/views/editStudentView.jsp");
			rd.forward(request, response);
		}
	}

}}
