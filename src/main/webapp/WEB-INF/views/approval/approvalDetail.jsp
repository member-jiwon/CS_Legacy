<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script><!-- jqeury-->
<script src="<c:url value='/resources/js/approval/approvalDetail.js'/>"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
            crossorigin="anonymous"><!-- 부트스트랩 -->
<link rel="stylesheet" href="<c:url value='/resources/css/approval/approvalDetail.css'/>"><!--css 파일-->
<script src="https://cdn.jsdelivr.net/npm/dayjs@1/dayjs.min.js"></script><!-- dayjs라이브러리 -->


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
		                    <div> ${dto.approval_title}</div>
		                </div>
		
		
		                <div class="div4">
		                    <div class ="div5">
		                        <div class = "div6 div6_left" id="unseen">${dto.member_email}</div>
		                        <div class="div6 div6_center" id="approvalDate"></div>
		                        <div class="div6 div6_right" id="approvalStatus"></div>
		                    </div>
		
		
		                    <div class= "div9">
		                        <div class = "rectangleParent">
									<!-- 파일 여기에 동적으로 추가됨 -->
		                        </div>
		
		                    </div>
		                    <div class = "div10">
		                        <div class = "txt">${dto.approval_content}</div>
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
let dtofiles = JSON.parse('${dtofiles}');
console.log(dtofiles);

//0. 파일목록 가져오기
$(document).ready(function () {
	if (!dtofiles || dtofiles.length === 0) {
		  let div = $("<div>").text("파일이 존재하지 않습니다");
		  $(".rectangleParent").append(div);
		} else {
		  dtofiles.forEach((file) => {
		    let fileRow = $("<div>").addClass("fileRow");

		    let hwp = $("<div>").addClass("hwp");
		    let aTag = $("<a>")
		      .attr("href", "/file/download?sysname=" + encodeURIComponent(file.sysname) + "&file_type=" + encodeURIComponent(file.file_type))
		      .attr("download", true)
		      .text(file.oriname);
		    hwp.append(aTag);

		    fileRow.append(hwp);
		    $(".rectangleParent").append(fileRow);
		  });
		}
})




// 1️. 날짜 표시
$("#approvalDate").text(dayjs(dtoJson.approval_at).format("YYYY-MM-DD"));

// 2️. 상태 코드 -> 한글 변환
const statusMap = {
  "w": "대기중",
  "n": "반려",
  "y": "승인"
};

// 3️. 매핑 후 표시 (기본값은 '알 수 없음')
const statusText = statusMap[dtoJson.approval_status] || "알 수 없음";
$("#approvalStatus").text(statusText);


//3. 승인하기, 반려버튼 눌럿을때
const handleStatus = (type)=>{
	const targetseq= dtoJson.approval_seq;
	const newStatus=type;
	
    $.ajax({
        url: "/approval/updatestatus",
        type: "post",
        data: { targetseq, newStatus },
        success: function (resp) {
        	const statusMap = { w: "대기중", n: "반려", y: "승인" };
            $("#approvalStatus").text(statusMap[resp]);
            handleHideButton();
            alert("상태가 '" + statusMap[resp] + "' 으로 변경되었습니다.");
        },
        error: function () {
          alert("업데이트 실패");
        }
      });
}

//4. 상태 따라 버튼 숨기기
const handleHideButton = ()=>{
	const currrentStatus =$("#approvalStatus").text();
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