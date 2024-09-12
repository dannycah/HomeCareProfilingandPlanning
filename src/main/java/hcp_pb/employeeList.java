package hcp_pb;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author mark
 */
public class employeeList {
    private int eid;
    private String fullName;
    private String status;
    private String startD;
    private String endD;

    // Constructor
    public employeeList(int eid, String fullName, String status, String startD, String endD) {
        this.eid = eid;
        this.fullName = fullName;
        this.status = status;
        this.startD = startD;
        this.endD = endD;
    }

    // Getters and Setters
    public int getEid() {
        return eid;
    }

    public void getEid(int eid){
        this.eid = eid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartD() {
        return startD;
    }

    public void setStartD(String startD) {
        this.startD = startD;
    }

    public String getEndD() {
        return endD;
    }

    public void setEndD(String endD) {
        this.endD = endD;
    }
}
