package pojo;

public class Doctor {

    private String DoctorID;//医生编号

    private String DoctorName;//医生姓名

    private String DoctorDescription;//医生简介

    private String DoctorDegree;//医生职称

    private String DepartmentID;//医生所属的科室的编号

    public Doctor(String doctorID, String doctorName, String doctorDescription, String doctorDegree, String departmentID) {
        DoctorID = doctorID;
        DoctorName = doctorName;
        DoctorDescription = doctorDescription;
        DoctorDegree = doctorDegree;
        DepartmentID = departmentID;
    }

    public Doctor(String doctorName,String doctorDescription,String doctorDegree,String departmentID){
        this.DoctorID="";
        this.DoctorName=doctorName;
        this.DoctorDescription=doctorDescription;
        this.DoctorDegree=doctorDegree;
        this.DepartmentID=departmentID;
    }

    public String getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(String doctorID) {
        DoctorID = doctorID;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getDoctorDescription() {
        return DoctorDescription;
    }

    public void setDoctorDescription(String doctorDescription) {
        DoctorDescription = doctorDescription;
    }

    public String getDoctorDegree() {
        return DoctorDegree;
    }

    public void setDoctorDegree(String doctorDegree) {
        DoctorDegree = doctorDegree;
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String departmentID) {
        DepartmentID = departmentID;
    }

    public Doctor() {
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "DoctorID='" + DoctorID + '\'' +
                ", DoctorName='" + DoctorName + '\'' +
                ", DoctorDescription='" + DoctorDescription + '\'' +
                ", DoctorDegree='" + DoctorDegree + '\'' +
                ", DepartmentID='" + DepartmentID + '\'' +
                '}';
    }
}
