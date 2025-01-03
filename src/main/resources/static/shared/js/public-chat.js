// Establish a STOMP connection over the WebSocket
let socket = new SockJS('/ws'); // Use SockJS if configured in Spring for compatibility
let stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
    // console.log('Connected: ' + frame);

    // Subscribe to the public chat topic
    stompClient.subscribe('/topic/broadcast', function (message) {
		var msg = JSON.parse(message.body);  // Parse the message

		// Display the message with custom styles
		displayMessage(msg);
    });
});

function sendPublicMessage() {
    if (stompClient && stompClient.connected) {
        // Construct a msg object containing the data the server needs to process the message from the chat client.
        const msg = {
            messageContent: document.getElementById("message").value,
        };

		console.log('msg: ' + JSON.stringify(msg));
		
        // Send the message to the broadcast channel via STOMP
        stompClient.send("/app/chat.broadcast", {}, JSON.stringify(msg));

        // Clear the input field after sending the message
        document.getElementById("message").value = "";
    } else {
        console.error("WebSocket connection is not open.");
    }
}

// Display the received message in the chat area 
function displayMessage(msg) {
    // Get the current username
    const username = $('#username').val();

    console.log("username: " + username);
    console.log("msg.sender.email: " + msg.sender.email);
    console.log("msg.sender.photoUrl: " + msg.sender.photoUrl);

    // Determine if the message is from the current user
    const isCurrentUser = msg.sender.email === username;

    // Create the message HTML dynamically
    let messageHtml = `
        <div class="media mb-3 ${isCurrentUser ? 'flex-row-reverse' : ''}">
            <img src="${msg.sender.photoUrl ? '/uploads/' + msg.sender.photoUrl : '/shared/img/profile-icon.png'}"
                 class="rounded-circle ${isCurrentUser ? 'ml-3' : 'mr-3'}" width="40" height="40" alt="Avatar">
            <div class="media-body ${isCurrentUser ? 'chat-message-me' : 'chat-message-other'}">
                <h6 class="mt-0">
                    ${msg.sender.firstName} ${msg.sender.lastName}
                    <small class="text-muted ml-2">${msg.createdAt}</small>
                </h6>
                <p>${msg.messageContent}</p>
            </div>
        </div>
    `;

    // Append the message to the conversation area
    $('#conversation').append(messageHtml);

    // Scroll to the bottom of the chat area
    $('#conversation').scrollTop($('#conversation')[0].scrollHeight);
}
