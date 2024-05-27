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
		<h3>Danh sách các giang viên</h3>
		<p style="color: red">${errors }</p>
		<form action="deleteGiangVien" id="deleteBookFromAdminForm" method="post">
			<input type="hidden" name="maGV" id="deleteBookFromAdmin" />
		</form>
		<div style="margin-bottom: 10px;">
			<form method="post" action="QLGiangVienHome">
			<input value="${keyword }" name="keyword" hidden/>
			<input value="${currentPage }" name="page" hidden/>

	
			</form>
		</div>
		<table border="1">
			<tr>
				<th>Mã Giang Viên</th>
				<th>Họ tên</th>
				<th>Ngày sinh</th>
				<th>Địa chỉ</th>
				<th>Email</th>
				<th>Số điện thoại</th>
				<th>Mã Khoa</th>
				<th colspan="3" width="150px">Thao tác</th>
			</tr>
			<c:forEach items="${gvList}" var="gv">
				<tr>
					<td>${gv.maGV}</td>
					<td>${gv.hoTen}</td>
					<td>${gv.ngaySinh}</td>
					<td>${gv.diaChi}</td>
					<td>${gv.email}</td>
					<td>${gv.soDienThoai}</td>
					<td>${gv.getMaKhoa1()}</td>
					
					<td align="center"><button type="button"
							onclick="activeAsLink('editGV?maGV=${gv.maGV}')">Sửa</button></td>
					<td align="center"><button type="button"
							onclick="onClickDeleteGiangVien('${gv.hoTen}', '${gv.maGV}')">Xóa</button></td>
					<td align="center"><button type="button"
							onclick="activeAsLink('detailGiangVien?maGV=${gv.maGV}')">Xem
							chi tiết</button></td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${empty keyword }">
			<div style="margin-top: 5px">
				<!-- link previous chỉ xuất hiện khi trang hiện tại lớn hơn 1 -->
				<c:if test="${currentPage gt 1 }">
					<a href="QLGiangVienHome?page=${currentPage - 1} ">Previous</a> &nbsp;
				</c:if>
				<c:forEach begin="1" end="${noOfPages }" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}"> <!-- Trùng lặp trang hiện tại thì không tạo link -->
							&nbsp;${i}&nbsp;
						</c:when>
						<c:otherwise>
							&nbsp;<a href="QLGiangVienHome?page=${i}">${i}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<!-- Link Next chỉ xuất hiện khi trang hiện tại nhỏ hơn tổng số trang -->
				<c:if test="${currentPage lt noOfPages }">
					&nbsp;<a href="QLGiangVienHome?page=${currentPage + 1}">Next</a>
				</c:if>
			</div>
		</c:if>

		<!-- có hoạt động tìm kiếm, thêm tham số keyword -->
	<!-- có hoạt động tìm kiếm, thêm tham số keyword -->
		<c:if test="${not empty keyword }">
			<div style="margin-top: 5px">
				<c:if test="${currentPage gt 1}">
					<a href="#" onclick="activeAsAjax('QLGiangVienHome?page=${currentPage - 1}&keyword=${keyword}'); return false;">Previous</a>&nbsp;
				</c:if>
				<c:forEach begin="1" end="${noOfPages }" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							&nbsp;${i}&nbsp;
						</c:when>
						<c:otherwise>
							&nbsp;<a  href="#" onclick="activeAsAjax('QLGiangVienHome?page=${i}&keyword=${keyword}'); return false;">${i}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${currentPage lt noOfPages }">
					&nbsp;<a
						href="#" onclick="activeAsAjax('QLGiangVienHome?page=${currentPage + 1}&keyword=${keyword}'); return false;">Next</a>
				</c:if>
			</div>
		</c:if>
		<br>
		<br/><a href="createGiangVien">Thêm giảng viên</a><br>
	</div>
    
</body>
</html>
