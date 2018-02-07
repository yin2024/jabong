package _06_group.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import _00_init.GlobalService;

public class GroupAccessBean implements Serializable {
    // 1. AccessBean必要準備項目：
    private static final long serialVersionUID = 1L;
    private DataSource ds = null;
    private int userID = 1;
    private int groupID = 1;

    // 2. AccessBean次要準備項目：(可能會set的參數)

    // 3. 預設建構子：負責取得資料庫連線
    public GroupAccessBean() throws ServletException, IOException, NamingException {
	Context ctx = new InitialContext();
	ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
    }
    
    // 4. Setter：負責存取外部參數用
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
    
    // 5. 查詢： 六個社團
    public Collection<GroupBean> getSixGroups() throws SQLException {
	Collection<GroupBean> coll = new ArrayList<>();
	String sql = "SELECT * FROM groups ORDER BY groupsID DESC LIMIT 6 ";
	PreparedStatement pStmt = null;
	Connection connection = null;
	ResultSet rs = null;
	try {
	    connection = ds.getConnection();
	    pStmt = connection.prepareStatement(sql);
	    rs = pStmt.executeQuery();
	    while (rs.next()) {
		GroupBean bean = new GroupBean();
		bean.setGroupID(rs.getInt(1));
		bean.setName(rs.getString(2));
		bean.setKind(rs.getInt(3));
		bean.setUserID(rs.getInt(4));
		bean.setComment(rs.getString(5));
		bean.setLocation(rs.getString(6));
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
    


}
