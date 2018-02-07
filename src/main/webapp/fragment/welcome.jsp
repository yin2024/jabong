<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
.welcome {
	text-align: center;
	padding: 10px 10px 10px 10px;
	color: #444;
	border: solid 1px #ccc;
}

.welcomePic {
	background-image: url(images/w1.png);
	background-size: cover;
	height: 500px;
}
</style>
<!-- 動態一則 start-->
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 welcome"
			style="background: #fff;">
			<h2>Welcome to Jabong!</h2>
			<p>您的帳號尚未加入好友，點選friends，拓展您的美食好友人脈。</p>
			<div class="welcomePic"></div>
		</div>
	</div>
</div>
<!-- 動態一則 end-->