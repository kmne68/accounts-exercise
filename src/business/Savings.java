
package business;
/*
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Calendar;
*/
import java.text.NumberFormat;

/**
 * Keith Emery
 * Mid-Term Assessment
 * 04/01/2017
 * IS 287
 * Professor Paul Daniel
 */

public class Savings extends AssetAccount {
    public static final String TYPECD = "SV";
    public static final String TYPEDESC = "Savings Account";
//    private int AcctNo;
//    private double balance;
//    private String actmsg,errmsg;
//    private String nm;
//    NumberFormat c = NumberFormat.getCurrencyInstance();
    
    public Savings(String nm, double sbal){
        super(Savings.TYPECD, nm, sbal);
        
        
    }
//        this.AcctNo = 0;
//        this.actmsg = "";
//        this.errmsg = "";
//        this.balance= 0;
        
  /*       while (this.AcctNo == 0) {
            try {
                this.AcctNo = (int) (Math.random() * 1000000);
                BufferedReader in = new BufferedReader(
                        new FileReader(Savings.TYPECD + this.AcctNo + ".txt"));
                in.close();
                this.AcctNo = 0;
            } catch (IOException e) {
                //'good' result: account does not yot exist....
                this.nm = nm;
                this.balance = sbal;
                writestatus();
                if (this.errmsg.isEmpty()) {
                   actmsg = Savings.TYPECD + " Account " +
                            this.nm + " " + this.AcctNo + " opened.";
                   writelog(actmsg);
                }   
                if (!this.errmsg.isEmpty()) {
                    this.balance = 0;
                    this.AcctNo = -1;
                }
            } catch (Exception e) {
                errmsg = "Fatal error in Savings Account constructor: " + e.getMessage();
                this.AcctNo = -1;
            }
        }//end of while 
*/

/*   
    private void writestatus() {
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
    
    private void writelog(String msg) {
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
                    "Savings account " + this.AcctNo + e.getMessage();
        } catch (Exception e) {
            errmsg = "General error in Savings write log: " + e.getMessage();
        }
    } //end of writelog
*/
/*    
    public int getAcctNo(){
        return this.AcctNo;
    }
    
    public String getName(){
        return this.nm;
    }
    
    public double getBalance(){
        return this.balance;
    }
    
    public String getErrMsg(){
        return this.errmsg;
    }
    
    public String getActionMsg(){
        return this.actmsg;
    }
    
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
    } //end of setcharge
    
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
*/
    
    @Override
    public void setInterest(double ir){
       errmsg = "";
       actmsg = "";
       double intearn;
       NumberFormat p = NumberFormat.getPercentInstance();
       
       if (this.AcctNo <= 0) {
           errmsg = "Interest attempt on non-active account.";
           return;
       }
       
       if (ir <= 0 || ir > 1.0) {
           actmsg = "Interest rate of " + p.format(ir) + 
                   " declined - rate not positive. or too large ";
           writelog(actmsg); 
       } else {
           intearn = this.balance * ir/12.00;
           this.balance += intearn;
           super.writestatus();
           if (this.errmsg.isEmpty()) {
               actmsg = "Interest earned = " + c.format(intearn) + " for " +
                       "month at annual rate of: " + p.format(ir);
                       
               writelog(actmsg);
           }else {
               this.balance -= intearn; //back out operation
           }
       } //end of interest charge method
    } 
    
}//end of savings
