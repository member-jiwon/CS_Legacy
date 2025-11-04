<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="invitation-container">
	<div class="invitation-header">
		<h1>회사 초대 코드</h1>
		<p>초대 코드를 복사해서 사용해 주세요</p>
	</div>

	<div class="invitation-code-section">
		<label for="invitationCode">회사 초대 코드</label>
		<div id="invitationCode" class="invitation-code">
			${companyCode}
		</div>
	</div>

	<div class="invitation-actions">
		<button class="btn btn-secondary" data-bs-dismiss="modal">뒤로가기</button>
	</div>
</div>

