<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sign Up</title>
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="login-box">
  <div class="login-header">
    <header>Sign Up</header>
  </div>
  <form>
    <div class="input-box">
      <input type="text" class="input-field" placeholder="Username" required>
    </div>
    <div class="input-box">
      <input type="email" class="input-field" placeholder="Email" required>
    </div>
    <div class="input-box">
      <input type="password" class="input-field" placeholder="Password" required>
    </div>
    <div class="input-box">
      <input type="password" class="input-field" placeholder="Confirm Password" required>
    </div>
    <div class="input-submit">
      <button class="submit-btn" id="signup"></button>
      <label for="signup">Sign Up</label>
    </div>
    <div class="sign-up-link">
      <p>Already have an account? <a href="login.jsp">Login</a></p>
    </div>
  </form>
</div>
</body>
</html>