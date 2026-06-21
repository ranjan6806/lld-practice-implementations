package atm.state;

import atm.ATM;
import atm.enums.Denomination;
import atm.model.Account;
import atm.model.Card;

import java.util.Map;

public class AuthenticatedState implements ATMStateHandler {
    public void insertCard(ATM atm, Card card) {
        throw new IllegalStateException("Card already inserted");
    }

    public void authenticate(ATM atm, String pin) {
        throw new IllegalStateException("Already authenticated");
    }

    public void withdraw(ATM atm, double amount) {
        Account account = atm.getCurrentCard().getAccount();
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        if (!atm.getCashDispenser().canDispense((int) amount)) {
            throw new IllegalArgumentException("ATM Can not dispense amount");
        }

        atm.getCashDispenser().dispense((int) amount);
        account.debit(amount);
        System.out.println("Withdrawn: " + amount);
    }

    public void deposit(ATM atm, Map<Denomination, Integer> notes) {
        int amount = 0;
        for (Map.Entry<Denomination, Integer> entry : notes.entrySet()) {
            amount = amount + entry.getKey().getValue() * entry.getValue();
        }

        atm.getCashDispenser().addCash(notes);
        atm.getCurrentCard().getAccount().credit(amount);

        System.out.println("Deposited: " + amount);
    }

    public void checkBalance(ATM atm) {
        double balance = atm.getCurrentCard().getAccount().getBalance();
        System.out.println("Balance: " + balance);
    }

    public void ejectCard(ATM atm) {
        atm.setCurrentCard(null);
        atm.setCurrentState(new IdleState());
        System.out.println("Card ejected");
    }
}
