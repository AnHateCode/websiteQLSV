<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
    	<div align="center" class="content" id="searchResult">
		<h3>Danh sách các sinh viên</h3>
		<p style="color: red">${errors }</p>
		<form action="deleteStudent" id="deleteBookFromAdminForm" method="post">
			<input type="hidden" name="maSV" id="deleteBookFromAdmin" />
		</form>
		<div style="margin-bottom: 10px;">
			<form method="post" action="adminHome">
			<input value="${keyword }" name="keyword" hidden/>
			<input value="${currentPage }" name="page" hidden/>

	
			</form>
		</div>
		<table border="1">
			<tr>
				<th>Mã sinh viên</th>
				<th>Họ tên</th>
				<th>Ngày sinh</th>
				<th>Giới tính</th>
				<th>Địa chỉ</th>
				<th>Email</th>
				<th>Số điện thoại</th>
				<th>Mã Khoa</th>
				<th colspan="3" width="150px">Thao tác</th>
			</tr>
			<c:forEach items="${svList}" var="sv">
				<tr>
					<td>${sv.maSV}</td>
					<td>${sv.hoTen}</td>
					<td>${sv.ngaySinh}</td>
					<td>${sv.gioiTinh}</td>
					<td>${sv.diaChi}</td>
					<td>${sv.email}</td>
					<td>${sv.soDienThoai}</td>
					<td>${sv.ma_khoa}</td>
					
					<td align="center"><button type="button"
							onclick="activeAsLink('editStudent?maSV=${sv.maSV}')">Sửa</button></td>
					<td align="center"><button type="button"
							onclick="onClickDeleteStudent('${sv.hoTen}', '${sv.maSV}')">Xóa</button></td>
					<td align="center"><button type="button"
							onclick="activeAsLink('detailStudent?maSV=${sv.maSV}')">Xem
							chi tiết</button></td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${empty keyword }">
			<div style="margin-top: 5px">
				<!-- link previous chỉ xuất hiện khi trang hiện tại lớn hơn 1 -->
				<c:if test="${currentPage gt 1 }">
					<a href="adminHome?page=${currentPage - 1} ">Previous</a> &nbsp;
				</c:if>
				<c:forEach begin="1" end="${noOfPages }" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}"> <!-- Trùng lặp trang hiện tại thì không tạo link -->
							&nbsp;${i}&nbsp;
						</c:when>
						<c:otherwise>
							&nbsp;<a href="adminHome?page=${i}">${i}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<!-- Link Next chỉ xuất hiện khi trang hiện tại nhỏ hơn tổng số trang -->
				<c:if test="${currentPage lt noOfPages }">
					&nbsp;<a href="adminHome?page=${currentPage + 1}">Next</a>
				</c:if>
			</div>
		</c:if>

		<!-- có hoạt động tìm kiếm, thêm tham số keyword -->
		<c:if test="${not empty keyword }">
			<div style="margin-top: 5px">
				<c:if test="${currentPage gt 1}">
					<a href="#" onclick="activeAsAjax('adminHome?page=${currentPage - 1}&keyword=${keyword}'); return false;">Previous</a>&nbsp;
				</c:if>
				<c:forEach begin="1" end="${noOfPages }" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							&nbsp;${i}&nbsp;
						</c:when>
						<c:otherwise>
							&nbsp;<a  href="#" onclick="activeAsAjax('adminHome?page=${i}&keyword=${keyword}'); return false;">${i}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${currentPage lt noOfPages }">
					&nbsp;<a
						href="#" onclick="activeAsAjax('adminHome?page=${currentPage + 1}&keyword=${keyword}'); return false;">Next</a>
				</c:if>
			</div>
		</c:if>
		<br>
		<br/><a href="createStudent">Thêm sinh viên</a><br>
	</div>
    
</body>
</html>
