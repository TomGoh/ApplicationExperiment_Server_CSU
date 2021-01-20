package pojo;

public class Department {

    private String departmentName;//科室名称

    private String departmentID;//科室编号

    private String departmentDescription;//科室简介

    public Department(String departName, String departmentID, String departmentDescription) {
        this.departmentName = departName;
        this.departmentID = departmentID;
        this.departmentDescription = departmentDescription;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    public Department() {
    }
}
