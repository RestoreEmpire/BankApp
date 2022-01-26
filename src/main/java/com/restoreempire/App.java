package com.restoreempire;

import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
    

        try (Scanner in = new Scanner(System.in)) {
            while(true){
            String s = in.nextLine();
            s = s.strip();
            switch (s) {
                case "create":
                    System.out.println("created");
                    break;
                case "read":
                    System.out.println("readed");
                    break;
                case "update":
                    System.out.println("updated");
                    break;
                case "delete":
                    System.out.println("deleted");
                    break;
                default:
                    break;
            }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}   
