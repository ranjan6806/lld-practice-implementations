package atm;

import atm.dispenser.CashDispenser;
import atm.enums.Denomination;
import atm.model.Card;
import atm.service.BankService;
import atm.state.ATMStateHandler;
import atm.state.IdleState;

import java.util.Map;

public class ATM {
    private final BankService bankService;
    private final CashDispenser cashDispenser;
    private ATMStateHandler currentState;
    private Card currentCard;

    public ATM(BankService bankService, CashDispenser cashDispenser) {
        this.bankService = bankService;
        this.cashDispenser = cashDispenser;
        this.currentState = new IdleState();
    }

    public void insertCard(Card card) {
        currentState.insertCard(this, card);
    }

    public void authenticate(String pin) {
        currentState.authenticate(this, pin);
    }

    public void withdraw(double amount) {
        currentState.withdraw(this, amount);
    }

    public void deposit(Map<Denomination, Integer> notes) {
        currentState.deposit(this, notes);
    }

    public void checkBalance() {
        currentState.checkBalance(this);
    }

    public void ejectCard() {
        currentState.ejectCard(this);
    }

    public BankService getBankService() {
        return bankService;
    }

    public ATMStateHandler getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ATMStateHandler currentState) {
        this.currentState = currentState;
    }

    public CashDispenser getCashDispenser() {
        return cashDispenser;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }
}
