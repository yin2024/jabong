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

@WebServlet("/SearchFriends.do")
public class SearchFriends extends HttpServlet {
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
	// 2. 接收表單資訊
	// 2-1 將接收資訊編碼設為UTF8，避免亂碼。
	request.setCharacterEncoding("UTF-8");
	// 2-2 宣告好要接收的資訊，並取得這些值。
	String searchType = null;
	String searchText = null;
	searchType = request.getParameter("sf");
	searchText = request.getParameter("sf_value");
	System.out.println(searchText);
	System.out.println(searchType);
	// 3. 準備一個FriendBean，將資料丟進去。

	// 2-3 準備好朋友專用的AccessBean，並丟入參數以便查詢。
	FriendAccessBean fab = null;
	try {
	    fab = new FriendAccessBean();
	    fab.setUserID(userID);
	    fab.setSearchText(searchText);
	    fab.setSearchType(searchType);
	    Collection<FriendBean> coll = fab.getSearchFriends();
	    request.setAttribute("friends_DSF", coll);
	    RequestDispatcher rd = request.getRequestDispatcher("search_friends.jsp");
	    rd.forward(request, response);
	    return;
	} catch (NamingException e) {
	    e.printStackTrace();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

}
