
document.addEventListener('DOMContentLoaded', function () {

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

        fetch(`/trade/search?keyword=${keyword}&option=${option}`)
            .then(response => response.json())
            .then(data => {
                // 검색 결과를 받아서 거래 목록 업데이트
                updateTradeList(data);
            })
            .catch(error => {
                console.error('검색 요청 실패:', error);
            });
    }


    function filterByCategory(category) {
        // 서버로 해당 카테고리의 거래 목록 요청하기
        fetch(`/trade/category/${category}`)
            .then(response => response.json())
            .then(data => {
                // 받아온 거래 목록으로 업데이트
                updateTradeList(data);
            })
            .catch(error => {
                console.error('카테고리 필터링 요청 실패:', error);
            });
    }


    function updateTradeList(trades) {
        const tbody = document.querySelector('table tbody');
        tbody.innerHTML = '';

        trades.forEach((trade, index) => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${index + 1}</td>
                <td>${trade.title}</td>
                <td>${trade.category}</td>
                <td>${formatDate(trade.tradeTime)}</td>
                <td>${trade.user.username}</td>
                <td>${trade.tradeComplete ? '거래완료' : '거래중'}</td>
            `;
            tbody.appendChild(row);
        });
    }


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