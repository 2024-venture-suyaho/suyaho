document.addEventListener('DOMContentLoaded', () => {
    // 프로필 사진 변경 버튼 클릭 이벤트
    document.getElementById('changePicBtn').addEventListener('click', () => {
        document.getElementById('fileInput').click(); // 파일 선택창
    });

    // 파일 입력 변경
    document.getElementById('fileInput').addEventListener('change', handleFileSelect);

    // 전화번호 변경 버튼에 클릭 이벤트
    document.getElementById('changePhoneBtn').addEventListener('click', () => {
        const newPhoneNumber = document.getElementById('phoneInput').value; // 입력칸에 입력된 전화번호 가져오기
        updatePhoneNumber(newPhoneNumber); // 전화번호 업데이트
    });

    // 학과 변경 버튼에 클릭 이벤트
    document.getElementById('changeMajorBtn').addEventListener('click', () => {
        const newMajor = document.getElementById('majorDropdown').value; // 학과 가져오기
        updateMajor(newMajor); // 학과 업데이트
    });

    // 비밀번호 변경 버튼에 클릭 이벤트
    document.getElementById('changePasswordBtn').addEventListener('click', () => {
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
});

// 학과 업데이트
function updateMajor(newMajor) {
    document.getElementById('userMajor').innerText = newMajor;
    // 서버에 업데이트 요청을 보내야 함
}

// 파일이 선택됐을 때
function handleFileSelect(event) {
    const selectedFile = event.target.files[0]; // 선택된 파일 가져오기
    const reader = new FileReader();

    reader.onload = function(event) {
        const profileImage = document.getElementById('profilePic');
        profileImage.src = event.target.result; // 선택 사진을 프로필 이미지로 설정
    };

    reader.readAsDataURL(selectedFile);
}

// 전화번호 업데이트
function updatePhoneNumber(newPhoneNumber) {
    document.getElementById('userPhone').innerText = newPhoneNumber;
    // 서버에 업데이트 요청을 보내야 함
}
