<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/qlsv_style.css">

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
		<h1>Thông tin người dùng</h1>
    <c:choose>
        <c:when test="${loginedUser.loaiNguoiDung eq 2}">
            <!-- Hiển thị thông tin cho admin -->
            <h2>Thông tin admin</h2>
            <p>Mã admin: ${userInfo.ma_admin}</p>
            <p>Email: ${userInfo.email}</p>
            <p>Mã khoa: ${userInfo.ma_khoa}</p>
            <!-- Thêm các thông tin khác của admin nếu cần -->
        </c:when>
        <c:when test="${loginedUser.loaiNguoiDung eq 3}">
            <!-- Hiển thị thông tin cho giáo viên -->
            <h2>Thông tin giáo viên</h2>
            <p>Mã giáo viên: ${userInfo.maGV}</p>
            <p>Họ và tên: ${userInfo.hoTen}</p>
            <p>Ngày sinh: ${userInfo.ngaySinh}</p>
            <p>Địa chỉ: ${userInfo.diaChi}</p>
            <p>Email: ${userInfo.email}</p>
            <p>Số điện thoại: ${userInfo.soDienThoai}</p>
            <p>Ảnh đại diện: <img src="${userInfo.avatar}" alt="Avatar" width="100"></p>
            <p>Mã khoa: ${userInfo.getMaKhoa1()}</p>
            <p>Học vấn: ${userInfo.hocVan}</p>
            <p>Chuyên môn: ${userInfo.chuyenMon}</p>
            <td align="center"><button type="button"
							onclick="activeAsLink('editGV?maGV=${userInfo.maGV}')">Sửa</button></td>
            <!-- Thêm các thông tin khác của giáo viên nếu cần -->
        </c:when>
        <c:when test="${loginedUser.loaiNguoiDung eq 1}">
            <!-- Hiển thị thông tin cho sinh viên -->
            <h2>Thông tin sinh viên</h2>
            <p>Mã sinh viên: ${userInfo.getMaSV()}</p>
            <p>Họ và tên: ${userInfo.getHoTen()}</p>
            <p>Ngày sinh: ${userInfo.getNgaySinh()}</p>
            <p>Giới tính: ${userInfo.getGioiTinh()}</p>
            <p>Địa chỉ: ${userInfo.getDiaChi()}</p>
            <p>Email: ${userInfo.getEmail()}</p>
            <p>Số điện thoại: ${userInfo.getSoDienThoai()}</p>
            <p>Ảnh đại diện: <img src="${userInfo.getAvatar()}" alt="Avatar" width="100"></p>
            <p>Mã khoa: ${userInfo.getMa_khoa()}</p>
            <p>Mã chuyên ngành: ${userInfo.getMa_chuyen_nganh()}</p>
            <p>Mã lớp: ${userInfo.getMa_lop()}</p>
                <td align="center"><button type="button"
							onclick="activeAsLink('editStudent?maSV=${userInfo.getMaSV()}')">Sửa</button></td>
            <!-- Thêm các thông tin khác của sinh viên nếu cần -->
        </c:when>
        <c:otherwise>
            <!-- Xử lý trường hợp khác nếu cần -->
            <p>Không có thông tin để hiển thị</p>
        </c:otherwise>
    </c:choose>
	
	</div>
	<div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
	</div>
</body>
</html>