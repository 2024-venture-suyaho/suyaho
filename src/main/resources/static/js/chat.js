var stompClient = null;

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/public', function (chatMessage) {
            showMessage(JSON.parse(chatMessage.body));
        });
    });
}

function sendMessage() {
    var messageInput = document.getElementById('message-input');
    var messageContent = messageInput.value.trim();
    if(messageContent) {
        var chatMessage = {
            sender: 'user',  // 유저 정보를 동적으로 설정
            content: messageContent,
            type: 'CHAT'
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
}

function showMessage(message) {
    var messages = document.getElementById('messages');
    var messageElement = document.createElement('div');
    messageElement.appendChild(document.createTextNode(message.content));
    messages.appendChild(messageElement);
}

document.addEventListener('DOMContentLoaded', (event) => {
    connect();
});
