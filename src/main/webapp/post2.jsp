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
<title>Jabong</title>
</head>
<body>
	<c:set var="navName" value="Post" scope="session" />
	<jsp:include page="fragment/top.jsp" />
	<!-- POST START -->
	<div class="container" id="post">
		<div class="row">
			<form class="ff" ENCTYPE="multipart/form-data" action="InsertPost.do"
				onsubmit="return checkCoords();" method="POST" name="InsertPostForm">
				<div class="col-md-6 col-md-offset-3">
					<div id="dropzone">
						<h1>Jabong</h1>
						<h5>
							<span class="glyphicon glyphicon-circle-arrow-down"
								aria-hidden="true"></span> 投入您的美食旅程照片...
						</h5>
						<input type="file" accept="image/jpeg" id="file" name="file" />
					</div>
				</div>
				<div class="col-md-6 col-md-offset-3" id="postPic">
					<!-- <img src="" alt=""> -->
				</div>
				<div class="col-md-6 col-md-offset-3" id="postInfo">
					<div class="rating f">
						評價： <span class="starRating"> <input id="rating5"
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
					<input type="text" class="f" id="place" name="place"
						placeholder="新增地點"> <input type="text" class="f"
						id="price" name="price" placeholder="分享價位">
					<textarea class="f" rows="4" placeholder="撰寫心情..." name="comment"></textarea>
					<input type="hidden" id="x" name="x" /> <input type="hidden"
						id="y" name="y" /> <input type="hidden" id="w" name="w" /> <input
						type="hidden" id="h" name="h" /> <input type="hidden" id="pw"
						name="pw" /> <input type="hidden" id="ph" name="ph" /> <input
						type="submit" value="分享" class="f" />

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

</html>
