
document.addEventListener('DOMContentLoaded', function () {

    document.addEventListener('DOMContentLoaded', function() {
        const categorySelect = document.getElementById('category');

        categorySelect.addEventListener('change', function() {
            const selectedCategoryId = this.value;

            if (selectedCategoryId) {
                // 선택한 카테고리에 해당하는 거래 목록 가져오기
                fetch(`/trade/category/${selectedCategoryId}`)
                    .then(response => response.json())
                    .then(data => {
                        // 거래 목록 업데이트
                        updateTradeList(data);
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
            } else {
                // 전체 거래 목록 가져오기
                fetch('/trade/list')
                    .then(response => response.json())
                    .then(data => {
                        // 거래 목록 업데이트
                        updateTradeList(data);
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
            }
        });
    const searchBtn = document.getElementById('search-btn');
    searchBtn.addEventListener('click', function () {
        const keyword = document.getElementById('search-input').value;
        const option = document.getElementById('search-option').value;
        // 서버로 검색 요청 보내기
        searchTrades(keyword, option);
    });


    const categoryLinks = document.querySelectorAll('.category-filter a');
    categoryLinks.forEach(function (link) {
        link.addEventListener('click', function (event) {
            event.preventDefault();
            const category = this.getAttribute('data-category');
            // 서버로 해당 카테고리의 거래 목록 요청하기
            filterByCategory(category);
        });
    });


    function searchTrades(keyword, option) {
        axios.get(`/trade/search?keyword=${keyword}&option=${option}`)
            .then(response => {
                updateTradeList(response.data);
            })
            .catch(error => {
                console.error('검색 요청 실패:', error);
            });
    }

    function filterByCategory(category) {
        axios.get(`/trade/category/${category}`)
            .then(response => {
                updateTradeList(response.data);
            })
            .catch(error => {
                console.error('카테고리 필터링 요청 실패:', error);
            });
    }

    fetchTradeList();

    function fetchTradeList() {
        axios.get('/trade/list')
            .then(response => {
                updateTradeList(response.data);
            })
            .then(data => {
                // 응답 처리
                if (data.success) {
                    // 거래 목록 페이지로 이동
                    window.location.href = '/trade/list';
                } else {
                    // 실패 시 에러 메시지 표시
                    const errorMessage = document.getElementById('error-message');
                    errorMessage.textContent = data.message;
                }
            })
            .catch(error => {
                console.error('거래 목록 요청 실패:', error);
            });
    }
    function updateTradeList(trades) {
        const tradeList = document.getElementById('trade-list');
        tradeList.innerHTML = '';

        trades.forEach(trade => {
            const tradeItem = document.createElement('div');
            tradeItem.classList.add('trade-item');
            tradeItem.textContent = trade.title;
            tradeList.appendChild(tradeItem);
        });
    }
});


    function formatDate(dateString) {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        return `${year}-${month}-${day} ${hours}:${minutes}`;
    }
});