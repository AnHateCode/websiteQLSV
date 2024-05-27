package vnua.qlsv.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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


import vnua.qlsv.bean.News;
import vnua.qlsv.formbean.NewsForm;
import vnua.qlsv.model.NewsDAO;
import vnua.qlsv.utils.MyUtil;

@WebServlet(urlPatterns = {"/createNews"})
@MultipartConfig
public class CreateNewsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private NewsDAO newsDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		newsDAO = new NewsDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getServletContext()
				.getRequestDispatcher("/views/createNewsView.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Xử lý Tiếng việt cho request,reponse
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		String tieuDe = request.getParameter("tieuDe");
		String loaiTin = request.getParameter("loaiTin");
		String noiDung = request.getParameter("noiDung");
		Part filePart = request.getPart("file");
		String imagePath;

		NewsForm newsForm = new NewsForm(tieuDe, noiDung, loaiTin, filePart);
		List<String> errors = newsForm.validate();
		if (errors.isEmpty()) {

			// lưu ảnh vào thư mục 'img' nếu có
			String fileName = tieuDe + "_" + MyUtil.getTimeLabel()
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

			News news = new News(imagePath, tieuDe, noiDung, loaiTin);
			news.setNgayTao(new Date());

			boolean insertResult = false;
			try {
				insertResult = newsDAO.addNews(news);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!insertResult) {
				errors.add("Thêm tin tức không thành công");
			} else {
				response.sendRedirect(request.getContextPath() + "/QLNewsHome");
			}
		}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", String.join(", ", errors));
			request.setAttribute("news", newsForm);
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/views/createNewsView.jsp");
			rd.forward(request, response);
		}
	}
	
	
}
