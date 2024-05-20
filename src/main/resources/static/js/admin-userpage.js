document.addEventListener('DOMContentLoaded', function() {
    // 특정 사용자의 사용자 번호를 가져옵니다.
    const userNo = document.getElementById('userNo').value;

    // 페이지 로드 시 특정 사용자 번호를 사용하여 테이블 데이터를 가져옴
    fetchTradesByUserNo(userNo);

    // 프로필 사진 변경 버튼 클릭 이벤트
    document.getElementById('changePicBtn').addEventListener('click', function() {
        document.getElementById('fileInput').click(); // 파일 선택창
    });

    // 파일 입력 변경
    document.getElementById('fileInput').addEventListener('change', handleFileSelect);

    // 전화번호 변경 버튼에 클릭 이벤트
    document.getElementById('changePhoneBtn').addEventListener('click', function() {
        const newPhoneNumber = document.getElementById('phoneInput').value; // 입력칸에 입력된 전화번호 가져오기
        updatePhoneNumber(newPhoneNumber); // 전화번호 업데이트
    });

    // 학과 변경 버튼에 클릭 이벤트
    document.getElementById('changeMajorBtn').addEventListener('click', function() {
        const newMajor = document.getElementById('majorDropdown').value; // 학과 가져오기
        updateMajor(newMajor); // 학과 업데이트
    });

    // 비밀번호 변경 버튼에 클릭 이벤트
    document.getElementById('changePasswordBtn').addEventListener('click', function() {
        const currentPassword = document.getElementById('currentPassword').value; // 현재 비밀번호
        const newPassword = document.getElementById('newPassword').value; // 새로운 비밀번호 입력 값 가져옴
        const confirmPassword = document.getElementById('confirmPassword').value; // 비밀번호 확인 입력란 값 가져옴

        // 현재 비밀번호가 올바른지 확인 (서버에서 가져온 데이터와 비교)
        if (currentPassword !== serverUserData.password) {
            alert('현재 비밀번호가 올바르지 않습니다.');
            return;
        }

        // 새로운 비밀번호와 비밀번호 확인이 일치하는지 확인
        if (newPassword !== confirmPassword) {
            alert('새로운 비밀번호와 비밀번호 확인이 일치하지 않습니다.');
            return;
        }

        // 새로운 비밀번호 업데이트 (서버에 업데이트 요청을 보내야 함)
        updatePassword(newPassword);
    });

    // 함수 호출 추가
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
                    // Remove the trade from the table
                    this.parentElement.parentElement.remove();
                })
                .catch(error => console.error('Error deleting trade:', error));
        });
    });
}

// 학과 업데이트 함수
function updateMajor(newMajor) {
    document.getElementById('userMajor').innerText = newMajor;
    // 서버에 업데이트 요청을 보내야 함
}

// 파일이 선택됐을 때 처리하는 함수
function handleFileSelect(event) {
    const selectedFile = event.target.files[0]; // 선택된 파일 가져오기
    const reader = new FileReader();

    reader.onload = function(event) {
        const profileImage = document.getElementById('profilePic');
        profileImage.src = event.target.result; // 선택 사진을 프로필 이미지로 설정
    };

    reader.readAsDataURL(selectedFile);
}

// 전화번호 업데이트 함수
function updatePhoneNumber(newPhoneNumber) {
    document.getElementById('userPhone').innerText = newPhoneNumber;
    // 서버에 업데이트 요청을 보내야 함
}
