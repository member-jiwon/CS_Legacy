<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글 작성</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 부트스트랩 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/board/boardPost.css'/>">
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
				<form action="<c:url value='/notice/post'/>" method="post"
					enctype="multipart/form-data">
					<div class="post-wrapper">
						<!-- 글 작성 제목 -->
						<div class="form-header">
							<h2 class="header-title">글 작성</h2>
						</div>

						<!-- 제목 입력 -->
						<div class="title-container">
							<input type="text" name="title" placeholder="제목을 입력해 주세요"
								class="title-input" />
						</div>

						<!-- 파일 첨부 -->
						<div class="file-container">
							<label class="file-label"> 
								<input type="file" name="file" class="file-input" /> 
								<span class="file-text">눌러 파일을 첨부해 주세요</span>
							</label>
						</div>

						<!-- 본문 입력 -->
						<div class="content-container">
							<textarea name="content" placeholder="보낼 내용을 입력해 주세요" class="content-input"></textarea>
						</div>
					</div>

					<!-- 버튼 -->
					<div class="button-container">
						<a href="<c:url value='/notice/list' />" class="btn btn-secondary">뒤로가기</a>
						<button type="submit" class="btn btn-primary">공유</button>
					</div>
				</form>
			</div>

		</div>
	</div>

	<script src="<c:url value='/resources/js/board/boardPost.js'/>"></script>
</body>
</html>
