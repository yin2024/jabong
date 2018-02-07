<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="fragment/ref_css.jsp" />
<style>
.title {
	text-align: center;
	color: #fff;
	font-size: 20px;
	line-height: 40px;
	background: #FFA488;
}

.settings {
	background: #fff;
	border: solid 1px #ddd;
	text-align: center;
}

.service-box {
	color: #888;
	margin-top: 10px;
	margin-bottom: 10px;
	padding: 20px 20px;
	cursor: pointer;
	border: solid 1px #eee;
	border-radius: 10px;
	height: 200px;
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
</style>
<title>Jabong</title>
</head>
<body>
	<c:set var="navName" value="Settings" scope="session" />
	<jsp:include page="/fragment/top.jsp" />
	<!-- Content Start-->
	<div class="container">
		<div class="col-md-8 col-md-offset-2 settings">
			<div class="row title">
				<span class="glyphicon glyphicon-cog"></span>
			</div>
			<div class="row groupOption">
				<div class="col-md-4 col-sm-6 text-center">
					<div class="service-box">
						<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
						<h3>新增好友</h3>
						<form class="ff" action="SearchFriends.do" method="POST"
							name="SearchFriendsForm">
							<div class="row">
								<input id="sf1" type="radio" name="sf" value="account" checked>
								<label for="sf1">Account</label> <input id="sf2" type="radio"
									name="sf" value="name"> <label for="sf2">Name</label>
							</div>
							<div class="row">
								<input type="text" id="sf" name="sf_value" placeholder="尋找好友..."
									style="width: 120px;">
								<button type="submit" id="sf">
									<span class="glyphicon glyphicon-search"
										style="font-size: 10px;"></span>
								</button>
							</div>
						</form>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 text-center">
					<div class="service-box" onclick="AcceptFriends()">
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span>
						<h3>查詢邀請</h3>
						<p class="text-muted">查看目前加入社團，關注目前發佈活動及美食資訊。</p>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 text-center">
					<div class="service-box">
						<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
						<h3>查詢通知</h3>
						<p class="text-muted">查尋目前加入或被邀請的活動，方便您安排行程。</p>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 text-center">
					<div class="service-box">
						<span class="glyphicon glyphicon-tree-conifer" aria-hidden="true"></span>
						<h3>主題變更</h3>
						<p class="text-muted">搜索Jabong社團圈，尋找美食同好，建立美食社群。</p>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 text-center">
					<div class="service-box">
						<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
						<h3>個人裝潢</h3>
						<p class="text-muted">從社團出發，揪出美食同好，累積點數並歡聚品嚐美食。</p>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 text-center">
					<div class="service-box">
						<span class="glyphicon glyphicon-gift" aria-hidden="true"></span>
						<h3>禮品清單</h3>
						<p class="text-muted">查看目前加入社團，關注目前發佈活動及美食資訊。</p>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 text-center">
					<div class="service-box">
						<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
						<h3>任務清單</h3>
						<p class="text-muted">獲取本週任務，蓋上餐廳的圖章已獲取高額點數。</p>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 text-center">
					<div class="service-box">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						<h3>搜尋社團</h3>
						<p class="text-muted">搜索Jabong社團圈，尋找美食同好，建立美食社群。</p>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 text-center">
					<div class="service-box">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						<h3>搜尋活動</h3>
						<p class="text-muted">搜索Jabong社團圈，尋找美食同好，建立美食社群。</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Content End-->
	<jsp:include page="/fragment/footer.jsp" />
</body>
<jsp:include page="fragment/ref_js.jsp" />
<script type="text/javascript">
function AcceptFriends() {
	location.href='AcceptFriends';
}
</script>
</html>