package MiniBank;

import java.util.ArrayList;

class Bank {
    private ArrayList<BankAccount> accounts = new ArrayList<>();

    public void addAccount(BankAccount acc) {
        accounts.add(acc);
    }

    public BankAccount findAccount(String accNo) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accNo)) {
                return acc;
            }
        }
        return null;
    }

    public void transfer(String from, String to, double amount) {
        BankAccount sender = findAccount(from);
        BankAccount receiver = findAccount(to);

        if (sender != null && receiver != null) {
            sender.withdraw(amount);
            receiver.deposit(amount);
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Account not found!");
        }
    }

    public void showAllAccounts() {
        for (BankAccount acc : accounts) {
            acc.showDetails();
        }
    }
}
