public class Auto extends Loan {
    //Custom Constructor
    public Auto(String recID, float intRate, float amLeft, float term){
        this.RecordID = recID;
        this.type = 1; //Type is preset as this is for an "auto" loan
        this.interestRate = intRate;
        this.amountLeft = amLeft;
        this.loanTerm = term;
    }
    //Default Constructor
    public Auto(){
        this.RecordID = null;
        this.type = 1;
        this.interestRate = 50f;
        this.amountLeft = 100f;
        this.loanTerm = 1f;
    }

    //Getters and Setters
    @Override
    public String getRecID(){
        return this.RecordID;
    }
    @Override
    public int getType(){
        return this.type;
    }
    @Override
    public float getIntRate(){
        return this.interestRate;
    }
    @Override
    public float getAmountLeft(){
        return this.amountLeft;
    }
    @Override
    public float getLoanTerm(){
        return this.loanTerm;
    }
    @Override
    public void setRecID(String newRecID){
        this.RecordID = newRecID;
    }
    @Override
    public void setType(int newType){
        this.type = newType;
    }
    @Override 
    public void setIntRate(float newIntRate){
        this.interestRate = newIntRate;
    }
    @Override
    public void setAmmountLeft(float newAmountLeft){
        this.amountLeft = newAmountLeft;
    }
    @Override
    public void setLoanTerm(float newLoanTerm){
        this.loanTerm = newLoanTerm;
    }
    
    //Print the values
    public String printValues(){
        String returnString = RecordID + " " + type + " " + interestRate + " " + loanTerm;
        return returnString;  
    }
}