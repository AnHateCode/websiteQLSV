package vnua.qlsv.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.qlsv.bean.ClassInfo;
import vnua.qlsv.model.ClassInfoDAO;

@WebServlet("/thongKeKhoa")
public class ThongKeKhoaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClassInfoDAO classInfoDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        classInfoDAO = new ClassInfoDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<ClassInfo> classInfoList = classInfoDAO.getClassInfoList();
            request.setAttribute("classInfoList", classInfoList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/thongKeKhoa.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
