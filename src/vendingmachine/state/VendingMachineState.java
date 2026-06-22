package vendingmachine.state;

import vendingmachine.enums.Coin;
import vendingmachine.machine.VendingMachine;
import vendingmachine.model.DispenseResult;

public interface VendingMachineState {
    void insertCoin(VendingMachine machine, Coin coin);

    void selectItem(VendingMachine machine, String code);

    DispenseResult dispense(VendingMachine machine);

    void refund(VendingMachine machine);
}
