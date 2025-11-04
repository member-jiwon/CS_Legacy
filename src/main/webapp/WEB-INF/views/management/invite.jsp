<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>초대 관리</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 부트스트랩 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/invite.css'/>">
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

			<!-- 사원관리 콘텐츠 -->
			<div class="col-10 px-0 content">
				<div class="employee-management">
					<div class="header-title">
						<b>초대 관리</b>
					</div>

					<!-- 테이블 헤더 -->
					<div class="table-header d-flex">
						<div class="col-id">번호</div>
						<div class="col-name">이름</div>
						<div class="col-email">이메일</div>
						<div class="col-date">가입일</div>
						<div class="col-status">초대 승인</div>
					</div>

					<!-- 초대 리스트 예시 -->
					<div class="table-body">
						<c:forEach var="invite" items="${invites}" varStatus="status">
							<div class="table-row d-flex align-items-center"
								data-email="${invite.email}"
								data-company-code="${invite.company_code}">
								<div class="col-id">${status.index + 1}</div>
								<div class="col-name">${invite.name}</div>
								<div class="col-email">${invite.email}</div>
								<div class="col-date">
									<fmt:formatDate value="${invite.signup_at}"
										pattern="yyyy-MM-dd" />
								</div>
								<div class="col-status">

									<div class="button-group">
										<button
											class="btn ${invite.status == null || invite.status == '미승인' ? 'active' : ''} btn-status"
											data-status="미승인">미승인</button>
										<button
											class="btn ${invite.status eq '승인'|| invite.status eq '직원' ? 'active' : ''} btn-status"
											data-status="승인">승인</button>
										<button
											class="btn ${invite.status == '거절' ? 'active' : ''} btn-status"
											data-status="거절">거절</button>
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

	<script src="<c:url value='/resources/js/invite.js'/>"></script>

</body>
</html>
