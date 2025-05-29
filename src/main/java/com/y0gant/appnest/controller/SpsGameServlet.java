package com.y0gant.appnest.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;

@WebServlet("/spsGame")
public class SpsGameServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(SpsGameServlet.class);
    private static final String[] MOVES = {"stone", "paper", "scissors"};

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userMove = req.getParameter("userMove");
        if (userMove == null || userMove.isEmpty()) {
            logger.warn("No move received in SPS game");
            req.setAttribute("result", "Invalid move. Please try again.");
            req.getRequestDispatcher("stonePaperScissor.jsp").forward(req, resp);
            return;
        }

        String computerMove = MOVES[new Random().nextInt(3)];
        String result = getResult(userMove, computerMove);

        logger.info("SPS Game - User: {}, Computer: {}, Result: {}", userMove, computerMove, result);

        req.setAttribute("userMove", userMove);
        req.setAttribute("computerMove", computerMove);
        req.setAttribute("result", result);

        req.getRequestDispatcher("stonePaperScissor.jsp").forward(req, resp);
    }

    private String getResult(String user, String computer) {
        if (user.equals(computer)) return "It's a Draw!";

        return switch (user) {
            case "stone" -> computer.equals("scissors") ? "You Win!" : "You Lose!";
            case "paper" -> computer.equals("stone") ? "You Win!" : "You Lose!";
            case "scissors" -> computer.equals("paper") ? "You Win!" : "You Lose!";
            default -> "Invalid move.";
        };
    }
}
