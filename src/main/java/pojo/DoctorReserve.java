package pojo;

import utils.GenerateDate;

public class DoctorReserve {

    String DoctorID;//医生编号

    int DoctorSurplus;//医生预约的剩余量

    String ReserveDate;//预约日期

    public DoctorReserve() {
    }

    public DoctorReserve(String doctorID, int doctorSurplus) {
        DoctorID = doctorID;
        DoctorSurplus = doctorSurplus;
        ReserveDate= GenerateDate.getYYYYMMDD();
    }

    public String getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(String doctorID) {
        DoctorID = doctorID;
    }

    public int getDoctorSurplus() {
        return DoctorSurplus;
    }

    public void setDoctorSurplus(int doctorSurplus) {
        DoctorSurplus = doctorSurplus;
    }

    public String getReserveDate() {
        return ReserveDate;
    }

    public void setReserveDate(String reserveDate) {
        ReserveDate = reserveDate;
    }

    public DoctorReserve(String doctorID, int doctorSurplus, String reserveDate) {
        DoctorID = doctorID;
        DoctorSurplus = doctorSurplus;
        ReserveDate = reserveDate;
    }
}
