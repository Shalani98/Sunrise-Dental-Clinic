package controller;


import model.Dentist;
import service.DentistService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/DentistServlet")
public class DentistServlet extends HttpServlet {


    private DentistService dentistService =
            new DentistService();



    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {


        response.setContentType("application/json");


        ArrayList<Dentist> dentists =
                dentistService.getAllDentists();



        String json = "[";


        for(int i=0; i<dentists.size(); i++){


            Dentist d = dentists.get(i);


            json += "{"
                    + "\"dentistId\":\""
                    + d.getDentistId()
                    + "\","
                    + "\"dentistName\":\""
                    + d.getDentistName()
                    + "\""
                    + "}";


            if(i < dentists.size()-1){

                json += ",";

            }

        }


        json += "]";


        response.getWriter().print(json);

    }

}