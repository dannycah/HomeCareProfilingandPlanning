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
public class caseReps {
    private String cName_case;
    private Date cDate_case;
    private String csoCase; 
    private String cStat_case;

    // Constructor
    public caseReps(String cName_case, Date cDate_case, String csoCase, String cStat_case) {
        this.cName_case = cName_case;
        this.cDate_case = cDate_case;
        this.csoCase = csoCase; 
        this.cStat_case = cStat_case;
    }

    // Getters
    public String getCName_case() {
        return cName_case;
    }

    public Date getCDate_case() {
        return cDate_case;
    }

    public String getCsoCase() { 
        return csoCase;
    }

    public String getCStat_case() {
        return cStat_case;
    }

    // Setters
    public void setCName_case(String cName_case) {
        this.cName_case = cName_case;
    }

    public void setCDate_case(Date cDate_case) {
        this.cDate_case = cDate_case;
    }

    public void setCsoCase(String csoCase) {
        this.csoCase = csoCase;
    }

    public void setCStat_case(String cStat_case) {
        this.cStat_case = cStat_case;
    }
}
