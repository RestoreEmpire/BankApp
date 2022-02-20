package com.restoreempire.service;

import java.util.List;
import java.util.Map;

import com.restoreempire.exceptions.ValidationException;
import com.restoreempire.model.BaseModel;

/** The service layer consists of a collection of Java classes that implement business logic */ 
public interface Service<T extends BaseModel> {

    /**
     * Update {@code updated} object's database entry with {@code updating} object 
     * @param updated
     * @param updating
     * @throws ValidationException
     */
    public void update(T updated,T updating) throws ValidationException;

    /**
     * Create {@code model} object from {@code HttpServletRequest.getParameterMap()} map
     * @param map 
     * @return {@code model}
     */
    public T setParams(Map<String, String[]> map);

    /**
     * Create database entry from {@code model}
     * @param model
     * @throws ValidationException
     */
    public void create(T model ) throws ValidationException;

    /**
     * Number of pages to render in servlet.  
     * @param elements per page
     * @return number of pages
     */
    public int getPageCount(int elements);
    
    /**
     * Contains simple pagination logic. 
     * Return {@code List<List<Object>>} where {@code List<Object>} is database row.
     * @param page number of page to return
     * @param elements number of elements on page
     * @return list of data
     */
    public List<List<Object>> pagination(int page, int elements);
}
