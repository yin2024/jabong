<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var='debug' value='true' scope='application' />
<!-- NAV START -->
<nav class="navbar navbar-fixed-top navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<div class="navbar-brand"
				onclick="javascript:location.href='${pageContext.request.contextPath}'">
				<img src="${pageContext.request.contextPath}/svg/jb.svg"
					height="20px" alt="jb">
			</div>
		</div>
		<div class="collapse navbar-collapse" id="navbar-collapse">
			<ul class="nav navbar-nav" style="margin-left: -5px;">
				<!-- HOME -->
				<c:if test="${ navName == 'HOME' }">
					<li class="active"><a href="<c:url value='/DisplayPosts' />">
							<span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;Home
					</a></li>
				</c:if>
				<c:if test="${ navName != 'HOME' }">
					<li><a href="<c:url value='/DisplayPosts' />"> <span
							class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;Home
					</a></li>
				</c:if>
				<!-- POST -->
				<c:if test="${ navName == 'POST' }">
					<li class="active"><a href="post.jsp"> <span
							class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Post
					</a></li>
				</c:if>
				<c:if test="${ navName != 'POST' }">
					<li><a href="post.jsp"> <span
							class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Post
					</a></li>
				</c:if>
				<!-- WORLD -->
				<c:if test="${ navName == 'WORLD' }">
					<li class="active"><a
						href="<c:url value='/DisplayMyPlaces' />"> <span
							class="glyphicon glyphicon-globe"></span>&nbsp;&nbsp;World
					</a></li>
				</c:if>
				<c:if test="${ navName != 'WORLD' }">
					<li><a href="<c:url value='/DisplayMyPlaces' />"> <span
							class="glyphicon glyphicon-globe"></span>&nbsp;&nbsp;World
					</a></li>
				</c:if>
				<!-- FRIEND -->
				<c:if test="${ navName == 'FRIENDS' }">
					<li class="active"><a
						href="<c:url value='/DisplayAllFriends' />"> <span
							class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;Friends
					</a></li>
				</c:if>
				<c:if test="${ navName != 'FRIENDS' }">
					<li><a href="<c:url value='/DisplayAllFriends' />"> <span
							class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;Friends
					</a></li>
				</c:if>
				<!-- GROUP -->
				<c:if test="${ navName == 'GROUPS' }">
					<li class="active"><a href="<c:url value='/Groups' />"> <span
							class="glyphicon glyphicon-glass"></span>&nbsp;&nbsp;Groups
					</a></li>
				</c:if>
				<c:if test="${ navName != 'GROUPS' }">
					<li><a href="<c:url value='/Groups' />"> <span
							class="glyphicon glyphicon-glass"></span>&nbsp;&nbsp;Groups
					</a></li>
				</c:if>
				<!-- GIFT -->
				<c:if test="${ navName == 'GIFTS' }">
					<li class="active"><a href="gifts.jsp"> <span
							class="glyphicon glyphicon-gift"></span>&nbsp;&nbsp;Gifts
					</a></li>
				</c:if>
				<c:if test="${ navName != 'GIFTS' }">
					<li><a href="gifts.jsp"> <span
							class="glyphicon glyphicon-gift"></span>&nbsp;&nbsp;Gifts
					</a></li>
				</c:if>
			</ul>
			<!-- LEFT SIDE END-->
			<!-- RIGHT SIDE START-->
			<ul class="nav navbar-nav navbar-right">
				<!-- NOTIFICATION START -->
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false"
					id="notify_num"> </a>
					<ul class="dropdown-menu" role="menu" id="notify_info">

					</ul></li>
				<!-- NOTIFICATION END -->
				<!-- USER START -->
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false"> <img
						src="${pageContext.servletContext.contextPath}/getImage?id=${LoginOK.userID}&type=user"
						height="20px" width="20px" class="img-circle">&nbsp;&nbsp;${LoginOK.name}
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="<c:url value='/DisplayMe' />"> <span
								class="glyphicon glyphicon-user"></span> Profile
						</a></li>
						<li><a href="settings.jsp"> <span
								class="glyphicon glyphicon-cog"></span> Settings
						</a></li>
						<li class="divider"></li>
						<li><a href="login.jsp"> <span
								class="glyphicon glyphicon-log-out"></span> Logout
						</a></li>
					</ul></li>
				<!-- USER END -->
			</ul>
			<!-- RIGTH SIDE END -->
		</div>
	</div>
</nav>
<!-- NAV END -->
<!-- JQUERY INCLUDE -->
<script src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
<!-- AJAX FOR NOTIFICATION INCLUDE -->
<script src="js/ajax.notify.js"></script>
<!-- LOGO ONCLICK SCRIPT -->
<script>



function notifyAccess(type, typeID) {
	if (type == 1) {
		document.forms[0].action = "<c:url value='AcceptFriends' />";
		document.forms[0].method = "POST";
		document.forms[0].elements[0].name = 'id';
		document.forms[0].elements[0].value = id;
		document.forms[0].submit();
	} else if (type == 2) {
		document.forms[0].action = "<c:url value='DisplayPosts' />";
		document.forms[0].method = "POST";
		document.forms[0].elements[0].name = 'id';
		document.forms[0].elements[0].value = typeID;
		document.forms[0].submit();
	} else {
		
	}
}
</script>