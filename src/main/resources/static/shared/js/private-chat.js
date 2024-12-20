let stompClient = null;
let socket = new SockJS("/ws");
stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
    console.log("Connected: " + frame);
    stompClient.subscribe("/topic/private", function (messageOutput) {
        let message = JSON.parse(messageOutput.body);
		// console.log("server send: " + message);
        displayMessage(message); // Display the received message
    });
});


// Function to send a private message
function sendPrivateMessage() {
    
	let receiverId = document.getElementById("receiverId").value;  // Assuming you have an input field with the receiver's ID. 
	console.log("receiverId: " + receiverId);		
	
	let messageContent = document.getElementById("messageContent").value.trim(); // Trim to remove leading/trailing spaces

	if (!messageContent) {
	    alert("Message content cannot be empty.");
	    return; // Do not send if the message content is empty
	}
	
    // Prepare the message payload
    let message = {
        messageContent: messageContent
    };

    // Send the message to the WebSocket destination
    stompClient.send("/app/chat.private." + receiverId, {}, JSON.stringify(message));

    // Clear the message input field after sending
    document.getElementById("messageContent").value = "";
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



 