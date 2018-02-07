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
<!-- myCss Start-->
<style>
#userCard {
	border: solid 1px #ccc;
	background: #fff;
	border-radius: 10px;
	text-align: center;
	color: #888;
	font-size: 40px;
}

#userCard img {
	margin-top: 20px;
	/*border: solid 1px #ccc;*/
	width: 50%;
	background: #fff;
}

#userCard p {
	margin-top: 10px;
	font-size: 10px;
}

#userCard h4 {
	color: #fff;
	padding-top: 5px;
	padding-bottom: 5px;
	background: rgba(241, 124, 103, 0.8);
	font-size: 30px;
	margin-bottom: 0px;
}

#userCard h5 {
	margin-top: 0px;
	height: 30px;
	line-height: 30px;
	font-size: 15px;
	border-left: solid 2px orange;
	background: #FFFAF0;
}

#userCard h6 {
	font-size: 20px;
	color: #ccc;
	background: #fff;
	padding-top: 3px;
	padding-bottom: 3px;
	margin-top: 0px;
}

#userCard input {
	margin-top: -20px;
	color: steelblue;
	border-radius: 4px;
	font-size: 10px;
	border: solid 1px steelblue;
	/*background: #fff;*/
	width: 100px;
}

#userCardButton {
	background: #fff;
	border: solid 1px #ccc;
	font-size: 20px;
	height: 40px;
	line-height: 40px;
	border-radius: 10px;
	color: #888;
	margin-bottom: 10px;
	cursor: pointer;
}

#userCardButton p {
	font-size: 17px;
}
</style>
<!-- myCss End-->
<title>Jabong</title>
</head>
<body>
	<c:set var="navName" value="" scope="session" />
	<jsp:include page="/fragment/top.jsp" />
	<!-- Content Start-->
	<c:forEach varStatus="stVar" var="aFriendBean" items="${friends_DOF}">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3" id="userCard">
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
						<div class="col-xs-2 col-xs-offset-1" id="userCardButton">
							<p>
								<span class="glyphicon glyphicon-picture" aria-hidden="true"
									style="color: #78C2C4;"></span>
							</p>
						</div>
						<div class="col-xs-2 col-xs-offset-2" id="userCardButton"
							style="color: #FB966E;">
							<p>
								<span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
							</p>
						</div>

						<div class="col-xs-2 col-xs-offset-2" id="userCardButton"
							style="color: #F596AA;"
							onclick="removeFriend(${aFriendBean.userID}, '${aFriendBean.name}')">
							<p>
								<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br>
	</c:forEach>
	<form>
		<input type="hidden" name="a" />
	</form>
	<!-- Content End-->
	<jsp:include page="/fragment/footer.jsp" />
</body>
<jsp:include page="fragment/ref_js.jsp" />
<!-- myJS Start-->
<script type="text/javascript">
	function removeFriend(id, name) {
		if (confirm("確定將 " + name + " 移除好友關係?") ) {
			document.forms[0].action = "<c:url value='RemoveFriend?id=" + id + "' />";
			document.forms[0].method = "POST";
			document.forms[0].submit();
		} else {
		
		}
	}
</script>
<!-- myJS End-->
</html>