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
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userName") == null || ((String) session.getAttribute("userName")).isEmpty()) {
            logger.warn("Unauthorized access attempt to /dashboard. Redirecting to login.");
            response.sendRedirect("login.jsp");
            return;
        }

        String userName = (String) session.getAttribute("userName");
        String email = (String) session.getAttribute("Email");

        logger.info("User '{}' accessed the dashboard.", userName);

        request.setAttribute("userName", userName);
        request.setAttribute("email", email);

        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
