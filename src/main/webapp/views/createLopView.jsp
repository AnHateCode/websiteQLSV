<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/hl4.css">
<script
	src="https://cdn.ckeditor.com/ckeditor5/41.1.0/classic/ckeditor.js"></script>
<title>Trang chủ quản trị</title>


</head>
<body>

	  <c:choose>
        <c:when test="${loginedUser.getLoaiNguoiDung() eq 2}">
            <jsp:include page="_dashboard_admin.jsp"></jsp:include>
        </c:when>
        <c:when test="${loginedUser.getLoaiNguoiDung() eq 3}">
            <jsp:include page="_dashboard_lecturer.jsp"></jsp:include>
        </c:when>
        <c:when test="${loginedUser.getLoaiNguoiDung() eq 1}">
            <jsp:include page="_dashboard_student.jsp"></jsp:include>
        </c:when>
        <c:when test="${loginedUser.getLoaiNguoiDung() eq 4}">
            <jsp:include page="_dashboard_faculty.jsp"></jsp:include>
        </c:when>
        <c:otherwise>
            <jsp:include page="_dashboard.jsp"></jsp:include>
        </c:otherwise>
    </c:choose>
	
	<div class="header-content">
	<jsp:include page="_header.jsp"></jsp:include>
	
	<div align="center" class="content">
		<h3>Thêm Lớp mới</h3>
		<p style="color: red">${errors}</p>
		<form action="createLop" method="post" enctype="multipart/form-data" class="lop-form">
    <table class="form-table">
        <tr>
            <td class="form-label">Mã lớp</td>
            <td><input type="text" name="maLop" value="${lh.maLop}" class="form-input"></td>
        </tr>
        <tr>
            <td class="form-label">Mã niên khóa</td>
            <td>
                <select name="maNienKhoa" id="maNienKhoa" class="form-select">
                    <c:forEach items="${listNienKhoa}" var="nk">
                        <option value="${nk.maNienKhoa}">${nk.maNienKhoa}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="form-label">Mã khoa</td>
            <td>
                <select name="maKhoa" id="maKhoa" class="form-select">
                    <c:forEach items="${listKhoa}" var="khoa">
                        <option value="${khoa.maKhoa}">${khoa.tenKhoa}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="form-label">Mã chuyên ngành</td>
            <td>
                <select name="maCN" id="maCN" class="form-select">
                    <c:forEach items="${listChuyenNganh}" var="cn">
                        <option value="${cn.maChuyenNganh}">${cn.tenChuyenNganh}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="form-label">Tên lớp</td>
            <td><input type="text" name="tenLop" value="${lh.getTenLop()}" class="form-input"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Thêm mới" class="form-submit">&nbsp;&nbsp;<a href="adminHome" class="form-cancel">Bỏ qua</a></td>
        </tr>
    </table>
</form>
	</div>
	<div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
	</div>
</body>
</html>