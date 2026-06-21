package atm.state;

import atm.ATM;
import atm.enums.Denomination;
import atm.model.Card;

import java.util.Map;

public interface ATMStateHandler {
    void insertCard(ATM atm, Card card);

    void authenticate(ATM atm, String pin);

    void withdraw(ATM atm, double amount);

    void deposit(ATM atm, Map<Denomination, Integer> notes);

    void checkBalance(ATM atm);

    void ejectCard(ATM atm);
}
