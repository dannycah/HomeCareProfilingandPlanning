/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hcp_pb;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

/**
 *
 * @author mark
 */
public class Cases {
    private int caseID;
    private String clientName;
    private String caseType;
    private String assignedCSO;
    private String status;

    public Cases(int caseID, String clientName, String caseType, String assignedCSO, String status) {
        this.caseID = caseID;
        this.clientName = clientName;
        this.caseType = caseType;
        this.assignedCSO = assignedCSO;
        this.status = status;
    }

    public int getCaseID() {
        return caseID;
    }

    public String getClientName() {
        return clientName;
    }

    public String getCaseType() {
        return caseType;
    }

    public String getAssignedCSO() {
        return assignedCSO;
    }

    public String getStatus() {
        return status;
    }
}


