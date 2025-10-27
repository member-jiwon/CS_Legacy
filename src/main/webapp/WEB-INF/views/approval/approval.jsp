<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <!DOCTYPE html>
      <html>

      <head>
         <meta charset="UTF-8">
         <title>Insert title here</title>
         

         <link rel="stylesheet" href="<c:url value='/resources/css/approval/approval.css'/>"><!--css 파일-->
         <link rel="stylesheet" href="<c:url value='/resources/css/common/pageNaviBar/pageNaviBar.css'/>"> <!--페이지 네비 css 파일-->
         
         
         <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script> <!-- jqeruy -->
         <script src="https://cdn.jsdelivr.net/npm/dayjs@1/dayjs.min.js"></script><!-- dayjs라이브러리 -->
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
            crossorigin="anonymous"><!-- 부트스트랩 -->

      </head>

<body>
    <div class="container-fluid g-0">
        <div class="row">
            <div class="navi col-2">
                <div class="navibar">네비바 영역</div>
            </div>

            <div class="contentbox col-10">
                <div class="content row g-0">
                    <div class="firstFloor col-12">
                        <div class="row g-0">
                            <div class="type col-9">
                                전자결재
                            </div>
                            
                            <div class="dropDownBox col-3">
                       <form id="filterForm" method="get" action="/approval" class="d-flex gap-2">
                         <!-- 사용자 선택용 -->
                         <select id="approvalStatusSelect" class="form-select">
                          <option value="all"
                            <c:if test="${selectedStatus eq 'all'}">selected</c:if>>전체결재</option>
                          <option value="w"
                            <c:if test="${selectedStatus eq 'w'}">selected</c:if>>대기중</option>
                          <option value="y"
                            <c:if test="${selectedStatus eq 'y'}">selected</c:if>>완료</option>
                          <option value="n"
                            <c:if test="${selectedStatus eq 'n'}">selected</c:if>>반려</option>
                        </select>
                     
                         <select id="departmentTypeSelect" class="form-select">
                           <!-- 부서 동적으로 붙이기 -->
                         </select>
                     
                         <!-- 실제 제출용 -->
                           <input type="hidden" id="approvalStatus" name="approvalStatus" value="${selectedStatus}" />
                           <input type="hidden" id="departmentType" name="departmentType" value="${selectedDept}" />
                       </form>
                     </div>
                            
                            
                        </div>
                    </div>
                    <div class="secondFloor col-12">
                        <div class="row">
                            <div class="col-1 seq">번호</div>
                            <div class="col-4 title">제목</div>
                            <div class="col-1 name">이름</div>
                            <div class="col-1 dept">부서</div>
                            <div class="col-1 level">직급</div>
                            <div class="col-1 date">날짜</div>
                            <div class="col-3 status">결재 상황</div>
                        </div>

                    </div>
                    <div class="thirdFloor col-12">
                        <!-- <div class="line">동적으로 여기다가 붙여야함</div> -->
                    </div>
                    <div class="fourthFloor col-12">
                        <jsp:include page="/WEB-INF/views/common/pageNaviBar/pageNaviBar.jsp">
                         <jsp:param name="action" value="/approval" />
                          <jsp:param name="recordTotalCount" value="${recordTotalCount}" />
                          <jsp:param name="recordCountPerPage" value="${recordCountPerPage}" />
                          <jsp:param name="naviCountPerPage" value="5" />
                          <jsp:param name="currentPage" value="${currentPage}" />
                          <jsp:param name="approvalStatus" value="${selectedStatus}" />
                          <jsp:param name="departmentType" value="${selectedDept}" />
                     </jsp:include>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script>
