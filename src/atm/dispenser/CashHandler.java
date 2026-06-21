package atm.dispenser;

import atm.enums.Denomination;
import atm.model.CashInventory;

import java.util.Map;

public interface CashHandler {
    void setNextHandler(CashHandler nextHandler);

    boolean dispenseCheck(int amount, CashInventory inventory, Map<Denomination, Integer> dispensedNotes);
}
