/**
 * 
 */
$(document).ready(function () {
    // 비밀번호 재설정 취소버튼
    $(".backbt").on("click", function () {
        window.location.href = "/admin/findpwPage";
    });

    // 비밀번호 입력창 입력시
    const pwRegex = /^[a-z0-9!@#$%^&*()]{6,}$/; // 소문자,숫자,특수문자 최소 6글자 이상
    let pwAuth = false;
    
    $("#pw").on("input", function () {
        if (!pwRegex.test($("#pw").val())) {
            pwAuth = false;
            // css 보더 넣어야하는곳
        }else{
            pwAuth = true;
        }
    });

    // 완료 버튼 클릭시
    $(".okbt").on("click", function (e) {
    	e.preventDefault();
        if ($("#pw").val() == "" || !pwAuth) {
            alert("비밀번호를 반드시 입력해주세요");
            return false;
        }

        if ($("#pw").val() == undefined) {
            alert("오류발생");
        }
        $("form").submit();
    });
});