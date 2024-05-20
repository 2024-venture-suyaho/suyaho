document.addEventListener('DOMContentLoaded', function() {

    const tradesPerPage = 9;
    let currentPage = 1;
    let filteredTrades = [];

    const tradeList = document.getElementById('users'); // Corrected ID selection
    const prevButton = document.getElementById('prev');
    const nextButton = document.getElementById('next');
    const paginationContainer = document.querySelector('.pagination'); // 변경: 페이지네이션 컨테이너를 선택할 때 다른 클래스나 ID를 사용
    const searchInput = document.querySelector('.search-input');
    const searchButton = document.querySelector('.search-button');
    const searchCategory = document.querySelector('.search-category');

    function updatePagination(trades) {
        const totalPages = Math.ceil(trades.length / tradesPerPage);
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
                    displayPaginatedTrades(currentPage, filteredTrades);
                });
                paginationContainer.appendChild(pageButton);
            }

            paginationContainer.appendChild(nextButton);
        }
    }

    function displayTrades(trades) {
        tradeList.innerHTML = ''; // Updated to use tradeList
        trades.forEach(trade => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
            <td>${trade.tradeNum}</td>
            <td>${trade.tradeTitle}</td>
            <td>${trade.tradeCategory}</td>
            <td>${trade.tradeTime}</td>
            <td>${trade.schoolNum}</td>
            <td>${trade.tradeComplete}</td>
            <td><button class="delete-button" data-tradeNum="${trade.tradeNum}">삭제</button></td>
        `;
            tradeList.appendChild(tr); // Updated to use tradeList
        });
    }

    function displayPaginatedTrades(page, trades) {
        const start = (page - 1) * tradesPerPage;
        const end = start + tradesPerPage;
        if (Array.isArray(trades)) {
            displayTrades(trades.slice(start, end));
            updatePagination(trades);
            addDeleteEventToTradeButtons();
        } else {
            console.error('Trades data is not an array:', trades);
        }
    }

    fetch('/api/trades')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            if (Array.isArray(data)) {
                filteredTrades = data;
                displayPaginatedTrades(currentPage, filteredTrades);
            } else {
                console.error('Expected array but got:', data);
            }
        })
        .catch(error => console.error('Error fetching trades:', error));


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

    // 카테고리 검색
    searchButton.addEventListener('click', function() {
        const searchTerm = searchInput.value.toLowerCase();
        const category = searchCategory.value;
        fetch(`/api/adminpost/search?category=${category}&keyword=${searchTerm}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                if (Array.isArray(data)) {
                    filteredTrades = data;
                    currentPage = 1;
                    displayPaginatedTrades(currentPage, filteredTrades);
                } else {
                    console.error('Expected array but got:', data);
                }
            })
            .catch(error => console.error('Error fetching trades:', error));
    });

// 게시글 삭제
    function addDeleteEventToTradeButtons() {
        const deleteButtons = document.querySelectorAll('.delete-button');
        deleteButtons.forEach(button => {
            button.addEventListener('click', function() {
                const tradeNum = this.getAttribute('data-tradeNum');
                fetch(`/api/trades/${tradeNum}`, {
                    method: 'DELETE'
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        filteredTrades = filteredTrades.filter(trade => trade.tradeNum != tradeNum);
                        displayPaginatedTrades(currentPage, filteredTrades);
                    })
                    .catch(error => console.error('Error deleting trade:', error));
            });
        });
    }
    function nextPage() {
        if (currentPage * tradesPerPage < filteredTrades.length) {
            currentPage++;
            displayPaginatedTrades(currentPage, filteredTrades);
        }
    }


    function init() {
        fetch('/api/trades')
            .then(response => response.json())
            .then(data => {
                if (Array.isArray(data)) {
                    filteredTrades = data;
                    displayPaginatedTrades(currentPage, filteredTrades);
                } else {
                    console.error('Expected array but got:', data);
                }
            })
            .catch(error => console.error('Error fetching trades:', error));
    }

    init();


});