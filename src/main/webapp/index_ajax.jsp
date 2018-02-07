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
<style>
#post {
	border: solid 1px #ccc;
	background: #fff;
}

#postHead {
	/*color: #C73E3A;*/
	color: #999;
	background: #FFF;
	line-height: 80px;
	height: 80px;
}

#postImage {
	padding: 20px 15px;
	text-align: center;
}

#postImage .img-circle {
	background: #fff;
	border: solid 1px #ccc;
}

#postName h5 {
	color: orange;
	font-weight: bold;
	padding: 10px 0px;
	line-height: 0px;
}

#postName h6 {
	padding-top: 0;
	line-height: 0px;
}

#postTime {
	text-align: right;
	font-size: 5px;
	padding: 15px 5px;
	line-height: 20px;
}

#postTime .glyphicon-edit {
	color: orange;
	font-size: 15px;
}

#postLike {
	height: 50px;
	line-height: 50px;
}

#postLike button {
	border: 0px;
	background: none;
}

#postComment {
	background: #FFFAF0;
	border-left: solid 2px orange;
	border-top: solid 0px #ccc;
	border-bottom: solid 0px #ccc;
	line-height: 22px;
	font-size: 13px;
}

#postComment p {
	margin-top: 10px;
}

#respList {
	margin-top: 5px;
	color: #555;
	font-size: 10px;
}

hr {
	margin-top: 0px;
	margin-bottom: 3px;
}

#myNews {
	border: solid 1px #ccc;
	background: #fff;
}

#resp {
	width: 100%;
	height: 100%;
	border: none;
	background: #fff;
}
</style>
<meta charset="UTF-8">
<title>Jabong 動態時報</title>
</head>
<body>
	<c:set var="navName" value="HOME" scope="session" />
	<jsp:include page="fragment/top.jsp" />
	<!-- 動態一則 start-->
	<c:forEach varStatus="stVar" var="aPostBean" items="${post_DPP}">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3" id="post">
					<div class="row" id="postHead">
						<div class="col-xs-2" id="postImage">
							<img
								src="${pageContext.servletContext.contextPath}/getImage?id=${aPostBean.userID}&type=user"
								height="40" width="40" id="postUser"
								class="img-circle img-responsive">
						</div>
						<div class="col-xs-7" id="postName">
							<h5>${ aPostBean.userName }</h5>
							<h6>${ aPostBean.place }</h6>
							<h6>
								<c:forEach var="s" begin="1" end="${ aPostBean.star }">
									<c:out value='<span class="glyphicon glyphicon-star"></span>'
										escapeXml='false' />
								</c:forEach>
								<c:forEach var="s" begin="1" end="${ 5-aPostBean.star }">
									<c:out
										value='<span
									class="glyphicon glyphicon-star-empty"></span>'
										escapeXml='false' />
								</c:forEach>
							</h6>
						</div>
						<div class="col-xs-3" id="postTime">
							<c:if test="${ aPostBean.userID == LoginOK.userID }">
								<c:out
									value='<span
                                    class="glyphicon glyphicon-edit" style="cursor: pointer;" onclick="editPost(${aPostBean.postID})"></span></a>'
									escapeXml='false' />
							</c:if>
							<br> <span class="glyphicon glyphicon-time"></span>
							${aPostBean.postTime}
						</div>
					</div>
					<div class="row">
						<img
							src="${pageContext.servletContext.contextPath}/getImage?id=${aPostBean.postID}&type=post"
							class="img-responsive" style="width: 100%;">
					</div>
					<div class="row">
						<div class="col-xs-12" id="postLike">
							<c:if test="${ aPostBean.postLikeCheck == 0 }">
								<span class="glyphicon glyphicon-heart-empty"
									id="${aPostBean.postID}"></span>&nbsp;&nbsp;
								<c:if test="${aPostBean.postLikeBeans.size()>0}">
									<c:forEach varStatus="stVar" var="aPostLikeBean"
										items="${ aPostBean.postLikeBeans }">
										${aPostLikeBean.userName}&nbsp;&nbsp;
									</c:forEach>
										共${aPostBean.postLikeBeans.size()}人說這食物讚 
								</c:if>
								<c:if test="${aPostBean.postLikeBeans.size()==0}">
									成為第一位按讚的朋友！
								</c:if>
							</c:if>
							<c:if test="${ aPostBean.postLikeCheck == 1 }">
								<span class="glyphicon glyphicon-heart"></span>&nbsp;&nbsp;
								<c:forEach varStatus="stVar" var="aPostLikeBean"
									items="${ aPostBean.postLikeBeans }">
									${aPostLikeBean.userName}&nbsp;&nbsp;
								</c:forEach>
									共${aPostBean.postLikeBeans.size()}人說這食物讚 
							</c:if>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12" id="postComment">
							<p>${ aPostBean.comment }</p>
						</div>
					</div>
					<c:forEach varStatus="stVar" var="aPostRespBean"
						items="${ aPostBean.postRespBeans }">
						<div class="row">
							<div class="col-xs-12" id="respList">${aPostRespBean.userName}:
								${aPostRespBean.response} - ${aPostRespBean.time}</div>
						</div>
					</c:forEach>
					<div class="row">
						<div class="col-xs-12" id="respList">查看全部 ${ aPostBean.postRespBeans.size() }
							則回應</div>
					</div>
					<hr>
					<div class="row">
						<form action="<c:url value='InsertPostResp.do' />" method="POST"
							name="PostRespForm">
							<div class="col-xs-10">
								<input type="text" id="resp" name="resp" placeholder="留言...">
								<input type="hidden" id="postID" name="postID"
									value="${ aPostBean.postID }">
							</div>
							<div class="col-xs-2">
								<button type="submit" id="resp">
									<span class="glyphicon glyphicon-pencil"></span>
								</button>
							</div>
						</form>
					</div>
					<div class="row"></div>
				</div>
			</div>
		</div>
		<br>
	</c:forEach>
	<!-- 動態一則 end-->
	<jsp:include page="/fragment/footer.jsp" />
</body>
<jsp:include page="fragment/ref_js.jsp" />
<script src="js/ajax.postview.js"></script>
<script type="text/javascript">
function editPost(id) {
	document.forms[0].action = "<c:url value='EditPost?id=" + id + "' />";
	document.forms[0].method = "POST";
	document.forms[0].submit();
}
</script>
</html>