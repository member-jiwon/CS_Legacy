<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <!DOCTYPE html>
      <html>

      <head>
         <meta charset="UTF-8">
         <title>Insert title here</title>
         

         
         <link rel="stylesheet" href="<c:url value='/resources/css/common/pageNaviBar/pageNaviBar.css'/>"> <!--페이지 네비 css 파일-->
         
         
         <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script> <!-- jqeruy -->
         <script src="https://cdn.jsdelivr.net/npm/dayjs@1/dayjs.min.js"></script><!-- dayjs라이브러리 -->
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
            crossorigin="anonymous"><!-- 부트스트랩 -->
         
         <link rel="stylesheet" href="<c:url value='/resources/css/approval/approval.css'/>"><!--css 파일-->

      </head>

<body>


<!-- 탑바 -->
   <header class="header d-flex align-items-center">
      <jsp:include page="/WEB-INF/views/common/sideBar/topBar.jsp" />
   </header>


<!-- 탑바 제외 영역 -->
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
               
               
               <!-- 1층 헤더 -->
               <div class="board-header">
                  <h2 class="board-title">전자결재</h2>
                      <form id="filterForm" method="get" action="/approval" class="d-flex gap-2">
                      <!-- 사용자 선택용 -->
                          <select id="approvalStatusSelect" class="form-select">
                                <option value="all"
                                  <c:if test="${selectedStatus eq 'all'}">selected</c:if>>전체</option>
                                <option value="w"
                                  <c:if test="${selectedStatus eq 'w'}">selected</c:if>>대기</option>
                                <option value="y"
                                  <c:if test="${selectedStatus eq 'y'}">selected</c:if>>승인</option>
                                <option value="n"
                                  <c:if test="${selectedStatus eq 'n'}">selected</c:if>>반려</option>
                          </select>
                           
                          <select id="departmentTypeSelect" class="form-select">
                          <!-- 부서 동적으로 붙이기 -->
                          </select>
                           
                        <!-- 실제 제출용 -->
                        <input type="hidden" id="approvalStatus" name="status" value="${selectedStatus}" />
                        <input type="hidden" id="departmentType" name="departmentType" value="${selectedDept}" />
                     </form>
               </div>


               <!-- 2층 바디 영역-->
               <div class="board-table">
               
               	<!-- 보드 테이블의 헤더 -->
                  <div class="board-table-header">
                     <div class="col-num">번호</div>
                     <div class="col-title">제목</div>
                     <div class="col-name">이름</div>
                     <div class="col-dept">부서</div>
                     <div class="col-level">직급</div>
                     <div class="col-date">날짜</div>
                     <div class="col-status">결재상황</div>
                  </div>

                     <div class="board-table-box">
                        <!-- 반복문 돌려서 추가시키는 영역 -->
               		</div>


               <!-- footer - 페이지 네비게이션 -->
               <div class="pagination">
                       <jsp:include page="/WEB-INF/views/common/pageNaviBar/pageNaviBar.jsp">
                            <jsp:param name="action" value="/approval" />
                             <jsp:param name="recordTotalCount" value="${recordTotalCount}" />
                             <jsp:param name="recordCountPerPage" value="${recordCountPerPage}" />
                             <jsp:param name="naviCountPerPage" value="5" />
                             <jsp:param name="currentPage" value="${currentPage}" />
                             <jsp:param name="status" value="${selectedStatus}" />
                             <jsp:param name="departmentType" value="${selectedDept}" />
                        </jsp:include>
               </div>
            </div>

         </div>


      </div>
   </div>


<script>
let selectedDept = "${selectedDept}";


const statuses = [
    { value: "w", label: "대기" },
    { value: "y", label: "승인" },
    { value: "n", label: "반려" },
  ];
const statusMap = { w: "대기", y: "승인", n: "반려"};  
  
  // 0.화면 뒤로가기 햇을떄 새로고침 시키기 (db내용 반영시키기 위해서)
    window.onpageshow = function (event) {
    if (event.persisted) {
      location.reload();
    }
  };
  
  // 1. 화면 렌더링시 기본으로 append시키기
  $(document).ready(function () {
       console.log("선택된 부서",selectedDept)
       let list = JSON.parse('${list}');
       console.log(list);
       
     //1-1. 드롭다운 옵션 불러오기
       let depts = JSON.parse('${depts}');
        console.log(depts);
       $("#departmentTypeSelect").append(createDepartmentOption({ dept_code: "all", dept_name: "전체부서" }, true));
       depts.forEach((item)=>{$("#departmentTypeSelect").append(createDepartmentOption(item))})
       
       console.log(list);
       list.forEach((row) => $(".board-table-box").append(createApprovalRow(row))); //기본값으로 가져다 놓기
       
       $("#approvalStatusSelect").val("${selectedStatus}");
       $("#departmentTypeSelect").val("${selectedDept}");
       
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
	  const line = $("<div>").addClass("board-table-row");
	  const seq = $("<div>").addClass("col-num").text(row.APPROVAL_SEQ);
	  const title = $("<div>").addClass("col-title");
	  const a = $("<a>")
	    .attr("href", "/approval/detail?seq=" + row.APPROVAL_SEQ)
	    .text(row.APPROVAL_TITLE);
	  title.append(a);
	
	  const name = $("<div>").addClass("col-name").text(row.MEMBER_NAME);
	  const dept = $("<div>").addClass("col-dept").text(row.DEPT_NAME);
	  const level = $("<div>").addClass("col-level").text(row.LEVEL_NAME);
	  const date = $("<div>").addClass("col-date").text(dayjs(row.APPROVAL_AT).format("YYYY-MM-DD"));
	
	  // 상태 버튼 묶음
	  const statusDiv = $("<div>").addClass("col-status");
	  const btnwrapper = $("<div>").addClass("status-btn-group");
	
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
    const targetseq = $(this).closest(".board-table-row").find(".col-num").text();
	
    $.ajax({
      url: "/approval/updatestatus",
      type: "post",
      data: { targetseq, newStatus },
      success: function () {
    	alert(statusMap[newStatus] + "상태로 변경되었습니다.");
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