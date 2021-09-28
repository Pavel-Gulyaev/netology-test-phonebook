package service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileServiceImpl implements FileService {
    private final String FILE_NAME = "contactBook.txt";
    @Override
    public List<String> loadData() {
        try  (Stream<String> input = Files.lines(Paths.get(FILE_NAME), StandardCharsets.UTF_8)) {
            return input
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error during load data");
        }
        return new ArrayList<>();
    }

    @Override
    public void saveData(List<String> data) {
        try {
            Files.write(Paths.get(FILE_NAME), data);
        } catch (IOException e) {
            System.out.println("Error during save data");
        }
    }
}
