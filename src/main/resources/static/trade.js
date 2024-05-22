
async function fetchTrades() {
    const response = await fetch('http://localhost:8080/trade/trades', {
        method: 'GET', // 요청 방식 (GET, POST 등)
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if (!response.ok) {
        throw new Error('Network response was not ok ' + response.statusText);
    }

    const data = await response.json();
    console.log(data);

    const tradesDiv = document.getElementById('trades');
    tradesDiv.innerHTML = JSON.stringify(data, null, 2);
}


function submitTradeRequest() {
    const form = document.getElementById('trade-form');
    const formData = new FormData(form);
    const tradeRequest = {
        title: formData.get('title'),
        bookWriting: document.getElementById('writing-yes').checked,
        bookCover: document.getElementById('cover-yes').checked,
        bookDiscoloration: document.getElementById('discoloration-yes').checked,
        bookDamage: document.getElementById('damage-yes').checked,
        productName: formData.get('product-name'),
        quantity: parseInt(formData.get('quantity')),
        price: parseFloat(formData.get('price')),
        description: formData.get('description'),
        categoryId: parseInt(formData.get('category-id')),
        image: formData.get('image'),
        publisher: formData.get('publisher'),
        userNo: getCurrentUserNo(),
        tradeComplete: false,
        tradeTime: new Date().toISOString()
    };

    // 거래 테이블에 저장할 데이터
    const tradeBoardData = {
        tradeTitle: tradeRequest.title,
        tradeProduct: tradeRequest.productName,
        tradeQuantity: tradeRequest.quantity,
        tradePrice: tradeRequest.price,
        tradeCondition: `필기 흔적: ${tradeRequest.bookWriting ? '있음' : '없음'},  
                         변색: ${tradeRequest.bookDiscoloration ? '있음' : '없음'}, 
                         훼손: ${tradeRequest.bookDamage ? '있음' : '없음'}`,
        tradePhoto: tradeRequest.image,
        tradeComplete: false,
        userNo: tradeRequest.userNo,
        tradeTime: tradeRequest.tradeTime
    };

    // 책 테이블에 저장할 데이터
    const bookData = {
        bookWriting: tradeRequest.bookWriting,
        bookCover: tradeRequest.bookCover,
        bookDiscoloration: tradeRequest.bookDiscoloration,
        bookDamage: tradeRequest.bookDamage,
        userNo: tradeRequest.userNo,
        bookCompany: tradeRequest.publisher,
        tradeNum: null
    };

    // 서버로 데이터를 보내는 작업 수행
    fetch('/trade/write', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            tradeBoardData: tradeBoardData,
            bookData: bookData
        })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                window.location.href = './trade/list.html';
            } else {
                console.error('Error:', data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


function searchTrades(keyword, option) {
    axios.get(`/trade/search?keyword=${keyword}&option=${option}`)
        .then(response => {
            updateTradeList(response.data);
        })
        .catch(error => {
            console.error('검색 요청 실패:', error);
        });
}

/*이거 카테고리 할거*/
function filterByCategory(category) {
    axios.get(`/trade/category/${category}`)
        .then(response => {
            updateTradeList(response.data);
        })
        .catch(error => {
            console.error('카테고리 필터링 요청 실패:', error);
        });
}


function updateTradeList(trades) {
    const tradeList = document.getElementById('trade-list').querySelector('tbody');
    tradeList.innerHTML = '';

    trades.forEach((trade) => {
        const tradeRow = document.createElement('tr');

        const idCell = document.createElement('td');
        idCell.textContent = trade.id;
        tradeRow.appendChild(idCell);

        const titleCell = document.createElement('td');
        titleCell.textContent = trade.title;
        tradeRow.appendChild(titleCell);

        const categoryCell = document.createElement('td');
        categoryCell.textContent = trade.category.categoryName;
        tradeRow.appendChild(categoryCell);

        const dateCell = document.createElement('td');
        dateCell.textContent = formatDate(trade.tradeTime);
        tradeRow.appendChild(dateCell);

        const usernameCell = document.createElement('td');
        usernameCell.textContent = trade.user.username;
        tradeRow.appendChild(usernameCell);

        const tradeStatusCell = document.createElement('td');
        tradeStatusCell.textContent = trade.tradeComplete ? '거래완료' : '거래중';
        tradeRow.appendChild(tradeStatusCell);

        tradeList.appendChild(tradeRow);
    });
}


function loadTrades() {
    fetch('/trade/trades')
        .then(response => response.json())
        .then(data => {
            updateTradeList(data);
        })
        .catch(error => {
            console.error('Error fetching trades:', error);
        });
}

// 날짜 형식화 함수
function formatDate(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    return `${year}-${month}-${day} ${hours}:${minutes}`;
}

document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('trade-form');


    form.addEventListener('submit', function(event) {
        event.preventDefault();


        submitTradeRequest();
    });


    const searchBtn = document.getElementById('search-btn');
    searchBtn.addEventListener('click', function()  {
        const keyword = document.getElementById('search-input').value;
        const option = document.getElementById('search-option').value;
        // 서버로 검색 요청 보내기
        searchTrades(keyword, option);
    });

// 카테고리 필터링 링크 클릭 이벤트 리스너
    const categoryLinks = document.querySelectorAll('.category-filter a');
    categoryLinks.forEach(function (link) {
        link.addEventListener('click', function (event) {
            event.preventDefault();
            const category = this.getAttribute('data-category');
            // 서버로 해당 카테고리의 거래 목록 요청하기
            filterByCategory(category);
        });
    });

// 페이지 로드 시 거래 목록 불러오기
    loadTrades();
});
