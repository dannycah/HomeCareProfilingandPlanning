/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hcp_pb;

/**
 *
 * @author mark
 */
public class adminUser {
    
private String AdminID;
    private String roleID;

    public adminUser(String AdminID, String roleID) {
        this.AdminID = AdminID;
        this.roleID = roleID;
    }

    public adminUser(String AdminID) {
        this(AdminID, null);
    }

    public String getAdminID() {
        return AdminID;
    }

    public void setAdminID(String AdminID) {
        this.AdminID = AdminID;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
}

