// management.js

// 부서 업데이트
function updateDept(email, deptCode, element) {
    // companyCode 추출
    var $row = $(element).closest('.table-row');
    var companyCode = $row.data('company-code');

    $.post('/job/updateDept', { 
        email: email, 
        dept_code: deptCode,
        company_code: companyCode // company_code 전달
    }).done(function(res){
         if(res === 'success') location.reload();
         else alert('부서 변경 실패');
    }).fail(()=>alert('서버 오류'));
}

// 직급 업데이
function updateStatus(email, status, btn) {
    $.ajax({
        type: "POST",
        url: contextPath + "/job/updateStatus",
        data: {
            email: email,
            status: status,
            company_code: $(btn).closest('.table-row').data('company-code')
        },
        success: function(res) {
            if(res === 'success') {
                // 버튼 상태 업데이트
                $(btn).siblings().removeClass('active');
                $(btn).addClass('active');
            } else {
                alert("실패");
            }
        },
        error: function(err) {
            console.error(err);
            alert("요청 실패");
        }
    });
}