document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('lost-form');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = new FormData(form);
        const tradeRequest = {
            lostTitle: formData.get('lost-title'),
            lostPosition: formData.get('lost-position'),
            lostText: formData.get('lost-text'),
            lostPhoto: formData.get('lost-photo'),
            userNo: getCurrentUserNo(), // 사용자 고유 번호 가져오는 함수
            lostStatus: '보관중' // 초기 상태는 '보관중'으로 설정
        };

        fetch('/lost/write', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(tradeRequest)
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    // 작업이 완료된 후 수행할 작업
                    window.location.href = './lost/list.html'; // 분실물 목록 페이지로 이동
                } else {
                    console.error('Error:', data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
});