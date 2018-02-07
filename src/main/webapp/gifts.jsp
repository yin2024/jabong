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
.giftRow2 {
	padding: 40px 0px 40px 0px;
	background: #fff;
	text-align: center;
}

.giftRow3 {
	padding: 40px 0px 40px 0px;
	background: #DAC9A6;
	color: #888;
	text-align: center;
}

.giftRow4 {
	padding: 40px 0px 40px 0px;
	background: #fff;
	text-align: center;
}

.giftRow5 {
	padding: 40px 0px 40px 0px;
	background: #6E75A4;
	color: #888;
	text-align: center;
}

.myGift {
	padding-top: 20px;
	padding-bottom: 20px;
	background: #fff;
	color: #333;
}

.myGift p {
	font-size: 10px;
	color: #888;
}

.my2Gift {
	padding-right: 0px;
	padding-left: 0px;
}

.sticker {
	height: 100px;
	background-repeat: no-repeat;
	background-position: center;
	left: 40%;
	z-index: 1;
	cursor: pointer;
}

#s1 {
	background-image: url(svg/gifts/_00_gaming5.svg);
}

#s1:hover {
	background-image: url(svg/gifts/_00_gaming4.svg);
}

#s2 {
	background-image: url(svg/gifts/_01_giveToSb6.svg);
}

#s2:hover {
	background-image: url(svg/gifts/_01_giveToSb5.svg);
}

#s3 {
	background-image: url(svg/gifts/_03_certificate4.svg);
}

#s3:hover {
	background-image: url(svg/gifts/_03_certificate3.svg);
}

#s4 {
	background-image: url(svg/gifts/_04_50discount2.svg);
}

#s4:hover {
	background-image: url(svg/gifts/_04_50discount.svg);
}

#s5 {
	background-image: url(svg/gifts/_04_50discount4.svg);
}

#s5:hover {
	background-image: url(svg/gifts/_04_50discount3.svg);
}

#s6 {
	background-image: url(svg/gifts/_02_checkIt4.svg);
}

#s6:hover {
	background-image: url(svg/gifts/_02_checkIt3.svg);
}

#s7 {
	background-image: url(svg/gifts/_05_fastFood2.svg);
}

#s7:hover {
	background-image: url(svg/gifts/_05_fastFood3.svg);
}

#s8 {
	background-image: url(svg/gifts/_00_gaming.svg);
}

