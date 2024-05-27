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
		<h3>Thêm Giang Viên mới</h3>
		<p style="color: red">${errors}</p>
		<form action="createNews" method="post" enctype="multipart/form-data">
			<table border="0">
				<tr>
					<td>Tiêu đề</td>
					<td><input type="text" name="tieuDe"
						value="${news.getTieuDe()}"></td>
				</tr>
				<tr>
					<td>Nội dung</td>
					<td><textarea name="noiDung" id="editor" cols="10" rows="20">${news.getNoiDung()}</textarea>
						<script>
							ClassicEditor.create(document
									.querySelector('#editor'));
						</script></td>
				</tr>
				<tr>
					<td>loại tin</td>
					 <td>
      		<select class="filter-select " name="loaiTin">
        	<option value="">Chọn loại tin tức</option>
            <option value="Tin công đoàn">Tin công đoàn</option>
            <option value="Tổ chức cán bộ">Tổ chức cán bộ</option>
            <option value="Tin khoa học">Tin khoa học</option>
            <option value="Tuyển dụng cán bộ">Tuyển dụng cán bộ</option>
      		</select>
   					 </td>
				</tr>
				<tr>
					<td>Ảnh tin </td>
					<td><img id="bookImage" width="150"> &nbsp; <input
						type="file" name="file" accept="image/*"
						onchange="loadImage(event)" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Thêm mới">&nbsp;&nbsp;
						<a href="QLNewsHome">Bỏ qua</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
	</div>
</body>
</html>