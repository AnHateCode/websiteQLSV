package vnua.qlsv.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vnua.qlsv.bean.User;
import vnua.qlsv.model.UserDAO;
import vnua.qlsv.utils.MyUtil;

@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	@Override
	public void init(FilterConfig filterConfig) {
		String jdbcURL = filterConfig.getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = filterConfig.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = filterConfig.getServletContext().getInitParameter("jdbcPassword");
//		userDAO = new UserDAO("jdbc:mysql://localhost:3306/bookstore", "root", "123456");
		userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		
		User userInSession=MyUtil.getLoginedUser(session);
		if(userInSession!=null) {
			session.setAttribute("CHECKED_COOKIE", "CHECKED");
			chain.doFilter(request, res);
			return;
		}
		String checked=(String) session.getAttribute("CHECKED_COOKIE");
		if(checked==null) {
			String username=MyUtil.getUsernameInCookie(request);
			if(username!=null && !username.isEmpty()) {
				User user=userDAO.findUser(username);
				if(user!=null) {
					String token=MyUtil.getTokenInCookie(request);
					if(token.equals(MyUtil.createTokenFromUserInfo(user))) {
						MyUtil.storeLoginedUser(session, user);
						session.setAttribute("CHECKED_COOKIE", "CHECKED");
					}
				}
			}
		}
		// TODO Auto-generated method stub
		chain.doFilter(req, res);
	}

}
