document.addEventListener("DOMContentLoaded", function () {
    const menuItems = document.querySelectorAll(".menu-item");
    const currentPath = window.location.pathname; // 현재 URL 경로 (예: /notice/post)

    menuItems.forEach(function (item) {
        const icon = item.querySelector("img"); // 메뉴 아이콘 이미지
        const href = item.getAttribute("href"); // 메뉴 링크

        // 아이콘 파일 이름 추출 (예: Popup.svg → Popup)
        const baseFilename = icon.src
            .split("/").pop()              // 파일명만 가져오기
            .replace("-active.svg", "")    // -active 제거
            .replace(".svg", "");          // .svg 제거

        // ---------------------------
        // URL 비교 로직
        // /notice/list, /notice/post, /notice/view 등
        // 모두 /notice로 시작하면 게시판 메뉴 활성화
        // ---------------------------
        if (href && currentPath.startsWith("/" + href.split("/")[1])) {
            item.classList.add("active"); // 메뉴 활성화 클래스 추가
            icon.src = `/resources/imgs/sideBar/${baseFilename}-active.svg`; // 활성화 아이콘으로 변경
        } else {
            item.classList.remove("active"); // 활성화 클래스 제거
            icon.src = `/resources/imgs/sideBar/${baseFilename}.svg`; // 기본 아이콘으로 변경
        }

        // ---------------------------
        // 마우스 오버 시 임시 active 효과
        // ---------------------------
        item.addEventListener("mouseenter", function () {
            if (!item.classList.contains("active")) {
                icon.src = `/resources/imgs/sideBar/${baseFilename}-active.svg`;
            }
        });

        // ---------------------------
        // 마우스 떠날 시 원래 상태로 복원
        // ---------------------------
        item.addEventListener("mouseleave", function () {
            if (!item.classList.contains("active")) {
                icon.src = `/resources/imgs/sideBar/${baseFilename}.svg`;
            }
        });
    });
});
