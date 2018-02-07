package _05_world.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

@WebServlet("/DisplayAllFriendPlaces")
public class DisplayAllFriendPlaces extends HttpServlet {
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
	    fab = new FriendAccessBean();
	    fab.setUserID(userID);

	    Collection<FriendBean> coll = fab.getAllFriends();	
	    Collection<FriendBean> collFriend = new ArrayList<FriendBean>();
	    for (FriendBean fb : coll) {
		fab.setFriendUserID(fb.getUserID());
		List<PostBean> list = fab.getFriendPlace();
		String categoriesJson = new Gson().toJson(list);
		fb.setPostJson(categoriesJson);
		System.out.println(categoriesJson);
		collFriend.add(fb);
	    }
	    
	    request.setAttribute("friends_DAF", collFriend);
	    
	    RequestDispatcher rd = request.getRequestDispatcher("world_friend.jsp");
	    rd.forward(request, response);
	    return;
	} catch (SQLException e) {
	    throw new ServletException(e);
	} catch (NamingException e) {
	    throw new ServletException(e);

	}
    }

}
