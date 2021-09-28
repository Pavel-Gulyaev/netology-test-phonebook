package service;

import java.util.List;

public interface FileService {
    public List<String> loadData();

    public void saveData(List<String> data);
}
