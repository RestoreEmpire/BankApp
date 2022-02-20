package com.restoreempire.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.restoreempire.logging.Logger;
import com.restoreempire.model.BaseModel;

/**
 * This class provides methods that used in other Data access object classes.
 * Also, basic methods automatically connected to database.
 * Before you start working with project, you need to change default JDBC connection properties,
 * that provided in this class.
 */
abstract class BaseDao<T extends BaseModel> implements Dao<T>{
    
    /**
     * Database name
     */
    private final static String database = "BankApp";
    /**
     * Database username
     */
    private final static String dbUser = "postgres";
    /**
     * Database password
     */
    private final static String dbPassword = System.getenv("POSTGRES_PASSWORD");
    /**
     * JDBC url
     */
    private final static String connectionUrl = "jdbc:postgresql://localhost:5432/" + database;

    /**
     * Create a row in database table with name {@code tableName} from object {@code model}
     * @param model object that represents database table
     */
    protected void insert(T model){
        var map = serialized(model);
        String keys = String.join(", ", map.keySet());
        var values = new ArrayList<Object>(map.values());
        int size = map.size();
        String q = questionMarks(size);
        String statement = String.format("insert into %s(%s) values (%s)" , getTableName(), keys, q);
        try (Connection connection = DriverManager.getConnection(connectionUrl,dbUser, dbPassword)) {
            PreparedStatement insert = connection.prepareStatement(statement);
            int i = 1;
            for (Object o : values) {
                insert.setObject(i, o);
                i++;
            }
            insert.executeUpdate();
            Logger.write("New row in db table " + getTableName() + " was created", Logger.Status.OK);
        } catch (Exception e) {
            Logger.write(e.getMessage(), Logger.Status.WARNING);
        }

    }

    /**
     * Return {@code ResultSet} of element with id that equals {@code id}
     * @param id of element in table
     * @return (1) {@code ResultSet} of element with specified {@code id} in table {@code tableName} or
     * (2) null if a row not exist in database table
     */
    protected ResultSet select(long id) {
        try (Connection connection = DriverManager.getConnection(connectionUrl, dbUser, dbPassword)) {
            PreparedStatement select = connection.prepareStatement(
                String.format(
                    "select * from %s where id = ?",
                    getTableName()
                    ));
            select.setLong(1, id);
            ResultSet rs = select.executeQuery();
            if (rs.next())
                return rs;
            else
                throw new Exception("Row was not found");
        } catch (Exception e) {
            Logger.write(e.getMessage(), Logger.Status.WARNING);
            return null;
        }
    }

    /**
     * Return {@code ResultSet} of database elements was found by SQL-query {@code query}
     * @param query sql query
     * @return (1) {@code ResultSet} of elements with specified {@code id} in table {@code tableName} or
     * (2) null if rows not exist in database table
     */
    protected static ResultSet queryResult(String query){
        try (Connection connection = DriverManager.getConnection(connectionUrl, dbUser, dbPassword)) {
            PreparedStatement select = connection.prepareStatement(query);
            ResultSet rs = select.executeQuery();
            if (rs.next()) {
                rs.beforeFirst();
                return rs;
            }
            else
                throw new Exception("Row was not found");
        } catch (Exception e) {
            Logger.write(e.getMessage(), Logger.Status.WARNING);
            return null;
        }
    }

    /**
     * Return {@code ResultSet} of all database elements in table {@code tablename}
     * @return (1) {@code ResultSet} of elements with specified {@code id} in table {@code tableName} or
     * (2) null if rows not exist in database table
     */
    protected ResultSet selectAll() {
        try (Connection connection = DriverManager.getConnection(connectionUrl, dbUser, dbPassword)) {
            PreparedStatement selectAll = connection.prepareStatement(
                "select * from " + getTableName(), 
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs = selectAll.executeQuery();
            if (rs.next()){
                rs.beforeFirst();
                return rs;
            }
            else 
                throw new Exception("Row was not found");
        } catch (Exception e) {
            Logger.write(e.getMessage(), Logger.Status.WARNING);
            return null;
        }
    }

    /**
     * Update element of database table {@code tableName} at {@code id} with object {@code model}
     * @param id id of element in database
     * @param model dictionary with data to insertion
     */
    protected void dbUpdate(long id, T model) {
        var map = serialized(model);
        String keys = String.join(", ", map.keySet());
        var values = new ArrayList<Object>(map.values());
        int size = map.size();
        String q = questionMarks(size);
        String statement = String.format(
            "update %s set (%s) = (select %s) where id = ?",
            getTableName(),
            keys,
            q
            );
        try (Connection connection = DriverManager.getConnection(connectionUrl,dbUser, dbPassword)) {
            PreparedStatement insert = connection.prepareStatement(statement);
            int i = 1;
            for (Object o : values) {
                insert.setObject(i, o);
                i++;
            }
            insert.setLong(i, id);
            insert.executeUpdate();
            Logger.write("Row was updated", Logger.Status.OK);
        } catch (Exception e) {
            Logger.write(e.getMessage(), Logger.Status.WARNING);
        }

    }

    /**
     * Return {@code String} of question marks, separated by commas.
     * This trick is used to simplify methods that used in derived classes.
     * So, no SQL-required! And no SQL injection danger.
     * @param size
     * @return String of question marks that separated with comma
     */
    private String questionMarks(int size) {
        String q = String.join(", ","?".repeat(size).split(""));
        return q;
    }


    /**
     * Delete database table element by {@code id} if it exists
     * @param tableName name of database table
     * @param id of element in database
     */
    protected void dbDelete(long id){
        String statement = String.format("delete from %s where id = %d", getTableName(), id);
        try (Connection connection = DriverManager.getConnection(connectionUrl, dbUser, dbPassword)) {
            PreparedStatement delete = connection.prepareStatement(statement);
            delete.executeUpdate();
        } catch (Exception e) {
            Logger.write(e.getMessage(), Logger.Status.WARNING);
        }
    }
}
