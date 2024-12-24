$(document).ready(function () {
    // Handle user selection
    $("#userSelect").on("change", function () {
        let selectedOption = $(this).find(":selected");

        if (selectedOption.val()) { 
			// Fetch the user data using attr() for data attributes
			var email = selectedOption.attr("data-email");
			var firstName = selectedOption.attr("data-firstname");
			var lastName = selectedOption.attr("data-lastname");

			// Check if the data attributes are being correctly passed
			console.log("Email:", email, "First Name:", firstName, "Last Name:", lastName);
		
			
			// Set the fields with the user data
			$("#email").val(email).prop("disabled", true);
			$("#firstName").val(firstName).prop("disabled", true);
			$("#lastName").val(lastName).prop("disabled", true); 

            // Set the hidden customer ID field, so it gets sent to the server
            $("#customerId").val(selectedOption.val());

        } else {
            // Clear and enable fields if no user is selected
            $("#email").val("").prop("disabled", false);
            $("#firstName").val("").prop("disabled", false);
            $("#lastName").val("").prop("disabled", false); 

            // Clear customer ID as well
            $("#customerId").val("");
        }
    });

    // Client-side validation before submitting the form
    $("form").on("submit", function (e) {
        let isValid = true;

        if (!$("#email").val().trim()) {
            alert("Email is required.");
            isValid = false;
        }

        if (!$("#firstName").val().trim()) {
            alert("First Name is required.");
            isValid = false;
        }

        if (!$("#lastName").val().trim()) {
            alert("Last Name is required.");
            isValid = false;
        }

        if (!$("#phone").val().trim()) {
            alert("Phone number is required.");
            isValid = false;
        }

        if (!$("#address").val().trim()) {
            alert("Address is required.");
            isValid = false;
        }

        if (!isValid) e.preventDefault(); // Prevent submission if validation fails
    });
});
