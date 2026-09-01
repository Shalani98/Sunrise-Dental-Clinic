package dao;

import model.Dentist;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DentistDAO {


    // Add Dentist
    public boolean addDentist(Dentist dentist) {

        boolean result = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO dentists(dentist_name, specialization) VALUES(?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, dentist.getDentistName());
            ps.setString(2, dentist.getSpecialization());


            int rows = ps.executeUpdate();

            if(rows > 0){
                result = true;
            }


        } catch(Exception e){
            e.printStackTrace();
        }


        return result;
    }



    // Get All Dentists
    public ArrayList<Dentist> getAllDentists(){

        ArrayList<Dentist> dentists = new ArrayList<>();

        try{

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM dentists";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();


            while(rs.next()){

                Dentist dentist = new Dentist();


                dentist.setDentistId(rs.getInt("dentist_id"));
                dentist.setDentistName(rs.getString("dentist_name"));
                dentist.setSpecialization(rs.getString("specialization"));


                dentists.add(dentist);
            }


        }catch(Exception e){
            e.printStackTrace();
        }


        return dentists;
    }




    // Update Dentist
    public boolean updateDentist(Dentist dentist){

        boolean result = false;


        try{

            Connection con = DBConnection.getConnection();


            String sql = "UPDATE dentists SET dentist_name=?, specialization=? WHERE dentist_id=?";


            PreparedStatement ps = con.prepareStatement(sql);


            ps.setString(1, dentist.getDentistName());
            ps.setString(2, dentist.getSpecialization());
            ps.setInt(3, dentist.getDentistId());


            int rows = ps.executeUpdate();


            if(rows > 0){
                result = true;
            }


        }catch(Exception e){
            e.printStackTrace();
        }


        return result;
    }





    // Delete Dentist
    public boolean deleteDentist(int dentistId){

        boolean result = false;


        try{

            Connection con = DBConnection.getConnection();


            String sql = "DELETE FROM dentists WHERE dentist_id=?";


            PreparedStatement ps = con.prepareStatement(sql);


            ps.setInt(1, dentistId);


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