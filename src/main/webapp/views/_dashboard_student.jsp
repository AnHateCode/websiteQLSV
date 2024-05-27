<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div class="container">
    <div class="dashboard">
        <a href="/clientHome" class="logo-link">
            <img src="img/logo.png" alt="Logo">
            <div>vnua.edu.vn</div>
        </a>
        <div class="divider"></div> <!-- Vạch trắng ngăn cách -->
        <ul>
            <li> <a href="${pageContext.request.contextPath}/studentHome">Trang chủ</a></li>
            <li>  <a href="${pageContext.request.contextPath}/viewCTDT">Chương trình đào tạo</a></li>
            <li><a href="${pageContext.request.contextPath}/dangKiMonHoc">Đăng kí môn học</a></li>  
        </ul>
    </div>