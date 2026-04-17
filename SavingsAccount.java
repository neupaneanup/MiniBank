package MiniBank;

class SavingsAccount extends BankAccount {
    private final double minBalance = 1000;

    public SavingsAccount(String accNo, double balance, String customerName, String phone, String address) {
        super(accNo, balance, customerName, phone, address);
    }

    @Override
    boolean withdraw(double amount) {
        if (balance - amount >= minBalance) {
            balance -= amount;
            System.out.println("Savings Withdraw: " + amount);
            return true;

        } else {
            System.out.println("Minimum balance required: " + minBalance);
            return false;
        }
    }

    @Override
    public void showDetails() {
        System.out.println("\n--- Savings Account ---");
        super.showDetails();
    }
}