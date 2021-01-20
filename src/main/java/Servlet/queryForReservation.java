package Servlet;

import Service.PatientService;
import Service.impl.PatientServiceImpl;
import com.google.gson.Gson;
import pojo.Doctor;
import pojo.DoctorReserve;
import pojo.FullReservation;
import pojo.Reservation;
import utils.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 一个提供查询预约的类，根据前端查询的不同数值返回不同的结果
 * 可能提供的返回结果有：
 *  1， 某个日期的某个医生的剩余预约数
 *  2. 某个科室某个日期的剩余预约数
 */
public class queryForReservation extends HttpServlet {
    PatientService patientService=new PatientServiceImpl();

    private final static String REQUEST_DOCTOR="doctorID";
    private final static String REQUEST_DATE="date";
    private final static String REQUEST_HEAD="request";
    private final static String REQUEST_GET_SURPLUS="getSurplusByDoctor";
    private final static String REQUEST_SAVE_RESERVATION="savePatientReservation";
    private final static String REQUEST_PATIENT_ID="patientID";
    private final static String RESERVATION_SAVED="reservationSaved";
    private final static String GET_PATIENT_RESERVATION="getPatientReservation";
    private final static String GET_FULL_RESERVATION="getFullReservation";
    private final static String REQUEST_FOR_CANCELLING="cancelReservation";
    private final static String RESERVE_ID="reserveID";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        String requestType=req.getParameter(REQUEST_HEAD);

        if(requestType.equalsIgnoreCase(REQUEST_GET_SURPLUS)) {
            String ID=req.getParameter(REQUEST_DOCTOR);
            List<DoctorReserve> doctorReserveList=patientService.getDoctorSurplusByID(ID);
            Gson gson=new Gson();
            String json=gson.toJson(doctorReserveList);
            resp.getWriter().write(json);
        }else if(requestType.equalsIgnoreCase(REQUEST_SAVE_RESERVATION)){
            String patientID=req.getParameter(REQUEST_PATIENT_ID);
            String doctorID=req.getParameter(REQUEST_DOCTOR);
            String date=req.getParameter(REQUEST_DATE);
            int result=patientService.saveReservation(patientID,doctorID,date);
            if(result==1){
                Gson gson=new Gson();
                Message message=new Message(RESERVATION_SAVED);
                String json=gson.toJson(message);
                resp.getWriter().write(json);
            }
        }else if(requestType.equalsIgnoreCase(GET_PATIENT_RESERVATION)){
            String patientID=req.getParameter(REQUEST_PATIENT_ID);
            List<Reservation> reservationList=patientService.getPatientReservation(patientID);
            Gson gson=new Gson();
            String json=gson.toJson(reservationList);
            resp.getWriter().write(json);
        }else if(requestType.equalsIgnoreCase(GET_FULL_RESERVATION)){
            String patientID=req.getParameter(REQUEST_PATIENT_ID);
            List<FullReservation> fullReservationList=patientService.getFullReservationByID(patientID);
            Gson gson=new Gson();
            String json=gson.toJson(fullReservationList);
            resp.getWriter().write(json);
        }else if(requestType.equalsIgnoreCase(REQUEST_FOR_CANCELLING)){
            String patientID=req.getParameter(REQUEST_PATIENT_ID);
            String reserveID=req.getParameter(RESERVE_ID);
            int result=patientService.queryForCancelingReserve(patientID,reserveID);
            if(result==2||result==1){
                Message message=new Message("SUCCESS");
                Gson gson=new Gson();
                String json=gson.toJson(message);
                resp.getWriter().write(json);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
