package service;

import dao.AppointmentDAO;
import model.Appointment;

import java.util.ArrayList;

public class AppointmentService {

    private AppointmentDAO appointmentDAO ;


    // Used by the real application
    public AppointmentService() {
        this.appointmentDAO = new AppointmentDAO();
    }

    // Used by automated unit tests
    public AppointmentService(AppointmentDAO appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }



    public boolean addAppointment(Appointment appointment) {

        return appointmentDAO.addAppointment(appointment);

    }


    public ArrayList<Appointment> getAllAppointments() {

        return appointmentDAO.getAllAppointments();

    }


    public Appointment searchAppointment(String appointmentNo) {

        return appointmentDAO.searchAppointment(appointmentNo);

    }


    public boolean updateAppointment(Appointment appointment) {

        return appointmentDAO.updateAppointment(appointment);

    }


    public boolean deleteAppointment(String appointmentNo) {

        return appointmentDAO.deleteAppointment(appointmentNo);

    }

}