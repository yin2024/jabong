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

@MultipartConfig(location = "", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024
	* 1024 * 500 * 5)
@WebServlet("/InsertPost.do")
public class InsertPost extends HttpServlet {
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

	// from View
	InputStream is = null;
	long sizeInBytes = 0;
	Collection<Part> parts = request.getParts(); // 取出HTTP multipart
						     // request內所有的parts
	GlobalService.exploreParts(parts, request);
	int star = Integer.valueOf(request.getParameter("rating"));
	String place = request.getParameter("place");
	String comment = request.getParameter("comment");
	String price = request.getParameter("price");
	System.out.println("comment=" + comment);
	int x = Integer.valueOf(request.getParameter("x"));
	int y = Integer.valueOf(request.getParameter("y"));
	int w = Integer.valueOf(request.getParameter("w"));
	int h = Integer.valueOf(request.getParameter("h"));
	int pw = Integer.valueOf(request.getParameter("pw"));
	int ph = Integer.valueOf(request.getParameter("ph"));
	double lat = Double.valueOf(request.getParameter("lat"));
	double lng = Double.valueOf(request.getParameter("lng"));
	
	String fileName;
	String file;
	ImageProcess ip = null;
	if (parts != null) {
	    for (Part p : parts) {
		// String fldName = p.getName();
		if (p.getContentType() != null) {
		    file = GlobalService.getFileName(p); // 此為圖片檔的檔名
		    fileName = GlobalService.adjustFileName(file, GlobalService.IMAGE_FILENAME_LENGTH);
		    if (fileName != null && fileName.trim().length() > 0) {
			System.out.println(sizeInBytes = p.getSize());
			System.out.println(is = p.getInputStream());
			ip = new ImageProcess(is);
		    }

		}
	    }
	}

	// String path = "/images";
	// String inFile = "/girl2.jpg";
	// String realPath = getServletContext().getRealPath(path);
	// ip.clipImage(x, y, w, h, pw, ph);
	ip.clipImageToInputStream(x, y, w, h, pw, ph);
	is = ip.is;
	System.out.println(is);
	sizeInBytes = ip.sizeInBytes;
	System.out.println(sizeInBytes);
	//
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
	    pab.setUserID(userID);
	    pab.insertPost(pb, is, sizeInBytes);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	// successMsgs.put("success", "資料新增成功");
	// RequestDispatcher rd = request.getRequestDispatcher("DisplayPosts");
	// rd.forward(request, response);
	response.sendRedirect(response.encodeRedirectURL("DisplayPosts"));
	return;
    }

}
