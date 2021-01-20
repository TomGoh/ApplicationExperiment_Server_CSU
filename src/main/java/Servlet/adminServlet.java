package Servlet;

import Service.PatientService;
import Service.impl.PatientServiceImpl;
import com.google.gson.Gson;
import pojo.*;
import utils.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class adminServlet extends HttpServlet {

    private static final String REQUEST_HEAD="request";
    private static final String APPEND_REQUEST="appendDoctor";
    private static final String DOCTOR_ID="doctorID";
    private static final String DOCTOR_NAME="doctorName";
    private static final String DOCTOR_DEPARTMENT="doctorDepartment";
    private static final String DOCTOR_DESC="doctorDescription";
    private static final String DOCTOR_DEGREE="doctorDegree";
    private static final String ADMIN_LOGIN="adminLogin";
    private static final String ADMIN_ACCOUNT="account";
    private static final String ADMIN_PASSWORD="pssword";
    private static final String CHECKALL="check";
    private static final String APPEND_RESERVE="appendReserve";
    private static final String APPEND_DATE="date";
    private static final String APPEND_SURPLUS="surplus";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PatientService patientService=new PatientServiceImpl();
        resp.setCharacterEncoding("UTF-8");
        String requestType=req.getParameter(REQUEST_HEAD);
        if(requestType.equalsIgnoreCase(APPEND_REQUEST)){
            String doctorName=req.getParameter(DOCTOR_NAME);
            String doctorDepartment=req.getParameter(DOCTOR_DEPARTMENT);
            Department department=patientService.queryDepartmentByName(doctorDepartment);
            String doctorDesc=req.getParameter(DOCTOR_DESC);
            String doctorDegree=req.getParameter(DOCTOR_DEGREE);
            Doctor doctor=new Doctor(doctorName,"",doctorDegree,department.getDepartmentID());
            int result=patientService.appendDoctor(doctor);
            if(result==1){
                Gson gson=new Gson();
                Message message=new Message("APPEND SUCCESS");
                String json=gson.toJson(message);
                resp.getWriter().write(json);
                return;
            }
        }else if (requestType.equalsIgnoreCase(ADMIN_LOGIN)){
            String account=req.getParameter(ADMIN_ACCOUNT);
            String password=req.getParameter(ADMIN_PASSWORD);
            Patient result=patientService.login(new Patient(account,password));
            if(result.getPatientAccount()!=null&&result.getPatientAccount().equalsIgnoreCase("root")){
                Gson gson=new Gson();
                Message message=new Message("SUCCESS");
                req.getSession().setAttribute("user",result);
                String json=gson.toJson(message);
                resp.getWriter().write(json);
                return;
            }else{
                Gson gson=new Gson();
                Message message=new Message("FAILED");
                String json=gson.toJson(message);
                resp.getWriter().write(json);
                return;
            }
        }else if(requestType.equalsIgnoreCase(CHECKALL)){
            List<DoctorReserve> reserveList=null;
            reserveList=patientService.queryForAllDoctorReserve();
            List<Doctor> doctorList=patientService.queryForAllDoctor();
            List<FullDoctorReserve> result=new ArrayList<>();
            List<Department> departmentList=patientService.checkAllDepartment();
            for(DoctorReserve doctorReserve:reserveList){
                String doctorName=null;
                String department=null;
                for(Doctor doctor:doctorList){
                    if (doctor.getDoctorID().equalsIgnoreCase(doctorReserve.getDoctorID())){
                        doctorName=doctor.getDoctorName();
                        for(Department department1:departmentList){
                            if (doctor.getDepartmentID().equalsIgnoreCase(department1.getDepartmentID())){
                                department=department1.getDepartmentName();
                            }
                        }
                    }
                }
                result.add(new FullDoctorReserve(doctorReserve.getDoctorID(),doctorName,department,doctorReserve.getReserveDate(),doctorReserve.getDoctorSurplus()+""));
            }
            Gson gson=new Gson();
            String json=gson.toJson(result);
            resp.getWriter().write(json);
            System.out.println(json);
            return;
        }else if(requestType.equalsIgnoreCase(APPEND_RESERVE)){
            String ID=req.getParameter(DOCTOR_ID);
            String date=req.getParameter(APPEND_DATE);
            String surplus=req.getParameter(APPEND_SURPLUS);
            int result=patientService.appendReserve(new DoctorReserve(ID,Integer.parseInt(surplus),date));
            if(result==1){
                Gson gson=new Gson();
                Message message=new Message("APPEND SUCCESS");
                String json=gson.toJson(message);
                resp.getWriter().write(json);
                return;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
