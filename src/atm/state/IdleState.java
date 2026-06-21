package atm.state;

import atm.ATM;
import atm.enums.Denomination;
import atm.model.Card;

import java.util.Map;

public class IdleState implements ATMStateHandler {
    public void insertCard(ATM atm, Card card) {
        atm.setCurrentCard(card);
        atm.setCurrentState(new CardInsertedState());
    }

    public void authenticate(ATM atm, String pin) {
        throw new IllegalStateException("Insert card first");
    }

    public void withdraw(ATM atm, double amount) {
        throw new IllegalStateException("Insert card first");
    }

    public void deposit(ATM atm, Map<Denomination, Integer> notes) {
        throw new IllegalStateException("Insert card first");
    }

    public void checkBalance(ATM atm) {
        throw new IllegalStateException("Insert card first");
    }

    public void ejectCard(ATM atm) {
        throw new IllegalStateException("No card inserted");
    }
}
