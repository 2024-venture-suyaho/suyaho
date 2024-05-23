document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('trade-form');
    const submitButton = document.getElementById('submit-button');
    // 뒤로가기버튼구현
    const backButton = document.getElementById("backButton");
    if (backButton) {
        backButton.addEventListener("click", function() {
            window.history.back();
        });
    }
    submitButton.addEventListener('click', async function(event) {
        event.preventDefault();  // 기본 동작을 막음
        console.log('Submit button clicked');  // 디버깅용 로그

        const formData = new FormData(form); // 폼 데이터 수집
        formData.append('categoryId', formData.get('category-id'));
        formData.append('tradeProduct', formData.get('product-name'));
        formData.append('bookWriting', document.getElementById('writing-yes').checked ? 'true' : 'false');
        formData.append('bookCover', document.getElementById('cover-yes').checked ? 'true' : 'false');
        formData.append('bookDiscoloration', document.getElementById('discoloration-yes').checked ? 'true' : 'false');
        formData.append('bookDamage', document.getElementById('damage-yes').checked ? 'true' : 'false');
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
                window.location.href = '/posts';  // 등록 후 /posts로 이동
            } else {
                console.error('Error:', data.message);
                window.location.href = '/posts';  // 등록 후 /posts로 이동
            }
        } catch (error) {
            console.error('Error:', error);
            window.location.href = '/posts';  // 등록 후 /posts로 이동
        }
    });
});
