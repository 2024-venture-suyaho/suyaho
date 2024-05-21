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
                .then(response => response.json())
                .then(data => {
                    // 응답 처리
                    if (data.success) {
                        // 거래 목록 페이지로 이동
                        window.location.href = '/trade/list';
                    } else {
                        // 실패 시 에러 메시지 표시
                        const errorMessage = document.getElementById('error-message');
                        errorMessage.textContent = data.message;
                        updateTradeList(data); // 거래 목록 업데이트
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

document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('trade-form');

    form.addEventListener('submit', function(event) {
        event.preventDefault();  // 기본 제출 동작을 막음

        const formData = new FormData(form); // 폼 데이터 수집
        const tradeRequest = {
            title: formData.get('title'),
            bookWriting: document.getElementById('writing-yes').checked, // 체크된 상태 확인
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
            userNo: null, // 나중에 추가
            tradeComplete: false,
            tradeComplete: false,
            tradeTime: new Date().toISOString()
        };

        // 사용자 고유 번호를 가져오는 함수
        const userNo = getCurrentUserNo(); // 이 함수를 구현해야 합니다
        tradeRequest.userNo = userNo;

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
            userNo: userNo,
            tradeTime: new Date().toISOString()
        };

        // 책 테이블에 저장할 데이터
        const bookData = {
            bookWriting: tradeRequest.bookWriting,
            bookCover: tradeRequest.bookCover,
            bookDiscoloration: tradeRequest.bookDiscoloration,
            bookDamage: tradeRequest.bookDamage,
            userNo: userNo,
            bookCompany: tradeRequest.publisher,
            tradeNum: null // 나중에 tradeNum이 생성된 후에 추가합니다.
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
    });
});
