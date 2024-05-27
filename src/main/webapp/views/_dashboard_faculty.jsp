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
           <li> <a href="${pageContext.request.contextPath}/clientHome">Trang chủ</a></li>
            <li><a href="${pageContext.request.contextPath}/QLKhoaHome">Quản lý bộ môn</a></li>
      		 <li><a href="${pageContext.request.contextPath}/QLLop">Quản lý Lớp của khoa</a></li>
      		<li><a href="${pageContext.request.contextPath}/adminHome">Quản lý sinh viên</a></li>
      		<li>  <a href="${pageContext.request.contextPath}/QLGiangVienHome">Giang viên</a></li>
      		  <li><a href="${pageContext.request.contextPath}/QLNewsHome">Quản lý bài viết</a></li>
      		    <li><a href="${pageContext.request.contextPath}/thongKeTrangThaiSinhVien">Thông kê sinh viên nghỉ học</a></li>
              <li><a href="${pageContext.request.contextPath}/thongKeKhoa">Thống kê từng lớp của khoa</a></li>
        </ul>
    </div>