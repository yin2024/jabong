package _04_friend.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _00_init.GlobalService;
import _01_register.model.MemberBean;
import _03_post.model.PostBean;
import _04_friend.model.FriendAccessBean;
import _04_friend.model.FriendBean;

@WebServlet("/DisplayOneFriend")
public class DisplayOneFriend extends HttpServlet {
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
	
	int friendUserID = 1;
	friendUserID = Integer.parseInt(request.getParameter("id"));

	FriendAccessBean fab = null;
	try {
	    fab = new FriendAccessBean();
	    fab.setFriendUserID(friendUserID);
	    // fab.setPageNo(pageNo);
	    // fab.setRecordsPerPage(GlobalService.RECORDS_PER_PAGE);
	    Collection<FriendBean> coll1 = fab.getFriend();
	    Collection<PostBean> coll2 = fab.getFriendPost();
	    int post_counts = fab.getFriendPostCounts();
	    int place_counts = fab.getFriendPlaceCounts();
	    request.setAttribute("friends_DOF", coll1);
	    request.setAttribute("friends_DOFP", coll2);
	    request.setAttribute("post_counts", post_counts);
	    request.setAttribute("place_counts", place_counts);
	    RequestDispatcher rd = request.getRequestDispatcher("user_profile.jsp");
	    rd.forward(request, response);
	    return;
	} catch (SQLException e) {
	    throw new ServletException(e);
	} catch (NamingException e) {
	    throw new ServletException(e);
	    
	}
    }

}
