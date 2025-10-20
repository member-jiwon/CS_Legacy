<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="<c:url value='/resources/js/findpw/findpw.js'/>"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/findpw/findpw.css'/>">
</head>
<body>
<div class="container" 
     style="
        width: 100vw;
        height: 100vh;
        background-image: url('<c:url value="/back.png"/>');
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        display: flex;
        justify-content: center;
        align-items: center;
     ">
    <div class="pwfindbox">

        <div class="pfin">

            <div class="high">
                <h1>비밀번호 찾기</h1>
                <span>비밀번호를 잊어버리셨나요?</span>
            </div>
			<form action="/admin/gnewpw" method="post">
            <div class="findemail">
                <label for="email">이메일</label>
                <div class="inputButtonemail">
                    <input id="admin_email" type="email" placeholder="이메일" name="admin_email"/>
                    <button class="emailrequest" id="emailCheck" type="button">인증 요청</button>
                </div>
            </div>

            <div class="requestcheck">
                <label for="emailcheck">인증확인</label>
                <input id="emailAuth" type="text" placeholder="인증확인" name="emailcheck"/>
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