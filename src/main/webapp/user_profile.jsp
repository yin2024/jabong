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

#userCardInfo {
	border: solid 1px #eee;
	margin: 10px 10px 30px 10px;
	line-height: 10px;
	text-align: left;
	padding-left: 25px;
}

#userCardButton {
	background: #fff;
	font-size: 20px;
	height: 40px;
	line-height: 40px;
	border-radius: 10px;
	color: #888;
	margin-bottom: 10px;
}

#userCardButton:hover {
	border: solid 1px #ccc;
}

#userCardButton p {
	font-size: 20px;
}

#userPicture {
	/*border: solid 1px #000;*/
	padding-left: 0;
	padding-right: 0;
}

#userPicture div {
	border: solid 2px #fff;
	width: 100%;
	padding-top: 70%;
	border-radius: 10px;
	opacity: 0.8;
	cursor: pointer;
	background-size: cover;
}

#userPicture div:hover {
	opacity: 1;
	/*border: solid 2px #FFC8B4;*/
}

#userPicture div:active {
	opacity: 1;
}

#userPicture p {
	background: #000;
	font-size: 13px;
	opacity: 0.6;
	padding-top: 3px;
	padding-bottom: 3px;
	color: #fff;
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
				<div class="col-md-8 col-md-offset-2" id="userCard">
					<div class="row"
						style="background-image: url(images/login3.png); background-size: cover;">
						<img
							src="${pageContext.servletContext.contextPath}/getImage?id=${aFriendBean.userID}&type=user"
							class="img-circle">
						<h4>${aFriendBean.name}</h4>
					</div>
					<div class="row">
						<h5>${aFriendBean.account}(${aFriendBean.mutual})</h5>
						<!-- <h6>共同好友：5位</h6> -->
						<p>- ${aFriendBean.comment} -</p>
						<div id="userCardInfo">
							<p>
								<span class="glyphicon glyphicon-piggy-bank" aria-hidden="true"></span>
								點數 - ${aFriendBean.point} JB
							</p>
							<p>
								<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
								信箱 - ${aFriendBean.email}
							</p>
							<p>
								<span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
								電話 - ${aFriendBean.tel}
							</p>
							<p>
								<span class="glyphicon glyphicon-gift" aria-hidden="true"></span>
								誕辰 - ${aFriendBean.birth}
							</p>
							<p>
								<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
								登入 - ${aFriendBean.login} ｜ 創立 - ${aFriendBean.create}
							</p>
							<p></p>
						</div>
						<div class="col-xs-2 col-xs-offset-1" id="userCardButton">
							<p>
								<span class="glyphicon glyphicon-picture" aria-hidden="true"
									style="color: #78C2C4;"></span>
							</p>
						</div>
						<div class="col-xs-2 col-xs-offset-2" id="userCardButton"
							style="color: #FB966E;"
							onclick="location.href = 'DisplayOneFriendPlaces?id=${aFriendBean.userID}';">
							<p>
								<span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
							</p>
						</div>
						<c:if test="${aFriendBean.userID == LoginOK.userID}">
							<div class="col-xs-2 col-xs-offset-2" id="userCardButton"
								style="color: #F596AA;" onclick="editMe()">
								<p>
									<span class="glyphicon glyphicon glyphicon-edit"
										aria-hidden="true"></span>
								</p>
							</div>
						</c:if>
						<c:if test="${aFriendBean.userID != LoginOK.userID}">
							<div class="col-xs-2 col-xs-offset-2" id="userCardButton"
								style="color: #F596AA;"
								onclick="removeFriend(${aFriendBean.userID}, '${aFriendBean.name}')">
								<p>
									<span class="glyphicon glyphicon-remove-sign"
										aria-hidden="true"></span>
								</p>
							</div>
						</c:if>
					</div>
					<hr>
					<div class="row">
						<c:forEach varStatus="stVar" var="aPostBean"
							items="${friends_DOFP}">
							<div class="col-xs-4" id="userPicture">
								<div
									style="background-image: url(${pageContext.servletContext.contextPath}/getImage?id=${aPostBean.postID}&type=post);"
									onclick="clickPost(${aPostBean.postID})">
									<p>${aPostBean.place}</p>
								</div>
							</div>
						</c:forEach>
					</div>
					<hr>
					<p>- 共 ${ post_counts } 張 -</p>
				</div>
			</div>
		</div>
		<br>
	</c:forEach>
	<!-- Content End-->
	<jsp:include page="/fragment/footer.jsp" />
</body>
<jsp:include page="fragment/ref_js.jsp" />
<!-- myJS Start-->
<script type="text/javascript">
function displayFriend(id) {
	document.forms[0].action = "<c:url value='DisplayOneFriend?id=" + id + "' />";
	document.forms[0].method = "POST";
	document.forms[0].submit();
}
function editMe() {
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
function clickPost(id) {
	location.href='DisplayOnePost?id='+id;
}
</script>
<script>
var w = parseInt($('#userPicture div').css("width"));
$('#userPicture div').css("height", w);
</script>
<!-- myJS End-->
</html>