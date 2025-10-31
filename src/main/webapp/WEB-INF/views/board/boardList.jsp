<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 리스트</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 부트스트랩 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<!-- 보드리스트 css -->
<link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>">
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

			<!-- boardList -->
			<div class="col-10 px-0 content">
				<div class="board-container">
					<!-- header -->
					<div class="board-header">
						<h2 class="board-title">공지사항</h2>
						<button type="button" class="board-add-btn"
							onclick="location.href='<c:url value="/notice/post"/>'">
							<img class="add-icon"
								src="<c:url value='/resources/imgs/board/Add.svg'/>" alt="글 작성">
							글 작성
						</button>
					</div>


					<!-- body -->
					<div class="board-table">
						<div class="board-table-header">
							<div class="col-num">번호</div>
							<div class="col-title">제목</div>
							<div class="col-date">작성일</div>
							<div class="col-views">조회수</div>
						</div>

						<!-- 반복문으로 리스트 출력 -->
						<c:forEach var="notice" items="${notices}">
							<form action="<c:url value='/notice/detail'/>" method="get">
								<input type="hidden" name="notice_seq"
									value="${notice.notice_seq}" />
								<div class="board-table-row" onclick="this.parentNode.submit();">
									<div class="col-num">${notice.notice_seq}</div>
									<div class="col-title">${notice.title}</div>
									<div class="col-date">
										<fmt:formatDate value="${notice.created_at}"
											pattern="yyyy-MM-dd" />
									</div>
									<div class="col-views">${notice.view_count}회</div>
								</div>
							</form>
						</c:forEach>
					</div>

					<!-- 내용 없을 경우 - empty notices: 리스트가 비어 있을 때 -->
					<c:if test="${empty notices}">
						<div class="file-table">
							<img src="<c:url value='/resources/imgs/board/File.svg' />"
								class="file-icon" alt="">
							<div class="text">공지사항이 없습니다</div>
						</div>
					</c:if>

					<!-- footer - 페이지 네비게이션 -->
					<nav class="pagination">
						<!-- 한 번에 이전 (<<) -->
						<button class="jump prev"
							data-page="${currentPage - pageBlockSize}">&laquo;</button>
						<!-- 한 페이지씩 이전 (<) -->
						<button class="prev" data-page="${currentPage - 1}">&lt;</button>

						<ul>
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<li class="${i == currentPage ? 'active' : ''}" data-page="${i}">${i}</li>
							</c:forEach>
						</ul>

						<!-- 한 페이지씩 다음 (>) -->
						<button class="next" data-page="${currentPage + 1}">&gt;</button>
						<!-- 한 번에 다음 (>>) -->
						<button class="jump next"
							data-page="${currentPage + pageBlockSize}">&raquo;</button>
					</nav>


				</div>
			</div>
		</div>
	</div>

	<%-- JS 파일은 항상 </body> 닫는 태그 직전에 위치하는 것이 성능에 좋습니다. --%>
	<script src="<c:url value='/resources/js/board/boardList.js'/>"></script>
</body>
</html>