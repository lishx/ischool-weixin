<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale= 1.0 ,minimum-scale=1.0 , maximum-scale=1.0,user-scalable= no">
	<meta content="telephone=no" name="format-detection" />
	<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
			<title>考试相关安排</title>
			<link rel="stylesheet" href="${ctx }/css/sm.min.css" />
			<link rel="stylesheet" href="${ctx }/css/sm-extend.min.css" />
			<link rel="stylesheet" href="${ctx }/css/style.css" />
			<script type="text/javascript" src="${ctx }/js/zepto.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm-extend.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm-city-picker.min.js"></script>
			<script type="text/javascript">
				var ctx = "${ctx}";
				var classId = '${classId}';
			</script>
			<script type="text/javascript" src="${ctx }/js/teaching.js"></script>
</head>
<body>
	<div class="page-group">
		<div id="addexam" class="page">
			<header class="bar bar-nav header_bg"> <a
				class="button button-link button-nav pull-left back"> <span
				class="icon icon-left col_w"></span> <!--返回-->
			</a>
			<h1 class="title col_w">考试相关安排</h1>
			</header>
			<div class="content bg_w">
				<form id="addexamform" method="post" action="${ctx }/exam/doadd" >
					<input id="classId" type="hidden" name="scid" value="${classId }" />
					<input id="formexamtime" type="hidden" name="examtimeVo" value="" />
					<input id="formendtime" type="hidden" name="endtimeVo" value="" />
					<input id="msId" type="hidden" name="msid" value="${lesson.tsid }" />
					<input id="formAddress" type="hidden" name="address" value="" />
					<input id="formDescription" type="hidden" name="examreq" value="" />
					<div class="list-block media-list list_defi">
						<ul>
							<li>
									<div class="item-content">
										<div class="item-inner">
											<div class="item-title-row open-about">
												<div class="item-title col_cc">课程名称</div>
											</div>
											<div id="leassonName" class="item-subtitle col_4d ">${lesson.subname }</div>
										</div>
									</div>
							</li>
							<li>
									<div class="item-content">
										<div class="item-inner">
											<div class="item-title-row open-about">
												<div class="item-title col_cc">标题</div>
											</div>
											<input type="text" name="examtitle" value="">
										</div>
									</div>
							</li>
							<!-- <li>
									<div class="item-content">
										<div class="item-inner">
											<div class="item-title-row open-about">
												<div class="item-title col_cc">学期</div>
											</div>
											<select name="semester">
												<option value="1">上学期</option>
												<option value="2">下学期</option>
											</select>
										</div>
									</div>
							</li> -->
							<li>
									<div class="item-content">
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title col_cc">考试时间</div>
											</div>
											<div class="item-subtitle col_4d">
												<div>
													<input type="text"  value="" id="examtime"   />
												</div>至
												<div>
													<input type="text"  value="" id="endtime" />
												</div>
												
											</div>
										</div>
									</div>
							</li>
							<li>
									<div class="item-content">
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title col_cc">考试说明</div>
											</div>
											<div id="description" contenteditable="true" class="col_4d edt"></div>
										</div>
									</div>
							</li>
							<li>
									<div class="item-content">
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title col_cc">考试地点</div>
											</div>
											<div id="address" contenteditable="true" class="col_4d edt"></div>
										</div>
									</div>
							</li>
						</ul>
					</div>
					  <p class="defi_c"><a href="javascript:;" id="submit" class="button button-fill mgt_2">提交</a></p>
				</form>
			</div>

			<!--Popup -->
			<div class="popup popup-about">
				<div id="leassonList" class=" content-block">
					<p>
						<a href="#" class="close-popup">关闭</a>
					</p>
					<c:forEach var="t" items="${lessons }">
						<p class="leassonItem" data-id="${t.tsid }">${t.subname }</p>
					</c:forEach>
				</div>
			</div>
			<!--Popup -->
		</div>
	</div>
</body>
</html>
