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
			<title>教务教学</title>
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
				var classId = '${classId}';
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
										<div class="item-title col_66">学科名称：{{=it[bean].subname}}</div>
									</div>
									<div class="item-subtitle col_83">
										<span>标题:</span><span>{{=it[bean].examtitle}}</span>
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
						<a href="${ctx}/work/teacher/workInfo?homeworkid={{=it[bean].homeworkid}}" class="external">
							<div class="item-content">
								<div class="item-media">
									<img src="${ctx }/image/03.png">
								</div>
								<div class="item-inner">
									<div class="item-title-row">
										<div class="item-title col_66">{{=it[bean].subname}}</div>
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
			<script type="text/javascript" src="${ctx }/js/teaching.js"></script>
			<script type="text/javascript">
			 var lessonNo = '${lessonNo}';
			var options = "";
			(function(){
				if(lessonNo){
					var jsonlessons = $.parseJSON(lessonNo);
					for(var i in jsonlessons){
						options +='<option value="'+jsonlessons[i].lessonno+'">'+jsonlessons[i].lessonno+'</option>';
					}
				}
			})()
			
			 $(document).on('click','#checkSign', function () {
				    $.modal({
				      title:  '选择课程编号',
				      text: '<div class="item-content">'+
						          '<div class="item-media"><i class="icon icon-form-gender"></i></div>'+
						          '<div class="item-inner">'+
						            //'<div class="item-title label">课程</div>'+
						            '<div class="item-input">'+
						              '<select id="se_lesson">'+
						              	'<option value="">请选择</option>'
						              		+options+
						              '</select>'+
						           '</div>'+
						         '</div>'+
						        '</div>',
				      buttons: [
				        {
				          text: 'ok',
				          bold: true,
				          onClick:function(a,b){
				        	  var val =$(a[0]).find("#se_lesson").val();
				        	  if(a[0]){
				        		  window.location.href='${ctx }/sign/checkSignIn?classId=${classId}&lessonno='+val;
				        	  }else{
				        		  $.alert("未选择课程编号");
				        	  }
				        	
				          }
				        },
				      ]
				    })
				    });/* */
			</script>
</head>
<body>
	<div class="page-group">
		<div class="page">
			<header class="bar bar-nav header_bg barline_no"> <a
				class="button button-link button-nav pull-left back"> <span
				class="icon icon-left col_w"></span> <!--返回-->
			</a>
			<h1 class="title col_w">教务&教学</h1>
			</header>
			<div class="content">
				<img src="${ctx }/image/01.png" class="img" />
				<div class="row mgt_05 no-gutter">
					<div class="col-33 disply_flex">
<%-- 						<a href="${ctx }/exam/examarrangement?classId=${classId}"> --%>
							<a href="${ctx }/exam/toksap?classId=${classId}">
							<div class="col_inner">
								<img src="${ctx }/image/02.png" />
								<p>考试安排</p>
							</div>
						</a>
					</div>
					<div class="col-33 disply_flex" id="checkSign">
<%-- 						<a href="${ctx }/sign/checkSignIn?classId=${classId}">  --%>
							<div class="col_inner">
								<img src="${ctx }/image/02.png" />
								<p>签到查看</p>
							</div>
<!-- 					</a> -->
					</div>
					<div class="col-33 disply_flex">
<%-- 						<a href="${ctx }/work/addwork?classId=${classId}"> --%>
						<a href="${ctx }/work/toworklist?classId=${classId}">
							<div class="col_inner">
								<img src="${ctx }/image/02.png" />
								<p>布置作业</p>
							</div>
						</a>
					</div>
					<div class="col-33 disply_flex">
						<a href="${ctx }/resouce/teacher/tolist?lmtype=1&scid=${classId}" >
							<div class="col_inner">
								<img src="${ctx }/image/02.png" />
								<p>资料分享</p>
							</div>
						</a>
					</div>
					<div class="col-33 disply_flex">
					<a href="${ctx }/resouce/teacher/tolist?lmtype=2&scid=${classId}" >
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
