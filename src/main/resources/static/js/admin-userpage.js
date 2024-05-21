document.addEventListener('DOMContentLoaded', function() {
    // 특정 사용자의 사용자 번호를 가져옵니다.
    const userNo = document.getElementById('userNo').value;

    // 페이지 로드 시 특정 사용자 번호를 사용하여 테이블 데이터를 가져옴
    fetchTradesByUserNo(userNo);

    // 프로필 사진 변경 버튼에 클릭 이벤트 리스너를 추가합니다.
    document.getElementById('changePicBtn').addEventListener('click', function() {
        document.getElementById('fileInput').click();
    });

    document.getElementById('fileInput').addEventListener('change', function(event) {
        const file = event.target.files[0];
        if (file) {
            // 파일 크기 검증 (2MB 이하로 제한)
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
                    if (data.imageUrl) {// 캐시 무효화를 위해 쿼리 문자열 추가
                        // 이미지 URL 변경 후 새로고침 없이 바로 반영
                        document.getElementById('profilePic').src = data.imageUrl + '?timestamp=' + new Date().getTime();
                        //document.getElementById('profilePic').src = data.imageUrl;


                    } else {
                        alert('프로필 사진 업로드에 실패했습니다.');
                    }
                })
                .catch(error => console.error('Error uploading profile picture:', error));
        }
    });

    // 전화번호 변경 버튼에 클릭 이벤트 리스너를 추가합니다.
    document.getElementById('changePhoneBtn').addEventListener('click', function() {
        const currentPassword = document.getElementById('currentPassword').value; // 현재 비밀번호 입력 값
        const newPhoneNumber = document.getElementById('phoneInput').value; // 입력칸에 입력된 전화번호 가져오기

        if (!currentPassword) {
            alert('현재 비밀번호를 입력해주세요.');
            return;
        }

        // 전화번호 형식 검증 (010-@@@@-@@@@)
        const phonePattern = /^010-\d{4}-\d{4}$/;
        if (!phonePattern.test(newPhoneNumber)) {
            alert('전화번호 형식이 올바르지 않습니다. 010-XXXX-XXXX 형식으로 입력해주세요.');
            return;
        }

        // 전화번호 변경 요청 보내기
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
                    document.getElementById('userPhone').innerText = newPhoneNumber; // UI 업데이트
                    location.reload();
                } else {
                    response.json().then(data => alert(data.message));
                }
            })
            .catch(error => console.error('Error changing phone number:', error));
    });

    // 학과 변경 버튼에 클릭 이벤트 리스너를 추가합니다.
    document.getElementById('changeMajorBtn').addEventListener('click', function() {
        const currentPassword = document.getElementById('currentPassword').value; // 현재 비밀번호 입력 값
        const newMajor = document.getElementById('majorDropdown').value; // 선택된 학과명 가져오기

        if (!currentPassword) {
            alert('현재 비밀번호를 입력해주세요.');
            return;
        }

        // 학과 변경 요청 보내기
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
                    document.getElementById('userMajor').innerText = newMajor; // UI 업데이트
                    location.reload();
                } else if (response.status === 400) {
                    response.json().then(data => {
                        if (data.message === 'Current password is incorrect') {
                            alert('현재 비밀번호가 일치하지 않습니다.');
                        } else {
                            alert(data.message);
                        }
                    });
                } else {
                    response.json().then(data => alert(data.message));
                }
            })
            .catch(error => console.error('Error changing major:', error));
    });

    // 비밀번호 변경 버튼에 클릭 이벤트 리스너를 추가합니다.
    document.getElementById('changePasswordBtn').addEventListener('click', function() {
        const currentPassword = document.getElementById('currentPassword').value; // 현재 비밀번호
        const newPassword = document.getElementById('newPassword').value; // 새로운 비밀번호 입력 값 가져옴
        const confirmPassword = document.getElementById('confirmPassword').value; // 비밀번호 확인 입력란 값 가져옴

        if (!newPassword) {
            alert('변경할 비밀번호를 입력해주세요.');
            return;
        }
        if (newPassword !== confirmPassword) {
            alert('새로운 비밀번호와 비밀번호 확인이 일치하지 않습니다.');
            return;
        }

        // 비밀번호 변경 요청 보내기
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

    // 게시글 삭제 버튼에 이벤트 리스너 추가
    addDeleteEventToTradeButtons();
});

// 특정 사용자 번호를 사용하여 게시글을 가져오는 함수
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
                    <td><button>수정</button></td>
                `;
                tbody.appendChild(tr);
            });
            addDeleteEventToTradeButtons(); // 새로운 삭제 이벤트 추가
        })
        .catch(error => console.error('Error fetching trades:', error));
}

// 삭제 버튼에 이벤트 리스너를 추가하는 함수
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
