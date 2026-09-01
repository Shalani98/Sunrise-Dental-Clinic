package dao;

import model.Bill;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BillDAO {

    // Add Bill
    public boolean addBill(Bill bill) {

        boolean result = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO bills(appointment_no, consultation_fee, treatment_cost, total_amount, bill_date) VALUES(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bill.getAppointmentNo());
            ps.setDouble(2, bill.getConsultationFee());
            ps.setDouble(3, bill.getTreatmentCost());
            ps.setDouble(4, bill.getTotalAmount());
            ps.setDate(5, bill.getBillDate());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public Bill getBillByAppointmentNo(String appointmentNo){


        Bill bill = null;


        try{


            Connection con =
                    DBConnection.getConnection();


            String sql =
                    "SELECT * FROM bills WHERE appointment_no=?";


            PreparedStatement ps =
                    con.prepareStatement(sql);


            ps.setString(1, appointmentNo);


            ResultSet rs =
                    ps.executeQuery();



            if(rs.next()){


                bill = new Bill();


                bill.setBillId(
                        rs.getInt("bill_id")
                );


                bill.setAppointmentNo(
                        rs.getString("appointment_no")
                );


                bill.setConsultationFee(
                        rs.getDouble("consultation_fee")
                );


                bill.setTreatmentCost(
                        rs.getDouble("treatment_cost")
                );


                bill.setTotalAmount(
                        rs.getDouble("total_amount")
                );


                bill.setBillDate(
                        rs.getDate("bill_date")
                );


            }


        }catch(Exception e){

            e.printStackTrace();

        }



        return bill;

    }
    // Check duplicate bill by appointment number
    public boolean existsByAppointmentNo(String appointmentNo) {

        boolean exists = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "SELECT * FROM bills WHERE appointment_no=?";


            PreparedStatement ps =
                    con.prepareStatement(sql);


            ps.setString(1, appointmentNo);


            ResultSet rs =
                    ps.executeQuery();


            if(rs.next()) {

                exists = true;

            }


        } catch(Exception e) {

            e.printStackTrace();

        }


        return exists;

    }
    // Get All Bills
    public ArrayList<Bill> getAllBills() {

        ArrayList<Bill> bills = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM bills";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Bill bill = new Bill();

                bill.setBillId(rs.getInt("bill_id"));
                bill.setAppointmentNo(rs.getString("appointment_no"));
                bill.setConsultationFee(rs.getDouble("consultation_fee"));
                bill.setTreatmentCost(rs.getDouble("treatment_cost"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
                bill.setBillDate(rs.getDate("bill_date"));

                bills.add(bill);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bills;
    }

    // Update Bill
    public boolean updateBill(Bill bill) {

        boolean result = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE bills SET appointment_no=?, consultation_fee=?, treatment_cost=?, total_amount=?, bill_date=? WHERE bill_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bill.getAppointmentNo());
            ps.setDouble(2, bill.getConsultationFee());
            ps.setDouble(3, bill.getTreatmentCost());
            ps.setDouble(4, bill.getTotalAmount());
            ps.setDate(5, bill.getBillDate());
            ps.setInt(6, bill.getBillId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // Delete Bill
    public boolean deleteBill(int billId) {

        boolean result = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM bills WHERE bill_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, billId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}