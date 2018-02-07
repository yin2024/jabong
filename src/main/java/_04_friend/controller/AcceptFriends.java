package _04_friend.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
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
import _08_notification.model.NotifyBean;

@WebServlet("/AcceptFriends")
public class AcceptFriends extends HttpServlet {
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

	// 3. 準備一個FriendBean，將資料丟進去。

	// 2-3 準備好朋友專用的AccessBean，並丟入參數以便查詢。
	FriendAccessBean fab = null;
	NotifyAccessBean nab = null;
	try {
	    fab = new FriendAccessBean();
	    fab.setUserID(userID);
	    Collection<FriendBean> coll = fab.getRequestFriends();
	    request.setAttribute("friends_DRF", coll);
	    // 用戶看到通知了，取消通知
	    nab = new NotifyAccessBean();
	    nab.setUserID(userID);
	    nab.setType(1); // 1 = friend
	    nab.clickNotify();
	    // 英睿： 抓使用者的Notification，識別字串為"Notify"
	    Collection<NotifyBean> ncoll = new ArrayList<NotifyBean>();
	    NotifyAccessBean nabs;
	    try {
		nabs = new NotifyAccessBean();
		nabs.setUserID(mb.getUserID());
		ncoll = nabs.getAllNotify();
		System.out.println(ncoll);
		session.setAttribute("Notify", ncoll);
	    } catch (NamingException e1) {
		e1.printStackTrace();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    RequestDispatcher rd = request.getRequestDispatcher("accept_friends.jsp");
	    rd.forward(request, response);
	    return;
	} catch (NamingException e) {
	    e.printStackTrace();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

}
