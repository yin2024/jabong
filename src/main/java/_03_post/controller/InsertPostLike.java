package _03_post.controller;

import java.io.IOException;
import java.io.InputStream;

import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import _00_init.GlobalService;
import _01_register.model.MemberBean;
import _03_post.model.PostAccessBean;
import _03_post.model.PostBean;
import _03_post.model.PostLikeBean;
import _03_post.model.PostRespBean;
import _08_notification.model.NotifyAccessBean;

@WebServlet("/InsertPostLike.do")
public class InsertPostLike extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	HttpSession session = request.getSession(false);
	if (session == null) {
	    response.sendRedirect(response.encodeRedirectURL("/login.jsp"));
	    return;
	}
	MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
	int userID = mb.getUserID();

	// 本程式負責接收 文章 的回應推文。
	int postID = Integer.parseInt(request.getParameter("postID"));
	
	PostLikeBean plb = new PostLikeBean();
	plb.setPostID(postID);
	plb.setUserID(userID);
	plb.setLike(1);
	PostAccessBean pab;
	NotifyAccessBean nab;
	
	try {
	    pab = new PostAccessBean();
	    pab.setUserID(userID);
	    pab.setPostID(postID);
	    pab.insertPostLike(plb);
	    
	    nab = new NotifyAccessBean();
	    nab.setUserID(userID);
	    nab.setsID(userID);
	    nab.setType(2);
	    nab.setTypeID(postID);
	    nab.insertNotify();
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	// successMsgs.put("success", "資料新增成功");
	// RequestDispatcher rd = request.getRequestDispatcher("/DisplayPosts");
	// rd.forward(request, response);
	response.sendRedirect(response.encodeRedirectURL("DisplayPosts"));
	return;
    }

}
