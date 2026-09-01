package service;

import dao.TreatmentDAO;
import model.Treatment;

import java.util.ArrayList;

public class TreatmentService {


    private TreatmentDAO treatmentDAO = new TreatmentDAO();


    public ArrayList<Treatment> getAllTreatments(){

        return treatmentDAO.getAllTreatments();

    }

}