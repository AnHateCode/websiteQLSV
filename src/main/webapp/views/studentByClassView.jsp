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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<title>Trang chủ phía admin</title>
<script type="text/javascript">
function onClickDeleteStudent(studentName, svId) {
    let c = confirm('Bạn chắc chắn muốn xóa ' + studentName + '?');
    if (c) {
        let form = document.getElementById("deleteBookFromAdminFormClass");
        form.querySelector("#deleteBookFromAdminClass").value = svId;
        form.submit();
    }
}
function onClickDeleteCBL(tenlop, lhId, maLop) {
    let c = confirm('Bạn chắc chắn muốn xóa cán bộ lớp:  ' + tenlop + '?');
    if (c) {
        let form = document.getElementById("deleteBookFromAdminForm");
        form.querySelector("#deleteBookFromAdmin").value = lhId;
        form.querySelector("#deleteBookFromAdmin1").value = maLop;
        form.submit();
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
	
	
	<div align="center" class="content" id="searchResult1">
		<h3>Danh sách cán bộ lớp</h3>
		<p style="color: red">${errors }</p>
		<c:if test="${not empty svList1}">
		<form action="deleteCBL" id="deleteBookFromAdminForm" method="post">
    <input type="hidden" name="maSV" id="deleteBookFromAdmin" />
    <input type="hidden" name="maLH" id="deleteBookFromAdmin1" />
</form>
		<div style="margin-bottom: 10px;">
			<form method="post" action="adminHome">
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
				<th>thời gian bắt đầu làm cán bộ lớp</th>
				<th>thời gian kêt thúc cán bộ lớp</th>
				<th>role</th>
				<th colspan="3" width="150px">Thao tác</th>
			</tr>
			<c:forEach items="${svList1}" var="sv">
				<tr>
					<td>${sv.getMaSV()}</td>
					<td>${sv.hoTen}</td>
					<td>${sv.ngaySinh}</td>
					<td>${sv.gioiTinh}</td>
					<td>${sv.diaChi}</td>
					<td>${sv.email}</td>
					<td>${sv.soDienThoai}</td>
					<td>${sv.ma_khoa}</td>
					 <c:forEach items="${chuNhiemList}" var="chuNhiem">
            <c:if test="${chuNhiem.sinhVien.maSV eq sv.maSV}">
                <td>
    <fmt:formatDate value="${chuNhiem.namBatDau}" pattern="dd-MM-yyyy" />
</td>
<td>
    <fmt:formatDate value="${chuNhiem.namKetThuc}" pattern="dd-MM-yyyy" />
</td>
                <td>${chuNhiem.role}</td>
            </c:if>
        </c:forEach>
					
					   <td align="center">
            <button type="button" onclick="activeAsLink('editCBL?maSV=${sv.maSV}&maLH=${lop.maLop}')">
                Chỉnh sửa thời gian chủ nhiệm
            </button>
        </td>
        <td align="center">
            <button type="button" onclick="onClickDeleteCBL('${sv.hoTen}', '${sv.maSV}', '${lop.maLop}')">
                Xóa
            </button>
        </td>
        <td align="center">
            <button type="button" onclick="activeAsLink('detailStudent?maSV=${sv.maSV}')">
                Xem chi tiết
            </button>
        </td>
				</tr>
			</c:forEach>
		</table>
		<br>
		</c:if>
	</div>

		<hr style="margin: 100px">
	Tìm sinh viên qua mã  &nbsp; <input style="width: 300px" name="search" onkeyup="activeAsAjax('${pageContext.request.contextPath}/adminHome?keyword='+this.value);">											
	<div align="center" class="content" id="searchResult">
		<h3>Danh sách các sinh viên</h3>
		<h4>lớp: ${lop.maLop}</h4>
		<h4>Tên lớp: ${lop.tenLop}</h4>
		<h4>Khoa: ${lop.khoa.maKhoa}</h4>
		<h4>Chuyên ngành : ${lop.chuyenNganh.maChuyenNganh}</h4>
		
		<p style="color: red">${errors2}</p>
		<form action="deleteStudent" id="deleteBookFromAdminFormClass" method="post">
    <input type="hidden" name="maSV" id="deleteBookFromAdminClass" />
</form>
		<div style="margin-bottom: 10px;">
			<form method="post" action="adminHome">
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
				<th>Gi chú</th>
				<th colspan="6" width="200px">Thao tác</th>
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
					<c:choose>
    <c:when test="${sv.trangThai eq 1}">
        <!-- Trường hợp trạng thái là 1 (bỏ học) -->
        <td>Bỏ học</td>
    </c:when>
    <c:when test="${sv.trangThai eq 2}">
        <!-- Trường hợp trạng thái là 2 (bảo lưu) -->
        <td>Bảo lưu</td>
    </c:when>
    <c:when test="${sv.trangThai eq 3}">
        <!-- Trường hợp trạng thái là 3 (bị đuổi học) -->
        <td>Bị đuổi học</td>
    </c:when>
    <c:otherwise>
        <!-- Trường hợp khác (nếu có) -->
        <td></td>
    </c:otherwise>
</c:choose>
				  <td align="center">
                                <c:choose>
                                    <c:when test="${sv.trangThai eq 1 || sv.trangThai eq 2 || sv.trangThai eq 3}">
                                        <button type="button" disabled>Sửa</button>
                                    </c:when>
                                    <c:otherwise>                                      
                                        <button type="button"
							onclick="activeAsLink('editStudent?maSV=${sv.maSV}')">Sửa</button>
                                    </c:otherwise>
                                </c:choose>
                   </td>
                     <td align="center">
                                <c:choose>
                                    <c:when test="${sv.trangThai eq 1 || sv.trangThai eq 2 || sv.trangThai eq 3}">
                                        <button type="button" disabled>Xóa</button>
                                    </c:when>
                                    <c:otherwise>                                      
                                        <button type="button"
							onclick="onClickDeleteStudent('${sv.hoTen}', '${sv.maSV}')">Xóa</button></td>
                                    </c:otherwise>
                                </c:choose>
                   </td>
                     <td align="center">
                                <c:choose>
                                    <c:when test="${sv.trangThai eq 1 || sv.trangThai eq 2 || sv.trangThai eq 3}">
                                        <button type="button" disabled>Xem
							chi tiết</button>
                                    </c:when>
                                    <c:otherwise>                                      
                                        <button type="button"
							onclick="activeAsLink('detailStudent?maSV=${sv.maSV}')">Xem
							chi tiết</button>
                                    </c:otherwise>
                                </c:choose>
                   </td>
                     <td align="center">
                                <c:choose>
                                    <c:when test="${sv.trangThai eq 1 || sv.trangThai eq 2 || sv.trangThai eq 3}">
                                        <button type="button" disabled>chọn làm cán bộ lớp</button>
                                    </c:when>
                                    <c:otherwise>                                      
                                        <button type="button"
					onclick="activeAsLink('createCBL?maSV=${sv.maSV}&maLH=${lop.maLop}')">chọn làm cán bộ lớp</button>
                                    </c:otherwise>
                                </c:choose>
                   </td>
                     <td align="center">
                                <c:choose>
                                    <c:when test="${sv.trangThai eq 1 || sv.trangThai eq 2 || sv.trangThai eq 3}">
                                        <button type="button" disabled>chuyển lớp</button>
                                    </c:when>
                                    <c:otherwise>                                      
                                       <button type="button"
					onclick="activeAsLink('changeClass?maSV=${sv.maSV}&maKhoa=${sv.ma_khoa}&maNienKhoa=${lop.nienKhoa.getMaNienKhoa()}')">chuyển lớp</button>
                                    </c:otherwise>
                                </c:choose>
                   </td>	
                   <td>
                <c:choose>
                    <c:when test="${sv.trangThai eq 1 || sv.trangThai eq 2 || sv.trangThai eq 3}">
                    	<button type="button"
					onclick="activeAsLink('changeStatus?maSV=${sv.maSV}&maLH=${lop.maLop}')"> <span class="fas fa-lock locked" style="color: red;"></span>Khóa</button>
                    </c:when>
                    <c:otherwise>
                        <!-- Trường hợp khác: Icon móc khóa không bị khóa và màu xanh -->
                        <button type="button"
					onclick="activeAsLink('changeStatus?maSV=${sv.maSV}&maLH=${lop.maLop}')"> <span class="fa fa-lock-open" style="color: blue;"></span>Khóa</button>
                    </c:otherwise>
                </c:choose>
                </td>
            </button>
        </td>
					
					
				</tr>
			</c:forEach>
		</table>
		<br>
		<td align="center"><button type="button"
					onclick="activeAsLink('createStudent1?maLH=${lop.maLop}&maKhoa=${lop.khoa.maKhoa}&maCN=${lop.chuyenNganh.maChuyenNganh}')">thêm sinh viên cho lớp này</button></td>
   
   
     <h2>Thêm sinh viên từ file Excel</h2>
        <form action="UploadStudentFileServlet" method="post" enctype="multipart/form-data">
            Chọn file Excel: <input type="file" name="file" accept=".xlsx" required /><br />
            <input type="submit" value="Upload" />
        </form>

	</div>
	<div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
	</div>
</body>
</html>