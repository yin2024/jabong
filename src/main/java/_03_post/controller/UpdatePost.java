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

// 本程式進行動作：修改動態文章。
@WebServlet("/UpdatePost.do")
public class UpdatePost extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// 1. 設定好瀏覽器給予的請求編碼為UTF-8。
	request.setCharacterEncoding("UTF-8");
	// 2. 取得session。
	HttpSession session = request.getSession(false);
	if (session == null) {
	    response.sendRedirect(response.encodeRedirectURL("/login.jsp"));
	    return;
	}
	// 3. 取得用戶帳號。
	MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
	int userID = mb.getUserID();
	int star = Integer.valueOf(request.getParameter("rating"));
	String place = request.getParameter("place");
	String comment = request.getParameter("comment");
	String price = request.getParameter("price");
	System.out.println("comment=" + comment);
	double lat = Double.valueOf(request.getParameter("lat"));
	double lng = Double.valueOf(request.getParameter("lng"));
	int postID = Integer.valueOf(request.getParameter("postID"));
	
	PostBean pb = new PostBean();
	pb.setUserID(userID);
	pb.setPlace(place);
	pb.setLat(lat);
	pb.setLng(lng);
	pb.setPrice(price);
	pb.setComment(comment);
	pb.setStar(star);
	PostAccessBean pab;
	try {
	    pab = new PostAccessBean();
	    pab.setPostID(postID);
	    pab.setUserID(userID);
	    pab.updatePost(pb);
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
