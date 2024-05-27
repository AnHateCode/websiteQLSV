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

<script>

</script>
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
        <h3>Đổi Tên Đăng Nhập/Mật Khẩu</h3>
        <p style="color: red">${errors}</p>
        <form action="editUser" method="POST" enctype="multipart/form-data" class="student-form">
            <table class="form-table">
                <tr>
                    <td class="form-label">Mã đăng nhập</td>
                    <td><input type="text" readonly value="${user.getMaDangNhap()}" name="maDangNhap" class="form-input"></td>
                </tr>
                <tr>
                    <td class="form-label">Tên đang nhập</td>
                    <td><input type="text" name="tenDangNhap" value="${user.getTenDangNhap()}" class="form-input"></td>
                </tr>
                <tr>
                    <td class="form-label">Mật khẩu</td>
                    <td><input type="text" name="matKhau" value="${user.getMatKhau()}" class="form-input"></td>
                </tr>
                <tr>
                    <td class="form-label">Vai trò</td>
                    <td>
                        <select name="loaiNguoiDung" class="form-input-select">
                            <option value="${user.getLoaiNguoiDung()}">${user.getLoaiNguoiDung()}</option>
                            <option value="1">admin</option>
                            <option value="2">khoa</option>
                            <option value="3">sinh viên</option>
                            <option value="4">giảng viên</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Cập nhật" class="form-submit">&nbsp;&nbsp;<a href="QLUserHome" class="form-cancel">Bỏ qua</a></td>
                </tr>
            </table>
        </form>
    </div>
    <div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
</div>

</body>
</html>
