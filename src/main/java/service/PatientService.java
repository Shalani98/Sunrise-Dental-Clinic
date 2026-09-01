package service;


import dao.PatientDAO;
import model.Patient;


public class PatientService {


    private PatientDAO patientDAO = new PatientDAO();



    public int addPatient(Patient patient){

        return patientDAO.addPatient(patient);

    }


}