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
		<h1>Danh sách Khoa và Chuyên ngành</h1>
    <table border="1">
			<tr>
				<th>Mã Đăng nhập</th>
				<th>Tên đăng nhập</th>
				<th>Mật khẩu</th>
				<th>Loại người dùng</th>
				<th colspan="3" width="150px">Thao tác</th>
			</tr>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td>${user.getMaDangNhap()}</td>
					<td>${user.getTenDangNhap()}</td>
					<td>${user.getMatKhau()}</td>
					<td>${user.getLoaiNguoiDung()}</td>
					<td align="center"><button type="button"
							onclick="activeAsLink('editUser?maUser=${user.getMaDangNhap()}')">Sửa</button></td>
					<td align="center"><button type="button"
							onclick="onClickDeleteGiangVien('${user.getTenDangNhap()}', '${user.getMaDangNhap()}')">Xóa</button></td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${empty keyword }">
			<div style="margin-top: 5px">
				<!-- link previous chỉ xuất hiện khi trang hiện tại lớn hơn 1 -->
				<c:if test="${currentPage gt 1 }">
					<a href="QLUserHome?page=${currentPage - 1} ">Previous</a> &nbsp;
				</c:if>
				<c:forEach begin="1" end="${noOfPages }" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}"> <!-- Trùng lặp trang hiện tại thì không tạo link -->
							&nbsp;${i}&nbsp;
						</c:when>
						<c:otherwise>
							&nbsp;<a href="QLUserHome?page=${i}">${i}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<!-- Link Next chỉ xuất hiện khi trang hiện tại nhỏ hơn tổng số trang -->
				<c:if test="${currentPage lt noOfPages }">
					&nbsp;<a href="QLUserHome?page=${currentPage + 1}">Next</a>
				</c:if>
			</div>
		</c:if>

		<!-- có hoạt động tìm kiếm, thêm tham số keyword -->
		<c:if test="${not empty keyword }">
			<div style="margin-top: 5px">
				<c:if test="${currentPage gt 1}">
					<a href="#" onclick="activeAsAjax('QLUserHome?page=${currentPage - 1}&keyword=${keyword}'); return false;">Previous</a>&nbsp;
				</c:if>
				<c:forEach begin="1" end="${noOfPages }" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							&nbsp;${i}&nbsp;
						</c:when>
						<c:otherwise>
							&nbsp;<a  href="#" onclick="activeAsAjax('QLUserHome?page=${i}&keyword=${keyword}'); return false;">${i}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${currentPage lt noOfPages }">
					&nbsp;<a
						href="#" onclick="activeAsAjax('QLUserHome?page=${currentPage + 1}&keyword=${keyword}'); return false;">Next</a>
				</c:if>
			</div>
		</c:if>
		<br>
		<br/><a href="createGiangVien">Thêm giảng viên</a><br>
	</div>
    
</body>
</html>
