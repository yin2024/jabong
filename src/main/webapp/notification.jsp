<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="fragment/ref_css.jsp" />
<!-- myCss Start-->
<style>
.myNoti {
	background: #fff;
	border: solid 1px #ccc;
	text-align: center;
}

.myNoti h3 {
	background: #eee;
}

.notiList {
	line-height: 50px;
	border: solid 1px #ccc;
	margin: 5px 10px;
	cursor: pointer;
}

.notiList:hover {
	background: #ffc;
	border: solid 1px orange;
}

.notiList img {
	border-radius: 100%;
	background: #fff;
	border: solid 1px #ccc;
}
</style>
<!-- myCss End-->
<title>Jabong</title>
</head>
<body>
	<c:set var="navName" value="" scope="session" />
	<jsp:include page="/fragment/top.jsp" />
	<!-- Content Start-->
	<div class="container">
		<div class="col-md-6 col-md-offset-3 myNoti">
			<div class="row">
				<h3>
					<span class="glyphicon glyphicon-envelope"> Your
						Notifications </span>
				</h3>
			</div>
			<c:forEach varStatus="stVar" var="aNotifyBean"
				items="${notification}">
				<div class="row">
					<div class="notiList" onclick="location.href='#'">
						<img
							src="${pageContext.servletContext.contextPath}/getImage?id=${aNotifyBean.sID}&type=user"
							width="30px" height="30px"> ${aNotifyBean.name}
						<c:if test="${aNotifyBean.type == 1}">
						向您提出 好友邀請
					</c:if>
						<c:if test="${aNotifyBean.type == 2}">
						說您的文章讚
					</c:if>
						<c:if test="${aNotifyBean.type == 3}">
						回應您的文章
					</c:if>
						- ${aNotifyBean.time}
					</div>
				</div>
			</c:forEach>
			<div class="row">- 共 ${notification.size()} 條歷史通知 -</div>
			<br>
		</div>
	</div>
	<!-- Content End-->
	<jsp:include page="/fragment/footer.jsp" />
</body>
<jsp:include page="fragment/ref_js.jsp" />
<!-- myJS Start-->

<!-- myJS End-->
</html>