package controller;

import model.Appointment;
import model.User;
import service.AppointmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import model.Patient;
import service.PatientService;

@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {


    private AppointmentService appointmentService = new AppointmentService();

    private PatientService patientService = new PatientService();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String action = request.getParameter("action");



        // ADD APPOINTMENT
        if ("add".equals(action)) {


            Appointment appointment = new Appointment();



            // Auto generate appointment number
            String appointmentNo =
                    "A" + System.currentTimeMillis();


            appointment.setAppointmentNo(appointmentNo);



            HttpSession session = request.getSession(false);

            User user = null;

            if(session != null){
                user = (User) session.getAttribute("user");
            }

            if(user == null){

                response.getWriter().println(
                        "<script>alert('Please login first'); window.location='login.html';</script>"
                );

                return;
            }


// Create patient record

            Patient patient = new Patient();


            patient.setPatientName(
                    request.getParameter("patientName")
            );


            patient.setAddress(
                    request.getParameter("address")
            );


            patient.setContactNumber(
                    request.getParameter("contactNumber")
            );



            int patientId = patientService.addPatient(patient);



            if(patientId == 0){

                response.getWriter().println(
                        "<script>alert('Patient registration failed'); window.location='appointments.html';</script>"
                );

                return;

            }



// Link appointment with patient

            appointment.setPatientId(patientId);




            // Dentist selected from dropdown
            appointment.setDentistId(
                    Integer.parseInt(request.getParameter("dentistId"))
            );



            // Treatment selected from dropdown
            appointment.setTreatmentId(
                    Integer.parseInt(request.getParameter("treatmentId"))
            );



            appointment.setAppointmentDate(
                    Date.valueOf(request.getParameter("appointmentDate"))
            );



            String timeValue =
                    request.getParameter("appointmentTime");



            if(timeValue != null && !timeValue.isEmpty()) {

                appointment.setAppointmentTime(
                        Time.valueOf(timeValue + ":00")
                );

            }



            // Default status
            appointment.setStatus("Pending");



            boolean result =
                    appointmentService.addAppointment(appointment);



            if(result) {


                response.getWriter().println(
                        "<script>alert('Appointment added successfully'); window.location='appointments.html';</script>"
                );


            } else {


                response.getWriter().println(
                        "<script>alert('Failed to add appointment'); window.location='appointments.html';</script>"
                );

            }



        }



        // UPDATE APPOINTMENT
        else if ("update".equals(action)) {


            Appointment appointment = new Appointment();



            appointment.setAppointmentNo(
                    request.getParameter("appointmentNo")
            );



            appointment.setPatientId(
                    Integer.parseInt(request.getParameter("patientId"))
            );



            appointment.setDentistId(
                    Integer.parseInt(request.getParameter("dentistId"))
            );



            appointment.setTreatmentId(
                    Integer.parseInt(request.getParameter("treatmentId"))
            );



            appointment.setAppointmentDate(
                    Date.valueOf(request.getParameter("appointmentDate"))
            );



            String timeValue =
                    request.getParameter("appointmentTime");



            if(timeValue != null && !timeValue.isEmpty()) {

                appointment.setAppointmentTime(
                        Time.valueOf(timeValue + ":00")
                );

            }



            appointment.setStatus(
                    request.getParameter("status")
            );



            boolean result =
                    appointmentService.updateAppointment(appointment);



            if(result) {

                response.getWriter().println(
                        "<script>alert('Appointment updated successfully'); window.location='appointments.html';</script>"
                );


            } else {

                response.getWriter().println(
                        "<script>alert('Failed to update appointment'); window.location='appointments.html';</script>"
                );

            }


        }



        // DELETE APPOINTMENT
        else if ("delete".equals(action)) {



            String appointmentNo =
                    request.getParameter("appointmentNo");



            boolean result =
                    appointmentService.deleteAppointment(appointmentNo);



            if(result) {


                response.getWriter().println(
                        "<script>alert('Appointment deleted successfully'); window.location='appointments.html';</script>"
                );


            } else {


                response.getWriter().println(
                        "<script>alert('Failed to delete appointment'); window.location='appointments.html';</script>"
                );

            }

        }

    }




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        request.setAttribute(
                "appointmentList",
                appointmentService.getAllAppointments()
        );


        request.getRequestDispatcher("appointments.html")
                .forward(request, response);

    }

}