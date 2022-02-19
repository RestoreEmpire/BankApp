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
     *  Update object's {@code updated} entry from database with {@code updating} object.
     *  That means {@code updating} object's fields will replace {@code updated} object's fields
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

    public String getTableName();

    public Map<String,Object> serialized(T model);

    public String[] getKeys();


}
