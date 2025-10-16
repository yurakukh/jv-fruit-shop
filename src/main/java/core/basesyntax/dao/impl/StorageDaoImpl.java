package core.basesyntax.dao.impl;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.db.Storage;
import java.util.Map;

public class StorageDaoImpl implements StorageDao {

    @Override
    public void addFruitQuantity(String fruit, int quantity) {
        Storage.putFruitQuantity(fruit, getFruitQuantity(fruit) + quantity);
    }

    @Override
    public void deductFruitQuantity(String fruit, int quantity) {
        Storage.putFruitQuantity(fruit, getFruitQuantity(fruit) - quantity);
    }

    @Override
    public int getFruitQuantity(String fruit) {
        return Storage.getFruitQuantity(fruit);
    }

    @Override
    public Map<String, Integer> getAllFruitsQuantities() {
        return Storage.getAllFruitsQuantities();
    }
}
