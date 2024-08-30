/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hcp_pb;

/**
 *
 * @author mark
 */
public class Clients {

    private int acID;
    private String acName;

    // Constructor
    public Clients(int acID, String acName) {
        this.acID = acID;
        this.acName = acName; 
    }

    // Getter for aClientIDtab
    public int getAcID() {
        return acID;
    }

    // Setter for aClientIDtab
    public void setAcID(int acID) {
        this.acID = acID;
    }

    // Getter for aClientNametab
    public String getAcName() {
        return acName;
    }

    // Setter for aClientNametab
    public void setAcName(String acName) {
        this.acName = acName;
    }

}

