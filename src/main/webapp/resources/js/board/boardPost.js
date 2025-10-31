/**
 * 공지사항 작성 js
 */
 $(document).ready(function() {
 	// 필수 입력 
    $('form').on('submit', function(e) {
      if ($('input[name="title"]').val().trim() === '' ||
          $('textarea[name="content"]').val().trim() === '') {
        alert('제목과 내용을 모두 입력해 주세요.');
        e.preventDefault();
      }
    });
  });
  
  //첨부 파일 이름 표시
  $('.file-input').on('change', function() {
  const fileName = $(this).val().split('\\').pop();
  $('.file-text').text(fileName || '눌러 파일을 첨부해 주세요');
});