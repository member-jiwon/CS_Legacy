document.addEventListener("DOMContentLoaded", function(){
    const menuItems = document.querySelectorAll(".menu-item");

    menuItems.forEach(function(item){
        const icon = item.querySelector("img");
        const baseFilename = icon.src.split("/").pop().replace("-active.svg","").replace(".svg","");

        item.addEventListener("click", function(e){
            e.preventDefault(); // 클릭 시 페이지 새로고침 방지

            // 메뉴 활성화 토글
            menuItems.forEach(function(i){
                i.classList.remove("active");
                const iIcon = i.querySelector("img");
                const iBase = iIcon.src.split("/").pop().replace("-active.svg","").replace(".svg","");
                iIcon.src = "/resources/imgs/sideBar/" + iBase + ".svg";
            });

            item.classList.add("active");
            icon.src = "/resources/imgs/sideBar/" + baseFilename + "-active.svg";

            // mainContent에 해당 URL 로드 (AJAX)
            const url = item.getAttribute("href");
            if(url){
                const mainContent = document.getElementById("mainContent");
                if(mainContent){
                    fetch(url)
                        .then(response => response.text())
                        .then(html => mainContent.innerHTML = html)
                        .catch(err => console.error("컨텐츠 로드 실패:", err));
                }
            }
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
});
