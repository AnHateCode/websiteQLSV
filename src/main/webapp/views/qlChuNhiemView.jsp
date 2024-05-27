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
function onClickDeleteGVCN(tenlop, lhId,maLop) {
	let c = confirm('Bạn chắc chắn muốn xóa GVCN:  ' + tenlop + '?');
	if (c) {
		document.getElementById("deleteBookFromAdmin").value = lhId;
		document.getElementById("deleteBookFromAdmin1").value = maLop;
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
	<div align="center" class="content" id="searchResult">
		<h3>Danh sách chủ nhiệm của lớp</h3>
		<p style="color: red">${errors }</p>
		<c:if test="${not empty gvList1}">
		<form action="deleteGVCN" id="deleteBookFromAdminForm" method="post">
			<input type="hidden" name="maGV" id="deleteBookFromAdmin" />
			<input type="hidden" name="maLH" id="deleteBookFromAdmin1" />
		</form>
		<div style="margin-bottom: 10px;">
			<form method="post" action="adminHome">
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
				<th>Thời gian bắt đầu</th>
				<th>Thời gian kết thúc</th>
				<th colspan="3" width="150px">Thao tác</th>
			</tr>
			<c:forEach items="${gvList1}" var="gv">
				<tr>
					<td>${gv.maGV}</td>
					<td>${gv.hoTen}</td>
					<td>${gv.ngaySinh}</td>
					<td>${gv.diaChi}</td>
					<td>${gv.email}</td>
					<td>${gv.soDienThoai}</td>
					<td>${gv.getMaKhoa1()}</td>
						 <c:forEach items="${chuNhiemList}" var="chuNhiem">
            <c:if test="${chuNhiem.giangVien.maGV eq gv.maGV}">
                <td>${chuNhiem.namBatDau}</td>
                <td>${chuNhiem.namKetThuc}</td>
         
            </c:if>
        </c:forEach>
					
					
						   <td align="center">
            <button type="button" onclick="activeAsLink('editGVCN?maGV=${gv.maGV}&maLH=${lop.maLop}')">
                Chỉnh sửa thời gian chủ nhiệm
            </button>
        </td>
					<td align="center"><button type="button"
							onclick="activeAsLink('detailGiangVien?maGV=${gv.maGV}')">Xem
							chi tiết</button></td>
				  <td align="center">
            <button type="button" onclick="onClickDeleteGVCN('${gv.hoTen}', '${gv.maGV}', '${lop.maLop}')">
                Xóa
            </button>
        </td>
		</c:forEach>
		</table>
		<br>
		</c:if>
	</div>

		<hr style="margin: 100px">
	<div align="center" >
		<h3>Danh sách các giảng viên phù hợp với </h3>
		<p style="color: red;">${errors1}</p>
		<form action="${pageContext.request.contextPath }/order" method="post"
			enctype="multipart/form-data">
			<input value="${cartOfCustomer.totalCost }" name="amount" hidden>
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
			<c:forEach items="${gvList}" var="gv1">
				<tr>
					<td>${gv1.maGV}</td>
					<td>${gv1.hoTen}</td>
					<td>${gv1.ngaySinh}</td>
					<td>${gv1.diaChi}</td>
					<td>${gv1.email}</td>
					<td>${gv1.soDienThoai}</td>
					<td>${gv1.getMaKhoa1()}</td>
					
						<td align="center"><button type="button"
							onclick="activeAsLink('createGVCN?maGV=${gv1.maGV}&maLH=${lop.maLop}')">chọn làm giáo viên chủ nhiệm</button></td>
					<td align="center"><button type="button"
							onclick="activeAsLink('detailGiangVien?maGV=${gv1.maGV}')">Xem
							chi tiết</button></td>
				</tr>
			</c:forEach>
		</table>
		</form>
	</div>
	<div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
	</div>
</body>
</html>