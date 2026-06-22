package vendingmachine.state;

import vendingmachine.enums.Coin;
import vendingmachine.machine.VendingMachine;
import vendingmachine.model.DispenseResult;
import vendingmachine.model.Transaction;

public class ItemSelectedState implements VendingMachineState {
    public void insertCoin(VendingMachine machine, Coin coin) {
        Transaction transaction = machine.getCurrentTransaction();
        transaction.addCoin(coin);
        System.out.println("Inserted: " + coin.getValue());
        if (transaction.getRemainingAmount() <= 0) {
            machine.setCurrentState(new DispensingState());
        }
    }

    public void selectItem(VendingMachine machine, String code) {
        System.out.println("Item already selected");
    }

    public DispenseResult dispense(VendingMachine machine) {
        System.out.println("Insufficient balance");
        return null;
    }

    public void refund(VendingMachine machine) {
        machine.refundBalance();
    }
}
