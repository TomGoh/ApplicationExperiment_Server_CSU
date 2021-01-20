package Servlet;

import Service.PatientService;
import Service.impl.PatientServiceImpl;
import com.google.gson.Gson;
import pojo.Patient;
import utils.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class loginServlet extends HttpServlet {

    private final PatientService patientService=new PatientServiceImpl();
    private final static String PATIENT_ACCOUNT="PatientAccount";
    private final static String PATIENT_PASSWORD="PatientPassword";
    private final static String EMPTY_FEEDBACK="EmptyInput";
    private final static String WRONG_LOGIN="WrongPasswordORWrongAccount";
    private final static String SUCCESS_LOGIN="LoginSuccess";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        String patientAccount=req.getParameter(PATIENT_ACCOUNT);
        String patientPassword=req.getParameter(PATIENT_PASSWORD);
        System.out.println("Get request:"+patientAccount+" "+patientPassword);

        if(patientAccount==null || patientPassword==null){
            Gson gson=new Gson();
            Message message=new Message(EMPTY_FEEDBACK);
            String json=gson.toJson(message);
            resp.getWriter().write(json);
            return;
        }

        Patient loginResult=patientService.login(new Patient(patientAccount,patientPassword));
        Gson gson=new Gson();
        String json=gson.toJson(loginResult);
        resp.getWriter().write(json);
    }
}
