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
    var loaiTin = document.getElementById("loaiTin").value;

    // Tạo chuỗi query parameters
    var queryParams = "?loaiTin=" + loaiTin;

    // Gọi hàm activeAsAjax với chuỗi query parameters
    activeAsAjax('QLNewsHome' + queryParams);
}


//Gán sự kiện onchange cho các dropdown để tự động gọi hàm filterData
document.getElementById("loaiTin").onchange = filterData;

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
        <h1>Tìm bài đăng qua mã</h1>
        <input class="search-input" name="search" style="width: 300px" onkeyup="activeAsAjax('${pageContext.request.contextPath}/QLNewsHome?keyword='+this.value);">
    </div>
    
    <div>
        <select class="filter-select " name="loaiTin" id="loaiTin" onchange="filterData()">
            <option value="">-- Chọn loại tin tức --</option>
            <option value="Tin công đoàn">-- Tin công đoàn --</option>
            <option value="Tổ chức cán bộ">-- Tổ chức cán bộ --</option>
            <option value="Tin khoa học">-- Tin khoa học --</option>
            <option value="Tuyển dụng cán bộ">-- Tuyển dụng cán bộ --</option>
        </select>
    </div>
    
    <div align="center" class="content" id="searchResult">
        <h3>Danh sách các bài đăng</h3>
        <p style="color: red">${errors}</p>
        <form action="deleteNews" id="deleteBookFromAdminForm" method="post">
            <input type="hidden" name="id" id="deleteBookFromAdmin" />
        </form>
        
        <div style="margin-bottom: 10px;">
            <form method="post" action="QLNewsHomeHoc">
                <input value="${keyword}" name="keyword" hidden/>
                <input value="${currentPage}" name="page" hidden/>
            </form>
        </div>
        
        <table class="styled-table">
            <tr>
                <th>ID</th>
                <th>Ảnh bìa</th>
                <th>Tiêu đề</th>
                <th>Ngày tạo</th>
                <th>Nội dung</th>
                <th>Loại tin</th>
                <th colspan="3">Thao tác</th>
            </tr>
            <c:forEach items="${newsList}" var="lh">
                <tr>
                    <td>${lh.id}</td>
                    <td>
                        <button onclick="document.getElementById('divImg${lh.id}').style.display='block'">Xem chi tiết</button>
                        <button onclick="document.getElementById('divImg${lh.id}').style.display='none'">Ẩn</button>
                        <div id="divImg${lh.id}" style="display: none; padding-top: 5px;">
                            <img alt="Transfer Image" src="${pageContext.request.contextPath}/${lh.anhBia}" width="150" />
                        </div>
                    </td>
                    <td>${lh.tieuDe}</td>
                    <td>${lh.ngayTao}</td>
                    <td>${lh.noiDung}</td>
                    <td>${lh.loaiTin}</td>
                    <td align="center"><button type="button" onclick="activeAsLink('editNews?id=${lh.id}')">Sửa</button></td>
                    <td align="center"><button type="button" onclick="onClickDeleteGiangVien('${lh.tieuDe}', '${lh.id}')">Xóa</button></td>
                    <td align="center"><button type="button" onclick="activeAsLink('detailNews?id=${lh.id}')">Xem chi tiết</button></td>
                </tr>
            </c:forEach>
        </table>
        
        <c:choose>
            <c:when test="${empty keyword}">
                <div class="pagination">
                    <c:if test="${currentPage gt 1}">
                        <a href="QLNewsHome?page=${currentPage - 1}">Previous</a>&nbsp;
                    </c:if>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                &nbsp;${i}&nbsp;
                            </c:when>
                            <c:otherwise>
                                &nbsp;<a href="QLNewsHome?page=${i}">${i}</a>&nbsp;
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage lt noOfPages}">
                        &nbsp;<a href="QLNewsHome?page=${currentPage + 1}">Next</a>
                    </c:if>
                </div>
            </c:when>
            <c:otherwise>
                <div class="pagination">
                    <c:if test="${currentPage gt 1}">
                        <a href="QLNewsHomeHoc?page=${currentPage - 1}&keyword=${keyword}">Previous</a>&nbsp;
                    </c:if>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                &nbsp;${i}&nbsp;
                            </c:when>
                            <c:otherwise>
                                &nbsp;<a href="QLNewsHome?page=${i}&keyword=${keyword}">${i}</a>&nbsp;
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage lt noOfPages}">
                        &nbsp;<a href="QLNewsHome?page=${currentPage + 1}&keyword=${keyword}">Next</a>
                    </c:if>
                </div>
            </c:otherwise>
        </c:choose>
        
        <br>
        <br/>
        <a href="createNews" class="more-classes">Thêm bài đăng</a><br>
    </div>
    
    <div class="content">
        <jsp:include page="chatRoomView.jsp"></jsp:include>
    </div>
</div>
</body>
</html>