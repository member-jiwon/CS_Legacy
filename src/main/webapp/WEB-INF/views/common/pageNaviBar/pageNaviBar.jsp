<%@ page contentType="text/html;charset=UTF-8" %>
<%
  // 파라미터 수신
  String action = request.getParameter("action");
  int totalCount = Integer.parseInt(request.getParameter("recordTotalCount"));
  int recordPerPage = Integer.parseInt(request.getParameter("recordCountPerPage"));
  int naviPerPage = Integer.parseInt(request.getParameter("naviCountPerPage")); // 최대 버튼 수
  int currentPage = Integer.parseInt(request.getParameter("currentPage"));
  String status = request.getParameter("status");
  String departmentType = request.getParameter("departmentType");

  // 페이지 계산
  int totalPage = (int) Math.ceil((double) totalCount / recordPerPage);
  int group = (currentPage - 1) / naviPerPage;
  int startNavi = group * naviPerPage + 1;
  int endNavi = Math.min(startNavi + naviPerPage - 1, totalPage);
%>

<div class="pageNaviContainer">
  <form method="get" action="<%= action %>">
    <input type="hidden" name="status" value="<%= status %>">
    <input type="hidden" name="departmentType" value="<%= departmentType %>">

    <nav class="paginationParent">
      <!-- << 첫 페이지 -->
      <button type="submit" name="cpage" value="1"
        class="paginationPage <%= currentPage == 1 ? "disabled" : "" %>">
        <img src="/icon/doubleLeftArrow.svg" alt="처음">
      </button>

      <!-- < 이전 그룹 -->
      <button type="submit" name="cpage" value="<%= Math.max(1, startNavi - 1) %>"
        class="paginationPage <%= startNavi == 1 ? "disabled" : "" %>">
        <img src="/icon/leftArrow.svg" alt="이전">
      </button>

      <!-- 페이지 번호 -->
      <% for (int i = startNavi; i <= endNavi; i++) { %>
        <button type="submit" name="cpage" value="<%= i %>"
          class="<%= i == currentPage ? "paginationPageActive" : "paginationPage" %>">
          <span class="pageNumber"><%= i %></span>
        </button>
      <% } %>

      <!-- > 다음 그룹 -->
      <button type="submit" name="cpage" value="<%= Math.min(totalPage, endNavi + 1) %>"
        class="paginationPage <%= endNavi == totalPage ? "disabled" : "" %>">
        <img src="/icon/rightArrow.svg" alt="다음">
      </button>

      <!-- >> 마지막 페이지 -->
      <button type="submit" name="cpage" value="<%= totalPage %>"
        class="paginationPage <%= currentPage == totalPage ? "disabled" : "" %>">
        <img src="/icon/doubleRightArrow.svg" alt="끝">
      </button>
    </nav>
  </form>
</div>