package Servlet;

import Service.impl.PatientServiceImpl;
import com.google.gson.Gson;
import utils.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class changePasswordServlet extends HttpServlet {

    private final static String OLD_PASSWORD="oldPassword";
    private final static String NEW_PASSWORD="newPassword";
    private final static String ACCOUNT="patientAccount";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPassword=req.getParameter(OLD_PASSWORD);
        String newPassword=req.getParameter(NEW_PASSWORD);
        String patientAccount=req.getParameter(ACCOUNT);
        int result=0;
        result=new PatientServiceImpl().changePassword(patientAccount,oldPassword,newPassword);
        if(result==1){
            Message message=new Message("SUCCESS");
            Gson gson=new Gson();
            String json=gson.toJson(message);
            resp.getWriter().write(json);
        }else{
            Message message=new Message("FAILED");
            Gson gson=new Gson();
            String json=gson.toJson(message);
            resp.getWriter().write(json);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
