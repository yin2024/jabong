<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page import='java.sql.*' %>
<%@ page import='javax.sql.*' %>
<%@ page import='javax.naming.*' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Tomcat Server Datasource Example</title>
</head>
<body>
<%
Connection result = null;
try {
    /* InitialContext ic = new InitialContext();
    Context initialContext = (Context) ic.lookup("java:comp/env");
    DataSource datasource = (DataSource) initialContext.lookup("jdbc/MySQLDS");
     */
    Context ctx = new InitialContext();
    DataSource datasource = (DataSource) ctx.lookup("java:comp/env/jdbc/MySQLDS");
    result = datasource.getConnection();
    
    /* Statement stmt = result.createStatement() ; */
    String query = "select * from user;" ;
    /* ResultSet rs = stmt.executeQuery(query) ; */ 
    
    PreparedStatement pstmt = result.prepareStatement(query);
    ResultSet rs = pstmt.executeQuery();
    
    while (rs.next()) {
        out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + "<br />");
    }
} catch (Exception ex) {
    out.println("Exception: " + ex + ex.getMessage());
}
%>
</body>
</html>