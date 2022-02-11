package com.restoreempire.dao;

import java.util.List;
import java.util.Map;
import com.restoreempire.model.BaseModel;


public interface Dao<T extends BaseModel> {



    /**
     * Create a representation of Object in database table
     */
    public void create(T t);

    /**
     * Read database element data with {@code id} in current object's fields
     * @param id id of element in database
     */
    public T read(long id);

    /**
     *  Update current object from database with {@code object}
     *  @param object object that updates current object fields with its own fields
     */
    public void update(T updated, T updating);

    /**
     * Delete current object from database
     */
    public void delete(T t);


    public List<T> getAll();


    public Map<String, Object> serialized(T t);
}
