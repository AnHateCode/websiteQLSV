<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/qlsv_style.css">
<script
	src="https://cdn.ckeditor.com/ckeditor5/41.1.0/classic/ckeditor.js"></script>
<title>Trang chủ quản trị</title>

<script type="text/javascript">
function loadImage(event) {
	let output = document.getElementById('bookImage');
	output.src = URL.createObjectURL(event.target.files[0]);
	output.onload = function() {
		URL.revokeObjectURL(output.src)
	}
}
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
		<h3>Thêm học sinh mới</h3>
		<p style="color: red">${errors}</p>
		<form action="createStudent" method="post" enctype="multipart/form-data">
			<table border="0">
				<tr>
					<td>Mã sinh viên</td>
					<td><input type="text" name="maSV" value="${student.maSV}"></td>
				</tr>
				<tr>
					<td>Họ Tên</td>
					<td><input type="text" name="HoTen" value="${student.getHoTen()}"></td>
				</tr>
				<tr>
					<td>Ngày sinh dạng năm-tháng-ngày</td>
					<td><input type="text" name="NgaySinh" value="${student.getNgaySinh()}">&nbsp;&nbsp;
						</td>
				</tr>
				<tr>
					<td>Giới tính</td>
					<td><input type="text" name="GioiTinh" value="${student.getGioiTinh()}">&nbsp;&nbsp;
						</td>
				</tr>
				<tr>
					<td>Địa chỉ</td>
					<td><input type="text" name="DiaChi"
						value="${student.getDiaChi()}"></td>
				</tr>
				<tr>
					<td>email</td>
					<td><input type="text" name="email"
						value="${student.getEmail()}"></td>
				</tr>
				<tr>
					<td>Số điện thoại</td>
					<td><input type="text" name="SoDienThoai"
						value="${student.getSoDienThoai()}"></td>
				</tr>
			<tr>
    <td>Mã Khoa</td>
    <td><input type="text" name="ma_khoa" value="${maKhoa}" readonly></td>
</tr>
<tr>
    <td>Mã chuyên ngành</td>
    <td><input type="text" name="ma_chuyen_nganh" value="${maCN}" readonly></td>
</tr>
<tr>
    <td>Mã lớp</td>
    <td><input type="text" name="ma_lop" value="${maLH}" readonly></td>
</tr>
				

				<tr>
					<td>Ảnh sinh viên</td>
					<td><img id="bookImage" width="150"> &nbsp; <input
						type="file" name="file" accept="image/*"
						onchange="loadImage(event)" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Thêm mới">&nbsp;&nbsp;
						<a href="adminHome">Bỏ qua</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
	</div>
</body>
</html>