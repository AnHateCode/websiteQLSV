<!DOCTYPE html>
<html>
<head>
<title>Demo WebSocket</title>
<style>
    /* CSS cho nút "Open Chat" */
   .openChatButton {
    /* Đặt nút bên phải */
    position: fixed;
    top: 85%;
    right: 20px; /* Đặt khoảng cách từ cạnh phải */
    transform: translateY(-50%); /* Dịch nút theo trục y */
    /* Các thuộc tính khác */
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

    .openChatButton:hover {
        background-color: #0056b3; /* Tô màu nền nhạt hơn khi di chuột qua */
    }

    /* CSS cho popup */
    .popup-container {
        display: none;
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 400px;
        background-color: #f1f1f1;
        border-radius: 10px;
        padding: 20px;
        box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.3);
        z-index: 1000;
    }

    .close {
        position: absolute;
        top: 10px;
        right: 10px;
        cursor: pointer;
    }
</style>
</head>
    <button onclick="toggleChatPopup()" class="openChatButton">Open Chat</button>

    <!-- Popup chứa form chat -->
    <div id="chatPopup" class="popup-container">
        <span class="close" onclick="toggleChatPopup()">&times;</span>
        <h2>Chat Room</h2>
        <input id="textMessage" type="text" />
        <input onclick="sendMessage()" value="Send Message" class="sendMessageButton" type="button" />
        <br/><br/>
        <textarea id="textAreaMessage" rows="10" cols="40"></textarea>
    </div>

    <script type="text/javascript">
        var popupVisible = false;

        function toggleChatPopup() {
            var chatPopup = document.getElementById('chatPopup');
            popupVisible = !popupVisible; // Đảo ngược trạng thái hiển thị của popup
            chatPopup.style.display = popupVisible ? 'block' : 'none';
        }

        var websocket = new WebSocket("ws://localhost:8080/QLSV/chatRoomServer");
        websocket.onopen = function(message) {processOpen(message);};
        websocket.onmessage = function(message) {processMessage(message);};
        websocket.onclose = function(message) {processClose(message);};
        websocket.onerror = function(message) {processError(message);};

        function processOpen(message) {
            document.getElementById('textAreaMessage').value += "Server connect... \n";
        }

        function processMessage(message) {
            console.log(message);
            document.getElementById('textAreaMessage').value += message.data + " \n";
        }

        function processClose(message) {
            document.getElementById('textAreaMessage').value += "Server Disconnect... \n";
        }

        function processError(message) {
            document.getElementById('textAreaMessage').value += "Error... " + message +" \n";
        }

        function sendMessage() {
            if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
                websocket.send(document.getElementById('textMessage').value);
                document.getElementById('textMessage').value = "";
            }
        }
    </script>
</body>
</html>
