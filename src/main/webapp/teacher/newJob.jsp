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
			<title>新增作业布置</title>
			<link rel="stylesheet" href="${ctx }/css/sm.min.css" />
			<link rel="stylesheet" href="${ctx }/css/sm-extend.min.css" />
			<link rel="stylesheet" href="${ctx }/css/style.css" />
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
.progress-bar{text-align: center;width: 100%;}
</style>
			<script type="text/javascript" src="${ctx }/js/zepto.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm.min.js"></script>
			<script type="text/javascript" src="${ctx }/js/sm-extend.min.js"></script>
			<script type="text/javascript">
				var ctx = "${ctx}";
				var classId = '${classId}';
				var path ='${fileServer}'; 
			</script>
			<script type="text/javascript" src="${ctx }/js/teaching.js"></script>
</head>
<body>
	<div class="page-group">
		<div class="page" id="addHomeWork">
			<header class="bar bar-nav header_bg"> <a
				class="button button-link button-nav pull-left back"> <span
				class="icon icon-left col_w"></span> <!--返回-->
			</a>
			<h1 class="title col_w">新增作业布置</h1>
			</header>
			<div class="content bg_w">
				<div class="list-block media-list list_defi">
					<form id="addformwork" method="post" action="${ctx }/work/doadd">
						<input type="hidden" value="${maxId }" name="homeworkid" />
						<input type="hidden" value="${classId }" name="classId" /> <input
							id="formworkinfo" type="hidden" value="" name="workinfo" /> <input
							id="msId" type="hidden" name="msid" value="${lesson.tsid }" />
						<ul>
							<li><a href="">
									<div class="item-content">
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title col_cc">课程名称</div>
											</div>
											<div id="leassonName" class="item-subtitle col_4d ">${lesson.subname }</div>
										</div>
									</div>
							</a></li>
							<li><a href="">
									<div class="item-content">
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title col_cc">作业完成时间</div>
												<!--<span class="pull-right icon icon-menu"></span>-->
											</div>
											<div class="item-subtitle col_4d">
												<input type="text" name="limittimeVo" value=""
													id="limittime" />
											</div>
										</div>
									</div>
							</a></li>
							<li><a href="">
									<div class="item-content">
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title col_cc">作业要求</div>
											</div>
											<div id="divworkinfo" contenteditable="true"
												class="col_4d edt mgt_02"></div>
										</div>
									</div>
							</a></li>
							<li><a href="">
									<div class="item-content">
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title col_cc">附件上传</div>
											</div>
										</div>
									</div>
							</a></li>
							<!-- 1 -->
						</ul>
					</form>
					<div class="list-block media-list list_defi dis_no">
						<form id="fileform" enctype="multipart/form-data" method="post" >
							<input id="lefile" type="file" style="display:none" /> 
						</form>
						<ul>
							<li><a href="">
									<div class="item-content">
										<div class="item-media">
											<a class="g-button" href="javascript:void(0);"> <i
												class="iconfont">&#xe61a;</i>
											</a>
										</div>
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title col_00 fs_875" onclick="$('#lefile').click();">上传文件</div>
											</div>
										</div>
									</div>
							</a>
								<div id="progress" class="progress hide">
								    <!-- <div class="progress-bar item-inner" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" >
								        <span id="show-filename">xxxx.jpg</span>
								        <span class="sr-only">0%</span>
								        <span class="del">
								        	<a href="#">删除</a>
								        </span>
								    </div> -->
								</div>
							</li>
							
						</ul>
					</div>
					<!--  -->
				</div>
				<p class="defi_c">
					<a href="javascript:;" id="submit" class="button button-fill mgt_2">提交</a>
				</p>

			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctx }/js/ajaxfile.js"></script>
	<script type="text/javascript">
		//http://121.40.127.79:8082/upsoft-files/downloadFile?fileId=xx 下载 
		//http://121.40.127.79:8082/upsoft-files/deleteFile?fileId=xx 删除
		//http://121.40.127.79:8082/upsoft-files/uploadFile 上传 
		//http://121.40.127.79:8082/upsoft-files/batchBusinessFileMsg 文件列表 
		$(function(){
			
			/*
			 * 布置作业
			 * */
			 $("#limittime").datetimePicker({});
			 $(document).on("touchend",".leassonItem",function(){
					var $this = $(this);
					var uname = $this.text();
					var uId = $this.attr("data-id");
					$("#leassonName").text(uname);
					$("#msId").val(uId);
					$(".close-popup").click();
					
			});
			
			 $(document).on("touchend","#submit",function(){
				 //examtime
					var workinfo = $("#divworkinfo").text().trim();
					$("#formworkinfo").val(workinfo);
					$("#addformwork").submit();
			});
			var count =0;// role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"
			var protpl ='<div id="pro{pid}" class="progress-bar item-inner" >'+
		        '<span id="show-filename">{filename}</span>'+
		        '<span id="p{pid}" class="sr-only">0%</span>'+
		        '<span class="del">'+
		        	'<a  class="js_delFile" id="d{pid}" fileId="{fileId}" href="javascript:void(0);">删除</a>'+
		        '</span>'+
		    '</div>';
		  
		    
			$("#lefile").on("change",function(){
				var fileObj = document.getElementById("lefile").files[0]; // js 获取文件对象
				var html = "";
				html = protpl.replace("{filename}",fileObj.name);
				html = html.replace("{path}",path);
				html = html.replace(/{pid}/g,count++);
				var $div = $("#progress");
				$div.append(html)
				upladFile({
					fileid: "lefile",
					server: path+'/index/uploadFile?systemCode=ischool&businessKey='+'work'+'${maxId}', 
					/* formdata:{
						businessKey:'homework_'+'${entity.homeworkid}',
						systemCode:'ischool-weixin'
					}, */
					callback:function(data){
						//$("#progress").addClass("hide");
						var dela = $("#d"+(count-1));
						var fid = dela.attr("fileId");
						dela.attr("fileId",fid.replace("{fileId}",data.fileId))
						$.alert("上传成功");
						
					},
					progress:function(evt){
						if (evt.lengthComputable) {
							var $div = $("#pro"+(count-1));
							//$div.attr("aria-valuenow", evt.loaded);
							//$div.attr("aria-valuemax", evt.total);
							var finishrate = Math.round(evt.loaded / evt.total * 100)+ "%";
							// $div.css("width", finishrate);
							$div.find("span.sr-only").text(finishrate);
							
						}
					}
				});
			});
			
			$("#progress").on("click",".js_delFile",function(){
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
			})
			
		})
	$.init();
	</script>
</body>
</html>
