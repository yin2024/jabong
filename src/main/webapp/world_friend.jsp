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
<link rel="stylesheet" href="css/bootstrap-multiselect.css">
<style>
a {
	color: #000;
}

a:hover {
	color: orange;
}

#myMap {
	border: solid 1px #ddd;
	border-top: none;
	margin-top: 0px;
}

a {
	color: #aaa;
}

.mapContent {
	background: #eee;
}

.searchBar {
	margin-top: -10px;
	padding-top: 20px;
	padding-bottom: 20px;
	text-align: center;
	background: #fff;
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
		<div class="col-md-6 col-md-offset-3 mapContent">
			<div class="row">
				<ul class="nav nav-tabs">
					<li role="presentation"><a href="DisplayMyPlaces"><span
							class="glyphicon glyphicon-heart" aria-hidden="true"></span> 我食</a></li>
					<li role="presentation" class="active"><a href="#"><span
							class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
							好友</a></li>
					<li role="presentation"><a href="#"><span
							class="glyphicon glyphicon-glass" aria-hidden="true"></span> 社團</a></li>
					<li role="presentation"><a href="#"><span
							class="glyphicon glyphicon-cloud" aria-hidden="true"></span> 附近</a></li>
				</ul>
			</div>
			<div class="row" id="myMap">
				<!-- Build your select: <-->
				<div class="col-xs-6 searchBar">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span> <select
						id="selectFriend" multiple="multiple">
						<c:forEach varStatus="stVar" var="aFriendBean"
							items="${friends_DAF}">
							<option value="${ aFriendBean.userID }">${ aFriendBean.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-xs-3 searchBar">
					<span class="glyphicon glyphicon-picture" aria-hidden="true"></span>
					<select id="selectPic">
						<option value="post">圖</option>
						<option value="friend">人</option>
					</select>
				</div>
				<div class="col-xs-3 searchBar">
					<span class="glyphicon glyphicon-time" aria-hidden="true"></span> <select
						id="selectTime">
						<option value="day">本日</option>
						<option value="week">本週</option>
						<option value="month">本月</option>
					</select>
				</div>
			</div>
			<div class="row" id="myMap">
				<div id='map_canvas' style="width: 100%; height: 500px;"></div>
			</div>
			<c:forEach varStatus="stVar" var="aFriendBean" items="${friends_DAF}">
				<input type="hidden" id="spotJson${ aFriendBean.userID }"
					value='${ aFriendBean.postJson }' />
			</c:forEach>
			<!-- <p id="position"></p> -->
		</div>
	</div>
	<!-- Content End-->
	<jsp:include page="/fragment/footer.jsp" />
</body>
<jsp:include page="fragment/ref_js.jsp" />
<!-- myJS Start-->
<script src="js/bootstrap-multiselect.js"></script>
<link rel="stylesheet" href="css/jquery.mobile-1.3.1.min.css" />
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAmYQ-0CCxFtUWaatWTWbhx1NbPo_dDkPo&libraries=places"></script>
<script src="js/jquery.ui.map.js"></script>
<script src="js/jquery.ui.map.services.js"></script>
<script src="js/jquery.ui.map.extensions.js"></script>
<script src="js/places.world.friend.ajax.js"></script>
<!-- myJS End-->
</html>