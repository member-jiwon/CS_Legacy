/**
 * 공지사항 작성 js
 */
$(document).ready(function() {
    // 필수 입력 체크
    $('form').on('submit', function(e) {
        if ($('input[name="title"]').val().trim() === '' ||
            $('textarea[name="content"]').val().trim() === '') {
            alert('제목과 내용을 모두 입력해 주세요.');
            e.preventDefault();
        }
    });
});