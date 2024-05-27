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
		<h3>Danh sách các lớp học</h3>
		<p style="color: red">${errors }</p>
		<form action="deleteLopHoc" id="deleteBookFromAdminForm" method="post">
			<input type="hidden" name="maLopHoc" id="deleteBookFromAdmin" />
		</form>
		<div style="margin-bottom: 10px;">
			<form method="post" action="QLLopHoc">
			<input value="${keyword }" name="keyword" hidden/>
			<input value="${currentPage }" name="page" hidden/>

	
			</form>
		</div>
		<table border="1">
			<tr>
				<th>Mã lớp học</th>
				<th>Mã lớp</th>
				<th>Mã kỳ học </th>
				<th>Mã môn học</th>
				<th>Mã Giang viên</th>
				<th>Ngày bắt đầu</th>
				<th>Ngày kết thúc</th>
				<th>Ngày trong tuần</th>
				<th>số tiết</th>
				<th>Tiết bắt đầu </th>
				<th>Phòng học</th>
				<th colspan="3" width="150px">Thao tác</th>
			</tr>
			<c:forEach items="${lopHocList}" var="lh">
				<tr>
					<td>${lh.maLopHoc}</td>
					<td>${lh.lop.getMaLop()}</td>
					<td>${lh.kyHoc.getMaKiHoc()}</td>
					<td>${lh.monHoc.getMaMonHoc()}</td>
					<td>${lh.giangVien.getMaGV()}</td>
					<td>${lh.ngayBatDau}</td>
					<td>${lh.ngayKetThuc}</td>
					<td>${lh.ngayTrongTuan}</td>
					<td>${lh.soTiet}</td>
					<td>${lh.tietBatDau}</td>
					<td>${lh.phongHoc}</td>
					<td align="center"><button type="button"
							onclick="activeAsLink('editLopHoc?maLH=${lh.maLopHoc}')">Sửa</button></td>
					<td align="center"><button type="button"
							onclick="onClickDeleteLopHoc('${lh.lop.getMaLop()}', '${lh.maLopHoc}')">Xóa</button></td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${empty keyword }">
			<div style="margin-top: 5px">
				<!-- link previous chỉ xuất hiện khi trang hiện tại lớn hơn 1 -->
				<c:if test="${currentPage gt 1 }">
					<a href="QLLopHoc?page=${currentPage - 1} ">Previous</a> &nbsp;
				</c:if>
				<c:forEach begin="1" end="${noOfPages }" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}"> <!-- Trùng lặp trang hiện tại thì không tạo link -->
							&nbsp;${i}&nbsp;
						</c:when>
						<c:otherwise>
							&nbsp;<a href="QLLopHoc?page=${i}">${i}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<!-- Link Next chỉ xuất hiện khi trang hiện tại nhỏ hơn tổng số trang -->
				<c:if test="${currentPage lt noOfPages }">
					&nbsp;<a href="QLLopHoc?page=${currentPage + 1}">Next</a>
				</c:if>
			</div>
		</c:if>

		<!-- có hoạt động tìm kiếm, thêm tham số keyword -->
	<c:if test="${not empty keyword }">
			<div style="margin-top: 5px">
				<c:if test="${currentPage gt 1}">
					<a href="#" onclick="activeAsAjax('QLLopHoc?page=${currentPage - 1}&keyword=${keyword}'); return false;">Previous</a>&nbsp;
				</c:if>
				<c:forEach begin="1" end="${noOfPages }" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							&nbsp;${i}&nbsp;
						</c:when>
						<c:otherwise>
							&nbsp;<a  href="#" onclick="activeAsAjax('QLLopHoc?page=${i}&keyword=${keyword}'); return false;">${i}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${currentPage lt noOfPages }">
					&nbsp;<a
						href="#" onclick="activeAsAjax('QLLopHoc?page=${currentPage + 1}&keyword=${keyword}'); return false;">Next</a>
				</c:if>
			</div>
		</c:if>
		<br>
		<br/><a href="createLopHoc">Thêm Lịch học</a><br>
	</div>
</body>
</html>
