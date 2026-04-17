package MiniBank;

class SavingsAccount extends BankAccount {
    private final double minBalance = 1000;

    public SavingsAccount(String accNo, double balance, String customerName, String phone, String address) {
        super(accNo, balance, customerName, phone, address);
    }

    @Override
    void withdraw(double amount) {
        if (balance - amount >= minBalance) {
            balance -= amount;
            System.out.println("Savings Withdraw: " + amount);
        } else {
            System.out.println("Minimum balance required: " + minBalance);
        }
    }

    @Override
    public void showDetails() {
        System.out.println("\n--- Savings Account ---");
        super.showDetails();
    }
}