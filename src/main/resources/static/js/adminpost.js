document.addEventListener('DOMContentLoaded', function() {
    const userData = [
        { postno: 1, title: '똥', category: '응가', posttime: '2024.00.00 15:10', userid: 'ddong', postStatus: '거래중' },
        { postno: 2, title: '똥', category: '응가', posttime: '2024.00.00 15:10', userid: 'ddong', postStatus: '거래중'  },
        { postno: 3, title: '똥', category: '응가', posttime: '2024.00.00 15:10', userid: 'ddong', postStatus: '거래중'  },
        { postno: 4, title: '똥d', category: '응가', posttime: '2024.00.00 15:10', userid: 'ddong', postStatus: '거래중'  },
        { postno: 5, title: '똥', category: '응가', posttime: '2024.00.00 15:10', userid: 'ddong', postStatus: '거래중'  },
        { postno: 6, title: '똥', category: '응가', posttime: '2024.00.00 15:10', userid: 'ddong', postStatus: '거래중'  },
        { postno: 7, title: '똥', category: '응가', posttime: '2024.00.00 15:10', userid: 'ddong', postStatus: '거래중'  },
        { postno: 8, title: '똥', category: '응가', posttime: '2024.00.00 15:10', userid: 'ddong', postStatus: '거래중'  },
        { postno: 9, title: '똥', category: '응가', posttime: '2024.00.00 15:10', userid: 'ddong', postStatus: '거래중'  },
        { postno: 10, title: '똥', category: '응가', posttime: '2024.00.00 15:10', userid: 'ddong', postStatus: '거래중'  },
        { postno: 11, title: '똥', category: '응가', posttime: '2024.00.00 15:10', userid: 'ddong', postStatus: '거래중'  },
        { postno: 12, title: '똥', category: '응가', posttime: '2024.00.00 15:10', userid: 'ddong', postStatus: '거래중'  }
    ];

    const usersPerPage = 9;
    let currentPage = 1;
    const userList = document.getElementById('users');
    const prevButton = document.getElementById('prev');
    const nextButton = document.getElementById('next');
    const searchInput = document.querySelector('.search-input');
    const searchButton = document.querySelector('.search-button');
    const searchCategory = document.querySelector('.search-category');
    let filteredUsers = userData;

    function updatePagination(users) {
        prevButton.disabled = currentPage === 1;
        nextButton.disabled = currentPage * usersPerPage >= users.length;
    }

    function displayUsers(users) {
        const tbody = document.querySelector('.users-table tbody');
        tbody.innerHTML = '';
        users.forEach(user => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${user.postno}</td>
                <td>${user.title}</td>
                <td>${user.category}</td>
                <td>${user.posttime}</td>
                <td>${user.userid}</td>
                <td>${user.postStatus}</td>
                <td><button class="delete-button" data-postno="${user.postno}">삭제</button></td>
            `;
            tbody.appendChild(tr);
        });
    }

    function displayPaginatedUsers(page, users) {
        const start = (page - 1) * usersPerPage;
        const end = start + usersPerPage;
        displayUsers(users.slice(start, end));
        updatePagination(users);
        addDeleteEventToUserButtons();
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

    function addDeleteEventToUserButtons() {
        const deleteButtons = document.querySelectorAll('.delete-button');
        deleteButtons.forEach(button => {
            button.addEventListener('click', function() {
                const postno = this.getAttribute('data-postno');
                filteredUsers = filteredUsers.filter(user => user.postno != postno);
                displayPaginatedUsers(currentPage, filteredUsers);
            });
        });
    }

    function nextPage() {
        if (currentPage * usersPerPage < filteredUsers.length) {
            currentPage++;
            displayPaginatedUsers(currentPage, filteredUsers);
        }
    }

    function init() {
        displayPaginatedUsers(currentPage, filteredUsers);
    }

    init();

    window.addEventListener('scroll', function() {
        if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
            nextPage();
        }
    });
});
