package vendingmachine.state;

import vendingmachine.enums.Coin;
import vendingmachine.machine.VendingMachine;
import vendingmachine.model.DispenseResult;
import vendingmachine.model.Item;

public class IdleState implements VendingMachineState {
    public void insertCoin(VendingMachine machine, Coin coin) {
        throw new IllegalArgumentException("Please select an item first");
    }

    public void selectItem(VendingMachine machine, String code) {
        if (!machine.getInventory().isAvailable(code)) {
            System.out.println("Here");
            System.out.println("Item unavailable");
            return;
        }

        Item item = machine.getInventory().getItem(code);
        machine.getCurrentTransaction().selectItem(item);

        machine.setCurrentState(new ItemSelectedState());

        System.out.println("Selected Item: " + item.getName() + ", Price: " + item.getPrice());
    }

    public DispenseResult dispense(VendingMachine machine) {
        System.out.println("Please select an item first");
        return null;
    }

    public void refund(VendingMachine machine) {
        System.out.println("No active transaction");
    }
}
