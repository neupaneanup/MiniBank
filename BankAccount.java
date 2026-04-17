package MiniBank;

abstract class BankAccount {
    protected String accountNumber;
    protected double balance;

    protected String customerName;
    protected String phone;
    protected String address;

    public BankAccount(String accNo, double balance, String customerName, String phone, String address) {
        this.accountNumber = accNo;
        this.balance = balance;

        this.customerName= customerName;
        this.phone= phone;
        this.address= address;
    }

    // Abstract method (must override)
    abstract boolean withdraw(double amount);

    // Method Overloading
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public void deposit(double amount, String source) {
        balance += amount;
        System.out.println("Deposited: " + amount + " via " + source);
    }

    public void showDetails() {
        System.out.println("\nAccount Number: " + accountNumber);
        System.out.println("Balance: " + balance);

        System.out.println("Customer Name:" + customerName);
        System.out.println("Phone Number:"+ phone);
        System.out.println("Address:"+ address);
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}