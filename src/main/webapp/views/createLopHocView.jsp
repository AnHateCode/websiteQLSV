<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/hl4.css">
<script
	src="https://cdn.ckeditor.com/ckeditor5/41.1.0/classic/ckeditor.js"></script>
<title>Trang chủ quản trị</title>


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
        <h3>Thêm Lớp học mới</h3>
        <p style="color: red">${errors}</p>
        <form action="createLopHoc" method="post" enctype="multipart/form-data" class="student-form">
            <table class="form-table">
                <tr>
                    <td class="form-label">Mã lớp học</td>
                    <td><input type="text" name="maLopHoc" value="${lh.maLopHoc}" class="form-input"></td>
                </tr>
                <tr>
                    <td class="form-label">Mã lớp</td>
                    <td>
                        <select name="maLop" id="maLop" class="form-input-select">
                            <c:forEach items="${listLop}" var="lop">
                                <option value="${lop.maLop}">${lop.tenLop}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="form-label">Mã kỳ học</td>
                    <td>
                        <select name="maKiHoc" id="maKiHoc" class="form-input-select">
                            <c:forEach items="${listKyHoc}" var="kyHoc">
                                <option value="${kyHoc.maKiHoc}">${kyHoc.namHoc}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="form-label">Mã môn học</td>
                    <td>
                        <select name="maMonHoc" id="maMonHoc" class="form-input-select">
                            <c:forEach items="${listMonHoc}" var="monHoc">
                                <option value="${monHoc.maMonHoc}">${monHoc.tenMonHoc}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="form-label">Giang viên</td>
                    <td>
                        <select name="maGV" id="maGV" class="form-input-select">
                            <c:forEach items="${listGiangVien}" var="giangVien">
                                <option value="${giangVien.maGV}">${giangVien.hoTen}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="form-label">Ngày bắt đầu</td>
                    <td><input type="text" name="ngayBatDau" value="${gv.getSoDienThoai()}" class="form-input"></td>
                </tr>
                <tr>
                    <td class="form-label">Ngày kết thúc</td>
                    <td><input type="text" name="ngayKetThuc" value="${gv.getmaKhoa()}" class="form-input"></td>
                </tr>
                <tr>
                    <td class="form-label">Ngày trong tuần</td>
                    <td><input type="text" name="ngayTrongTuan" value="${gv.getHocVan()}" class="form-input"></td>
                </tr>
                <tr>
                    <td class="form-label">Số tiết</td>
                    <td><input type="text" name="soTiet" value="${gv.getChuyenMon()}" class="form-input"></td>
                </tr>
                <tr>
                    <td class="form-label">Tiết bắt đầu</td>
                    <td><input type="text" name="tietBatDau" value="${gv.getChuyenMon()}" class="form-input"></td>
                </tr>
                <tr>
                    <td class="form-label">Phòng học</td>
                    <td><input type="text" name="phongHoc" value="${gv.getChuyenMon()}" class="form-input"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Thêm mới" class="form-submit">&nbsp;&nbsp;<a href="adminHome" class="form-cancel">Bỏ qua</a></td>
                </tr>
            </table>
        </form>
    </div>
    <div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
</div>
</body>
</html>