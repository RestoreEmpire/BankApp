import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

public class FileReadWrite {
    private String scanedString;
    private File file;

    public FileReadWrite(File file){
        this.file = file;
        try (BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            StringBuffer sb = new StringBuffer();
            int i = br.read();
            while(i != -1) {
                sb.append((char) i);
                i = br.read();
            }
            br.close();
            setScanedString(sb.toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String getScanedString() {
        return scanedString;
    }

    private void setScanedString(String scanedString) {
        this.scanedString = scanedString;
    }

    public void writeToEnd(String string){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8, true))) {
            bw.append(string); 
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
