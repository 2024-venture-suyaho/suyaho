
document.addEventListener('DOMContentLoaded', function() {
    const userData = [
        { userno: 1, name: '홍길동', department: '컴퓨터공학', studentID: '20210001', email: 'hong@gmail.com' },
        { userno: 2, name: '김철수', department: '전자공학', studentID: '20210002', email: 'kim@gmail.com' },
        { userno: 3, name: '이영희', department: '경영학', studentID: '20210003', email: 'lee@gmail.com' },
        { userno: 4, name: '홍길동', department: '컴퓨터공학', studentID: '20210001', email: 'hong@gmail.com' },
        { userno: 5, name: '김철수', department: '전자공학', studentID: '20210002', email: 'kim@gmail.com' },
        { userno: 6, name: '박영희', department: '경영학', studentID: '20210003', email: 'lee@gmail.com' },
        { userno: 7, name: '최영희', department: '경영학', studentID: '20210003', email: 'lee@gmail.com' },
        { userno: 8, name: '남영희', department: '경영asdfasdfasdfad학', studentID: '20210003', email: 'lee@gmail.com' }
    ];

    const usersPerPage = 7;
    let currentPage = 1;
    const userList = document.getElementById('users');
    const prevButton = document.getElementById('prev');
    const nextButton = document.getElementById('next');
    const searchInput = document.querySelector('.search-input');
    const searchButton = document.querySelector('.search-button');
    const searchCategory = document.querySelector('.search-category');
    let filteredUsers = userData;

    // 유저 목록 클릭 시 해당 유저의 정보 표시
    function displayUsers(users) {
        userList.innerHTML = '';
        users.forEach(user => {
            const userDiv = document.createElement('div');
            userDiv.classList.add('user-item');

            const numberSpan = document.createElement('span');
            numberSpan.classList.add('user-info');
            numberSpan.textContent = user.userno;
            userDiv.appendChild(numberSpan);

            const nameSpan = document.createElement('span');
            nameSpan.classList.add('user-info');
            nameSpan.textContent = user.name;
            userDiv.appendChild(nameSpan);

            const departmentSpan = document.createElement('span');
            departmentSpan.classList.add('user-info');
            departmentSpan.textContent = user.department;
            userDiv.appendChild(departmentSpan);

            const studentIDSpan = document.createElement('span');
            studentIDSpan.classList.add('user-info');
            studentIDSpan.textContent = user.studentID;
            userDiv.appendChild(studentIDSpan);

            userDiv.dataset.userno = user.userno;  // userno를 데이터 속성으로 추가
            userList.appendChild(userDiv);
        });
    }

    function updatePagination(users) {
        prevButton.disabled = currentPage === 1;
        nextButton.disabled = currentPage * usersPerPage >= users.length;
    }

    function displayPaginatedUsers(page, users) {
        const start = (page - 1) * usersPerPage;
        const end = start + usersPerPage;
        displayUsers(users.slice(start, end));
        updatePagination(users);
        addClickEventToUserItems();
    }

    prevButton.addEventListener('click', function() {
        if (currentPage > 1) {
            currentPage--;
            displayPaginatedUsers(currentPage, filteredUsers);
        }
    });

    nextButton.addEventListener('click', function() {
        if (currentPage * usersPerPage < filteredUsers.length) {
            currentPage++;
            displayPaginatedUsers(currentPage, filteredUsers);
        }
    });

    searchButton.addEventListener('click', function() {
        const searchTerm = searchInput.value.toLowerCase();
        const category = searchCategory.value;
        filteredUsers = userData.filter(user =>
            user[category].toLowerCase().includes(searchTerm)
        );
        currentPage = 1;
        displayPaginatedUsers(currentPage, filteredUsers);
    });

    // 유저 상세 정보 표시
    function displayUserDetails(userno) {
        const user = filteredUsers.find(u => u.userno === userno);
        const userNo = document.getElementById('user-no');
        const userName = document.getElementById('user-name');
        const userDepartment = document.getElementById('user-department');
        const userId = document.getElementById('user-id');
        const userEmail = document.getElementById('user-email');

        userNo.textContent = user.userno;
        userName.textContent = user.name;
        userDepartment.textContent = user.department;
        userId.textContent = user.studentID;
        userEmail.textContent = user.email;
    }

    // 각 유저 목록 클릭 이벤트
    function addClickEventToUserItems() {
        const userItems = document.querySelectorAll('.user-item');
        userItems.forEach(item => {
            item.addEventListener('click', () => {
                const userno = parseInt(item.dataset.userno);
                displayUserDetails(userno);
            });
        });
    }

    // 유저 삭제 버튼
    function addDeleteEventToUserButton() {
        const deleteUserButton = document.querySelector('.delete-user-button');
        deleteUserButton.addEventListener('click', function() {
            const userNo = parseInt(document.getElementById('user-no').textContent);
            filteredUsers = filteredUsers.filter(user => user.userno !== userNo);
            displayPaginatedUsers(currentPage, filteredUsers);
        });
    }

    function init() {
        displayPaginatedUsers(currentPage, filteredUsers);
        addClickEventToUserItems();
        addDeleteEventToUserButton();
    }

    init();
});

