document.addEventListener('DOMContentLoaded', function() {
    const images = document.querySelectorAll('.book-image img');
    images.forEach(image => {
        image.addEventListener('error', function() {
            this.src = '/img/Ckong.jpg';
        });
    });
    const backButton = document.getElementById("backButton");
    if (backButton) {
        backButton.addEventListener("click", function() {
            window.history.back();
        });
    }
});

