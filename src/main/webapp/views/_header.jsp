<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/hl4.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/qlsv_script.js"></script>
</head>
<body>
<div class="header">
    <div class="login-left">
        <h2>Website QLSV</h2>
    </div>
    <div  class="login-right">
        <c:if test="${not empty loginedUser}">
            Xin chào: <b>${loginedUser.getTenDangNhap()}</b>
            <a href="${pageContext.request.contextPath}/userInfor">Thông tin tài khoản</a>
            <a href="${pageContext.request.contextPath}/logout" class="logout-link">Đăng xuất</a>
        </c:if>

        <c:if test="${empty loginedUser}">
            <a href="${pageContext.request.contextPath}/login" class="login-link">Đăng nhập</a>
        </c:if>
        <br>
        <br>
    
    </div>
</div>
</body>
</html>