package _03_post.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.model.MemberBean;
import _03_post.model.PostAccessBean;
import _03_post.model.PostBean;
import _03_post.model.PostLikeBean;

@WebServlet("/DisplayOnePost")
public class DisplayOnePost extends HttpServlet {
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
	    response.sendRedirect(response.encodeRedirectURL("login.jsp"));
	    return;
	}
	MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
	int userID = mb.getUserID();
	// 3. getParameter：抓請求的屬性與參數
	int postID = Integer.parseInt(request.getParameter("id"));

	PostAccessBean pab = null;
	try {
	    pab = new PostAccessBean();
	    pab.setUserID(userID);
	    pab.setPostID(postID);
	    List<PostBean> list = new ArrayList<>();
	    PostBean bean = pab.getPost();
	    System.out.println(bean);
	    list.add(bean);

	    Label: for (int i = 0; i < list.size(); i++) {
		PostBean pb = list.get(i);
		for (PostLikeBean plb : pb.getPostLikeBeans()) {
		    if (plb.getUserID() == userID) {
			pb.setPostLikeCheck(1);
			list.set(i, pb);
			continue Label;
		    }
		}
	    }
	    
	    request.setAttribute("post_DPP", list);
	    RequestDispatcher rd = request.getRequestDispatcher("one_post.jsp");
	    rd.forward(request, response);
	    return;
	} catch (SQLException e) {
	    throw new ServletException(e);
	} catch (NamingException e) {
	    throw new ServletException(e);
	}
    }
}