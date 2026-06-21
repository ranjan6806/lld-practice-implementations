package atm.service;

import atm.enums.TransactionType;
import atm.model.Account;
import atm.model.Card;
import atm.model.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {
    private final Map<String, Account> accounts;
    private final Map<String, Card> cards;
    private final Map<String, List<Transaction>> transactionHistory;

    public BankService() {
        this.accounts = new ConcurrentHashMap<>();
        this.cards = new ConcurrentHashMap<>();
        this.transactionHistory = new ConcurrentHashMap<>();
    }

    public Account createAccount(String accountNumber, double initialBalance) {
        Account account = new Account(accountNumber, initialBalance);
        accounts.put(accountNumber, account);
        transactionHistory.put(accountNumber, new ArrayList<>());
        return account;
    }

    public Card createCard(String cardNumber, String pin, String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        Card card = new Card(cardNumber, pin, account);
        cards.put(cardNumber, card);
        return card;
    }

    public boolean authenticate(String cardNumber, String pin) {
        Card card = cards.get(cardNumber);
        if (card == null) {
            throw new IllegalArgumentException("Card not found");
        }

        return card.getPin().equals(pin);
    }

    public double getBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }

        return account.getBalance();
    }

    public void debit(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }

        account.debit(amount);
        recordTransaction(accountNumber, TransactionType.WITHDRAWAL, amount);
    }

    public void credit(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }

        account.credit(amount);
        recordTransaction(accountNumber, TransactionType.DEPOSIT, amount);
    }

    public List<Transaction> getTransactionHistory(String accountNumber) {
        return transactionHistory.getOrDefault(accountNumber, new ArrayList<>());
    }

    private void recordTransaction(String accountNumber, TransactionType transactionType, double amount) {
        Transaction transaction = new Transaction(transactionType, amount, LocalDateTime.now());
        transactionHistory.computeIfAbsent(accountNumber, key -> new ArrayList<>()).add(transaction);
    }
}
