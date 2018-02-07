<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="icon" type="image/x-icon" href="ico/jb.ico">
<link rel="stylesheet" href="css/login.css">
<title>Welcome to Jabong!</title>
</head>
<body>
	<c:remove var="LoginOK" scope="session" />
	<!-- 載入器START -->
	<div class="loader">
		<div>
			<img src="svg/jabong.svg">
		</div>
	</div>
	<!-- 載入器END -->
	<!-- 幻燈片START -->
	<div id="supersize">
		<a> 
			<img src="images/login3.png" />
		</a> 
		<a> 
			<img src="images/login1.jpg" />
		</a> 
		<a> 
			<img src="images/login2.jpg" />
		</a>
	</div>
	<!-- 幻燈片END -->
	<!-- 登入介面START -->
	<div id="content" type="sign">
		<div id="contentframe">
			<div class="col-xs-2 col-xs-offset-2">
				<div class="row">
					<div class="header">
						<div>
							Ja<span>Bong</span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="login">
						<form action="<c:url value='login.do' />" method="POST"
							name="loginForm">
							<input type="text" placeholder="username" name="userId">
							<input type="password" placeholder="password" name="pswd">
							<input type="submit" value="Sign in"> <input
								type="button" onclick="location.href='register.jsp'"
								value="Sign up">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 登入介面END -->
	<jsp:include page="fragment/ref_js.jsp" />
	<script src="js/supersized.2.0.js"></script>
	<!-- 幻燈片控制器 START-->
	<script>
		$(function() {
			$.fn.supersized.options = {
				startwidth : 640,
				startheight : 480,
				vertical_center : 1,
				slideshow : 1, //自動輪播 1開 0關
				navigation : 1, //播放控制鈕 1開 0關
				transition : 1,
				//0-None, 1-Fade, 2-slide top, 3-slide right, 4-slide bottom, 5-slide left
				pause_hover : 0, //滑入停止輪播
				slide_counter : 1,
				slide_captions : 1,
				slide_interval : 5000
			//換場時間
			};
			$('#supersize').supersized();
		});
	</script>
	<!-- 幻燈片控制器 END-->
</body>
</html>
