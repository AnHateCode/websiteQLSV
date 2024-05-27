package vnua.qlsv.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import vnua.qlsv.bean.News;
import vnua.qlsv.model.NewsDAO;

@WebServlet(urlPatterns = {"/detailNews"})
public class DetailNewsServlet extends HttpServlet {

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
				errors.add("Không có tin nào ");
			} else {
				request.setAttribute("news", news);
				RequestDispatcher rd = request.getServletContext()
						.getRequestDispatcher("/views/detailNewsView.jsp");
				rd.forward(request, response);
			}
		}
		if(!errors.isEmpty()) {
			request.setAttribute("errors", String.join(", ", errors));
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/QLNewsHome");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
