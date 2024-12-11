// Establish a STOMP connection over the WebSocket
let socket = new SockJS('/ws'); // Use SockJS if configured in Spring for compatibility
let stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);

    // Subscribe to the public chat topic
    stompClient.subscribe('/topic/broadcast', function (message) {
        displayMessage(JSON.parse(message.body));
    });
});

function sendPublicMessage() {
    if (stompClient && stompClient.connected) {
        // Construct a msg object containing the data the server needs to process the message from the chat client.
        const msg = {
            messageContent: document.getElementById("message").value,
        };

        // Send the message to the broadcast channel via STOMP
        stompClient.send("/app/chat.broadcast", {}, JSON.stringify(msg));

        // Clear the input field after sending the message
        document.getElementById("message").value = "";
    } else {
        console.error("WebSocket connection is not open.");
    }
}






// Display the received message in the chat area 
function displayMessage(message) {
    let messageElem = document.createElement('div');
    

	var currentUserEmail =  [[#model.currentUser.email]];
	console.log(currentUserEmail);	
	console.log("${message.sender.email}");
	
	// Check if the message sender is the current user
	const isCurrentUser = "${message.sender.email}" === currentUserEmail;

	// Add appropriate HTML for current user or other users
	if (isCurrentUser) {
	    // For current user's messages
	    messageElem.innerHTML = `
	        <div class="media mb-3 flex-row-reverse">
	            <img src="${message.sender.photoUrl ? '/uploads/' + message.sender.photoUrl : '/shared/img/profile-icon.png'}"
	                 class="rounded-circle ml-3" width="40" height="40" alt="Avatar">
	            <div class="media-body chat-message-me text-right">
	                <h6 class="mt-0">You
	                    <small class="text-muted ml-2">${message.updatedAt}</small>
	                </h6>
	                <p>${message.messageContent}</p>
	            </div>
	        </div>`;
	} else {
	    // For other users' messages
	    messageElem.innerHTML = `
	        <div class="media mb-3">
	            <img src="${message.sender.photoUrl ? '/uploads/' + message.sender.photoUrl : '/shared/img/profile-icon.png'}"
	                 class="rounded-circle mr-3" width="40" height="40" alt="Avatar">
	            <div class="media-body chat-message-other">
	                <h6 class="mt-0">
	                    <span>${message.sender.firstName} ${message.sender.lastName}</span>
	                    <small class="text-muted ml-2">${message.updatedAt}</small>
	                </h6>
	                <p>${message.messageContent}</p>
	            </div>
	        </div>`;
	}

    
    // Append the message element to the conversation
    document.getElementById('conversation').appendChild(messageElem);
}