/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atm_interface;
import java.util.Scanner;

// functionalities which are mainly used in banks.
class BankAccount {
    private String accountNumber;
    private String pin;
    private double balance;

    public BankAccount(String accountNumber, String pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        balance -= amount;
    }
}

//functionalities like withraw money, deposit money, etc.
class ATM {
    private BankAccount currentAccount;

    public void createAccount(String accountNumber, String pin) {
        currentAccount = new BankAccount(accountNumber, pin);
    }

    public void login(String accountNumber, String pin) throws InvalidCredentialsException {
        if (currentAccount == null || !currentAccount.getAccountNumber().equals(accountNumber) ||
                !currentAccount.getPin().equals(pin)) {
            throw new InvalidCredentialsException("Invalid account number or PIN");
        }
    }

    public void deposit(double amount) {
        currentAccount.deposit(amount);
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        currentAccount.withdraw(amount);
    }

    public double checkBalance() {
        return currentAccount.getBalance();
    }

    public String getCurrentAccountNumber() {
        return currentAccount.getAccountNumber();
    }

    public String getCurrentAccountPin() {
        return currentAccount.getPin();
    }
}


// Handling exception.
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

// Handling exception.
class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}


// main atm_interface which calls all the methods.
public class Atm_interface {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM interface");
        System.out.print("Create a new account (account number): ");
        String accountNumber = scanner.nextLine();
        System.out.print("Set your PIN: ");
        String pin = scanner.nextLine();

        atm.createAccount(accountNumber, pin);

        System.out.print("Login (account number): ");
        accountNumber = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        pin = scanner.nextLine();

        try {
            atm.login(accountNumber, pin);

            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Deposit money");
                System.out.println("2. Withdraw money");
                System.out.println("3. Check balance");
                System.out.println("4. Exit");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(withdrawAmount);
                        break;
                    case 3:
                        double balance = atm.checkBalance();
                        System.out.println("Current balance: " + balance);
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM");
                        return;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
        } catch (InvalidCredentialsException e) {
            System.out.println("Invalid credentials. Exiting...");
        } catch (InsufficientBalanceException e) {
            System.out.println("Insufficient balance.");
        }
    }
}

