package com.restoreempire.view.console;

import com.restoreempire.view.console.machine.StateHandler;

import java.util.Scanner;

public class App {



    public static void main(String[] args) {
        StateHandler sh = new StateHandler();
        System.out.print(sh.getMessage());
        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                sh.setInput(input);
                System.out.print(sh.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
