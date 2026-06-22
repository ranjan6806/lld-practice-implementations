package vendingmachine.model;

import vendingmachine.enums.Coin;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private Item selectedItem;
    private List<Coin> insertedCoins;

    public Transaction() {
        this.insertedCoins = new ArrayList<>();
    }

    public void selectItem(Item item) {
        this.selectedItem = item;
    }

    public void addCoin(Coin coin) {
        insertedCoins.add(coin);
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public List<Coin> getInsertedCoins() {
        return insertedCoins;
    }

    public int getInsertedAmount() {
        return insertedCoins.stream()
                .mapToInt(Coin::getValue)
                .sum();
    }

    public int getRemainingAmount() {
        if (selectedItem == null) {
            return 0;
        }

        return Math.max(0, selectedItem.getPrice() - getInsertedAmount());
    }

    public int getChangeAmount() {
        if (selectedItem == null) {
            return 0;
        }

        return Math.max(0, getInsertedAmount() - selectedItem.getPrice());
    }

    public void reset() {
        selectedItem = null;
        insertedCoins.clear();
    }
}
