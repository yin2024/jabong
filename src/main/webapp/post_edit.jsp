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
<!-- myCss Start-->
<link rel="stylesheet" href="css/yinDC.css">
<style>
.postEdit {
	border: solid 1px #ccc;
	padding: 10px 10px 10px 10px;
	background: #fff;
}

.postEdit span {
	cursor: pointer;
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
<!-- myCss End-->
<title>Jabong</title>
</head>
<body>
	<c:set var="navName" value="" scope="session" />
	<jsp:include page="/fragment/top.jsp" />
	<!-- Content Start-->
	<c:forEach varStatus="stVar" var="aPostBean" items="${post_DOP}">
		<div class="container">
			<div class="col-md-6 col-md-offset-3 postEdit">
				<span class="glyphicon glyphicon-trash" aria-hidden="true"
					onclick="deletePost(${aPostBean.postID})"> </span>&nbsp;刪除文章&nbsp;&nbsp;&nbsp;&nbsp;<span
					class="glyphicon glyphicon-remove" aria-hidden="true"
					onclick="cancelEdit()"></span>&nbsp;取消編輯 <img
					src="${pageContext.servletContext.contextPath}/getImage?id=${aPostBean.postID}&type=post"
					width="100%" alt="">
				<form class="postEditForm" action="UpdatePost.do" method="POST"
					name="UpdatePostForm">
					<div class="rating formItem">
						<span class="glyphicon glyphicon-cutlery" aria-hidden="true"></span>
						評分 <span class="starRating"> <input id="rating5"
							type="radio" name="rating" value="5"> <label
							for="rating5">5</label> <input id="rating4" type="radio"
							name="rating" value="4"> <label for="rating4">4</label> <input
							id="rating3" type="radio" name="rating" value="3" checked>
							<label for="rating3">3</label> <input id="rating2" type="radio"
							name="rating" value="2"> <label for="rating2">2</label> <input
							id="rating1" type="radio" name="rating" value="1"> <label
							for="rating1">1</label>
						</span>
					</div>
					<div class="formItem">
						<span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
						地點
						<!-- <input type="text" class="f" id="place" name="place" placeholder="新增地點" value="臺大鬆餅屋"> -->
						<input id="pac-input" class="controls" type="text"
							placeholder="新增地點" name="place" value="${aPostBean.place}">
						<div id="map" style="height: 200px;"></div>
					</div>
					<div class="formItem">
						<span class="glyphicon glyphicon-usd" aria-hidden="true"></span>
						價位分享 <input type="text" class="f" id="price" name="price"
							placeholder="分享價位" value="${aPostBean.price}">
					</div>
					<div class="formItem">
						<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
						評論
						<textarea rows="4" placeholder="撰寫心情..." name="comment">${aPostBean.comment}</textarea>
					</div>
					<div>
						<input type="hidden" id="lat" name="lat" value="${aPostBean.lat}" />
						<input type="hidden" id="lng" name="lng" value="${aPostBean.lng}" />
						<input type="hidden" id="postID" name="postID"
							value="${aPostBean.postID}" /> <input type="submit" value="更新" />
					</div>
				</form>
			</div>
		</div>
	</c:forEach>
	<!-- Content End-->
	<jsp:include page="/fragment/footer.jsp" />
</body>
<jsp:include page="fragment/ref_js.jsp" />
<!-- myJS Start-->
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAmYQ-0CCxFtUWaatWTWbhx1NbPo_dDkPo&signed_in=true&libraries=places&callback=initMap"
	async defer></script>
<script src="js/google-place-autocomplete.js"></script>
<!-- myJS End-->
<script type="text/javascript">
function deletePost(id) {
	if (confirm("確定將本篇文章移除？") ) {
		document.forms[0].action = "<c:url value='DeletePost.do?id=" + id + "' />";
		document.forms[0].method = "POST";
		document.forms[0].submit();
	} else {
	
	}
}
function cancelEdit() {
	history.go(-1);
}
</script>
</html>