package data.process;
import java.math.BigDecimal;

public class DataValidation {

    /**
     * Валидация имени
     */
    public static boolean validateName(String name){

        try {
            if(name.length() > 128) // не больше 127 символов
                throw new Exception("Name length is too long. Please, use shortened name");
            for(char c : name.toCharArray()) {
                if(!Character.isLetter(c) && !Character.isSpaceChar(c) && !(c == '.')) // только буквы, точки и пробелы
                    throw new Exception("Name shouldn't contain symbols or digits");
            }
            return true;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Валидация банковского счёта
     */
    public static boolean validateAccountFunds(BigDecimal money){

        try {
            if(money.compareTo(BigDecimal.ZERO) == -1) 
                throw new Exception("Not enough money");  // не может быть отрицательной суммы на счету
            else return true;
        } 
        catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    /**
     * Валидация ID
     */
    public static boolean validateId(String id){

        try {
            if(id.length() != 12) throw new Exception("Wrong id length or type"); // должно быть 16 символов в ID
            for(char c : id.toCharArray()){
                if(!Character.isDigit(c)) throw new Exception("ID should consist of digits"); // только цифры
            }
            return true;
        } 
        catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
