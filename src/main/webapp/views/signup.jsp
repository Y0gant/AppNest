<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/signup-validation.js"></script>
    <script src="${pageContext.request.contextPath}/js/fade.js"></script>
</head>
<body>
<div class="login-box">
    <div class="login-header">
        <header>Sign Up</header>
    </div>
    <form id="signupForm" action="${pageContext.request.contextPath}/sign-up" method="post">
        <div class="input-box">
            <label>
                <input type="text" id="username" name="username" class="input-field" placeholder="Username" required>
            </label>
            <span class="error" id="usernameError" style="color: red;"></span>
        </div>
        <div class="input-box">
            <label>
                <input type="email" id="email" name="email" class="input-field" placeholder="Email" required>
            </label>
            <span class="error" id="emailError" style="color: red;"></span>
        </div>
        <div class="input-box">
            <label>
                <input type="password" id="password" name="passwd" class="input-field" placeholder="Password" required>
            </label>
            <span class="error" id="passwordError" style="color: red;"></span>
        </div>
        <div class="input-box">
            <label>
                <input type="password" id="confirmPassword" class="input-field" placeholder="Confirm Password" required>
            </label>
            <span class="error" id="confirmPasswordError" style="color: red;"></span>
        </div>
        <div class="input-submit">
            <button class="submit-btn" id="signup"></button>
            <label for="signup">Sign Up</label>
        </div>
        <% String error = (String) request.getAttribute("errorMessage"); %>
        <% if (error != null && !error.isEmpty()) { %>
        <div class="error-message" id="login-error">
            <%= error %>
        </div>
        <% } %>
        <div class="sign-up-link">
            <p>Already have an account? <a href="login.jsp">Login</a></p>
        </div>
    </form>
</div>
</body>
</html>