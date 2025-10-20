<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="<c:url value='/resources/js/home.js'/>"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/home.css'/>">
</head>
<body>
	<div class="container"
		style="width: 100vw; height: 100vh; background-image: url('/back.png'); background-size: cover; background-position: center; background-repeat: no-repeat; display: flex; justify-content: center; align-items: center;">

		<div class="loginbox">

			<div class="logininbox">

				<div class="img">
				<!-- 	<img src="/Logo.png" alt="Logo" /> -->
				</div>

				<h1>CS에 오신 걸 환영합니다!</h1>
				<form action="/admin/login" method="post">
				<div class="emailbox">
					<label for="email">이메일</label><br /> 
					<input name="admin_email" id="email" type="email" placeholder="이메일" />
				</div>

				<div class="pwbox">
					<label for="pw">비밀번호</label><br /> 
					<input name="pw" id="pw" type="password" placeholder="비밀번호" />
				</div>

				<button class="loginbutton" type="submit">로그인</button>
				</form>
				<a href="/admin/findpwPage" class="changepw">비밀번호 찾기</a>

			</div>
		</div>
	</div>

</body>
</html>