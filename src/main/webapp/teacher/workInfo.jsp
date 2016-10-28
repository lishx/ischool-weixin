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
			<title>作业详情</title>
			<link rel="stylesheet" href="${ctx }/css/sm.min.css" />
			<link rel="stylesheet" href="${ctx }/css/sm-extend.min.css" />
			<link rel="stylesheet" href="${ctx }/css/style.css" />
			<script type="text/javascript" src="${ctx }/js/zepto.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm-extend.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm-city-picker.min.js"></script>
			<style type="text/css">
				.del{float:right;}
			</style>
</head>
<body>
	<div class="page-group">
		<div id="addexam" class="page">
			<header class="bar bar-nav header_bg"> <a
				class="button button-link button-nav pull-left back"> <span
				class="icon icon-left col_w"></span> <!--返回-->
			</a>
			<h1 class="title col_w">作业详情</h1>
			</header>
			<div class="content bg_w">
					<div class="list-block media-list list_defi">
						<ul>
							<li>
									<div class="item-content">
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title col_cc">课程名称</div>
											</div>
											<div id="leassonName" class="item-subtitle col_4d ">${entity.subname }</div>
										</div>
									</div>
							</li>
							<li>
									<div class="item-content">
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title col_cc">作业描述</div>
											</div>
											<div id="leassonName" class="item-subtitle col_4d ">${entity.workinfo }</div>
										</div>
									</div>
							</li>
							<li>
									<div class="item-content">
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title col_cc">完成时限</div>
											</div>
											<div class="item-subtitle col_4d">${entity.limittime }</div>
										</div>
									</div>
							</li>
							<li>
									<div class="item-content">
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title col_cc">创建时间</div>
											</div>
											<div class="item-subtitle col_4d">${entity.createtime }</div>
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
			</div>
		</div>
	</div>
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
		        	//'<a  class="js_delFile" id="d{pid}" fileId="{fileId}" href="javascript:void(0);">删除</a>'+
		        	'<a  class="js_downFile" id="d{pid}" href="'+path+'/index/downloadFile?fileId={fileId}">下载</a>'+
		        '</span>'+
		    '</div>';
		    
		    $.ajax({
		    	url:path+'/index/batchBusinessFileMsg',
		    	data:{
		    		systemCode:"ischool",
		    		businessKeys:'work'+"${entity.homeworkid}"
		    	},
		    	success:function(data){
		    		var $div = $("#flielist");
		    		$div.html("");
		    	//	console.log(data)
		    		var html = "";
		    		for(var i in data){
		    			//console.log(data[i])
		    			html += protpl.replace("{filename}",data[i].fileName)
		    			.replace("{fileId}",data[i].fileId).replace(/{pid}/g,i);
						
		    		}
		    		$div.append(html)
		    	}
		    })
			
		    
			
			
		})
	
	</script>
</body>
</html>
