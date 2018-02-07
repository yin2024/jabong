package _03_post.model;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.servlet.*;
import javax.sql.*;

import _00_init.*;
import _03_post.model.PostBean;
import _08_notification.model.NotifyAccessBean;

public class PostAccessBean implements Serializable, PostAccessDAO {
    // 1. AccessBean必要準備項目：
    private static final long serialVersionUID = 1L;
    private DataSource ds = null;
    private int userID = 1;
    private int postID = 1;

    // 2. AccessBean次要準備項目：(可能會set的參數)
    private int notifyTypeLike = 1;
    private int notifyTypeResp = 1;

    // 3. 預設建構子：負責取得資料庫連線
    public PostAccessBean() throws ServletException, IOException, NamingException {
	Context ctx = new InitialContext();
	ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
    }

    // 4. Setter：負責存取外部參數用
    public void setUserID(int userID) {
	this.userID = userID;
    }

    public void setPostID(int postID) {
	this.postID = postID;
    }

    public void setNotifyTypeLike(int notifyTypeLike) {
	this.notifyTypeLike = notifyTypeLike;
    }

    public void setNotifyTypeResp(int notifyTypeResp) {
	this.notifyTypeResp = notifyTypeResp;
    }

    // 5. MySQL：查詢
    // 5.1 MySQL：查詢 - 取得用戶好友及自己的文章
    public List<PostBean> getAllPosts() throws SQLException {
	String sql = "SELECT * FROM post p JOIN (SELECT userID, name FROM user) u ON (u.userID = p.userID) WHERE p.userID IN (SELECT oID FROM friend WHERE sID = ? and status = 1 ) OR (p.userID = ?) ORDER BY p.postID DESC ";
	List<PostBean> coll = new ArrayList<PostBean>();
	Connection connection = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, userID);
	    pStmt.setInt(2, userID);
	    rs = pStmt.executeQuery();
	    while (rs.next()) {
		PostBean bean = new PostBean();
		bean.setPostID(rs.getInt(1));
		bean.setUserID(rs.getInt(2));
		bean.setPlace(rs.getString(3));
		bean.setPrice(rs.getString(6));
		// 換行符號改為html專用的br標籤
		bean.setComment(rs.getString(7).replace("\r\n", "<br>"));
		bean.setStar(rs.getInt(8));
		// 顯示給用戶的時間改為相對時間
		bean.setPostTime(TimeService.getRelativeTime(rs.getString(9)));
		bean.setUserName(rs.getString(11));
		// 以下負責為每篇文章分別在進行查詢，來取得他們的點讚集合、回應集合
		// 首先，要告知本文章的postID是幾號，以便查詢時之依據
		this.postID = rs.getInt(1);
		bean.setPostRespBeans(getPostResp());
		bean.setPostLikeBeans(getPostLike());
		coll.add(bean);
	    }

	} finally {
	    if (rs != null) {
		rs.close();
	    }
	    if (pStmt != null) {
		pStmt.close();
	    }
	    if (connection != null) {
		connection.close();
	    }
	}
	return coll;
    }

    // 5.2 MySQL：查詢 - 取得單一篇文章
    public PostBean getPost() throws SQLException {
	PreparedStatement pStmt = null;
	Connection connection = null;
	ResultSet rs = null;
	PostBean bean = null;
	String sql = "SELECT * FROM post p JOIN user u ON(p.userID=u.userID) WHERE postID = ? ";
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, postID);
	    rs = pStmt.executeQuery();
	    while (rs.next()) {
		bean = new PostBean();
		bean.setPostID(rs.getInt(1));
		bean.setUserID(rs.getInt(2));
		bean.setPlace(rs.getString(3));
		bean.setLat(rs.getDouble(4));
		bean.setLng(rs.getDouble(5));
		bean.setPrice(rs.getString(6));
		bean.setComment(rs.getString(7).replace("\r\n", "<br>"));
		bean.setStar(rs.getInt(8));
		bean.setPostTime(TimeService.getRelativeTime(rs.getString(9)));
		bean.setPostRespBeans(getPostResp());
		bean.setPostLikeBeans(getPostLike());
		bean.setUserName(rs.getString(13));
	    }

	} finally {
	    if (rs != null) {
		rs.close();
	    }
	    if (pStmt != null) {
		pStmt.close();
	    }
	    if (connection != null) {
		connection.close();
	    }
	}
	return bean;
    }

    public List<PostLikeBean> getPostLike() throws SQLException {
	List<PostLikeBean> list = new ArrayList<PostLikeBean>();
	PreparedStatement pStmt = null;
	Connection connection = null;
	ResultSet rs = null;
	String sql = "SELECT * FROM postLike p JOIN user u ON(p.userID = u.userID) WHERE postID = ? ORDER BY postLikeID DESC ";
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, postID);
	    rs = pStmt.executeQuery();
	    while (rs.next()) {
		PostLikeBean bean = new PostLikeBean();
		bean.setPostLikeID(rs.getInt(1));
		bean.setPostID(rs.getInt(2));
		bean.setUserID(rs.getInt(3));
		bean.setLike(rs.getInt(4));
		bean.setTime(rs.getString(5));
		bean.setUserName(rs.getString(9));
		list.add(bean);
	    }

	} finally {
	    if (rs != null) {
		rs.close();
	    }
	    if (pStmt != null) {
		pStmt.close();
	    }
	    if (connection != null) {
		connection.close();
	    }
	}
	return list;
    }

    public List<PostRespBean> getPostResp() throws SQLException {
	List<PostRespBean> list = new ArrayList<PostRespBean>();
	PreparedStatement pStmt = null;
	Connection connection = null;
	ResultSet rs = null;
	String sql = "SELECT * FROM postResp p JOIN user u ON(p.userID = u.userID) WHERE postID = ? ORDER BY postRespID DESC ";
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, postID);
	    rs = pStmt.executeQuery();
	    while (rs.next()) {
		PostRespBean bean = new PostRespBean();
		bean.setPostRespID(rs.getInt(1));
		bean.setPostID(rs.getInt(2));
		bean.setUserID(rs.getInt(3));
		bean.setResponse(rs.getString(4));
		bean.setTime(rs.getString(5));
		bean.setUserName(rs.getString(9));
		list.add(bean);
	    }

	} finally {
	    if (rs != null) {
		rs.close();
	    }
	    if (pStmt != null) {
		pStmt.close();
	    }
	    if (connection != null) {
		connection.close();
	    }
	}
	return list;
    }

    // 5.5 MySQL：查詢 - 取得文章的作者
    public int getWhoPost() throws SQLException {
	String sql = "SELECT userID FROM post WHERE postID = ? ";
	PreparedStatement pStmt = null;
	Connection connection = null;
	ResultSet rs = null;
	int whoID = 0;
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, postID);
	    rs = pStmt.executeQuery();
	    while (rs.next()) {
		whoID = rs.getInt(1);
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
	return whoID;
    }

    public int insertPost(PostBean bean, InputStream is, long size) throws SQLException {
	int n = 0;
	Connection connection = null;
	PreparedStatement pStmt = null;
	PreparedStatement pStmt2 = null;
	try {
	    String sql = "INSERT INTO post (userID, place, price, comment, star, lat, lng) VALUES (?, ?, ?, ?, ?, ?, ?);";
	    String sql2 = "INSERT INTO postImage (image) VALUES (?);";
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt2 = connection.prepareStatement(sql2);
	    pStmt.setInt(1, userID);
	    pStmt.setString(2, bean.getPlace());
	    pStmt.setString(3, bean.getPrice());
	    pStmt.setString(4, bean.getComment());
	    pStmt.setInt(5, bean.getStar());
	    pStmt.setDouble(6, bean.getLat());
	    pStmt.setDouble(7, bean.getLng());
	    pStmt2.setBinaryStream(1, is, size);
	    n = pStmt.executeUpdate();
	    n = pStmt2.executeUpdate();
	} finally {
	    if (pStmt != null) {
		try {
		    pStmt.close();
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
	return n;
    }

    public int updatePost(PostBean bean) throws SQLException {
	int n = 0;
	Connection connection = null;
	PreparedStatement pStmt = null;
	try {
	    String sql = "UPDATE post SET place = ? , price = ? , comment = ? , star = ? , lat = ? , lng = ? WHERE postID = ? ";
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setString(1, bean.getPlace());
	    pStmt.setString(2, bean.getPrice());
	    pStmt.setString(3, bean.getComment());
	    pStmt.setInt(4, bean.getStar());
	    pStmt.setDouble(5, bean.getLat());
	    pStmt.setDouble(6, bean.getLng());
	    pStmt.setInt(7, postID);
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
	return n;
    }

    public int deletePost() throws SQLException {
	int n = 0;
	Connection connection = null;
	PreparedStatement pStmt = null;
	PreparedStatement pStmt2 = null;
	try {
	    String sql = "DELETE FROM post WHERE postID = ? ";
	    String sql2 = "DELETE FROM postimage WHERE postID = ? ";
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, postID);
	    pStmt2 = connection.prepareStatement(sql2);
	    pStmt2.setInt(1, postID);
	    n = pStmt.executeUpdate();
	    n = pStmt2.executeUpdate();
	} finally {
	    if (pStmt != null) {
		try {
		    pStmt.close();
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
	return n;
    }

    public int insertPostResp(PostRespBean prb) throws SQLException {
	int n = 0;
	int gKey = 0;
	Connection connection = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	try {
	    String sql = "INSERT INTO postResp (postID, userID, response) VALUES (?, ?, ?) ";
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    pStmt.setInt(1, prb.getPostID());
	    pStmt.setInt(2, prb.getUserID());
	    pStmt.setString(3, prb.getResponse());
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

    public int insertPostLike(PostLikeBean plb) throws SQLException {
	Connection connection = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	int gKey = 0;
	try {
	    String sql = "INSERT INTO postLike (postID, userID) VALUES (?, ?) ";
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    pStmt.setInt(1, plb.getPostID());
	    pStmt.setInt(2, plb.getUserID());
	    pStmt.executeUpdate();
	    rs = pStmt.getGeneratedKeys();
	    while (rs.next()) {
		gKey = rs.getInt(1);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
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

    public void deletePostLike(PostLikeBean plb) throws SQLException {
	int n = 0;
	Connection connection = null;
	PreparedStatement pStmt = null;
	try {
	    String sql = "DELETE FROM postLike WHERE postID = ? AND userID = ? ";
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, plb.getPostID());
	    pStmt.setInt(2, plb.getUserID());
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