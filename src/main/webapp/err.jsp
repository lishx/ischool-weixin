<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale= 1.0 ,minimum-scale=1.0 , maximum-scale=1.0,user-scalable= no">
	<meta content="telephone=no" name="format-detection" />
	<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
			<!-- <title>提示信息</title> -->
			<link rel="stylesheet" href="${ctx }/css/sm.min.css" />
			<link rel="stylesheet" href="${ctx }/css/sm-extend.min.css" />
			<link rel="stylesheet" href="${ctx }/css/style.css" />
			<script type="text/javascript" src="${ctx }/js/zepto.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm-extend.min.js"></script>
			<script type="text/javascript">
				var ctx = "${ctx}";
				var classId = '${classId}';
			</script>

			<script type="text/javascript" src="${ctx }/js/teaching.js"></script>
</head>
<body>
	<div class="page-group">
		<div id="exam_AboutArrangement" class="page">
			<header class="bar bar-nav header_bg"> <a
				class="button button-link button-nav pull-left back"> <span
				class="icon icon-left col_w"></span> <!--返回-->
			</a>
			<h1 class="title col_w">出错了</h1>
			</header>
			<div class="content bg_w">
				<div class="tm" style="height: 100%">
					<br />
					<br />
					<c:choose>
						<c:when test="${empty msg }">
							出错了
						</c:when>
						<c:otherwise>
							${ msg }
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
