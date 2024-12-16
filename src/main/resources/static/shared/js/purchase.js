class PurchaseManager {
    constructor() {
        this.purchaseItems = []; // List of purchase items
        this.totalAmount = 0;    // Total amount
        this.totalItems = 0;     // Total items
        this.init();             // Initialize event listeners
    }

    // Initialize event listeners
    init() {
        const self = this;

        // Fetch products when a supplier is selected
        $("#supplier").on("change", function () {
            const supplierId = parseInt( $(this).val(), 10); // Convert to integer
            if (!supplierId) {
                self.clearPurchase();
                return;
            }
            self.loadProductFragment(supplierId);
        });

        // Add item when button is clicked
        $(document).on("click", "#addItem", function () {
            const productId = parseInt( $("#productSelect").val(), 10); // Convert to integer
            const quantity = parseInt( $("#quantityInput").val(), 10); // Convert to integer

            if (productId && quantity > 0) {
                self.addOrUpdateItem(productId, quantity);
                self.updateTable();
            } else {
                alert("Please select a product and enter a valid quantity.");
            }
        });

        // Remove item from the list
        $(document).on("click", ".removeItem", function () {
            const productId = parseInt( $(this).data("product-id"), 10); // Convert to integer
			console.log("Removing item with productId:", productId);
            self.removeItem(productId);
            self.updateTable();
        });
       
		// Handle form submission via JavaScript to send JSON
		$("#purchase").on("submit", function (e) {
		    e.preventDefault(); // Prevent default form submission
		    if (self.purchaseItems.length === 0) { // Ensure the form cannot be submitted without items
		        alert("The purchase list cannot be empty.");
		        return;
		    }

		    const purchaseData = {
		        purchaseItems: self.purchaseItems
		    };

		    self.submitPurchase(purchaseData);
		});
    }

    // Load product fragment for the selected supplier
    loadProductFragment(supplierId) {
        $.get(`/purchases/get-products/${supplierId}`, (html) => {
            $("#productFragment").html(html);
            this.clearPurchase(); // Clear purchase items on supplier change
        }).fail(() => {
            console.log("Failed to load products for the selected supplier.");
        });
    }

    // Add a new item or update quantity if the product already exists
    addOrUpdateItem(productId, newQuantity) {
        const existingItem = this.purchaseItems.find(item => item.productId === productId);

        if (existingItem) {
            existingItem.quantity = newQuantity; // Update quantity with new input
            existingItem.subTotalPrice = existingItem.price * newQuantity;
        } else {
            const productName = $("#productSelect option:selected").text();
            const productPrice = parseFloat($("#productSelect option:selected").data("price"));
            this.purchaseItems.push({
                productId,
                productName,
                quantity: newQuantity,
                price: productPrice,
                subTotalPrice: productPrice * newQuantity
            });
        }
        this.updateTotals();
    }

    // Remove an item from the list
    removeItem(productId) {
		console.log("Before removal:", this.purchaseItems);
		this.purchaseItems = this.purchaseItems.filter(item => item.productId !== productId);
		console.log("After removal:", this.purchaseItems);		
        this.updateTotals();
    }

    // Update totals
    updateTotals() {
        this.totalAmount = this.purchaseItems.reduce((sum, item) => sum + item.subTotalPrice, 0);
        this.totalItems = this.purchaseItems.reduce((sum, item) => sum + item.quantity, 0);

        $("#totalAmount").text(this.totalAmount.toFixed(2));
        $("#totalItems").text(this.totalItems);
    }

    // Update the HTML table
    updateTable() {
        const tbody = $("table tbody");
        tbody.empty();

        this.purchaseItems.forEach(item => {
            tbody.append(`
                <tr>
                    <td>${item.productName}</td>
                    <td>${item.quantity}</td>
					<td>${item.price}</td>
                    <td>${item.subTotalPrice.toFixed(2)}</td>
                    <td><button class="removeItem btn btn-danger" data-product-id="${item.productId}">Remove</button></td>
                </tr>
            `);
        });
    }

    // Clear purchase items and reset table
    clearPurchase() {
        this.purchaseItems = [];
        this.updateTable();
        this.updateTotals();
    }

	// Submit the purchase data via AJAX (or fetch)
	submitPurchase(purchaseData) {

		// Get the selected supplier ID
		const supplierId = $("#supplier").val();

		// Ensure a supplier is selected before submitting
		if (!supplierId) {
		    alert("Please select a supplier.");
		    return;
		}

		// Add the supplier ID and total amount to the purchaseData
		purchaseData.supplierId = parseInt(supplierId, 10);
		purchaseData.totalAmount = this.totalAmount;
				
		console.log("Submitting Purchase Data:", JSON.stringify(purchaseData, null, 2));
		
		$.ajax({
		    url: "/purchases/add",  // URL to send the POST request to
		    type: "POST",           // HTTP method (POST)
		    contentType: "application/json",  // The content type we are sending to the server
		    data: JSON.stringify(purchaseData), // Converting the JavaScript object into JSON string
		    success: function(response) { 
		        // Handle the redirection manually
		        window.location.href = "/purchases/list"; // Redirect to the list page 
		    },
		    error: function(xhr, status, error) {
		        // This is executed if the request fails (HTTP 4xx, 5xx, or network issues)
		        console.error('There was a problem with the AJAX request:', error);
		        alert('Failed to save purchase. Please try again.');
		    }
		});
	}	
}

// Initialize PurchaseManager on document ready
$(document).ready(() => {
    new PurchaseManager();
});
