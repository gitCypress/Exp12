package exp.io;

import exp.entity.Iris;

import java.util.List;

public interface ReadFile {
    public String getPlainTextFromFile(String filepath);
    public List<Iris> getResolvedDataFromFile(String filepath);
}
