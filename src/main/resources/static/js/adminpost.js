document.addEventListener('DOMContentLoaded', function() {

    const tradesPerPage = 9;
    let currentPage = 1;
    let filteredTrades = [];

    const tradeList = document.getElementById('trades');
    const prevButton = document.getElementById('prev');
    const nextButton = document.getElementById('next');
    const searchInput = document.querySelector('.search-input');
    const searchButton = document.querySelector('.search-button');
    const searchCategory = document.querySelector('.search-category');

    function updatePagination(trades) {
        prevButton.disabled = currentPage === 1;
        nextButton.disabled = currentPage * tradesPerPage >= trades.length;
    }

    function displayTrades(trades) {
        const tbody = document.querySelector('.users-table tbody');
        tbody.innerHTML = '';
        trades.forEach(trade => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
            <td>${trade.trade_num}</td>
            <td>${trade.trade_title}</td>
            <td>${trade.trade_category}</td>
            <td>${trade.trade_time}</td>
            <td>${trade.user_no}</td>
            <td>${trade.trade_complete}</td>
            <td><button class="delete-button" data-tradeNum="${trade.trade_num}">삭제</button></td>
        `;
            tbody.appendChild(tr);
        });
    }

    function displayPaginatedTrades(page, trades) {
        const start = (page - 1) * tradesPerPage;
        const end = start + tradesPerPage;
        displayTrades(trades.slice(start, end));
        updatePagination(trades);
        addDeleteEventToTradeButtons();
    }

    prevButton.addEventListener('click', function() {
        if (currentPage > 1) {
            currentPage--;
            displayPaginatedTrades(currentPage, filteredTrades);
        }
    });

    nextButton.addEventListener('click', function() {
        if (currentPage * tradesPerPage < filteredTrades.length) {
            currentPage++;
            displayPaginatedTrades(currentPage, filteredTrades);
        }
    });

    searchButton.addEventListener('click', function() {
        const searchTerm = searchInput.value.toLowerCase();
        const category = searchCategory.value;
        fetch(`/api/trades?category=${category}&searchTerm=${searchTerm}`)
            .then(response => response.json())
            .then(data => {
                filteredTrades = data;
                currentPage = 1;
                displayPaginatedTrades(currentPage, filteredTrades);
            })
            .catch(error => console.error('Error fetching trades:', error));
    });

    function addDeleteEventToTradeButtons() {
        const deleteButtons = document.querySelectorAll('.delete-button');
        deleteButtons.forEach(button => {
            button.addEventListener('click', function() {
                const tradeNum = this.getAttribute('data-tradeNum');
                filteredTrades = filteredTrades.filter(trade => trade.tradeNum != tradeNum);
                displayPaginatedTrades(currentPage, filteredTrades);
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
                filteredTrades = data;
                displayPaginatedTrades(currentPage, filteredTrades);
            })
            .catch(error => console.error('Error fetching trades:', error));
    }

    init();

    window.addEventListener('scroll', function() {
        if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
            nextPage();
        }
    });
});
