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

public class signUpServlet extends HttpServlet {

    private static final PatientService patientService=new PatientServiceImpl();

    private final static String PATIENT_ACCOUNT="PatientAccount";
    private final static String PATIENT_NAME="PatientName";
    private final static String PATIENT_PASSWORD="PatientPassword";
    private final static String EMPTY_FEEDBACK="EmptyInput";
    private final static String DUPLICATED ="DuplicatedAccount";
    private final static String SUCCESS_SIGNUP="SignUpSuccess";
    private final static String REGISTER_FAILED ="registerFailed";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String patientAccount=req.getParameter(PATIENT_ACCOUNT);
        String patientName=req.getParameter(PATIENT_NAME);
        String patientPassword=req.getParameter(PATIENT_PASSWORD);

        //检测是否为空
        if(patientAccount==null || patientPassword==null){
            Gson gson=new Gson();
            Message message=new Message(EMPTY_FEEDBACK);
            String json=gson.toJson(message);
            resp.getWriter().write(json);
            return;
        }

        //检测是否已被占用
        boolean usage=patientService.checkAccountUsage(patientAccount);
        if(usage){
            Gson gson=new Gson();
            Message message=new Message(DUPLICATED);
            String json=gson.toJson(message);
            resp.getWriter().write(json);
            return;
        }else{
            int result = patientService.register(new Patient(patientName,patientPassword,patientAccount));
            if(result==2){
                Gson gson=new Gson();
                Message message=new Message(SUCCESS_SIGNUP);
                String json=gson.toJson(message);
                resp.getWriter().write(json);
                return;
            }else if(result==0){
                int re=0;
                try{
                    re=patientService.deleteAccount(patientAccount);
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    if(re==1){
                        System.out.println("Revoked");
                    }else{
                        System.out.println("??? Delete Falsely on "+patientAccount);
                    }
                }

            }else if(result==-2){
                Gson gson=new Gson();
                Message message=new Message(REGISTER_FAILED);
                String json=gson.toJson(message);
                resp.getWriter().write(json);
            }
        }
    }
}
