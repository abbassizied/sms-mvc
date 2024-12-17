class ShoppingCart {
    constructor() {
        this.cart = JSON.parse(localStorage.getItem('cart')) || {};
        this.updateCartCount();
        this.bindEvents();
    }

    // Add product to cart or update quantity
    addToCart(productId, productName, productPrice) {
        if (this.cart[productId]) {
            this.cart[productId].quantity += 1;
        } else {
            this.cart[productId] = {
                id: productId,
                name: productName,
                price: productPrice,
                quantity: 1,
            };
        }
        this.saveCart();
        this.updateCartCount();
        this.renderCart();
    }

    // Remove product from cart
    removeFromCart(productId) {
        delete this.cart[productId];
        this.saveCart();
        this.renderCart();
        this.updateCartCount();
    }

    // Update product quantity in cart
    updateQuantity(productId, quantity) {
        if (this.cart[productId]) {
            this.cart[productId].quantity = quantity;
            this.saveCart();
            this.renderCart();
            this.updateCartCount();
        }
    }

    // Clear the entire cart
    clearCart() {
        this.cart = {};
        this.saveCart();
        this.renderCart();
        this.updateCartCount();
    }

    // Save cart to localStorage
    saveCart() {
        try {
            localStorage.setItem('cart', JSON.stringify(this.cart));
        } catch (e) {
            console.error("Failed to save cart to localStorage:", e);
        }
    }

    // Get total items count
    getTotalCount() {
        return Object.values(this.cart).reduce((acc, item) => acc + item.quantity, 0);
    }

    // Get total cart amount
    getTotalAmount() {
        return Object.values(this.cart).reduce((acc, item) => acc + item.price * item.quantity, 0).toFixed(2);
    }

    // Update cart count in the UI
    updateCartCount() {
        $("#cart-count").text(this.getTotalCount());
    }

    // Render the shopping cart details in the UI
    renderCart() {
        const cartDetails = $("#cart-details");
        cartDetails.empty();

        if (Object.keys(this.cart).length === 0) {
            cartDetails.html("<p>Your cart is empty.</p>");
            return;
        }

        const table = $(`
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Product</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Unit Price</th>
                        <th scope="col">Total</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
        `);

        const tbody = table.find("tbody");

        Object.entries(this.cart).forEach(([id, item]) => {
            const row = $(`
                <tr>
                    <td>${item.name}</td>
                    <td><input type="number" class="form-control cart-quantity" data-id="${id}" value="${item.quantity}" min="1"></td>
                    <td>$${item.price.toFixed(2)}</td>
                    <td>$${(item.price * item.quantity).toFixed(2)}</td>
                    <td><button class="btn btn-danger btn-sm remove-item" data-id="${id}"><i class="bi bi-trash"></i></button></td>
                </tr>
            `);
            tbody.append(row);
        });

        cartDetails.append(table);
        cartDetails.append(`<div class="cart-total mt-3"><strong>Total: $${this.getTotalAmount()}</strong></div>`);
        cartDetails.append('<button id="buy-button" class="btn btn-success w-100 mt-2">Buy Now</button>');

        // Bind events
        this.bindEvents();
    }

    // Bind events for the cart
    bindEvents() {
        // Bind remove button events
        $(".remove-item").off("click").on("click", (e) => {
            const productId = $(e.currentTarget).data("id");
            this.removeFromCart(productId);
        });

        // Bind quantity change events
        $(".cart-quantity").off("change").on("change", (e) => {
            const productId = $(e.target).data("id");
            const newQuantity = parseInt($(e.target).val(), 10);
            if (newQuantity > 0 && !isNaN(newQuantity)) {
                this.updateQuantity(productId, newQuantity);
            } else {
                $(e.target).val(this.cart[productId].quantity); // Reset to the current quantity if invalid
            }
        });

        // Bind buy button event
        $("#buy-button").off("click").on("click", () => {
            this.submitCart();
        });

        // Bind add to cart button event
        $(".add-to-cart").off("click").on("click", (e) => {
            const button = $(e.currentTarget);
            const productId = button.data("id");
            const productName = button.data("name");
            const productPrice = parseFloat(button.data("price"));

            if (productId && productName && !isNaN(productPrice)) {
                this.addToCart(productId, productName, productPrice);
            } else {
                console.error("Failed to add product: Invalid data");
            }
        });

        // Bind toggle cart visibility event
        $("#toggle-cart").off("click").on("click", (e) => {
            e.preventDefault();
            $("#cart-card").toggle();
            if ($("#cart-card").is(":visible")) {
                this.renderCart();
            }
        });

        // Bind clear cart event
        $("#clear-cart").off("click").on("click", () => {
            this.clearCart();
        });

        // Bind confirm checkout event
        $("#confirm-checkout").off("click").on("click", () => {
            this.submitCart();
        });

        // Hide dropdown cart if clicked outside
        $(document).click((e) => {
            const cartContainer = $(".cart-container");
            if (!cartContainer.is(e.target) && cartContainer.has(e.target).length === 0) {
                $("#cart-card").hide();
            }
        });
    }

    // Submit the cart to the server
    submitCart() {
        const cartItems = Object.values(this.cart).map(item => ({
           // Product ID (Long value)
			productId: String(item.id),          // Ensure productId is a string
            quantity: item.quantity,
            subtotal: (item.price * item.quantity).toFixed(2)
        }));

        const orderData = {
            items: cartItems,
            totalAmount: this.getTotalAmount()
        };

        console.log("Submitting order data:", orderData); // Debugging line to check the payload

		const self = this;  // Capture the correct context
		
        $.ajax({
            url: "/orders/checkout",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(orderData),
            success: function(response) {
			    // Handle success response here
			    console.log("Response:", response);  // Log the response to the console
			       		
				// Fix for `this.clearCart()` not working by using proper context
				self.clearCart();  // Assuming 'self' is defined as the correct context for cart methods
				 
				alert("Purchase successful!");				
				
				// Redirect to the "Thank You" page
				window.location.href = "/thank-you";  // This is the URL to your "Thank You" page					
            },
			error: function(xhr, status, error) { 
				console.log("AJAX Error:", error);

				// If a non-logged-in user tries to submit, the response will be an HTML page (login page)
				if (xhr.status === 401 || xhr.status === 403) {
				    window.location.href = "/login";  // Redirect to login page
				} else {
				    console.error("Error submitting cart:", xhr.responseText);
				    alert("Failed to complete purchase. Please try again.");
				}
			}
        });
    }
}

// Initialize shopping cart
$(document).ready(() => {
    new ShoppingCart();
});
