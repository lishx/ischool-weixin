<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	//response.sendRedirect("valid");
	request.getRequestDispatcher("/valid").forward(request,response);

%>
<head>
	<title>index</title>
</head>
<h2>Hello World!</h2>
</body>
</html>
