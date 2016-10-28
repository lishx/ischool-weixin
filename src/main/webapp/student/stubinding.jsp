<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale= 1.0 ,minimum-scale=1.0 , maximum-scale=1.0,user-scalable= no">
        <meta content="telephone=no" name="format-detection" />
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>学生信息绑定信息</title>
		<link rel="stylesheet" href="${ctx }/css/sm.min.css" />
		<link rel="stylesheet" href="${ctx }/css/sm-extend.min.css" />
		<link rel="stylesheet" href="${ctx }/css/style.css" />
		<script type="text/javascript" src="${ctx }/js/zepto.min.js"></script>
		<script type="text/javascript" src="${ctx }/js/sm.min.js" ></script>
		<script type="text/javascript" src="${ctx }/js/sm-extend.min.js"></script>
		<script type="text/javascript" src="${ctx }/js/commons.js"></script>
		<script type="text/javascript" >
		$(function(){
			$(".classItem").on("touchend",function(){
					var $this = $(this);
					var className = $this.text();
					var classId = $this.attr("data-id");
					$("#className").text(className);
					$("#classId").val(classId);
					$(".close-popup").click();
					
			});
			
			$(document).on('click', ".open-student",function () {
				var classId = $("#classId").val();
				if(classId){
					$.ajax({
						url:"${ctx}/guanzu/getStudents",
						data:{"classId":classId},
						success:function(data){
							if(data && data.length){
								var html = "";
								for(var  i  in  data){
									html +='<p class="stuItem" data-id="'+data[i].userid+'" data-phone="'+data[i].phone+'" data-code="'+data[i].codeinfo+'">'+data[i].username+'</p>'
								}
								$("#divcontent").html(html)
								$.popup('.popup-student');
							}else{ 
								$.toast("该班级学生已全部绑定完成");
							}
							
						}
					})
				}
			});	
			
			$(document).on("click",".stuItem",function(){
				var $this = $(this);
				$("#username").text($this.text());
				$("#userId").val($this.attr("data-id"));
				$("#codeinfo").text($this.attr("data-code"));
				$("#phone").text($this.attr("data-phone"));
				$.closeModal()
			})
			
			$("#submit").on("touchend",function(){
				var weichat = $("#weichat").val();
				if(!weichat){
					$.alert("未获取到微信账号");
					return false;
				}
				var userId = $("#userId").val();
				if(!userId){
					$.alert("未选择绑定学生");
					return false;
				}
				$("#form").submit();
			})
			
		})
		</script>
		<script type="text/javascript" src="${ctx }/js/teaching.js"></script>
	</head>
	<body>
        <div class="page-group">
			<div id="stubind" class="page">
				 <header class="bar bar-nav header_bg">
				    <a class="button button-link button-nav pull-left back" >
				      <span class="icon icon-left col_w"></span>
				      		<!--返回-->
				    </a>
				    <h1 class="title col_w">绑定信息</h1>
				 </header>
			     <div class="content bg_w">
			     <form action="${ctx }/guanzu/dobind" id="form" method="post">
					<input id="weichat" name="weichat" type="hidden" value="${OpenID }" />
					<input id="userId" name="userid" type="hidden" value="" />
					<input id="classId" name="classId" type="hidden" value="" />
			     	<input type="hidden" name="nickname" value="${userinfo.nickname }">
			     	<div class="list-block media-list list_defi">
					    <ul>
					    	<li>
					        <div class="item-content">
					          <div class="item-inner">
					          	
						            <div class="item-title-row  js_chooseClass">
						              <div class="item-title col_cc">班级</div>
						              <span class="pull-right triangle_down mgt_02"></span>
						            </div>
					            <div id="className" class="item-subtitle col_4d"></div>
					          </div>
					        </div>
					      </li>
					      <li>
					        <div class="item-content">
					          <div class="item-inner">
						          	<a href="#" class="open-student"> 
							            <div class="item-title-row ">
							              <div class="item-title col_cc">姓名</div>
							              <span class="pull-right triangle_down mgt_02"></span>
							            </div>
						            </a>
					            	<div id="username" class="item-subtitle col_4d"></div>
					          </div>
					        </div>
					      </li>
					      <li>
					        <div class="item-content">
					          <div class="item-inner">
					            <div class="item-title-row">
					              <div class="item-title col_cc">联系电话</div>
					            </div>
					            <div id="phone"  class="col_4d edt"></div> 
					          </div>
					        </div>
					      </li>
					      <li>
					        <div class="item-content">
					          <div class="item-inner">
					            <div class="item-title-row">
					              <div class="item-title col_cc">学号</div>
					            </div>
					            <div id="codeinfo"  class="col_4d edt"></div> 
					          </div>
					        </div>
					      </li>
					    </ul>
					  </div>
					  <p class="defi_c"><a href="javascript:;" id="submit" class="button button-fill mgt_2">提交</a></p>
					</form>
				</div> 
			</div>
		</div>
			<!--Popup class -->
				<div class="popup popup-about">
				  <div id="teacherList" class="content-block">
				    <p><a href="#" class="close-popup">关闭</a></p>
				    <c:forEach var="t" items="${classList }">
				    	<p class="classItem" data-id="${t.classid }">${t.classname }</p>
				    </c:forEach>
				  </div>
				</div>
				<!--Popup -->
				<!--Popup -->
				<div class="popup popup-student" style="display: none;">
				  <div class="content-block">
				    <p ><a href="#" class="close-popup">关闭</a></p>
				    <div id="divcontent">
				    
				    </div>
				  </div>
				</div>
				<!--Popup -->
	</body>
</html>
