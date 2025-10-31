<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="stylesheet"
	href="<c:url value='/resources/css/common/sideBar/topBar.css'/>">

<link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/invitationCode.css'/>">
</head>

<!-- 모달 -->
<div class="modal fade" id="invitationModal" tabindex="1"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-lg">
		<div class="modal-content">
			<div class="modal-body" id="invitationModalBody"></div>
		</div>
	</div>
</div>

<!-- topbar -->
<div class="topbar-container">
	<div class="topbar">
		<!-- 로고 -->
		<div class="topbar-logo">
			<img src="<c:url value='/resources/imgs/topBar/log.svg' />"
				alt="Logo" class="logo-icon" />
			<div class="logo-text">CS</div>
		</div>

		<!-- 아이콘 -->
		<div class="topbar-icons">
			<!-- 알람 -->
			<img src="<c:url value='/resources/imgs/topBar/Notification.svg' />"
				class="icon-alarm" alt="Notification" />

			<!-- 회사 초대코드 버튼 -->
			<button id="inviteBtn" class="icon-button">
				<img src="<c:url value='/resources/imgs/topBar/UserAdd.svg' />"
					class="icon-add" alt="User Add" />
			</button>
		</div>
	</div>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    var contextPath = '${pageContext.request.contextPath}';
</script>
<script src="<c:url value='/resources/js/common/sideBar/topBar.js' />"></script>
