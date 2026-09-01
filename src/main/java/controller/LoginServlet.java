package controller;

import model.User;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");


        // Basic server-side validation
        if (username == null ||
                username.trim().isEmpty() ||
                password == null ||
                password.trim().isEmpty()) {

            response.sendRedirect("login.html?error=empty");
            return;
        }


        username = username.trim();


        LoginService loginService =
                new LoginService();


        User user =
                loginService.login(
                        username,
                        password
                );


        if (user != null) {

            // Create new session
            HttpSession session =
                    request.getSession(true);


            session.setAttribute(
                    "user",
                    user
            );


            session.setAttribute(
                    "role",
                    user.getRole()
            );


            // Optional session timeout - 30 minutes
            session.setMaxInactiveInterval(
                    30 * 60
            );


            if ("admin".equalsIgnoreCase(
                    user.getRole())) {

                response.sendRedirect(
                        "adminDashboard.html"
                );


            } else if ("staff".equalsIgnoreCase(
                    user.getRole())) {

                response.sendRedirect(
                        "staffDashboard.html"
                );


            } else {

                // Unknown role
                session.invalidate();

                response.sendRedirect(
                        "login.html?error=role"
                );

            }


        } else {

            response.sendRedirect(
                    "login.html?error=invalid"
            );

        }

    }
}