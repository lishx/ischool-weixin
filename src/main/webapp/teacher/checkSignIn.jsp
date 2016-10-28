<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
			<title>老师查看签到</title>
			<link rel="stylesheet" href="${ctx }/css/sm.min.css" />
			<link rel="stylesheet" href="${ctx }/css/sm-extend.min.css" />
			<link rel="stylesheet" href="${ctx }/css/style.css" />
			<script type="text/javascript" src="${ctx }/js/zepto.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm-extend.min.js"></script>
			<script type="text/javascript" >
				var classId ="${classId}";
				var controller="${controller}";
				$(function(){
					$(".teacherSign").on("touchend",function(){
		 				var userId = $(this).attr("data-userid");
		 				var lessonid = $(this).attr("data-lessonid");
		 				$.ajax({
							url:'${ctx }/sign/dosignByTeacher',
							data:{
								lessonid:lessonid,
								studentid:userId,
								remark:'老师修改',
								state:1
							},
							success:function(data){
								if(data.status){
									$.alert("修改签到成功",function(){
										//loadSignData();
										window.location.href="${ctx}/sign/"+controller+"?classId="+classId;
									});
									
								}else{
									$.alert(data.msg)
								}
							}
						})
		 	
		 	})
				})
			</script>
</head>
<body>
	<div class="page-group">
		<div class="page">
			<header class="bar bar-nav header_bg"> <a
				class="button button-link button-nav pull-left back"> <span
				class="icon icon-left col_w"></span> <!--返回-->
			</a>
			<h1 class="title col_w">查看签到</h1>
			</header>
			<div class="content bg_w">
				<div class="buttons-tab">
					<a href="#tab1" class="tab-link active button">已签到&nbsp;(<span>${fn:length(list)}</span>人)
					</a> <a href="#tab2" class="tab-link button">未签到&nbsp;(<span>${fn:length(notlist)}</span>人)
					</a>

				</div>
				<div class="content-block mg_0">
					<div class="tabs">
						<div id="tab1" class="tab active">
							<div class="list-block media-list list_defi">
								<ul>
								<c:forEach var="bean" items="${list }">
									<li>
										<div class="item-content">
											<div class="item-inner">
												<div class="item-title-row">
													<div class="item-title col_83">${bean.username }</div>
												</div>
												<div class="item-subtitle col_cc">
													<span>班级：</span><span>${bean.classname }
												</div>
												<div class="item-subtitle col_cc">
													<span>学号：</span><span>${bean.codeinfo }
												</div>
												<div class="item-subtitle col_cc">
													<span>地点：</span><span>${bean.signaddress }
												</div>
												<div class="item-subtitle col_cc">
													<span>时间：</span><span>${bean.signintime }
												</div>
											</div>
										</div>
									</li>
								</c:forEach>
								</ul>
							</div>
						</div>
						<div id="tab2" class="tab">
							<div class="content-block">
								<ul>
								<c:forEach var="bean" items="${notlist }">
									<li>
										<div class="item-content">
											<div class="item-inner">
												<div class="item-title-row">
													<div class="item-title col_83">
														<span>${bean.username }</span>
														<span data-userid="${bean.suid }" data-lessonid="${bean.lessonid}" class="teacherSign fr dib">签到</span>
													</div>
													<div class="item-title col_83"></div>
												</div>
												<div class="item-subtitle col_cc">
													<span>班级：</span><span>${bean.classname }
												</div>
												<div class="item-subtitle col_cc">
													<span>学号：</span><span>${bean.codeinfo }
												</div>
											</div>
											
										</div>
									</li>
								</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
