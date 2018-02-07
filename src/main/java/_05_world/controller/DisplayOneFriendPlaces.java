package _05_world.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import _01_register.model.MemberBean;
import _03_post.model.PostBean;
import _04_friend.model.FriendAccessBean;
import _04_friend.model.FriendBean;

@WebServlet("/DisplayOneFriendPlaces")
public class DisplayOneFriendPlaces extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
	    Collection<FriendBean> coll = fab.getFriend();
	    List<PostBean> list = fab.getFriendPlace();
	    String categoriesJson = new Gson().toJson(list); 
	    System.out.println(categoriesJson);
	    int place_counts = fab.getFriendPlaceCounts();
	    System.out.println(place_counts);
	    request.setAttribute("friend_coll", coll);
	    request.setAttribute("place_spotJson", categoriesJson);
	    request.setAttribute("place_counts", place_counts);
	    RequestDispatcher rd = request.getRequestDispatcher("user_world.jsp");
	    rd.forward(request, response);
	    return;
	} catch (SQLException e) {
	    throw new ServletException(e);
	} catch (NamingException e) {
	    throw new ServletException(e);

	}
    }

}
