package com.y0gant.appnest.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Received POST request for /logout");

        String action = req.getParameter("action");
        logger.debug("Logout action received: {}", action);

        if ("Confirm".equalsIgnoreCase(action)) {
            HttpSession session = req.getSession(false);
            if (session != null) {
                String userName = (String) session.getAttribute("userName");
                session.invalidate();
                logger.info("User '{}' logged out successfully.", userName);
            } else {
                logger.warn("No session found during logout attempt.");
            }
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else if ("Cancel".equalsIgnoreCase(action)) {
            logger.info("Logout cancelled by user.");
            resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
        } else {
            logger.warn("Unexpected logout action: '{}'", action);
            resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
        }
    }
}
