package _03_post.controller;

import java.awt.Label;
import java.io.IOException;
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

import _01_register.model.MemberBean;
import _03_post.model.PostAccessBean;
import _03_post.model.PostBean;
import _03_post.model.PostLikeBean;
import _08_notification.model.NotifyAccessBean;
import _08_notification.model.NotifyBean;

@WebServlet("/DisplayPosts")
public class DisplayPosts extends HttpServlet {
    private static final long serialVersionUID = 1L;
    int pageNo = 1;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	// ==============1.Session===============
	HttpSession session = request.getSession(false);
	// 紀錄目前請求的RequestURI,以便使用者登入成功後能夠回到原本的畫面
	// 如果session物件不存在=>請使用者登入
	if (session == null) {
	    response.sendRedirect(response.encodeRedirectURL("/login.jsp"));
	    return;
	}
	// 存在=>Login成功=>取得使用者帳號
	MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
	int userID = mb.getUserID();
	
	// =====================================
	//
	// ==============2.Post的呈現===============
	// 本類別負責讀取資料庫內post表格內對應自己與朋友所post之所有紀錄。
	PostAccessBean pab = null;
	try {
	    pab = new PostAccessBean();
	    // 指定查詢postID為1的post
	    pab.setUserID(userID);
	    // bab.getPageBooks():讀取某一頁的所有紀錄
	    List<PostBean> list = pab.getAllPosts();
	    
	    Label:
	    for (int i = 0 ; i < list.size() ; i++) {
		PostBean pb = list.get(i);
		for (PostLikeBean plb : pb.getPostLikeBeans()) {
		    if ( plb.getUserID() == userID) {
			pb.setPostLikeCheck(1);
			list.set(i, pb);
			continue Label;
		    }
		}
	    }
	    // request.setAttribute("pageNo", pageNo);
	    request.setAttribute("totalPages", pab.getAllPosts());
	    // request.setAttribute("recordsPerPage",
	    // GlobalService.RECORDS_PER_PAGE);
	    request.setAttribute("post_DPP", list);
	    // 交由index.jsp來顯示某頁的post資料。
	    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	    rd.forward(request, response);
	    return;
	} catch (SQLException e) {
	    throw new ServletException(e);
	} catch (NamingException e) {
	    throw new ServletException(e);
	}
    }
}