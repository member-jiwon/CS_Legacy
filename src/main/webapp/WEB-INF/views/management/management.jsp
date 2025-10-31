<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원관리</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 부트스트랩 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

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
								data-email="${member.email}"
								data-company-code="${member.company_code}">
								
								<div class="col-id">${status.count}</div>
								<div class="col-name">${member.name}</div>
								<div class="col-email">${member.email}</div>

								<!-- 부서 -->
								<div class="col-department dropdown">
									<button class="btn btn-light dropdown-toggle" type="button"
										data-bs-toggle="dropdown" aria-expanded="false">
										<c:choose>
											<c:when test="${not empty member.dept_name}">
								        ${member.dept_name}
								    </c:when>
											<c:otherwise>미정</c:otherwise>
										</c:choose>
									</button>

									<ul class="dropdown-menu">
										<c:forEach var="dept" items="${depts}">
											<li><a class="dropdown-item" href="#"
												onclick="updateDept('${member.email}', '${dept.dept_code}', this)">
													${dept.dept_name}</a></li>
										</c:forEach>
									</ul>
								</div>

								<!-- 직급 -->
								<div class="col-level dropdown">
									<button class="btn btn-light dropdown-toggle" type="button"
										data-bs-toggle="dropdown" aria-expanded="false">
										<c:choose>
											<c:when test="${not empty member.level_name}">
								        ${member.level_name}
								    </c:when>
											<c:otherwise>미정</c:otherwise>
										</c:choose>
									</button>

									<ul class="dropdown-menu">
										<c:forEach var="level" items="${levels}">
											<li><a class="dropdown-item" href="#"
												onclick="updateLevel('${member.email}', '${level.level_code}', this)">
													${level.level_name}</a></li>
										</c:forEach>
									</ul>
								</div>
								<div class="col-status">
									<div class="button-group">
										<button class="btn active">직원</button>
										<button class="btn">퇴사</button>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>

		</div>
	</div>

	<script src="<c:url value='/resources/js/management.js'/>"></script>

</body>
</html>
