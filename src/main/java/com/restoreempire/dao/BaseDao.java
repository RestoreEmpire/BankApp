package com.restoreempire.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Map;

import com.restoreempire.model.BaseModel;


import com.restoreempire.logging.Logger;

/**
 * This class provides methods that used in other Data access object classes.
 * Also, basic methods automatically connected to database.
 * Before you start working with project, you need to change default JDBC connection properties,
 * that provided in this class.
 */
abstract class BaseDao<T extends BaseModel> implements Dao<T> {

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
     * Create a row in database table with name {@code tableName} from data in dictionary {@code data}
     * @param tableName name of database table
     * @param data dictionary with data to insertion
     */
    protected void insert(String tableName, Map<String, Object> data){
        String keys = String.join(", ", data.keySet().toArray(new String[0]));
        Collection<Object> values = data.values();
        int size = data.size();
        String q = questionMarks(size);
        String statement = String.format("insert into %s(%s) values (%s)" , tableName, keys, q);
        try (Connection connection = DriverManager.getConnection(connectionUrl,dbUser, dbPassword)) {
            PreparedStatement insert = connection.prepareStatement(statement);
            int i = 1;
            for (Object o : values) {
                insert.setObject(i, o);
                i++;
            }
            insert.executeUpdate();
            Logger.write("New row in db table " + tableName + " was created", Logger.Status.OK);
        } catch (Exception e) {
            Logger.write(e.getMessage(), Logger.Status.WARNING);
        }

    }

    /**
     * Return {@code ResultSet} of element with id that equals {@code id}
     * @param tableName name of database table
     * @param id of element in table
     * @return (1) {@code ResultSet} of element with specified {@code id} in table {@code tableName} or
     * (2) null if a row not exist in database table
     */
    protected ResultSet select(String tableName, long id) {
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
            Logger.write(e.getMessage(), Logger.Status.WARNING);
            return null;
        }
    }

    /**
     * Return {@code ResultSet} of database elements was found by SQL-query {@code query}
     * @param tableName name of table in database
     * @param query sql query
     * @return (1) {@code ResultSet} of elements with specified {@code id} in table {@code tableName} or
     * (2) null if rows not exist in database table
     */
    protected static ResultSet select(String tableName, String query){
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
     * @param tableName name of table in database
     * @return (1) {@code ResultSet} of elements with specified {@code id} in table {@code tableName} or
     * (2) null if rows not exist in database table
     */
    protected static ResultSet selectAll(String tableName) {
        try (Connection connection = DriverManager.getConnection(connectionUrl, dbUser, dbPassword)) {
            PreparedStatement selectAll = connection.prepareStatement(
                "select * from " + tableName, 
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
     * Update element of database table {@code tableName} at {@code id} with data from dictionary {@code data}
     * @param tableName name of database table
     * @param id id of element in database
     * @param data dictionary with data to insertion
     */
    protected void dbUpdate(String tableName, long id, Map<String, Object> data) {
        String keys = String.join(", ", data.keySet().toArray(new String[0]));
        Collection<Object> values = data.values();
        int size = data.size();
        String q = questionMarks(size);
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
    protected void dbDelete(String tableName, long id){
        String statement = String.format("delete from %s where id = %d", tableName, id);
        try (Connection connection = DriverManager.getConnection(connectionUrl, dbUser, dbPassword)) {
            PreparedStatement delete = connection.prepareStatement(statement);
            delete.executeUpdate();
        } catch (Exception e) {
            Logger.write(e.getMessage(), Logger.Status.WARNING);
        }
    }

    /**
     * Return {@code Map<String, Object>} of {@code model} object's fields, where key {@code String} is
     * database column name and value {@code Object} is representation of database's row values.
     * This method is used to simplify database connectivity.
     * @param model object
     * @return {@code Map<String, Object>} of {@code model} object fields
     */
    abstract protected Map<String, Object> serialized(T model);
    
}
