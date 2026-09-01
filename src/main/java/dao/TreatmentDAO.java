package dao;

import model.Treatment;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TreatmentDAO {


    // Add Treatment
    public boolean addTreatment(Treatment treatment){

        boolean result = false;

        try{

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO treatments(treatment_name, treatment_cost) VALUES(?,?)";

            PreparedStatement ps = con.prepareStatement(sql);


            ps.setString(1, treatment.getTreatmentName());
            ps.setDouble(2, treatment.getTreatmentCost());


            int rows = ps.executeUpdate();


            if(rows > 0){
                result = true;
            }


        }catch(Exception e){
            e.printStackTrace();
        }


        return result;
    }




    // Get All Treatments
    public ArrayList<Treatment> getAllTreatments(){

        ArrayList<Treatment> treatments = new ArrayList<>();


        try{

            Connection con = DBConnection.getConnection();


            String sql = "SELECT * FROM treatments";


            PreparedStatement ps = con.prepareStatement(sql);


            ResultSet rs = ps.executeQuery();



            while(rs.next()){


                Treatment treatment = new Treatment();


                treatment.setTreatmentId(rs.getInt("treatment_id"));

                treatment.setTreatmentName(rs.getString("treatment_name"));

                treatment.setTreatmentCost(rs.getDouble("treatment_cost"));



                treatments.add(treatment);

            }


        }catch(Exception e){

            e.printStackTrace();

        }


        return treatments;
    }






    // Update Treatment
    public boolean updateTreatment(Treatment treatment){


        boolean result = false;


        try{


            Connection con = DBConnection.getConnection();


            String sql = "UPDATE treatments SET treatment_name=?, treatment_cost=? WHERE treatment_id=?";


            PreparedStatement ps = con.prepareStatement(sql);



            ps.setString(1, treatment.getTreatmentName());

            ps.setDouble(2, treatment.getTreatmentCost());

            ps.setInt(3, treatment.getTreatmentId());



            int rows = ps.executeUpdate();



            if(rows > 0){

                result = true;

            }



        }catch(Exception e){

            e.printStackTrace();

        }



        return result;
    }






    // Delete Treatment
    public boolean deleteTreatment(int treatmentId){


        boolean result = false;


        try{


            Connection con = DBConnection.getConnection();


            String sql = "DELETE FROM treatments WHERE treatment_id=?";


            PreparedStatement ps = con.prepareStatement(sql);


            ps.setInt(1, treatmentId);



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