package model;

import java.sql.Date;


public class Bill {

    private int billId;
    private String appointmentNo;

    private double consultationFee;
    private double treatmentCost;
    private double totalAmount;

    private Date billDate;


    public int getBillId() {
        return billId;
    }


    public void setBillId(int billId) {
        this.billId = billId;
    }


    public String getAppointmentNo() {
        return appointmentNo;
    }


    public void setAppointmentNo(String appointmentNo) {
        this.appointmentNo = appointmentNo;
    }


    public double getConsultationFee() {
        return consultationFee;
    }


    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }


    public double getTreatmentCost() {
        return treatmentCost;
    }


    public void setTreatmentCost(double treatmentCost) {
        this.treatmentCost = treatmentCost;
    }


    public double getTotalAmount() {
        return totalAmount;
    }


    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }


    public Date getBillDate() {
        return billDate;
    }


    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
}