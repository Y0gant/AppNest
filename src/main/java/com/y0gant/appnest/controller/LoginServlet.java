package com.y0gant.appnest.controller;

import com.y0gant.appnest.dao.UserDao;
import com.y0gant.appnest.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.debug("Received POST request for /login.");

        response.setContentType("text/html");

        String identifier = request.getParameter("userInput").trim();
        String passwd = request.getParameter("passwd").trim();

        logger.debug("Attempting login for identifier: {}", identifier);

        UserDao dao = new UserDao();
        User user = dao.findUserByEmailAndPassword(identifier, passwd);
        if (user == null) {
            user = dao.findUserByUserNameAndPassword(identifier, passwd);
        }

        if (user != null) {
            logger.info("Login successful for user: {}", user.getName());

            HttpSession session = request.getSession();
            session.setAttribute("userName", user.getName());
            session.setAttribute("Email", user.getEmail());

            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            logger.warn("Login failed for identifier: {}", identifier);

            request.setAttribute("errorMessage", "Invalid username/e-mail or password.");
            try {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } catch (ServletException e) {
                logger.error("ServletException occurred while forwarding to login.jsp", e);
                throw new RuntimeException(e);
            }
        }
    }
}