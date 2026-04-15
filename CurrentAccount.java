package MiniBank;

class CurrentAccount extends BankAccount {
    private final double overdraftLimit = 10000;

    public CurrentAccount(String accNo, double balance) {
        super(accNo, balance);
    }

    @Override
    void withdraw(double amount) {
        if (amount <= balance + overdraftLimit) {
            balance -= amount;
            System.out.println("Current Withdraw: " + amount);
        } else {
            System.out.println("Overdraft limit exceeded!");
        }
    }

    @Override
    public void showDetails() {
        System.out.println("\n--- Current Account ---");
        super.showDetails();
    }
}