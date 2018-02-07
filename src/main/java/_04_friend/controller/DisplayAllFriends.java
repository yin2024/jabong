package _04_friend.controller;

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

import _00_init.GlobalService;
import _01_register.model.MemberBean;
import _04_friend.model.FriendAccessBean;
import _04_friend.model.FriendBean;

@WebServlet("/DisplayAllFriends")
public class DisplayAllFriends extends HttpServlet {
    private static final long serialVersionUID = 1L;
    int pageNo = 1;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	 HttpSession session = request.getSession(false);
	 if (session == null) {
	 response.sendRedirect(response.encodeRedirectURL("login.jsp"));
	 return;
	 }
	 MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
	 int userID = mb.getUserID();

	
	FriendAccessBean fab = null;
	try {
	    String pageNoStr = request.getParameter("pageNo");
	    // if (pageNoStr == null) {
	    // pageNo = 1;
	    // Cookie[] cookies = request.getCookies();
	    // if (cookies != null) {
	    // for (Cookie c : cookies) {
	    // // System.out.println("DisplayPageProducts.java,
	    // // getName=" + c.getName());
	    // if (c.getName().equals(memberId + "pageNo")) {
	    //
	    // try {
	    // pageNo = Integer.parseInt(c.getValue().trim());
	    // } catch (NumberFormatException e) {
	    // ;
	    // }
	    // break;
	    // }
	    // }
	    // }
	    // } else {
	    // try {
	    // pageNo = Integer.parseInt(pageNoStr.trim());
	    // } catch (NumberFormatException e) {
	    // pageNo = 1;
	    // }
	    // }
	    fab = new FriendAccessBean();
	    fab.setUserID(userID);
	    fab.setPageNo(pageNo);
	    fab.setRecordsPerPage(GlobalService.RECORDS_PER_PAGE);
	    Collection<FriendBean> coll = fab.getAllFriends();
//	    request.setAttribute("pageNo", pageNo);
//	    request.setAttribute("totalPages", fab.getTotalPages());
//	    request.setAttribute("recordsPerPage", GlobalService.RECORDS_PER_PAGE);
	    request.setAttribute("friends_DAF", coll);

	    // -----------------------
	    // Cookie pnCookie = new Cookie(memberId + "pageNo",
	    // String.valueOf(pageNo));
	    // pnCookie.setMaxAge(30 * 24 * 60 * 60);
	    // pnCookie.setPath(request.getContextPath());
	    // response.addCookie(pnCookie);
	    // -----------------------
	    // 交由listBooks.jsp來顯示某頁的書籍資料，同時準備『第一頁』、
	    // 『前一頁』、『下一頁』、『最末頁』等資料
	    RequestDispatcher rd = request.getRequestDispatcher("friends.jsp");
	    rd.forward(request, response);
	    return;
	} catch (SQLException e) {
	    throw new ServletException(e);
	} catch (NamingException e) {
	    throw new ServletException(e);
	}
    }

}
