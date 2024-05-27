<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/css/hl4.css">
</head>
<body>
	 
            <jsp:include page="_dashboard.jsp"></jsp:include>
     <div class="login-container">
    <div class="login-form">
        <p class="error-message">${errors}</p>
        <form action="login" method="post">
            <div class="form-group">
                <label for="username">Tài khoản</label>
                <input type="text" name="username" id="username" value="${loginForm.username}" class="form-input">
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <input type="password" name="password" id="password" value="${loginForm.password}" class="form-input">
            </div>
            <div class="form-group checkbox-group">
                <input type="checkbox" name="rememberMe" id="rememberMe" value="${loginForm.rememberMe}">
                <label for="rememberMe">Ghi nhớ</label>
            </div>
            <div class="form-actions">
                <input type="submit" value="Đăng nhập" class="form-submit">
                <a href="${pageContext.request.contextPath}/" class="form-cancel">Bỏ qua</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>