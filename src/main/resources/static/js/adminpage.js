document.addEventListener('DOMContentLoaded', function() {
    let filteredUsers = [];
    const usersPerPage = 7;
    let currentPage = 1;
    const userList = document.getElementById('users');
    const prevButton = document.getElementById('prev');
    const nextButton = document.getElementById('next');
    const paginationContainer = document.querySelector('.pagination');
    const searchInput = document.querySelector('.search-input');
    const searchButton = document.querySelector('.search-button');
    const searchCategory = document.querySelector('.search-category');

    // 뒤로가기버튼구현
    const backButton = document.getElementById("backButton");
    if (backButton) {
        backButton.addEventListener("click", function() {
            window.history.back();
        });
    }
    function getUsersFromApi() {
        fetch('/api/users')
            .then(response => response.json())
            .then(data => {
                filteredUsers = data;
                displayPaginatedUsers(currentPage, filteredUsers);
            })
            .catch(error => console.error('Error fetching users:', error));
    }

    function displayUsers(users) {
        userList.innerHTML = '';
        users.forEach(user => {
            const userDiv = document.createElement('div');
            userDiv.classList.add('user-item');

            const numberSpan = document.createElement('span');
            numberSpan.classList.add('user-info');
            numberSpan.textContent = user.userNo;
            userDiv.appendChild(numberSpan);

            const nameSpan = document.createElement('span');
            nameSpan.classList.add('user-info');
            nameSpan.textContent = user.userName;
            userDiv.appendChild(nameSpan);

            const departmentSpan = document.createElement('span');
            departmentSpan.classList.add('user-info');
            departmentSpan.textContent = user.userMajor;
            userDiv.appendChild(departmentSpan);

            const studentIDSpan = document.createElement('span');
            studentIDSpan.classList.add('user-info');
            studentIDSpan.textContent = user.userSchoolNum;
            userDiv.appendChild(studentIDSpan);

            userDiv.dataset.userno = user.userNo;
            userList.appendChild(userDiv);
        });
    }

    function updatePagination(users) {
        const totalPages = Math.ceil(users.length / usersPerPage);
        paginationContainer.innerHTML = '';

        if (totalPages > 1) {
            prevButton.disabled = currentPage === 1;
            nextButton.disabled = currentPage === totalPages;

            paginationContainer.appendChild(prevButton);
            const maxButtons = 5;
            const startPage = Math.floor((currentPage - 1) / maxButtons) * maxButtons + 1;
            const endPage = Math.min(startPage + maxButtons - 1, totalPages);

            for (let i = startPage; i <= endPage; i++) {
                const pageButton = document.createElement('button');
                pageButton.textContent = i;
                pageButton.classList.add('page-button');
                if (i === currentPage) {
                    pageButton.classList.add('active');
                }
                pageButton.addEventListener('click', () => {
                    currentPage = i;
                    displayPaginatedUsers(currentPage, filteredUsers);
                });
                paginationContainer.appendChild(pageButton);
            }

            paginationContainer.appendChild(nextButton);
        }
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
        const totalPages = Math.ceil(filteredUsers.length / usersPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            displayPaginatedUsers(currentPage, filteredUsers);
        }
    });

    searchButton.addEventListener('click', function() {
        const searchTerm = searchInput.value.toLowerCase();
        const category = searchCategory.value;
        fetch(`/api/search?category=${category}&keyword=${searchTerm}`)
            .then(response => response.json())
            .then(data => {
                filteredUsers = data;
                currentPage = 1;
                displayPaginatedUsers(currentPage, filteredUsers);
            })
            .catch(error => console.error('Error searching users:', error));
    });

    function displayUserDetails(userno) {
        const user = filteredUsers.find(u => u.userNo === userno);
        const userNo = document.getElementById('user-no');
        const userName = document.getElementById('user-name');
        const userDepartment = document.getElementById('user-department');
        const userId = document.getElementById('user-id');
        const userEmail = document.getElementById('user-email');

        userNo.textContent = user.userNo;
        userName.textContent = user.userName;
        userDepartment.textContent = user.userMajor;
        userId.textContent = user.userSchoolNum;
        userEmail.textContent = user.userEmail;

        const deleteButton = document.querySelector('.delete-user-button');
        deleteButton.addEventListener('click', () => {
            deleteUser(userno);
        });
    }

    function addClickEventToUserItems() {
        const userItems = document.querySelectorAll('.user-item');
        userItems.forEach(item => {
            item.addEventListener('click', () => {
                const userno = parseInt(item.dataset.userno);
                displayUserDetails(userno);
            });
        });
    }

    function deleteUser(userno) {
        fetch(`/api/users/${userno}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    getUsersFromApi();
                } else {
                    console.error('Failed to delete user');
                }
            })
            .catch(error => console.error('Error deleting user:', error));
    }

    function init() {
        getUsersFromApi();
    }

    init();
});
