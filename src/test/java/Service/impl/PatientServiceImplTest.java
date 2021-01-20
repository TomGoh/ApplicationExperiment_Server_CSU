package Service.impl;

import Service.PatientService;
import org.junit.Test;
import pojo.Department;
import pojo.Doctor;
import pojo.DoctorReserve;
import pojo.Patient;
import utils.Message;

import javax.print.Doc;
import java.util.List;

import static org.junit.Assert.*;

public class PatientServiceImplTest {

    PatientService patientService=new PatientServiceImpl();
    @Test
    public void checkAccountUsage() {
        System.out.println(patientService.checkAccountUsage("8208180521"));
        System.out.println(patientService.checkAccountUsage("8208180522"));
    }

    @Test
    public void login() {
        System.out.println(patientService.login(new Patient("吴昊泽","Dashui506","8208180521")).getPatientName());
    }

    @Test
    public void register() {
        System.out.println(patientService.register(new Patient("def","123456","defdef")));
        System.out.println(patientService.register(new Patient("吴昊泽","123456","8208180521")));
    }

    @Test
    public void getPatientByAccount() {
        System.out.println(patientService.getPatientByAccount("8208180521").getPatientName());
    }

    @Test
    public void getAvailableReservationByDateAndDoctorID() {
        System.out.println(patientService.getAvailableReservationByDateAndDoctorID(new Doctor("1","神经外科一",null,null,"1"),"2020-12-24"));
        System.out.println(patientService.getAvailableReservationByDateAndDoctorID(new Doctor("2","神经外科二",null,null,"1"),"2020-12-24"));
    }

    @Test
    public void getDoctorByDepartment() {
        List<Doctor> doctorList=patientService.getDoctorByDepartment("1");
        for(Doctor doctor:doctorList){
            System.out.println(doctor.getDoctorName());
        }
    }

    @Test
    public void checkForBalance() {
        System.out.println(patientService.checkForBalance("789"));
    }

    @Test
    public void checkAllDepartment() {
        List<Department> departmentList=patientService.checkAllDepartment();
        for(Department department:departmentList){
            System.out.println(department.getDepartmentName());
        }
    }

    @Test
    public void getDoctorSurplusByID(){
        List<DoctorReserve> doctorReserves=patientService.getDoctorSurplusByID("1");
        for(DoctorReserve doctorReserve:doctorReserves){
            System.out.println(doctorReserve.getDoctorID()+"  "+doctorReserve.getDoctorSurplus());
        }
    }

    @Test
    public void saveReservation(){
        int result=patientService.saveReservation("8208180521","1","2020-12-24");
    }
}