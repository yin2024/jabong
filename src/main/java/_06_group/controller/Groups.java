package _06_group.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.model.MemberBean;
import _06_group.model.GroupAccessBean;
import _06_group.model.GroupBean;

@WebServlet("/Groups")
public class Groups extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// 1. 設定請求編碼
	request.setCharacterEncoding("UTF-8");
	// 2. Session：抓用戶的userID
	HttpSession session = request.getSession(false);
	if (session == null) {
	    response.sendRedirect(response.encodeRedirectURL("login.jsp"));
	    return;
	}
	MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
	int userID = mb.getUserID();
	// 3. getParameter：抓請求的屬性與參數

	// 4. 設定回應
	Collection<GroupBean> coll = null;
	GroupAccessBean gab = null;
	try {
	    gab = new GroupAccessBean();
	    gab.setUserID(userID);
	    coll = gab.getSixGroups();
	    request.setAttribute("six_groups", coll); 
	    RequestDispatcher rd = request.getRequestDispatcher("groups.jsp");
	    rd.forward(request, response);
	    return;
	} catch (SQLException e) {
	    throw new ServletException(e);
	} catch (NamingException e) {
	    e.printStackTrace();
	}
    }

}
