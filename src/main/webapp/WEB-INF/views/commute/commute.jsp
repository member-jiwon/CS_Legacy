<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>



<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
	rel="stylesheet" /><!-- 부트스트랩 -->
<link rel="stylesheet" href="<c:url value='/resources/css/commute.css'/>"><!--css파일 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script><!--Jquery-->

<script src="https://cdn.jsdelivr.net/npm/plotly.js-dist-min@3.1.2/plotly.min.js"></script><!-- plotly.js CDN -->
<script src="<c:url value='/resources/js/commute.js'/>"></script>

</head>
<body>

<!-- 탑바 -->
   <header class="header d-flex align-items-center">
      <jsp:include page="/WEB-INF/views/common/sideBar/topBar.jsp" />
   </header>
   
<!-- 탑바 제외 영역 -->
   <div class="container-fluid body-wrapper g-0">
      <div class="row g-0">
      
<!-- 사이드바 -->      
         <div class="col-2 px-0">
            <div class="sidebarbox">
               <jsp:include page="/WEB-INF/views/common/sideBar/sideBar.jsp" />
            </div>
         </div>
         
<!-- 서브 사이드바 -->          
         <div class="col-2 px-0">
			  <div class="subSidebar" id="subSidebar">
			      
			    <form method="get" action="/commute" id="commuteForm">
				  <!-- 날짜 -->
				  <div class="dateBox">
				    <label for="startDate">시작</label><br>
				    
				    <input type="date" id="startDate" name="startDate"
				           value="${startDate}"
				           min="${oneYearAgo}" max="${today}"
				           class="form-control mb-2">
				    <br>
				    <label for="endDate">종료</label><br>
				    <input type="date" id="endDate" name="endDate"
				           value="${endDate}"
				           min="${oneYearAgo}" max="${today}"
				           class="form-control mb-3">
				  </div>
				
				  <!-- 전체 -->
				  <div class="tapWrapper">
				    <button type="submit" class="tap" name="type" value="전체">전체</button>
				  </div>
				
				  <!-- 팀원 -->
				  <div class="tapWrapper">
				    <button type="button" class="tap team-btn">팀별</button>
				    <div id="teamListContainer">
				    	<!-- 여기에 팀 목록이 동적으로 들어감 -->
				    </div> 
				  </div>
				</form>
				
			  </div>
			</div>
              
<!-- 사이드바 제외 우측 영역 -->
         <div class="col-8 px-0 content">         
			<div class="dashboard">
			
			
			  <div class="left">
			    <div class="box">
			    	<h6>근태관리</h6>
            		<div id="attendancePlot" class="donut-container"></div>
			    </div>
			    
			    <div class="box">
			    	<h6>출근관리</h6>
            		<div id="workPlot" class="donut-container"></div>
			    </div>
			  </div>
			  
			  
			  <div class="right">
			    <div class="box">
			    	<h6 id="margin-h6">전자결재 진행</h6>
            		<div id="approvalPlot"></div>
			    </div>
			  </div>
			</div>
         </div>      
      
      </div>
   </div>


<script>


$(document).ready(function () {
// 팀별 버튼 클릭
$(".team-btn").on("click", function() {
  const $container = $("#teamListContainer");

  // 이미 열려 있으면 닫기
  if ($container.children().length > 0) {
    $container.empty();
    return;
  }

  $.get("<c:url value='/commute/teamList'/>")
    .then(function(list) {
      console.log(list);

      if (list && list.length > 0) {
        const $divlist = $("<div>").addClass("teamListBox list-group");

        
        list.forEach(dept => {
          const $button = $("<button>")
            .addClass("list-group-item team-item").attr({type:"submit", name:"type", value :dept.dept_name})
            .text(dept.dept_name);
          $divlist.append($button);
        });

        $container.empty().append($divlist).hide().fadeIn(200);
      } else {
        $container.html("<p>등록된 팀이 없습니다.</p>");
      }
    })
    .catch(function() {
      alert("팀 목록을 불러오지 못했습니다.");
    });
});
	
	
	
	  const $form = $("#commuteForm");
	  const $start = $("#startDate");
	  const $end = $("#endDate");

	  // 오늘 / 1년 전
	  const today = new Date().toISOString().split("T")[0];
	  const oneYearAgo = new Date(new Date().setFullYear(new Date().getFullYear() - 1)).toISOString().split("T")[0];

	  // 기본 min/max 설정
	  $start.attr({ min: oneYearAgo, max: today });
	  $end.attr({ min: oneYearAgo, max: today });

	  // 이전 값 저장용 변수
	  let prevStart = $start.val();
	  let prevEnd = $end.val();
	  
	  // 날짜 보내는 함수
	  function handleDateChange(e) {
	    const startVal = $start.val();
	    const endVal = $end.val();

	    // 시작 > 종료 → 경고 후 원복
	    if (startVal && endVal && startVal > endVal) {
	      alert("시작일은 종료일보다 앞서야 합니다.");

	      // 변경한 input만 이전 값으로 복원
	      if (e.target.id === "startDate") $start.val(prevStart);
	      if (e.target.id === "endDate") $end.val(prevEnd);
	      return;
	    }

	    // 정상 범위일 때만 이전 값 갱신 + 전송
	    prevStart = startVal;
	    prevEnd = endVal;

	    if (startVal || endVal) $form.submit();
	  }

	  // 이벤트 등록
	  $start.on("change", handleDateChange);
	  $end.on("change", handleDateChange);
	});








