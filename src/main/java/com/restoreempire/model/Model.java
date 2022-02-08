package com.restoreempire.model;

import java.util.HashMap;

public interface Model<T> {

    /**
     * Create a representation of Object in database table
     */
    public void create();
    /**
     * Read database element data with {@code id} in current object's fields
     * @param id id of element in database
     */
    public void read(long id);
        /**
     *  Update current object from database with {@code object}
     *  @param object object that updates current object fields with its own fields
     */
    public void update(T object);
    /**
     * Delete current object from database
     */
    public void delete();

    public HashMap<String, Object> serialized();



}
