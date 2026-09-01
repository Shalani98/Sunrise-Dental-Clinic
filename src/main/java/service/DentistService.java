package service;

import dao.DentistDAO;
import model.Dentist;

import java.util.ArrayList;

public class DentistService {


    private DentistDAO dentistDAO = new DentistDAO();


    public ArrayList<Dentist> getAllDentists(){

        return dentistDAO.getAllDentists();

    }

}