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
		<title>班级列表</title>
		<link rel="stylesheet" href="${ctx }/css/sm.min.css"/>
		<link rel="stylesheet" href="${ctx }/css/sm-extend.min.css" />
		<link rel="stylesheet" href="${ctx }/css/style.css" />
		<script type="text/javascript" src="${ctx }/js/zepto.min.js" ></script>
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
				    <h1 class="title col_w">班级列表</h1>
				 </header>
			     <div class="content">
			     	 <div class="list-block media-list list_defi">
			     	 	<c:forEach var="bean" items="${list }">
			     	 		<ul>
							      <li>
							      	<%-- <a href="${ctx }/tellmsg?scid=${bean.scid}&suid=${bean.suid}" > --%>
							        <a href="${ctx }/resouce/student/tolist?lmtype=${lmtype }&scid=${bean.scid}" >
							        <div class="item-content">
							          <div class="item-media"><img src="${ctx }/image/class.png"></div>
							          <div class="item-inner">
							            <div class="item-title-row">
							              <div class="item-title col_83">${bean.scname }</div>
							            </div>
							            <div class="item-subtitle"><span class="col_99">学科名称:</span><span class="col_83">${bean.subname }</span></div>
							            <div class="item-subtitle col_99"><span>专业编码:</span><span>${bean.cmcode }</span></div>
							          </div>
							        </div>
							        </a>
							      </li>
							    </ul>
			     	 	</c:forEach>
					 </div>
				 </div>  
			</div>
		</div>
	</body>
</html>
