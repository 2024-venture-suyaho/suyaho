var stompClient = null;

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/public', function (message) {
            showMessage(JSON.parse(message.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendMessage() {
    var messageContent = $("#message").val();
    stompClient.send("/app/chat.sendMessage", {}, JSON.stringify({'mesText': messageContent, 'userName': $("#name").val()}));
    /*
    *
    * */
}

function showMessage(message) {
    $("#response").append("<tr><td>" + message.userName + ": " + message.mesText + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    connect();
    $("#send").click(function () {
        sendMessage();
    });
});