<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/resources/css/approval/approvalDetail.css'/>">
<script src="<c:url value='/resources/js/approval/approvalDetail.js'/>"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
            crossorigin="anonymous"><!-- 부트스트랩 -->
<script src="https://cdn.jsdelivr.net/npm/dayjs@1/dayjs.min.js"></script><!-- dayjs라이브러리 -->


<style>
       body,
        html {
            width: 100%;
            height: 100%;
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            overflow: hidden;
        }
        .row{
            width: 100%;
            height: 100%;
            padding: 0;
            margin: 0;
        }
		.container-fluid{
		            background-color: pink;
		            width: 100%;
		            height: 100%;
		            margin: 0;
		}
		.navi {
		            background-color: aquamarine;
		            height: 100vh;
		        }
        .contentbox {
            padding: 30px;
            height: 90vh;
            /* width:100vw; */
            background-color: rgb(222, 246, 255);
        }
        .top{
        background-color: pink;
        height:10vh;
        }



/*최상위박스*/
.detailBox{
    padding: 10px 30px 0 30px;
    width: 100%;
    height: 100%;

    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
}
/*버튼제외 글불러오는 박스*/
.parent {
    border-radius: 22px;
    background-color: #fff;
    border: 1px solid #8c8c8c;

     align-self: stretch;
     height: 90%;
     display: flex;
     flex-direction: column;
     align-items: center;
}
/*타이틀*/
.div2 {
     align-self: stretch;
     display: flex;
     align-items: center;
     flex-wrap: wrap;
     align-content: center;
     padding: 30px;
}
.div3 {
     position: relative;
     text-transform: capitalize;
     font-weight: 600;
     font-size: 20px;
}
/*이메일, 작성일, 상태*/
.div4 {
     /* align-self: stretch; */
     width: 100%;
     height: 100%;
     display: flex;
     flex-direction: column;
     align-items: flex-start;
     padding: 0px 20px;
     gap: 30px;
     font-size: 18px;
}

.div5 {
     width: 100%;
     border-bottom: 1px solid #8c8c8c;
     display: flex;
     flex-direction: row;
     padding-bottom: 10px;
     gap: 10px
}


.div6 {
flex-shrink: 0;
  height: 100%;
  text-transform: capitalize;
  font-weight: 500;
  display: flex;
  align-items: center;
}
.div6_left {
  flex: 1;
}
.div6_center {
  flex: 1;
}
.div6_right {
  flex: 8;
}


.frameChild {
     height: 47px;
     width: 0px;
}
.frameItem {
     height: 31px;
     width: 1px;
     position: relative;
     border-right: 1px solid #8c8c8c;
     box-sizing: border-box;
}
.frame {
     height: 47px;
     display: flex;
     align-items: center;
     justify-content: center;
     color: #8c8c8c;
}

/*파일영역*/
.div9 {
     align-self: stretch;
     display: flex;
     align-items: center;
     padding: 0px 20px;
     font-size: 16px;
     color: #8c8c8c;
}
.rectangleParent {
     height: 59px;
     width: 100%;
     display: flex;
     border-radius: 16px;
     border: 1px solid #8c8c8c;
     box-sizing: border-box;
}
.iconbox{
    width: 10%;
    height: 100%;
    float: left;
    
    display: flex;
    align-items: center;
    justify-content: center;
}
.documentIcon {
     width: 24px;
     height: 24px;
     margin :auto;
}
.hwp {
    width: 90%;
    height: 100%;
    float: left;
     display: flex;
     align-items: center;
     padding-left: 15px;
}
/*내용영역*/
.div10 {
    height: 380px;
     align-self: stretch;
     display: flex;
     align-items: center;
     padding: 0px 20px;
     
}
.txt {
    overflow-y: auto;
     width: 100%;
     height: 100%;
     padding: 5px;
}

/*버튼*/
.btns{
     width: 100%;
     height: 10%;
     display: flex;
     align-items: center;
     justify-content: flex-end; 

	 background-color:pink;
     font-size: 18px;
     color: #fff;
     font-family: Pretendard;
    
     gap: 25px;
}
button{
     height: 48px;
     width: 200px;
     border-radius: 22px;
     display: flex;
     align-items: center;
     justify-content: center;
     padding: 16px;
     box-sizing: border-box;
}
.btn1{ /*파랑*/
    background-color: #0090ff;
}
.btn2{/*주황*/
    color: #ed7833;
    background-color: #ffceb1;
}
.btn3{/*회색*/
    background-color: #d9d9d9;
}

/*수정영역*/
/* ApprovalDetail.module.css */
.txtActive {
  border: 2px solid #0090ff;
  /* background-color: #f0f8ff; */
  border-radius: 6px;
}

.div3Active {
  border: 2px solid #0090ff;
  /* background-color: #f3f7fa; */
  border-radius: 6px;
}










 @media (max-width: 1350px) {
.div6{
    font-size: 16px;
}
.div6_left {
  flex: 1;
}
.div6_center {
  flex: 1.2;
}
.div6_right {
  flex: 7.8;
}
}

 @media (max-width: 1115px) {
.div6{
    font-size: 16px;
}
.div6_left {
  flex: 1;
}
.div6_center {
  flex: 2;
}
.div6_right {
  flex: 7;
}
.documentIcon {
     width: 16px;
     height: 16px;
     margin :auto;
}
}

 @media (max-width: 860px) {
  #unseen{
    display: none;
  }
  .div6_center {
  flex: 2.5;
}
.div6_right {
  flex: 7.5;
}
}

 @media (max-width: 768px) {
  .div6_center {
  flex: 2;
}
.div6_right {
  flex: 8;
}
}


 @media (max-width: 545px) {
  .div6_center {
  flex: 3;
}
.div6_right {
  flex: 7;
}
.documentIcon {
     width: 10px;
     height: 10px;
     margin :auto;
}
}

 @media (max-width: 415px) {
  .div6_center {
  flex: 3.5;
}
.div6_right {
  flex: 6.5;
}
.documentIcon {
     width: 10px;
     height: 10px;
     margin :auto;
}
}


</style>


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
		                            파일영역
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