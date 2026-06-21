package atm.state;

import atm.ATM;
import atm.enums.Denomination;
import atm.model.Card;

import java.util.Map;

public class CardInsertedState implements ATMStateHandler {
    public void insertCard(ATM atm, Card card) {
        throw new IllegalArgumentException("Card already inserted");
    }

    public void authenticate(ATM atm, String pin) {
        boolean authenticated = atm.getBankService().authenticate(atm.getCurrentCard().getCardNumber(), pin);

        if (!authenticated) {
            throw new IllegalArgumentException("Invalid PIN");
        }

        atm.setCurrentState(new AuthenticatedState());
        System.out.println("Authentication Successful");
    }

    public void withdraw(ATM atm, double amount) {
        throw new IllegalStateException("Authenticate first");
    }

    public void deposit(ATM atm, Map<Denomination, Integer> notes) {
        throw new IllegalStateException("Authenticate first");
    }

    public void checkBalance(ATM atm) {
        throw new IllegalStateException("Authenticate first");
    }

    public void ejectCard(ATM atm) {
        atm.setCurrentCard(null);
        atm.setCurrentState(new IdleState());
        System.out.println("Card ejected");
    }
}
