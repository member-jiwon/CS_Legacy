<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
			<img src="<c:url value='/resources/imgs/topBar/Notification.svg' />"
				class="icon-alarm" alt="Notification" /> <img
				src="<c:url value='/resources/imgs/topBar/UserAdd.svg' />"
				class="icon-add" alt="User Add" />

			<!-- 카테고리 -->
			<button id="menuToggle" class="btn btn-light d-md-none me-0 p-0"
				style="border: none; background: none;">
				<img src="<c:url value='/resources/imgs/Menu.svg' />" alt="menu"
					class="icon-menu" />
			</button>
		</div>
	</div>
</div>

<script src="<c:url value='/resources/js/common/sideBar/topBar.js' />"></script>
