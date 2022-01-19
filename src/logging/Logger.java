package logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    
    public static void write(String string){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss dd-MM-yyyy");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDate = dateTime.format(dtf);
        StringBuilder sb = new StringBuilder(String.format("[%s] ", formattedDate));
        sb.append(string);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./log.txt", StandardCharsets.UTF_8, true))) {
            bw.append(sb.toString());
            bw.newLine();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
