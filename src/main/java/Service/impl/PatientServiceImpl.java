package Service.impl;

import Service.PatientService;
import dao.ServiceDao;
import dao.impl.ServiceDaoImpl;
import pojo.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PatientServiceImpl implements PatientService {

    ServiceDao serviceDao=new ServiceDaoImpl();

    /**
     * 查找一个账号的可用程度
     * @param account 需要查询的账号
     * @return 如果已被占用则返回true，未被占用返回false
     */
    @Override
    public Boolean checkAccountUsage(String account) {
        return serviceDao.queryPatientByID(account)!=null;
    }

    /**
     * 实现登录功能
     * @param patient 需要登陆的病人类
     * @return 返回Null为登陆失败，否则为成功
     */
    @Override
    public Patient login(Patient patient) {
        return serviceDao.queryPatientByIDAndPassword(patient.getPatientAccount(),patient.getPatientPassword());
    }

    /**
     * 实现注册功能
     * @param patient 需要注册的病人
     * @return 返回2则表示注册成功，返回-2表示失败，返回-1表示重复的用户名
     */
    @Override
    public int register(Patient patient) {
        if(!checkAccountUsage(patient.getPatientAccount())){
            return serviceDao.savePatient(patient);
        }else{
            return -1;
        }
    }

    /**
     * 根据病人的账户获得病人信息
     * @param account 病人的账号
     * @return 查询的病人对象，为null则 表示失败
     */
    @Override
    public Patient getPatientByAccount(String account) {
        return serviceDao.queryPatientByID(account);
    }

    /**
     * 根据医生信息和日期查询可用的预约数
     * @param doctor 医生类
     * @param date 需要查询的日期
     * @return 当天对应医生的剩余预约数
     */
    @Override
    public int getAvailableReservationByDateAndDoctorID(Doctor doctor, String date) {
        return serviceDao.queryForDoctorReservationByDateAndID(date,doctor);
    }

    /**
     * 根据科室编号查询该科室的所有医生
     * @param departmentID 需要查询的科室的编号
     * @return 医生列表
     */
    @Override
    public List<Doctor> getDoctorByDepartment(String departmentID) {
        return serviceDao.queryForDoctorByDepartmentID(departmentID);
    }

    /**
     * 查询病人的账户余额
     * @param patientID 需要查询的病人ID
     * @return 账户余额
     */
    @Override
    public float checkForBalance(String patientID) {
        return serviceDao.queryForBalanceByAccount(patientID);
    }

    /**
     * 查询所有的科室
     * @return 科室列表
     */
    @Override
    public List<Department> checkAllDepartment() {
       return serviceDao.queryForDepartmentList();
    }

    /**
     * 工具函数，防止注册时Account成功但Patient失败的情况
     * @param account 对应的Account
     * @return 返回1则表示Revoke Successfully
     */
    @Override
    public int deleteAccount(String account) {
        return serviceDao.deleteAccount(account);
    }

    @Override
    public Doctor getDoctorByID(String id) {
        return serviceDao.queryDoctorByID(id);
    }

    @Override
    public int saveReservation(String patientID, String doctorID, String reservedDate){
        String departmentID=serviceDao.queryForDepartmentIdByDoctorId(doctorID);
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Date date=simpleDateFormat.parse(reservedDate);
        return serviceDao.saveReservation(new Reservation(patientID,doctorID,departmentID,reservedDate,0));
    }

    @Override
    public List<Reservation> getPatientReservation(String patientID) {
        return serviceDao.queryForPatientReservation(patientID);
    }

    @Override
    public List<DoctorReserve> getDoctorSurplusByID(String ID) {
        return serviceDao.queryDoctorReserveByDoctorId(ID);
    }

    /**
     * 根据病人编号返回完整的预约数据
     * @param patientID 病人编号
     * @return 完整的预约数据
     */
    @Override
    public List<FullReservation> getFullReservationByID(String patientID) {
        return serviceDao.queryForFullReservation(patientID);
    }

    @Override
    public float getBalanceByPatientID(String patientID) {
        return  serviceDao.queryForBalanceByAccount(patientID);
    }

    @Override
    public int alterBalance(String patientID, String amount) {
        return serviceDao.queryForAddingBalance(patientID,amount);
    }

    @Override
    public int alterReservationPayment(String patientID, String reserveID) {
        return serviceDao.queryForAlteringReservePayment(patientID,reserveID);
    }

    @Override
    public int queryForCancelingReserve(String patientID, String reserveID) {
        return serviceDao.queryForCancelingReserve(patientID,reserveID);
    }

    @Override
    public int changePassword(String account, String oldPassword, String newPassword) {
        return serviceDao.queryForChangingPassword(account,oldPassword,newPassword);
    }

    @Override
    public Department queryDepartmentByName(String departmentName) {
        return serviceDao.queryForDepartmentByName(departmentName);
    }

    @Override
    public List<DoctorReserve> queryForAllDoctorReserve() {
        return serviceDao.queryForAllDoctorReserve();
    }

    @Override
    public List<Doctor> queryForAllDoctor(){
        return serviceDao.queryForAllDoctor();
    }

    @Override
    public int appendDoctor(Doctor doctor) {
        return serviceDao.appendDoctor(doctor);
    }

    @Override
    public int appendReserve(DoctorReserve doctorReserve){
        return serviceDao.appendReserve(doctorReserve);
    }
}
