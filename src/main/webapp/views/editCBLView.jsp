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
        <h3>Chọn thời gian làm gvcn</h3>
        <p style="color: red">${errors }</p>
            <form action="editCBL" method="POST" enctype="multipart/form-data">
                <table>
                    <tr>
                    <td>Mã sinh viên</td>
                    <td><input type="text" readonly value="${maSV}" name="maSV" value="${maSV}"></td>
                </tr>
                <tr>
                    <td>Mã Lop</td>
                    <td><input type="text" name="maLop" value="${lop.getMaLop()}"></td>
                </tr>
                  <tr>
            <td>Ngày bắt đầu : </td>
            <td><input type="date" name="NgayBatDau" id="NgayBatDau" value="${ngayBatDau}" onchange="updateNgayBatDau()">&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td>Ngày kết thúc: </td>
            <td><input type="date" name="NgayKetThuc" id="NgayKetThuc" value="${ngayKetThuc}" onchange="updateNgayKetThuc()"></td>
        </tr>
        <tr style="display:none;">
            <td><input type="hidden" name="ngayBatDauValue" id="ngayBatDauValue"  value="${ngayBatDau}"></td>
            <td><input type="hidden" name="ngayKetThucValue" id="ngayKetThucValue" value="${ngayKetThuc}"></td>
        </tr>
                <tr>
                    <td>mã niên khóa</td>
                    <td><input type="text" name="maNienKhoa"
                        value="${lop.nienKhoa.getMaNienKhoa()}"></td>
            </tr>
                      <tr>
    <td>Vai trò</td>
    <td>
        <select name="role">
            <option value="lớp trưởng">Lớp trưởng</option>
            <option value="lớp phó">Lớp phó</option>
            <option value="bí thư">Bí thư</option>
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
