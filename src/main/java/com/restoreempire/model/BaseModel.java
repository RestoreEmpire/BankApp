package com.restoreempire.model;

import com.restoreempire.exceptions.IdValidationException;

public abstract class BaseModel {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) throws RuntimeException {
        if(id <= 0) 
            throw new IdValidationException("ID should be > 0");
        else
            this.id = id;
    }

}
