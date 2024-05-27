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

<script>
function updateNgayBatDau() {
    var ngayBatDauInput = document.getElementById('NgayBatDau');
    var ngayBatDauValue = ngayBatDauInput.value;
    document.getElementById('ngayBatDauValue').value = ngayBatDauValue;
}

function updateNgayKetThuc() {
    var ngayKetThucInput = document.getElementById('NgayKetThuc');
    var ngayKetThucValue = ngayKetThucInput.value;
    document.getElementById('ngayKetThucValue').value = ngayKetThucValue;
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
    
    <div align="center" class="content">
        <h3>Chọn lớp muốn chuyển trong niên khóa </h3>${maNienKhoa}
        <p style="color: red">${errors2}</p>
            <form action="changeClass" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="maSV" value="${maSV}" />
                <table>
                 
   				<tr>
					<td>Chọn lớp muốn chuyển</td>
					<td>
					 <select name="malop" id="malop">
            		<c:forEach items="${listLop}" var="lop">
                	<option value="${lop.maLop}">${lop.tenLop}</option>
           			 </c:forEach>
       				 </select>
					</td>
				</tr>

<tr>
						<td></td>
						<td><input type="submit" value="Cập nhật">
							&nbsp;&nbsp; <a href="QLGiangVienHome">Bỏ qua</a></td>
					</tr>
                </table>
            </form>
    </div>
    <div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
    </div>
</body>
</html>
