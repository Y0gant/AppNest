document.addEventListener("DOMContentLoaded", function () {
    const errorBox = document.getElementById("login-error");
    if (errorBox) {
        setTimeout(() => {
            errorBox.style.opacity = "0";
        }, 3000); // wait 3 seconds then fade
    }
});
