package com.restoreempire.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.restoreempire.dao.Dao;
import com.restoreempire.exceptions.ValidationException;
import com.restoreempire.model.BaseModel;
import com.restoreempire.service.comparator.ModelComparator;

public abstract class BaseService<T extends BaseModel> implements Service<T> {

    private Dao<T> dao;

    public void setDao(Dao<T> dao){
        this.dao = dao;
    }

    public Dao<T> getDao(){
        return dao;
    }

    public List<List<Object>> getAll() {
        List<T> models = dao.getAll();
        Collections.sort(models, new ModelComparator());
        List<List<Object>> values = new ArrayList<>();
        for (T model : models) {
            Map<String, Object> map = dao.serialized(model);
            String[] keys = dao.getKeys();
            List<Object> objList = new ArrayList<>(); 
            for (int i = 0; i < map.size(); i++) {
                objList.add(map.get(keys[i]));
            }
            values.add(objList);
        }
        return values;
    }
    
    public int getPageCount(int elements) {
        return ((getAll().size() - 1) / elements) + 1;
    }

    public List<List<Object>> pagination(int page, int elements) {
        List<List<Object>> values = new ArrayList<>();
        List<List<Object>> all = getAll();
        for (int i = (page - 1) * elements; (i < page * elements) && (i < all.size()); i++) {
            values.add(all.get(i));
        }
        return values;
    }

    protected Map<String, String> check(Map<String, String[]> map ) {
        Map<String, String> resultMap = new HashMap<>();
        map.forEach(
            (t, u) -> {
                if(u.length == 1)
                resultMap.put(t, u[0]);
                else 
                    throw new ValidationException("Multiple parameters of form is not accepted");
            }
        );
        return resultMap;
    }
    
 
    
}
