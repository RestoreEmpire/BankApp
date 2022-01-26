package com.restoreempire.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Logger {

    public enum Status{OK,ERROR};

    private static String logStatus(Status st){
        String status = switch (st) {
            case OK -> "OK!";
            case ERROR -> "ERROR!";
            default -> "UNKNOWN";
        };
        return status;

    }

    static String filePath = "./log.txt";

    public static void changeLoggingFilePath(String path){
        filePath = path;
    }

    public static String show(){
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(new File(filePath), StandardCharsets.UTF_8)) {
            while(sc.hasNextLine()){
                sb.append(sc.nextLine());
            }
        return sb.toString();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static void write(String string, Status status) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss dd-MM-yyyy");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDate = dateTime.format(dtf);
        StringBuilder sb = new StringBuilder(String.format("%s ", logStatus(status)));
        sb.append(String.format("[%s] ", formattedDate));
        sb.append(string);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, StandardCharsets.UTF_8, true))) {
            bw.append(sb.toString());
            bw.newLine();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
