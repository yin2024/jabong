package _03_post.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

@WebServlet("/EditPost")
public class EditPost extends HttpServlet {
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
	int postID = Integer.valueOf(request.getParameter("id"));

	PostAccessBean pab = null;
	try {
	    pab = new PostAccessBean();
	    pab.setUserID(userID);
	    pab.setPostID(postID);
	    PostBean pb = pab.getPost();
	    Collection<PostBean> pbc = new ArrayList<>();
	    pbc.add(pb);
	    request.setAttribute("post_DOP", pbc);
	    RequestDispatcher rd = request.getRequestDispatcher("post_edit.jsp");
	    rd.forward(request, response);
	    return;
	} catch (Exception e) {
	}
    }

}
