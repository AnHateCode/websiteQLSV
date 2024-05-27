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
function onClickDeleteGiangVien(GiangVienName, gvId) {
	let c = confirm('Bạn chắc chắn muốn xóa ' + GiangVienName + '?');
	if (c) {
		document.getElementById("deleteBookFromAdmin").value = gvId;
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
        <h1>Tìm khoa qua mã</h1>
        <input class="search-input" name="search" onkeyup="activeAsAjax('${pageContext.request.contextPath}/QLKhoaHome?keyword='+this.value);" style="width: 300px;">
    </div>
    <div align="center" class="content" id="searchResult">
        <h1>Danh sách Khoa và Chuyên ngành</h1>
        <table class="styled-table">
            <thead>
                <tr>
                    <th>Mã Khoa</th>
                    <th>Tên Khoa</th>
                    <th>Danh sách Bộ Môn</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${khoaList}" var="khoa">
                    <tr>
                        <td>${khoa.maKhoa}</td>
                        <td>${khoa.tenKhoa}</td>
                        <td>
                            <button class="button button-detail" onclick="document.getElementById('div${khoa.maKhoa}').style.display='block'">Xem chi tiết</button>
                            <button class="button button-hidden" onclick="document.getElementById('div${khoa.maKhoa}').style.display='none'">Ẩn</button>
                            <button class="button button-hidden" onclick="document.getElementById('div${khoa.maKhoa}').style.display='none'">Ẩn</button>
                            <div id="div${khoa.maKhoa}" style="display: none;">
                                <h3>Các bộ môn của khoa</h3>
                                <table border="1">
                                    <tr style="background: yellow;">
                                        <th>Mã Bộ môn</th>
                                        <th>Tên bộ môn</th>
                                    </tr>
                                    <c:forEach var="chuyenNganh" items="${khoa.chuyenNganhList}">
                                        <tr>
                                            <td>${chuyenNganh.maChuyenNganh}</td>
                                            <td align="center">
                                                <button type="button" onclick="activeAsLink('detailBoMon?maCN=${chuyenNganh.maChuyenNganh}&tenCN=${chuyenNganh.tenChuyenNganh}')">${chuyenNganh.tenChuyenNganh}</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:choose>
            <c:when test="${empty keyword}">
                <div class="pagination">
                    <c:if test="${currentPage gt 1}">
                        <a href="QLKhoaHome?page=${currentPage - 1}">Previous</a>&nbsp;
                    </c:if>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                &nbsp;${i}&nbsp;
                            </c:when>
                            <c:otherwise>
                                &nbsp;<a href="QLKhoaHome?page=${i}">${i}</a>&nbsp;
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage lt noOfPages}">
                        &nbsp;<a href="QLKhoaHome?page=${currentPage + 1}">Next</a>
                    </c:if>
                </div>
            </c:when>
            <c:otherwise>
                <div class="pagination">
                    <c:if test="${currentPage gt 1}">
                        <a href="QLKhoaHome?page=${currentPage - 1}&keyword=${keyword}">Previous</a>&nbsp;
                    </c:if>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                &nbsp;${i}&nbsp;
                            </c:when>
                            <c:otherwise>
                                &nbsp;<a href="QLKhoaHome?page=${i}&keyword=${keyword}">${i}</a>&nbsp;
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage lt noOfPages}">
                        &nbsp;<a href="QLKhoaHome?page=${currentPage + 1}&keyword=${keyword}">Next</a>
                    </c:if>
                </div>
            </c:otherwise>
        </c:choose>
        <br>
        <br/><a href="createKhoa" class="more-departments">Thêm khoa</a><br>
    </div>
    <div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
</div>
</body>
</html>