$(document).ready(function() {
    // Keep track of orderItems dynamically, including the index for hidden fields
    var itemIndex = $("input[name^='orderItems']").length;  // Start with the current number of orderItems in the form

    $('#addProductBtn').click(function() { 	
		
        // Get selected product and quantity
        var productId = $('#productSelect').val();
        var quantity = $('#quantity').val();

        if (productId && quantity) {
            // Get the product name using the selected product ID
            var productName = $('#productSelect option:selected').text();

            // Check if the product is already in the list
            var existingItem = $('#orderList li[data-product-id="' + productId + '"]');

            if (existingItem.length > 0) {
				// Update the quantity if the product already exists 
				var newQuantity = parseInt(quantity); 

                // Update the displayed quantity
                existingItem.find('.quantity-text').text(newQuantity);

                // Update the hidden quantity input associated with the product
                existingItem.find('input[name^="orderItems"]').each(function() {
                    if ($(this).attr('name').includes('quantity')) {
                        $(this).val(newQuantity);  // Update the hidden quantity input
                    }
                });
            } else {
                // Product is not in the list, create a new list item
                var listItem = $('<li class="list-group-item" data-product-id="' + productId + '">' +
                    productName + ' - Quantity: <span class="quantity-text">' + quantity + '</span>' +
                    ' <button type="button" class="btn btn-danger btn-sm float-right remove-btn">Remove</button>' +
                    '</li>');

                // Append to the order list
                $('#orderList').append(listItem);

                // Create hidden fields for the orderItem, using the dynamic index
                var hiddenProductId = $('<input>').attr({
                    type: 'hidden',
                    name: 'orderItems[' + itemIndex + '].product.id',  // Use dynamic index
                    value: productId
                });

                var hiddenQuantity = $('<input>').attr({
                    type: 'hidden',
                    name: 'orderItems[' + itemIndex + '].quantity', // Use dynamic index
                    value: quantity
                });

                // Append hidden inputs to the form
                $('form').append(hiddenProductId, hiddenQuantity);

                // Increment the index for the next product
                itemIndex++;

                // Add event listener for the remove button
                listItem.find('.remove-btn').click(function() {
                    // Remove the product from the list
                    listItem.remove();

                    // Optionally, remove hidden inputs
                    $('input[name="orderItems[' + itemIndex + '].product.id"][value="' + productId + '"]').remove();
                    $('input[name="orderItems[' + itemIndex + '].quantity"][value="' + quantity + '"]').remove();
                });
            }
        } else {
            alert('Please select a product and quantity.');
        }
    });
});
