package pojo;

public class FullDoctorReserve {

    String doctorID;
    String doctorName;
    String doctorDepartment;
    String date;
    String surplus;

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorDepartment() {
        return doctorDepartment;
    }

    public void setDoctorDepartment(String doctorDepartment) {
        this.doctorDepartment = doctorDepartment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSurplus() {
        return surplus;
    }

    public void setSurplus(String surplus) {
        this.surplus = surplus;
    }

    public FullDoctorReserve() {
    }

    public FullDoctorReserve(String doctorID, String doctorName, String doctorDepartment, String date, String surplus) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.doctorDepartment = doctorDepartment;
        this.date = date;
        this.surplus = surplus;
    }
}
