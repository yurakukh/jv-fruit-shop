package core.basesyntax;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.impl.StorageDaoImpl;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import core.basesyntax.service.FileReaderService;
import core.basesyntax.service.FileWriterService;
import core.basesyntax.service.ReportGenerator;
import core.basesyntax.service.ShopService;
import core.basesyntax.service.impl.DataConverterImpl;
import core.basesyntax.service.impl.FileReaderServiceImpl;
import core.basesyntax.service.impl.FileWriterServiceImpl;
import core.basesyntax.service.impl.ReportGeneratorImpl;
import core.basesyntax.service.impl.ShopServiceImpl;
import core.basesyntax.strategy.OperationHandler;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.impl.BalanceOperationHandler;
import core.basesyntax.strategy.impl.OperationStrategyImpl;
import core.basesyntax.strategy.impl.PurchaseOperationHandler;
import core.basesyntax.strategy.impl.ReturnOperationHandler;
import core.basesyntax.strategy.impl.SupplyOperationHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Feel free to remove this class and create your own.
 */
public class HelloWorld {
    public static void main(String[] args) {
        //read data from file
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        List<String> fileContent = fileReaderService.readFile("dailyActivities.csv");

        //create StorageDao to pass it to all handlers
        StorageDao storageDao = new StorageDaoImpl();

        Map<FruitTransaction.Operation, OperationHandler> handlers = new HashMap<>();
        handlers.put(FruitTransaction.Operation.BALANCE, new BalanceOperationHandler(storageDao));
        handlers.put(FruitTransaction.Operation.SUPPLY, new SupplyOperationHandler(storageDao));
        handlers.put(FruitTransaction.Operation.PURCHASE, new PurchaseOperationHandler(storageDao));
        handlers.put(FruitTransaction.Operation.RETURN, new ReturnOperationHandler(storageDao));

        //get FruitTransaction List from Strings List
        DataConverter dataConverter = new DataConverterImpl();
        List<FruitTransaction> transactions =
                dataConverter.convertStringsToTransactionsList(fileContent);
        //handle all transactions using strategy
        OperationStrategy operationStrategy = new OperationStrategyImpl(handlers);
        ShopService shopService = new ShopServiceImpl(operationStrategy);
        shopService.processTransaction(transactions);

        //generate report
        ReportGenerator reportGenerator = new ReportGeneratorImpl(storageDao);
        String finalReport = reportGenerator.generateReport();

        //write report to file
        FileWriterService fileWriterService = new FileWriterServiceImpl();
        fileWriterService.writeToFile("finalReport.csv", finalReport);

    }
}
