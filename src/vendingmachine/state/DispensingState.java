package vendingmachine.state;

import vendingmachine.enums.Coin;
import vendingmachine.machine.VendingMachine;
import vendingmachine.model.DispenseResult;

public class DispensingState implements VendingMachineState {
    public void insertCoin(VendingMachine machine, Coin coin) {
        System.out.println("Dispensing in progress");
    }

    public void selectItem(VendingMachine machine, String code) {
        System.out.println("Dispensing in progress");
    }

    public DispenseResult dispense(VendingMachine machine) {
        return machine.dispenseItem();
    }

    public void refund(VendingMachine machine) {
        System.out.println("Can not refund while dispensing");
    }
}
