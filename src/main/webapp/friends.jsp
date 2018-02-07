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

<style>
#myFri {
	border: solid 1px #ccc;
	background: #fff;
	margin-bottom: 10px;
	height: 130px;
	cursor: pointer;
}

#myFri:hover {
	background: #FFFFF0;
	border: solid 1px orange;
}

#myFri img {
	border: solid 1px #ccc;
	margin-top: 13px;
}

#myFri h4 {
	margin-top: 22px;
	color: steelblue;
}
</style>
<title>Jabong - Friends</title>
</head>
<body>
	<c:set var="navName" value="FRIENDS" scope="session" />
	<jsp:include page="fragment/top.jsp" />
	<!-- FRIEND START -->
	<!-- 1. Friend Button -->
	<div class="container">
		<c:forEach varStatus="stVar" var="aFriendBean" items="${friends_DAF}">
			<c:if test="${ stVar.count % 3 == 1 }">
				<c:out value='<div class="row">' escapeXml='false' />
			</c:if>
			<div class="col-md-4" id="myFri" data-toggle="modal"
				data-target=".friendID${aFriendBean.userID}">
				<div class="row">
					<div class="col-xs-4">
						<img
							src="${pageContext.servletContext.contextPath}/getImage?id=${aFriendBean.userID}&type=user"
							alt="" height="100px" width="100px" class="img-circle">
					</div>
					<div class="col-xs-8">
						<input TYPE="hidden" name="id" value="${ aFriendBean.userID }">
						<h4>${ aFriendBean.name }</h4>
						<h5>使用者編號：${ aFriendBean.userID }</h5>
						<address>朋友名單編號：${ aFriendBean.friendID }</address>
					</div>
				</div>
			</div>

			<c:if test="${ stVar.count % 3 == 0 }">
				<c:out value='</div>' escapeXml='false' />
			</c:if>
			<c:if test="${ stVar.last && stVar.count % 3 != 0  }">
				<c:out value='</div>' escapeXml='false' />
			</c:if>
		</c:forEach>
		<form>
			<input type="hidden" name="a" />
		</form>
	</div>
	<!-- 2. Friend View -->
	<c:forEach varStatus="stVar" var="aFriendBean" items="${friends_DAF}">
		<div class="modal fade friendID${aFriendBean.userID}" tabindex="-1"
			role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="row">
						<div class="col-md-12" id="userCard">
							<div class="row"
								style="background-image: url(images/login3.png); background-size: cover;">
								<img
									src="${pageContext.servletContext.contextPath}/getImage?id=${aFriendBean.userID}&type=user"
									class="img-circle">
								<h4>${ aFriendBean.name }</h4>
							</div>
							<div class="row">
								<h5>${ aFriendBean.account }(${ aFriendBean.mutual })</h5>
								<p>${ aFriendBean.comment }</p>
								<div class="col-xs-2 col-xs-offset-1" id="userCardButton"
									onclick="displayFriend(${aFriendBean.userID})">
									<p>
										<span class="glyphicon glyphicon-picture" aria-hidden="true"
											style="color: #78C2C4;"></span>
									</p>
								</div>
								<div class="col-xs-2 col-xs-offset-2" id="userCardButton"
									style="color: #FB966E;"
									onclick="location.href = 'DisplayOneFriendPlaces?id=${aFriendBean.userID}';">
									<p>
										<span class="glyphicon glyphicon-map-marker"
											aria-hidden="true"></span>
									</p>
								</div>

								<div class="col-xs-2 col-xs-offset-2" id="userCardButton"
									style="color: #F596AA;"
									onclick="removeFriend(${aFriendBean.userID}, '${aFriendBean.name}')">
									<p>
										<span class="glyphicon glyphicon-remove-sign"
											aria-hidden="true"></span>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<!-- FRIEND END -->
	<jsp:include page="/fragment/footer.jsp" />
</body>
<jsp:include page="fragment/ref_js.jsp" />
<script type="text/javascript">
function displayFriend(id) {
	document.forms[0].action = "<c:url value='DisplayOneFriend?id=" + id + "' />";
	document.forms[0].method = "POST";
	document.forms[0].submit();
}
function removeFriend(id, name) {
	if (confirm("確定將 " + name + " 移除好友關係?") ) {
		document.forms[0].action = "<c:url value='RemoveFriend' />";
		document.forms[0].method = "POST";
		document.forms[0].elements[0].name = 'id';
		document.forms[0].elements[0].value = id;
		document.forms[0].submit();
	} else {
	
	}
}
</script>
</html>
