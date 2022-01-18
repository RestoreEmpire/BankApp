import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class FileIOBinary {
    public static String readFile(File file) {
        try{
        FileInputStream fis = new FileInputStream(file);
        byte[] b = new byte[fis.available()];
        fis.read(b, 0, fis.available());
        String s = new String(b, StandardCharsets.UTF_8);
        fis.close();
        return s;
        } catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static byte[] readFileToBytes(File file){
        try{
        FileInputStream fis = new FileInputStream(file);
        byte[] b = new byte[fis.available()];
        fis.read(b, 0, fis.available());
        fis.close();
        return b;
        } catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    public static void addMessageToFile(File file, String message){
        String fileInput = readFile(file);
        StringBuilder sb = new StringBuilder(fileInput);
        sb.append(message);
        String output = sb.toString();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(output.getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
