package dao;

import model.Patient;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PatientDAO {


    // Add Patient and return generated patientId
    public int addPatient(Patient patient) {

        int patientId = 0;

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "INSERT INTO patients(patient_name,address,contact_number) VALUES(?,?,?)";


            PreparedStatement ps =
                    con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);


            ps.setString(1, patient.getPatientName());
            ps.setString(2, patient.getAddress());
            ps.setString(3, patient.getContactNumber());


            ps.executeUpdate();


            ResultSet rs = ps.getGeneratedKeys();


            if (rs.next()) {

                patientId = rs.getInt(1);

            }


        } catch(Exception e) {

            e.printStackTrace();

        }


        return patientId;

    }



    // Get All Patients
    public ArrayList<Patient> getAllPatients(){

        ArrayList<Patient> patients = new ArrayList<>();

        try{

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM patients";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();


            while(rs.next()){

                Patient patient = new Patient();

                patient.setPatientId(rs.getInt("patient_id"));
                patient.setPatientName(rs.getString("patient_name"));
                patient.setAddress(rs.getString("address"));
                patient.setContactNumber(rs.getString("contact_number"));


                patients.add(patient);
            }


        }catch(Exception e){
            e.printStackTrace();
        }


        return patients;
    }




    // Update Patient
    public boolean updatePatient(Patient patient){

        boolean result = false;

        try{

            Connection con = DBConnection.getConnection();


            String sql = "UPDATE patients SET patient_name=?, address=?, contact_number=? WHERE patient_id=?";


            PreparedStatement ps = con.prepareStatement(sql);


            ps.setString(1, patient.getPatientName());
            ps.setString(2, patient.getAddress());
            ps.setString(3, patient.getContactNumber());
            ps.setInt(4, patient.getPatientId());


            int rows = ps.executeUpdate();


            if(rows > 0){
                result = true;
            }


        }catch(Exception e){
            e.printStackTrace();
        }


        return result;
    }





    // Delete Patient
    public boolean deletePatient(int patientId){

        boolean result = false;


        try{

            Connection con = DBConnection.getConnection();


            String sql = "DELETE FROM patients WHERE patient_id=?";


            PreparedStatement ps = con.prepareStatement(sql);


            ps.setInt(1, patientId);


            int rows = ps.executeUpdate();


            if(rows > 0){
                result = true;
            }


        }catch(Exception e){
            e.printStackTrace();
        }


        return result;
    }

}