let selectedDept = "${selectedDept}";


  // 1. 화면 렌더링시 기본으로 append시키기
  $(document).ready(function () {
       console.log("선택된 부서",selectedDept)
       let list = JSON.parse('${list}');
       
     //1-1. 드롭다운 옵션 불러오기
       let depts = JSON.parse('${depts}');
        console.log(depts);
       $("#departmentTypeSelect").append(
              createDepartmentOption({ dept_code: "all", dept_name: "전체부서" }, true));
       depts.forEach((item)=>{$("#departmentTypeSelect").append(createDepartmentOption(item))})
       
       console.log(list);
       list.forEach((row) => $(".thirdFloor").append(createApprovalRow(row))); //기본값으로 가져다 놓기
      
       //폼태그의 인풋안에 밸류값 넣어주기
       $("#approvalStatusSelect, #departmentTypeSelect").on("change", function () {
            $("#approvalStatus").val($("#approvalStatusSelect").val());
            $("#departmentType").val($("#departmentTypeSelect").val());
            $("#filterForm").submit();
          });
  });

        //1-1. 드롭다운 옵션 추가하기 함수
      function createDepartmentOption(item, isAllOption = false) {
        const option = $("<option>")
          .val(item.dept_code)
          .text(item.dept_name);   
        // "전체부서" 옵션일 경우
        if (isAllOption && selectedDept === "all") {
          option.prop("selected", true);
        }
      
        // 일반 부서 옵션일 경우
        if (!isAllOption && item.dept_code === selectedDept) {
          option.prop("selected", true);
        }
      
        return option;
      }


  
  //2.리스트 구성하는 페이지
	function createApprovalRow(row) {
	  const line = $("<div>").addClass("row g-0 line");
	
	  const seq = $("<div>").addClass("col-1 targetseq").text(row.APPROVAL_SEQ);
	  const title = $("<div>").addClass("col-4");
	  const a = $("<a>")
	    .attr("href", "/approval/detail?seq=" + row.APPROVAL_SEQ)
	    .text(row.APPROVAL_TITLE);
	  title.append(a);
	
	  const name = $("<div>").addClass("col-1").text(row.MEMBER_NAME);
	  const dept = $("<div>").addClass("col-1").text(row.DEPT_CODE);
	  const level = $("<div>").addClass("col-1").text(row.LEVEL_CODE);
	  const date = $("<div>").addClass("col-1").text(dayjs(row.APPROVAL_AT).format("YYYY-MM-DD"));
	
	  // 상태 버튼 묶음
	  const statusDiv = $("<div>").addClass("col-3");
	  const btnwrapper = $("<div>").addClass("status-btn-group");
	
	  const statuses = [
	    { value: "w", label: "대기" },
	    { value: "y", label: "승인" },
	    { value: "n", label: "반려" },
	  ];
	
	  statuses.forEach(s => {
	    const btn = $("<button>")
	      .addClass("status-btn")
	      .attr("data-value", s.value)
	      .text(s.label);
	
	    // ✅ 현재 상태일 경우 활성 표시
	    if (row.APPROVAL_STATUS === s.value) {
	      btn.addClass("active");
	    }
	
	    // ✅ 승인(y) 또는 반려(n) 상태라면 비활성화
	    if (row.APPROVAL_STATUS === "y" || row.APPROVAL_STATUS === "n") {
	      btn.prop("disabled", true); // 버튼 비활성화
	      btn.addClass("disabled-btn"); // (선택) 스타일 추가용
	    }
	
	    btnwrapper.append(btn);
	  });
	
	  statusDiv.append(btnwrapper);
	  line.append(seq, title, name, dept, level, date, statusDiv);
	  return line;
	}


  // 상태 변경 AJAX
  $(document).on("click", ".status-btn", function () {
    const newStatus = $(this).data("value");
    const targetseq = $(this).closest(".line").find(".col-1:first").text();

    $.ajax({
      url: "/approval/updatestatus",
      type: "post",
      data: { targetseq, newStatus },
      success: function () {
        alert("상태가 변경되었습니다.");
        location.reload();
      },
      error: function () {
        alert("업데이트 실패");
      }
    });
  });
</script>

</body>

</html>