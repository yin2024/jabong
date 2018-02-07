package _04_friend.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import _00_init.GlobalService;
import _03_post.model.PostBean;
import _08_notification.model.NotifyAccessBean;

// 與資料庫建立連結的Model Bean，Bean需實作Serializable
public class FriendAccessBean implements Serializable {
    private static final long serialVersionUID = 1L;

    // 1. 宣告 DataSource 、 主鍵 (friendID) 、 一些非屬FriendBean的屬性，如頁碼。
    private DataSource ds = null;
    private int userID = 1;
    private int friendUserID = 1;
    // - 使用者看到第？頁。
    private int pageNo = 0;
    // - 每頁共有？筆資料。
    private int recordsPerPage = GlobalService.RECORDS_PER_PAGE;
    // - 共？頁。＊
    private int totalPages = -1;
    private String searchType = null;
    private String searchText = null;
    private int oID = 1;
    private String time = "day";
    private int place_counts = 0;
    private int post_counts = 0;
    // - type是給Notify那邊用的，辨別朋友邀請通知、推文通知、按讚通知等等。
    private int type = 1;

    // 2. 預設建構子，本Bean被實體化時，會先初始化與資料庫的連結環境。
    public FriendAccessBean() throws ServletException, IOException, NamingException {
	Context ctx = new InitialContext();
	ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
    }

