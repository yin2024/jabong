package _08_notification.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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
import _08_notification.model.NotifyAccessBean;
import _08_notification.model.NotifyBean;

@WebServlet("/Notification")
public class Notification extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
	// 英睿： 抓使用者的Notification，每次載入本Servlet，就要抓一次資料庫看有沒有最新通知。
	Collection<NotifyBean> coll = new ArrayList<NotifyBean>();
	NotifyAccessBean nab;
	try {
	    nab = new NotifyAccessBean();
	    nab.setUserID(userID);
	    coll = nab.getAllHistoryNotify();
	    request.setAttribute("notification", coll);
	    RequestDispatcher rd = request.getRequestDispatcher("notification.jsp");
	    rd.forward(request, response);
	    return;
	} catch (NamingException e1) {
	    e1.printStackTrace();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }
}
