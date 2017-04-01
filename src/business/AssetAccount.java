/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;

/**
 * Keith Emery
 * Mid-Term Assessment
 * 04/01/2017
 * IS 287
 * Professor Paul Daniel
 */

public abstract class AssetAccount implements Account {

    protected int AcctNo;
    protected double balance;
    protected String actmsg, errmsg;
    private String nm;
    private String typeCode;
    NumberFormat c = NumberFormat.getCurrencyInstance();

    public AssetAccount(String code, String nm, double sbal) {

        this.actmsg = "";
        this.errmsg = "";
        this.typeCode = code;

        while (this.AcctNo == 0) {
            try {               
                this.AcctNo = (int) (Math.random() * 1000000);
                BufferedReader in = new BufferedReader(
                    new FileReader(typeCode + this.AcctNo + ".txt"));
//                    new FileReader(Savings.TYPECD + this.AcctNo + ".txt"));
                in.close();
                this.AcctNo = 0;
            } catch (IOException e) {
                //'good' result: account does not yot exist....
                this.nm = nm;
                this.balance = sbal;
                writestatus();
                if (this.errmsg.isEmpty()) {
                    actmsg = typeCode + " Account "
                            + this.nm + " " + this.AcctNo + " opened.";
                    writelog(actmsg);
                }
                if (!this.errmsg.isEmpty()) {
                    this.balance = 0;
                    this.AcctNo = -1;
                }
            } catch (Exception e) {
                errmsg = "Fatal error in Account constructor: " + e.getMessage();
                this.AcctNo = -1;
            }

        }
    }

    protected void writestatus() {
        try {
            PrintWriter out = new PrintWriter(
                    new FileWriter(Savings.TYPECD + this.AcctNo + ".txt"));
            out.println(this.nm);
            out.println(this.balance);
            out.close();
        } catch (IOException e) {
            errmsg = "Error writing status file for "
                    + " Savings account: "+ this.AcctNo;
        } catch(Exception e) {
            errmsg = "General error in Savings status update: " + e.getMessage();
        }
    } //end of writestatus
    
    protected void writelog(String msg) {
        try {
            Calendar cal = Calendar.getInstance();
            DateFormat df = DateFormat.getDateTimeInstance();
            String ts = df.format(cal.getTime());
            PrintWriter out = new PrintWriter(
                              new FileWriter(Savings.TYPECD + "L" +
                                      this.AcctNo + ".txt",true));
            out.println(ts + ": " + msg);
            out.close();
        } catch (IOException e) {
            errmsg = "Error writing log file for " +
                    " account " + this.AcctNo + e.getMessage();
        } catch (Exception e) {
            errmsg = "General error in account write log: " + e.getMessage();
        }
    } //end of writelog
    
    @Override
    public int getAcctNo() {
        return this.AcctNo;
    }
    
    @Override
    public String getName() {
        return this.nm;
    }
    
    @Override
    public double getBalance() {
        return this.balance;
    }
    
    @Override
    public void setCharge(double amt, String desc) {
        errmsg = "";
        actmsg = "";
        
        if (this.AcctNo <= 0) {
            errmsg = "Charge attempt on non-active account.";
            return;
        }
        
        if (amt <= 0) {
           actmsg = "Charge of " + c.format(amt) + " for " + desc +
                    " declined - illegal amount not positive. ";
           writelog(actmsg);
        } else if( amt > this.balance) {
           actmsg = "Charge of " + c.format(amt) + " for " + desc +
                    " declined - insufficeint funds "; 
           writelog(actmsg);
        } else {
           this.balance -= amt;
           writestatus();
           if (this.errmsg.isEmpty()) {
               actmsg = "Charge of " + c.format(amt) + " for " + desc +
                        " posted.";
               writelog(actmsg);
           }else {
               this.balance += amt; //back out operation
           }
        }
    }
    
    @Override
    public void setPayment(double amt) {
        errmsg = "";
        actmsg = "";
        
        if (this.AcctNo <= 0) {
            errmsg = "Deposit attempt on non-active account.";
            return;
        }
        
        if (amt <= 0) {
            actmsg = "Payment of " + c.format(amt) + 
                    " declined - amount must be positive.";
            writelog(actmsg); 
        } else {
            this.balance  += amt;
            writestatus();
            if (this.errmsg.isEmpty()) {
                actmsg = "Deposit of " + c.format(amt) + " posted.";
                writelog(actmsg);
            }else {
                this.balance -= amt;
            }
        }
   } //end of payment
    
 /*   public void setInterest(double rate){
        this.interestRate = rate;
    } */
    
    @Override
    public String getErrMsg(){
        return this.errmsg;
    }
    
    @Override
    public String getActionMsg() {
        return this.actmsg;
    }
    
    @Override
    public String getTypeCd() {
        
        return this.typeCode;
    }
    
    @Override
    public String getTypeDesc() {
        return "";
    }
}
