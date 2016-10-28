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
		<title>绑定信息</title>
		<link rel="stylesheet" href="${ctx }/css/sm.min.css" />
		<link rel="stylesheet" href="${ctx }/css/sm-extend.min.css" />
		<link rel="stylesheet" href="${ctx }/css/style.css" />
		<script type="text/javascript" src="${ctx }/js/zepto.min.js"></script>
		<script type="text/javascript" src="${ctx }/js/sm.min.js" ></script>
		<script type="text/javascript" src="${ctx }/js/sm-extend.min.js"></script>
		<script type="text/javascript" src="${ctx }/js/commons.js"></script>
		<script type="text/javascript" >
		$(function(){
			$(".teacherItem").on("click",function(){
				var $this = $(this);
				var uname = $this.text();
				var uId = $this.attr("data-id");
				$("#username").text(uname);
				$("#userId").val(uId);
				var phone = $this.attr("data-phone");
				$("#phone").text(phone);
				$(".close-popup").click();
				
			});
			
			$("#submit").on("touchend",function(){
				var weichat = $("#weichat").val();
				if(!weichat){
					$.alert("未获取到微信账号");
					return false;
				}
				var userId = $("#userId").val();
				if(!userId){
					$.alert("未选择绑定老师");
					return false;
				}
				$("#formPhone").val($("#phone").text().trim());
				$("#form").submit();
			})
			
		})
		
		</script>
	
	</head>
	<body>
        <div class="page-group">
			<div class="page">
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
			     	<input id="formPhone" name="phone" type="hidden" value="" />
<%-- 			     	<input type="hidden" name="nickname" value="${userinfo.nickname }"> --%>
			     	<div class="list-block media-list list_defi">
					    <ul>
					      <li>
					      	<a href="">
					        <div class="item-content">
					          <div class="item-inner">
					            <div class="item-title-row">
					              <div class="item-title col_cc">教师姓名</div>
					              <span class="pull-right triangle_down mgt_02 js_chooseName"></span>
					            </div>
					            <div id="username" class="item-subtitle col_4d"></div>
					          </div>
					        </div>
					        </a>
					      </li>
					      <li>
					      	<a href="">
					        <div class="item-content">
					          <div class="item-inner">
					            <div class="item-title-row">
					              <div class="item-title col_cc">联系电话</div>
					            </div>
					            <!--<textarea class="col_4d">15955668896</textarea>-->
					            <div id="phone" contenteditable="true" class="col_4d edt"></div> 
					          </div>
					        </div>
					        </a>
					      </li>
					    </ul>
					  </div>
					  <p class="defi_c"><a href="javascript:;" id="submit" class="button button-fill mgt_2">提交</a></p>
					</form>
				</div> 
			
				<!--Popup -->
				<div class="popup popup-about">
				  <div id="teacherList" class=" content-block">
				    <p><a href="#" class="close-popup">关闭</a></p>
				    <c:forEach var="t" items="${queryTeacherList }">
				    	<p class="teacherItem" data-id="${t.userid }" data-phone="${t.phone }">${t.username }</p>
				    </c:forEach>
				  </div>
				</div>
				<!--Popup -->
			</div>
		</div>
	</body>
</html>
