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

import vnua.qlsv.bean.GiangVien;
import vnua.qlsv.bean.SinhVien;
import vnua.qlsv.formbean.StudentForm;
import vnua.qlsv.formbean.GiangVienForm;
import vnua.qlsv.model.GiangVienDAO;
import vnua.qlsv.model.SinhVienDao;
import vnua.qlsv.utils.MyUtil;


@WebServlet(urlPatterns = {"/createGiangVien"})
@MultipartConfig
public class CreateGiangVienServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private GiangVienDAO gvDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		gvDAO = new GiangVienDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getServletContext()
				.getRequestDispatcher("/views/createGiangVienView.jsp");
		rd.forward(request, response);
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
		String imagePath;
		
	
		GiangVienForm giangvienForm = new GiangVienForm(maGV,HoTen,NgaySinh,DiaChi,email,SoDienThoai,hocVan,chuyenMon,maKhoa,filePart);
		
		List<String> errors = giangvienForm.validateCreateBookForm();
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
			String fileName = maGV + "_" + MyUtil.getTimeLabel()
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

			GiangVien gv = new GiangVien(maGV,HoTen,NgaySinh,DiaChi,email,SoDienThoai,imagePath,maKhoa,hocVan,chuyenMon);
			
			boolean insertResult = gvDAO.insertGiangVien(gv);
			if (!insertResult) {
				errors.add("Thêm sách không thành công");
			} else {
				response.sendRedirect(request.getContextPath() + "/QLGiangVienHome");
			}
		}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", String.join(", ", errors));
			request.setAttribute("gv", giangvienForm);
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/views/createGiangVienView.jsp");
			rd.forward(request, response);
		}
	}
}
