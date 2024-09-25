public interface CheckPrinter {
    public boolean checkCustomerEligibility(Customer customer, float creditRecord, float newLoanAmount);
    public void printCustomerDetails();
}