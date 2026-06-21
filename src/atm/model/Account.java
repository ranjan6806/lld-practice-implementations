package atm.model;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private final String accountNumber;
    private final ReentrantLock lock = new ReentrantLock();
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        lock.lock();

        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public void debit(double amount) {
        lock.lock();

        try {
            if (balance < amount) {
                throw new IllegalArgumentException("Insufficient account balance");
            }
            balance = balance - amount;
        } finally {
            lock.unlock();
        }
    }

    public void credit(double amount) {
        lock.lock();

        try {
            balance = balance + amount;
        } finally {
            lock.unlock();
        }
    }
}
