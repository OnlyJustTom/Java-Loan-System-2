import java.util.ArrayList;
import java.util.List;

public class Customer implements CheckPrinter{
    public String customerID; //ID of the customer
    public float customerIncome; //Their income
    public boolean eligibilityStatus; //If the can take out another loan or not
    public float creditRecord; //The total amount they have taken out
    public List<Loan> listOfLoans = new ArrayList<>(); //This list of all loans they have taken out
    //As customer is new eligibility status and creditRecord are unknown and therfore are left as false and null respectivly
    //Eligibility will be tested for later in the class and this will be appended to the customer when they take out a new loan

    //Custom Constructor
    public Customer(String custID, float custIncome){
        this.customerID = custID;
        this.customerIncome = custIncome;
        this.eligibilityStatus = false;
        this.creditRecord = 0;
        this.listOfLoans = new ArrayList<>(XYZbank.maxNumRecord); //The max number of the list of loans is set
    }
    //Default Constructor
    public Customer(){
        this.customerID = "AAA123";
        this.customerIncome = 1000f;
        this.eligibilityStatus = false;
        this.creditRecord = 0;
        this.listOfLoans = new ArrayList<>(XYZbank.maxNumRecord); //The max number of the list of loans is set
    }

    //Getters and Setters

    public String getCustID(){
        return this.customerID;
    }
    public float getCustIncome(){
        return this.customerIncome;
    }
    public boolean getStatus(){
        return this.eligibilityStatus;
    }
    public float getCreditRecord(){
        return this.creditRecord;
    }
    public List<Loan> getListofLoans(){
        return this.listOfLoans;
    }

    public void setCustID(String newCustomerID){
        this.customerID = newCustomerID;
    }
    public void setCustIncome(float newCustomerIncome){
        this.customerIncome = newCustomerIncome;
    }
    public void setStatus(boolean newStatus){
        this.eligibilityStatus = newStatus;
    }
    public void setCreditRecord(float newCreditRecord){
        this.creditRecord = newCreditRecord;
    }


    public void addNewLoan(String recID, String custID, int type, float intRate, float ammLeft, float loanTerm){
        //The eligibility of the customer is first checked before a loan is added
        if(checkCustomerEligibility(this, this.creditRecord, ammLeft)){   
            float records = 0;
            switch (type) {
                //Depeding on the type of loan the respective constructor is called and the loan is added to the list of loans
                case 1:
                    Auto AutoLoan = new Auto(recID, intRate, ammLeft, loanTerm);
                    AutoLoan.printValues();
                    records = AutoLoan.amountLeft;
                    listOfLoans.add(AutoLoan);
                    break;
                case 2:
                    Builder BuilderLoan = new Builder(recID, intRate, ammLeft, loanTerm);
                    records = BuilderLoan.amountLeft;
                    listOfLoans.add(BuilderLoan);
                    
                    break;
                case 3:
                    Mortgage MortgageLoan = new Mortgage(recID, intRate, ammLeft, loanTerm);
                    records = MortgageLoan.amountLeft;
                    listOfLoans.add(MortgageLoan);
                     
                    break;
                case 4:
                    Other OtherLoan = new Other(recID, intRate, ammLeft, loanTerm);
                    records = OtherLoan.amountLeft;
                    listOfLoans.add(OtherLoan);
                    
            }
        //The amount the customer took out its added to the credit rating
        addRecordToCreditRating(this ,records);
        }
        else{
            //If the customer cannot take out a loan then this is printed
            System.out.println("You cannot take a loan with your credit");
        }
    }
    
    public void addRecordToCreditRating(Customer creditRecord, float amountToAdd){
        //The inputed amount left on a loan is added to the credit rating and the credit rating of the object is updated
        float newCreditRecord = creditRecord.creditRecord + amountToAdd;
        creditRecord.setCreditRecord(newCreditRecord);
    }

    public boolean checkCustomerEligibility(Customer customer, float creditRecord, float newLoanAmount){
        float customerIncome = customer.getCustIncome();
        //If the credit record of the customer is greater than 4x the income of the customer then they are not eligable
        if (creditRecord > (4*customerIncome)){
            this.setStatus(false);
            return false;
        }
        //If the amount the customer is trying to take out combined with the amount they have already taken out is greater than 4x their income then they are not eligable
        else if(creditRecord + newLoanAmount > (4*customerIncome) ){
            this.setStatus(true);
            return false;
        }
        else{
            return true;
        }
    }

    public void printCustomerDetails(){
        for(Loan loan : this.listOfLoans){
            //Formatting the printed result
            System.out.println("=====================================");
            System.out.println("Customer ID: " + this.getCustID());
            System.out.println("Eligable to take out more loans: " + !this.getStatus());
            System.out.println("RecordID   Loan Type   Interest Rate   Amount Left   Time Left");
            //Prints the loan details of each loan the customer has out
            //If none than this will be empty
            System.out.println(loan.printValues());
            System.out.println("====================================");
        }
    }
}