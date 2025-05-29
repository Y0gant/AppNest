<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="sessionCheck.jspf" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - AppNest</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="dashboard-box">
    <div class="welcome-text">Welcome, <%= session.getAttribute("userName") %>!</div>
    <div class="info-text">You're logged in as <%= session.getAttribute("Email") %>
    </div>

    <div class="app-links">
        <form action="weather.jsp" method="get">
            <button class="app-btn">Weather</button>
        </form>
        <form action="ticTacToe.jsp" method="get">
            <button class="app-btn">Tic Tac Toe</button>
        </form>
        <form action="stonePaperScissor.jsp" method="get">
            <button class="app-btn">Stone Paper Scissor</button>
        </form>
    </div>

    <form action="logout.jsp" method="post">
        <button class="logout-btn">Logout</button>
    </form>
</div>

</body>
</html>
