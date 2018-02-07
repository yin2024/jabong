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
<link rel="stylesheet" href="css/bootstrap-multiselect.css">
<style>
a {
	color: #000;
}

a:hover {
	color: orange;
}

#myMap {
	border: solid 1px #ddd;
	border-top: none;
	margin-top: 0px;
}

a {
	color: #aaa;
}

.mapContent {
	background: #eee;
}

.searchBar {
	margin-top: -10px;
	padding-top: 20px;
	padding-bottom: 20px;
	text-align: center;
	background: #fff;
}
</style>
<!-- myCss End-->
<title>Jabong</title>
</head>
<body>
	<c:set var="navName" value="WORLD" scope="session" />
	<jsp:include page="/fragment/top.jsp" />
	<!-- Content Start-->
	<div class="container">
		<div class="col-md-6 col-md-offset-3 mapContent">
			<div class="row">
				<ul class="nav nav-tabs">
					<li role="presentation" class="active"><a href="#"><span
							class="glyphicon glyphicon-heart" aria-hidden="true"></span> 我食</a></li>
					<li role="presentation"><a href="DisplayAllFriendPlaces"><span
							class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
							好友</a></li>
					<li role="presentation"><a href="#"><span
							class="glyphicon glyphicon-glass" aria-hidden="true"></span> 社團</a></li>
					<li role="presentation"><a href="#"><span
							class="glyphicon glyphicon-cloud" aria-hidden="true"></span> 附近</a></li>
				</ul>
			</div>
			<div class="row" id="myMap">
				<!-- Build your select: <-->
				<div class="col-sm-12 searchBar">
					<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
					期間： <select style="width: 100px;">
						<option>1</option>
						<option>2</option>
						<option>3</option>
					</select>
				</div>
			</div>
			<div class="row" id="myMap">
				<div id='map_canvas' style="width: 100%; height: 500px;"></div>
			</div>
			<input type="hidden" id="spotJson" value='${ place_spotJson }' /> <input
				type="hidden" id="spotCounts" value="${ place_counts }" />
			<!-- <p id="position"></p> -->
		</div>
	</div>
	<!-- Content End-->
	<jsp:include page="/fragment/footer.jsp" />
</body>
<jsp:include page="fragment/ref_js.jsp" />
<!-- myJS Start-->
<!-- 本頁專用script -->
<script src="js/bootstrap-multiselect.js"></script>
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
<script>
	var centerX;
	var centerY;
	// var centerX = 25.047240;
	// var centerY = 121.517080;

	navigator.geolocation.getCurrentPosition(success, error, options);

	var options = {
		enableHighAccuracy : true,
		timeout : 5000,
		maximumAge : 0
	};

	function success(pos) {

		var crd = pos.coords;
		centerX = crd.latitude;
		centerY = crd.longitude;

		$('#map_canvas').gmap({
			'center' : centerX + "," + centerY
		});
		$('#map_canvas').gmap().bind(
				'init',
				function(ev, map) {
					$('#map_canvas').gmap('addControl', 'control',
							google.maps.ControlPosition.LEFT_TOP);
					$('#map_canvas').gmap('option', 'zoom', 12);
					$('#map_canvas').gmap('addMarker', {
						'position' : centerX + "," + centerY,
						'bounds' : false, // true 會影響到地圖比例的大小
					}).click(function() {
						$('#map_canvas').gmap('openInfoWindow', {
							'content' : '您目前所在地!'
						}, this);
					});
				});

		// 讀取並塞點
		var arr = $("#spotJson").val(); 
		var obj = JSON.parse(arr);
		var x = $('#spotCounts').val();
		
		$.each(obj, function(index, coord) {
			var image = {
				    url: 'getImage?id=' + coord.postID + '&type=post',
				    // This marker is 20 pixels wide by 32 pixels high.
				    size: new google.maps.Size(50, 50),
				    // The origin for this image is (0, 0).
				    origin: new google.maps.Point(0, 0),
				    // The anchor for this image is the base of the flagpole at (0, 32).
				    anchor: new google.maps.Point(0, 0)
				  };
			$('#map_canvas').gmap('addMarker', {
				'position' : coord.lat + "," + coord.lng,
				'bounds' : false, // 會影響到地圖比例的大小
				'icon': new google.maps.MarkerImage( 'getImage?id=' + coord.postID + '&type=post' , undefined, undefined, undefined, new google.maps.Size(50, 50)),
			}).click(function() {
				$('#map_canvas').gmap('openInfoWindow', {
					'content' : '<div><a href="DisplayOnePost?id=' + coord.postID + '"><strong>' + coord.place + '</strong></a><br>' + coord.postTime
				}, this);
			});
			
		});
	}

	function error(err) {
		console.warn('ERROR(' + err.code + '): ' + err.message);
	}
	
</script>
<!-- myJS End-->
</html>