// management.js

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

function updateLevel(email, levelCode, element) {
    // companyCode 추출
    var $row = $(element).closest('.table-row');
    var companyCode = $row.data('company-code');

    $.post('/job/updateLevel', { 
        email: email, 
        level_code: levelCode,
        company_code: companyCode // company_code 전달
    }).done(function(res){
         if(res === 'success') location.reload();
         else alert('직급 변경 실패');
    }).fail(()=>alert('서버 오류'));
}