const attendanceStats = ${attendanceStatsJson};
const workStats = ${workStatsJson};
const approvalStats = ${approvalStatsJson};
console.log(attendanceStats);
console.log(workStats);
console.log(approvalStats);


//1. 근태관리 (도넛차트)
const aLabels = Object.keys(attendanceStats);
const aValues = Object.values(attendanceStats);
const data1 = [{
	  values: aValues,
	  labels: aLabels,
	  type: 'pie',
	  hole: 0.6,
	  textinfo: 'none',
	  marker: { colors: ['#3B82F6','#C084FC','#FDBA74'] },
	  scalegroup: 'donutGroup',            // ✅ 여기!
	  domain: { x: [0, 1], y: [0, 1] }  // ✅ 여기!
	}];

	const layout1 = {
	  height: 280, width: 420,
	  margin: { t:0, b:0, l:30, r:30 },
	  showlegend: true,
	  legend: { x: 1.1, y: 0.5, font: { size: 14 } }
	};

	Plotly.newPlot('attendancePlot', data1, layout1, {responsive: true});

//2. 출근관리 (도넛차트)
const wLabels = Object.keys(workStats);
const wValues = Object.values(workStats);
const data2 = [{
	  values: wValues,
	  labels: wLabels,
	  type: 'pie',
	  hole: 0.6,
	  textinfo: 'none',
	  marker: { colors: ['#FF6384','#FDBA74'] },
	  scalegroup: 'donutGroup',            // ✅ 동일 그룹
	  domain: { x: [0, 1], y: [0, 1] }  // ✅ 동일 도메인
	}];

	const layout2 = {
	  height: 280, width: 420,
	  margin: { t:0, b:0, l:30, r:30 },
	  showlegend: true,
	  legend: { x: 1.1, y: 0.5, font: { size: 14 } }
	};

	Plotly.newPlot('workPlot', data2, layout2, {responsive: true});



//3. 전자결재 (막대그래프)
const labels = Object.keys(approvalStats);
const values = Object.values(approvalStats);

const data3 = [{
  x: labels,
  y: values,
  type: 'bar',
  marker: { color: ['#3B82F6', '#3B82F6', '#3B82F6'] }
}];
const layout3 = {
		  autosize: true,
		  height: 560,
		  margin: { t: 30, b: 50, l: 40, r: 20 },

		  // 하얀 배경 + 회색 줄무늬
		  plot_bgcolor: '#ffffff',
		  paper_bgcolor: '#ffffff',

		  // X축 설정
		  xaxis: {
		    tickfont: { size: 13 },
		    showgrid: false,
		    zeroline: false,
		    linecolor: '#000000', // X축 라인 색상 (검정)
		    linewidth: 1,       // 두께
		    mirror: false         // 아래쪽만 표시
		  },

		  // Y축 설정
		  yaxis: {
		    showgrid: true,       // 가로 줄무늬
		    gridcolor: '#e5e5e5', // 줄 색상
		    gridwidth: 1,
		    zeroline: false,
		    linecolor: '#000000', // Y축 라인 색상 (검정)
		    linewidth: 1,       // 두께
		    mirror: false         // 왼쪽만 표시
		  },

		  showlegend: false,
		  bargap: 0.35
		};

		// ✅ 반응형 옵션
		Plotly.newPlot('approvalPlot', data3, layout3, { responsive: true });



</script>




</body>
</html>