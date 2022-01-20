package application;

public interface Model {
    
    final static String path = "\\db\\";

    /**
     * Создаёт представление объекта в таблице
     */
    public void create();
}
