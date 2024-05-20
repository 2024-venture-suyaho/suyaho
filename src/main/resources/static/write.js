const form = document.getElementById('post-form');
const submitBtn = document.querySelector('.submit-btn');

form.addEventListener('submit', (e) => {
    e.preventDefault();
    const formData = new FormData(form);


    const title = formData.get('title');
    const condition = formData.get('condition');
    const cover = formData.get('cover');
    const pageColor = formData.get('page-color');
    const pageWear = formData.get('page-wear');
    const author = formData.get('author');
    const publisher = formData.get('publisher');
    const productName = formData.get('product-name');
    const quantity = formData.get('quantity');
    const price = formData.get('price');
    const description = formData.get('description');
    const image = formData.get('image');

    // 폼 데이터 처리 후 list 페이지로 이동
    window.location.href = 'list.html';
});

submitBtn.addEventListener('click', (e) => {
    e.preventDefault();
    form.dispatchEvent(new Event('submit'));
});