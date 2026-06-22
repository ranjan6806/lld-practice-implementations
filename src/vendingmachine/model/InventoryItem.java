package vendingmachine.model;

public class InventoryItem {
    private final Item item;
    private int quantity;

    public InventoryItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isAvailable() {
        return quantity > 0;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }
}
