package atm;

import atm.dispenser.CashDispenser;
import atm.enums.Denomination;
import atm.model.Account;
import atm.model.Card;
import atm.model.CashInventory;
import atm.service.BankService;

import java.util.EnumMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Bank setup
        BankService bankService = new BankService();
        Account account = bankService.createAccount("ACC001", 10000);
        Card card = bankService.createCard("CARD001", "1234", account.getAccountNumber());

        // ATM Cash setup
        CashInventory inventory = new CashInventory();
        inventory.addCash(Denomination.HUNDRED, 20);
        inventory.addCash(Denomination.FIFTY, 20);
        inventory.addCash(Denomination.TWENTY, 20);
        inventory.addCash(Denomination.TEN, 20);

        CashDispenser cashDispenser = new CashDispenser(inventory);
        ATM atm = new ATM(bankService, cashDispenser);

        // Withdraw Flow
        atm.insertCard(card);
        atm.authenticate("1234");
        atm.checkBalance();
        atm.withdraw(200);
        atm.checkBalance();
        atm.ejectCard();

        // Deposit Flow
        atm.insertCard(card);
        atm.authenticate("1234");
        Map<Denomination, Integer> depositNotes = new EnumMap<>(Denomination.class);
        depositNotes.put(Denomination.HUNDRED, 5);
        depositNotes.put(Denomination.FIFTY, 2);

        atm.deposit(depositNotes);
        atm.checkBalance();
        atm.ejectCard();
    }

}
