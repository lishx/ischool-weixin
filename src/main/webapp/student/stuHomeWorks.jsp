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
			<title>考试安排</title>
			<link rel="stylesheet" href="${ctx }/css/sm.min.css" />
			<link rel="stylesheet" href="${ctx }/css/sm-extend.min.css" />
			<link rel="stylesheet" href="${ctx }/css/style.css" />
			<script type="text/javascript" src="${ctx }/js/zepto.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm-extend.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/doT.js"></script>
				<script id="zylb" type="text/x-dot-template">
				{{ for( var bean in it) { }} 
				<ul>
					<li>
						<a href="${ctx}/work/student/workInfo?studentId=${studentId}&homeworkid={{=it[bean].homeworkid}}">
							<div class="item-content">
								<div class="item-media">
									<img src="${ctx }/image/03.png">
								</div>
								<div class="item-inner">
									<div class="item-title-row">
										<div class="item-title col_66">{{=it[bean].scname}}</div>
									</div>
									<div class="item-subtitle col_83">
										<span>上传时间:</span><span>{{=it[bean].createtime}}</span>
									</div>
								</div>
							</div>
						</a>
					</li>
				</ul>
			{{ } }}
		</script>
			<script type="text/javascript" src="${ctx }/js/teaching.js"></script>
			<script type="text/javascript">
				var ctx = "${ctx}";
				var classId = '${classId}';
				var studentId ='${studentId}';
				loadHomeWorkData();
			</script>
			
			<style type="text/css">
.infinite-scroll-preloader {
	margin-top: -20px;
}
</style>
</head>
<body>
	
		<div class="page-group">
			<div id="homeworkpage" class="page">
				<header class="bar bar-nav header_bg"> <a
					class="button button-link button-nav pull-left back"> <span
					class="icon icon-left col_w"></span> <!--返回-->
				</a>
					<h1 class="title col_w">作业列表</h1>
<%-- 				 	<a href="${ctx }/exam/toadd?classId=${classId}" class="button button-link button-nav pull-right col_w">新增</a> --%>
				</header>
				<div class="content infinite-scroll" data-distance="100">
					<div class="list-block media-list list_defi ">
						
					</div>
					<!-- 加载提示符 -->
					<div class="infinite-scroll-preloader">
						<div class="preloader"></div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>
