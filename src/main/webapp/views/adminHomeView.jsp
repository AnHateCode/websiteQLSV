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
    <div class="h1-input">
	    <h1>Tìm sinh viên qua mã </h1>
	    <input class="search-input" name="search" onkeyup="activeAsAjax('${pageContext.request.contextPath}/adminHome?keyword='+this.value);">
    </div>
    <div align="center" class="content" id="searchResult">
        <h3>Danh sách các sinh viên</h3>
        <p style="color: red">${errors}</p>
        <form action="deleteStudent" id="deleteBookFromAdminForm" method="post">
            <input type="hidden" name="maSV" id="deleteBookFromAdmin" />
        </form>
        <div style="margin-bottom: 10px;">
            <form method="post" action="adminHome">
                <input value="${keyword}" name="keyword" hidden/>
                <input value="${currentPage}" name="page" hidden/>
            </form>
        </div>
        <table class="styled-table">
            <tr>
                <th>Mã sinh viên</th>
                <th>Họ tên</th>
                <th>Ngày sinh</th>
                <th>Giới tính</th>
                <th>Địa chỉ</th>
                <th>Email</th>
                <th>Số điện thoại</th>
                <th>Mã Khoa</th>
                <th colspan="3">Thao tác</th>
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
                    <td><button class="button button-edit" onclick="activeAsLink('editStudent?maSV=${sv.maSV}')">Sửa</button></td>
                    <td><button class="button button-delete" onclick="onClickDeleteStudent('${sv.hoTen}', '${sv.maSV}')">Xóa</button></td>
                    <td><button class="button button-detail" onclick="activeAsLink('detailStudent?maSV=${sv.maSV}')">Xem chi tiết</button></td>
                </tr>
            </c:forEach>
        </table>
        <c:choose>
            <c:when test="${empty keyword}">
                 <div class="pagination">
            <c:if test="${currentPage gt 1}">
                <a href="adminHome?page=${currentPage - 1}" >Previous</a>&nbsp;
            </c:if>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}"> 
                        &nbsp;${i}&nbsp;
                    </c:when>
                    <c:otherwise>
                        &nbsp;<a href="adminHome?page=${i}">${i}</a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt noOfPages}">
                &nbsp;<a href="adminHome?page=${currentPage + 1}" >Next</a>
            </c:if>
        </div>
            </c:when>
            <c:otherwise>
                <div class="pagination">
            <c:if test="${currentPage gt 1}">
                <a href="adminHome?page=${currentPage - 1}&keyword=${keyword}" >Previous</a>&nbsp;
            </c:if>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        &nbsp;${i}&nbsp;
                    </c:when>
                    <c:otherwise>
                        &nbsp;<a href="adminHome?page=${i}&keyword=${keyword}">${i}</a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt noOfPages}">
                &nbsp;<a href="adminHome?page=${currentPage + 1}&keyword=${keyword}" >Next</a>
            </c:if>
        </div>
            </c:otherwise>
        </c:choose>
        <br>
        <br/><a href="createStudent" class="more-students">Thêm sinh viên</a><br>
    </div>
    <div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
</div>
</body>
</html>