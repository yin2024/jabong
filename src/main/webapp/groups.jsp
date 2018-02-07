<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="gaBean" class="_06_group.model.GroupAccessBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="fragment/ref_css.jsp" />
<!-- myCss Start-->
<style>
.title {
	text-align: center;
	color: #fff;
	font-size: 40px;
	line-height: 100px;
}

.service-box {
	color: #888;
	margin-top: 40px;
	margin-bottom: 40px;
	padding: 10px 10px;
	cursor: pointer;
	border: solid 1px #fff;
	border-radius: 10px;
}

.service-box p {
	font-size: 10px;
}

.service-box:hover {
	background: #eee;
	border: solid 1px #ccc;
	border-radius: 10px;
}

.service-box span {
	font-size: 50px;
}

.no-gutter div {
	background-size: cover;
	height: 200px;
	cursor: pointer;
	text-align: center;
	vertical-align: middle;
	padding: 0px 0px;
}

.no-gutter p {
	height: 100%;
	opacity: 0;
	font-size: 30px;
}

.no-gutter p:hover {
	opacity: 1;
	background-color: rgba(256, 256, 256, 0.5);
}

.group_kind {
	text-align: center;
}

#myGroup {
	background: #fff;
	height: 200px;
	text-align: center;
	cursor: pointer;
}

#myGroup img {
	margin-top: 13px;
	background: #fff;
}
</style>
<!-- myCss End-->
<title>Jabong</title>
</head>
<body>
	<c:set var="navName" value="GROUPS" scope="session" />
	<jsp:include page="/fragment/top.jsp" />
	<!-- Content Start-->
	<div class="container" style="background: #fff;">
		<div class="row" style="background: #EBB471; color: orange;">
			<div class="col-lg-12 text-center">
				<img src="svg/jabong.svg" alt=""
					style="background: #fff; border-radius: 100%;">
			</div>
		</div>
		<div class="row groupOption">
			<div class="col-md-3 text-center">
				<div class="service-box">
					<span class="glyphicon glyphicon-bullhorn" aria-hidden="true"></span>
					<h3>開始揪團</h3>
					<p class="text-muted">從社團出發，揪出美食同好，累積點數並歡聚品嚐美食。</p>
				</div>
			</div>
			<div class="col-md-3 text-center">
				<div class="service-box">
					<span class="glyphicon glyphicon-glass" aria-hidden="true"></span>
					<h3>我的社團</h3>
					<p class="text-muted">查看目前加入社團，關注目前發佈活動及美食資訊。</p>
				</div>
			</div>
			<div class="col-md-3 text-center">
				<div class="service-box">
					<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
					<h3>我的活動</h3>
					<p class="text-muted">查尋目前加入或被邀請的活動，方便您安排行程。</p>
				</div>
			</div>
			<div class="col-md-3 text-center">
				<div class="service-box">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<h3>搜尋社團</h3>
					<p class="text-muted">搜索Jabong社團圈，尋找美食同好，建立美食社群。</p>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 title" style="background: #B54434;">熱門</div>
		</div>
		<div class="row no-gutter">
			<c:forEach varStatus="stVar" var="aGroupBean" items="${six_groups}">
				<div class="col-lg-4 col-sm-6 gp"
					style="background-image: url(${pageContext.servletContext.contextPath}/getImage?id=${aGroupBean.groupID}&type=group); ">
					<p>${aGroupBean.name}</p>
				</div>
			</c:forEach>
		</div>
		<div class="row">
			<div class="col-lg-12 title" style="background: #eee; color: #888;">
				分類</div>
		</div>
		<div class="row group_kind">
			<div class="col-md-4 col-sm-6" id="myGroup"
				style="background: #FEDFE1">
				<img src="svg/taiwan.svg" alt="" height="100px" width="100px"
					class="img-circle">
				<h4>臺灣味</h4>
				<p>小吃、夜市、合菜</p>
			</div>
			<div class="col-md-4 col-sm-6" id="myGroup"
				style="background: #B28FCE">
				<img src="svg/sushi.svg" alt="" height="100px" width="100px"
					class="img-circle">
				<h4>日式</h4>
				<p>丼物、炸物、壽司</p>
			</div>
			<div class="col-md-4 col-sm-6" id="myGroup"
				style="background: #A5DEE4">
				<img src="svg/hamburger.svg" alt="" height="100px" width="100px"
					class="img-circle">
				<h4>美式</h4>
				<p>漢堡、速食</p>
			</div>
			<div class="col-md-4 col-sm-6" id="myGroup"
				style="background: #A8D8B9">
				<img src="svg/korea2.svg" alt="" height="100px" width="100px"
					class="img-circle">
				<h4>韓式</h4>
				<p>人參雞</p>
			</div>
			<div class="col-md-4 col-sm-6" id="myGroup"
				style="background: #B5CAA0">
				<img src="svg/italian.svg" alt="" height="100px" width="100px"
					class="img-circle">
				<h4>義式</h4>
				<p>披薩、義大利麵</p>
			</div>
			<div class="col-md-4 col-sm-6" id="myGroup"
				style="background: #D9CD90">
				<img src="svg/coffee.svg" alt="" height="100px" width="100px"
					class="img-circle">
				<h4>輕食</h4>
				<p>咖啡、下午茶、早午餐</p>
			</div>
			<div class="col-md-4 col-sm-6" id="myGroup"
				style="background: #FFBA84">
				<img src="svg/hongkong.svg" alt="" height="100px" width="100px"
					class="img-circle">
				<h4>港式</h4>
				<p>港式點心、飲茶</p>
			</div>
			<div class="col-md-4 col-sm-6" id="myGroup"
				style="background: #F19483">
				<img src="svg/alcohol.svg" alt="" height="100px" width="100px"
					class="img-circle">
				<h4>酒類</h4>
				<p>酒吧、啤酒</p>
			</div>
			<div class="col-md-4 col-sm-6" id="myGroup"
				style="background: #D7C4BB">
				<img src="svg/thai.svg" alt="" height="100px" width="100px"
					class="img-circle">
				<h4>南島料理</h4>
				<p>泰式、越式</p>
			</div>
		</div>
	</div>
	<!-- Content End-->
	<jsp:include page="/fragment/footer.jsp" />
</body>
<jsp:include page="fragment/ref_js.jsp" />
<!-- myJS Start-->
<script>
	var w = parseInt($('.gp').css("width"));
	$('.gp').css("height", w);
	$('.gp p').css("padding-top", w / 2 - 20);
</script>
<!-- myJS End-->
</html>