package com.restoreempire.dao;

import java.util.List;
import java.util.Map;

import com.restoreempire.model.BaseModel;

/**
 * Basic Data access object inteface. It keeps the domain model completely decoupled from the persistence layer.
 */
public interface Dao<T extends BaseModel> {



    /**
     * Create a representation of object {@code model} in database table
     */
    public void create(T model);

    /**
     * Read database element data by {@code id} and return it
     * @param id id of element in database
     * @return Object by {@code id}
     */
    public T read(long id);

    /**
     *  Update entry of object with id {@code id} with {@code updating} object.
     *  That means not null {@code updating} object's fields will replace {@code updated} object's fields
     *  and than it will be written in the database.
     *  @param updated object that will be replaced
     *  @param updating object that updates {@code updated} object fields with its own fields
     */
    public void update(long id, T updating);

    /**
     * Delete object's {@code model} entry from database, if it exists.
     * @param model 
     */
    public void delete(T model);

    /**
     * Return all database entries as objects.
     * Equivalent of SQL query {@code select * from (?)}, where {@code (?)} is table name
     * @return {@code List<T>} of all database entries, represented as objects
     */
    public List<T> getAll();

    /**
     * Return name of database table.
     * @return name of database table.
     */
    public String getTableName();

    
    /**
     * Return {@code Map<String, Object>} of {@code model} object's fields, where key {@code String} is
     * database column name and value {@code Object} is representation of database's row values.
     * This method is used to simplify database connectivity.
     * @param model object
     * @return {@code Map<String, Object>} of {@code model} object fields
     */
    public Map<String,Object> serialized(T model);

    /**
     * Return column names of database table that represent object. 
     * @return column names of database tabel.
     */
    public String[] getKeys();


}
