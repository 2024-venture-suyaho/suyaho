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
            <td>${trade.tradeNum}</td>
            <td>${trade.tradeTitle}</td>
            <td>${trade.tradeCategory}</td>
            <td>${trade.tradeTime}</td>
            <td>${trade.schoolNum}</td>
            <td>${trade.tradeComplete}</td>
            <td><button class="delete-button" data-tradeNum="${trade.tradeNum}">삭제</button></td>
        `;
            tbody.appendChild(tr);
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
                        // Remove the trade from the filteredTrades array
                        filteredTrades = filteredTrades.filter(trade => trade.tradeNum != tradeNum);
                        // Update the display
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

    window.addEventListener('scroll', function() {
        if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
            nextPage();
        }
    });
});