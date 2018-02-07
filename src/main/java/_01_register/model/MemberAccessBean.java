package _01_register.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import _00_init.GlobalService;
import _04_friend.model.FriendBean;

public class MemberAccessBean implements Serializable {
    // 1. AccessBean必要準備項目：
    private static final long serialVersionUID = 1L;
    private DataSource ds = null;
    private int userID = 1;

    // 2. AccessBean次要準備項目：(可能會set的參數)

    // 3. 預設建構子：負責取得資料庫連線
    public MemberAccessBean() throws ServletException, IOException, NamingException {
	Context ctx = new InitialContext();
	ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
    }
    
    // 4. Setter：負責存取外部參數用
    public void setUserID(int userID) {
        this.userID = userID;
    }

    // 5. 查詢：使用者詳細資訊
    public FriendBean getMember() throws SQLException {
	FriendBean bean = new FriendBean();
	Connection connection = null;
	PreparedStatement pStmt = null;
	String sql = "SELECT * FROM user WHERE userID = ? ";
	ResultSet rs = null;
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, userID);
	    rs = pStmt.executeQuery();
	    while (rs.next()) {
		bean = new FriendBean();
		bean.setUserID(rs.getInt(1));
		bean.setAccount(rs.getString(2));
		bean.setName(rs.getString(4));
		bean.setComment(rs.getString(11));
		bean.setMutual(5); // 寫方法！
		bean.setPoint(rs.getInt(5));
		bean.setEmail(rs.getString(6));
		bean.setTel(rs.getString(7));
		bean.setCreate(rs.getString(8));
		bean.setLogin(rs.getString(9));
		bean.setBirth(rs.getString(10));
	    }

	} finally {
	    if (rs != null) {
		try {
		    rs.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (pStmt != null) {
		try {
		    pStmt.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }

	    if (connection != null) {
		try {
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return bean;
    }

}
