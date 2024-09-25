import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class XYZbank {
    public static int maxNumRecord = setMaxRecords();

    public static List<Customer> listofCustomers = new ArrayList<>();
    

    public static void main(String[] args){
        //Sets up input for the program
        Scanner input = new Scanner(System.in);
        //This boolean will be set to true when the user is finished with the program and will cause the main while loop to exit stopping the program
        boolean done = false;
        //Main program loop 
        while(done == false){
            //Displays the main menu and gets the users input
            int choice = mainMenu(input);
            String customerID;
            switch (choice) {
                case 1:
                        try{
                            //Code for creating a new customer

                            System.out.println("Enter a Customer ID - eg AAA111: ");
                            customerID = input.next();
                            System.out.println("Enter the Customers annual income - a postive float: ");
                            float annualIncome = input.nextFloat();
                            createNewCustomer(customerID,annualIncome);
                        }
                        //If the user doesnt enter a float for the customer income this runs
                        catch(InputMismatchException e){
                            System.out.println("Please enter a float for the customer income\n");
                        }
                        //The assertion error is called when the user enters a customer id in the wrong format
                        catch(AssertionError e){
                            System.out.println(e.getMessage());
                        }
                    break;
                case 2:
                    try{

                        //Code for adding a record to a customer

                        //Getting the parameters for adding the record
                        System.out.println("Enter the CustomerID to add the record to: ");
                        customerID = input.next();
                        System.out.println("Enter the record ID: ");
                        String recordID = input.next();
                        System.out.println("Enter the type of loan: ");
                        int type = input.nextInt();
                        System.out.println("Enter the loan amount: ");
                        float loanAmount = input.nextFloat();
                        System.out.println("Enter the interest rate of the loan: ");
                        float interestRate = input.nextFloat();
                        System.out.println("Enter the loan term: ");
                        float loanTerm = input.nextFloat();
                        
                        addRecordToCustomer(recordID,customerID,type,interestRate,loanAmount,loanTerm);//Customer id, record id, type ,interest rate, amount left, loan term
                    }
                    //Assertion error will occur if any of the fields above were entered incorrectly
                    catch(AssertionError e){
                        System.out.println(e.getMessage());
                    }
                    //This will run if any data types are inserted wrong eg "a" inserted as type
                    catch(InputMismatchException e){
                        System.out.println("Please enter the correct data types");
                    }
                    break;    
                case 3:
                    try{

                        //Code for printing customer records

                        System.out.println("Enter the customerID or use * for all customers:");
                        customerID = input.next();
                        if(customerID == "*"){
                            //Prints all customers details
                            for(Customer customer : listofCustomers){
                                printCustomerRecord(customer.getCustID());;
                            }
                        }else{
                            //Prints specific customers details
                            printCustomerRecord(customerID);
                        }
                        //if the wrong data type is entered then run this 
                    }catch(InputMismatchException e){
                        System.out.println("Customer ID is incorrect");
                    }
                    break;
                case 4:
                    try{
                        //Code to change inputted data in a record
                        changeCustomerData(input);
                    }
                    //This code will return any errors in formatting of the entered new data
                    catch (AssertionError e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    //Sets done to true exiting the while loop and stopping the program
                    done = true;
                    //Closes the scanner to prevent a memory leak
                    input.close();
                    break;
            }
        }
        
        
    }

    
    private static int mainMenu(Scanner input){
        //"Main menu screen" with 5 options 1) Adds a new customer, 2) Adds a new record to a customer 3) Prints a customers records 4) Changes a record of a customer 5) Quits the program
        System.out.println("###############################");
        System.out.println("Welcome to XYZBanks Loan system");
        System.out.println("####Please choose an option####");
        System.out.println("#### 1) Creates a customer ####");
        System.out.println("#2) Add a record to a customer#");
        System.out.println("# 3) Print a customers record #");
        System.out.println("##4)Change a customer record ##");
        System.out.println("########### 5) Quit ###########");
        System.out.println("###############################");

        try{
            int choice = input.nextInt();
            //Checks if the entered input is valid
            if(choice >=7 || choice < 0){
                System.out.println("Invalid choice\n");
                mainMenu(input);
            }else{
                return choice;
            }
        
        }
        //If an int isnt entered then this code runs
        catch(InputMismatchException e){
            System.out.println("Please enter a number");
            input.next();
        }
        //Returns -1 which isnt accepted by the main switch statement therefore the while loop runs again printing the main menu again
        return -1;
    }

    public static int setMaxRecords(){
        //Sets the max number of records the system can hold
        try{
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the maximum number of records: ");
            int output = input.nextInt();
            return output;
        }
        //If the user doesnt enter a number the program runs recursivly until they do
        catch(InputMismatchException e){
            System.out.println("Enter a number");
            return setMaxRecords();
        }
        
    }

    public static void createNewCustomer(String customerID, float customerIncome){ 
        //The followeing assert statements tests the entered String customerID
        assert customerIncome > 0 : "Enter a positve income";
         //Test to see if the first 3 characters of the customer id are letters
         boolean first3CustDigits = true;
         for(int i = 0; i < customerID.length()-3; i++){
             if(!Character.isLetter(customerID.charAt(i))){
                 first3CustDigits = false;
             }
         }
        assert first3CustDigits = true : "The first 3 characters of Customer ID need to be Letters";
         //Tests to see if the last 3 characters of the customer id are digits
         boolean last3CustDigits = true;
         for(int i = 3; i < customerID.length(); i++){
             if(!Character.isDigit(customerID.charAt(i))){
                 last3CustDigits = false;
             }
         }
        assert last3CustDigits = true : "The last 3 characters of Customer ID need to be digits";
        //If all tests pass then the customer is created and added to the list of all customers
        Customer newCustomer = new Customer(customerID,customerIncome);
        listofCustomers.add(newCustomer);
    }

    public static void addRecordToCustomer(String recID, String customerID, int type, float intRate, float ammLeft, float loanTerm){
        //Validates the entered data
        addRecordValidate(recID,customerID,type,intRate,ammLeft,loanTerm);
        //If all tests then first get the customer we are adding the record to
        Customer currentCustomer = resolveCustomerID(customerID);
        //Add the record
        currentCustomer.addNewLoan(recID, customerID, type, intRate, ammLeft, loanTerm);
    }

    public static void addRecordValidate(String recID, String customerID, int type, float intRate, float ammLeft, float loanTerm){
        //Validates the data for adding a new record
        //Tests to see if the record id is 6 characters lond
        assert recID.length() == 6 : "Record ID needs to be 6 NUMBERS long";
        //Tests if the record id is all digits
        boolean recIDIsDigit = true;
        for(int i = 0; i < recID.length(); i++){
            if(!Character.isDigit(recID.charAt(i))){
                recIDIsDigit = false;
            }
        }
        assert recIDIsDigit = true : "Record ID needs to be only Numbers";
        //Tests that the customer id is 6 characters long
        assert customerID.length() == 6 : "Customer ID needs to be 6 characters long";
        //Test to see if the first 3 characters of the customer id are letters
        boolean first3CustDigits = true;
        for(int i = 0; i < customerID.length()-3; i++){
            if(!Character.isLetter(customerID.charAt(i))){
                first3CustDigits = false;
            }
        }
        assert first3CustDigits = true : "The first 3 characters of Customer ID need to be Letters";
        //Tests to see if the last 3 characters of the customer id are digits
        boolean last3CustDigits = true;
        for(int i = 3; i < customerID.length(); i++){
            if(!Character.isDigit(customerID.charAt(i))){
                last3CustDigits = false;
            }
        }
        assert last3CustDigits = true : "The last 3 characters of Customer ID need to be digits";
        //Tests to see if type is between 1 and 4
        assert type > 1 || type < 5 : "Type needs to be between 1 and 4";
        //Tests to see if interest rate is positive
        assert intRate > 0 : "Interest Rate needs to be above 0";
        //Tests to see if amount left is positive
        assert ammLeft > 0 : "Amount left needs to be above 0";
        //Tests to see if loan term is positive
        assert loanTerm > 0 : "Loan term needs to be above 0";
    }


    public static Customer resolveCustomerID(String customerID){
        //Gets the customer object by searching the list of customers using its customerID attribute
        for(Customer customer : listofCustomers){
            if(customer.getCustID().equals(customerID)){
                return customer; //Returns the found customer
            }
        }
        return null; // Return null if customer is not found
    }
    

    public static void printCustomerRecord(String customerID){
        //Gets the customer object from the customerID
        Customer customer = resolveCustomerID(customerID);
        //Calls the printCustomerDetails method in the customer class
        customer.printCustomerDetails();
    }

    public static void changeCustomerData(Scanner input){
        //Gets the customer object that has the record being changed
        System.out.println("Enter the Customer ID: ");
        String customerID = input.next();
        Customer customer = resolveCustomerID(customerID);
        //Sets up the list of records that customer has
        List<Loan> listOfRecords = customer.getListofLoans();
        //Gets the record to change by its id
        String recordToChange = getRecordToChange(input);
        //Gets the part of the record to change eg type or interest rate
        int indexToChange = getIndexToChange(input);
        Loan record;
        switch(indexToChange){
            /*Each case statement gets the value the user would like to change to and 
             * validates it before continuing if the validation is sucessful or returning 
             * an error message detailing what the user did wrong */
            case 1:
                System.out.println("Enter a new Record ID");
                String recordID = input.nextLine();
                //Tests to see if the record id is 6 characters lond
                assert recordID.length() == 6 : "Record ID needs to be 6 NUMBERS long";
                //Tests if the record id is all digits
                boolean recIDIsDigit = true;
                for(int i = 0; i < recordID.length(); i++){
                    if(!Character.isDigit(recordID.charAt(i))){
                    recIDIsDigit = false;
                    }
                }
                assert recIDIsDigit = true : "Record ID needs to be only Numbers";
                record = resolveRecordID(recordToChange,listOfRecords);
                record.setRecID(recordID);
                break;
            case 2:
                System.out.println("Enter a new Type");
                int type = input.nextInt();
                //Tests to see if type is between 1 and 4
                assert type > 1 || type < 5 : "Type needs to be between 1 and 4";
                record = resolveRecordID(recordToChange,listOfRecords);
                record.setType(type);
                break;
            case 3:
                System.out.println("Enter a new interest rate");
                float interestRate = input.nextFloat();
                //Tests to see if interest rate is positive
                assert interestRate > 0 : "Interest Rate needs to be above 0";
                record = resolveRecordID(recordToChange,listOfRecords);
                record.setIntRate(interestRate);
                break;
            case 4:
                System.out.println("Enter a new ammount left");
                float ammountLeft = input.nextFloat();
                //Tests to see if amount left is positive
                assert ammountLeft > 0 : "Amount left needs to be above 0";
                record = resolveRecordID(recordToChange,listOfRecords);
                record.setAmmountLeft(ammountLeft);
                break;
            case 5:
                System.out.println("Enter a new loan term");
                float loanTerm = input.nextFloat();
                //Tests to see if loan term is positive
                assert loanTerm > 0 : "Loan term needs to be above 0";
                record = resolveRecordID(recordToChange,listOfRecords);
                record.setLoanTerm(loanTerm);
                break;
        }

        
    }
    private static int getIndexToChange(Scanner input){
        //This gets the part of the record to be changed in the changeCustomerData method
        while (true) {
            try {
                System.out.println("Enter the index to change:\n1 = Record ID\n2 = Type\n3 = Interest ammount\n4 = Ammount left\n5 = Loan term");
                int whatToChange = input.nextInt();
                assert (whatToChange > 5 || whatToChange < 0);
                return whatToChange;//Return if integer is entered
            } catch (java.util.InputMismatchException e) {
                // If user input is not an integer, display an error message
                System.out.println("Invalid input. Please enter an integer.");
                input.nextLine(); // Clean scanner buffer to prevent further errors
            } catch(AssertionError e){
                System.out.println("Enter a number between 1 and 6");
                input.nextLine(); // Clean scanner buffer to prevent further errors
            }
        }
                
    }

    private static String getRecordToChange(Scanner input){
        //This gets the record id of the record the user wants to change
        while(true){
            try {
                System.out.print("Enter the ID of the record to change");
                String recordToChange = input.next();
                return recordToChange;
            } catch (java.util.InputMismatchException e) {
                // If user input is not an integer, display an error message
                System.out.println("Invalid input. Please enter an String.");
                input.nextLine(); // Clean scanner buffer to prevent further errors
            }
        }
    }

    private static Loan resolveRecordID(String recordID, List<Loan> listOfLoans){
        //Gets the Loan object corresponding to its recordID so that the record can be changed and returns it
        for(Loan record : listOfLoans){
            if (record.getRecID().equals(recordID)){
                return record; //Returns the found record
            }
        }
        return null; // Return null if the recordID is not found
    }
}