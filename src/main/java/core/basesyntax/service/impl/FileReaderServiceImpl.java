package core.basesyntax.service.impl;

import core.basesyntax.service.FileReaderService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderServiceImpl implements FileReaderService {

    @Override
    public List<String> readFile(String fileName) {
        List<String> linesFromFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                linesFromFile.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file " + fileName, e);
        }
        return linesFromFile;
    }
}
