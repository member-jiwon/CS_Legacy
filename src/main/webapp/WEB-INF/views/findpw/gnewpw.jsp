<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="<c:url value='/resources/js/findpw/gnewpw.js'/>"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/findpw/gnewpw.css'/>">
</head>
<body>
	<div class="container"
		style="
        width: 100vw;
        height: 100vh;
        background-image: url('${pageContext.request.contextPath}/back.png');
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        display: flex;
        justify-content: center;
        align-items: center;
    ">
		<div class="newpwcontainer">
			<div class="newpwin">

				<div class="newpwhigh">
					<h1>비밀번호 재설정</h1>
					<span>비밀번호를 변경해주세요</span>
				</div>
				<form action="/admin/updatePw" method="POST">
					<div class="newpw">
						<label for="newpw">새 비밀번호</label> <input id="pw"
							type="password" placeholder="비밀번호" name="pw"/>
						<input type="hidden" id="admin_email" name="admin_email" value="${admin_email}"/>
					</div>

					<div class="pwcheckbuttons">
						<button class="backbt" type="button">취소</button>
						<button class="okbt">완료</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>