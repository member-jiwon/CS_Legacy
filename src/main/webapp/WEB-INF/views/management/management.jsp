<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원관리</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />

<link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/management.css'/>">
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
				<div class="employee-management">
					<div class="header-title">
						<b>사원관리</b>
					</div>

					<div class="table-header d-flex">
						<div class="col-id">번호</div>
						<div class="col-name">이름</div>
						<div class="col-email">이메일</div>
						<div class="col-department">부서</div>
						<div class="col-level">직급</div>
						<div class="col-status">초대 승인</div>
					</div>

					<div class="table-body">
						<c:forEach var="member" items="${members}" varStatus="status">
							<div class="table-row d-flex align-items-center"
								data-email="${member.EMAIL}"
								data-company-code="${member.COMPANY_CODE}">

								<div class="col-id">${status.count}</div>
								<div class="col-name">${member.NAME}</div>
								<div class="col-email">${member.EMAIL}</div>

								<!-- 부서 -->
								<div class="col-department dropdown">
									<button class="btn btn-light dropdown-toggle" type="button"
										data-bs-toggle="dropdown" aria-expanded="false">
										<c:choose>
											<c:when test="${not empty member.DEPT_NAME}">${member.DEPT_NAME}</c:when>
											<c:otherwise>미정</c:otherwise>
										</c:choose>
									</button>

									<ul class="dropdown-menu">
										<c:forEach var="dept" items="${depts}">
											<li><a class="dropdown-item" href="#"
												onclick="updateDept('${member.EMAIL}', '${dept.dept_code}', this)">${dept.dept_name}</a></li>
										</c:forEach>
									</ul>
								</div>

								<!-- 직급 -->
								<div class="col-level dropdown">
									<button class="btn btn-light dropdown-toggle" type="button"
										data-bs-toggle="dropdown" aria-expanded="false">
										<c:choose>
											<c:when test="${not empty member.LEVEL_NAME}">${member.LEVEL_NAME}</c:when>
											<c:otherwise>미정</c:otherwise>
										</c:choose>
									</button>

									<ul class="dropdown-menu">
										<c:forEach var="level" items="${levels}">
											<li><a class="dropdown-item" href="#"
												onclick="updateLevel('${member.EMAIL}', '${level.level_code}', this)">${level.level_name}</a>
											</li>
										</c:forEach>
									</ul>
								</div>

								<!-- 직원 / 퇴사 버튼 -->
								<div class="col-status">
									<div class="button-group">
										<button
											class="btn ${member.STATUS eq '승인' || member.STATUS eq '직원' ? 'active' : ''}"
											onclick="updateStatus('${member.EMAIL}', '직원', this)">직원</button>
										<button class="btn ${member.STATUS eq '퇴사' ? 'active' : ''}"
											onclick="updateStatus('${member.EMAIL}', '퇴사', this)">퇴사</button>
									</div>
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
    
    <!-- 사원관리 -->
	<script src="<c:url value='/resources/js/management.js'/>"></script>
</body>
</html>
