package atm.dispenser;

import atm.enums.Denomination;
import atm.model.CashInventory;

import java.util.Map;

public class DenominationHandler implements CashHandler {
    private final Denomination denomination;
    private CashHandler nextHandler;

    public DenominationHandler(Denomination denomination) {
        this.denomination = denomination;
    }

    public void setNextHandler(CashHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public boolean dispenseCheck(int amount, CashInventory inventory, Map<Denomination, Integer> dispensedNotes) {
        int noteValue = denomination.getValue();
        int availableNotes = inventory.getCount(denomination);
        int requiredNotes = amount / noteValue;
        int notesToDispense = Math.min(requiredNotes, availableNotes);

        if (notesToDispense > 0) {
            dispensedNotes.put(denomination, notesToDispense);
            amount = amount - notesToDispense * noteValue;
        }

        if (amount == 0) {
            return true;
        }

        if (nextHandler == null) {
            return false;
        }

        return nextHandler.dispenseCheck(amount, inventory, dispensedNotes);
    }

}
