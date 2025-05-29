package com.y0gant.appnest.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Received GET request to /dashboard.");

        HttpSession session = request.getSession(false);

        if (session == null) {
            logger.warn("Session is null. Redirecting to login.jsp.");
            response.sendRedirect("login.jsp");
            return;
        }

        String userName = (String) session.getAttribute("userName");
        String email = (String) session.getAttribute("Email");

        if (userName == null || userName.isEmpty()) {
            logger.warn("User not logged in or userName is missing in session. Redirecting to login.jsp.");
            response.sendRedirect("login.jsp");
            return;
        }

        logger.info("User '{}' with email '{}' accessed the dashboard.", userName, email);

        request.setAttribute("userName", userName);
        request.setAttribute("email", email);

        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        logger.debug("Forwarding request to dashboard.jsp.");
        dispatcher.forward(request, response);
    }
}
