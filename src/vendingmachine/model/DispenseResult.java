package vendingmachine.model;

import vendingmachine.enums.Coin;

import java.util.Map;

public class DispenseResult {
    private final Item item;
    private final Map<Coin, Integer> change;

    public DispenseResult(Item item, Map<Coin, Integer> change) {
        this.item = item;
        this.change = change;
    }

    public Item getItem() {
        return item;
    }

    public Map<Coin, Integer> getChange() {
        return change;
    }
}
