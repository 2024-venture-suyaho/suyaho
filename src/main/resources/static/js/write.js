/*
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('trade-form');
    const submitButton = document.getElementById('submit-button');

    submitButton.addEventListener('click', async function(event) {
        event.preventDefault();  // a 태그의 기본 동작을 막음
        console.log('Submit button clicked');  // 디버깅용 로그
        const formData = new FormData(form); // 폼 데이터 수집
        formData.append('bookWriting', document.getElementById('writing-yes').checked ? 'Y' : 'N');
        formData.append('bookCover', document.getElementById('cover-yes').checked ? 'Y' : 'N');
        formData.append('bookDiscoloration', document.getElementById('discoloration-yes').checked ? 'Y' : 'N');
        formData.append('bookDamage', document.getElementById('damage-yes').checked ? 'Y' : 'N');
        formData.append('userNo', 1); // 예제 사용자 ID, 실제로는 세션 또는 인증된 사용자 정보 사용
        formData.append('tradeComplete', 'N');
        formData.append('tradeTime', new Date().toISOString());
        console.log('Form data collected:', formData);  // 디버깅용 로그
        try {
            const response = await fetch('/api/trade/write', {
                method: 'POST',
                body: formData // FormData를 그대로 전송
            });

            const data = await response.json();
            console.log('Server response:', data);  // 디버깅용 로그
            if (response.ok) {
                window.location.href = './trade/list.html';
            } else {
                console.error('Error:', data.message);
            }
        } catch (error) {
            console.error('Error:', error);
        }

        const tradeRequest = {
            title: formData.get('title'),
            bookWriting: document.getElementById('writing-yes').checked ? 'Y' : 'N',
            bookCover: document.getElementById('cover-yes').checked ? 'Y' : 'N',
            bookDiscoloration: document.getElementById('discoloration-yes').checked ? 'Y' : 'N',
            bookDamage: document.getElementById('damage-yes').checked ? 'Y' : 'N',
            productName: formData.get('product-name'),
            quantity: parseInt(formData.get('quantity')),
            price: parseFloat(formData.get('price')),
            description: formData.get('description'),
            categoryId: parseInt(formData.get('category-id')),
            image: formData.get('image'), // 이 값은 그대로 사용
            publisher: formData.get('publisher'),
            userNo: null, // 사용자 고유 번호 추가
            tradeComplete: false,
            tradeTime: new Date().toISOString() // 현재 시간을 가져오는 것을 고려해야 함
        };

        const tradeBoardData = {
            tradeTitle: tradeRequest.title,
            tradeProduct: tradeRequest.productName,
            tradeQuantity: tradeRequest.quantity,
            tradePrice: tradeRequest.price,
            tradeCondition: `필기 흔적: ${tradeRequest.bookWriting === 'Y' ? '있음' : '없음'},  
                             변색: ${tradeRequest.bookDiscoloration === 'Y' ? '있음' : '없음'}, 
                             훼손: ${tradeRequest.bookDamage === 'Y' ? '있음' : '없음'}`,
            tradePhoto: tradeRequest.image,
            tradeComplete: false,
            userNo: 1,
            tradeTime: tradeRequest.tradeTime // 현재 시간을 가져오는 것을 고려해야 함
        };

        const bookData = {
            bookWriting: tradeRequest.bookWriting,
            bookCover: tradeRequest.bookCover,
            bookDiscoloration: tradeRequest.bookDiscoloration,
            bookDamage: tradeRequest.bookDamage,
            userNo: tradeRequest.userNo,
            bookCompany: tradeRequest.publisher,
            tradeNum: 1 // 나중에 tradeNum이 생성된 후에 추가합니다.
        };

        console.log('Data to be sent:', {
            tradeBoardData: tradeBoardData,
            bookData: bookData
        });  // 디버깅용 로그

        try {
            const response = await fetch('/trade/write', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    tradeBoardData: tradeBoardData,
                    bookData: bookData
                })
            });

            const data = await response.json();
            console.log('Server response:', data);  // 디버깅용 로그
            if (data.success) {
                window.location.href = './trade/list.html';
            } else {
                console.error('Error:', data.message);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    });
});
*/


document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('trade-form');
    const submitButton = document.getElementById('submit-button');

    submitButton.addEventListener('click', async function(event) {
        event.preventDefault();  // 기본 동작을 막음
        console.log('Submit button clicked');  // 디버깅용 로그

        const formData = new FormData(form); // 폼 데이터 수집
        formData.append('categoryId', formData.get('category-id'));
        formData.append('tradeProduct', formData.get('product-name'));
        formData.append('bookWriting', document.getElementById('writing-yes').checked ? 'Y' : 'N');
        formData.append('bookCover', document.getElementById('cover-yes').checked ? 'Y' : 'N');
        formData.append('bookDiscoloration', document.getElementById('discoloration-yes').checked ? 'Y' : 'N');
        formData.append('bookDamage', document.getElementById('damage-yes').checked ? 'Y' : 'N');
        formData.append('tradeCondition',
            `필기 흔적: ${document.getElementById('writing-yes').checked ? '없음' : '있음'}, ` +
            `변색: ${document.getElementById('discoloration-yes').checked ? '없음' : '있음'}, ` +
            `훼손: ${document.getElementById('damage-yes').checked ? '없음' : '있음'}`);
        formData.append('userNo', 1); // 예제 사용자 ID, 실제로는 세션 또는 인증된 사용자 정보 사용
        formData.append('tradeComplete', 'N');
        formData.append('tradeTime', new Date().toISOString());

        formData.delete('category-id');
        formData.delete('product-name');

        // FormData 디버그
        for (var pair of formData.entries()) {
            console.log(pair[0] + ', ' + pair[1]);
        }

        try {
            const response = await fetch('/api/trade/write', {
                method: 'POST',
                body: formData // FormData를 그대로 전송
            });

            // 응답이 JSON 형식인지 확인하고 파싱
            const text = await response.text();
            let data;
            try {
                data = JSON.parse(text);
            } catch (error) {
                console.error('Failed to parse JSON response:', text);
                throw error;
            }

            console.log('Server response:', data);  // 디버깅용 로그
            if (response.ok) {
                window.location.href = './trade/list.html';
            } else {
                console.error('Error:', data.message);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    });
});
