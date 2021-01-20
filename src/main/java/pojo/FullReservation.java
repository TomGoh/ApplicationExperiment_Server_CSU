package pojo;

public class FullReservation {
    String doctorName;//医生姓名
    String departmentName;//科室名称
    String patientName;//患者姓名
    Integer payment;//预约支付情况
    String date;//预约日期
    String reserveID;//预约编号

    public FullReservation() {
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer isPayment() {
        return payment;
    }

    public FullReservation(String doctorName, String departmentName, String patientName, Integer payment, String date,String reserveID) {
        this.doctorName = doctorName;
        this.departmentName = departmentName;
        this.patientName = patientName;
        this.payment = payment;
        this.date = date;
        this.reserveID=reserveID;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPayment() {
        return payment;
    }

    public String getReserveID() {
        return reserveID;
    }

    public void setReserveID(String reserveID) {
        this.reserveID = reserveID;
    }


}
