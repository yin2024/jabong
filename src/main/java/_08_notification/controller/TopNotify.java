package _08_notification.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.NamingException;
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

@WebServlet("/TopNotify")
public class TopNotify extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	response.setContentType("application/json; charset=utf-8");
	HttpSession session = request.getSession(false);
	if (session == null) {
	    response.sendRedirect(response.encodeRedirectURL("/login.jsp"));
	    return;
	}
	MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
	int userID = mb.getUserID();

	// 英睿： 抓使用者的Notification，每次載入本Servlet，就要抓一次資料庫看有沒有最新通知。
	Collection<NotifyBean> coll = new ArrayList<NotifyBean>();
	NotifyAccessBean nab;
	try {
	    nab = new NotifyAccessBean();
	    nab.setUserID(userID);
	    coll = nab.getAllNotify();
	    Gson gson = new Gson();
	    String jsonDataBean = gson.toJson(coll);
	    // System.out.println(jsonDataBean);
	    PrintWriter out = response.getWriter();
	    out.write(jsonDataBean);
	    out.close();
	} catch (NamingException e1) {
	    e1.printStackTrace();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }
}
