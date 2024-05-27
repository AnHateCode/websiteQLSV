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
<title>Website QLSV</title>
</head>
<body>
	<jsp:include page="_dashboard_student.jsp"></jsp:include>

	
	<div class="header-content">
	<jsp:include page="_header.jsp"></jsp:include>
	 <div align="center" class="content" id="searchResult">
	Danh sách chương trình đào tạo của mã chuyên ngành: <b>${sv.getMa_chuyen_nganh()}</b>
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
				<th>Mã môn học</th>
				<th>Tên môn học</th>
			</tr>
			<c:forEach items="${listMonHoc}" var="mh">
				<tr>
					<td>${mh.getMaMonHoc()}</td>
					<td>${mh.getTenMonHoc()}</td>
				</tr>
			</c:forEach>
		</table>
		<br>
	</div>
	<div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
	</div>
</body>
</html>