document.addEventListener('DOMContentLoaded', function() {
    const tradesPerPage = 9;
    let currentPage = 1;
    let filteredTrades = [];

    const tradeList = document.querySelector('.post-table tbody');
    const prevButton = document.getElementById('prev');
    const nextButton = document.getElementById('next');
    const paginationContainer = document.querySelector('.pagination');
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
        tradeList.innerHTML = '';
        trades.forEach(trade => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${trade.tradeNum}</td>
                <td>${trade.tradeTitle}</td>
                <td>${trade.tradeCategory}</td>
                <td>${trade.tradeTime}</td>
                <td>${trade.user.userName}</td>
                <td>${trade.tradeComplete == 'Y' ? '거래완료' : '거래중'}</td>
            `;
            tradeList.appendChild(tr);
        });
    }

    function displayPaginatedTrades(page, trades) {
        const start = (page - 1) * tradesPerPage;
        const end = start + tradesPerPage;
        if (Array.isArray(trades)) {
            displayTrades(trades.slice(start, end));
            updatePagination(trades);
        } else {
            console.error('Trades data is not an array:', trades);
        }
    }

    console.log('Fetching trades from /api/posts'); // Fetch 요청 이전에 로그 추가

    fetch('/api/posts')
        .then(response => {
            console.log('Fetch response:', response); // 응답 객체 출력
            if (!response.ok) {
                console.error('Network response was not ok', response.statusText);
                throw new Error('Network response was not ok');
            }
            return response.text(); // 응답을 텍스트로 먼저 읽음
        })
        .then(text => {
            console.log('Response text:', text); // 응답 텍스트 출력
            try {
                const data = JSON.parse(text); // 텍스트를 JSON으로 파싱
                console.log('Parsed data:', data);
                if (Array.isArray(data)) {
                    filteredTrades = data;
                    displayPaginatedTrades(currentPage, filteredTrades);
                } else {
                    console.error('Expected array but got:', data);
                }
            } catch (error) {
                console.error('Error parsing JSON:', error);
            }
        })
        .catch(error => console.error('Error fetching trades:', error));

    searchButton.addEventListener('click', function() {
        const searchTerm = searchInput.value.trim().toLowerCase();
        const category = searchCategory.value;
        const encodedSearchTerm = encodeURIComponent(searchTerm);
        const encodedCategory = encodeURIComponent(category);
        const searchUrl = `/api/posts/search?category=${encodedCategory}&keyword=${encodedSearchTerm}`;

        console.log('Search Term:', searchTerm);
        console.log('Category:', category);
        console.log('Encoded Search Term:', encodedSearchTerm);
        console.log('Encoded Category:', encodedCategory);
        console.log('Fetching search URL:', searchUrl);  // 디버깅을 위해 URL 출력

        fetch(searchUrl)
            .then(response => {
                console.log('Search fetch response:', response); // 검색 응답 객체 출력
                if (!response.ok) {
                    console.error('Network response was not ok', response.statusText);
                    throw new Error('Network response was not ok');
                }
                return response.text(); // 응답을 텍스트로 먼저 읽음
            })
            .then(text => {
                console.log('Search response text:', text); // 검색 응답 텍스트 출력
                try {
                    const data = JSON.parse(text); // 텍스트를 JSON으로 파싱
                    console.log('Search parsed data:', data);
                    if (Array.isArray(data)) {
                        filteredTrades = data;
                        currentPage = 1;
                        displayPaginatedTrades(currentPage, filteredTrades);
                    } else {
                        console.error('Expected array but got:', data);
                    }
                } catch (error) {
                    console.error('Error parsing JSON:', error);
                }
            })
            .catch(error => console.error('Error fetching trades:', error));
    });
});
