class ShoppingCart {
    constructor() {
        // Initialize cart from localStorage or create an empty object
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
    }

    // Remove product from cart
    removeFromCart(productId) {
        delete this.cart[productId];
        this.saveCart();
        this.renderCart();
        this.updateCartCount();
    }

    // Save cart to localStorage
    saveCart() {
        localStorage.setItem('cart', JSON.stringify(this.cart));
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
        document.getElementById('cart-count').textContent = this.getTotalCount();
    }

    // Render the shopping cart details in the UI
	renderCart() {
	    const cartDetails = document.querySelector("#cart-details");
	    if (!cartDetails) {
	        console.error("Element with id 'cart-details' not found in the DOM.");
	        return;
	    }

	    cartDetails.innerHTML = ""; // Clear existing cart details

	    if (Object.keys(this.cart).length === 0) {
	        cartDetails.innerHTML = "<p>Your cart is empty.</p>";
	        return;
	    }

	    // Create the table structure
	    let tableHTML = `
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
	            <tbody>
	    `;

	    Object.entries(this.cart).forEach(([id, item]) => {
	        tableHTML += `
	            <tr>
	                <td>${item.name}</td>
	                <td>${item.quantity}</td>
	                <td>$${item.price.toFixed(2)}</td>
	                <td>$${(item.price * item.quantity).toFixed(2)}</td>
	                <td>
	                    <button class="remove-item btn btn-sm btn-danger" data-id="${id}">
	                        <i class="bi bi-trash3"></i>
	                    </button>
	                </td>
	            </tr>
	        `;
	    });

	    tableHTML += `
	            </tbody>
	        </table>
	        <div class="cart-total mt-3">
	            <strong>Total: $${this.getTotalAmount()}</strong>
	        </div>
	        <button id="buy-button" class="btn btn-success mt-2">Buy Now</button>
	    `;

	    // Insert the table into the cart details container
	    cartDetails.innerHTML = tableHTML;

	    // Re-bind remove button events
	    document.querySelectorAll(".remove-item").forEach((button) => {
	        button.addEventListener("click", (e) => {
	            const productId = e.target.dataset.id;
	            this.removeFromCart(productId);
	        });
	    });

	    // Bind "Buy Now" button event
	    const buyButton = document.querySelector("#buy-button");
	    if (buyButton) {
	        buyButton.addEventListener("click", () => {
	            this.submitCart();
	        });
	    }
	}



    // Send the shopping cart to the server
    submitCart() {
        const cartItems = Object.values(this.cart);

        if (cartItems.length === 0) {
            alert('Your cart is empty. Please add some products first.');
            return;
        }

        // Example AJAX request to submit the cart
        fetch('/checkout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(cartItems),
        })
            .then((response) => {
                if (response.ok) {
                    alert('Purchase successful!');
                    this.cart = {}; // Clear cart on successful purchase
                    this.saveCart();
                    this.updateCartCount();
                    this.renderCart();
                } else {
                    alert('Failed to complete purchase. Please try again.');
                }
            })
            .catch((error) => {
                console.error('Error submitting cart:', error);
                alert('Failed to complete purchase. Please try again.');
            });
    }

    // Bind click events
	bindEvents() {
	    const toggleCartButton = document.querySelector('#toggle-cart');
	    const closeCartButton = document.querySelector('#close-cart');
	    const cartCard = document.querySelector('#cart-card');

	    // Toggle cart dropdown visibility
	    toggleCartButton.addEventListener('click', (e) => {
	        e.preventDefault();
	        cartCard.style.display = cartCard.style.display === 'none' || cartCard.style.display === '' ? 'block' : 'none';
	        if (cartCard.style.display === 'block') {
	            this.renderCart(); // Ensure cart details are up to date
	        }
	    });

	    // Close cart dropdown
	    closeCartButton.addEventListener('click', () => {
	        cartCard.style.display = 'none';
	    });

	    // Hide dropdown cart if clicked outside
	    document.addEventListener('click', (e) => {
	        const cartContainer = document.querySelector('.cart-container');
	        if (!cartContainer.contains(e.target)) {
	            cartCard.style.display = 'none';
	        }
	    });

	    // Other event bindings for add-to-cart buttons
	    const addToCartButtons = document.querySelectorAll('.add-to-cart');
	    addToCartButtons.forEach((button) => {
	        button.addEventListener('click', (e) => {
	            const productId = e.currentTarget.dataset.id;
	            const productName = e.currentTarget.dataset.name;
	            const productPrice = parseFloat(e.currentTarget.dataset.price);

	            if (productId && productName && !isNaN(productPrice)) {
	                this.addToCart(productId, productName, productPrice);
	                alert(`${productName} has been added to the cart!`);
	            } else {
	                console.error('Failed to add product: Invalid data');
	            }
	        });
	    });
	}


}


