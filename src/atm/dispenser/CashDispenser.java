package atm.dispenser;

import atm.enums.Denomination;
import atm.model.CashInventory;

import java.util.EnumMap;
import java.util.Map;

public class CashDispenser {
    private final CashInventory inventory;
    private final CashHandler chainHead;

    public CashDispenser(CashInventory inventory) {
        this.inventory = inventory;
        CashHandler hundredHandler = new DenominationHandler(Denomination.HUNDRED);
        CashHandler fiftyHandler = new DenominationHandler(Denomination.FIFTY);
        CashHandler twentyHandler = new DenominationHandler(Denomination.TWENTY);
        CashHandler tenHandler = new DenominationHandler(Denomination.TEN);

        hundredHandler.setNextHandler(fiftyHandler);
        fiftyHandler.setNextHandler(twentyHandler);
        twentyHandler.setNextHandler(tenHandler);

        this.chainHead = hundredHandler;
    }

    public boolean canDispense(int amount) {
        Map<Denomination, Integer> notes = new EnumMap<>(Denomination.class);
        return chainHead.dispenseCheck(amount, inventory, notes);
    }

    public Map<Denomination, Integer> dispense(int amount) {
        Map<Denomination, Integer> dispensedNotes = new EnumMap<>(Denomination.class);
        boolean success = chainHead.dispenseCheck(amount, inventory, dispensedNotes);
        if (!success) {
            throw new IllegalArgumentException("Unable to dispense amount");
        }

        for (Map.Entry<Denomination, Integer> entry : dispensedNotes.entrySet()) {
            inventory.removeCash(entry.getKey(), entry.getValue());
        }

        return dispensedNotes;
    }

    public void addCash(Map<Denomination, Integer> notes) {
        for (Map.Entry<Denomination, Integer> entry : notes.entrySet()) {
            inventory.addCash(entry.getKey(), entry.getValue());
        }
    }
}
