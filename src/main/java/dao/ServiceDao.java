package dao;

import pojo.*;

import javax.print.Doc;
import java.util.List;

public interface ServiceDao {

    /**
     * 根据ID号查询医生信息
     * @param ID 医生的ID号
     * @return 医生类对象
     */
    public Doctor queryDoctorByID(String ID);

    /**
     * 根据ID号查询病人信息
     * @param ID 病人的ID号
     * @return 病人类对象
     */
    public Patient queryPatientByID(String ID);

    /**
     * 根据姓名查询医生信息
     * @param Name 医生的姓名
     * @return 医生类对象
     */
    public Doctor queryDoctorByName(String Name);

    /**
     * 根据账号和密码查询患者信息
     * @param account 账号
     * @param Password 密码
     * @return 查询的患者对象
     */
    public Patient queryPatientByIDAndPassword(String account,String Password);

    /**
     * 根据姓名查询病人信息
     * @param Name 病人的姓名
     * @return 病人类对象
     */
    public Patient queryPatientByName(String Name);

    /**
     * 查询所有病人信息
     * @return 所有病人的列表
     */
    public List<Patient> queryForPatientList();

    /**
     * 查询所有医生信息
     * @return 所有医生的列表
     */
    public List<Doctor> queryForDoctorList();

    /**
     * 根据科室编号查询该科室的所有医生
     * @param departmentID 科室编号
     * @return 该科室的所有医生组成的列表
     */
    public List<Doctor> queryForDoctorByDepartmentID(String departmentID);

    /**
     * 查询所有科室的信息
     * @return 包含所有科室的列表
     */
    public List<Department> queryForDepartmentList();

    /**
     * 保存新加入的病人信息，同时注册账户
     * @param patient 新的病人对象
     * @return 返回2则表示添加成功，返回0则表示account注册成功但patient失败，返回-2均为失败
     */
    public int savePatient(Patient patient);

    /**
     * 保存新的预约信息
     * @param reservation 新的预约对象
     * @return 返回1则表示添加成功
     */
    public int saveReservation(Reservation reservation);

    /**
     * 根据医生对象查询剩余的挂号量
     * @param doctor 需要查询的医生对象
     * @return 该医生剩余的挂号量
     */
    public int queryForDoctorReserveRest(Doctor doctor);

    /**
     * 根据科室对象查询剩余的挂号量
     * @param department 需要查询的科室对象
     * @return 该科室剩余的挂号量
     */
    public int queryForDepartmentReserveRest(Department department);

    /**
     * 根据日期和医生对象查找对应医生的预约的剩余量
     * @param date 需要查询的日期
     * @param doctor 医生对象
     * @return 在该日期该医生的剩余预约量
     */
    public int queryForDoctorReservationByDateAndID(String date,Doctor doctor);

    /**
     * 根据患者账号查询余额
     * @param account 患者账号
     * @return 余额
     */
    public float queryForBalanceByAccount(String account);

    /**
     * 删除账户
     * @param account 需要删除的账户
     * @return 执行SQL语句的结果，返回1为成功
     */
    public int deleteAccount(String account);

    /**
     * 根据医生的编号查找其对应科室号
     * @param doctorId 医生编号
     * @return 该医生所在的科室的编号
     */
    public String queryForDepartmentIdByDoctorId(String doctorId);

    /**
     * 根据患者编号查询其名下的所有预约
     * @param patientID 患者编号
     * @return 其名下所有预约的列表
     */
    public List<Reservation> queryForPatientReservation(String patientID);

    /**
     * 根据医生编号查询医生的预约情况
     * @param doctorID 医生编号
     * @return 该医生在系统内所有日期的预约情况
     */
    public List<DoctorReserve> queryDoctorReserveByDoctorId(String doctorID);

    /**
     * 根据患者编号查询预约的完整信息
     * @param patientID 患者ID
     * @return 所有完整预约信息的列表
     */
    public List<FullReservation> queryForFullReservation(String patientID);

    /**
     * 增加患者账户内的余额
     * @param patientID 患者ID
     * @param amount 需要增加的余额
     * @return 执行SQL语句的结果
     */
    public int queryForAddingBalance(String patientID,String amount);

    /**
     * 更改某一个预约的支付情况
     * @param patientID 患者的ID
     * @param reserveID 预约的ID
     * @return 执行SQL语句的结果
     */
    public int queryForAlteringReservePayment(String patientID,String reserveID);

    /**
     * 取消预约
     * @param patientID 患者ID
     * @param reserveID 预约ID
     * @return 执行SQL语句的结果
     */
    public int queryForCancelingReserve(String patientID,String reserveID);

    /**
     * 修改密码
     * @param patientID 患者账户
     * @param oldPassword 患者账户的旧密码
     * @param newPassword 患者账户的密码
     * @return 执行SQL语句的结果
     */
    public int queryForChangingPassword(String patientID,String oldPassword,String newPassword);

    /**
     * 根据科室名称查找科室对象
     * @param departmentName 科室名称
     * @return 需要查找的科室对象
     */
    public Department queryForDepartmentByName(String departmentName);

    /**
     * 插入医生数据
     * @param doctor 医生对象
     * @return SQL语句执行结果
     */
    public int InsertDoctor(Doctor doctor);

    /**
     * 查询所有医生的预约信息以供管理界面显示
     * @return 医生预约的列表
     */
    public List<DoctorReserve> queryForAllDoctorReserve();

    /**
     * 查询所有医生信息
     * @return 医生信息列表
     */
    public List<Doctor> queryForAllDoctor();

    /**
     * 增加医生信息
     * @param doctor 需要增加的医生对象
     * @return SQL语句执行结果
     */
    public int appendDoctor(Doctor doctor);

    /**
     * 新增医生的预约安排
     * @param doctorReserve 需要增加的医生安排
     * @return SQL语句的执行结果
     */
    public int appendReserve(DoctorReserve doctorReserve);
}
