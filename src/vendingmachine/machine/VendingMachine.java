package vendingmachine.machine;

import vendingmachine.display.Display;
import vendingmachine.enums.Coin;
import vendingmachine.inventory.CashInventory;
import vendingmachine.inventory.Inventory;
import vendingmachine.model.DispenseResult;
import vendingmachine.model.Item;
import vendingmachine.model.Transaction;
import vendingmachine.state.IdleState;
import vendingmachine.state.VendingMachineState;

import java.util.Map;

public class VendingMachine {
    private final Inventory inventory;
    private final CashInventory cashInventory;
    private final Display display;

    private VendingMachineState currentState;
    private Transaction currentTransaction;

    public VendingMachine(Inventory inventory, CashInventory cashInventory, Display display) {
        this.inventory = inventory;
        this.cashInventory = cashInventory;
        this.display = display;
        this.currentState = new IdleState();
        this.currentTransaction = new Transaction();
    }

    public void insertCoin(Coin coin) {
        currentState.insertCoin(this, coin);
    }

    public void selectItem(String code) {
        currentState.selectItem(this, code);
    }

    public DispenseResult dispense() {
        return currentState.dispense(this);
    }

    public void refund() {
        currentState.refund(this);
    }

    public DispenseResult dispenseItem() {
        Item item = currentTransaction.getSelectedItem();
        int changeAmount = currentTransaction.getChangeAmount();

        if (!cashInventory.canReturnChange(changeAmount)) {
            System.out.println("Unable to return change. Refunding money");
            refundBalance();
            return null;
        }

        inventory.reduceStock(item.getCode());
        cashInventory.addCoins(currentTransaction.getInsertedCoins());
        Map<Coin, Integer> change = cashInventory.getChange(changeAmount);

        cashInventory.dispenseChange(change);
        DispenseResult result = new DispenseResult(item, change);
        System.out.println("Dispensed: " + item.getName());

        reset();
        return result;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public CashInventory getCashInventory() {
        return cashInventory;
    }

    public Display getDisplay() {
        return display;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public VendingMachineState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(VendingMachineState currentState) {
        this.currentState = currentState;
    }

    public void refundBalance() {
        int refundAmount = currentTransaction.getInsertedAmount();
        System.out.println("Refund Amount: " + refundAmount);
        reset();
    }

    public void reset() {
        currentState = new IdleState();
        currentTransaction = new Transaction();
    }

    public void addItem(String code, String name, int price, int quantity) {
        inventory.addItem(new Item(code, name, price), quantity);
    }

    public void restock(String code, int quantity) {
        inventory.restock(code, quantity);
    }

    public void showItems() {
        display.showItems(inventory.getAllItems());
    }
}
