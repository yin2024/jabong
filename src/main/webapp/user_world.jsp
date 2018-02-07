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
a {
	color: #000;
}

a:hover {
	color: orange;
}

.myWorld {
	background: #fff;
	border: solid 1px #ccc;
	color: #000;
}

.userBar {
	padding: 0px 0px 0px 0px;
	background-image: url(images/login3.png);
	background-size: cover;
}

.userBar img {
	border: solid 1px #ccc;
	border-radius: 100%;
	background: #fff;
}

.userBar p {
	background-color: rgba(0, 0, 0, 0.2);
	padding: 10px 10px;
	margin: 0;
	font-size: 20px;
	color: #fff;
}

.checkBar {
	background-color: rgba(256, 256, 256, 0.8);
	color: #555;
	padding: 5px 20px;
	margin-bottom: 0px;
}
</style>
<!-- myCss End-->
<title>Jabong</title>
</head>
<body>
	<c:set var="navName" value="" scope="session" />
	<jsp:include page="/fragment/top.jsp" />
	<!-- Content Start-->
	<c:forEach varStatus="stVar" var="aFriendBean" items="${friend_coll}">

		<div class="container">
			<div class="col-md-10 col-md-offset-1 myWorld">
				<div class="row userBar">
					<p>
						<img
							src="${pageContext.servletContext.contextPath}/getImage?id=${aFriendBean.userID}&type=user"
							height="50px"> ${aFriendBean.name}
					</p>
					<div class="checkBar">
						<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
						期間： <label class="radio-inline"> <input type="radio"
							name="inlineRadioOptions" id="inlineRadio1" value="option1"
							checked> Month
						</label> <label class="radio-inline"> <input type="radio"
							name="inlineRadioOptions" id="inlineRadio2" value="option2">
							Week
						</label> <label class="radio-inline"> <input type="radio"
							name="inlineRadioOptions" id="inlineRadio3" value="option3">
							Day
						</label>

					</div>
				</div>
				<div class="row myMap">
					<div id='map_canvas' style="width: 100%; height: 500px;"></div>
				</div>
				<input type="hidden" id="spotJson" value='${ place_spotJson }' /> <input
					type="hidden" id="spotCounts" value="${ place_counts }" />
			</div>
		</div>
	</c:forEach>
	<!-- Content End-->
	<jsp:include page="/fragment/footer.jsp" />
</body>
<jsp:include page="fragment/ref_js.jsp" />
<!-- myJS Start-->
<link rel="stylesheet"
	href="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.css" />
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAmYQ-0CCxFtUWaatWTWbhx1NbPo_dDkPo&libraries=places"></script>
<script type="text/javascript"
	src="js/jquery.ui.map.js"></script>
<script type="text/javascript"
	src="js/jquery.ui.map.services.js"></script>
<script type="text/javascript"
	src="js/jquery.ui.map.extensions.js"></script>
<script src="js/places.user.world.js"></script>
<!-- myJS End-->
</html>