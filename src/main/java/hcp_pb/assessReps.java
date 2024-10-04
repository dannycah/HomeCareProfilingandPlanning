/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hcp_pb;

import java.sql.Date;

/**
 *
 * @author mark
 */
public class assessReps {
    private String assessName;
    private Date assessDate;
    private String assessCSO;
    private String assessRemarks;

    // Constructor
    public assessReps(String assessName, Date assessDate, String assessCSO, String assessRemarks) {
        this.assessName = assessName;
        this.assessDate = assessDate;
        this.assessCSO = assessCSO;
        this.assessRemarks = assessRemarks;
    }

    // Getters
    public String getAssessName() {
        return assessName;
    }

    public Date getAssessDate() {
        return assessDate;
    }

    public String getAssessCSO() {
        return assessCSO;
    }

    public String getAssessRemarks() {
        return assessRemarks;
    }

    // Setters
    public void setAssessName(String assessName) {
        this.assessName = assessName;
    }

    public void setAssessDate(Date assessDate) {
        this.assessDate = assessDate;
    }

    public void setAssessCSO(String assessCSO) {
        this.assessCSO = assessCSO;
    }

    public void setAssessRemarks(String assessRemarks) {
        this.assessRemarks = assessRemarks;
    }
}

