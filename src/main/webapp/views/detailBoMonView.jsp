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
    <h1 class="title">BM</h1>${tenCN}
    <table border="0" width="100%" cellpadding="5">
<tbody>
<tr>
<td><a href="https://fita.vnua.edu.vn/bo-mon/bm-khoa-hoc-may-tinh/gioi-thieu-chung/"><img class="aligncenter size-full wp-image-2281" src="https://fita.vnua.edu.vn/wp-content/uploads/2013/05/1-gioi-thieu-khmt.jpg" alt="1-gioi-thieu-khmt" width="183" height="131"></a></td>
<td><a href="https://fita.vnua.edu.vn/bo-mon/bm-khoa-hoc-may-tinh/cac-mon-giang-day/"><img loading="lazy" class="aligncenter size-full wp-image-2282" src="https://fita.vnua.edu.vn/wp-content/uploads/2013/05/2-mon-day.jpg" alt="2-mon-day" width="183" height="131"></a></td>
<td onclick="activeAsLink('detailBoMonGV?maCN=${maCN}')">
    <img loading="lazy" class="aligncenter size-full wp-image-2283" src="https://fita.vnua.edu.vn/wp-content/uploads/2013/05/4-dscb.jpg" alt="3-csvc" width="183" height="131">
</td>
</tr>
</tbody>
</table>   
    <div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
    </div>
    </div>
</body>
</html>
