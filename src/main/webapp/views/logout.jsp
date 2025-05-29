<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="sessionCheck.jspf" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logout</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="login-box">
    <div class="login-header">
        <header>Logout</header>
    </div>
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <p style="text-align: center; font-size: 1.1rem;">Are you sure you want to logout?</p>
        <div style="display: flex; justify-content: center; gap: 20px; margin-top: 20px;">
            <input class="submit-btn" name="action" style="color: #dfdfdf;" type="submit" value="Confirm">
            <input class="submit-btn" name="action" style="color: #dfdfdf;" type="submit" value="Cancel">
        </div>
    </form>
</div>
</body>
</html>
