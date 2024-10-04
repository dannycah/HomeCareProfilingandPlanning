/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hcp_pb;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author mark
 */
public class servicesUpdate {

    private SimpleStringProperty sID;
    private SimpleStringProperty sDesc;
    private SimpleDoubleProperty sRate;

    // Default constructor
    public servicesUpdate() {
        this.sID = new SimpleStringProperty();
        this.sDesc = new SimpleStringProperty();
        this.sRate = new SimpleDoubleProperty();
    }

    // Parameterized constructor
    public servicesUpdate(String sID, String sDesc, double sRate) {
        this.sID = new SimpleStringProperty(sID);
        this.sDesc = new SimpleStringProperty(sDesc);
        this.sRate = new SimpleDoubleProperty(sRate);
    }

    // Getter and setter for sID
    public String getSID() {
        return sID.get();
    }

    public void setSID(String sID) {
        this.sID.set(sID);
    }

    public SimpleStringProperty sIDProperty() {
        return sID;
    }

    // Getter and setter for sDesc
    public String getSDesc() {
        return sDesc.get();
    }

    public void setSDesc(String sDesc) {
        this.sDesc.set(sDesc);
    }

    public SimpleStringProperty sDescProperty() {
        return sDesc;
    }

    // Getter and setter for sRate
    public double getSRate() {
        return sRate.get();
    }

    public void setSRate(double sRate) {
        this.sRate.set(sRate);
    }

    public SimpleDoubleProperty sRateProperty() {
        return sRate;
    }
}
