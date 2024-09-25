public abstract class Loan {
    public String RecordID; //The ID of the record
    public int type; //Auto,Builder,Mortgage or Other
    public float interestRate; //Its interest rate
    public float amountLeft; //Amount left to pay / amount took out 
    public float loanTerm; //Time left to pay
    
    public String printValues(){
        //Returns the details of the loan
        String returnString = RecordID + "   " + type + "   " + interestRate + "   " + loanTerm;
        return returnString;  
    }

    //Abstract Getters and Setters implemented in each loan class
    public abstract String getRecID();
    public abstract int getType();
    public abstract float getIntRate();
    public abstract float getAmountLeft();
    public abstract float getLoanTerm();    
    public abstract void setRecID(String newRecID);
    public abstract void setType(int newType);
    public abstract void setIntRate(float newIntRate);
    public abstract void setAmmountLeft(float newAmountLeft);
    public abstract void setLoanTerm(float newLoanTerm);
}