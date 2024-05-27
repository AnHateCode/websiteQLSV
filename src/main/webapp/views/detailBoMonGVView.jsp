<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/qlsv_style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<title>Trang chủ phía admin</title>

<script type="text/javascript">
function onClickDeleteGVCN(tenlop, lhId, maLop) {
    let c = confirm('Bạn chắc chắn muốn xóa GVCN: ' + tenlop + '?');
    if (c) {
        document.getElementById("deleteBookFromAdmin").value = lhId;
        document.getElementById("deleteBookFromAdmin1").value = maLop;
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
        <div align="center">
            <h3>Danh sách các giảng viên bộ môn:</h3>${maCN}
            <p style="color: red;">${errors1}</p>
            <form action="${pageContext.request.contextPath }/order" method="post" enctype="multipart/form-data">
                <input value="${cartOfCustomer.totalCost }" name="amount" hidden>
                <table border="1">
                    <tr>
                        <th>Mã Giang Viên</th>
                        <th>Họ tên</th>
                        <th>Ngày sinh</th>
                        <th>Địa chỉ</th>
                        <th>Email</th>
                        <th>Số điện thoại</th>
                        <th>Mã Khoa</th>
                        <th>Ghi chú</th>
                        <th colspan="4" width="150px">Thao tác</th>
                    </tr>
                    <c:forEach items="${gvList}" var="gv1">
                        <tr>
                            <td>${gv1.maGV}</td>
                            <td>${gv1.hoTen}</td>
                            <td>${gv1.ngaySinh}</td>
                            <td>${gv1.diaChi}</td>
                            <td>${gv1.email}</td>
                            <td>${gv1.soDienThoai}</td>
                            <td>${gv1.getMaKhoa1()}</td>
                            <c:choose>
                                <c:when test="${gv1.trangThai eq 1}">
                                    <td>Chuyển công tác</td>
                                </c:when>
                                <c:when test="${gv1.trangThai eq 2}">
                                    <td>Học cao học</td>
                                </c:when>
                                <c:when test="${gv1.trangThai eq 3}">
                                    <td>Nghỉ việc</td>
                                </c:when>
                                <c:otherwise>
                                    <td></td>
                                </c:otherwise>
                            </c:choose>
                            <td align="center">
                                <c:choose>
                                    <c:when test="${gv1.trangThai eq 1 || gv1.trangThai eq 2 || gv1.trangThai eq 3}">
                                        <button type="button" disabled>phân công nhiệm vụ</button>
                                    </c:when>
                                    <c:otherwise>
                                      
                                        <button type="button" onclick="activeAsLink('detailBoMonGVNhiemVu?maGV=${gv1.maGV}')">phân công nhiệm vụ</button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td align="center">
                                <c:choose>
                                    <c:when test="${gv1.trangThai eq 1 || gv1.trangThai eq 2 || gv1.trangThai eq 3}">
                                     
                                           <button type="button" disabled>Xem chi tiết</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="button" onclick="activeAsLink('detailGiangVien?maGV=${gv1.maGV}')">Xem chi tiết</button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td align="center">
                                <c:choose>
                                    <c:when test="${gv1.trangThai eq 1 || gv1.trangThai eq 2 || gv1.trangThai eq 3}">
                                
                                        <button type="button" disabled>chuyển khoa</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="button" onclick="activeAsLink('detailBoMonGVChange?maGV=${gv1.maGV}&maKhoa=${gv1.maKhoa1}')">chuyển khoa</button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${gv1.trangThai eq 1 || gv1.trangThai eq 2 || gv1.trangThai eq 3}">
                                        <button type="button" onclick="activeAsLink('detailBoMonGVLock?maGV=${gv1.maGV}')">
                                            <span class="fas fa-lock locked" style="color: red;"></span>Khóa
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="button" onclick="activeAsLink('detailBoMonGVLock?maGV=${gv1.maGV}')">
                                            <span class="fa fa-lock-open" style="color: blue;"></span>Khóa
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </form>
        </div>
        <div class="content">
            <jsp:include page="chatRoomView.jsp"></jsp:include>
        </div>
    </div>
</body>
</html>
