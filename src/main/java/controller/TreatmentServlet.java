package controller;


import model.Treatment;
import service.TreatmentService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/TreatmentServlet")
public class TreatmentServlet extends HttpServlet {


    private TreatmentService treatmentService =
            new TreatmentService();



    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {


        response.setContentType("application/json");


        ArrayList<Treatment> treatments =
                treatmentService.getAllTreatments();


        String json = "[";


        for(int i=0; i<treatments.size(); i++){


            Treatment t =
                    treatments.get(i);


            json += "{"
                    + "\"treatmentId\":\""
                    + t.getTreatmentId()
                    + "\","
                    + "\"treatmentName\":\""
                    + t.getTreatmentName()
                    + "\""
                    + "}";


            if(i < treatments.size()-1){

                json += ",";

            }

        }


        json += "]";


        response.getWriter().print(json);

    }

}