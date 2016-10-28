<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body,html,#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
}

#l-map {
	float:left;
	height: 300px;
	width: 100%;
}

#r-result {
	width: 100%;
	margin-left: 25px;
	font-size: 14px;
	margin-top: 5px;
}

#r-results {
	float:left;
	width: 100%;
	height:auto;
	border:1px solid blue;
}

body {
	float: left;
	width: 100%;
	height: 100%;
	position: relative;
}

.map-max {
	float:left; width:100%; height:100%;; position:relative;
}
.search-cont{ position:absolute; left:30px; top:10px; right:10px; height:26px; z-index:9;}
.search-ipt{ float:left; margin-left:5px; height:26px; line-height:26px; width:200px; border:1px solid #999; text-indent:5px; color:#333;}
.search-btn{ float:left; margin-left:10px; color:#fff; text-laign:center; width:50px; height:30px; line-height:30px; padding:0 !important; border-radius:5px; background:#5DB75D; border:1px solid #5db75d;}
#r-results{ float:left; width:100%; height:auto;}
#button{ position:absolute; left:0; right:0; bottom:0; height:76px }
#button .bttn{ float:left; display:block !important; width:94%; margin-left:3%; height:46px; line-height:46px; border-radius:5px; border:1px solid #336600; font-size:20px; margin-top:15px; text-align:center; background:#5DB75D; color:#fff;}
.cont{ position:Absolute;left:0; top:0; right:0; bottom:76px; overflow:auto;}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=DMK7x5azS9vRccC1X4HggW78"></script>
<script type="text/javascript" src="/ep-weixin/js/jquery-1.7.2.min.js"></script>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="/ep-weixin/common/bootstrap-3.3.5/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="/ep-weixin/common/bootstrap-3.3.5/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="/ep-weixin/common/jquery/jquery-1.9.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="/ep-weixin/common/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script src="/ep-weixin/common/weixin/jweixin-1.0.0.js"></script>
<style type="text/css">
#r-result > div > div > ol > li a{ display:none !important;}
</style>
<title>定位</title>
</head>
<body>
	<!--<div id="allmap"></div> -->
	<div class="map-max">
		<div class="cont">
			<div id="l-map"></div>
			<div class="search-cont">
				<div id="r-result">
					<input class="search-ipt" type="text" id="suggestId" size="20" value="请输入搜索内容" />
					<input class="search-btn" type="button" value="搜索" onclick="search()">
				</div>
				<div id="searchResultPanel"
					style="border: 1px solid #C0C0C0; width: 150px; height: auto; display: none;"></div>
			</div>
			<div id="r-results"></div>
			<input type="hidden" id="addressApi" name="addressApi" value="">
		</div>
		<div id="button">
			<a id="nextBtn" onclick="submitAddress()" class="bttn" role="确定">确定</a>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	var lng = "${longitude}";
	var lat = "${latitude}";
	var address = "${address}";
	var index = "${index}";
	var pellng = "${pellng}";
	var pellat = "${pellat}";
	var peladdress = "${peladdress}";

	var degree = "${degree}";
	var duration = "${duration}";
	var enterpriseName = "${enterpriseName}";
	var phenomenon = "${phenomenon}";
	var localIds="${localIds}";
	function submitAddress() {
		
		var value = jQuery("#addressApi").val();
		window.location.href = "/ep-weixin/ep/weixin/complaint/toComplaint?lng="
				+ lng
				+ "&lat="
				+ lat
				+ "&pellng="
				+ pellng
				+ "&pellat="
				+ pellat + "&peladdress=" + peladdress+"&address="+value 
				+ "&degree="
				+ degree
				+ "&duration="
				+ duration
				+ "&enterpriseName="
				+ enterpriseName
				+ "&phenomenon=" + phenomenon+"&localIds="+localIds;
	}
	//标记是否使用查询功能0表示未使用
	var flag = 0;

	//百度地图API功能
	function G(id) {
		return document.getElementById(id);
	}
	// 百度地图API功能
	var map = new BMap.Map("l-map");
	//map.centerAndZoom("重庆", 14);
	var point = new BMap.Point(lng, lat);
	map.centerAndZoom(point, 14);
	map.addControl(new BMap.NavigationControl());

	map.addEventListener("tilesloaded", function() {
		// jQuery("#r-results").html("<p>"+address+"</p>");
	});
	if (index == '1') {

		//坐标转换完之后的回调函数
		translateCallback = function(data) {
			if (data.status === 0) {
				var marker = new BMap.Marker(data.points[0]);
				map.addOverlay(marker);
				map.setCenter(data.points[0]);

			}
		}
		setTimeout(function() {
			jQuery("#r-results").html("<p>" + address + "</p>");
			jQuery("#addressApi").val(address);
			var convertor = new BMap.Convertor();
			var pointArr = [];
			pointArr.push(point);
			convertor.translate(pointArr, 1, 5, translateCallback)
		}, 1000);

	} else {
		var marker = new BMap.Marker(point);// 创建标注
		map.addOverlay(marker);
	}
	//关键字检索
	function search() {
		var value = jQuery("#suggestId").val();
		var local = new BMap.LocalSearch(map, {
			renderOptions : {
				map : map,
				panel : "r-results",
				enableMessage : false,
				enableDetail : false,
				autoViewport : true
			},
			pageCapacity : 4
		});
		local.search(value);
		local.setInfoHtmlSetCallback(function(poi,html){
			
			lng =poi.point.lng;
			lat =poi.point.lat;
			jQuery("#addressApi").val(poi.address);
		});
	
		flag = 1;
// 		var a = jQuery("#r-results > div >div>ol>li");
// 		var c = jQuery("#r-results > div > div>ol>li>div>div:nth-child(1)>a");
// 		jQuery("a").hide();
		//循环除去详情
		//  	for(var i=0;i<c.length;i++){
		//  		var d=c[i];
		//  	}
	}
	// 	var myKeys = ["三元"];
	// 	var local = new BMap.LocalSearch(map, {
	// 		renderOptions:{map: map, panel:"r-results"},
	// 		pageCapacity:5
	// 	});
	// 	local.searchInBounds(myKeys, map.getBounds());

	// 添加带有定位的导航控件
	var navigationControl = new BMap.NavigationControl({
		// 靠左上角位置
		anchor : BMAP_ANCHOR_TOP_LEFT,
		// LARGE类型
		type : BMAP_NAVIGATION_CONTROL_LARGE,
		// 启用显示定位
		enableGeolocation : true
	});
	map.addControl(navigationControl);
	// 添加定位控件
	var geolocationControl = new BMap.GeolocationControl();
	map.addControl(geolocationControl);

	//单击获取点击的经纬度
	map.addEventListener("click", function(e) {

		map.clearOverlays();
		lng = e.point.lng;
		lat = e.point.lat;
		var point1 = new BMap.Point(lng, lat);
		//map.centerAndZoom(point1,14);
		var marker = new BMap.Marker(point1);// 创建标注
		map.addOverlay(marker);
		var geoc = new BMap.Geocoder();
		var pt = e.point;
		geoc.getLocation(pt, function(rs) {
			var addComp = rs.addressComponents;
			var str = addComp.province + addComp.city + addComp.district
					+ addComp.street + addComp.streetNumber;
			jQuery("#r-results").html("<p>" + str + "</p>");
			jQuery("#addressApi").val(str);

		});
	});
</script>