    // 2.1 更新資料用，當新增刪除修改資料後，autoIncKey到幾號用此方法。
    public int getLastAutoIncKey(String sql) throws SQLException {
	Connection connection = null;
	Statement stmt = null;
	ResultSet rs = null;
	int autoIncKeyFromApi = -1;
	try {
	    connection = ds.getConnection();
	    stmt = connection.createStatement();
	    stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
	    rs = stmt.getGeneratedKeys();
	    if (rs.next()) {
		autoIncKeyFromApi = rs.getInt(1);
	    }
	} finally {
	    if (rs != null) {
		try {
		    rs.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (stmt != null) {
		try {
		    stmt.close();
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
	return autoIncKeyFromApi;
    }

    // 3. 運用Setter來取得使用者ID，以辨找尋他的朋友們。
    public void setUserID(int userID) {
	this.userID = userID;
    }

    // 4. 先計算使用者有多少位朋友？
    public int getRecordCounts() throws SQLException {
	String sql = "SELECT COUNT(*) FROM friend WHERE (oID = ? ) and status = 1";
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	Connection connection = null;
	int result = 0;
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, userID);
	    rs = pStmt.executeQuery();
	    if (rs.next()) {
		result += rs.getInt(1);
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
	return result;
    }

    // 5. 方法：從多少位朋友計算佔幾頁。
    public int getTotalPages() throws SQLException {
	if (totalPages == -1) {
	    totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
	}
	return totalPages;
    }

    // 6. 提供pageNo的Setter Getter方法給使用者自由轉換頁面
    public int getPageNo() {
	return pageNo;
    }

    public void setPageNo(int pageNo) {
	this.pageNo = pageNo;
    }

    // 7. 提供recordsPerPage的Setter Getter方法，未來可調整頁面的顯示朋友數量
    public int getRecordsPerPage() {
	return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
	this.recordsPerPage = recordsPerPage;
    }

    // 8. 查詢 - 取得所有朋友的朋友Collection
    public Collection<FriendBean> getAllFriends() throws SQLException {
	Collection<FriendBean> coll = new ArrayList<FriendBean>();
	Connection connection = null;
	PreparedStatement pStmt = null;
	String sql = "SELECT * FROM user u JOIN (SELECT * FROM friend WHERE (sID = ? ) and status = 1) f on (u.userID = f.oID)";
	ResultSet rs = null;
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, userID);
	    rs = pStmt.executeQuery();
	    while (rs.next()) {
		FriendBean bean = new FriendBean();
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

    public void setSearchType(String searchType) {
	this.searchType = searchType;
    }

    public void setSearchText(String searchText) {
	this.searchText = searchText;
    }

    // 9. 查詢 - 取得非自己朋友的朋友Collection
    public Collection<FriendBean> getSearchFriends() throws SQLException {
	Collection<FriendBean> coll = new ArrayList<FriendBean>();
	Connection connection = null;
	PreparedStatement pStmt = null;
	String sql = null;
	searchText = "%" + searchText + "%";
	if (searchType.equals("name")) {
	    sql = "SELECT * FROM user WHERE ( userID NOT IN (SELECT oID FROM friend WHERE (sID = ? ))) AND (userID <> ?) AND (name LIKE ?) ";
	} else {
	    sql = "SELECT * FROM user WHERE ( userID NOT IN (SELECT oID FROM friend WHERE (sID = ? ))) AND (userID <> ?) AND (account LIKE ?) ";
	}
	System.out.println(searchText);
	ResultSet rs = null;
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, userID);
	    pStmt.setInt(2, userID);
	    pStmt.setString(3, searchText);
	    rs = pStmt.executeQuery();
	    while (rs.next()) {
		FriendBean bean = new FriendBean();
		bean.setUserID(rs.getInt(1));
		bean.setAccount(rs.getString(2));
		bean.setName(rs.getString(4));
		bean.setComment(rs.getString(11));
		bean.setMutual(5); // 寫方法！
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

    // 10. 查詢 - 取得提出的朋友邀請的用戶Collection
    public Collection<FriendBean> getRequestFriends() throws SQLException {
	Collection<FriendBean> coll = new ArrayList<FriendBean>();
	Connection connection = null;
	PreparedStatement pStmt = null;
	String sql = "SELECT * FROM user WHERE userID IN (SELECT sID FROM friend WHERE oID = ? AND status = ?)";
	ResultSet rs = null;
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, userID);
	    pStmt.setInt(2, 0);
	    rs = pStmt.executeQuery();
	    while (rs.next()) {
		FriendBean bean = new FriendBean();
		bean.setUserID(rs.getInt(1));
		bean.setAccount(rs.getString(2));
		bean.setName(rs.getString(4));
		bean.setComment(rs.getString(11));
		bean.setMutual(5); // 寫方法！
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

    // 11. 新增 - 向用戶提出好友請求
    public int toRequestFriend(int oID) throws Exception {
	String sql = "INSERT INTO friend (sID, oID, time) VALUES (?, ?, CURRENT_TIMESTAMP)";
	Connection connection = null;
	PreparedStatement pStmt = null;
	int n = 0;
	int gKey = 0;
	ResultSet rs = null;
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    pStmt.setInt(1, userID);
	    pStmt.setInt(2, oID);
	    n = pStmt.executeUpdate();
	    rs = pStmt.getGeneratedKeys();
	    while (rs.next()) {
		gKey = rs.getInt(1);
	    }
	    
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
	return gKey;
    }

    public void setoID(int oID) {
	this.oID = oID;
    }

    // 12. 新增 - 對提出好友請求的用戶進行確認接受
    public void toConfirmFriend() throws SQLException {
	Connection connection = null;
	PreparedStatement pStmt1 = null;
	PreparedStatement pStmt2 = null;
	String sql1 = "INSERT INTO friend (sID, oID, time, status) VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
	String sql2 = "UPDATE friend SET status = ? WHERE sID = ? AND oID = ?";
	int status = 1;
	int n = 0;
	try {
	    connection = ds.getConnection();
	    pStmt1 = connection.prepareStatement(sql1);
	    pStmt1.setInt(1, userID);
	    pStmt1.setInt(2, oID);
	    pStmt1.setInt(3, status);
	    n = pStmt1.executeUpdate();
	    pStmt2 = connection.prepareStatement(sql2);
	    pStmt2.setInt(1, status);
	    pStmt2.setInt(2, oID);
	    pStmt2.setInt(3, userID);
	    n = pStmt2.executeUpdate();
	} finally {
	    if (pStmt1 != null) {
		try {
		    pStmt1.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (pStmt2 != null) {
		try {
		    pStmt2.close();
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

    // 13. 查詢 - 取得某一朋友Collection
    public Collection<FriendBean> getFriend() throws SQLException {
	Collection<FriendBean> coll = new ArrayList<FriendBean>();
	Connection connection = null;
	PreparedStatement pStmt = null;
	String sql = "SELECT * FROM user WHERE userID = ? ";
	ResultSet rs = null;
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, friendUserID);
	    rs = pStmt.executeQuery();
	    while (rs.next()) {
		FriendBean bean = new FriendBean();
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

    public void setFriendUserID(int friendUserID) {
	this.friendUserID = friendUserID;
    }

    // 14. 刪除 - 對提出好友請求的用戶進行確認接受
    public void toRemoveFriend() throws SQLException {
	Connection connection = null;
	PreparedStatement pStmt = null;
	String sql = "DELETE FROM friend WHERE ((friend.sID = ?) AND (friend.oID = ?)) OR ((friend.sID = ?) AND (friend.oID = ?)) ";
	int n = 0;
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, userID);
	    pStmt.setInt(2, friendUserID);
	    pStmt.setInt(3, friendUserID);
	    pStmt.setInt(4, userID);
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

    // 15. 查詢 - 某一位朋友，他在近期time發過哪些文章。
    public List<PostBean> getFriendPost() throws SQLException {
	List<PostBean> list = new ArrayList<PostBean>();
	Connection connection = null;
	PreparedStatement pStmt = null;
	String sql = null;
	ResultSet rs = null;
	if (time == "day") {
	    sql = "SELECT * FROM post WHERE userID = ? AND postTime > '2016-03-15' ORDER BY postID DESC ";
	}
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, friendUserID);
	    rs = pStmt.executeQuery();
	    while (rs.next()) {
		PostBean bean = new PostBean();
		bean.setPostID(rs.getInt(1));
		bean.setUserID(rs.getInt(2));
		bean.setPlace(rs.getString(3));
		bean.setLat(rs.getDouble(4));
		bean.setLng(rs.getDouble(5));
		bean.setPrice(rs.getString(6));
		bean.setComment(rs.getString(7));
		bean.setStar(rs.getInt(8));
		bean.setPostTime(rs.getString(9));
		list.add(bean);
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
	post_counts = list.size();
	return list;
    }

    public int getFriendPostCounts() {
	return post_counts;
    }

    // 16. 查詢 - 從某一位朋友近期time發過文章，去篩選有確切經緯度之店家。
    public List<PostBean> getFriendPlace() throws SQLException {
	List<PostBean> list_place = new ArrayList<PostBean>();
	List<PostBean> list_post = new ArrayList<PostBean>();
	list_post = getFriendPost();
	for (PostBean bean : list_post) {
	    if (bean.getLat() != 0.0) {
		list_place.add(bean);
	    }
	}
	place_counts = list_place.size();
	return list_place;
    }

    public int getFriendPlaceCounts() {
	return place_counts;
    }

    // 17. 查詢 - 取得某一朋友詳細資料Profile頁面
    public FriendBean getFriendProfile() throws SQLException {
	FriendBean bean;
	Connection connection = null;
	PreparedStatement pStmt = null;
	String sql = "SELECT * FROM user WHERE userID = ? ";
	ResultSet rs = null;
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, friendUserID);
	    rs = pStmt.executeQuery();
	    bean = new FriendBean();
	    bean.setUserID(rs.getInt(1));
	    bean.setAccount(rs.getString(2));
	    bean.setName(rs.getString(4));
	    bean.setComment(rs.getString(11));
	    // 呼叫共同好友的方法
	    bean.setMutual(5);
	    bean.setPoint(rs.getInt(5));
	    bean.setEmail(rs.getString(6));
	    bean.setTel(rs.getString(7));
	    bean.setCreate(rs.getString(8));
	    bean.setLogin(rs.getString(9));
	    bean.setBirth(rs.getString(10));
	    // 呼叫 查詢 某位朋友一個月內張貼文章的地方。
	    bean.setPostBeans(getFriendPost());

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
