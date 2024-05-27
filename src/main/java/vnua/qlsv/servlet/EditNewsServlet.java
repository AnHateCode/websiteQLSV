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
import vnua.qlsv.bean.News;
import vnua.qlsv.bean.User;
import vnua.qlsv.formbean.GiangVienForm;
import vnua.qlsv.formbean.NewsForm;
import vnua.qlsv.model.NewsDAO;
import vnua.qlsv.utils.MyUtil;

@WebServlet(urlPatterns = {"/editNews"})
@MultipartConfig

public class EditNewsServlet extends HttpServlet {

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
		List<String> errors = new ArrayList<String>();
		String id1 = request.getParameter("id");
		
		int id = Integer.parseInt(id1);

		if (errors.isEmpty()) {
			News news = newsDAO.getNewsById(id);
			if (news == null) {
				errors.add("Không lấy dc news");
			} else {
				request.setAttribute("news",news);
				RequestDispatcher rd = request.getServletContext()
						.getRequestDispatcher("/views/editNewsView.jsp");
				rd.forward(request, response);
			}
		}
		if (!errors.isEmpty()) {
			request.setAttribute("errors", String.join(", ", errors));
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/QLNewsHome");
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Xử lý Tiếng việt cho request,reponse
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id1 = request.getParameter("id");
		int id = Integer.parseInt(id1);
		
		String tieuDe = request.getParameter("tieuDe");
		String loaiTin = request.getParameter("loaiTin");
		String noiDung = request.getParameter("noiDung");
		Part filePart = request.getPart("file");
		String anhBia = request.getParameter("anhBia");
		
	
		
		NewsForm newsForm = new NewsForm(anhBia,tieuDe, noiDung, loaiTin, filePart);
		List<String> errors = newsForm.validate();
		if (errors.isEmpty()) {

			if (errors.isEmpty()) {

				if (filePart != null && filePart.getSize() > 0) {
					String fileName = tieuDe + "_" + MyUtil.getTimeLabel()
							+ MyUtil.extracFileExtension(filePart);
					String appPath = getServletContext().getRealPath("");
					filePart.write(
							MyUtil.getFolderUpload(appPath, "anhBia-img").getAbsolutePath()
									+ File.separator + fileName);
					anhBia = "anhBia-img" + File.separator + fileName;

				}
		}
			
			News news = new News(id,anhBia, tieuDe, noiDung, loaiTin);
			boolean resultUpdate = newsDAO.updateNews(news);
			if (!resultUpdate) {
				errors.add("Update thất bại");
			} else {
				
					response.sendRedirect(request.getContextPath() + "/QLNewsHome");
			}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", String.join(", ", errors));
			request.setAttribute("news", newsForm);
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/views/editNewsView.jsp");
			rd.forward(request, response);
		}
	}
	}
	
	
}
