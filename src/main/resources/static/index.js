const socket = new WebSocket("ws://localhost:8080/ws/chat");

function testSend() {
    let data = {
        messageType : 'ENTER',
        chatRoomId : 1,
        senderId : 1,
        message : document.querySelector('#socket-text-area').value,
    }
    socket.send(JSON.stringify(data));
    document.querySelector('#socket-text-area').value = ''; // 전송 후 초기화
}

socket.onopen = function() {
    // todo : 소켓 열렸을 떄
};

// 소켓을 통해 메시지를 받을 경우
socket.onmessage = function(event) {
    const eventResult = JSON.parse(event.data);
    if (!eventResult.message) {
        return;
    }
    document.querySelector('#text-area').innerHTML += '<p>' + eventResult.message + '</p>';

};

socket.onclose = function(event) {
    // todo : 소켓 닫힐 떄
};