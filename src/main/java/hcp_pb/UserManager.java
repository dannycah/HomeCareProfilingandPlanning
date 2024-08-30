/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hcp_pb;

/**
 *
 * @author mark
 */
public class UserManager {
    
    private int uID;
    private String empName;
    
    // Constructor
    public UserManager(int uID, String empName) {
        this.uID = uID;
        this.empName = empName;
    }

    // Getter for uID
    public int getUID() {
        return uID;
    }

    // Setter for uID
    public void setUID(int uID) {
        this.uID = uID;
    }

    // Getter for empName
    public String getEmpName() {
        return empName;
    }

    // Setter for empName
    public void setEmpName(String empName) {
        this.empName = empName;
    }
}



 
