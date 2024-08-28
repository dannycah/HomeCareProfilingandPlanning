/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hcp_pb;

/**
 *
 * @author mark
 */
public class MyCases {
    private int caseID;
    private String clientName;
    private String caseType;
    private String casePriority;
    private int clientID; // Add clientID field

    public MyCases(int caseID, int clientID, String clientName, String caseType, String casePriority) {
        this.caseID = caseID;
        this.clientID = clientID; // Initialize clientID
        this.clientName = clientName;
        this.caseType = caseType;
        this.casePriority = casePriority;
    }

    // Getters
    public int getCaseID() {
        return caseID;
    }

    public String getClientName() {
        return clientName;
    }

    public String getCaseType() {
        return caseType;
    }

    public String getCasePriority() {
        return casePriority;
    }

    public int getClientID() { // Getter for clientID
        return clientID;
    }

    // Setters
    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public void setCasePriority(String casePriority) {
        this.casePriority = casePriority;
    }

    public void setClientID(int clientID) { // Setter for clientID
        this.clientID = clientID;
    }
}
