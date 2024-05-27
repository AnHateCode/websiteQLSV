<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/hl4.css">

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
        <h3 class="form-heading">Chỉnh sửa thông tin giảng viên</h3>
        <p class="error-message">${errors}</p>
        <c:if test="${not empty gv}">
            <form action="editGV" method="POST" enctype="multipart/form-data" class="giangvien-form">
                <table class="form-table">
                    <tr>
                        <td class="form-label">Mã giảng viên</td>
                        <td><input type="text" name="maGV" value="${gv.maGV}" class="form-input"></td>
                    </tr>
                    <tr>
                        <td class="form-label">Họ Tên</td>
                        <td><input type="text" name="HoTen" value="${gv.getHoTen()}" class="form-input"></td>
                    </tr>
                    <tr>
                        <td class="form-label">Ngày sinh </td>
                        <td><input type="text" name="NgaySinh" value="${gv.getNgaySinh()}" placeholder="Năm/Tháng/Ngày" class="form-input"></td>
                    </tr>
                    <tr>
                        <td class="form-label">Địa chỉ</td>
                        <td><input type="text" name="DiaChi" value="${gv.getDiaChi()}" class="form-input"></td>
                    </tr>
                    <tr>
                        <td class="form-label">email</td>
                        <td><input type="text" name="email" value="${gv.getEmail()}" class="form-input"></td>
                    </tr>
                    <tr>
                        <td class="form-label">Số điện thoại</td>
                        <td><input type="text" name="SoDienThoai" value="${gv.getSoDienThoai()}" class="form-input"></td>
                    </tr>
                    <tr>
                        <td class="form-label">Mã khoa</td>
                        <td><input type="text" name="ma_khoa" value="${gv.getMaKhoa1()}" class="form-input"></td>
                    </tr>
                    <tr>
                        <td class="form-label">Mã chuyên ngành</td>
                        <td><input type="text" name="hoc_van" value="${gv.getHocVan()}" class="form-input"></td>
                    </tr>
                    <tr>
                        <td class="form-label">Mã lớp</td>
                        <td><input type="text" name="chuyen_mon" value="${gv.getChuyenMon()}" class="form-input"></td>
                    </tr>
                    <tr>
                        <td class="form-label">Ảnh giảng viên</td>
                        <td>
                            <img id="bookImage" alt="" src="${gv.avatar}" width="150" class="form-image">
                            <input type="hidden" name="avatar">
                            &nbsp; Đổi ảnh: &nbsp;
                            <input type="file" name="file" accept="image/*" onchange="loadImage(event)" class="form-file">
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="submit" value="Cập nhật" class="form-submit">
                            &nbsp;&nbsp;
                            <a href="QLGiangVienHome" class="form-cancel">Bỏ qua</a>
                        </td>
                    </tr>
                </table>
            </form>
        </c:if>
    </div>
    <div class="content">
        <jsp:include page="chatRoomView.jsp"></jsp:include>
    </div>
</div>
</body>
</html>