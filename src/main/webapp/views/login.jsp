<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/fade.js"></script>
</head>
<body>
</body>
<div class="login-box">
    <div class="login-header">
        <header>Login</header>
    </div>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="input-box">
            <label>
                <input name="userInput" type="text" class="input-field" placeholder="Username/E-mail" required>
            </label>
        </div>

        <div class="input-box">
            <label>
                <input name="passwd" type="password" class="input-field" placeholder="password" required>
            </label>

        </div>
        <div class="input-submit">
            <button class="submit-btn" id="submit"></button>
            <label for="submit">Sign-In</label>
        </div>
        <% String error = (String) request.getAttribute("errorMessage"); %>
        <% if (error != null && !error.isEmpty()) { %>
        <div class="error-message" id="login-error">
            <%= error %>
        </div>
        <% } %>

        <div class="sign-up-link">
            <p>Don't have an account? <a href="signup.jsp">Sign Up</a></p>
        </div>
    </form>
</div>
</html>
