package vendingmachine.inventory;

import vendingmachine.model.InventoryItem;
import vendingmachine.model.Item;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    private final Map<String, InventoryItem> items;

    public Inventory() {
        items = new ConcurrentHashMap<>();
    }

    public void addItem(Item item, int quantity) {
        items.put(item.getCode(), new InventoryItem(item, quantity));
    }

    public void restock(String code, int quantity) {
        InventoryItem inventoryItem = items.get(code);
        if (inventoryItem != null) {
            inventoryItem.increaseQuantity(quantity);
        }
    }

    public Item getItem(String code) {
        InventoryItem inventoryItem = items.get(code);

        if (inventoryItem == null) {
            return null;
        }

        return inventoryItem.getItem();
    }

    public boolean isAvailable(String code) {
        InventoryItem inventoryItem = items.get(code);

        System.out.println(code);
        System.out.printf("inventoryItem %d %s", inventoryItem.getQuantity(), inventoryItem.getItem().getName());

        return inventoryItem != null && inventoryItem.isAvailable();
    }

    public int getStock(String code) {
        InventoryItem inventoryItem = items.get(code);
        return inventoryItem == null ? 0 : inventoryItem.getQuantity();
    }

    public void reduceStock(String code) {
        InventoryItem inventoryItem = items.get(code);
        if (inventoryItem != null) {
            inventoryItem.decreaseQuantity();
        }
    }

    public Collection<InventoryItem> getAllItems() {
        return items.values();
    }
}
