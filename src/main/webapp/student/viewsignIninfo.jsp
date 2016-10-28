<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale= 1.0 ,minimum-scale=1.0 , maximum-scale=1.0,user-scalable= no">
        <meta content="telephone=no" name="format-detection" />
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>签到信息</title>
		<link rel="stylesheet" href="${ctx }/css/sm.min.css" />
		<link rel="stylesheet" href="${ctx }/css/sm-extend.min.css" />
		<link rel="stylesheet" href="${ctx }/css/style.css" />
		<script type="text/javascript" src="${ctx }/js/zepto.min.js"></script>
		<script type="text/javascript" src="${ctx }/js/sm.min.js" ></script>
		<script type="text/javascript" src="${ctx }/js/sm-extend.min.js"></script>
	</head>
	<body>
        <div class="page-group">
			<div class="page">
				 <header class="bar bar-nav header_bg">
				    <a class="button button-link button-nav pull-left back" >
				      <span class="icon icon-left col_w"></span>
				      		<!--返回-->
				    </a>
				    <h1 class="title col_w">签到信息</h1>
				 </header>
			     <div class="content bg_w">
			     	  <div class="list-block media-list list_defi">
					    <ul>
					      <li>
					      	<a href="">
					        <div class="item-content">
					          <div class="item-inner">
					            <div class="item-title-row">
					              <div class="item-title col_cc">课程名称</div>
					            </div>
					            <div class="item-subtitle col_4d">${entity.subname }</div>
					          </div>
					        </div>
					        </a>
					      </li>
					      <li>
					      	<a href="">
					        <div class="item-content">
					          <div class="item-inner">
					            <div class="item-title-row">
					              <div class="item-title col_cc">课程编号</div>
					            </div>
					            <div class="item-subtitle col_4d">${entity.subcode }</div>
					          </div>
					        </div>
					        </a>
					      </li>
					     <%--  <li>
					      	<a href="">
					        <div class="item-content">
					          <div class="item-inner">
					            <div class="item-title-row">
					              <div class="item-title col_cc">教师工号</div>
					            </div>
					            <div class="item-subtitle col_4d">${entity.codeinfo }</div>
					          </div>
					        </div>
					        </a>
					      </li> --%>
					      <li>
					      	<a href="">
					        <div class="item-content">
					          <div class="item-inner">
					            <div class="item-title-row">
					              <div class="item-title col_cc">教师姓名</div>
					            </div>
					            <div class="item-subtitle col_4d">${entity.username }</div>
					          </div>
					        </div>
					        </a>
					      </li>
					      <li>
					      	<a href="">
					        <div class="item-content">
					          <div class="item-inner">
					            <div class="item-title-row">
					              <div class="item-title col_cc">签到地点</div>
					            </div>
					            <div class="item-subtitle col_4d">${entity.signaddress }</div>
					          </div>
					        </div>
					        </a>
					      </li>
					      <li>
					      	<a href="">
					        <div class="item-content">
					          <div class="item-inner">
					            <div class="item-title-row">
					              <div class="item-title col_cc">签到上课时间</div>
					            </div>
					            <div class="item-subtitle col_4d">${entity.signintime }</div>
					          </div>
					        </div>
					        </a>
					      </li>
					    </ul>
					  </div>
<%-- 					  <p><a href="${ctx }/sign/tosign" class="button button-fill">签到</a></p> --%>
				</div>  
			</div>
		</div>
	</body>
</html>
