$(document).ready(function () {
    // Connect to WebSocket and listen for notifications
    connectWebSocket();

    // Fetch notifications initially to populate the list (Optional)
    fetchNotifications();
});



let stompClient = null;

// Connect to WebSocket server
function connectWebSocket() {
    const socket = new SockJS('/ws'); // Ensure this matches the WebSocket URL on the server
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        // Subscribe to the topic where notifications are sent
        stompClient.subscribe('/topic/notifications', function (notificationMessage) {
            const notification = JSON.parse(notificationMessage.body);
            displayOneNotification(notification);
            updateUnseenCount();
        });
    });
}





// Fetch initial notifications (Optional, can be skipped if WebSocket updates are enough)
function fetchNotifications() {
    $.ajax({
        url: '/notifications/contact-messages/unseen', // Fetch only unseen contact message notifications
        type: 'GET',
        success: function (data) {
            displayNotifications(data);
            updateUnseenCount();
        },
        error: function () {
            console.error('Error fetching notifications');
        }
    });
}



// Display notifications in the dropdown
function displayNotifications(notifications) {
    const notificationList = $('#notification_message_list');
    notificationList.empty(); // Clear the list before appending new items

    if (notifications.length === 0) {
        notificationList.append('<li>No new contact messages.</li>');
        return;
    }

    notifications.forEach(notification => {
        const isSeenClass = notification.seen ? '' : 'notification-unread';
		const listItem = `
		    <li class="${isSeenClass}" data-id="${notification.id}">
		        <a href="javascript:void(0)" onclick="markAsSeen(${notification.id})">  
		            <p>${notification.body}</p> 
		            <span class="created-at" data-created-at="${notification.createdAt}"></span> 
		        </a>
		    </li>
		`;
        notificationList.append(listItem);
    });

    // Update timeAgo text for all created-at elements after appending notifications
    updateTimeAgo();
}


// Mark notification as seen
function markAsSeen(notificationId) {
    $.ajax({
        url: '/notifications/mark-seen/' + notificationId,
        type: 'POST',
        success: function () {
            // Update the notification list after marking as seen
            fetchNotifications();
        },
        error: function () {
            console.error('Error marking notification as seen');
        }
    });
}


// Display a single notification (used when a new notification is received)
function displayOneNotification(notification) {
    const notificationList = $('#notification_message_list');

    const isSeenClass = notification.seen ? '' : 'notification-unread';
    const listItem = `
        <li class="${isSeenClass}" data-id="${notification.id}">
            <a href="javascript:void(0)" onclick="markAsSeen(${notification.id})">  
                <p>${notification.body}</p>
                <span class="created-at" data-created-at="${notification.createdAt}"></span>
            </a>
        </li>
    `;
    // Append the new notification to the end of the list
    notificationList.append(listItem);

    // Update timeAgo text for the created-at element
    updateTimeAgo();
}



// Update unseen message count dynamically
function updateUnseenCount() {
    $.ajax({
        url: '/notifications/contact-messages/unseen/count', // Update to get unseen count from server
        type: 'GET',
        success: function (data) {
            const unseenCount = data.count;
            $('#unseen-message-count').text(unseenCount);
            $('#notification-badge').text(unseenCount);
            $('#new-messages-count').text(`${unseenCount} New Messages`);
        },
        error: function () {
            console.error('Error fetching unseen notifications count');
        }
    });
}
