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
		<h3>Thêm Lớp học mới</h3>
		<p style="color: red">${errors}</p>
		<form action="editLopHoc" method="post" enctype="multipart/form-data">
			<table border="0">
				<tr>
					<td>Mã lớp học</td>
					<td><input type="text" name="maLopHoc" value="${lh.maLopHoc}"></td>
				</tr>
				<tr>
					<td>mã lớp</td>
					<td>
					<select name="maLop" id="maLop">
           			 <c:forEach items="${listLop}" var="lop">
                	<option value="${lop.maLop}">${lop.tenLop}</option>
            		</c:forEach>
        			</select>
        			</td>
				</tr>
				<tr>
					<td>Mã kỳ học</td>
					<td>
					<select name="maKiHoc" id="maKiHoc">
            		<c:forEach items="${listKyHoc}" var="kyHoc">
                	<option value="${kyHoc.maKiHoc}">${kyHoc.namHoc}</option>
            	</c:forEach>
        			</select>
        			</td>
				</tr>
				<tr>
					<td>Mã môn học</td>
					<td>
					<select name="maMonHoc" id="maMonHoc">
            		<c:forEach items="${listMonHoc}" var="monHoc">
                	<option value="${monHoc.maMonHoc}">${monHoc.tenMonHoc}</option>
            		</c:forEach>
        			</select>
        			</td>
				</tr>
				<tr>
					<td>Giang viên</td>
					<td>
					 <select name="maGV" id="maGV">
            		<c:forEach items="${listGiangVien}" var="giangVien">
                	<option value="${giangVien.maGV}">${giangVien.hoTen}</option>
           			 </c:forEach>
       				 </select>
					</td>
				</tr>
				
				<tr>
					<td>Ngày bắt đầu</td>
					<td><input type="text" name="ngayBatDau"
						value="${lh.getNgayBatDau()}"></td>
				</tr>
				<tr>
					<td>Ngày kết thúc</td>
					<td><input type="text" name="ngayKetThuc"
						value="${lh.getNgayKetThuc()}"></td>
				</tr>
				
				<tr>
					<td>Ngày trong tuần</td>
					<td><input type="text" name="ngayTrongTuan"
						value="${lh.getNgayTrongTuan()}"></td>
				</tr>
				<tr>
					<td>Số tiết</td>
					<td><input type="text" name="soTiet"
						value="${lh.getSoTiet()}"></td>
				</tr>
				
				<tr>
					<td>Tiết bắt đầu</td>
					<td><input type="text" name="tietBatDau"
						value="${lh.getTietBatDau()}"></td>
				</tr>
				<tr>
					<td>Phòng học</td>
					<td><input type="text" name="phongHoc"
						value="${lh.getPhongHoc()}"></td>
				</tr>
				<tr>
				<td></td>
						<td><input type="submit" value="Cập nhật">
							&nbsp;&nbsp; <a href="QLLopHocHome">Bỏ qua</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
	</div>
</body>
</html>