package _01_register.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import _01_register.model.*;

/*
 * 本程式讀取使用者輸入資料，進行必要的資料轉換，檢查使用者輸入資料，
 * 進行Business Logic運算，依照Business Logic運算結果來挑選適當的畫面
 * 
 */
//
//啟動檔案上傳的功能：
//1. <form>標籤的 method屬性必須是"post", 而且
//    enctype屬性必須是"multipart/form-data"
//注意：enctype屬性的預設值為"application/x-www-form-urlencoded"
//2. 定義可以挑選上傳檔案的表單欄位：
//<input type='file' name='user-defined_name' />
//
//所謂 HTTP multipart request是指由Http客戶端(如瀏覽器)所建構的ㄧ種請求，
//用來上傳一般的表單資料(form data)與檔案。
//參考網頁：http://stackoverflow.com/questions/913626/what-should-a-multipart-http-request-with-multiple-files-look-like
//
//Servlet規格書一直到Servlet 3.0才提出標準API將檔案上傳的功能標準化。
//
//在Servlet 3.0中，若要能夠處理瀏覽器送來的HTTP multipart request, 
//我們撰寫的Servlet程式必須以註釋
//『javax.servlet.annotation.MultipartConfig』來加以說明。
//
//MultipartConfig的屬性說明:
//location: 上傳之表單資料與檔案暫時存放在Server端之路徑，此路徑必須存在。
//fileSizeThreshold: 檔案的大小臨界值，超過此臨界值，上傳檔案會用存放在硬碟，
//否則存放在主記憶體。
//maxFileSize: 上傳單一檔案之長度限制，如果超過此數值，Web Container會丟出例外
//maxRequestSize: 上傳所有檔案之總長度限制，如果超過此數值，Web Container會丟出例外
@MultipartConfig(location = "", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024
	* 1024 * 500 * 5)
@WebServlet("/register.do")
public class RegisterServletMP extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	request.setCharacterEncoding("UTF-8");
	Map<String, String> errorMsg = new HashMap<String, String>();
	Map<String, String> msgOK = new HashMap<String, String>();
	HttpSession session = request.getSession();
	// 顯示錯誤訊息
	request.setAttribute("MsgMap", errorMsg);
	// 顯示正常訊息
	session.setAttribute("MsgOK", msgOK);
	// 1. 讀取使用者輸入的資料。
	String account = null;
	String password = null;
	String email = null;
	String cellphone = null;
	account = request.getParameter("name");
	password = request.getParameter("pw");
	email = request.getParameter("email");
	cellphone = request.getParameter("mobile");
	System.out.println(account);
	System.out.println(password);
	System.out.println(email);
	System.out.println(cellphone);
	// 2. 讀取預設的大頭照片
	InputStream is = null;
	long sizeInBytes = 0;
	String path = "/images/girl2.jpg";
	String realPath = getServletContext().getRealPath(path);
	File file = new File(realPath);
	is = new FileInputStream(file);
	sizeInBytes = file.length();
	// 3. 檢查使用者輸入資料
	if (account == null || account.trim().length() == 0) {
	    errorMsg.put("errorIDEmpty", "帳號欄必須輸入");
	}
	if (password == null || password.trim().length() == 0) {
	    errorMsg.put("errorPasswordEmpty", "密碼欄必須輸入");
	}
	if (email == null || email.trim().length() == 0) {
	    errorMsg.put("errorEmail", "電子郵件欄必須輸入");
	}
	if (cellphone == null || cellphone.trim().length() == 0) {
	    errorMsg.put("errorTel", "電話號碼欄必須輸入");
	}
	// 如果有錯誤
	if (!errorMsg.isEmpty()) {
	    // 導向原來輸入資料的畫面，這次會顯示錯誤訊息
	    RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
	    rd.forward(request, response);
	    // return;
	}
	// 4. 進行Business Logic運算
	// RegisterServiceFile類別的功能：
	// (1) - 檢查帳號是否已經存在
	// (2) - 儲存會員的資料
	try {
	    RegisterServiceDAO rs = new RegisterServiceDAO_JDBC();
	    if (rs.idExists(account)) {
		errorMsg.put("errorIDDup", "此代號已存在，請換新代號");
	    } else {
		MemberBean mem = new MemberBean();
		mem.setAccount(account);
		mem.setName(account);
		mem.setPassword(password);
		mem.setEmail(email);
		mem.setCellphone(cellphone);
		int userID = rs.saveMember(mem, is, sizeInBytes);
		// 將MemberBean mem立即寫入Database
		if (userID > 0) {
		    mem.setUserID(userID);
		    msgOK.put("InsertOK", "<Font color='red'>新增成功，請開始使用本系統</Font>");
		    session.setAttribute("LoginOK", mem);
		    response.sendRedirect(request.getContextPath()+ "/DisplayPosts");
		    return;
		} else {
		    errorMsg.put("errorIDDup", "新增此筆資料有誤(RegisterServlet)");
		}
	    }
	    // 5.依照 Business Logic 運算結果來挑選適當的畫面
	    if (!errorMsg.isEmpty()) {
		// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
		RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
		rd.forward(request, response);
		return;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    errorMsg.put("errorIDDup", e.getMessage());
	    RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
	    rd.forward(request, response);
	}
    }
}