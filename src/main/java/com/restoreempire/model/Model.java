package com.restoreempire.model;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;

abstract class Model<T> {
    
    private final String database = "BankApp";
    private final String dbUser = "postgres";
    private final String dbPassword = System.getenv("POSTGRES_PASSWORD");
    private final String connectionUrl = "jdbc:postgresql://localhost:5432/" + database;



    /**
     * Создаёт представление объекта в таблице
     */
    protected void insert(String tableName, HashMap<String, Object> data){
        String keys = String.join(", ", data.keySet().toArray(new String[0]));
        Collection<Object> values = data.values();
        int size = data.size();
        String q = String.join(", ","?".repeat(size).split(""));
        String statement = String.format("insert into %s(%s) values (%s)" , tableName, keys, q);
        try (Connection connection = DriverManager.getConnection(connectionUrl,dbUser, dbPassword)) {
            PreparedStatement insert = connection.prepareStatement(statement);
            int i = 1;
            for (Object o : values) {
                insert.setObject(i, o);
                i++;
            }
            insert.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public abstract void create();

    /**
     *
     * @param tableName name of table in database
     * @param id of element in table
     * @return {@code ResultSet} of element with specified {@code id} in table {@code tableName}
     */
    protected ResultSet select(String tableName, int id) {
        try (Connection connection = DriverManager.getConnection(connectionUrl, dbUser, dbPassword)) {
            PreparedStatement select = connection.prepareStatement(
                    String.format(
                        "select * from %s where id = %d",
                        tableName,
                        id
                        )
                    );
            ResultSet rs = select.executeQuery();
            if (rs.next())
                return rs;
            else
                throw new Exception("Row was not found");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param tableName name of table in database
     * @param query sql query
     * @return {@code ResultSet} of element with specified {@code id} in table {@code tableName}
     */
    protected ResultSet select(String tableName, String query){
        try (Connection connection = DriverManager.getConnection(connectionUrl, dbUser, dbPassword)) {
            PreparedStatement select = connection.prepareStatement(query);
            ResultSet rs = select.executeQuery();
            if (rs.next()) {
                return rs;
            }
            else
                throw new Exception("Row was not found");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Считывает из таблицы строку по номеру в объект
     */
    public abstract void read(int id);

    protected void dbUpdate(String tableName, int id, HashMap<String, Object> data) {
        String keys = String.join(", ", data.keySet().toArray(new String[0]));
        Collection<Object> values = data.values();
        int size = data.size();
        String q = String.join(", ","?".repeat(size).split(""));
        String statement = String.format(
            "update %s set (%s) = (%s) where id = %d",
            tableName,
            keys,
            q,
            id
            );
        try (Connection connection = DriverManager.getConnection(connectionUrl,dbUser, dbPassword)) {
            PreparedStatement insert = connection.prepareStatement(statement);
            int i = 1;
            for (Object o : values) {
                insert.setObject(i, o);
                i++;
            }
            insert.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *  Обновляет выбранный объект объектом из параметра, если он существует
     */
    public abstract void update(T object);

    protected void dbDelete(String tableName, int id){
        String statement = String.format("delete from %s where id = %d", tableName, id);

    }

    /**
     * Удаляет объект из таблицы, если он существует
     */
     abstract public void delete();

     protected abstract HashMap<String, Object> serialized();

}
