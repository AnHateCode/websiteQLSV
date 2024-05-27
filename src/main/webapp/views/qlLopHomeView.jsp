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

function filterData() {
    var maChuyenNganh = document.getElementById("maChuyenNganh").value;
    var maKhoa = document.getElementById("maKhoa").value;
    var maNienKhoa = document.getElementById("maNienKhoa").value;

    // Tạo chuỗi query parameters
    var queryParams = "?maChuyenNganh=" + maChuyenNganh + "&maKhoa=" + maKhoa + "&maNienKhoa=" + maNienKhoa;

    // Gọi hàm activeAsAjax với chuỗi query parameters
    activeAsAjax('QLLop' + queryParams);
}


//Gán sự kiện onchange cho các dropdown để tự động gọi hàm filterData
document.getElementById("maChuyenNganh").onchange = filterData;
document.getElementById("maKhoa").onchange = filterData;
document.getElementById("maNienKhoa").onchange = filterData;

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
        <h1>Tìm lớp học qua mã</h1>
        <input class="search-input" name="search" style="width: 300px" onkeyup="activeAsAjax('${pageContext.request.contextPath}/QLLop?keyword='+this.value);">
    </div>
    <div>
        <select class="filter-select" name="maChuyenNganh" id="maChuyenNganh" onchange="filterData()">
            <option value="">Tất cả các bộ môn</option>
            <c:forEach items="${cnList}" var="list">
                <option value="${list.maChuyenNganh}">${list.maChuyenNganh}</option>
            </c:forEach>
        </select>

        <select class="filter-select" name="maKhoa" id="maKhoa" onchange="filterData()">
            <option value="">Tất cả các khoa</option>
            <c:forEach items="${khoaList}" var="list">
                <option value="${list.maKhoa}">${list.maKhoa}</option>
            </c:forEach>
        </select>

        <select class="filter-select" name="maNienKhoa" id="maNienKhoa" onchange="filterData()">
            <option value="">Tất cả niên khóa</option>
            <c:forEach items="${nkList}" var="list">
                <option value="${list.maNienKhoa}">${list.maNienKhoa}</option>
            </c:forEach>
        </select>
    </div>
    <div align="center" class="content" id="searchResult">
        <h3>Danh sách các lớp học</h3>
        <p style="color: red">${errors}</p>
        <form action="deleteLopHoc" id="deleteBookFromAdminForm" method="post">
            <input type="hidden" name="maLopHoc" id="deleteBookFromAdmin" />
        </form>
        <div style="margin-bottom: 10px;">
            <form method="post" action="QLLopHoc">
                <input value="${keyword}" name="keyword" hidden/>
                <input value="${currentPage}" name="page" hidden/>
            </form>
        </div>
        <table class="styled-table">
            <tr>
                <th>Mã lớp</th>
                <th>Tên lớp</th>
                <th>Mã khoa</th>
                <th>Mã chuyên ngành</th>
                <th>Mã niên khóa</th>
                <th colspan="3">Thao tác</th>
            </tr>
            <c:forEach items="${lopList}" var="lh">
                <tr>
                    <td>${lh.maLop}</td>
                    <td>${lh.tenLop}</td>
                    <td>${lh.khoa.getMaKhoa()}</td>
                    <td>${lh.chuyenNganh.getMaChuyenNganh()}</td>
                    <td>${lh.nienKhoa.getMaNienKhoa()}</td>
                    <td><button class="button button-edit" onclick="activeAsLink('viewStudentByClass?maLH=${lh.maLop}')">Xem danh sách sinh viên</button></td>
                    <td><button class="button button-delete" onclick="activeAsLink('QLChuNhiem?maLH=${lh.maLop}')">Xem GVCN</button></td>
                </tr>
            </c:forEach>
        </table>
        <c:choose>
            <c:when test="${empty keyword}">
                <div class="pagination">
                    <c:if test="${currentPage gt 1}">
                        <a href="QLLop?page=${currentPage - 1}">Previous</a>&nbsp;
                    </c:if>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                &nbsp;${i}&nbsp;
                            </c:when>
                            <c:otherwise>
                                &nbsp;<a href="QLLop?page=${i}">${i}</a>&nbsp;
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage lt noOfPages}">
                        &nbsp;<a href="QLLop?page=${currentPage + 1}">Next</a>
                    </c:if>
                </div>
            </c:when>
            <c:otherwise>
                <div class="pagination">
                    <c:if test="${currentPage gt 1}">
                        <a href="QLLop?page=${currentPage - 1}&keyword=${keyword}">Previous</a>&nbsp;
                    </c:if>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                &nbsp;${i}&nbsp;
                            </c:when>
                            <c:otherwise>
                                &nbsp;<a href="QLLop?page=${i}&keyword=${keyword}">${i}</a>&nbsp;
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage lt noOfPages}">
                        &nbsp;<a href="QLLop?page=${currentPage + 1}&keyword=${keyword}">Next</a>
                    </c:if>
                </div>
            </c:otherwise>
        </c:choose>
        <br>
        <br>
        <br/><a href="createLop" class="more-classes">Thêm lớp</a><br>
    </div>
    <div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
</div>
</body>
</html>