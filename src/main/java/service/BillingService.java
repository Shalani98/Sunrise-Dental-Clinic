package service;

import dao.BillDAO;
import model.Bill;

import java.util.ArrayList;

public class BillingService {

    private BillDAO billDAO = new BillDAO();


    public boolean addBill(Bill bill) {

        return billDAO.addBill(bill);

    }


    public ArrayList<Bill> getAllBills() {

        return billDAO.getAllBills();

    }


    public boolean updateBill(Bill bill) {

        return billDAO.updateBill(bill);

    }


    public boolean deleteBill(int billId) {

        return billDAO.deleteBill(billId);

    }

}