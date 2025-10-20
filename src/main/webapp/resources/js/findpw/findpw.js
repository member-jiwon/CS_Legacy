/**
 * 
 */
 $(document).ready(function() {
 	// 이메일 유효성
 	const idRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; // e-mail 정규식(영서띠가보내줌)
 	let code = "";
 	let mailAuth = false;
 	
 	// 비번찾기 취소버튼
 	$(".backbt").on("click", function(){ 
		window.location.href = "/"; 
	});
	
	// 이메일 입력시
	$("#admin_email").on("input", function(){
		if(!idRegex.test($("#admin_email").val())){
			// css 보더 넣어야하는곳
		}
	});
	
	// 이메일 인증 확인 버튼
	$("#emailCheck").on("click", function (e) {
	    $.ajax({
	        url: "/admin/emilAuth",
	        data: { admin_email: $("#admin_email").val() },
	        type: "POST",
	        success: function (reps) {
	            if (reps == 1) {
	                $.ajax({
	                    url: "/admin/emailCheck",
	                    data: { admin_email: $("#admin_email").val() },
	                    type: "POST",
	                    success: function (reps2) {
	                        if (reps2 !== "실패") {
	                        	code = reps2;
	                            alert("인증 메일 발송 완료.");
	                        } else {
	                            alert("오류 : 이메일 재확인 필요");
	                        }
	                    }
	                })
	            }else{
	                alert("가입되어있는 사용자의 이메일이 아닙니다.")
	            }
	        }
	    })
	});
	
	// 이메일 인증코드 일치 확인
	$("#emailAuth").on("input", function(e){
		e.preventDefault();
    	if(code ===$("#emailAuth").val()){
        	mailAuth = true;
    	}else{
    		mailAuth = false;
        	// 여기에 보더에 컬러넣는 css
    	}
	});
	
	// 완료 버튼 클릭시
	$(".okbt").on("click", function(){
    	if(!mailAuth){
        	alert("이메일 인증 필수");
        	return false;
    	}
    	
		if(code === ""){
			alert("이메일 인증 필수12");
        	return false;
		}
		$("form").submit();
	});
});