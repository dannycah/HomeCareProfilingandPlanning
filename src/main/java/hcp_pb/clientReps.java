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
import java.sql.Date;

public class clientReps {
    private int clientRepID;
    private String clientRepName;
    private Date clientRepBirth;
    private String clientRepLevel; 
    private String clientRepMed;

    // Constructor
    public clientReps(int clientRepID, String clientRepName, Date clientRepBirth, String clientRepLevel, String clientRepMed) {
        this.clientRepID = clientRepID;
        this.clientRepName = clientRepName;
        this.clientRepBirth = clientRepBirth;
        this.clientRepLevel = clientRepLevel;
        this.clientRepMed = clientRepMed;
    }

    // Getters
    public int getClientRepID() {
        return clientRepID;
    }

    public String getClientRepName() {
        return clientRepName;
    }

    public Date getClientRepBirth() {
        return clientRepBirth;
    }

    public String getClientRepLevel() {
        return clientRepLevel;
    }

    public String getClientRepMed() {
        return clientRepMed;
    }

    // Setters
    public void setClientRepID(int clientRepID) {
        this.clientRepID = clientRepID;
    }

    public void setClientRepName(String clientRepName) {
        this.clientRepName = clientRepName;
    }

    public void setClientRepBirth(Date clientRepBirth) {
        this.clientRepBirth = clientRepBirth;
    }

    public void setClientRepLevel(String clientRepLevel) {
        this.clientRepLevel = clientRepLevel;
    }

    public void setClientRepMed(String clientRepMed) {
        this.clientRepMed = clientRepMed;
    }
}

