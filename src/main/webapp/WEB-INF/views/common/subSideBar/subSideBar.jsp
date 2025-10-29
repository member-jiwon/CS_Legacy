<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="stylesheet"
   href="<c:url value='/resources/css/common/subSideBar/subSideBar.css'/>">

</head>

<div class="subSidebar" id="subSidebar">
   <div class="submenu-section">
      <!-- 전체 -->
      <a href="<c:url value='commute' />" class="menu-item">
      	<span class="menu-label">전체</span>
      </a>
      <!-- 팀원 -->
      <a href="<c:url value='/notice/list' />" class="menu-item">
      	<span class="menu-label">팀별</span>
      </a>
   </div>
</div>


