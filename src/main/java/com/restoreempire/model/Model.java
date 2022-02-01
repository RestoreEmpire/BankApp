package com.restoreempire.model;

import java.util.HashMap;

public interface Model<T> {

    public void create();
    /**
     * Считывает из таблицы строку по номеру в объект
     */
    public void read(int id);

    /**
     *  Обновляет выбранный объект объектом из параметра, если он существует
     */
    public void update(T object);

    /**
     * Удаляет объект из таблицы, если он существует
     */
    public void delete();

    public HashMap<String, Object> serialized();

}
