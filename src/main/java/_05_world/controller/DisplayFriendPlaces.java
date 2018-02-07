package _05_world.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/DisplayFriendPlaces")
public class DisplayFriendPlaces extends HttpServlet {
    private static final long serialVersionUID = 1L;
    int pageNo = 1;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	response.setContentType("application/json; charset=utf-8");
	try {
	    String[] strArr = request.getParameter("friendUserID").split(",");
	    List<PostBean> list = new ArrayList<>();
	    FriendAccessBean fab = null;
	    try {
		for (String s : strArr) {
		    int friendUserID = Integer.parseInt(s);
		    System.out.println(friendUserID);
		    fab = new FriendAccessBean();
		    fab.setFriendUserID(friendUserID);
		    list.addAll(fab.getFriendPlace());
		}
		String categoriesJson = new Gson().toJson(list);
		PrintWriter out = response.getWriter();
		out.write(categoriesJson);
		out.close();
	    } catch (SQLException e) {
		throw new ServletException(e);
	    } catch (NamingException e) {
		throw new ServletException(e);

	    }
	} catch (NumberFormatException e) {
	    System.out.println("WORLD NULL");
	}
    }

}
