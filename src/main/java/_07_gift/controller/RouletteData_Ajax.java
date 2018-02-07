package _07_gift.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.text.spi.NumberFormatProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import _01_register.model.MemberBean;
import _07_gift.model.GiftBean;
import _07_gift.model.RouletteAccessBean;
import _07_gift.model.RouletteBean;

@WebServlet("/RouletteData_Ajax")
public class RouletteData_Ajax extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpSession session = request.getSession(false);
	if (session == null) {
	    response.sendRedirect(response.encodeRedirectURL("/login.jsp"));
	    return;
	}
	MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
	int userID = mb.getUserID();
	request.setCharacterEncoding("UTF-8");
	response.setContentType("application/json; charset=utf-8");
	RouletteAccessBean rab = null;
	try {
	    rab = new RouletteAccessBean();
	    rab.setUserID(userID);
	    RouletteBean bean = rab.getGift();
	    rab.insertGift();
	    Gson gson = new Gson();
	    String jsonDataBean = gson.toJson(bean);
	    PrintWriter out = response.getWriter();
	    out.write(jsonDataBean);
	    out.close();
	} catch (NamingException e) {
	    e.printStackTrace();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}
