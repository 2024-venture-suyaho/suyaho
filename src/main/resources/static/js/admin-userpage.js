document.addEventListener('DOMContentLoaded', function() {
    // 사용자 번호를 가져오기
    const userNo = document.getElementById('userNo').value;

    // 페이지 로드 시 사용자의 게시글을 가져오기
    fetchTradesByUserNo(userNo);

    // 프로필 사진 변경 버튼 클릭 이벤트
    document.getElementById('changePicBtn').addEventListener('click', function() {
        document.getElementById('fileInput').click();
    });

    const backButton = document.getElementById("backButton");
    if (backButton) {
        backButton.addEventListener("click", function() {
            window.history.back();
        });
    }

    document.getElementById('fileInput').addEventListener('change', function(event) {
        const file = event.target.files[0];
        if (file) {
            // 파일 크기 검증
            if (file.size > 2 * 1024 * 1024) {
                alert('파일 크기가 너무 큽니다. 최대 크기는 2MB입니다.');
                return;
            }

            const formData = new FormData();
            formData.append('file', file);
            formData.append('userNo', userNo);

            fetch('/api/users/uploadProfileImage', {
                method: 'POST',
                body: formData
            })
                .then(response => response.json())
                .then(data => {
                    if (data.imageUrl) {
                        document.getElementById('profilePic').src = data.imageUrl + '?timestamp=' + new Date().getTime();
                    } else {
                        alert('프로필 사진 업로드에 실패했습니다.');
                    }
                })
                .catch(error => console.error('Error uploading profile picture:', error));
        }
    });

    // 전화번호 변경 이벤트
    document.getElementById('changePhoneBtn').addEventListener('click', function() {
        const currentPassword = document.getElementById('currentPassword').value;
        const newPhoneNumber = document.getElementById('phoneInput').value;

        if (!currentPassword) {
            alert('현재 비밀번호를 입력해주세요.');
            return;
        }

        const phonePattern = /^010-\d{4}-\d{4}$/;
        if (!phonePattern.test(newPhoneNumber)) {
            alert('전화번호 형식이 올바르지 않습니다. 010-XXXX-XXXX 형식으로 입력해주세요.');
            return;
        }

        fetch(`/api/users/changePhone`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                userNo: userNo,
                currentPassword: currentPassword,
                newPhoneNumber: newPhoneNumber
            })
        })
            .then(response => {
                if (response.ok) {
                    alert('전화번호가 성공적으로 변경되었습니다.');
                    document.getElementById('userPhone').innerText = newPhoneNumber;
                    document.getElementById('currentPassword').value = ''; // 비밀번호 필드 초기화

                } else {
                    response.json().then(data => alert(data.message));
                    document.getElementById('currentPassword').value = ''; // 비밀번호 필드 초기화
                }
            })
            .catch(error => console.error('Error changing phone number:', error));
        document.getElementById('currentPassword').value = ''; // 비밀번호 필드 초기화
    });

    // 학과 변경 이벤트
    document.getElementById('changeMajorBtn').addEventListener('click', function() {
        const currentPassword = document.getElementById('currentPassword').value;
        const newMajor = document.getElementById('majorDropdown').value;

        if (!currentPassword) {
            alert('현재 비밀번호를 입력해주세요.');
            return;
        }

        fetch(`/api/users/changeMajor`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                userNo: userNo,
                currentPassword: currentPassword,
                newMajor: newMajor
            })
        })
            .then(response => {
                if (response.ok) {
                    alert('학과가 성공적으로 변경되었습니다.');
                    document.getElementById('userMajor').innerText = newMajor;
                    document.getElementById('currentPassword').value = ''; // 비밀번호 필드 초기화

                } else if (response.status === 400) {
                    response.json().then(data => {
                        if (data.message === 'Current password is incorrect') {
                            alert('현재 비밀번호가 일치하지 않습니다.');
                        } else {
                            alert(data.message);
                        }
                        document.getElementById('currentPassword').value = ''; // 비밀번호 필드 초기화
                    });
                } else {
                    response.json().then(data => alert(data.message));
                    document.getElementById('currentPassword').value = ''; // 비밀번호 필드 초기화
                }
            })
            .catch(error => console.error('Error changing major:', error));
    });

    // 비밀번호 변경 이벤트
    document.getElementById('changePasswordBtn').addEventListener('click', function() {
        const currentPassword = document.getElementById('currentPassword').value;
        const newPassword = document.getElementById('newPassword').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        if (!newPassword) {
            alert('변경할 비밀번호를 입력해주세요.');
            return;
        }
        if (newPassword !== confirmPassword) {
            alert('새로운 비밀번호와 비밀번호 확인이 일치하지 않습니다.');
            return;
        }

        fetch(`/api/users/changePassword`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                userNo: userNo,
                currentPassword: currentPassword,
                newPassword: newPassword
            })
        })
            .then(response => {
                if (response.ok) {
                    alert('비밀번호가 성공적으로 변경되었습니다.');
                    window.location.href = '/';
                } else {
                    response.json().then(data => alert(data.message));
                }
            })
            .catch(error => console.error('Error changing password:', error));
    });

    // 게시글 삭제 이벤트
    addDeleteEventToTradeButtons();
});
//게시글 랜더링
function fetchTradesByUserNo(userNo) {
    fetch(`/api/userposts/${userNo}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const tbody = document.querySelector('.users-table tbody');
            tbody.innerHTML = ''; // 테이블 내용 초기화
            data.forEach(trade => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                <td>${trade.tradeNum}</td>
                <td>${trade.tradeTitle}</td>
                <td>${trade.tradeCategory}</td>
                <td>${trade.tradeTime}</td>
                <td>${trade.schoolNum}</td>
                <td>${trade.tradeComplete}</td>
                <td><button class="delete-button" data-tradenum="${trade.tradeNum}">삭제</button></td>
                 <td>
                <select class="trade-status-dropdown" data-trade-num="${trade.tradeNum}">
                            <option ${trade.tradeComplete === 'N' ? 'selected' : ''} value="N">거래전</option>
                            <option ${trade.tradeComplete === 'I' ? 'selected' : ''} value="I">거래중</option>
                            <option ${trade.tradeComplete === 'Y' ? 'selected' : ''} value="Y">거래후</option>
                </select>
            </td>
            `;
                tbody.appendChild(tr);
            });
            addDeleteEventToTradeButtons(); // 새로운 삭제 이벤트 추가
            addChangeEventToSelects(); // 수정 이벤트
        })
        .catch(error => console.error('Error fetching trades:', error));
}

