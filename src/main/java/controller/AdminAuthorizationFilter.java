package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import java.io.IOException;

@WebFilter(urlPatterns = {
        "/adminDashboard.html",
        "/users.html",
        "/addUser.html"
})
public class AdminAuthorizationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {

        // No initialization required
    }


    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {


        HttpServletRequest httpRequest =
                (HttpServletRequest) request;


        HttpServletResponse httpResponse =
                (HttpServletResponse) response;


        HttpSession session =
                httpRequest.getSession(false);


        // Check login first
        if (session == null ||
                session.getAttribute("user") == null) {


            httpResponse.sendRedirect(
                    httpRequest.getContextPath()
                            + "/login.html?error=session"
            );


            return;
        }


        String role =
                (String) session.getAttribute("role");


        // Allow admin only
        if ("admin".equalsIgnoreCase(role)) {


            chain.doFilter(
                    request,
                    response
            );


        } else {


            httpResponse.sendRedirect(
                    httpRequest.getContextPath()
                            + "/staffDashboard.html?error=unauthorized"
            );

        }

    }


    @Override
    public void destroy() {

        // No cleanup required
    }

}