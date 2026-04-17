package MiniBank;

class CurrentAccount extends BankAccount {
    private final double overdraftLimit = 10000;

    public CurrentAccount(String accNo, double balance, String customerName, String phone, String address) {
        super(accNo, balance, customerName, phone, address);
    }

    @Override
    boolean withdraw(double amount) {
        if (amount <= balance + overdraftLimit) {
            balance -= amount;
            System.out.println("Current Withdraw: " + amount);
            return true;

        } else {
            System.out.println("Overdraft limit exceeded!");
            return false;
        }
    }

    @Override
    public void showDetails() {
        System.out.println("\n--- Current Account ---");
        super.showDetails();
    }
}