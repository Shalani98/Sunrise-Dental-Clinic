package controller;

import dao.BillDAO;
import model.Bill;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

@WebServlet("/PrintBillServlet")
public class PrintBillServlet extends HttpServlet {


    private BillDAO billDAO = new BillDAO();



    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {


        String appointmentNo =
                request.getParameter("appointmentNo");



        Bill bill =
                billDAO.getBillByAppointmentNo(appointmentNo);



        if(bill == null){

            response.getWriter()
                    .println("Bill not found");

            return;
        }



        response.setContentType("application/pdf");

        response.setHeader(
                "Content-Disposition",
                "inline; filename=DentalBill.pdf"
        );



        try {


            Document document =
                    new Document();


            PdfWriter.getInstance(
                    document,
                    response.getOutputStream()
            );



            document.open();



            Font titleFont =
                    FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD,
                            18
                    );


            document.add(
                    new Paragraph(
                            "Sunrise Dental Clinic",
                            titleFont
                    )
            );



            document.add(
                    new Paragraph(
                            "Patient Bill Receipt"
                    )
            );



            document.add(
                    new Paragraph(
                            "--------------------------------"
                    )
            );



            document.add(
                    new Paragraph(
                            "Appointment No : "
                                    + bill.getAppointmentNo()
                    )
            );



            document.add(
                    new Paragraph(
                            "Consultation Fee : Rs "
                                    + bill.getConsultationFee()
                    )
            );



            document.add(
                    new Paragraph(
                            "Treatment Cost : Rs "
                                    + bill.getTreatmentCost()
                    )
            );



            document.add(
                    new Paragraph(
                            "Total Amount : Rs "
                                    + bill.getTotalAmount()
                    )
            );



            document.add(
                    new Paragraph(
                            "Bill Date : "
                                    + bill.getBillDate()
                    )
            );



            document.add(
                    new Paragraph(
                            "Thank you for visiting Sunrise Dental Clinic"
                    )
            );



            document.close();



        } catch(Exception e){

            e.printStackTrace();

        }


    }

}