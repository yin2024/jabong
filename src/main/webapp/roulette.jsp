<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="fragment/ref_css.jsp" />
<!-- myCss Start-->
<style>
.roul {
	position: relative;
	padding: 10px 10px 10px 10px;
	text-align: center;
	background: #fff;
}

.roul p {
	/*background: #FFdca6;*/
	background: #DAC9A6;
	padding: 5px 0px 5px 0px;
}

.roultitle {
	text-align: center;
	font-size: 20px;
	line-height: 100px;
	border: 2px dotted #FB9966;
	border-radius: 20px;
	color: #FB9966;
	background: #fff;
	font: bold 24px/100px arial;
	margin: 10px 0px 10px 0px;
}

.roulist {
	position: relative;
	/*border: solid 1px #ccc;*/
	text-align: center;
}

.lottery-star {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	margin: auto;
	border: solid 1px #000;
	border-radius: 100%;
	width: 100px;
	height: 100px;
	line-height: 100px;
	font-size: 15px;
	text-align: center;
	cursor: pointer;
	background: rgba(233, 139, 42, 0.8);
}
</style>
<!-- myCss End-->
<title>Jabong - Roulette Ajax - Testing Page</title>
</head>
<body>
	<c:set var="navName" value="GIFTS" scope="session" />
	<jsp:include page="fragment/top.jsp" />
	<!-- Content Start-->
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3 roul">
				<h2>Jabong</h2>
				<h4>命運輪盤</h4>
				<p>您的目前點數為： ${member.point} JB，消耗100JB轉動命運輪盤</p>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-md-offset-3 roul">
				<div class="lottery-star" id="lotteryBtn">
					Jabong <br>V
				</div>
				<img class="img-responsive" src="images/dartBoard.png" alt="">
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-md-offset-3 roul" id="info">
				<span class="glyphicon glyphicon-hand-up" aria-hidden="true"></span>
				點選Jabong，進行抽獎。
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-md-offset-3 roulist roul"
				style="padding-top: 20px;">
				<table class="table table-striped">
					<tr>
						<td>編號</td>
						<td>獎品名稱</td>
						<td>數量</td>
					</tr>
					<c:forEach varStatus="stVar" var="aGiftBean"
						items="${roulette_gifts}">
						<tr>
							<td>${aGiftBean.rouletteID}</td>
							<td>${aGiftBean.content}</td>
							<td>${aGiftBean.amount}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<!-- Content End-->
	<jsp:include page="/fragment/footer.jsp" />
</body>
<jsp:include page="fragment/ref_js.jsp" />
<!-- myJS Start-->
<script src="js/jQueryRotate.2.2.js"></script>
<script src="js/ajax.roulette.yin.js"></script>
<!-- myJS End-->
</html>