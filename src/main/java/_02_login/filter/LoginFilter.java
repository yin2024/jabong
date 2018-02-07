package _02_login.filter;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.*;

import _01_register.model.*;

@WebFilter(urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "mustLogin1", value = "/friends.jsp"),
	@WebInitParam(name = "mustLogin2", value = "/index.jsp"),
	@WebInitParam(name = "mustLogin3", value = "/post.jsp"),
	@WebInitParam(name = "mustLogin4", value = "/DisplayPosts"),
	@WebInitParam(name = "mustLogin5", value = "/DisplayAllFriends"),
	@WebInitParam(name = "mustLogin6", value = "/friends.jsp"),
	@WebInitParam(name = "mustLogin7", value = "/world.jsp"),
	@WebInitParam(name = "mustLogin8", value = "/groups.jsp"),
	@WebInitParam(name = "mustLogin9", value = "/gifts.jsp"),
	@WebInitParam(name = "mustLogin10", value = "/SearchFriends.do"),
	@WebInitParam(name = "mustLogin11", value = "/settings.jsp")})
public class LoginFilter implements Filter {
    Collection<String> url = new ArrayList<String>();
    String servletPath;
    String contextPath;
    String requestURI;

    public void init(FilterConfig fConfig) throws ServletException {
	Enumeration<String> e = fConfig.getInitParameterNames();
	while (e.hasMoreElements()) {
	    String path = e.nextElement();
	    url.add(fConfig.getInitParameter(path));
	}
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	boolean isRequestedSessionIdValid = false;
	if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
	    HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse resp = (HttpServletResponse) response;
	    servletPath = req.getServletPath();
	    contextPath = req.getContextPath();
	    requestURI = req.getRequestURI();
	    isRequestedSessionIdValid = req.isRequestedSessionIdValid();

	    if (mustLogin()) {
		if (checkLogin(req)) { // 需要登入，已經登入
		    chain.doFilter(request, response);
		} else { // 需要登入，尚未登入
		    HttpSession session = req.getSession();
		    session.setAttribute("requestURI", requestURI);
		    if (!isRequestedSessionIdValid) {
			session.setAttribute("timeOut", "使用逾時，請重新登入");
		    }
		    resp.sendRedirect(contextPath + "/login.jsp");
		    return;
		}
	    } else { // 不需要登入
		chain.doFilter(request, response);
	    }
	} else {
	    throw new ServletException("Request / Response 型態錯誤");
	}
    }

    private boolean checkLogin(HttpServletRequest req) {
	HttpSession session = req.getSession();
	MemberBean loginToken = (MemberBean) session.getAttribute("LoginOK");
	if (loginToken == null) {
	    return false;
	} else {
	    return true;
	}
    }

    private boolean mustLogin() {
	boolean login = false;
	for (String sURL : url) {

	    if (servletPath.startsWith(sURL)) {
		login = true;

	    }
	}
	return login;
    }

    @Override
    public void destroy() {
    }
}