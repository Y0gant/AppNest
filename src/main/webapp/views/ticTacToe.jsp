<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="sessionCheck.jspf" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tic Tac Toe - AppNest</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/gameChecker.js"></script>
</head>
<body>
<div class="login-box">
    <div class="login-header">
        <header>Tic Tac Toe</header>
    </div>
    <div class="game-board">
        <div id="cell0" class="cell" onclick="handleClick(0)"></div>
        <div id="cell1" class="cell" onclick="handleClick(1)"></div>
        <div id="cell2" class="cell" onclick="handleClick(2)"></div>
        <div id="cell3" class="cell" onclick="handleClick(3)"></div>
        <div id="cell4" class="cell" onclick="handleClick(4)"></div>
        <div id="cell5" class="cell" onclick="handleClick(5)"></div>
        <div id="cell6" class="cell" onclick="handleClick(6)"></div>
        <div id="cell7" class="cell" onclick="handleClick(7)"></div>
        <div id="cell8" class="cell" onclick="handleClick(8)"></div>
    </div>
    <div class="status" id="status">Player X's turn</div>
    <div style="text-align:center;">
        <button class="reset-btn" onclick="resetGame()">Reset Game</button>
    </div>
</div>
</body>
</html>
