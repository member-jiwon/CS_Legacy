<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="stylesheet"
	href="<c:url value='/resources/css/common/sideBar/topBar.css'/>">
</head>

<!-- topbar -->
<div class="topbar-container">
	<div class="topbar">
		<!-- 로고 -->
		<div class="topbar-logo">
			<img src="<c:url value='/resources/imgs/topBar/log.svg' />"
				alt="Logo" class="logo-icon" />
			<div class="logo-text">cs</div>
		</div>

		<!-- 아이콘 -->
		<div class="topbar-icons">
			<!-- 알람 -->
			<img src="<c:url value='/resources/imgs/topBar/Notification.svg' />"
				class="icon-alarm" alt="Notification" />
			<!-- 회사 초대코드 -->
			<img src="<c:url value='/resources/imgs/topBar/UserAdd.svg' />"
				class="icon-add" alt="User Add" />
		</div>
	</div>
</div>

<!-- 사용할게 없긴함 아직은 -->
<script src="<c:url value='/resources/js/common/sideBar/topBar.js' />"></script>
