<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="login-box">
  <div class="login-header">
    <header>Login</header>
  </div>
  <div class="input-box">
    <input type="text" class="input-field" placeholder="Username" required>
  </div>

  <div class="input-box">
    <input type="password" class="input-field" placeholder="password" required>
  </div>
  <div class="forgot">
    <a href="#" class="forgot-link">Forgot Password?</a>
  </div>
  <div class="input-submit">
    <button class="submit-btn" id="submit"></button>
    <label for="submit">Sign-In</label>
  </div>
  <div class="sign-up-link">
    <p>Don't have an account? <a href="signup.jsp">Sign Up</a></p>
  </div>
</div>
</body>
</html>
