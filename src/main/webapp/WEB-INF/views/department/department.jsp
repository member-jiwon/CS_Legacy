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
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<!-- CSS -->
<link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/department.css'/>">
</head>
<body>
	<header class="header d-flex align-items-center">
		<jsp:include page="/WEB-INF/views/common/sideBar/topBar.jsp" />
	</header>

	<div class="container-fluid body-wrapper">
		<div class="row g-0">
			<!-- 사이드바 -->
			<div class="col-2 px-0">
				<div class="sidebar">
					<jsp:include page="/WEB-INF/views/common/sideBar/sideBar.jsp" />
				</div>
			</div>

			<!-- 콘텐츠 -->
			<div class="col-10 px-0 content">
				<div class="management-card">
					<div class="card-header d-flex justify-content-between align-items-center">
						<h2>부서 관리</h2>
						<button class="btn btn-primary d-flex align-items-center">
							<img src="<c:url value='/resources/imgs/department/Add.svg' />"
								class="icon me-2" alt="Add" /> 부서 추가
						</button>
					</div>

					<!-- 테이블 헤더 -->
					<div class="table-header d-flex">
						<div class="col-id">번호</div>
						<div class="col-name">이름</div>
						<div class="col-email">이메일</div>
						<div class="col-department">부서</div>
						<div class="col-level">직급</div>
						<div class="col-date">날짜</div>
						<div class="col-status">퇴사 승인</div>
					</div>

					<!-- 테이블 행 -->
					<c:forEach var="dept" items="${departmentList}">
						<div class="table-row d-flex align-items-center">
							<div class="col-id">${dept.id}</div>
							<div class="col-name">${dept.name}</div>
							<div class="col-email">${dept.email}</div>
							<div class="col-department">${dept.department}</div>

							<!-- 직급 드롭다운 -->
							<div class="col-level">
								<select class="form-select level-select" data-id="${dept.id}">
									<c:forEach var="levelOption" items="${levelList}">
										<option value="${levelOption}"
											<c:if test="${dept.level == levelOption}">selected</c:if>>
											${levelOption}</option>
									</c:forEach>
								</select>
							</div>

							<div class="col-date">
								<fmt:formatDate value="${dept.date}" pattern="yyyy-MM-dd" />
							</div>
							<div class="col-status">
								<div class="button-group">
									<button class="btn">직원</button>
									<button class="btn">퇴사</button>
								</div>
							</div>
						</div>
					</c:forEach>
					
				</div>
			</div>
		</div>
	</div>

	<script src="<c:url value='/resources/js/department.js'/>"></script>
</body>
</html>
