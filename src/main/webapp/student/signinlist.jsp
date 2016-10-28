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
			<title>学生签到</title>
			<link rel="stylesheet" href="${ctx }/css/sm.min.css" />
			<link rel="stylesheet" href="${ctx }/css/sm-extend.min.css" />
			<link rel="stylesheet" href="${ctx }/css/style.css" />
			<script type="text/javascript" src="${ctx }/js/zepto.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm-extend.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/doT.js"></script>
			<script id="qdlb" type="text/x-dot-template">
				{{ for( var bean in it) { }} 
					<ul>
					      <li>
					      	<a href="${ctx}/sign/getsignInfo?lsid={{=it[bean].lsid}}">
					        <div class="item-content">
					          <div class="item-inner">
					            <div class="item-title-row">
					              <div class="item-title col_83">{{=it[bean].subname}}</div>
					            </div>
					            <div class="item-subtitle col_99"><span>签到时间:</span><span>{{=it[bean].signintime}}</span></div>
					            <div class="item-subtitle col_99"><span>上课地点:</span><span>{{=it[bean].signaddress}}</span></div>
					          </div>
					        </div>
 							</a>
					      </li>
					    </ul>
			{{ } }}
		</script>
			<script type="text/javascript"
				src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
			<script type="text/javascript">
				var ctx = "${ctx}";
				var classId = '${classId}';
				var userId ='${userId}';
				var loading =false;
				var signpageIndex=0;
				function loadSignData(){
					// 每次加载添加多少条目
					var pageSize = 7; 
					if(loading){
						return;
					}
					loading =true;
					$.ajax({
						url : ctx + "/sign/dogetsign?userId="+userId+"&pageSize="+pageSize+"&pageIndex="+signpageIndex,
						success : function(data) {
							loading = false;
							if (data&& data.length) {
								signpageIndex++;
								// 删除加载提示符
								$('.infinite-scroll-preloader').remove();
								var evalText = doT.template($("#qdlb").text());
								$('#signList .list-block').append(evalText(data))
								// 更新最后加载的序号
								lastIndex =$('#signList .list-block ul').length;
								$.refreshScroller();
							}else{
								$('.infinite-scroll-preloader').remove();
								if(signpageIndex < 1){
									$('#signList .list-block').empty();
									
									$('#signList .list-block').append("<div style=\"text-align:center;\">暂无数据</div>");
								}
								// 加载完毕，则注销无限加载事件，以防不必要的加载
								$.detachInfiniteScroll($('.infinite-scroll'));
							}
						}
					});
				}
				
				
				$(function(){
					
					$(document).on('pageInit','#signList', function (e, id, page) {
						loadSignData();
						$(page).on('infinite',function() {
							loadSignData();
						});
					  	
					});
					
					$.init()
				})
				
			</script>
</head>
<body>
	<div class="page-group">
		<div id="signList" class="page">
			<form id="signform" action="${ctx}/sign/tosign" method="post">
				<input id="flessonid" name="lessonid" type="hidden" value="" />
				<input id="fstudentid" name="studentid" type="hidden" value="" />
				<input id="fsignaddress" name="signaddress" type="hidden" value="" />
				<input id="flongitude" name="longitude" type="hidden" value="" />
				<input id="flatitude" name="latitude" type="hidden" value="" />
				<input name="state" type="hidden" value="1" />
			</form>
			<header class="bar bar-nav header_bg"> 
			<a href="${ctx }/getcode?code=studentMain"
				class="button button-link button-nav pull-left external"> <span
				class="icon icon-left col_w"></span> <!--返回-->
			</a>
			<h1 class="title col_w">签到</h1>
			<!-- <a id="addsign"
				class=" button button-link button-nav pull-right col_w">扫一扫</a>  -->
			</header>
				<div class="content infinite-scroll infinite-scroll-bottom" data-distance="20">
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
