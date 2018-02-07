package _07_gift.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import _00_init.GlobalService;
import _04_friend.model.FriendBean;

public class RouletteAccessBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private DataSource ds = null;
    private int userID = 1;
    private int friendUserID = 1;
    private int num = 0;
    private int giftID = 0;
    private List<Integer> numList;

    public RouletteAccessBean() throws ServletException, IOException, NamingException {
	Context ctx = new InitialContext();
	ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
    }
    
    

    public void setUserID(int userID) {
        this.userID = userID;
    }



    public Collection<RouletteBean> getAllGifts() throws SQLException {
	Collection<RouletteBean> coll = new ArrayList<RouletteBean>();
	Connection connection = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	String sql = "SELECT * FROM roulette r JOIN gift g ON (r.giftID=g.giftID) ";
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    rs = pStmt.executeQuery();
	    while (rs.next()) {
		RouletteBean bean = new RouletteBean();
		bean.setRouletteID(rs.getInt(1));
		bean.setGiftID(rs.getInt(2));
		bean.setName(rs.getString(4));
		bean.setPoint(rs.getInt(5));
		bean.setContent(rs.getString(6));
		bean.setAmount(rs.getInt(9));
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

    // 1. 抽號碼排前，回先初始化好抽籤桶內的號碼排集合list
    public void setNumberList() throws SQLException {
	Collection<RouletteBean> coll = getAllGifts();
	numList = new LinkedList();
	for (RouletteBean bean : coll) {
	    for (int x = 0; x < bean.getAmount(); x++) {
		numList.add(bean.getRouletteID());
	    }
	}
    }

    // 2. 抽取號碼排
    public void setNumber() throws SQLException {
	setNumberList();
	int ran = (int) (Math.random() * numList.size());
	num = numList.get(ran);
    }

    public int getNum() throws SQLException {
	setNumber();
	return num;
    }

    public RouletteBean getGift() throws SQLException {
	getNum();
	RouletteBean bean = new RouletteBean();
	Connection connection = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	String sql = "SELECT * FROM roulette r JOIN gift g ON (r.giftID=g.giftID) WHERE rouletteID = ? ";
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, num);
	    rs = pStmt.executeQuery();
	    while (rs.next()) {
		bean.setRouletteID(rs.getInt(1));
		bean.setGiftID(rs.getInt(2));
		bean.setName(rs.getString(4));
		bean.setPoint(rs.getInt(5));
		bean.setContent(rs.getString(6));
		bean.setAmount(rs.getInt(9));
		bean.setAngle(getAngle());
		this.giftID = rs.getInt(2);
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

    // 亂數產生，對應號碼i的角度。如7號，角度約在0度~36度。
    public double getAngle() {
	double a = (Math.random() * 35 + 1) + (this.num + 3) * 36;
	System.out.println("號碼：" + num + "/角度：" + a);
	return a;
    }
    
    // 交易成立
    public void insertGift() throws SQLException {
	Connection connection = null;
	PreparedStatement pStmt = null;
	String sql = "INSERT INTO trans (userID, giftID, point) VALUES (?,?,0) "; 
	int n= 0;
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    pStmt.setInt(1, userID);
	    pStmt.setInt(2, giftID);
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
