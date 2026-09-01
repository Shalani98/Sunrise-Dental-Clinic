package dao;

import model.Appointment;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AppointmentDAO {


    // Add Appointment
    public boolean addAppointment(Appointment appointment) {

        boolean result = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "INSERT INTO appointments(" +
                    "appointment_no, patient_id, dentist_id, treatment_id, " +
                    "appointment_date, appointment_time, status" +
                    ") VALUES(?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, appointment.getAppointmentNo());
            ps.setInt(2, appointment.getPatientId());
            ps.setInt(3, appointment.getDentistId());
            ps.setInt(4, appointment.getTreatmentId());
            ps.setDate(5, appointment.getAppointmentDate());
            ps.setTime(6, appointment.getAppointmentTime());
            ps.setString(7, appointment.getStatus());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    // Update appointment status after bill creation
    public boolean updatePaymentStatus(String appointmentNo) {

        boolean result = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE appointments SET status=? WHERE appointment_no=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, "Paid");
            ps.setString(2, appointmentNo);

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    // Get All Appointments - basic version
    public ArrayList<Appointment> getAllAppointments() {

        ArrayList<Appointment> appointments =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM appointments";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Appointment appointment =
                        new Appointment();

                appointment.setAppointmentNo(
                        rs.getString("appointment_no")
                );

                appointment.setPatientId(
                        rs.getInt("patient_id")
                );

                appointment.setDentistId(
                        rs.getInt("dentist_id")
                );

                appointment.setTreatmentId(
                        rs.getInt("treatment_id")
                );

                appointment.setAppointmentDate(
                        rs.getDate("appointment_date")
                );

                appointment.setAppointmentTime(
                        rs.getTime("appointment_time")
                );

                appointment.setStatus(
                        rs.getString("status")
                );

                appointments.add(appointment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return appointments;
    }


    // Get All Appointments with complete patient information
    public ArrayList<Appointment> getAllAppointmentDetails() {

        ArrayList<Appointment> appointments =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT a.appointment_no, " +
                    "a.patient_id, " +
                    "a.dentist_id, " +
                    "a.treatment_id, " +
                    "a.appointment_date, " +
                    "a.appointment_time, " +
                    "a.status, " +
                    "p.patient_name, " +
                    "p.address, " +
                    "p.contact_number, " +
                    "d.dentist_name, " +
                    "t.treatment_name " +

                    "FROM appointments a " +

                    "INNER JOIN patients p " +
                    "ON a.patient_id = p.patient_id " +

                    "INNER JOIN dentists d " +
                    "ON a.dentist_id = d.dentist_id " +

                    "INNER JOIN treatments t " +
                    "ON a.treatment_id = t.treatment_id " +

                    "ORDER BY a.appointment_date DESC, " +
                    "a.appointment_time DESC";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Appointment appointment =
                        new Appointment();

                appointment.setAppointmentNo(
                        rs.getString("appointment_no")
                );

                appointment.setPatientId(
                        rs.getInt("patient_id")
                );

                appointment.setDentistId(
                        rs.getInt("dentist_id")
                );

                appointment.setTreatmentId(
                        rs.getInt("treatment_id")
                );

                appointment.setAppointmentDate(
                        rs.getDate("appointment_date")
                );

                appointment.setAppointmentTime(
                        rs.getTime("appointment_time")
                );

                appointment.setStatus(
                        rs.getString("status")
                );

                appointment.setPatientName(
                        rs.getString("patient_name")
                );

                appointment.setAddress(
                        rs.getString("address")
                );

                appointment.setContactNumber(
                        rs.getString("contact_number")
                );

                appointment.setDentistName(
                        rs.getString("dentist_name")
                );

                appointment.setTreatmentName(
                        rs.getString("treatment_name")
                );

                appointments.add(appointment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return appointments;
    }


    // Update Appointment
    public boolean updateAppointment(
            Appointment appointment) {

        boolean result = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE appointments SET " +
                    "patient_id=?, " +
                    "dentist_id=?, " +
                    "treatment_id=?, " +
                    "appointment_date=?, " +
                    "appointment_time=?, " +
                    "status=? " +
                    "WHERE appointment_no=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(
                    1,
                    appointment.getPatientId()
            );

            ps.setInt(
                    2,
                    appointment.getDentistId()
            );

            ps.setInt(
                    3,
                    appointment.getTreatmentId()
            );

            ps.setDate(
                    4,
                    appointment.getAppointmentDate()
            );

            ps.setTime(
                    5,
                    appointment.getAppointmentTime()
            );

            ps.setString(
                    6,
                    appointment.getStatus()
            );

            ps.setString(
                    7,
                    appointment.getAppointmentNo()
            );

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    // Delete Appointment
    public boolean deleteAppointment(
            String appointmentNo) {

        boolean result = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "DELETE FROM appointments WHERE appointment_no=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    appointmentNo
            );

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    // Search Appointment by Appointment Number
    public Appointment searchAppointment(
            String appointmentNo) {

        Appointment appointment = null;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT a.appointment_no, " +
                    "a.patient_id, " +
                    "a.dentist_id, " +
                    "a.treatment_id, " +
                    "a.appointment_date, " +
                    "a.appointment_time, " +
                    "a.status, " +
                    "p.patient_name, " +
                    "p.address, " +
                    "p.contact_number, " +
                    "d.dentist_name, " +
                    "t.treatment_name " +

                    "FROM appointments a " +

                    "INNER JOIN patients p " +
                    "ON a.patient_id = p.patient_id " +

                    "INNER JOIN dentists d " +
                    "ON a.dentist_id = d.dentist_id " +

                    "INNER JOIN treatments t " +
                    "ON a.treatment_id = t.treatment_id " +

                    "WHERE a.appointment_no = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    appointmentNo
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                appointment =
                        new Appointment();

                appointment.setAppointmentNo(
                        rs.getString("appointment_no")
                );

                appointment.setPatientId(
                        rs.getInt("patient_id")
                );

                appointment.setDentistId(
                        rs.getInt("dentist_id")
                );

                appointment.setTreatmentId(
                        rs.getInt("treatment_id")
                );

                appointment.setAppointmentDate(
                        rs.getDate("appointment_date")
                );

                appointment.setAppointmentTime(
                        rs.getTime("appointment_time")
                );

                appointment.setStatus(
                        rs.getString("status")
                );

                appointment.setPatientName(
                        rs.getString("patient_name")
                );

                appointment.setAddress(
                        rs.getString("address")
                );

                appointment.setContactNumber(
                        rs.getString("contact_number")
                );

                appointment.setDentistName(
                        rs.getString("dentist_name")
                );

                appointment.setTreatmentName(
                        rs.getString("treatment_name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return appointment;
    }
}