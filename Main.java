package MiniBank;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        // Sample Accounts
        // bank.addAccount(
        //     new SavingsAccount(
        //         "S1111",
        //         5000,
        //         "Ram Sharma",
        //         "9811111111",
        //         "Kathmandu"
        //     )
        // );

        // bank.addAccount(
        //     new CurrentAccount(
        //         "C111",
        //         3000,
        //         "Sita Rai",
        //         "9822222222",
        //         "Lalitpur"
        //     )
        // );

        while (true) {

            System.out.println("\n===== MINI BANKING SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Show One Account");
            System.out.println("6. Show All Accounts");
            System.out.println("7. Show Transactions");
            System.out.println("8. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            if (choice == 1) {

                System.out.println("\nChoose Account Type:");
                System.out.println("1. Savings");
                System.out.println("2. Current");
                System.out.print("Enter Type: ");

                int type = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Customer Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Phone Number: ");
                String phone = sc.nextLine();

                System.out.print("Enter Address: ");
                String address = sc.nextLine();

                System.out.print("Enter Account Number: ");
                String accNo = sc.nextLine();

                System.out.print("Enter Initial Balance: ");
                double balance = sc.nextDouble();
                sc.nextLine();

                if (type == 1) {

                    if (balance >= 1000) {

                        bank.addAccount(
                            new SavingsAccount(
                                accNo,
                                balance,
                                name,
                                phone,
                                address
                            )
                        );

                        System.out.println("Savings Account Created!");

                    } else {
                        System.out.println(
                            "Savings account requires minimum balance of 1000"
                        );
                    }

                } else if (type == 2) {

                    bank.addAccount(
                        new CurrentAccount(
                            accNo,
                            balance,
                            name,
                            phone,
                            address
                        )
                    );

                    System.out.println(
                        "Current Account Created! Overdraft Limit = 10000"
                    );

                } else {
                    System.out.println("Invalid Account Type");
                }

            } else if (choice == 2) {

                System.out.print("Enter Account Number: ");
                String accNo = sc.nextLine();

                System.out.print("Enter Amount: ");
                double amt = sc.nextDouble();
                sc.nextLine();

                // BankAccount acc = bank.findAccount(accNo); removed because bank.deposit le nai lookupg garchha

                bank.deposit(accNo, amt);

                // if (acc != null) {
                //     acc.deposit(amt);
                // } else {
                //     System.out.println("Account not found!");
                // }

            } else if (choice == 3) {

                System.out.print("Enter Account Number: ");
                String accNo = sc.nextLine();

                System.out.print("Enter Amount: ");
                double amt = sc.nextDouble();
                sc.nextLine();

                bank.withdraw(accNo, amt);

                // BankAccount acc = bank.findAccount(accNo);

                // if (acc != null) {
                //     acc.withdraw(amt);
                // } else {
                //     System.out.println("Account not found!");
                // }

            } else if (choice == 4) {

                System.out.print("From Account: ");
                String from = sc.nextLine();

                System.out.print("To Account: ");
                String to = sc.nextLine();

                System.out.print("Enter Amount: ");
                double amt = sc.nextDouble();
                sc.nextLine();

                bank.transfer(from, to, amt);

            } else if (choice == 5) {

                System.out.print("Enter Account Number: ");
                String accNo = sc.nextLine();

                BankAccount acc = bank.findAccount(accNo);

                if (acc != null) {
                    acc.showDetails();
                } else {
                    System.out.println("Account not found!");
                }

            } else if (choice == 6) {

                bank.showAllAccounts();

            } else if (choice == 7) {

                bank.showTransactions();

            } 
            else if (choice == 8) {

                System.out.println("Thank you for using the system.");
                break;

            } else {

                System.out.println("Invalid Choice");

            }
        }

        sc.close();
    }
}