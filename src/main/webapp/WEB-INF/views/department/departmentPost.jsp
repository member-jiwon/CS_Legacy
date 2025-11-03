<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" href="<c:url value='/resources/css/department/departmentPost.css'/>">

<div class="dept-post-content-wrapper">

	<div class="dept-post-header">
		<h2 class="dept-post-title">부서 추가</h2>
	</div>

	<form id="deptPostForm" action="<c:url value='/department/add'/>" method="post">
		<div class="dept-post-form-section">
			<div class="dept-post-form-row">
				<label for="newDeptName" class="dept-post-label">부서</label>

				<div class="dept-post-input-group">
					<input type="text" id="newDeptName" name="deptName"
						class="dept-post-input-field" placeholder="부서 추가"/>

					<button type="button"
						class="dept-post-add-btn dept-post-btn--inactive" disabled>추가</button>
				</div>
			</div>
		</div>
	</form>

	<div class="dept-post-list-section">
		<div class="dept-post-list-wrapper">
			<h3 class="dept-post-list-title">부서 목록</h3>

			<div class="dept-post-list-item-group">
				<%-- Controller에서 전달받은 allDepartments 리스트를 사용한다고 가정 --%>
				<c:forEach var="dept" items="${allDepartments}">
					<div class="dept-post-list-item" data-dept-id="${dept.dept_code}">
						<span class="dept-post-item-name">${dept.dept_name}</span> <img
							class="dept-post-item-delete-icon"
							src="<c:url value='/resources/imgs/department/X.svg'/>"
							alt="삭제">
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

	<div class="dept-post-action-btns">
		<button type="button" class="dept-post-btn dept-post-btn--secondary"
			data-bs-dismiss="modal">뒤로가기</button>
		<button type="submit" form="deptPostForm"
			class="dept-post-btn dept-post-btn--primary">완료</button>
	</div>
</div>

<script>
    // AJAX로 HTML이 로드된 후, JavaScript 파일을 실행합니다.
    $.getScript("<c:url value='/resources/js/department/departmentPost.js'/>");
</script>