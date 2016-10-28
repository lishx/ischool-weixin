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
<title>${title }</title>
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
	var path ='${fileServer}'; 
</script>
<script type="text/javascript" src="${ctx }/js/teaching.js"></script>
<style type="text/css">
@font-face {
	font-family: 'iconfont';
	src: url('${ctx }/css/font/iconfont.eot'); /* IE9*/
	src: url('${ctx }/css/font/iconfont.eot?#iefix')
		format('embedded-opentype'), /* IE6-IE8 */
			    url('${ctx }/css/font/iconfont.woff') format('woff'),
		/* chrome、firefox */
			    url('${ctx }/css/font/iconfont.ttf') format('truetype'),
		/* chrome、firefox、opera、Safari, Android, iOS 4.2+*/
			    url('${ctx }/css/font/iconfont.svg#iconfont') format('svg');
	/* iOS 4.1- */
}

.iconfont {
	font-family: "iconfont" !important;
	font-size: 36px;
	font-style: normal;
	-webkit-font-smoothing: antialiased;
	-webkit-text-stroke-width: 0.2px;
	-moz-osx-font-smoothing: grayscale;
}

.progress-bar {
	text-align: center;
	width: 100%;
}
</style>

</head>
<body>
	<div class="page-group">
		<div id="addres" class="page">
			<header class="bar bar-nav header_bg">
				<a class="button button-link button-nav pull-left back"> <span
					class="icon icon-left col_w"></span> <!--返回-->
				</a>
				<h1 class="title col_w">${title }</h1>
			</header>
			<div class="content bg_w">
				<form id="addresform" method="post" action="${ctx }/resouce/doadd">
					<input id="lmid" type="hidden" name="lmid" value="${maxId }" /> 
					<input  type="hidden" name="scid" value="${entity.scid }" /> 
					<input
						type="hidden" name="lmtype" value="${entity.lmtype }" />
					<div class="list-block media-list list_defi">
						<ul>
							<li>
								<div class="item-content">
									<div class="item-inner">
										<div class="item-title-row open-about">
											<div class="item-title col_cc">标题</div>
										</div>
										<input id="lmtitle" type="text" value="${entity.lmtitle }" name="lmtitle">
									</div>
								</div>
							</li>
							<li>
								<div class="item-content">
									<div class="item-inner">
										<div class="item-title-row open-about">
											<div class="item-title col_cc">描述</div>
										</div>
										<textarea type="text" name="lminfo" value="">${entity.lminfo }</textarea>
									</div>
								</div>
							</li>
							<li>
									<div class="item-content">
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title col_cc">附件列表</div>
											</div>
											
											<div id="flielist" class="item-subtitle col_4d">
											
											</div>
											
										</div>
									</div>
							</li>
						</ul>
					</div>
				</form>

			</div>
		</div>
	</div>
	<form id="fileform" enctype="multipart/form-data" method="post">
		<input id="lefile" type="file" style="display: none" />
	</form>
	<script type="text/javascript" src="${ctx }/js/ajaxfile.js"></script>
	<script type="text/javascript">
		//http://121.40.127.79:8082/upsoft-files/downloadFile?fileId=xx 下载 
		//http://121.40.127.79:8082/upsoft-files/deleteFile?fileId=xx 删除
		//http://121.40.127.79:8082/upsoft-files/uploadFile 上传 
		//http://121.40.127.79:8082/upsoft-files/batchBusinessFileMsg 文件列表 
		
			var path ='${fileServer}'; 	
			$(function(){
				var count =0;// role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"
				var protpl ='<div id="pro{pid}" class="progress-bar item-inner" >'+
			        '<span id="show-filename">{filename}</span>'+
			        '<span class="del">'+
			        	// '<a  class="js_delFile" id="d{pid}" fileId="{fileId}" href="javascript:void(0);">删除</a>'+
			        	'<a  class="external js_downFile" id="d{pid}" href="'+path+'/index/downloadFile?fileId={fileId}">下载</a>'+
			        '</span>'+
			    '</div>';
			    $.ajax({
			    	url:path+'/index/batchBusinessFileMsg',
			    	data:{
			    		systemCode:"ischool",
			    		businessKeys:'material'+"${entity.lmid}"
			    	},
			    	success:function(data){
			    		var $div = $("#flielist");
			    		$div.html("");
			    	//	console.log(data)
			    		var html = "";
			    		for(var i in data){
			    			//console.log(data[i])
			    			html += protpl.replace("{filename}",data[i].fileName)
			    			.replace(/{fileId}/g,data[i].fileId).replace(/{pid}/g,i);
							
			    		}
			    		$div.append(html)
			    	}
			    })
				
			    
				
			/* 	$("#flielist").on("click",".js_delFile",function(){
					var $this = $(this);
					var fileId = $this.attr("fileId");
					$.ajax({
						url: path+'/index/deleteFile?fileId='+fileId,
					    success:function(res){
					    	if(res && res.status){
					    		$this.parents(".progress-bar").remove();
					    		$.alert("删除成功");
					    	}
					    }
					})
				}) */
				
			})
		
	</script>
</body>
</html>
