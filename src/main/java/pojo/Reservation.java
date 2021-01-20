package pojo;

import utils.GenerateDate;

public class Reservation {

    String patientAccount;//患者账号

    String DoctorID;//预约的医生编号

    String ReserveID;//预约的编号

    String DepartmentID;//预约的科室编号

    String ReserveDate;//预约的日期

    Integer Payed;//预约的缴费情况

    public Reservation() {
    }

    public Reservation(String patientAccount, String doctorID,  String departmentID, Integer payed) {
        this.patientAccount = patientAccount;
        DoctorID = doctorID;
        DepartmentID = departmentID;
        ReserveDate= GenerateDate.getYYYYMMDD();
        Payed=payed;
    }

    public String getPatientAccount() {
        return patientAccount;
    }

    public void setPatientAccount(String patientAccount) {
        this.patientAccount = patientAccount;
    }

    public String getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(String doctorID) {
        DoctorID = doctorID;
    }

    public String getReserveID() {
        return ReserveID;
    }

    public void setReserveID(String reserveID) {
        ReserveID = reserveID;
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String departmentID) {
        DepartmentID = departmentID;
    }

    public String getReserveDate() {
        return ReserveDate;
    }

    public void setReserveDate(String reserveDate) {
        ReserveDate = reserveDate;
    }

    public  Reservation(String patientAccount,String doctorID,String departmentID,String reserveDate,Integer payed){
        this.ReserveDate=reserveDate;
        this.DepartmentID=departmentID;
        this.DoctorID=doctorID;
        this.patientAccount=patientAccount;
        this.Payed=payed;
    }

    public Integer getPayed() {
        return Payed;
    }

    public void setPayed(Integer payed) {
        Payed = payed;
    }
}
