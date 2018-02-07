<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<jsp:include page="fragment/ref_css.jsp" />
<link rel="stylesheet" href="css/yinDC.css">
<style>
.postEdit {
	border: solid 1px #ccc;
	padding: 10px 10px 10px 10px;
	background: #fff;
}

.postEditForm {
	margin-top: 10px;
	padding: 10px 10px 10px 10px;
	/*border: solid 1px orange;*/
	color: #888;
}

.formItem {
	margin-top: 10px;
	border: solid 1px #eee;
	border-radius: 6px;
	padding: 5px 5px;
	margin-bottom: 10px;
}

.postEditForm [type="text"], .postEditForm textarea {
	width: 100%;
	height: 30px;
	background: #fff;
	border: solid 1px #ccc;
	color: #888;
}

.postEditForm textarea {
	height: 110px;
}

.postEditForm [type="submit"] {
	color: #fff;
	background: orange;
	border: none;
	width: 100%;
}

.controls {
	margin-top: 10px;
	border: 1px solid transparent;
	border-radius: 2px 0 0 2px;
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	height: 32px;
	outline: none;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
}

#pac-input {
	background-color: #fff;
	font-family: Roboto;
	font-size: 15px;
	font-weight: 300;
	margin-left: 12px;
	padding: 0 11px 0 13px;
	text-overflow: ellipsis;
	width: 300px;
}

#pac-input:focus {
	border-color: #4d90fe;
}

.pac-container {
	font-family: Roboto;
}

#type-selector {
	color: #fff;
	background-color: #4d90fe;
	padding: 5px 11px 0px 11px;
}

#type-selector label {
	font-family: Roboto;
	font-size: 13px;
	font-weight: 300;
}
</style>
<title>Jabong</title>
</head>
<body>
	<c:set var="navName" value="POST" scope="session" />
	<jsp:include page="fragment/top.jsp" />
	<!-- POST START -->
	<div class="container">
		<div class="col-md-6 col-md-offset-3 postEdit">
			<form class="postEditForm" ENCTYPE="multipart/form-data"
				action="InsertPost.do" onsubmit="return checkCoords();"
				method="POST" name="InsertPostForm">
				<div id="dropzone">
					<h1>Jabong</h1>
					<h5>
						<span class="glyphicon glyphicon-circle-arrow-down"
							aria-hidden="true"></span> 投入您的美食旅程照片...
					</h5>
					<input type="file" accept="image/jpeg" id="file" name="file" />
				</div>
				<div id="postPic">
					<!-- <img src="" alt=""> -->
				</div>
				<div class="rating formItem">
					<span class="glyphicon glyphicon-cutlery" aria-hidden="true"></span>
					評分 <span class="starRating"> <input id="rating5"
						type="radio" name="rating" value="5"> <label for="rating5">5</label>
						<input id="rating4" type="radio" name="rating" value="4">
						<label for="rating4">4</label> <input id="rating3" type="radio"
						name="rating" value="3" checked> <label for="rating3">3</label>
						<input id="rating2" type="radio" name="rating" value="2">
						<label for="rating2">2</label> <input id="rating1" type="radio"
						name="rating" value="1"> <label for="rating1">1</label>
					</span>
				</div>
				<div class="formItem">
					<span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
					地點
					<!-- <input type="text" class="f" id="place" name="place" placeholder="新增地點" value="臺大鬆餅屋"> -->
					<input id="pac-input" class="controls" type="text"
						placeholder="新增地點" name="place">
					<div id="map" style="height: 200px;"></div>
				</div>
				<div class="formItem">
					<span class="glyphicon glyphicon-usd" aria-hidden="true"></span>
					價位分享 <input type="text" class="f" id="price" name="price"
						placeholder="分享價位" value="約50到80左右">
				</div>
				<div class="formItem">
					<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
					評論
					<textarea rows="4" placeholder="撰寫心情..." name="comment">拿在手裡是燙手的鬆餅體，內餡是晚一點吃就會融化的香草巧克力冰淇淋 好吃極了啦~ 鬆餅體本身烤的酥脆又鬆軟可口，因為天氣很熱，大口咬下冰淇淋鬆餅， 整個人的感覺就像在沙漠裡喝到水一樣這麼平價又好吃的鬆餅， 難怪一堆人都不怕太陽曬也要吃小木屋鬆餅。 台大校園內很多座位都可以坐著吃，也可以自己帶食物來趟野餐之旅~</textarea>
				</div>
				<div>
					<input type="hidden" id="x" name="x" /> <input type="hidden"
						id="y" name="y" /> <input type="hidden" id="w" name="w" /> <input
						type="hidden" id="h" name="h" /> <input type="hidden" id="pw"
						name="pw" /> <input type="hidden" id="ph" name="ph" /> <input
						type="hidden" id="lat" name="lat" /> <input type="hidden"
						id="lng" name="lng" /> <input type="submit" value="分享" />
				</div>
			</form>
		</div>
	</div>
	<!-- POST END -->
	<jsp:include page="/fragment/footer.jsp" />
</body>
<jsp:include page="fragment/ref_js.jsp" />
<!-- 本頁專用script -->
<script src="jcrop/js/jquery.min.js"></script>
<script src="jcrop/js/jquery.Jcrop.js"></script>
<script src="js/yinDC.js"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAmYQ-0CCxFtUWaatWTWbhx1NbPo_dDkPo&signed_in=true&libraries=places&callback=initMap"
	async defer></script>
<script src="js/google-place-autocomplete.js"></script>

</html>
