package Servlet;

import Service.PatientService;
import Service.impl.PatientServiceImpl;
import com.google.gson.Gson;
import utils.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class balanceServlet extends HttpServlet {

    private static final String REQUEST_HEAD="request";
    private static final String REQUEST_ADD="addBalance";
    private final static String REQUEST_PATIENT_ID="patientID";
    private final static String BALANCE_AMOUNT="balanceAmount";
    private final static String PAY_BILL="payBill";
    private final static String RESERVE_ID="reserveID";
    private final static String REQUEST_CHECK="checkBalance";

    final PatientService patientService=new PatientServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestType=req.getParameter(REQUEST_HEAD);
        if(requestType.equalsIgnoreCase(REQUEST_ADD)){
            String patientID=req.getParameter(REQUEST_PATIENT_ID);
            String amount=req.getParameter(BALANCE_AMOUNT);
            int result=patientService.alterBalance(patientID,amount);
            if(result==1){
                Message message=new Message("SUCCESS");
                Gson gson=new Gson();
                String json=gson.toJson(message);
                resp.getWriter().write(json);
            }
        }else if(requestType.equalsIgnoreCase(PAY_BILL)){
            String patientID=req.getParameter(REQUEST_PATIENT_ID);
            String reserveID=req.getParameter(RESERVE_ID);
            int result=patientService.alterReservationPayment(patientID,reserveID);
            if(result==2){
                Message message=new Message("SUCCESS");
                Gson gson=new Gson();
                String json=gson.toJson(message);
                resp.getWriter().write(json);
            }
        }else if(requestType.equalsIgnoreCase(REQUEST_CHECK)){
            String patientID=req.getParameter(REQUEST_PATIENT_ID);
            Message message=new Message(patientService.checkForBalance(patientID)+"");
            Gson gson=new Gson();
            String json=gson.toJson(message);
            resp.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
