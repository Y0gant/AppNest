<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="sessionCheck.jspf" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Stone Paper Scissors - AppNest</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="login-box sps-container">
    <div class="login-header">
        <header>Stone Paper Scissors</header>
    </div>

    <div class="sps-buttons">
        <form action="${pageContext.request.contextPath}/spsGame" method="post">
            <input type="hidden" name="userMove" value="stone"/>
            <button class="sps-btn" type="submit">Stone</button>
        </form>

        <form action="${pageContext.request.contextPath}/spsGame" method="post">
            <input type="hidden" name="userMove" value="paper"/>
            <button class="sps-btn" type="submit">Paper</button>
        </form>

        <form action="${pageContext.request.contextPath}/spsGame" method="post">
            <input type="hidden" name="userMove" value="scissors"/>
            <button class="sps-btn" type="submit">Scissors</button>
        </form>
    </div>

    <div class="result">
        <%
            String result = (String) request.getAttribute("result");
            String userMove = (String) request.getAttribute("userMove");
            String computerMove = (String) request.getAttribute("computerMove");

            if (result != null) {
        %>
        You chose <strong><%= userMove %>
    </strong>.<br/>
        Computer chose <strong><%= computerMove %>
    </strong>.<br/>
        <strong>Result: <%= result %>
        </strong>
        <% } %>
    </div>
    <div class="sign-up-link" style="margin-top: 20px;">
        <a href="dashboard.jsp">← Back to Dashboard</a>
    </div>
</div>
</body>
</html>
