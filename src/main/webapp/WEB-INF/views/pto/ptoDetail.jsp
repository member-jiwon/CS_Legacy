<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전자결재 디테일</title>

<script src="<c:url value='/resources/js/pto/ptoDetail.js'/>"></script>

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
let start = $("<span>").text(dayjs(dtoJson.pto_start_at).format("YYYY-MM-DD HH:mm")).append("  ~  ");
let end = $("<span>").text(dayjs(dtoJson.pto_end_at).format("YYYY-MM-DD HH:mm"));
$(".div3").append("휴가일정 :  ", start, end);

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
	const pto_used=dtoJson.pto_used;
	const pto_start_at=dtoJson.pto_start_at;
	const pto_end_at =dtoJson.pto_end_at;
	
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

	<!-- JS -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script>
        var contextPath = '${pageContext.request.contextPath}';
    </script>

</body>
</html>