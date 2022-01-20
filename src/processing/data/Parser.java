package processing.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

public class Parser {
    private String path;
    private String dbName;
    private String filePath;
    
    public Parser(String path, String dbName) {
        setPath(path);
        setDbName(dbName);
        setFilePath();
    }

    public String getPath() {
        return path;
    }
    public String getDbName() {
        return dbName;
    }
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public void setFilePath() {
        String absPath = new File("").getAbsolutePath();
        StringBuilder sb = new StringBuilder(absPath);
        sb.append(path);
        sb.append(dbName);
        sb.append(".csv");
        filePath = sb.toString();
    }

    public void writeToEnd(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length - 1; i++) {
            sb.append(strings[i] + ", ");
        }
        sb.append(strings[strings.length - 1]);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, StandardCharsets.UTF_8, true))) {
            bw.newLine();
            bw.append(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
