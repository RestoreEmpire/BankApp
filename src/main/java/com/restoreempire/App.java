package com.restoreempire;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class App {

    public static void main(String[] args)  {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BankApp", 
        "postgres", 
        System.getenv("POSTGRES_PASSWORD"))) {
            PreparedStatement insert = connection.prepareStatement("insert into bank(name) values (?)");
            PreparedStatement select = connection.prepareStatement("select * from bank where name = ?");
            insert.setString(1, "Sberbank");
            insert.executeUpdate();
            select.setString(1,"Tinkoff");
            ResultSet result = select.executeQuery();
            while (result.next()){
                String name = result.getString("name");
                System.out.println("Name " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
