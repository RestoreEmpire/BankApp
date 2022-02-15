package com.restoreempire.service;

import java.util.Comparator;

import com.restoreempire.model.BaseModel;

public class ModelComparator implements Comparator<BaseModel> {

    @Override
    public int compare(BaseModel o1,BaseModel o2) {
        long f1 = o1.getId();
        long f2 = o2.getId();
        return Long.compare(f1, f2);
    }
    
}
