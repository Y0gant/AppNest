package com.y0gant.appnest.controller;

import com.y0gant.appnest.dao.UserDao;
import com.y0gant.appnest.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {
    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z][a-zA-Z0-9_]{2,19}$");
    }

    public static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("username");
        String email = req.getParameter("email");
        String passwd = req.getParameter("passwd");

        // Validation
        if (!isValidUsername(uname) || !isValidEmail(email) || !isValidPassword(passwd)) {
            req.setAttribute("errorMessage", "Invalid input! Please check your entries.");
            RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");
            rd.forward(req, resp);
            return;
        }

        // Attempt to register the user
        UserDao dao = new UserDao();
        boolean userCreated = dao.registerUser(new User(uname, email, passwd));

        if (userCreated) {
            HttpSession session = req.getSession();
            session.setAttribute("userName", uname);
            session.setAttribute("Email", email);
            resp.sendRedirect("dashboard.jsp");
        } else {
            req.setAttribute("errorMessage", "User already exists or error creating user.");
            RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");
            rd.forward(req, resp);
        }
    }
}
