<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 관리</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 부트스트랩 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />

<!-- CSS -->
<link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/department/department.css'/>">
</head>
<body>
	<header class="header d-flex align-items-center">
		<jsp:include page="/WEB-INF/views/common/sideBar/topBar.jsp" />
	</header>

	<div class="container-fluid body-wrapper">
		<div class="row g-0">
			<!-- 사이드바 -->
			<div class="col-2 px-0">
				<div class="sidebarbox">
					<jsp:include page="/WEB-INF/views/common/sideBar/sideBar.jsp" />
				</div>
			</div>

			<!-- 콘텐츠 -->
			<div class="col-10 px-0 content">
				<div class="management-card">
					<div class="dept-body">
						<div
							class="card-header d-flex justify-content-between align-items-center">
							<h2>부서 관리</h2>

							<button id="deptAddTriggerBtn" type="button"
								class="btn btn-primary d-flex align-items-center"
								data-bs-toggle="modal" data-bs-target="#deptPostModal">
								<img src="<c:url value='/resources/imgs/department/Add.svg' />"
									class="icon me-2" alt="Add" /> 부서 추가
							</button>
						</div>

						<!-- 테이블 헤더 -->
						<div class="table-header d-flex">
							<div class="col-id">번호</div>
							<div class="col-name">부서명</div>
							<div class="col-count">직원 수</div>
							<div class="col-date">생성일</div>
						</div>

						<!-- 테이블 행 -->
						<c:forEach var="dept" items="${departmentList}" varStatus="loop">
							<div class="table-row d-flex">
								<div class="col-id">${loop.count}</div>
								<div class="col-name">${dept.dept_name}</div>
								<div class="col-count">${dept.employeeCount}</div>
								<div class="col-date">
									<fmt:formatDate value="${dept.create_date}"
										pattern="yyyy-MM-dd" />
								</div>
							</div>
						</c:forEach>
					</div>

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

	
	<!-- JS -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script>
        var contextPath = '${pageContext.request.contextPath}';
    </script>

	<!-- 오버레이 -->
	<div class="modal fade" id="deptPostModal" tabindex="-1"
		aria-labelledby="deptPostModalLabel" aria-hidden="true">
		<div class="modal-dialog ms-auto" style="max-width: 60%; margin-right: 0;">
			<div class="modal-content" style="width: 100%;">
				<div class="modal-body" id="deptPostModalBody"></div>
			</div>
		</div>
	</div>

	<script src="<c:url value='/resources/js/department/department.js'/>"></script>
</body>
</html>
