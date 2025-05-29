<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="sessionCheck.jspf" %>
<html>
<head>
    <title>Weather</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
<div class="login-box">
    <div class="login-header">Weather</div>

    <form action="${pageContext.request.contextPath}/weatherApp" method="get">
        <div class="input-box">
            <label>
                <input type="text" class="input-field" name="city" placeholder="Enter City Name" required>
            </label>
        </div>
        <div class="input-submit">
            <button type="submit" class="submit-btn" style="color: #dfdfdf">Get Weather</button>
        </div>
    </form>

    <%
        String temp = (String) request.getAttribute("temp");
        String desc = (String) request.getAttribute("desc");
        String city = (String) request.getAttribute("city");
        String error = (String) request.getAttribute("error");

        if (error != null) {
    %>
    <div class="error-message"><%= error %>
    </div>
    <% } else if (temp != null && desc != null && city != null) { %>
    <div class="info-text" style="margin-top: 20px;">
        <p><strong>City:</strong> <%= city %>
        </p>
        <p><strong>Temperature:</strong> <%= temp %> °C</p>
        <p><strong>Condition:</strong> <%= desc %>
        </p>
    </div>
    <% } %>

    <div class="sign-up-link" style="margin-top: 20px;">
        <a href="dashboard.jsp">← Back to Dashboard</a>
    </div>
</div>
</body>
</html>

