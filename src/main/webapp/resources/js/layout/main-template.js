document.addEventListener("DOMContentLoaded", function(){
    // ----------------------------------------------------------------
    // 1. 메뉴 아이콘 활성화 로직
    // ----------------------------------------------------------------
    const menuItems = document.querySelectorAll(".menu-item");

    menuItems.forEach(function(item){
        const icon = item.querySelector("img");
        const parts = icon.src.split("/");
        const filenameWithExt = parts[parts.length - 1];
        const baseFilename = filenameWithExt
            .replace("-active.svg", "")
            .replace(".svg", "");

        item.addEventListener("click", function(){
            menuItems.forEach(function(i){
                i.classList.remove("active");
                const iIcon = i.querySelector("img");
                const iParts = iIcon.src.split("/");
                const iFilenameWithExt = iParts[iParts.length - 1];
                const iBase = iFilenameWithExt
                    .replace("-active.svg", "")
                    .replace(".svg", "");
                iIcon.src = "/resources/imgs/sideBar/" + iBase + ".svg";
            });

            item.classList.add("active");
            icon.src = "/resources/imgs/sideBar/" + baseFilename + "-active.svg";
        });

        item.addEventListener("mouseenter", function(){
            if (!item.classList.contains("active")) {
                icon.src = "/resources/imgs/sideBar/" + baseFilename + "-active.svg";
            }
        });

        item.addEventListener("mouseleave", function(){
            if (!item.classList.contains("active")) {
                icon.src = "/resources/imgs/sideBar/" + baseFilename + ".svg";
            }
        });
    });

    // ----------------------------------------------------------------
    // 2. 반응형 햄버거 토글 로직
    // ----------------------------------------------------------------
    const menuToggle = document.getElementById('menuToggle');
    const sidebar = document.getElementById('sidebar');
    const overlay = document.getElementById('overlay'); // id='overlay' 확인
    const minDesktopWidth = 768;

    function toggleSidebar() {
        if (!sidebar || !overlay) {
            console.error("Sidebar or Overlay element not found. Check if sideBar.jsp has id='sidebar'.");
            return;
        }
        sidebar.classList.toggle('sidebar-active');
        overlay.classList.toggle('active');
    }

    if (menuToggle) {
        menuToggle.addEventListener('click', function(e) {
            e.stopPropagation(); // 클릭 이벤트 전파 방지
            console.log("햄버거 버튼 클릭됨"); // 로그 추가
            toggleSidebar();
        });
    }

    if (overlay) {
        overlay.addEventListener('click', function() {
            if (sidebar.classList.contains('sidebar-active')) {
                toggleSidebar();
            }
        });
    }

   let resizeTimeout;

	function handleResize() {
	    clearTimeout(resizeTimeout);
	    resizeTimeout = setTimeout(() => {
	        if (window.innerWidth >= minDesktopWidth) {
	            if (sidebar) sidebar.classList.remove('sidebar-active');
	            if (overlay) overlay.classList.remove('active');
	        }
	    }, 150); // 150ms 정도 딜레이
	}

    window.addEventListener('resize', handleResize);
    handleResize();
});
