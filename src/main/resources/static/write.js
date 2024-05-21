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
