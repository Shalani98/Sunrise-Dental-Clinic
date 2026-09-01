package controller;

import dao.AppointmentDAO;
import model.Appointment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/SearchAppointmentServlet")
public class SearchAppointmentServlet extends HttpServlet {

    private AppointmentDAO appointmentDAO =
            new AppointmentDAO();


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType(
                "application/json"
        );

        response.setCharacterEncoding(
                "UTF-8"
        );

        PrintWriter out =
                response.getWriter();

        String appointmentNo =
                request.getParameter(
                        "appointmentNo"
                );


        try {

           
            if (appointmentNo != null &&
                    !appointmentNo.trim().isEmpty()) {

                appointmentNo =
                        appointmentNo.trim();


                Appointment appointment =
                        appointmentDAO
                                .searchAppointment(
                                        appointmentNo
                                );


                if (appointment == null) {

                    response.setStatus(
                            HttpServletResponse
                                    .SC_NOT_FOUND
                    );


                    out.print(

                            "{\"success\":false," +
                            "\"message\":\"Appointment not found.\"}"

                    );


                    return;
                }


                out.print(
                        appointmentToJson(
                                appointment
                        )
                );


                return;
            }



          

            ArrayList<Appointment> appointments =
                    appointmentDAO
                            .getAllAppointmentDetails();


            StringBuilder json =
                    new StringBuilder();


            json.append("[");


            for (
                    int i = 0;
                    i < appointments.size();
                    i++
            ) {

                Appointment appointment =
                        appointments.get(i);


                json.append(
                        appointmentToJson(
                                appointment
                        )
                );


                if (
                        i <
                        appointments.size() - 1
                ) {

                    json.append(",");
                }
            }


            json.append("]");


            out.print(
                    json.toString()
            );


        } catch (Exception e) {

            e.printStackTrace();


            response.setStatus(
                    HttpServletResponse
                            .SC_INTERNAL_SERVER_ERROR
            );


            out.print(

                    "{\"success\":false," +
                    "\"message\":\"Unable to load appointment information.\"}"

            );

        } finally {

            out.flush();

        }

    }



    // =============================================
    // Convert Appointment to JSON
    // =============================================

    private String appointmentToJson(
            Appointment appointment) {


        StringBuilder json =
                new StringBuilder();


        json.append("{");


        json.append(
                "\"appointmentNo\":\""
        );

        json.append(
                escapeJson(
                        appointment
                                .getAppointmentNo()
                )
        );

        json.append("\",");



        json.append(
                "\"patientName\":\""
        );

        json.append(
                escapeJson(
                        appointment
                                .getPatientName()
                )
        );

        json.append("\",");



        json.append(
                "\"address\":\""
        );

        json.append(
                escapeJson(
                        appointment
                                .getAddress()
                )
        );

        json.append("\",");



        json.append(
                "\"contactNumber\":\""
        );

        json.append(
                escapeJson(
                        appointment
                                .getContactNumber()
                )
        );

        json.append("\",");



        json.append(
                "\"dentistName\":\""
        );

        json.append(
                escapeJson(
                        appointment
                                .getDentistName()
                )
        );

        json.append("\",");



        json.append(
                "\"treatmentName\":\""
        );

        json.append(
                escapeJson(
                        appointment
                                .getTreatmentName()
                )
        );

        json.append("\",");



        json.append(
                "\"appointmentDate\":\""
        );

        json.append(
                appointment
                        .getAppointmentDate()
        );

        json.append("\",");



        json.append(
                "\"appointmentTime\":\""
        );

        json.append(
                appointment
                        .getAppointmentTime()
        );

        json.append("\",");



        json.append(
                "\"status\":\""
        );

        json.append(
                escapeJson(
                        appointment
                                .getStatus()
                )
        );

        json.append("\"");


        json.append("}");


        return json.toString();
    }



    // =============================================
    // Escape JSON
    // =============================================

    private String escapeJson(
            String value) {


        if (value == null) {
            return "";
        }


        return value

                .replace(
                        "\\",
                        "\\\\"
                )

                .replace(
                        "\"",
                        "\\\""
                )

                .replace(
                        "\n",
                        "\\n"
                )

                .replace(
                        "\r",
                        "\\r"
                );

    }

}