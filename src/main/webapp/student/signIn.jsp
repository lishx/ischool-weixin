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
		<link rel="stylesheet" href="${ctx }/css/sm.min.css" />
		<link rel="stylesheet" href="${ctx }/css/sm-extend.min.css" />
		<link rel="stylesheet" href="${ctx }/css/style.css" />
		<script type="text/javascript" src="${ctx }/js/zepto.min.js"></script>
		<script type="text/javascript" src="${ctx }/js/sm.min.js"></script>
		<script type="text/javascript" src="${ctx }/js/sm-extend.min.js"></script>
		<script type="text/javascript" src="${ctx }/js/sm-city-picker.min.js"></script>
		<script type="text/javascript" src="${ctx }/js/commons.js"></script>
</head>
<body>
	<form id="signform" action="${ctx}/sign/tosign" method="post">
		<input id="flessonid" name="lessonid" type="hidden" value="" /> <input
			id="fstudentid" name="studentid" type="hidden" value="${userId}" />
		<input id="fsignaddress" name="signaddress" type="hidden" value="" />
		<input id="flongitude" name="longitude" type="hidden" value="" /> <input
			id="flatitude" name="latitude" type="hidden" value="" /> <input
			name="state" type="hidden" value="1" />
	</form>
	<script type="text/javascript"
		src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
	<script type="text/javascript">
		var ctx = "${ctx}";
		var classId = '${classId}';
		var latitude = null;
		var longitude = null;
		var lessonid = null;
		wx.config({
			debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			appId : '${appId}', // 必填，公众号的唯一标识
			timestamp : '${timestamp}', // 必填，生成签名的时间戳
			nonceStr : '${nonceStr}', // 必填，生成签名的随机串
			signature : '${signature}',// 必填，签名，见附录1
			jsApiList : [ "openLocation", "getLocation", "scanQRCode" ]
		// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});
		wx
				.ready(function() {
					// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
					wx
							.checkJsApi({
								jsApiList : [ "getLocation", "scanQRCode" ], // 需要检测的JS接口列表，所有JS接口列表见附录2,
								success : function(res) {
									wx
											.scanQRCode({
												// 默认为0，扫描结果由微信处理，1则直接返回扫描结果
												needResult : 1,
												desc : 'scanQRCode desc',
												success : function(res) {
													//res.lessonid=1;
													//$.showPreloader();
													//扫码后获取结果参数赋值给Input
													//$.alert(res && res.lessonid)
													var lessionStr = res.resultStr;
													if (!lessionStr) {
														$.alert(
																"扫描失败",
																function() {
																	wx.closeWindow();
																});
														return false;
													}
													var data = JSON
															.parse(lessionStr);

													if (data && data.lessonid) {

														lessonid = data.lessonid;
														wx
																.getLocation({
																	type : 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
																	success : function(
																			loc) {
																		latitude = loc.latitude; // 纬度，浮点数，范围为90 ~ -90
																		longitude = loc.longitude; // 经度，浮点数，范围为180 ~ -180。
																		$
																				.ajax({
																					url : "http://apis.map.qq.com/ws/geocoder/v1/?location="
																							+ latitude
																							+ ","
																							+ longitude
																							+ "&coord_type=5&key=ZAUBZ-NCVKX-HNQ43-TBGOG-HTXLQ-FOFYT&output=jsonp&callback=calllocation",
																					type : "GET",
																					dataType : 'jsonp',
																					jsonp : 'calllocation',
																					fail : function() {
																						$.alert("获取地理位置信息失败");
																					}
																				});

																	},
																	fail : function() {
																		$.alert(
																				"获取地理位置信息失败",
																				function() {
																					wx
																							.closeWindow();
																				});
																		return false;
																	},
																	cancel : function() {
																		wx
																				.closeWindow();
																	},
																});
													} else {
														$
																.alert(
																		"扫描失败",
																		function() {
																			wx
																					.closeWindow();
																		});
													}

												},
												cancel : function() {
													wx.closeWindow();
												},
												fail : function() {
													$.hidePreloader();
													$.alert(
															"扫描失败，未找到课程信息",
															function() {
																wx
																		.closeWindow();
															});
												}
											});

								}
							});
				});

		function calllocation(data) {
			//$.alert(JSON.stringify(data));
			var address = data.result.formatted_addresses.recommend;
			$("#flessonid").val(lessonid);
			$("#fsignaddress").val(address);
			$("#flongitude").val(longitude);
			$("#flatitude").val(latitude);
			//	$.showPreloader()
			$("#signform").submit();

		}
	</script>
</body>
</html>
