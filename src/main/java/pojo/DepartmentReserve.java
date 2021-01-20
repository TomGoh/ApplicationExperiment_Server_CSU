package pojo;

import utils.GenerateDate;

import java.util.Date;

public class DepartmentReserve {

    String DepartmentID;//预约的科室的编号

    int DepartmentSurplus;//科室的预约剩余量

    String ReserveDate;//预约的日期

    public DepartmentReserve() {
    }

    public DepartmentReserve(String departmentID, int departmentReserve) {
        DepartmentID = departmentID;
        DepartmentSurplus = departmentReserve;
        ReserveDate= GenerateDate.getYYYYMMDD();
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String departmentID) {
        DepartmentID = departmentID;
    }

    public int getDepartmentSurplus() {
        return DepartmentSurplus;
    }

    public void setDepartmentSurplus(int departmentReserve) {
        DepartmentSurplus = departmentReserve;
    }

    public String getReserveDate() {
        return ReserveDate;
    }

    public void setReserveDate(String reserveDate) {
        ReserveDate = reserveDate;
    }
}
