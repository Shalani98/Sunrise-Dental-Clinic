package controller;

import dao.AppointmentDAO;
import dao.BillDAO;
import model.Bill;
import service.BillingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/BillServlet")
public class BillServlet extends HttpServlet {

    private BillingService billingService = new BillingService();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");

            try {

                // Get appointment number
                String appointmentNo =
                        request.getParameter("appointmentNo");

                String consultationFeeText =
                        request.getParameter("consultationFee");

                String treatmentCostText =
                        request.getParameter("treatmentCost");

                String billDateText =
                        request.getParameter("billDate");


                // -------------------------------
                // Server-side validation
                // -------------------------------

                if (appointmentNo == null ||
                        appointmentNo.trim().isEmpty()) {

                    showError(
                            response,
                            "Please select a valid appointment."
                    );

                    return;
                }


                if (consultationFeeText == null ||
                        consultationFeeText.trim().isEmpty()) {

                    showError(
                            response,
                            "Consultation fee is required."
                    );

                    return;
                }


                if (treatmentCostText == null ||
                        treatmentCostText.trim().isEmpty()) {

                    showError(
                            response,
                            "Treatment cost is required."
                    );

                    return;
                }


                if (billDateText == null ||
                        billDateText.trim().isEmpty()) {

                    showError(
                            response,
                            "Bill date is required."
                    );

                    return;
                }


                double consultationFee =
                        Double.parseDouble(
                                consultationFeeText
                        );


                double treatmentCost =
                        Double.parseDouble(
                                treatmentCostText
                        );


                // Fees cannot be negative
                if (consultationFee < 0 ||
                        treatmentCost < 0) {

                    showError(
                            response,
                            "Fees cannot be negative."
                    );

                    return;
                }


                Date billDate =
                        Date.valueOf(
                                billDateText
                        );


                // Do not allow future bill date
                Date today =
                        new Date(
                                System.currentTimeMillis()
                        );


                if (billDate.after(today)) {

                    showError(
                            response,
                            "Bill date cannot be in the future."
                    );

                    return;
                }


                appointmentNo =
                        appointmentNo.trim();


                BillDAO billDAO =
                        new BillDAO();


                // Prevent duplicate bill
                if (billDAO.existsByAppointmentNo(
                        appointmentNo)) {

                    response.getWriter().println(

                            "<script>" +
                            "alert('Bill already exists for this appointment');" +
                            "window.location='frontend/searchAppointment.html';" +
                            "</script>"

                    );

                    return;
                }


                // -------------------------------
                // Calculate total on server
                // -------------------------------

                double totalAmount =
                        consultationFee +
                        treatmentCost;


                // Create Bill object
                Bill bill =
                        new Bill();


                bill.setAppointmentNo(
                        appointmentNo
                );


                bill.setConsultationFee(
                        consultationFee
                );


                bill.setTreatmentCost(
                        treatmentCost
                );


                bill.setTotalAmount(
                        totalAmount
                );


                bill.setBillDate(
                        billDate
                );


                // Save bill
                boolean result =
                        billingService.addBill(
                                bill
                        );


                if (result) {

                    AppointmentDAO appointmentDAO =
                            new AppointmentDAO();


                    appointmentDAO
                            .updatePaymentStatus(
                                    appointmentNo
                            );


                    response.getWriter().println(

                            "<script>" +
                            "alert('Bill created successfully');" +
                            "window.location='PrintBillServlet?appointmentNo=" +
                            appointmentNo +
                            "';" +
                            "</script>"

                    );

                } else {

                    showError(
                            response,
                            "Unable to create bill."
                    );

                }


            } catch (NumberFormatException e) {

                showError(
                        response,
                        "Please enter valid numeric values for fees."
                );


            } catch (IllegalArgumentException e) {

                showError(
                        response,
                        "Please enter a valid bill date."
                );


            } catch (Exception e) {

                e.printStackTrace();

                showError(
                        response,
                        "An unexpected error occurred while creating the bill."
                );

            }

        } else {

            showError(
                    response,
                    "Invalid billing action."
            );

        }

    }


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType(
                "application/json"
        );

        response.setCharacterEncoding(
                "UTF-8"
        );

        response.getWriter().println(
                "[]"
        );

    }


    // Error message helper
    private void showError(
            HttpServletResponse response,
            String message)
            throws IOException {

        String safeMessage =
                message
                        .replace("\\", "\\\\")
                        .replace("'", "\\'");


        response.getWriter().println(

                "<script>" +
                "alert('" +
                safeMessage +
                "');" +
                "window.history.back();" +
                "</script>"

        );

    }

}