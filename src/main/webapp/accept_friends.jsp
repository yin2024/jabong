<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="faBean" class="_04_friend.model.FriendAccessBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="fragment/ref_css.jsp" />
<style>
#mySearchF {
	border: solid 1px #ccc;
	background: #fff;
	border-radius: 10px;
	text-align: center;
	color: #888;
	font-size: 40px;
}

#mySearchF img {
	margin-top: 20px;
	/*border: solid 1px #ccc;*/
	width: 50%;
	background: #fff;
}

#mySearchF p {
	margin-top: 10px;
	font-size: 10px;
}

#mySearchF h4 {
	color: #fff;
	padding-top: 5px;
	padding-bottom: 5px;
	background: rgba(241, 124, 103, 0.8);
	font-size: 30px;
	margin-bottom: 0px;
}

#mySearchF h5 {
	margin-top: 0px;
	height: 30px;
	line-height: 30px;
	font-size: 15px;
	border-left: solid 2px orange;
	background: #FFFAF0;
}

#mySearchF h6 {
	font-size: 20px;
	color: #ccc;
	background: #fff;
	padding-top: 3px;
	padding-bottom: 3px;
	margin-top: 0px;
}

#mySearchF input {
	margin-bottom: 20px;
	color: steelblue;
	border-radius: 4px;
	font-size: 10px;
	border: solid 1px steelblue;
	/*background: #fff;*/
	width: 100px;
}
</style>
<title>Jabong</title>
</head>
<body>
	<c:set var="navName" value="" scope="session" />
	<jsp:include page="/fragment/top.jsp" />
	<!-- Content Start-->
	<c:forEach varStatus="stVar" var="aFriendBean" items="${friends_DRF}">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3" id="mySearchF">
					<div class="row"
						style="background-image: url(images/login3.png); background-size: cover;">
						<img
							src="${pageContext.servletContext.contextPath}/getImage?id=${aFriendBean.userID}&type=user"
							class="img-circle">
						<h4>${ aFriendBean.name }</h4>
					</div>
					<div class="row">
						<h5>${ aFriendBean.account }(${ aFriendBean.mutual })</h5>
						<!-- <h6>共同好友：5位</h6> -->
						<p>${ aFriendBean.comment }</p>
						<form action="ConfirmFriend.do" method="POST"
							name="ConfirmFriendForm">
							<input type="hidden" id="oID" name="oID"
								value="${aFriendBean.userID}" /> <input type="submit"
								value="接受好友邀請" class="btn btn-large btn-default" />
						</form>
					</div>
				</div>
			</div>
		</div>
		<br>
	</c:forEach>
	<!-- Content End-->
	<jsp:include page="/fragment/footer.jsp" />
</body>
<jsp:include page="fragment/ref_js.jsp" />
</html>