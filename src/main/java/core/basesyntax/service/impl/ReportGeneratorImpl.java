package core.basesyntax.service.impl;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.service.ReportGenerator;
import java.util.Map;

public class ReportGeneratorImpl implements ReportGenerator {
    private static final String REPORT_HEADER = "fruits,quantity";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String WORD_SEPARATOR = ",";
    private final StorageDao storageDao;

    public ReportGeneratorImpl(StorageDao storageDao) {
        this.storageDao = storageDao;
    }

    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder(REPORT_HEADER);
        report.append(LINE_SEPARATOR);
        for (Map.Entry<String, Integer> entry : storageDao.getAllFruitsQuantities().entrySet()) {
            report.append(entry.getKey())
                    .append(WORD_SEPARATOR)
                    .append(entry.getValue())
                    .append(LINE_SEPARATOR);
        }
        return report.toString();
    }
}
