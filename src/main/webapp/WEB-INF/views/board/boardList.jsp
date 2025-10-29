<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 부트스트랩 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<!-- 보드리스트 css -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/board/boardList.css'/>">
</head>
<body>
	<header class="header d-flex align-items-center">
		<jsp:include page="/WEB-INF/views/common/sideBar/topBar.jsp" />
	</header>

	<div class="container-fluid body-wrapper">
		<div class="row g-0">
			<div class="col-2 px-0">
				<div class="sidebarbox">
					<jsp:include page="/WEB-INF/views/common/sideBar/sideBar.jsp" />
				</div>
			</div>

			<div class="col-10 px-0 content">
				<div class="title">
					<h2>공지사항 리스트</h2>
				</div>
			</div>
		</div>
	</div>

	<%-- 💡 JS 파일은 항상 </body> 닫는 태그 직전에 위치하는 것이 성능에 좋습니다. --%>
	<script src="<c:url value='/resources/js/board/boardList.js'/>"></script>
</body>
</html>