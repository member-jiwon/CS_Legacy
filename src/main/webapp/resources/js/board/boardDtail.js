// ------------------ DOM 요소 선택 ------------------ //
const editBtn = document.getElementById('editBtn'); // 수정
const saveBtn = document.getElementById('saveBtn'); // 저장
const cancelBtn = document.getElementById('cancelBtn'); // 취소
const deleteBtn = document.getElementById('deleteBtn'); // 삭제
const backBtn = document.getElementById('backBtn'); // 뒤로가기

const boardTitle = document.getElementById('boardTitle');
const boardContent = document.getElementById('boardContent');

let originalTitle = boardTitle.textContent.trim();
// originalContent를 가져올 때, <p> 태그를 제거하고 순수한 텍스트만 가져와야 합니다.
let originalContent = boardContent.textContent.trim(); 

// 페이지 로드 시 save/cancel 숨김 (보조용)
saveBtn.classList.add('d-none');
cancelBtn.classList.add('d-none');

// ------------------ 수정 모드 ------------------ //
editBtn.addEventListener('click', () => {
    boardTitle.innerHTML = `<input type="text" id="editTitle" class="form-control" value="${originalTitle}">`;
    boardContent.innerHTML = `<textarea id="editContent" class="form-control" rows="10">${originalContent}</textarea>`;

    // 버튼 토글
    editBtn.classList.add('d-none');
    deleteBtn.classList.add('d-none');
    backBtn.classList.add('d-none');

    saveBtn.classList.remove('d-none');
    cancelBtn.classList.remove('d-none');
});

// ------------------ 수정 취소 ------------------ //
cancelBtn.addEventListener('click', () => {
    boardTitle.textContent = originalTitle;
	// 순수 텍스트만 -  HTML 구조를 깔끔하게 유지하기 위해 textContent 사용
    boardContent.textContent = originalContent;

    // 버튼 원복
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
    
    // Ajax 전송 데이터 구성
    let postData = {
        notice_seq: parseInt(noticeSeq),
        title: updatedTitle,
        content: updatedContent
    };

    $.ajax({
        url: '/notice/update',
        type: 'POST',
        data: postData, 
        success: function(res) {
            alert('공지사항이 수정되었습니다.');
            location.reload(); // 성공 후 화면 갱신
        },
        error: function(err) {
            alert('수정 실패: 서버 응답 문제. 콘솔을 확인하세요.');
            console.error('Ajax Error:', err);
        }
    });
});

// ------------------ 삭제 ------------------ //
deleteBtn.addEventListener('click', () => {
    if (confirm('정말 삭제하시겠습니까?')) {
        const form = document.createElement('form');
        form.method = 'post';
        form.action = '/notice/delete';

        // 1. notice_seq 입력 필드
        const seqInput = document.createElement('input');
        seqInput.type = 'hidden';
        seqInput.name = 'notice_seq';
        seqInput.value = parseInt(noticeSeq);
        form.appendChild(seqInput);
        
        document.body.appendChild(form);
        form.submit();
    }
});