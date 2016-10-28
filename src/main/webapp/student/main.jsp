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
			<title>教学教务</title>
			<link rel="stylesheet" href="${ctx }/css/sm.min.css" />
			<link rel="stylesheet" href="${ctx }/css/sm-extend.min.css" />
			<link rel="stylesheet" href="${ctx }/css/style.css" />
			<script type="text/javascript" src="${ctx }/js/zepto.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm-extend.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm-city-picker.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/commons.js"></script>
			<script type="text/javascript" src="${ctx }/js/doT.js"></script>
			<script type="text/javascript">
				var ctx = "${ctx}";
				var studentId ='${studentId}';
			</script>
			<script id="ksap" type="text/x-dot-template">
				{{ for( var bean in it) { }} 
				<ul>
					<li>
						<a href="${ctx}/exam/aboutExam?examid={{=it[bean].examid}}">
							<div class="item-content">
								<div class="item-media">
									<img src="${ctx }/image/03.png">
								</div>
								<div class="item-inner">
									<div class="item-title-row">
										<div class="item-title col_66">{{=it[bean].subname}}</div>
									</div>
									<div class="item-subtitle col_83">
										<span>考试时间:</span><span>{{=it[bean].examtime}}</span>
									</div>
								</div>
							</div>
						</a>
					</li>
				</ul>
			{{ } }}
		</script>
		<script id="zylb" type="text/x-dot-template">
				{{ for( var bean in it) { }} 
				<ul>
					<li>
						<a  href="${ctx}/work/student/workInfo?studentId={{=it[bean].studentid}}&homeworkid={{=it[bean].homeworkid}}">
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
			<script type="text/javascript" src="${ctx }/js/teaching.js"></script>
			<%-- <script type="text/javascript" src="${ctx }/js/jweixin-1.0.0.js"></script> --%>
			
			<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
			<script type="text/javascript">
			
			wx.config({
				debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				appId : '${appId}', // 必填，公众号的唯一标识
				timestamp : '${timestamp}', // 必填，生成签名的时间戳
				nonceStr : '${nonceStr}', // 必填，生成签名的随机串
				signature : '${signature}',// 必填，签名，见附录1
				jsApiList : ["getLocation" ,"scanQRCode"]
			// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
			
			wx.ready(function() {
				// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
				wx.checkJsApi({
					jsApiList : ["getLocation" ,"scanQRCode"], // 需要检测的JS接口列表，所有JS接口列表见附录2,
					success : function(res) {
						//判断是否是通过地图定为过
						console.log("ok"+res)

					}
				});
			});
			wx.error(function(res) {
				// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
				console.log("fail"+res);
			});
			 var latitude=null,longitude=null;
			wx.getLocation({
			    type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
			    success: function (res) {
			         latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
			         longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
			        var speed = res.speed; // 速度，以米/每秒计
			        var accuracy = res.accuracy; // 位置精度
			        console.log([latitude,longitude,speed,accuracy])
			    }
			});
			
			</script>
</head>
<body>
	<div class="page-group">
		<div class="page">
			<header class="bar bar-nav header_bg barline_no"> <a
				class="button button-link button-nav pull-left back"> <span
				class="icon icon-left col_w"></span> <!--返回-->
			</a>
			<h1 class="title col_w">教务、教学</h1>
<!-- 			 <a id="addsignmain" class=" button button-link button-nav pull-right col_w">扫一扫</a> -->
			</header>
			<div class="content">
				<img src="${ctx }/image/01.png" class="img" />
				<div class="row mgt_05 no-gutter">
					<div class="col-33 disply_flex">
						<a href="${ctx }/exam/student/examarrangement?studentId=${studentId}">
							<div class="col_inner">
								<img src="${ctx }/image/02.png" />
								<p>考试安排</p>
							</div>
						</a>
					</div>
					<div class="col-33 disply_flex">
						<a href="${ctx }/getcode?code=togetsign" class="external">
							<div class="col_inner">
								<img src="${ctx }/image/02.png" />
								<p>签到查看</p>
							</div>
						</a>
					</div>
					<div class="col-33 disply_flex">
						<a href="${ctx }/work/student/homework?studentId=${studentId }">
							<div class="col_inner">
								<img src="${ctx }/image/02.png" />
								<p>作业查看</p>
							</div>
						</a>
					</div>
					<div class="col-33 disply_flex">
					<a  href="${ctx }/class/subjectClass?suid=${studentId }&lmtype=1">
						<div class="col_inner">
							<img src="${ctx }/image/02.png" />
							<p>资料分享</p>
						</div>
					</a>
					</div>
					<div class="col-33 disply_flex">
					<a  href="${ctx }/class/subjectClass?suid=${studentId }&lmtype=2">
						<div class="col_inner">
							<img src="${ctx }/image/02.png" />
							<p>知识拓展</p>
						</div>
					</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
