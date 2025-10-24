<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="sidebar" id="sidebar">
	<div class="menu-section">
		<!-- 근태관리 -->
		<a href="<c:url value='/attend' />" class="menu-item active"> <img
			src="<c:url value='/resources/imgs/sideBar/Clock-active.svg' />"
			class="menu-icon" alt="근태관리" />  <span class="menu-label">근태관리</span>
		</a>
		<!-- 게시판 -->
		<a href="<c:url value='/board/list' />" class="menu-item"> <img
			src="<c:url value='/resources/imgs/sideBar/Popup.svg' />"
			class="menu-icon" alt="게시판" /> <span class="menu-label">게시판</span>
		</a>
		<!-- 전자결재 -->
		<a href="<c:url value='/boardList.jsp' />" class="menu-item"> <img
			src="<c:url value='/resources/imgs/sideBar/Sign_Document.svg' />"
			class="menu-icon" alt="전자결재" />  <span class="menu-label">전자결재</span>
		</a>
		<!-- 연차결재 -->
		<a href="#" class="menu-item"> <img
			src="<c:url value='/resources/imgs/sideBar/Document.svg' />"
			class="menu-icon" alt="연차 결재" /> <span class="menu-label">연차
				결재</span>
		</a>
		<!-- 사원관리 -->
		<a href="#" class="menu-item"> <img
			src="<c:url value='/resources/imgs/sideBar/Contacts.svg' />"
			class="menu-icon" alt="사원관리" /> <span class="menu-label">사원관리</span>
		</a>
		<!-- 부서관리 -->
		<a href="#" class="menu-item"> <img
			src="<c:url value='/resources/imgs/sideBar/Tree_Structure.svg' />"
			class="menu-icon" alt="부서 관리" /> <span class="menu-label">부서
				관리</span>
		</a>
	</div>

	<div class="menu-section bottom">
		<div class="menu-item">
			<img src="<c:url value='/resources/imgs/sideBar/Logout.svg' />"
				alt="로그아웃" class="menu-icon" />  <span class="menu-label">로그아웃</span>
		</div>
	</div>
</div>

<!-- 오버레이 -->
<div class="overlay" id="overlay"></div>

<script src="<c:url value='/resources/js/common/sideBar/sideBar.js' />"></script>