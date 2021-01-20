package Servlet;

import Service.PatientService;
import Service.impl.PatientServiceImpl;
import com.google.gson.Gson;
import pojo.Department;
import pojo.Doctor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class requestInforServlet extends HttpServlet {

    private final static String REQUEST_TAG="request";
    private final static String REQUEST_FOR_ALL_DEP="allDepartment";
    private final static String REQUEST_FOR_DOCTOR_BY_DEP="requestForDoctorByDep";
    private final static String REQUEST_DEPARTMENT_ID="departmentID";
    private final static String REQUEST_FOR_SINGLE_DOCTOR="requestForDoctor";
    private final static String REQUEST_DOCTOR_ID="doctorID";

    PatientService patientService=new PatientServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        String request=req.getParameter(REQUEST_TAG);
        if(request.equalsIgnoreCase(REQUEST_FOR_ALL_DEP)){
            List<Department> departmentList=patientService.checkAllDepartment();
            Gson gson=new Gson();
            String json=gson.toJson(departmentList);
            for(Department department:departmentList){
                System.out.println(department.getDepartmentName());
            }
            resp.getWriter().write(json);
        }else if(request.equalsIgnoreCase(REQUEST_FOR_DOCTOR_BY_DEP)){
            String departmentID=req.getParameter(REQUEST_DEPARTMENT_ID);
            List<Doctor> doctorList=patientService.getDoctorByDepartment(departmentID);
            Gson gson=new Gson();
            String json=gson.toJson(doctorList);
            resp.getWriter().write(json);
        }else if(request.equalsIgnoreCase(REQUEST_FOR_SINGLE_DOCTOR)){
            String doctorId=req.getParameter(REQUEST_DOCTOR_ID);
            Doctor doctor=patientService.getDoctorByID(doctorId);
            Gson gson=new Gson();
            String json=gson.toJson(doctor);
            resp.getWriter().write(json);
        }
    }
}
