/**
 *  공지사항 리스트 js
 */
  $(document).ready(function() {
  $.ajax({
    url: '/notice/api/list',
    method: 'GET',
    success: function(data) {
      // data는 공지사항 리스트 (JSON)
      let listHtml = '';
      data.forEach(notice => {
        listHtml += `<li>${notice.title} - ${notice.date}</li>`;
      });
      $('#noticeList').html(listHtml);
    },
    error: function() {
      alert('공지사항 데이터를 불러오는데 실패했습니다.');
    }
  });
});