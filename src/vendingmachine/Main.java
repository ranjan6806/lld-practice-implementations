package vendingmachine;

import vendingmachine.display.Display;
import vendingmachine.enums.Coin;
import vendingmachine.inventory.CashInventory;
import vendingmachine.inventory.Inventory;
import vendingmachine.machine.VendingMachine;
import vendingmachine.model.DispenseResult;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        CashInventory cashInventory = new CashInventory();

        Display display = new Display();

        VendingMachine machine = new VendingMachine(

                inventory,

                cashInventory,

                display

        );

        // Seed machine with coins for change

        cashInventory.addCoin(Coin.ONE);

        cashInventory.addCoin(Coin.ONE);

        cashInventory.addCoin(Coin.TWO);

        cashInventory.addCoin(Coin.FIVE);

        // Add products

        machine.addItem("A1", "Coke", 10, 5);

        machine.addItem("A2", "Pepsi", 20, 3);

        machine.addItem("A3", "Chips", 12, 10);

        machine.showItems();

        System.out.println("\n===== Purchase Coke =====");

        machine.selectItem("A1");

        machine.insertCoin(Coin.TEN);

        DispenseResult result = machine.dispense();

        printResult(result);

        System.out.println("\n===== Purchase Chips =====");

        machine.selectItem("A3");

        machine.insertCoin(Coin.TEN);

        machine.insertCoin(Coin.FIVE);

        result = machine.dispense();

        printResult(result);

        System.out.println("\n===== Inventory After Purchase =====");

        machine.showItems();

    }

    private static void printResult(

            DispenseResult result) {

        if (result == null) {

            return;

        }

        System.out.println(

                "Dispensed Item: "

                        + result.getItem().getName()

        );

        System.out.println(

                "Change Returned: "

                        + result.getChange()

        );

    }
}
