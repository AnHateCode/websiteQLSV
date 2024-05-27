<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết cuốn sách</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }
        .container1 {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table td {
            padding: 10px;
            border-bottom: 1px solid #dee2e6;
            border-right: 1px solid #dee2e6; /* Thêm đường kẻ dọc */
        }
        table td:last-child {
            border-right: none; /* Loại bỏ đường kẻ dọc ở cột cuối cùng */
        }
        .book-details {
            display: flex;
            align-items: center;
            margin-top: 20px;
        }
        .book-details img {
            max-width: 200px;
            height: auto;
            margin-right: 20px;
        }
        .book-details h2 {
            color: black;
        }
        .book-details p {
            line-height: 1.6;
        }
        /* Responsive design */
        @media (max-width: 768px) {
            .container {
                width: 90%;
            }
            .book-details {
                flex-direction: column;
            }
            .book-details img {
                margin-right: 0;
                margin-bottom: 20px;
            }
        }
    </style>
    <script>
        function plugvalue(elementId, maxQuantity) {
            let quantity = parseInt(document.getElementById(elementId).value);
            if (quantity + 1 <= maxQuantity) {
                document.getElementById(elementId).value = quantity + 1;
            } else {
                alert('Giá trị không được vượt quá: ' + maxQuantity);
            }
        }

        function minugvalue(elementId) {
            let quantity = parseInt(document.getElementById(elementId).value);
            if (quantity - 1 >= 1) {
                document.getElementById(elementId).value = quantity - 1;
            }
        }
	
    </script>
    

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/qlsv_style.css">
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
        <h3>Xem chi tiết thông tin Giang viên</h3>
        <p style="color: red">${errors }</p>
        <c:if test="${not empty gv}">
            <div class="container1">
                <form id="detailBookForm" action="cartBook/addToCart" method="POST">
                    <input type="hidden" name="bookId" value=${gv.maGV }" />
                    <table>
                        <tr>
                            <td><strong>Tên</strong></td>
                            <td>${gv.getHoTen() }</td>
                        </tr>
                        <tr>
                            <td><strong>Ngày Sinh</strong></td>
                            <td>${gv.getNgaySinh()}</td>
                        </tr>
                        <tr>
                            <td><strong>Địa chỉ</strong></td>
                            <td>${gv.getDiaChi()}</td>
                        </tr>
                         <tr>
                            <td><strong>email</strong></td>
                            <td>${gv.getEmail()}</td>
                        </tr>
                         <tr>
                            <td><strong>Học vấn</strong></td>
                            <td>${gv.getHocVan()}</td>
                        </tr>
                        <tr>
                            <td><strong>Chuyên Môn</strong></td>
                            <td>${gv.getChuyenMon()}</td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div class="book-details">
                                    <img id="bookImage" alt="BookImage" src="${gv.getAvatar()}" width="150">
                                    <div>
                                        <h2>Ma Khoa</h2>
                                        <p id="bookText">${gv.getMaKhoa1()}</p>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr align="center">
                            <td colspan="2"><a href="clientHome">Bỏ qua</a></td>
                        </tr>
                    </table>
                </form>
            </div>
        </c:if>
    </div>
    <div class="content"><jsp:include page="chatRoomView.jsp"></jsp:include></div>
    </div>
</body>
</html>
