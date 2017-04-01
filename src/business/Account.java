/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 * Keith Emery
 * Mid-Term Assessment
 * 04/01/2017
 * IS 287
 * Professor Paul Daniel
 */

public interface Account {
    
    public int getAcctNo();
    public String getName();
    public double getBalance();
    public void setCharge(double amt, String desc);
    public void setPayment(double amt);
    public void setInterest(double rate);
    public String getErrMsg();
    public String getActionMsg();
    public String getTypeCd();
    public String getTypeDesc();
}
