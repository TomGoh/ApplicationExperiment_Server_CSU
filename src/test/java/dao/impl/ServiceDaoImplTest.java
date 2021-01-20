package dao.impl;

import dao.ServiceDao;
import org.junit.Test;
import pojo.*;

import java.util.List;

public class ServiceDaoImplTest {

    ServiceDao serviceDao=new ServiceDaoImpl();

    @Test
    public void queryDoctorByID() {
        System.out.println(serviceDao.queryDoctorByID("1").toString());
    }

    @Test
    public void queryPatientByID() {
        System.out.println(serviceDao.queryDoctorByName("8208180521"));
    }

    @Test
    public void queryDoctorByName() {
        System.out.println(serviceDao.queryDoctorByName("胸外科一"));
    }

    @Test
    public void queryPatientByName() {
        System.out.println(serviceDao.queryPatientByName("吴昊泽"));
    }

    @Test
    public void queryForPatientList() {
        System.out.println(serviceDao.queryForPatientList().toString());
    }

    @Test
    public void savePatient() {
        System.out.println(serviceDao.savePatient(new Patient("789病人","123456","789")));
    }

    @Test
    public void saveReservation(){
        Reservation reservation=new Reservation("8208180521","2","1",0);
        System.out.println(serviceDao.saveReservation(reservation));
    }

    @Test
    public void queryForDoctorReserveRest(){
        List<Doctor> doctorList =serviceDao.queryForDoctorList();
        for(Doctor doctor:doctorList){
            System.out.println(doctor.getDoctorName()+":"+serviceDao.queryForDoctorReserveRest(doctor));
        }
    }

    @Test
    public void queryForDepartmentReserveRest(){
        List<Department> departmentList=serviceDao.queryForDepartmentList();
        for(Department department:departmentList){
            System.out.println(department.getDepartmentName()+":"+serviceDao.queryForDepartmentReserveRest(department));
        }
    }

    @Test
    public void queryForDoctorReservationByDateAndID(){
        System.out.println(serviceDao.queryForDoctorReservationByDateAndID("2020-12-24",new Doctor("1","神经外科一",null,null,"1")));
        System.out.println(serviceDao.queryForDoctorReservationByDateAndID("2020-12-24",new Doctor("2","神经外科二",null,null,"1")));
    }

    @Test
    public  void deleteAccount(){
        System.out.println(serviceDao.deleteAccount("123456789"));
    }

    @Test
    public void queryForFullReservation(){
        List<FullReservation> fullReservations=serviceDao.queryForFullReservation("8208180521");
        for(FullReservation reservation:fullReservations){
            System.out.println(reservation.getDoctorName()+" "+reservation.getPatientName()+" "+reservation.getDate());
        }
    }

    @Test
    public void queryForAddingBalance(){
        System.out.println(serviceDao.queryForAddingBalance("8208180521","1000"));
    }

    @Test
    public void queryForAlteringReservePayment(){
        System.out.println(serviceDao.queryForAlteringReservePayment("8208180521","2"));
    }

    @Test
    public void queryForCancelingReserve(){
        System.out.println(serviceDao.queryForCancelingReserve("8208180521","69"));
    }

    @Test
    public void queryForChangingPassword(){
        System.out.println(serviceDao.queryForChangingPassword("8208180507","123456789","123456"));
    }

    @Test
    public void InsertDoctor(){
        System.out.println(serviceDao.InsertDoctor(new Doctor("插入2测试","这是一条2插入测试语句","Master2 Dergee","2")));
    }

}