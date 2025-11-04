$(document).ready(function() {

    const $deptPostModal = $('#deptPostModal');
    const addDeptUrl = contextPath + '/department/add';
    const deleteDeptUrl = contextPath + '/department/delete';
    const reloadUrl = contextPath + '/department/post';

    let tempDeptList = [];

    // ----------------------------------------------------
    // 부서 목록 새로고침
    // ----------------------------------------------------
    function reloadModalContent() {
        $.ajax({
            url: reloadUrl,
            type: 'GET',
            success: function(htmlData) {
                const newContent = $(htmlData).find('.dept-post-content-wrapper');
                $deptPostModal.find('.modal-content').html(newContent);
                tempDeptList = [];
                bindEvents(); // 이벤트 다시 연결
            },
            error: function(xhr, status, error) {
                console.error("부서 목록 재로드 실패:", error);
                alert('목록 업데이트 중 오류가 발생했습니다.');
            }
        });
    }

    // ----------------------------------------------------
    // 이벤트 바인딩
    // ----------------------------------------------------
    function bindEvents() {
        const $deptPostForm = $('#deptPostForm');
        const $inputField = $('#newDeptName');
        const $addBtn = $('.dept-post-add-btn');
        const $deptListGroup = $('.dept-post-list-item-group');

        // 중복 등록 방지
        $addBtn.off('click');
        $deptListGroup.off('click', '.dept-post-item-delete-icon');
        $deptPostForm.off('submit');
        $inputField.off('input');

        // 입력 감지 → 버튼 활성화
        $inputField.on('input', function() {
            const val = $(this).val().trim();
            const active = val.length > 0;
            $addBtn
                .toggleClass('dept-post-btn--inactive', !active)
                .prop('disabled', !active)
                .css('background-color', active ? '#0090ff' : '#d9d9d9');
        }).trigger('input');

        // 부서 임시 추가
        $addBtn.on('click', function() {
            const dept_name = $inputField.val().trim();
            if (!dept_name) return;

            // 중복 체크
            const isDuplicate = $deptListGroup.find('.dept-post-list-item').filter(function() {
                return $(this).find('.dept-post-item-name').text() === dept_name;
            }).length > 0;

            if (isDuplicate) {
                alert('이미 존재하는 부서명입니다.');
                return;
            }

            // 새 임시 항목 추가
            const tempId = Date.now();
            const $newItem = $(`
                <div class="dept-post-list-item" data-dept-id="${tempId}" data-temp="true">
                    <span class="dept-post-item-name">${dept_name}</span>
                    <img class="dept-post-item-delete-icon"
                        src="${contextPath}/resources/imgs/department/X.svg" alt="삭제">
                </div>
            `);
            $deptListGroup.prepend($newItem);
            tempDeptList.push({ name: dept_name, tempId });
            $inputField.val('').trigger('input');
            $deptListGroup.scrollTop(0);
        });

		// 부서 삭제
		$deptListGroup.on('click', '.dept-post-item-delete-icon', function() {
		    const $item = $(this).closest('.dept-post-list-item');
		    const dept_code = $item.data('dept-id');
		    const dept_name = $item.find('.dept-post-item-name').text();
		    const isTemp = $item.data('temp');
		
		    if (!confirm(`'${dept_name}' 부서를 삭제하시겠습니까?`)) return;
		
		    // 임시 부서 삭제
		    if (isTemp) {
		        $item.addClass('deleted');
		        $item.find('.dept-post-item-name').text(`${dept_name} (삭제됨)`);
		        $item.find('.dept-post-item-delete-icon').remove();
		        tempDeptList = tempDeptList.filter(d => d.tempId !== dept_code);
		        return;
		    }
		
		    // DB 삭제
		    $.ajax({
		        url: deleteDeptUrl,
		        type: 'POST',
		        data: { dept_code },
		        success: function(response) {
		            if (response === 'success') {
		                // ✅ 삭제된 항목 표시만 변경 (리스트 유지)
		                $item.addClass('deleted');
		                $item.find('.dept-post-item-name').text(`${dept_name} (삭제됨)`);
		                $item.find('.dept-post-item-delete-icon').remove();
		            } else {
		                alert('부서 삭제에 실패했습니다.');
		            }
		        },
		        error: function() {
		            alert('서버 오류로 삭제에 실패했습니다.');
		        }
		    });
		});

        // 완료 버튼 클릭
        $deptPostForm.on('submit', function(e) {
            e.preventDefault();

            if (tempDeptList.length === 0) {
                alert('변경 사항이 적용되었습니다.');
                $deptPostModal.modal('hide');
                window.location.reload();
                return;
            }

            let successCount = 0;
            let completed = 0;
            const total = tempDeptList.length;

            tempDeptList.forEach(dept => {
                $.ajax({
                    url: addDeptUrl,
                    type: 'POST',
                    data: { dept_name: dept.name },
                    success: function(response) {
                        if (response === 'success') successCount++;
                        else if (response === 'duplicate')
                            alert(`'${dept.name}' 부서는 이미 존재합니다.`);
                    },
                    complete: function() {
                        completed++;
                        if (completed === total) {
                            alert(`총 ${total}개 중 ${successCount}개 부서 등록 완료.`);
                            $deptPostModal.modal('hide');
                            window.location.reload();
                        }
                    }
                });
            });
        });
    }

    // 초기 실행
    bindEvents();
});
