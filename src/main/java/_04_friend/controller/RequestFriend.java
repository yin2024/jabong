package _04_friend.controller;

import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.http.Part;

import _00_init.GlobalService;
import _01_register.model.MemberBean;
import _04_friend.model.FriendAccessBean;
import _04_friend.model.FriendBean;
import _08_notification.model.NotifyAccessBean;

@WebServlet("/RequestFriend.do")
public class RequestFriend extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// 1. 抓session並取得使用者的ID
	HttpSession session = request.getSession(false);
	if (session == null) {
	    response.sendRedirect(response.encodeRedirectURL("login.jsp"));
	    return;
	}
	MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
	int userID = mb.getUserID();
	// 2. 接收表單資訊 -> none
	int oID = 0;
	oID = Integer.parseInt(request.getParameter("oID"));
	
	FriendAccessBean fab = null;
	NotifyAccessBean nab = null;
	int gKey = 0;
	try {
	    
	    fab = new FriendAccessBean();
	    fab.setUserID(userID);
	    gKey = fab.toRequestFriend(oID);
	    
	    nab = new NotifyAccessBean();
	    nab.setUserID(userID);
	    nab.setsID(userID);
	    nab.setoID(oID);
	    nab.setType(1);
	    nab.setTypeID(gKey);
	    nab.insertNotify();
	    
	    RequestDispatcher rd = request.getRequestDispatcher("settings.jsp");
	    rd.forward(request, response);
	    return;
	    
	} catch (NamingException e) {
	    e.printStackTrace();
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
