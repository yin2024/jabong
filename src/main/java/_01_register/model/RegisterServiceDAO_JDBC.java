package _01_register.model;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import _00_init.*;
import _02_login.model.*;

// 註冊服務DAO - 與JDBC建立連結的實作程式
public class RegisterServiceDAO_JDBC implements RegisterServiceDAO {

    // 1. 先定義好一些必要屬性，包含DataSource、登入服務物件LoginServiceDB。
    private List<MemberBean> memberList;
    private DataSource ds = null;
    LoginServiceDB lsdb;

    // 2. 預設建構子，建立與JDBC連結、登入物件以取得現存的會員資料(驗證重複帳號用)。
    public RegisterServiceDAO_JDBC() throws NamingException, SQLException {
	Context ctx = new InitialContext();
	ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
	lsdb = new LoginServiceDB();
	memberList = lsdb.getMemberList();
    }

    // 3. 驗證帳號是否重複申請。 synchronized包住此方法，來避免多執行緒同時申請帳號的同步問題。
    synchronized public boolean idExists(String id) throws IOException {
	boolean exist = false;
	for (MemberBean mb : memberList) {
	    System.out.println(mb.getAccount());
	    if (mb.getAccount().equals(id.trim())) {
		exist = true;
		break;
	    }
	}
	return exist;
    }

    // 4. 方法 - 存取欲註冊的帳戶資料。
    synchronized public int saveMember(MemberBean mb, InputStream is, long size) throws SQLException {
	Connection conn = ds.getConnection();
	PreparedStatement pstmt1 = null;
	ResultSet rs = null;
	int gKey = 0;
	int rs1 = 0;
	PreparedStatement pstmt2 = null;
	int rs2 = 0;

	try {
	    // 4.1 查詢一 - 負責輸入文字資料。
	    String sql1 = "INSERT INTO user (account, password, name, point, email, cellphone, createTime) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    // 開始準備查詢，幫問號配上應對的文字。
	    pstmt1 = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
	    pstmt1.setString(1, mb.getAccount());
	    // 密碼 - 運用MD5加密。
	    String encrypedString = GlobalService.encryptString(mb.getPassword());
	    pstmt1.setString(2, GlobalService.getMD5Endocing(encrypedString));
	    pstmt1.setString(3, mb.getName());
	    // 點數 - 申請帳號給予1000之點數獎勵。
	    pstmt1.setInt(4, 1000);
	    pstmt1.setString(5, mb.getEmail());
	    pstmt1.setString(6, mb.getCellphone());
	    // 申請帳號時間 - 按照前端申請的時間為主。
	    pstmt1.setString(7, mb.getCreateTime());
	    // 查詢開始：用rs1去接成果。
	    rs1 = pstmt1.executeUpdate();
	    rs = pstmt1.getGeneratedKeys();
	    while (rs.next()) {
		gKey = rs.getInt(1);
	    }

	    // 4.2 查詢二 - 負責輸入預設大頭照。
	    String sql2 = "INSERT INTO userImage (userID, image) VALUES (?, ?)";
	    // 開始準備查詢，幫問號配上應對的物品。
	    pstmt2 = conn.prepareStatement(sql2);
	    // 因為不知道userID到幾號了，先用帳號去對應預設的照片，之後再讓使用者修改自己要的大頭照。
	    pstmt2.setInt(1, gKey);
	    pstmt2.setBinaryStream(2, is, size);
	    // pstmt2.setBlob(2, is, size); // setBlob方法目前未支援
	    // 查詢開始：用rs2去接成果。
	    rs2 = pstmt2.executeUpdate();
	    if (rs1 == 1) {
		// 註冊成功，先將使用者密碼用MD5加密好，以便往後登入驗證用。
		System.out.println("帳號註冊成功！");
		mb.setPassword(GlobalService.getMD5Endocing(encrypedString));
		// 將MemberBean加入LoginService的memberList內，讓註冊好的帳號直接登入網站。
		memberList.add(mb);
	    } else {
		throw new SQLException("RegisterServiceDB:新增記錄數 : 0");
	    }
	} finally {
	    if (pstmt1 != null) {
		try {
		    pstmt1.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	    if (pstmt2 != null) {
		try {
		    pstmt1.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	    if (conn != null) {
		try {
		    conn.close();
		} catch (Exception e) {
		    System.err.println("關閉相關物件時發生例外: " + e);
		}
	    }
	}
	return gKey;
    }
}
