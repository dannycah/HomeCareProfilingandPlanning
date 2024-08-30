/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hcp_pb;

/**
 *
 * @author mark
 */
public class CreateCase {
    private int cID;
    private String fullName;
    private String fundLevel;
    private String mediCare;

    // Constructor
    public CreateCase(int cID, String fullName, String fundLevel, String mediCare) {
        this.cID = cID;
        this.fullName = fullName;
        this.fundLevel = fundLevel;
        this.mediCare = mediCare;
    }

    // Getter and Setter for cID
    public int getCID() {
        return cID;
    }

    public void setCID(int cID) {
        this.cID = cID;
    }

    // Getter and Setter for fullName
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Getter and Setter for fundLevel
    public String getFundLevel() {
        return fundLevel;
    }

    public void setFundLevel(String fundLevel) {
        this.fundLevel = fundLevel;
    }

    // Getter and Setter for mediCare
    public String getMediCare() {
        return mediCare;
    }

    public void setMediCare(String mediCare) {
        this.mediCare = mediCare;
    }
}




