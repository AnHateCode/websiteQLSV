package vnua.qlsv.servlet;

import java.io.File;
import java.io.IOException;
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


import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.formbean.StudentForm;
import vnua.qlsv.model.SinhVienDao;
import vnua.qlsv.utils.MyUtil;

/**
 * Servlet implementation class createBookServlet
 */

@WebServlet(urlPatterns = {"/createStudent"})
@MultipartConfig
public class CreateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SinhVienDao svDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		svDAO = new SinhVienDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String maLH = (String) request.getParameter("maLH");
		request.setAttribute("maLH", maLH);
		
		if(maLH == null) {
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/views/createStudentView.jsp");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/views/createStudentView1.jsp");
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
		String imagePath;
		
		System.out.println(HoTen);
		StudentForm studentForm = new StudentForm(maSV,HoTen,NgaySinh,GioiTinh,DiaChi,email,SoDienThoai,maKhoa,maChuyenNganh,maLop,filePart);
		System.out.println(studentForm.getHoTen());
		List<String> errors = studentForm.validateCreateBookForm();
		if (errors.isEmpty()) {

//			// lưu ảnh thanh toán vào thư mục nếu có
//			String fileName = title + "_" + MyUtil.getTimeLabel()
//					+ MyUtil.extracFileExtension(filePart);
//			String appPath = getServletContext().getRealPath(""); // thu muc goc cua ung
//																	// dung web
//			filePart.write(MyUtil.getFolderUpload(appPath, "book-img").getAbsolutePath()
//					+ File.separator + fileName);
//			imagePath = "" + File.separator+fileName;

			// lưu ảnh vào thư mục 'img' nếu có
			String fileName = maSV + "_" + MyUtil.getTimeLabel()
					+ MyUtil.extracFileExtension(filePart);
			String contextPath = getServletContext().getRealPath("/"); // Lấy đường dẫn
																		// thực của ứng
																		// dụng web
			String savePath = contextPath + "img"; // Đường dẫn đến thư mục 'img'

			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir(); // Tạo thư mục 'img' nếu nó không tồn tại
			}

			String filePath = savePath + File.separator + fileName; // Đường dẫn file cuối
																	// cùng để lưu trữ ảnh
			filePart.write(filePath); // Lưu file ảnh
			imagePath = "img" + File.separator + fileName; // Đường dẫn tương đối để lưu
															// trong cơ sở dữ liệu

			SinhVien sv = new SinhVien(maSV,HoTen,NgaySinh,GioiTinh,DiaChi,email,SoDienThoai,imagePath,maKhoa,maChuyenNganh,maLop);
			
			boolean insertResult = svDAO.insertStudent(sv);
			if (!insertResult) {
				errors.add("Thêm sách không thành công");
			} else {
				response.sendRedirect(request.getContextPath() + "/adminHome");
			}
		}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", String.join(", ", errors));
			request.setAttribute("student", studentForm);
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/views/createStudentView.jsp");
			rd.forward(request, response);
		}
	}

}

