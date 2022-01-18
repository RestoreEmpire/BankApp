import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("./test.txt");
        var frw = new FileReadWrite(file);
        System.out.println(frw.getScanedString());
        frw.writeToEnd("YAML Ain't Markup Language\n"); 
        try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8)) {
            while(sc.hasNextLine()){
                
                System.out.println(sc.nextLine());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
