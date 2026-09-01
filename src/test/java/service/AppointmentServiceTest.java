package service;

import dao.AppointmentDAO;
import model.Appointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {

    private AppointmentDAO appointmentDAO;
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        appointmentDAO = mock(AppointmentDAO.class);
        appointmentService = new AppointmentService(appointmentDAO);
    }

    @Test
    void shouldAddAppointmentSuccessfully() {
        Appointment appointment = new Appointment();

        when(appointmentDAO.addAppointment(appointment))
                .thenReturn(true);

        boolean result =
                appointmentService.addAppointment(appointment);

        assertTrue(result);
        verify(appointmentDAO).addAppointment(appointment);
    }

    @Test
    void shouldReturnFalseWhenAddingAppointmentFails() {
        Appointment appointment = new Appointment();

        when(appointmentDAO.addAppointment(appointment))
                .thenReturn(false);

        boolean result =
                appointmentService.addAppointment(appointment);

        assertFalse(result);
        verify(appointmentDAO).addAppointment(appointment);
    }

    @Test
    void shouldReturnAllAppointments() {
        ArrayList<Appointment> expectedAppointments =
                new ArrayList<>();

        expectedAppointments.add(new Appointment());
        expectedAppointments.add(new Appointment());

        when(appointmentDAO.getAllAppointments())
                .thenReturn(expectedAppointments);

        ArrayList<Appointment> actualAppointments =
                appointmentService.getAllAppointments();

        assertEquals(2, actualAppointments.size());
        assertSame(expectedAppointments, actualAppointments);
        verify(appointmentDAO).getAllAppointments();
    }

    @Test
    void shouldFindAppointmentByAppointmentNumber() {
        String appointmentNo = "A1001";
        Appointment expectedAppointment = new Appointment();

        when(appointmentDAO.searchAppointment(appointmentNo))
                .thenReturn(expectedAppointment);

        Appointment actualAppointment =
                appointmentService.searchAppointment(appointmentNo);

        assertNotNull(actualAppointment);
        assertSame(expectedAppointment, actualAppointment);
        verify(appointmentDAO).searchAppointment(appointmentNo);
    }

    @Test
    void shouldUpdateAppointmentSuccessfully() {
        Appointment appointment = new Appointment();

        when(appointmentDAO.updateAppointment(appointment))
                .thenReturn(true);

        boolean result =
                appointmentService.updateAppointment(appointment);

        assertTrue(result);
        verify(appointmentDAO).updateAppointment(appointment);
    }

    @Test
    void shouldDeleteAppointmentSuccessfully() {
        String appointmentNo = "A1001";

        when(appointmentDAO.deleteAppointment(appointmentNo))
                .thenReturn(true);

        boolean result =
                appointmentService.deleteAppointment(appointmentNo);

        assertTrue(result);
        verify(appointmentDAO).deleteAppointment(appointmentNo);
    }
}