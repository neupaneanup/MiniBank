package MiniBank;

abstract class BankAccount {
    protected String accountNumber;
    protected double balance;

    public BankAccount(String accNo, double balance) {
        this.accountNumber = accNo;
        this.balance = balance;
    }

    // Abstract method (must override)
    abstract void withdraw(double amount);

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
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}