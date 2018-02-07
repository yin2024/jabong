package _08_notification.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import _00_init.GlobalService;
import _03_post.model.PostAccessBean;
import _04_friend.model.FriendBean;

public class NotifyAccessBean implements Serializable, NotifyAccessDAO {
    private static final long serialVersionUID = 1L;

    // 1. AccessBean必要準備項目
    // 2. AccessBean次要準備項目
    private DataSource ds = null;
    int userID = 1;
    int sID = 1;
    int oID = 1;
    int type = 1;
    int typeID = 1;

    // 3. 預設建構子：負責取得資料庫連線
    public NotifyAccessBean() throws ServletException, IOException, NamingException {
	Context ctx = new InitialContext();
	ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
    }

    // 4. Setter：負責存取外部參數用
    public void setUserID(int userID) {
	this.userID = userID;
    }

    public void setsID(int sID) {
	this.sID = sID;
    }

    public void setoID(int oID) {
	this.oID = oID;
    }

    public void setType(int type) {
	this.type = type;
    }

    public void setTypeID(int typeID) {
	this.typeID = typeID;
    }

    // 5. 查詢 - 取得所有通知
    public Collection<NotifyBean> getAllNotify() throws SQLException {
	String sql = "SELECT * FROM notify n JOIN user u ON (n.sID = u.userID) WHERE n.oID = ? AND n.click = 0 ORDER BY notifyID DESC ";
	Collection<NotifyBean> coll = new ArrayList<NotifyBean>();
	Connection connection = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, userID);
	    rs = pStmt.executeQuery();
	    while (rs.next()) {
		NotifyBean bean = new NotifyBean();
		bean.notifyID = rs.getInt(1);
		bean.sID = rs.getInt(2);
		bean.oID = rs.getInt(3);
		bean.type = rs.getInt(4);
		bean.typeID = rs.getInt(5);
		bean.check = rs.getInt(6);
		bean.time = rs.getString(7);
		bean.name = rs.getString(11);
		coll.add(bean);
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
	return coll;
    }
    
 // 5.2 查詢 - 取得所有歷史通知
    public Collection<NotifyBean> getAllHistoryNotify() throws SQLException {
	String sql = "SELECT * FROM notify n JOIN user u ON (n.sID = u.userID) WHERE n.oID = ? ORDER BY notifyID DESC ";
	Collection<NotifyBean> coll = new ArrayList<NotifyBean>();
	Connection connection = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, userID);
	    rs = pStmt.executeQuery();
	    while (rs.next()) {
		NotifyBean bean = new NotifyBean();
		bean.notifyID = rs.getInt(1);
		bean.sID = rs.getInt(2);
		bean.oID = rs.getInt(3);
		bean.type = rs.getInt(4);
		bean.typeID = rs.getInt(5);
		bean.check = rs.getInt(6);
		bean.time = rs.getString(7);
		bean.name = rs.getString(11);
		coll.add(bean);
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
	return coll;
    }
    
    // 6. 新增 - 發生互動，新增一筆通知到對方帳戶
    public void insertNotify() throws SQLException {
	String sql = "INSERT INTO notify (sID, oID, type, typeID) VALUES (?, ?, ?, ?)";
	Connection connection = null;
	PreparedStatement pStmt = null;
	int n = 0;
	if (type == 2 || type == 3) {
	    try {
		PostAccessBean pos = new PostAccessBean();
		pos.setPostID(typeID);
		this.oID = pos.getWhoPost();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	// 若自己點自己讚，或自己回應就不要有通知了
	if (userID != oID) {
	    try {
		connection = ds.getConnection();
		pStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		pStmt.setInt(1, userID); // 用戶提出好友邀請時，主ID為用戶本身ID
		pStmt.setInt(2, oID); // 用戶提出好友邀請時，受ID為被邀請人的ID
		pStmt.setInt(3, type); // 用戶提出好友邀請時，type為friend屬性，則為1(預設)
		pStmt.setInt(4, typeID); // typeID為friend table的流水號，以便未來參照friend
					 // table使用
		n = pStmt.executeUpdate();

	    } finally {
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
	}
    }

    // 7. 修改 - 通知已讀，修改這筆通知的click欄位
    public void clickNotify() throws SQLException {
	Connection connection = null;
	PreparedStatement pStmt = null;
	String sql = "UPDATE notify SET click = 1 WHERE oID = ? AND type = ? ";
	int n = 0;
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    pStmt.setInt(1, userID);
	    pStmt.setInt(2, type);
	    n = pStmt.executeUpdate();
	} finally {
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
    }
}
