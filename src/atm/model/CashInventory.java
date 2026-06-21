package atm.model;

import atm.enums.Denomination;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class CashInventory {
    private final Map<Denomination, Integer> notes = new EnumMap<>(Denomination.class);
    private final ReentrantLock lock = new ReentrantLock();

    public CashInventory() {
        for (Denomination denomination : Denomination.values()) {
            notes.put(denomination, 0);
        }
    }

    public void addCash(Denomination denomination, int count) {
        lock.lock();

        try {
            notes.put(denomination, notes.getOrDefault(denomination, 0) + count);
        } finally {
            lock.unlock();
        }
    }

    public void removeCash(Denomination denomination, int count) {
        lock.lock();

        try {
            int currentCount = notes.getOrDefault(denomination, 0);
            if (currentCount < count) {
                throw new IllegalArgumentException("Not enough notes available");
            }

            notes.put(denomination, currentCount - count);
        } finally {
            lock.unlock();
        }
    }

    public int getCount(Denomination denomination) {
        lock.lock();

        try {
            return notes.getOrDefault(denomination, 0);
        } finally {
            lock.unlock();
        }
    }
}
