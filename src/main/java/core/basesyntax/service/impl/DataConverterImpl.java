package core.basesyntax.service.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import java.util.ArrayList;
import java.util.List;

public class DataConverterImpl implements DataConverter {

    @Override
    public List<FruitTransaction> convertStringsToTransactionsList(List<String> lines) {
        if (lines == null) {
            throw new NullPointerException("Input parameter cannot be null");
        }
        List<FruitTransaction> fruitTransactions = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String currentLine = lines.get(i).trim();
            if (currentLine.isEmpty()) {
                continue;
            }
            fruitTransactions.add(parseLineToTransaction(currentLine));
        }
        return fruitTransactions;
    }

    private FruitTransaction parseLineToTransaction(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            throw new RuntimeException("Input line is invalid: has more than 3 parts");
        }
        String operationCode = parts[0].trim();
        String fruitName = parts[1].trim();
        int quantity;
        try {
            quantity = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Input line is invalid: quantity part is invalid");
        }
        FruitTransaction.Operation operation = getOperationByCode(operationCode);
        return new FruitTransaction(operation, fruitName, quantity);
    }

    private FruitTransaction.Operation getOperationByCode(String operationCode) {
        for (FruitTransaction.Operation operation : FruitTransaction.Operation.values()) {
            if (operation.getCode().equals(operationCode)) {
                return operation;
            }
        }
        throw new RuntimeException("Input line is invalid: unknown operation code");
    }

}
