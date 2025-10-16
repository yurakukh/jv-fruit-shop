package core.basesyntax.service;

import java.util.List;

public interface FileReaderService {
    //reads file and return all strings from it as a list of strings
    List<String> readFile(String fileName);
}