#s8:hover {
	background-image: url(svg/gifts/_00_gaming2.svg);
}
</style>
<!-- myCss End-->
<title>Jabong</title>
</head>
<body>
	<c:set var="navName" value="GIFTS" scope="session" />
	<jsp:include page="/fragment/top.jsp" />
	<!-- Content Start-->
	<div class="container"
		style="background: #ccc; margin-top: -20px; width: 100%;">
		<div class="row">
			<div id="carousel-example-generic" class="carousel slide"
				data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0"
						class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				</ol>
				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="images/g1.png" alt="...">
						<div class="carousel-caption">
							<h3>
								<span class="glyphicon glyphicon-screenshot" aria-hidden="true"></span>
								命運輪盤
							</h3>
							<p>每天各登入一次轉輪盤，有機會獲得Taipei 101 85樓高空景觀餐廳禮券1000元整。</p>
						</div>
					</div>
					<div class="item">
						<img src="images/g2.png" alt="...">
						<div class="carousel-caption">
							<h3>
								<span class="glyphicon glyphicon-screenshot" aria-hidden="true"></span>
								好友送禮
							</h3>
							<p>消耗點數，贈送禮券給志同道合的好友。</p>
						</div>
					</div>
					<div class="item">
						<img src="images/g3.png" alt="...">
						<div class="carousel-caption">
							<h3>
								<span class="glyphicon glyphicon-screenshot" aria-hidden="true"></span>
								打卡探險
							</h3>
							<p>獲取本週任務，蓋上餐廳的圖章已獲取高額點數。</p>
						</div>
					</div>
				</div>
				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-example-generic"
					role="button" data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#carousel-example-generic"
					role="button" data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>
		<div class="row giftRow2">
			<div class="col-md-6 col-md-offset-3">
				<h2>Jabong</h2>
				<h4>禮品兌換</h4>
				<p>以下提供近期各種美食新聞，及優惠店家相關資訊。</p>
			</div>
		</div>
		<div class="row giftRow3">
			<div class="col-md-10 col-md-offset-1 myGift">
				<div class="row">
					<div class="col-md-6 my2Gift">
						<div class="col-xs-6 myGift">
							<div class="row sticker" id="s1" data-toggle="tooltip"
								data-placement="top"
								title="每天各登入一次轉輪盤，有機會獲得Taipei 101 85樓高空景觀餐廳禮券1000元整。"
								onclick="location.href='Roulette'"></div>
							<div class="row">
								<h4>命運輪盤</h4>
								<p>每日現轉一次</p>
							</div>
						</div>
						<div class="col-xs-6 myGift">
							<div class="row sticker" id="s2" data-toggle="tooltip"
								data-placement="top" title="消耗點數，贈送禮券給志同道合的好友。"></div>
							<div class="row">
								<h4>好友送禮</h4>
								<p>贈送禮券給好友</p>
							</div>
						</div>
					</div>
					<div class="col-md-6 my2Gift">
						<div class="col-xs-6 myGift">
							<div class="row sticker" id="s3" data-toggle="tooltip"
								data-placement="top" title="在已登入Jabong之固定店家前往消費達到10次以上即可獲得優惠。"></div>
							<div class="row">
								<h4>集點優惠</h4>
								<p>蓋上餐廳的圖章獲取高額點數</p>
							</div>
						</div>
						<div class="col-xs-6 myGift">
							<div class="row sticker" id="s4" data-toggle="tooltip"
								data-placement="top" title="已登入在Jabong之店家的所有優惠細項。"></div>
							<div class="row">
								<h4>折價優惠</h4>
								<p>贈送禮券給好友</p>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 my2Gift">
						<div class="col-xs-6 myGift">
							<div class="row sticker" id="s5" data-toggle="tooltip"
								data-placement="top" title="達成5處或以上打卡紀錄並TAG店家即可多得精美贈品。"></div>
							<div class="row">
								<h4>多點觸發</h4>
								<p>贈送禮券給好友</p>
							</div>
						</div>
						<div class="col-xs-6 myGift">
							<div class="row sticker" id="s6" data-toggle="tooltip"
								data-placement="top" title="只要飲食消費並PO文打卡TAG店家即可獲得點數。"></div>
							<div class="row">
								<h4>打卡任務</h4>
								<p>贈送禮券給好友</p>
							</div>
						</div>
					</div>
					<div class="col-md-6 my2Gift">
						<div class="col-xs-6 myGift">
							<div class="row sticker" id="s7" data-toggle="tooltip"
								data-placement="top" title="完成已登入Jabong之店家的大胃王相關競賽即可獲得神秘獎勵"></div>
							<div class="row">
								<h4>吃飽就送</h4>
								<p>吃飽飽送大禮</p>
							</div>
						</div>
						<div class="col-xs-6 myGift">
							<div class="row sticker" id="s8" data-toggle="tooltip"
								data-placement="top" title="通報此處遇雷事蹟圖文並茂經由多方認可必定有獎。"></div>
							<div class="row">
								<h4>雷到有獎</h4>
								<p>贈送禮券給好友</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row giftRow4">
			<div class="col-md-6 col-md-offset-3">
				<h2>Jabong</h2>
				<h4>優惠報報</h4>
				<p>以下提供近期各種美食新聞，及優惠店家相關資訊。</p>
			</div>
		</div>
		<div class="row giftRow5">
			<div class="col-md-10 col-md-offset-1 myGift">
				<div class="col-md-4" id="myGift">
					<img src="images/711.png" alt="" height="100px" width="100px">
					<h4>7-11禮券</h4>
					<p>消耗點數，獲取全省7-11門市之禮券。</p>
				</div>
				<div class="col-md-4" id="myGift">
					<img src="images/starbucks.png" alt="" height="100px" width="100px">
					<h4>星巴克買一送一</h4>
					<p>消耗點數，獲取星巴克優惠。</p>
				</div>
				<div class="col-md-4" id="myGift">
					<img src="images/kfc.png" alt="" height="100px" width="100px">
					<h4>肯德基</h4>
					<p>消耗點數，獲取肯德基雞桶。</p>
				</div>
			</div>
		</div>
	</div>

	<!-- Content End-->
	<jsp:include page="/fragment/footer.jsp" />
</body>
<jsp:include page="fragment/ref_js.jsp" />
<!-- myJS Start-->
<script src="js/carousel.js"></script>
<script src="js/tooltip.js"></script>
<script>
	$(function() {
		$('[data-toggle="tooltip"]').tooltip()
	})
</script>
<!-- myJS End-->
</html>