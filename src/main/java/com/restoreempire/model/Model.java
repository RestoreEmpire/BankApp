package com.restoreempire.model;

public interface Model<T> {
    
    static String path = "\\db\\";

    /**
     * Создаёт представление объекта в таблице
     */
    public void create();

    /**
     * Считывает из таблицы строку по номеру в объект
     */
    public void read(String id);

    /**
     *  Обновляет выбранный объект объектом из параметра, если он существует
     */ 
    public void update(T object);

    /**
     * Удаляет объект из таблицы, если он существует
     */
    public void delete();

    /**
     * @return номер строки в таблице
     */
    public int search();
}
