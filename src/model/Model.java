package model;

public interface Model<T> {
    
    static String path = "\\db\\";

    /**
     * Создаёт представление объекта в таблице
     */
    public void create();

    /**
     * Возвращает из таблицы строку по номеру в качестве объекта
     */
    public T read(int rowIndex);

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
