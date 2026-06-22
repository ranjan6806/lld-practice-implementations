package vendingmachine.inventory;

import vendingmachine.enums.Coin;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashInventory {
    private final Map<Coin, Integer> coinCount;

    public CashInventory() {
        this.coinCount = new EnumMap<>(Coin.class);
        for (Coin coin : Coin.values()) {
            coinCount.put(coin, 0);
        }
    }

    public void addCoin(Coin coin) {
        coinCount.put(coin, coinCount.getOrDefault(coin, 0) + 1);
    }

    public void addCoins(List<Coin> coins) {
        for (Coin coin : coins) {
            addCoin(coin);
        }
    }

    public void removeCoin(Coin coin) {
        int count = coinCount.getOrDefault(coin, 0);
        if (count > 0) {
            coinCount.put(coin, count - 1);
        }
    }

    public boolean canReturnChange(int amount) {
        return getChange(amount) != null;
    }

    public Map<Coin, Integer> getChange(int amount) {
        Map<Coin, Integer> change = new HashMap<>();
        Coin[] coins = Coin.values();

        for (int i = coins.length - 1; i >= 0; i--) {
            Coin coin = coins[i];

            int availableCoins = coinCount.get(coin);
            int requiredCoins = amount / coin.getValue();

            int usedCoins = Math.min(availableCoins, requiredCoins);

            if (usedCoins > 0) {
                change.put(coin, usedCoins);
                amount -= usedCoins * coin.getValue();
            }
        }

        return amount == 0 ? change : null;
    }

    public void dispenseChange(Map<Coin, Integer> change) {
        for (Map.Entry<Coin, Integer> entry : change.entrySet()) {
            Coin coin = entry.getKey();
            int count = entry.getValue();
            coinCount.put(coin, coinCount.get(coin) - count);
        }
    }

    public int getCoinCount(Coin coin) {
        return coinCount.getOrDefault(coin, 0);
    }
}
