package controller;


import dao.UserDAO;
import model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {


    private UserDAO userDAO =
            new UserDAO();



    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {


        response.setContentType("application/json");


        ArrayList<User> users =
                userDAO.getAllUsers();



        String json="[";


        for(int i=0;i<users.size();i++){


            User u=users.get(i);


            json += "{";

            json += "\"userId\":"+u.getUserId()+",";

            json += "\"username\":\""+u.getUsername()+"\",";

            json += "\"role\":\""+u.getRole()+"\"";


            json+="}";


            if(i < users.size()-1)
                json+=",";


        }


        json+="]";


        response.getWriter().print(json);


    }







    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {



        String action =
                request.getParameter("action");



        User user =
                new User();



        if(action.equals("add")){


            user.setUsername(
                    request.getParameter("username")
            );


            user.setPassword(
                    request.getParameter("password")
            );


            user.setRole(
                    request.getParameter("role")
            );


            userDAO.addUser(user);


        }



        else if(action.equals("delete")){


            int id =
                    Integer.parseInt(request.getParameter("userId"));


            userDAO.deleteUser(id);


        }



        response.sendRedirect("users.html");


    }



}