function addDeleteEventToTradeButtons() {
    const deleteButtons = document.querySelectorAll('.delete-button');
    deleteButtons.forEach(button => {
        button.addEventListener('click', function() {
            const tradeNum = this.getAttribute('data-tradenum');
            fetch(`/api/trades/${tradeNum}`, {
                method: 'DELETE'
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    // 테이블에서 해당 게시글을 제거합니다.
                    this.parentElement.parentElement.remove();
                })
                .catch(error => console.error('Error deleting trade:', error));
        });
    });
}
// 거래상태 수정이벤트 호출
function addChangeEventToSelects() {
    const selects = document.querySelectorAll('.trade-status-dropdown');
    selects.forEach(select => {
        select.addEventListener('change', function() {
            updateTradeStatus(this);
        });
    });
}
// 거래상태 수정
function updateTradeStatus(selectElement) {
    const tradeNum = selectElement.getAttribute('data-trade-num');
    const newStatus = selectElement.value;

    fetch(`/api/trades/updateStatus/${tradeNum}`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({ tradeComplete: newStatus })
    })
        .then(response => {
            if (response.ok) {
                alert('거래 상태가 업데이트 되었습니다.');
                window.location.href = '/mypage';
            } else {
                alert('거래 상태 업데이트를 실패했습니다.');
                console.log(response);
            }
        })
        .catch(error => {
            console.error('Error updating trade status:', error);
        });
}
