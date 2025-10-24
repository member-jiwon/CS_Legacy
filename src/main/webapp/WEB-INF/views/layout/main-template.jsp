<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<link rel="stylesheet"
	href="<c:url value='/resources/css/common/sideBar/topBar.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/common/sideBar/sideBar.css'/>">

<link rel="stylesheet"
	href="<c:url value='/resources/css/layout/main-template.css'/>">
</head>
<body>
	<form action="/notice/list">
		<header class="header d-flex align-items-center">
			<jsp:include page="/WEB-INF/views/common/sideBar/topBar.jsp" />

			<%-- 💡 수정: 이 햄버거 버튼은 topBar.jsp로 옮겼으므로 제거합니다. --%>
			<%-- 
            <button id="menuToggle" class="btn btn-light d-md-none me-2">
				<img src="<c:url value='/resources/imgs/Menu.svg' />" alt="menu" />
			</button>
            --%>
		</header>

		<div class="container-fluid body-wrapper">
			<div class="row g-0">
				<div class="col-2 px-0">
					<div class="sidebarbox">
						<jsp:include page="/WEB-INF/views/common/sideBar/sideBar.jsp" />
					</div>
				</div>

				<div class="col-10 px-0 content">
					<jsp:include page="/WEB-INF/views/${contentPage}.jsp" />
				</div>
			</div>
		</div>
	</form>

	<%-- 💡 JS 파일은 항상 </body> 닫는 태그 직전에 위치하는 것이 성능에 좋습니다. --%>
	<script src="<c:url value='/resources/js/layout/main-template.js'/>"></script>
</body>
</html>