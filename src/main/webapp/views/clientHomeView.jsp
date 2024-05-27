<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang tin tức</title>
 <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1">
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
     <title>Layout</title>
     <style type="text/css">
 
    .header{
	padding: 60px;
	text-align: center;
	background: white;
	position: relative;
}
	.header a{
	color: black;
	text-align: center;
	text-decoration: none;
	line-height: 25px;
	padding: 10px;
	border-radius: 4px;
}

.header a:hover{
	background-color: #ddd;
	color: black;
}

.header_right{
	float: right;
	top: 10px;
	right: 20px;
	position: absolute;
}

.header_left{
	float: left;
	cursor: pointer;
	width: 150px;
	height: auto;
}

.h_menu{
	overflow: hidden;
	background-color: #333;
}
.h_menu a{
	float: left;
	display: blcok;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

.h_menu a:hover{
	background-color: #ddd;
	color: black;
}

.h_menu input[type = text]{
	float: right;
	border: none;
	margin-top: 8px;
	margin-right:16px ;
	width: 18%;
}
input[type=text],input[type=password]{
	width: 100%;
	padding: 5px 5px;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px ;
	box-sizing: border-box;
}
.list-group-item {
  display: flex; /* Sử dụng flexbox để căn chỉnh */
  align-items: center; /* Căn các phần tử theo chiều dọc */
  text-decoration: none; /* Loại bỏ gạch chân mặc định của liên kết */
}

.list-group-item i {
  margin-right: 10px; /* Khoảng cách giữa icon và chữ */
}
     </style>
</head>
<body>
 <div class="container" style = "height: auto">
     <header class="row">
     <div class="header" >
	<img onclick="activeAsLink('${pageContext.request.contextPath}/clientHome');" class="header_left" alt="" src="img/logo1.png">
		<h1>Website QLSV khoa </h1>
	
	<div class="header_right" style="float: right; text-align: right;">
		<c:if test="${not empty loginedUser }">
			Xin chào: <b> ${loginedUser.getTenDangNhap()} </b>
			|
			<a href="${pageContext.request.contextPath}/userInfor">Thông
				tin tài khoản</a>
			|
		
			<a href="${pageContext.request.contextPath}/logout"><i class="glyphicon glyphicon-log-in" style="color: #6CACFF"></i> Đăng xuất</a>
		</c:if>
		
		<c:if test="${empty loginedUser }">
			<a href="${pageContext.request.contextPath}/login"><i class="glyphicon glyphicon-user" style="color: #6CACFF"></i> Đăng nhập</a>
		</c:if>
		
			</div>
</div>    
     </header>
     <nav class="navbar navbar-inverse">
         <div class="h_menu" >
            <a href="${pageContext.request.contextPath}/clientHome"><i class="glyphicon glyphicon-home" style="color: #6CACFF"></i>Trang chủ</a>
        <a href="">Gioi thiệu chung</a>
        |
        <a href="">Tuyển dung</a>
       <input type="text" placeholder="Tìm kiếm..." name="search" onkeyup ="activeAsAjax('${pageContext.request.contextPath}/clientHome?keyword='+this.value);">
	</div>    
     </nav>
     <div class = "row">
          <aside class="col-sm-3">
          <div class="leftcolumn">
	<div class="panel panel-default">
  <div class="list-group">
    <a href="#" class="list-group-item">
      <i class="glyphicon glyphicon-send" style="color: #6CACFF;"></i> <!-- Icon -->
      <span>Tin công đoàn</span> <!-- Chữ -->
    </a>
    <a href="#" class="list-group-item">
      <i class="glyphicon glyphicon-tasks" style="color: #6CACFF;"></i> <!-- Icon -->
      <span>Tổ chức cán bộ </span> <!-- Chữ -->
    </a>
    <a href="#" class="list-group-item">
      <i class="glyphicon glyphicon-send" style="color: #6CACFF;"></i> <!-- Icon -->
      <span>Tin khoa học</span> <!-- Chữ -->
    </a>
    <a href="#" class="list-group-item">
      <i class="glyphicon glyphicon-send" style="color: #6CACFF;"></i> <!-- Icon -->
      <span>Tuyển dụng cán bộ</span> <!-- Chữ -->
    </a>
  </div>
</div>
	
	<div class="panel panel-default" align="center">
	 <div class="panel-body">
		<h2>Tin mới</h2>
		<div>
			<c:forEach items="${newsList}" var="news">
				<img class="mySlides" id="bookImage" alt="" src="${news.getAnhBia()}" width="70%">
			
			</c:forEach>
		</div>
		
		<script>
			var myIndex = 0;
			carousel();
			
			function carousel() {
				let x = document.getElementsByClassName("mySlides");
				
				for(let i =0; i< x.length; i++){
					x[i].style.display = "none";
				}
				myIndex++;
				if(myIndex > x.length){myIndex = 1}
				x[myIndex-1].style.display = "block";
				setTimeout(carousel,8000)
			}
		
		</script>
		</div>
	</div>
	
	<div class="panel panel-default">
	<div class="panel-body">
	<h3>Đăng kí nhận mail</h3>
	<input type = "text" placeholder="Nhập email...">
	<button class="btn btn-success" style="margin-top: 15px;padding: 15px;padding-top: 5px;padding-bottom: 5px">Gửi<i class="fa fa-send-o" style="color: #6CACFF"></i></button>
	</div>
	</div>
</div>   
         </aside>
         
         '
         <article class="col-sm-9">
          <div class="block" align="center" id = "searchResult">
           <c:forEach items="${newsList}" var="news">
                        
            <div class = "col-sm-12 poly-prod">
     			<div class = "panel panel-default">
         		<div class = "panel-heading">
             	<h4 class = "panel-title">
                 "${news.tieuDe}"
             	</h4>
         		</div>
         		<div class = "panel-body">
            	<img src="${news.getAnhBia()}" height="200" width="200"/></div>
         		<div class = "panel-footer">
                 ngày tạo: ${news.getNgayTao()} <a class="btn btn-primary" href="detailNews?id=${news.getId()}"> Xem chi tiết</a>
        		 </div>
     			</div>
 			</div>
 
            </c:forEach>
     <p style="color: red;">${errors}</p>
	<div class="block" align="center">
	<div class="block" align="center">
	<c:if test="${empty keyword }">
			<div style="margin-top: 5px">
				<!-- link previous chỉ xuất hiện khi trang hiện tại lớn hơn 1 -->
				<c:if test="${currentPage gt 1 }">
					<a href="QLNewsHome?page=${currentPage - 1} ">Previous</a> &nbsp;
				</c:if>
				<c:forEach begin="1" end="${noOfPages }" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}"> <!-- Trùng lặp trang hiện tại thì không tạo link -->
							&nbsp;${i}&nbsp;
						</c:when>
						<c:otherwise>
							&nbsp;<a href="QLNewsHome?page=${i}">${i}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<!-- Link Next chỉ xuất hiện khi trang hiện tại nhỏ hơn tổng số trang -->
				<c:if test="${currentPage lt noOfPages }">
					&nbsp;<a href="QLNewsHome?page=${currentPage + 1}">Next</a>
				</c:if>
			</div>
		</c:if>

		<!-- có hoạt động tìm kiếm, thêm tham số keyword -->
		<c:if test="${not empty keyword }">
			<div style="margin-top: 5px">
				<c:if test="${currentPage gt 1}">
					<a href="QLNewsHome?page=${currentPage - 1}&keyword=${keyword}">Previous</a>&nbsp;
				</c:if>
				<c:forEach begin="1" end="${noOfPages }" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							&nbsp;${i}&nbsp;
						</c:when>
						<c:otherwise>
							&nbsp;<a href="QLNewsHome?page=${i}&keyword=${keyword}">${i}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${currentPage lt noOfPages }">
					&nbsp;<a
						href="QLNewsHome?page=${currentPage + 1}&keyword=${keyword}">Next</a>
				</c:if>
			</div>
		</c:if>  
	
	</div>
     	
    </div>   
         </article>
     </div>
     <footer class="panel panel-default">
         <div class = "panel-heading text-center">
             <div>
    <p>
Địa chỉ Văn phòng Khoa: P316, Tầng 3 Nhà Hành chính, Học viện Nông nghiệp Việt Nam<br>
Điện thoại: (024) 62617701 – Fax: (024) 38276554<br>
Email: cntt@vnua.edu.vn - Website: https://fita.vnua.edu.vn</p>
</div>
         </div>
     </footer>
 </div>
</body>
</html>