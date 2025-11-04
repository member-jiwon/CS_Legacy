$(document).on('click', '.btn-status', function() {
    var $btn = $(this);
    var $row = $btn.closest('.table-row');
    var email = $row.data('email');
    var companyCode = $row.data('company-code');
    var status = $btn.data('status');

    $.ajax({
        url: '/invite/status',
        type: 'POST',
        data: {
            email: email,
            company_code: companyCode,
            status: status
        },
        success: function(response) {
            if(response === 'success') {
                // 버튼 active 상태 변경
                $row.find('.btn-status').removeClass('active');
                $btn.addClass('active');
            } else {
                alert('상태 변경 실패');
            }
        },
        error: function() {
            alert('서버 오류 발생');
        }
    });
});
