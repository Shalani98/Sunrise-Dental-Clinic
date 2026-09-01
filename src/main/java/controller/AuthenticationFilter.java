package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
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


        String path =
                httpRequest.getRequestURI()
                        .substring(
                                httpRequest.getContextPath().length()
                        );


        HttpSession session =
                httpRequest.getSession(false);


        boolean loggedIn =
                session != null &&
                session.getAttribute("user") != null;


       
        boolean publicResource =

                path.equals("/") ||

                path.equals("/index.html") ||

                path.equals("/login.html") ||

                path.equals("/LoginServlet") ||

                path.startsWith("/css/") ||

                path.startsWith("/js/") ||

                path.startsWith("/images/") ||

                path.endsWith(".css") ||

                path.endsWith(".js") ||

                path.endsWith(".png") ||

                path.endsWith(".jpg") ||

                path.endsWith(".jpeg") ||

                path.endsWith(".gif") ||

                path.endsWith(".ico");


        if (loggedIn || publicResource) {

         
            chain.doFilter(
                    request,
                    response
            );

        } else {

           
            httpResponse.sendRedirect(
                    httpRequest.getContextPath()
                            + "/login.html?error=session"
            );

        }
    }


    @Override
    public void destroy() {
        // No cleanup required
    }
}