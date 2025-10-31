// DOM 요소 선택
const editBtn = document.getElementById('editBtn');      // 수정 버튼
const saveBtn = document.getElementById('saveBtn');      // 저장 버튼
const cancelBtn = document.getElementById('cancelBtn');  // 취소 버튼
const deleteBtn = document.getElementById('deleteBtn');  // 삭제 버튼
const backBtn = document.getElementById('backBtn');      // 뒤로가기 버튼

const boardTitle = document.getElementById('boardTitle'); // 제목 영역
const boardContent = document.getElementById('boardContent'); // 내용 영역

// JSP에서 notice_seq 값을 가져오기
const noticeSeq = "${notice.notice_seq}";

// 원본 데이터 백업 (취소 시 복원용)
let originalTitle = boardTitle.textContent.trim();
let originalContent = boardContent.textContent.trim();

// ------------------ 수정 모드 ------------------ //
editBtn.addEventListener('click', () => {
    // 제목과 내용을 input/textarea로 변경
    boardTitle.innerHTML = `<input type="text" id="editTitle" class="form-control" value="${originalTitle}">`;
    boardContent.innerHTML = `<textarea id="editContent" class="form-control" rows="10">${originalContent}</textarea>`;

    // 버튼 표시 변경
    editBtn.classList.add('d-none');
    deleteBtn.classList.add('d-none');
    backBtn.classList.add('d-none');

    saveBtn.classList.remove('d-none');
    cancelBtn.classList.remove('d-none');
});

// ------------------ 수정 취소 ------------------ //
cancelBtn.addEventListener('click', () => {
    // 원본 데이터 복원
    boardTitle.textContent = originalTitle;
    boardContent.innerHTML = `<p>${originalContent}</p>`;

    // 버튼 표시 원복
    editBtn.classList.remove('d-none');
    deleteBtn.classList.remove('d-none');
    backBtn.classList.remove('d-none');

    saveBtn.classList.add('d-none');
    cancelBtn.classList.add('d-none');
});

// ------------------ 저장 (Ajax 전송) ------------------ //
saveBtn.addEventListener('click', () => {
    const updatedTitle = document.getElementById('editTitle').value.trim();
    const updatedContent = document.getElementById('editContent').value.trim();

    if (!updatedTitle || !updatedContent) {
        alert("제목과 내용을 모두 입력해주세요.");
        return;
    }

    $.ajax({
        url: '/notice/update',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            notice_seq: noticeSeq,
            title: updatedTitle,
            content: updatedContent
        }),
        success: function(res) {
            alert('공지사항이 수정되었습니다.');

            // 원본 갱신 및 화면 반영
            originalTitle = updatedTitle;
            originalContent = updatedContent;

            boardTitle.textContent = updatedTitle;
            boardContent.innerHTML = `<p>${updatedContent}</p>`;

            // 버튼 표시 원복
            editBtn.classList.remove('d-none');
            deleteBtn.classList.remove('d-none');
            backBtn.classList.remove('d-none');
            saveBtn.classList.add('d-none');
            cancelBtn.classList.add('d-none');
        },
        error: function(err) {
            console.error(err);
            alert('수정 실패: ' + err.responseText);
        }
    });
});

// ------------------ 삭제 (Ajax 전송) ------------------ //
deleteBtn.addEventListener('click', () => {
    if (confirm('정말 삭제하시겠습니까?')) {
        $.ajax({
            url: '/notice/delete',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ notice_seq: noticeSeq }),
            success: function(res) {
                alert('삭제 완료');
                // 삭제 성공 시 리스트 페이지로 이동
                window.location.href = '/notice/list';
            },
            error: function(err) {
                alert('삭제 실패: ' + err.responseText);
            }
        });
    }
});
