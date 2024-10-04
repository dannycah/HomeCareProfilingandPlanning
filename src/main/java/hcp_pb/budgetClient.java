/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hcp_pb;

/**
 *
 * @author mark
 */
public class budgetClient {
    private int bcid;
    private String bcname;

    // Constructor
    public budgetClient(int bcid, String bcname) {
        this.bcid = bcid;
        this.bcname = bcname;
    }

    // Getters and Setters
    public int getBcid() {
        return bcid;
    }

    public void setBcid(int bcid) {
        this.bcid = bcid;
    }

    public String getBcname() {
        return bcname;
    }

    public void setBcname(String bcname) {
        this.bcname = bcname;
    }


}
