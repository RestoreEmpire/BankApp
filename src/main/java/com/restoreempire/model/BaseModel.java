package com.restoreempire.model;

import com.restoreempire.exceptions.IdValidationException;

/** Basic model class. Every model classes should inherit from it. Contains id, and it's getter and setter. */
public abstract class BaseModel {


    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) throws RuntimeException {
        if(id <= 0) // id should be always more than zero
            throw new IdValidationException("ID should be > 0");
        else
            this.id = id;
    }




}
