<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale= 1.0 ,minimum-scale=1.0 , maximum-scale=1.0,user-scalable= no">
<meta content="telephone=no" name="format-detection" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>${title }</title>
<link rel="stylesheet" href="${ctx }/css/sm.min.css" />
<link rel="stylesheet" href="${ctx }/css/sm-extend.min.css" />
<link rel="stylesheet" href="${ctx }/css/style.css" />
<script type="text/javascript" src="${ctx }/js/zepto.min.js"></script>
<script type="text/javascript" src="${ctx }/js/sm.min.js"></script>
<script type="text/javascript" src="${ctx }/js/sm-extend.min.js"></script>
<script type="text/javascript" src="${ctx }/js/sm-city-picker.min.js"></script>
<script type="text/javascript" src="${ctx }/js/commons.js"></script>
<script type="text/javascript" src="${ctx }/js/doT.js"></script>
<script id="docSharingList" type="text/x-dot-template">
				{{ for( var bean in it) { }} 
				<ul>
					<li>
						<a href="${ctx}/resouce/resouceInfo?lmid={{=it[bean].lmid}}" class="external">
							<div class="item-content">
								<div class="item-media">
									<img src="${ctx }/image/03.png">
								</div>
								<div class="item-inner">
									<div class="item-title-row">
										<div class="item-title col_66">{{=it[bean].lmtitle}}</div>
									</div>
									<div class="item-subtitle col_83">
										<span>课程班级:</span><span>{{=it[bean].scname}}</span>
									</div>
								</div>
							</div>
						</a>
					</li>
				</ul>
			{{ } }}
		</script>

</head>
<body>
	<div class="page-group">
		<div id="resouceListpage" class="page">
			<header class="bar bar-nav header_bg">
				<a class="button button-link button-nav pull-left back"> <span
					class="icon icon-left col_w"></span> <!--返回-->
				</a>
				<h1 class="title col_w">${title }</h1>
				<a
					href="${ctx }/resouce/teacher/addresouce?lmtype=${lmtype }&scid=${scid}"
					class="button button-link button-nav pull-right col_w external">分享</a>
			</header>
			<div class="content infinite-scroll" data-distance="100">
				<div class="list-block media-list list_defi "></div>
				<!-- 加载提示符 -->
				<div class="infinite-scroll-preloader">
					<div class="preloader"></div>
				</div>
			</div>

		</div>
	</div>
	<%-- <script type="text/javascript" src="${ctx }/js/teaching.js"></script> --%>
	<script type="text/javascript">
		var ctx = "${ctx}";
		var lmtype = '${lmtype}';
		var scid = '${scid}';
		var loading = false;
		var resoucepageIndex = 0;

		$(function() {
			$.init();
			loadResouceData();
		})

		function loadResouceData() {
			// 每次加载添加多少条目 
			var pageSize = 7;
			if (loading) {
				return;
			}
			loading = true;
			$
					.ajax({
						url : ctx + "/resouce/doList?pageSize=" + pageSize
								+ "&pageIndex=" + resoucepageIndex + "&scid="
								+ scid + "&lmtype=" + lmtype,
						success : function(data) {

							loading = false;
							if (data && data.length) {
								resoucepageIndex++;
								// 删除加载提示符
								$('.infinite-scroll-preloader').remove();
								var evalText = doT.template($("#docSharingList").text());

								$('#resouceListpage .list-block').append(
										evalText(data))
								// 更新最后加载的序号
								lastIndex = $('#resouceListpage .list-block ul').length;
								$.refreshScroller();
							} else {
								$('.infinite-scroll-preloader').remove();
								if (resoucepageIndex < 1) {
									$('#resouceListpage .list-block')
											.html(
													"<div style=\"text-align:center;\">暂无数据</div>");
								}
								// 加载完毕，则注销无限加载事件，以防不必要的加载
								$.detachInfiniteScroll($('.infinite-scroll'));
							}
						}
					});
		}
		
		
	</script>
</body>
</html>
