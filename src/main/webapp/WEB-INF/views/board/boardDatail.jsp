<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 부트스트랩 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<!-- 보드디테일 css -->
<link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/board/boardDatail.css'/>">
</head>
<body>
	<header class="header d-flex align-items-center">
		<jsp:include page="/WEB-INF/views/common/sideBar/topBar.jsp" />
	</header>

	<div class="container-fluid body-wrapper">
		<div class="row g-0">
			<div class="col-2 px-0 sidebar">
				<jsp:include page="/WEB-INF/views/common/sideBar/sideBar.jsp" />
			</div>

			<!-- boardDetail -->
			<div class="col-10 px-0 content">
				<section class="notice-detail">
					<h2 id="boardTitle" class="notice-title mb-3">${notice.title}</h2>

					<!-- 작성일, 조회수 -->
					<div class="notice-meta d-flex mb-3">
						<span class="notice-date"> <fmt:formatDate
								value="${notice.created_at}" pattern="yyyy-MM-dd" />
						</span> <span class="notice-views">조회수: ${notice.view_count}회</span>
					</div>

					<!-- 첨부 파일 -->
					<c:if test="${not empty notice.files}">
						<div class="notice-files mb-3">
							<c:forEach var="file" items="${notice.files}">
								<a href="<c:url value='/uploads/${file.sysname}'/>"
									download="${file.oryname}"> 📎 ${file.oryname} </a>
								<br>
							</c:forEach>
						</div>
					</c:if>

					<!-- 본문 내용 -->
					<article id="boardContent" class="notice-content mb-3">
						<p>${notice.content}</p>
					</article>

					<!-- 버튼 -->
					<div class="notice-actions">
						<a href="<c:url value='/notice/list'/>" class="btn btn-secondary" id="backBtn">뒤로가기</a>
						<button class="btn btn-secondary d-none" id="cancelBtn">취소</button>
						<button class="btn btn-success d-none" id="saveBtn">저장</button>
						<button class="btn btn-danger" id="deleteBtn">삭제</button>
						<button class="btn btn-warning" id="editBtn">수정</button>
					</div>
				</section>
			</div>

		</div>
	</div>

	<script src="<c:url value='/resources/js/board/boardDtail.js'/>"></script>
</body>
</html>