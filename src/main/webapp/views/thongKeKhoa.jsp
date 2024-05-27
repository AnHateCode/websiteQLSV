<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/hl4.css">
<title>Trang chủ phía admin</title>

<script type="text/javascript">
function onClickDeleteStudent(studentName, svId) {
	let c = confirm('Bạn chắc chắn muốn xóa ' + studentName + '?');
	if (c) {
		document.getElementById("deleteBookFromAdmin").value = svId;
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
    
    <div class="content">
        <h2>Thống kê khoa CNTT</h2>

        <table border="1" class="styled-table">
            <thead>
                <tr>
                    <th>Mã lớp</th>
                    <th>Tên lớp</th>
                    <th>Giảng viên chủ nhiệm</th>
                    <th>Lớp trưởng</th>
                    <th>Lớp phó</th>
                    <th>Bí thư</th>
                    <th>Sĩ số</th>
                    <th>Số lượng nam</th>
                    <th>Số lượng nữ</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="classInfo" items="${classInfoList}">
                    <tr>
                        <td>${classInfo.maLop}</td>
                        <td>${classInfo.tenLop}</td>
                        <td>${classInfo.giangVienChuNhiem}</td>
                        <td>${classInfo.lopTruong}</td>
                        <td>${classInfo.lopPho}</td>
                        <td>${classInfo.biThu}</td>
                        <td>${classInfo.siSo}</td>
                        <td>${classInfo.soLuongNam}</td>
                        <td>${classInfo.soLuongNu}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <div class="content">
        <jsp:include page="chatRoomView.jsp"></jsp:include>
    </div>
</div>
</body>
</html>