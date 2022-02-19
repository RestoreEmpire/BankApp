package com.restoreempire.service;

import java.util.List;
import java.util.Map;

import com.restoreempire.exceptions.ValidationException;
import com.restoreempire.model.BaseModel;

public interface Service<T extends BaseModel> {

    public void update(T updated,T updating) throws ValidationException;

    public T setParams(Map<String, String[]> map);

    public void create(T model ) throws ValidationException;

    public int getPageCount(int elements);
    
    public List<List<Object>> pagination(int page, int elements);
}
