/**
 * 공지사항 리스트 js
 */
$(document).ready(function() {
    $('.pagination li, .pagination button').click(function() {
        let page = $(this).data('page');
        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;

        // 페이지 이동 (서버 요청)
        location.href = `/notice/list?page=${page}`;
    });
});
 