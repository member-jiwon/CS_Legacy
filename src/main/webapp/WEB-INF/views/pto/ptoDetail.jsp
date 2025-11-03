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
<link rel="stylesheet" href="<c:url value='/resources/css/pto/ptoDetail.css'/>"><!--css 파일-->

</head>



<body>
<!-- 탑바 -->
   <header class="header d-flex align-items-center">
      <jsp:include page="/WEB-INF/views/common/sideBar/topBar.jsp" />
   </header>


<!-- 탑바제외 영역 -->
<div class="container-fluid body-wrapper">
	<div class="row g-0">
	
<!-- 사이드바 -->      
         <div class="col-2 px-0">
            <div class="sidebarbox">
               <jsp:include page="/WEB-INF/views/common/sideBar/sideBar.jsp" />
            </div>
         </div>	
	
<!-- 사이드바 제외 우측 영역 -->
         <div class="col-10 px-0 content">
            <div class="board-container">
            
		       		<!-- 버늩영역 제외한 네모박스 -->
		            <div class = "parent">
		                <div class="div2">
		                    <div class="div3"></div>
		                </div>
		
		
		                <div class="div4">
		                    <div class ="div5">
		                        <div class = "div6 div6_left" id="unseen">${name}</div>
		                        <div class="div6 div6_right" id="ptoStatus"></div>
		                    </div>
		
		                    <div class = "div10">
		                        <div class = "txt">${dto.pto_content}</div>
		                    </div>
		                </div>
		            </div>
            
					<!-- 버늩영역 -->
		            <div class="btns">
						<button onclick="history.back()" id="btn3">뒤로가기</button>
						<button type="button" onclick='handleStatus("n")'class="waiting" id="btn2">반려</button>
						<button type="button" onclick='handleStatus("y")' class="waiting" id="btn1">승인</button>
		            </div>            
              
            </div>
         </div>	
	
	
	</div>
</div>




<script>
let dtoJson = JSON.parse('${dtoJson}');
console.log("dtoJson", dtoJson);

//1️. 날짜 표시
let start = $("<span>").text(dayjs(dtoJson.pto_start_at).format("YYYY-MM-DD HH:mm")).append("  ~  ");
let end = $("<span>").text(dayjs(dtoJson.pto_end_at).format("YYYY-MM-DD HH:mm"));
$(".div3").append("휴가일정 |  ", start, end);

// 2️. 상태 코드 -> 한글 변환
const statusMap = {
  "w": "대기",
  "n": "반려",
  "y": "승인"
};

// 3️. 매핑 후 표시 (기본값은 '알 수 없음')
const statusText = statusMap[dtoJson.pto_status] || "알 수 없음";
const $statusDiv = $("#ptoStatus");
$statusDiv.text(statusText);

// 상태별 색상 클래스 추가
$statusDiv.removeClass("inProgress approved denied");
if (statusText === "대기") {
  $statusDiv.addClass("inProgress");
} else if (statusText === "승인") {
  $statusDiv.addClass("approved");
} else if (statusText === "반려") {
  $statusDiv.addClass("denied");
}

//4. 승인하기, 반려버튼 눌럿을때
const handleStatus = (type)=>{
	const targetseq= dtoJson.pto_seq;
	const newStatus=type;
	const pto_used=dtoJson.pto_used;
	const pto_start_at = dayjs(dtoJson.pto_start_at).format("YYYY-MM-DD HH:mm:ss");
	const pto_end_at = dayjs(dtoJson.pto_end_at).format("YYYY-MM-DD HH:mm:ss");
	const member_email =dtoJson.member_email;
	
    $.ajax({
        url: "/pto/updatestatus",
        type: "post",
        data: { targetseq, newStatus,pto_used,pto_start_at, pto_end_at,member_email},
        success: function (resp) {
        	  const statusMap = { w: "대기", n: "반려", y: "승인" };
        	  	  
        	  // 연차 부족 시 서버에도 반려로 다시 업데이트
        	  if (resp === "lack") {
        	    alert("연차가 부족합니다. 자동으로 반려 처리됩니다.");
        	    
        	    $.ajax({
        	      url: "/pto/updatestatus",
        	      type: "post",
        	      data: { targetseq, newStatus: "n", pto_used, pto_start_at, pto_end_at, member_email },
        	      success: function () {
        	        $("#ptoStatus").text("반려").removeClass().addClass("denied");
        	        handleHideButton();
        	        alert("자동으로 반려 처리되었습니다.");
        	      },
        	      error: function () {
        	        alert("자동 반려 처리 중 오류 발생");
        	      }
        	    });
        	    return;
        	  }

        	  if (!statusMap[resp]) {
        	    alert("업데이트 실패");
        	    return;
        	  }

        	  const text = statusMap[resp];
        	  const $statusDiv = $("#ptoStatus");

        	  $statusDiv.text(text);
        	  $statusDiv.removeClass("inProgress approved denied");
        	  if (text === "대기") $statusDiv.addClass("inProgress");
        	  else if (text === "승인") $statusDiv.addClass("approved");
        	  else if (text === "반려") $statusDiv.addClass("denied");

        	  alert("상태가 '" + text + "' 으로 변경되었습니다.");
        	  handleHideButton();
        	},
        error: function () {
          alert("업데이트 실패");
        }
      });
}

//5. 상태 따라 버튼 숨기기
const handleHideButton = ()=>{
	const currrentStatus =$("#ptoStatus").text();
	if (currrentStatus !== "대기") {
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