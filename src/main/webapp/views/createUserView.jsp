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
    <jsp:include page="_dashboard_admin.jsp"></jsp:include>
    
    <div class="header-content">
    <jsp:include page="_header.jsp"></jsp:include>
    
    <div align="center" class="content">
        <h3>Thêm Tài Khoản Mới</h3>
        <p style="color: red">${errors}</p>
        <form action="createUser" method="POST" enctype="multipart/form-data" class="user-form">
            <table class="form-table">
                <tr>
                    <td class="form-label">Mã đăng nhập</td>
                    <td><input type="text" value="${user.getMaDangNhap()}" name="maDangNhap" class="form-input"></td>
                </tr>
                <tr>
                    <td class="form-label">Tên đăng nhập</td>
                    <td><input type="text" name="tenDangNhap" value="${user.getTenDangNhap()}" class="form-input"></td>
                </tr>
                <tr>
                    <td class="form-label">Mật khẩu</td>
                    <td><input type="text" name="matKhau" value="${user.getMatKhau()}" class="form-input"></td>
                </tr>
                <tr>
                    <td class="form-label">Vai trò</td>
                    <td>
                        <select name="loaiNguoiDung" class="form-select"> 
                            <option value="1">Quản trị viên</option>
                            <option value="2">Khoa</option>
                            <option value="3">Sinh viên</option>
                            <option value="4">Giảng viên</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Thêm mới" class="form-submit">
                        &nbsp;&nbsp;
                        <a href="QLUserHome" class="form-cancel">Bỏ qua</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
</div>
</body>
</html>
