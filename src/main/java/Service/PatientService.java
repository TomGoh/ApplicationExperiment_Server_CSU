package Service;

import pojo.*;

import javax.print.Doc;
import java.util.List;

public interface PatientService {

    /**
     * 查找一个账号的可用程度
     * @param account 需要查询的账号
     * @return 如果已被占用则返回true，未被占用返回false
     */
    public Boolean checkAccountUsage(String account);

    /**
     * 实现登录功能
     * @param patient 需要登陆的病人类
     * @return 返回Null为登陆失败，否则为成功
     */
    public Patient login(Patient patient);

    /**
     * 实现注册功能
     * @param patient 需要注册的病人
     * @return 返回2则表示注册成功，返回-2表示失败，返回-1表示重复的用户名
     */
    public int register(Patient patient);

    /**
     * 根据病人的账户获得病人信息
     * @param account 病人的账号
     * @return 查询的病人对象，为null则 表示失败
     */
    public Patient getPatientByAccount(String account);

    /**
     * 根据医生信息和日期查询可用的预约数
     * @param Doctor 医生类
     * @param date 需要查询的日期
     * @return 当天对应医生的剩余预约数
     */
    public int getAvailableReservationByDateAndDoctorID(Doctor Doctor, String date);

    /**
     * 根据科室编号查询该科室的所有医生
     * @param departmentID 需要查询的科室的编号
     * @return 医生列表
     */
    public List<Doctor> getDoctorByDepartment(String departmentID);

    /**
     * 查询病人的账户余额
     * @param patientID 需要查询的病人ID
     * @return 账户余额
     */
    public float checkForBalance(String patientID);

    /**
     * 查询所有的科室
     * @return 科室列表
     */
    public List<Department> checkAllDepartment();

    /**
     * 工具函数，防止注册时Account成功但Patient失败的情况
     * @param account 对应的Account
     * @return 返回1则表示Revoke Successfully
     */
    public int deleteAccount(String account);

    /**
     * 根据医生编号查询医生对象
     * @param id 医生的编号
     * @return 需要查询的医生对象
     */
    public Doctor getDoctorByID(String id);

    /**
     * 保存预约信息
     * @param patientID 预约的患者的ID
     * @param doctorID 预约的医生的ID
     * @param reservedDate 预约的日期
     * @return SQL执行结果
     */
    public int saveReservation(String patientID,String doctorID,String reservedDate);

    /**
     * 获得患者的所有预约信息
     * @param patientID 患者ID
     * @return 所有的预约信息
     */
    public List<Reservation> getPatientReservation(String patientID);

    /**
     * 根据医生的编号获得医生的预约信息
     * @param ID 医生编号
     * @return 医生预约信息列表
     */
    public  List<DoctorReserve> getDoctorSurplusByID(String ID);

    /**
     * 根据患者的ID获得其预约的完整信息
     * @param patientID 患者ID
     * @return 患者名下所有预约的详细信息
     */
    public List<FullReservation> getFullReservationByID(String patientID);

    /**
     *  根据患者的ID查询余额
     * @param patientID 患者ID
     * @return 余额
     */
    public float getBalanceByPatientID(String patientID);

    /**
     * 修改患者的余额
     * @param patientID 患者账号
     * @param amount 修改的余额
     * @return 执行SQL结果
     */
    public int alterBalance(String patientID,String amount);

    /**
     * 更改某一个预约的支付情况
     * @param patientID 患者的ID
     * @param reserveID 预约的ID
     * @return 执行SQL语句的结果
     */
    public int alterReservationPayment(String patientID,String reserveID);

    /**
     * 取消预约
     * @param patientID 患者ID
     * @param reserveID 预约ID
     * @return 执行SQL语句的结果
     */
    public int queryForCancelingReserve(String patientID,String reserveID);

    /**
     * 修改密码
     * @param account 患者账户
     * @param oldPassword 患者账户的旧密码
     * @param newPassword 患者账户的密码
     * @return 执行SQL语句的结果
     */
    public int changePassword(String account,String oldPassword,String newPassword);

    public Department queryDepartmentByName(String departmentName);

    public List<DoctorReserve> queryForAllDoctorReserve();

    public List<Doctor> queryForAllDoctor();

    public int appendDoctor(Doctor doctor);

    public int appendReserve(DoctorReserve doctorReserve);
}
