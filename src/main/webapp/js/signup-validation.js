document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("signupForm");

    form.addEventListener("submit", function (e) {
        let valid = true;

        // Clear previous error messages
        document.getElementById("usernameError").textContent = "";
        document.getElementById("emailError").textContent = "";
        document.getElementById("passwordError").textContent = "";
        document.getElementById("confirmPasswordError").textContent = "";

        const username = document.getElementById("username").value.trim();
        const email = document.getElementById("email").value.trim();
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirmPassword").value;

        const usernamePattern = /^[a-zA-Z][a-zA-Z0-9_]{2,19}$/;
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&]).{8,}$/;

        if (!usernamePattern.test(username)) {
            document.getElementById("usernameError").textContent =
                "Username must start with a letter and be 3-20 characters. Only letters, numbers, and underscores allowed.";
            valid = false;
        }

        if (!emailPattern.test(email)) {
            document.getElementById("emailError").textContent = "Enter a valid email address.";
            valid = false;
        }

        if (!passwordPattern.test(password)) {
            document.getElementById("passwordError").textContent =
                "Password must be at least 8 characters with uppercase, lowercase, number, and special character.";
            valid = false;
        }

        if (password !== confirmPassword) {
            document.getElementById("confirmPasswordError").textContent = "Passwords do not match.";
            valid = false;
        }

        // If validation fails, prevent form submission and apply fade effect
        if (!valid) {
            e.preventDefault(); // Prevent form submission

            // Apply the fade effect to the error messages
            const errorElements = document.querySelectorAll('.error');
            errorElements.forEach(error => {
                if (error.textContent.trim() !== '') {
                    setTimeout(() => {
                        error.style.transition = "opacity 1s ease-out";
                        error.style.opacity = "0"; // Fade out the error message

                        // After fadeout completes, reset form state
                        setTimeout(() => {
                            // Reset all form fields
                            form.reset();

                            // Reset error messages and opacity
                            errorElements.forEach(error => {
                                error.style.opacity = "1"; // Restore full opacity
                                error.textContent = "";    // Clear any error text
                            });
                        }, 1000); // Wait for fade-out duration (1 second)
                    }, 3000); // Wait 3 seconds before fading
                }
            });
        }
    });
});
