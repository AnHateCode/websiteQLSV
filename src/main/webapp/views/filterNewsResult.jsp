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
		<h3>Danh sách các bài đăng</h3>
		<p style="color: red">${errors }</p>
		<form action="deleteNews" id="deleteBookFromAdminForm" method="post">
			<input type="hidden" name="id" id="deleteBookFromAdmin" />
		</form>
		<div style="margin-bottom: 10px;">
			<form method="post" action="QLNewsHomeHoc">
			<input value="${keyword }" name="keyword" hidden/>
			<input value="${currentPage }" name="page" hidden/>

	
			</form>
		</div>
		<table border="1">
			<tr>
				<th>id</th>
				<th>Anh bìa</th>
				<th>tiêu đề </th>
				<th>Ngày tạo</th>
				<th>Nội dung</th>
				<th>loại tin</th>
				<th colspan="3" width="150px">Thao tác</th>
			</tr>
			<c:forEach items="${newsList}" var="lh">
				<tr>
					<td>${lh.id}</td>
					<td><button
							onclick="document.getElementById('divImg${lh.id}').style.display='block'">Xem
							chi tiết</button>
						<button
							onclick="document.getElementById('divImg${lh.id}').style.display='none'">Ẩn</button>
						<div id="divImg${lh.id}"
								style="display: none; padding-top: 5px;">
								<img alt="Transfer Image"
									src="${pageContext.request.contextPath }/${lh.anhBia}"
									width="150" />
							</div>
					</td>
					<td>${lh.tieuDe}</td>
					<td>${lh.ngayTao}</td>
					<td>${lh.noiDung}</td>
					<td>${lh.loaiTin}</td>
					<td align="center"><button type="button"
							onclick="activeAsLink('editNews?id=${lh.id}')">Sửa</button></td>
					<td align="center"><button type="button"
							onclick="onClickDeleteGiangVien('${lh.tieuDe}', '${lh.id}')">Xóa</button></td>
					<td align="center"><button type="button"
							onclick="activeAsLink('detailNews?id=${lh.id}')">Xem
							chi tiết</button></td>
				</tr>
			</c:forEach>
		</table>
		
		<c:if test="${empty keyword }">
			<div style="margin-top: 5px">
    <!-- link previous chỉ xuất hiện khi trang hiện tại lớn hơn 1 -->
    <c:if test="${currentPage gt 1 }">
        <a href="javascript:void(0)" onclick="activeAsAjax('QLNewsHome?page=${currentPage - 1}&keyword=${keyword}&maChuyenNganh=${param.maChuyenNganh}&maKhoa=${param.maKhoa}&maNienKhoa=${param.maNienKhoa}')">Previous</a> &nbsp;
    </c:if>
    <c:forEach begin="1" end="${noOfPages }" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                &nbsp;${i}&nbsp;
            </c:when>
            <c:otherwise>
                &nbsp;<a href="javascript:void(0)" onclick="activeAsAjax('QLNewsHome?page=${i}&keyword=${keyword}&maChuyenNganh=${param.maChuyenNganh}&maKhoa=${param.maKhoa}&maNienKhoa=${param.maNienKhoa}')">${i}</a>&nbsp;
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <!-- Link Next chỉ xuất hiện khi trang hiện tại nhỏ hơn tổng số trang -->
    <c:if test="${currentPage lt noOfPages }">
        &nbsp;<a href="javascript:void(0)" onclick="activeAsAjax('QLNewsHome?page=${currentPage + 1}&keyword=${keyword}&maChuyenNganh=${param.maChuyenNganh}&maKhoa=${param.maKhoa}&maNienKhoa=${param.maNienKhoa}')">Next</a>
    </c:if>
</div>

		</c:if>

		<!-- có hoạt động tìm kiếm, thêm tham số keyword -->
	<!-- có hoạt động tìm kiếm, thêm tham số keyword -->
		<c:if test="${not empty keyword }">
			<div style="margin-top: 5px">
				<c:if test="${currentPage gt 1}">
					<a href="#" onclick="activeAsAjax('QLNewsHome?page=${currentPage - 1}&keyword=${keyword}'); return false;">Previous</a>&nbsp;
				</c:if>
				<c:forEach begin="1" end="${noOfPages }" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							&nbsp;${i}&nbsp;
						</c:when>
						<c:otherwise>
							&nbsp;<a  href="#" onclick="activeAsAjax('QLNewsHome?page=${i}&keyword=${keyword}'); return false;">${i}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${currentPage lt noOfPages }">
					&nbsp;<a
						href="#" onclick="activeAsAjax('QLNewsHome?page=${currentPage + 1}&keyword=${keyword}'); return false;">Next</a>
				</c:if>
			</div>
		</c:if>
		<br>

	</div>
    
</body>
</html>
