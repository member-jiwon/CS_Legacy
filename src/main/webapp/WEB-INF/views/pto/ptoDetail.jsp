<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>



<script src="<c:url value='/resources/js/pto/ptoDetail.js'/>"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script><!-- jqeruy -->

<script src="https://cdn.jsdelivr.net/npm/dayjs@1/dayjs.min.js"></script><!-- dayjs라이브러리 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
crossorigin="anonymous"><!-- 부트스트랩 -->
<link rel="stylesheet" href="<c:url value='/resources/css/approval/approvalDetail.css'/>"><!--css 파일-->

</head>



<body>
<div class="container-fluid g-0">
	<div class="row">
	
	<div class="top col-12">
    	<div class="topbar">탑바 영역</div>
    </div>	
	
	
	<div class="navi col-2">
    	<div class="navibar">네비바 영역</div>
    </div>
	
	
		<div class="contentbox col-10">
		       <div class="detailBox">
		       		
		       		<!-- 버늩영역 제외한 네모박스 -->
		            <div class = "parent">
		                <div class="dateBox">
		                    <div class="start" id="start">시작</div>
		                    <div class="end" id="end">끝</div>
		                </div>
		
		
		                <div class="div4">
		                    <div class ="div5">
		                        <div class = "div6 div6_left" id="unseen">${dto.member_email}</div>
		                        <div class="div6 div6_right" id="ptoStatus"></div>
		                    </div>
		

		                    <div class = "div10">
		                        <div class = "txt">${dto.pto_content}</div>
		                    </div>
		                </div>
		            </div>
		
					<!-- 버늩영역 -->
		            <div class="btns">
						<button onclick="history.back()">뒤로가기</button>
						<button type="button" onclick='handleStatus("y")' class="waiting">승인</button>
						<button type="button" onclick='handleStatus("n")'class="waiting">반려</button>
		            </div>
		        </div>
		
		</div>
	</div>
</div>

<script>
let dtoJson = JSON.parse('${dtoJson}');
console.log("dtoJson", dtoJson);

//1️. 날짜 표시
$("#start").text(dayjs(dtoJson.pto_start_at).format("YYYY-MM-DD HH:mm"));
$("#end").text(dayjs(dtoJson.pto_end_at).format("YYYY-MM-DD HH:mm"));


// 2️. 상태 코드 -> 한글 변환
const statusMap = {
  "w": "대기중",
  "n": "반려",
  "y": "승인"
};

// 3️. 매핑 후 표시 (기본값은 '알 수 없음')
const statusText = statusMap[dtoJson.pto_status] || "알 수 없음";
$("#ptoStatus").text(statusText);

//4. 승인하기, 반려버튼 눌럿을때
const handleStatus = (type)=>{
	const targetseq= dtoJson.pto_seq;
	const newStatus=type;
	
    $.ajax({
        url: "/pto/updatestatus",
        type: "post",
        data: { targetseq, newStatus },
        success: function (resp) {
        	const statusMap = { w: "대기중", n: "반려", y: "승인" };
            $("#ptoStatus").text(statusMap[resp]);
            handleHideButton();
            alert("상태가 '" + statusMap[resp] + "' 으로 변경되었습니다.");
        },
        error: function () {
          alert("업데이트 실패");
        }
      });
}

//5. 상태 따라 버튼 숨기기
const handleHideButton = ()=>{
	const currrentStatus =$("#ptoStatus").text();
	if (currrentStatus !== "대기중") {
	    // 대기 상태가 아닐 경우 승인/반려 버튼 숨김
	    $(".waiting").hide();
	  }
}

$(document).ready(function () {
	handleHideButton();
	});
</script>


</body>
</html>