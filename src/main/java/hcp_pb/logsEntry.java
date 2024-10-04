/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hcp_pb;

/**
 *
 * @author mark
 */
public class logsEntry {

    private int logID;
    private String tstmp;  // Timestamp as a String, but you might consider using java.time.LocalDateTime for better date-time handling
    private String logDets;

    // Default constructor
    public logsEntry() {
    }

    // Parameterized constructor
    public logsEntry(int logID, String tstmp, String logDets) {
        this.logID = logID;
        this.tstmp = tstmp;
        this.logDets = logDets;
    }

    // Getter and Setter for logID
    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    // Getter and Setter for tstmp
    public String getTstmp() {
        return tstmp;
    }

    public void setTstmp(String tstmp) {
        this.tstmp = tstmp;
    }

    // Getter and Setter for logDets
    public String getLogDets() {
        return logDets;
    }

    public void setLogDets(String logDets) {
        this.logDets = logDets;
    }


    }

