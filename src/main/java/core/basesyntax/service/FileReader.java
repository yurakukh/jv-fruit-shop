package core.basesyntax.service;

import java.util.List;

public interface FileReader {
    //reads file and return all strings from it as a list of strings
    List<String> read(String fileName);
}
