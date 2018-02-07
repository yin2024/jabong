<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="paBean" class="_03_post.model.PostAccessBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="fragment/ref_css.jsp" />
<meta charset="UTF-8">
<title>Jabong 動態時報</title>
</head>
<body>
	<c:set var="navName" value="ONEPOST" scope="session" />
	<jsp:include page="fragment/top.jsp" />
	<!-- 動態一則 start-->
	<jsp:include page="fragment/post.jsp" />
	<!-- 動態一則 end-->
	<jsp:include page="/fragment/footer.jsp" />
</body>
<jsp:include page="fragment/ref_js.jsp" />
</html>