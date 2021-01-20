package dao.impl;

import dao.ServiceDao;
import pojo.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoImpl extends BaseDao implements ServiceDao {


    /**
     * 根据ID号查询医生信息
     * @param ID 医生的ID号
     * @return 医生类对象
     */
    @Override
    public Doctor queryDoctorByID(String ID) {
        String sql="select * from doctor where doctorID=?";
        return queryForOne(Doctor.class,sql,ID);
    }

    /**
     * 根据ID号查询病人信息
     * @param ID 病人的ID号
     * @return 病人类对象
     */
    @Override
    public Patient queryPatientByID(String ID) {
        String sql="select PatientName,PatientAccount,PatientPassword from patient where PatientAccount=?";
        return queryForOne(Patient.class,sql,ID);
    }

    /**
     * 根据姓名查询医生信息
     * @param Name 医生的姓名
     * @return 医生类对象
     */
    @Override
    public Doctor queryDoctorByName(String Name) {
        String sql="select * from doctor where doctorName=?";
        return queryForOne(Doctor.class,sql,Name);
    }

    /**
     * 根据姓名查询病人信息
     * @param Name 病人的姓名
     * @return 病人类对象
     */
    @Override
    public Patient queryPatientByName(String Name) {
        String sql="select * from patient where patientName=?";
        return queryForOne(Patient.class,sql,Name);
    }

    /**
     * 查询所有病人信息
     * @return 所有病人的列表
     */
    @Override
    public List<Patient> queryForPatientList() {
        String sql="Select * from patient";
        return queryForList(Patient.class,sql);
    }

    /**
     * 查询所有医生信息
     * @return 所有医生的列表
     */
    @Override
    public List<Doctor> queryForDoctorList() {
        String sql="Select * from doctor";
        return queryForList(Doctor.class,sql);
    }

    /**
     * 查询所有科室的信息
     * @return 包含所有科室的列表
     */
    @Override
    public List<Department> queryForDepartmentList() {
        String sql="Select * from department";
        return queryForList(Department.class,sql);
    }

    /**
     * 保存新加入的病人信息，同时注册账户
     * @param patient 新的病人对象
     * @return 返回2则表示添加成功，返回0则表示account注册成功但patient失败，返回-2均为失败
     */
    @Override
    public int savePatient(Patient patient) {
        String sql="insert into account (account,password) values(?,?);";
        int temp= update(sql,patient.getPatientAccount(),patient.getPatientPassword());
        String tempSQL="insert into patient(patientName,patientAccount,patientPassword) values(?,?,?)";
        temp+=update(tempSQL,patient.getPatientName(),patient.getPatientAccount(),patient.getPatientPassword());
        return temp;
    }

    /**
     * 保存新的预约信息
     * @param reservation 新的预约对象
     * @return 返回1则表示添加成功
     */
    @Override
    public int saveReservation(Reservation reservation) {
        String sql="insert into reserve(PatientAccount,DoctorID,ReserveID,DepartmentID,ReserveDate,Payed) values(?,?,?,?,?,?)";
        return update(sql,reservation.getPatientAccount(),reservation.getDoctorID(),null,reservation.getDepartmentID(),reservation.getReserveDate(),0);
    }

    /**
     * 根据医生对象查询剩余的挂号量
     * @param doctor 需要查询的医生对象
     * @return 该医生剩余的挂号量
     */
    @Override
    public int queryForDoctorReserveRest(Doctor doctor) {
        String sql="select * from doctorReserve where doctorID=?";
        DoctorReserve doctorReserve= queryForOne(DoctorReserve.class,sql,doctor.getDoctorID());
        return doctorReserve.getDoctorSurplus();
    }

    /**
     * 根据科室对象查询剩余的挂号量
     * @param department 需要查询的科室对象
     * @return 该科室剩余的挂号量
     */
    @Override
    public int queryForDepartmentReserveRest(Department department) {
        String sql="select * from departmentReserve where departmentID=?";
        DepartmentReserve departmentReserve= queryForOne(DepartmentReserve.class,sql,department.getDepartmentID());
        return departmentReserve.getDepartmentSurplus();
    }

    @Override
    public int queryForDoctorReservationByDateAndID(String date, Doctor doctor) {
        String sql="select * from doctorReserve where ReserveDate=? and DoctorId=?";
        DoctorReserve doctorReserve=queryForOne(DoctorReserve.class,sql,date,doctor.getDoctorID());
        return doctorReserve.getDoctorSurplus();
    }

    @Override
    public Patient queryPatientByIDAndPassword(String account, String password) {
        String sql="select * from patient where patientAccount=? and patientPassword=?";
        return queryForOne(Patient.class,sql,account,password);
    }

    @Override
    public List<Doctor> queryForDoctorByDepartmentID(String departmentID) {
        String sql="select * from doctor where departmentID=?";
        return queryForList(Doctor.class,sql,departmentID);
    }

    @Override
    public float queryForBalanceByAccount(String account) {
        String sql="select * from balance where PatientAccount=?";
        return queryForOne(Balance.class,sql,account).getBalance();
    }

    @Override
    public int deleteAccount(String account) {
        String sql="delete from account where Account = ?";
        return update(sql,account);
    }

    @Override
    public String queryForDepartmentIdByDoctorId(String doctorId) {
        String sql="select * from department where DepartmentID= (select departmentID from doctor where doctorID=?)";
        return queryForOne(Department.class,sql,doctorId).getDepartmentID();
    }

    @Override
    public List<Reservation> queryForPatientReservation(String patientID) {
        String sql="select * from reserve where patientAccount=?";
        return queryForList(Reservation.class,sql,patientID);
    }

    @Override
    public List<DoctorReserve> queryDoctorReserveByDoctorId(String doctorID) {
        String sql="select * from doctorReserve where doctorID=?";
        return queryForList(DoctorReserve.class,sql,doctorID);
    }

    @Override
    public List<FullReservation> queryForFullReservation(String patientID) {
        String sql1="select * from reserve where patientAccount=?";
        List<Reservation> reservationList=queryForList(Reservation.class,sql1,patientID);
        List<FullReservation> fullReservationList=new ArrayList<>();
        Patient patient=queryPatientByID(patientID);
        for(Reservation reservation:reservationList){
            Doctor doctor=queryDoctorByID(reservation.getDoctorID());
            String sql2="select * from department where departmentID=?";
            Department department=queryForOne(Department.class,sql2,doctor.getDepartmentID());
            FullReservation fullReservation=new FullReservation(doctor.getDoctorName(),department.getDepartmentName(),patient.getPatientName(),reservation.getPayed(),reservation.getReserveDate(),reservation.getReserveID());
            fullReservationList.add(fullReservation);
        }
        return fullReservationList;
    }


    @Override
    public int queryForAddingBalance(String patientID, String amount) {
        String sql="update balance set Balance = Balance+ ? where patientAccount=?";
        return update(sql,amount,patientID);
    }

    @Override
    public int queryForAlteringReservePayment(String patientID, String reserveID) {
        String sql="update reserve set payed =1 where patientAccount=? and reserveID=?";
        String sql2="update balance set balance=balance-10 where patientAccount=?";
        return update(sql2,patientID)+update(sql,patientID,reserveID);
    }

    @Override
    public int queryForCancelingReserve(String patientID, String reserveID) {
        String sql0="select * from reserve where patientAccount=? and reserveID=?";
        Reservation reservation=queryForOne(Reservation.class,sql0,patientID,reserveID);
        int result=0;
        String sql="delete from reserve where patientAccount=? and reserveID=?";
        result+=update(sql,patientID,reserveID);
        if(reservation.getPayed()==1){
            String sql1="update balance set balance=balance+10 where patientAccount=?";
            result+=update(sql1,patientID);
        }

        return result;
    }

    @Override
    public int queryForChangingPassword(String patientID, String oldPassword, String newPassword) {

        String sql="update account set Password=? where Account=? and Password=?";
        return update(sql,newPassword,patientID,oldPassword);
    }

    @Override
    public Department queryForDepartmentByName(String departmentName) {
        String sql="select * from department where departmentName=?";
        return queryForOne(Department.class,sql,departmentName);
    }

    @Override
    public int InsertDoctor(Doctor doctor) {
        String sql="insert into doctor(DoctorName, DoctorDescription, DepartmentID,DoctorDegree) values(?,?,?,?)";
        return update(sql,doctor.getDoctorName(),doctor.getDoctorDescription(),doctor.getDepartmentID(),doctor.getDoctorDegree());
    }

    @Override
    public List<DoctorReserve> queryForAllDoctorReserve() {
        String sql="select * from doctorReserve";
        return queryForList(DoctorReserve.class,sql);
    }

    @Override
    public List<Doctor> queryForAllDoctor() {
        String sql="select * from doctor";
        return queryForList(Doctor.class,sql);
    }

    @Override
    public int appendDoctor(Doctor doctor) {
        String sql="insert into doctor(doctorName,departmentID,doctorDegree) values(?,?,?)";
        return update(sql,doctor.getDoctorName(),doctor.getDepartmentID(),doctor.getDoctorDegree());
    }

    @Override
    public int appendReserve(DoctorReserve doctorReserve) {
        String sql="insert into doctorReserve(doctorID,doctorSurplus,ReserveDate) values(?,?,?)";
        return update(sql,doctorReserve.getDoctorID(),doctorReserve.getDoctorSurplus(),doctorReserve.getReserveDate());
    }
}
