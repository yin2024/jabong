package _07_gift.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.model.MemberAccessBean;
import _01_register.model.MemberBean;
import _04_friend.model.FriendBean;
import _07_gift.model.RouletteAccessBean;
import _07_gift.model.RouletteBean;


@WebServlet("/Roulette")
public class Roulette extends HttpServlet {
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
	    response.sendRedirect(response.encodeRedirectURL("/login.jsp"));
	    return;
	}
	MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
	int userID = mb.getUserID();
	// 3. getParameter：抓請求的屬性與參數
	
	// 4. 設定回應
	MemberAccessBean mab = null;
	RouletteAccessBean rab = null;
	try {
	    mab = new MemberAccessBean();
	    mab.setUserID(userID);
	    FriendBean fb = mab.getMember();
	    rab = new RouletteAccessBean();
	    rab.setUserID(userID);
	    Collection<RouletteBean> coll = new ArrayList<>();
	    coll = rab.getAllGifts();
	    request.setAttribute("member", fb);
	    request.setAttribute("roulette_gifts", coll);
	    RequestDispatcher rd = request.getRequestDispatcher("roulette.jsp");
	    rd.forward(request, response);
	    return;
	} catch (Exception e) {
	    e.getStackTrace();
	    System.out.println("NewServlet Exception!");
	}
    }

}
