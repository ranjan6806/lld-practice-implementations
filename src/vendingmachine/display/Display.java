package vendingmachine.display;

import vendingmachine.model.InventoryItem;
import vendingmachine.model.Item;

import java.util.Collection;

public class Display {
    public void showItems(Collection<InventoryItem> items) {
        System.out.println("\nAvailable Items:");

        for (InventoryItem inventoryItem : items) {
            Item item = inventoryItem.getItem();

            System.out.printf("Code=%s, Name=%s, Price=%d, Stock=%d%n",
                    item.getCode(), item.getName(), item.getPrice(), inventoryItem.getQuantity());
        }
    }
}
