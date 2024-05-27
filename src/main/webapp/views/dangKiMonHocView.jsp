<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/qlsv_style.css">
<title>Trang chủ phía admin</title>

<script type="text/javascript">
function onClickDeleteLopHoc(tenlop, lhId) {
	let c = confirm('Bạn chắc chắn muốn xóa ' + tenlop + '?');
	if (c) {
		document.getElementById("deleteBookFromAdmin").value = lhId;
		document.getElementById("deleteBookFromAdminForm").submit();
	}
}
function activeAsAjax(link) {
    if(window.XMLHttpRequest){
		request = new XMLHttpRequest();
	} else if(window.ActiveXObject){
		request = new ActiveXObject("Microsoft.XMLHTTP")
	}
	
	try{
		request.onreadystatechange = getInfoForSearch;
		request.open("GET",link,true);
		request.send();
	}catch(e){
		alert("unable to connect to server")
	}
}


// Hàm xử lý dữ liệu nhận được từ máy chủ
function getInfoForSearch() {
    if (request.readyState == 4 ) {
        let val = request.responseText;
        document.getElementById("searchResult").innerHTML = val;
    }
}
</script>
</head>
<body>
	<jsp:include page="_dashboard_student.jsp"></jsp:include>
	
	<div class="header-content">
	<jsp:include page="_header.jsp"></jsp:include>
	Tìm lop hoc qua mã  &nbsp; <input style="width: 300px" name="search" onkeyup="activeAsAjax('${pageContext.request.contextPath}/QLLopHoc?keyword='+this.value);">
	<div align="center" class="content" id="searchResult">
		<h3>Danh sách các lớp học mà bạn có thể đăng kí theo mã chuyên ngành</h3>
		<p style="color: red">${errors }</p>
		<form action="/createDangKiHoc" id="deleteBookFromAdminForm" method="post">
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
				<th>Tên môn học</th>
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
					<td>${lh.monHoc.getTenMonHoc()}</td>
					<td>${lh.giangVien.getMaGV()}</td>
					<td>${lh.ngayBatDau}</td>
					<td>${lh.ngayKetThuc}</td>
					<td>${lh.ngayTrongTuan}</td>
					<td>${lh.soTiet}</td>
					<td>${lh.tietBatDau}</td>
					<td>${lh.phongHoc}</td>
					<td align="center"><button type="button"
							onclick="activeAsLink('createDangKiHoc?maLH=${lh.maLopHoc}')">Đăng kí học</button></td>
			</c:forEach>
		</table>
	<hr style="margin: 100px">
	<div align="center">
		<h3>Các môn học đã đăng kí</h3>
		<p style="color: red;">${errors}</p>
		<form action="${pageContext.request.contextPath }/order" method="post"
			enctype="multipart/form-data">
			<input value="${cartOfCustomer.totalCost }" name="amount" hidden>
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
							onclick="activeAsLink('editLopHoc?maLH=${lh.maLopHoc}')">Xóa đăng kí</button></td>
			</c:forEach>
		</table>
		</form>
	</div>
	</div>
	<div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
	</div>
</body>
</html>