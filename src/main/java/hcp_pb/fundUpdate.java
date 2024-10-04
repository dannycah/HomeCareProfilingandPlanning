/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hcp_pb;

/**
 *
 * @author mark
 */
public class fundUpdate {
    
    private String levelID;
    private double dailyFund;  
    private double fortnightlyFund;
    private double monthlyFund;

    // Constructor
    public fundUpdate(String levelID, double dailyFund, double fortnightlyFund, double monthlyFund) {
        this.levelID = levelID;
        this.dailyFund = dailyFund;
        this.fortnightlyFund = fortnightlyFund;
        this.monthlyFund = monthlyFund;
    }

    // Getters and Setters
    public String getLevelID() {
        return levelID;
    }

    public void setLevelID(String levelID) {
        this.levelID = levelID;
    }

    public double getDailyFund() {
        return dailyFund;
    }

    public void setDailyFund(double dailyFund) {
        this.dailyFund = dailyFund;
    }

    public double getFortnightlyFund() {
        return fortnightlyFund;
    }

    public void setFortnightlyFund(double fortnightlyFund) {
        this.fortnightlyFund = fortnightlyFund;
    }

    public double getMonthlyFund() {
        return monthlyFund;
    }

    public void setMonthlyFund(double monthlyFund) {
        this.monthlyFund = monthlyFund;
    }
}

