$(document).ready(function() {
    const $deptPostModal = $('#deptPostModal');
    const $deptPostModalBody = $deptPostModal.find('.modal-content');
    const deptPostUrl = contextPath + '/department/post';

    $deptPostModal.on('show.bs.modal', function () {
        // 부서 추가 모달을 열 때마다 최신 HTML 불러오기
        $.ajax({
            url: deptPostUrl,
            type: 'GET',
            success: function(htmlData) {
                $deptPostModalBody.html(htmlData);

                // 스크립트 중복 로드 방지
                if (!window.deptPostLoaded) {
                    $.getScript(contextPath + '/resources/js/department/departmentPost.js', function() {
                        window.deptPostLoaded = true;
                    });
                }
            },
            error: function(xhr, status, error) {
                console.error("부서 추가 모달 로드 실패:", error);
                $deptPostModalBody.html('<p class="text-danger">화면 로드 중 오류가 발생했습니다.</p>');
            }
        });
    });
});
