let currentPlayer = 'X';
let board = ["", "", "", "", "", "", "", "", ""];
let gameActive = true;

function handleClick(cellIndex) {
    if (!gameActive || board[cellIndex] !== "") return;

    board[cellIndex] = currentPlayer;
    document.getElementById("cell" + cellIndex).textContent = currentPlayer;

    if (checkWinner()) {
        document.getElementById("status").textContent = `Player ${currentPlayer} wins!`;
        gameActive = false;
    } else if (!board.includes("")) {
        document.getElementById("status").textContent = "It's a draw!";
        gameActive = false;
    } else {
        currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
        document.getElementById("status").textContent = `Player ${currentPlayer}'s turn`;
    }
}

function checkWinner() {
    const winPatterns = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8],
        [0, 3, 6], [1, 4, 7], [2, 5, 8],
        [0, 4, 8], [2, 4, 6]
    ];
    return winPatterns.some(pattern => {
        const [a, b, c] = pattern;
        return board[a] && board[a] === board[b] && board[a] === board[c];
    });
}

function resetGame() {
    board = ["", "", "", "", "", "", "", "", ""];
    currentPlayer = 'X';
    gameActive = true;
    for (let i = 0; i < 9; i++) {
        document.getElementById("cell" + i).textContent = "";
    }
    document.getElementById("status").textContent = `Player ${currentPlayer}'s turn`;
}