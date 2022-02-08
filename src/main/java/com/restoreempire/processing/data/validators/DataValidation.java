package com.restoreempire.processing.data.validators;
import java.math.BigDecimal;

import com.restoreempire.exceptions.IdValidationException;
import com.restoreempire.exceptions.NameValidationException;
import com.restoreempire.logging.Logger;




public class DataValidation {

    public enum nameType {FIRST, LAST, MIDDLE};

    public static boolean validateName(String name, nameType nameType){

        String typeString = switch(nameType){
            case FIRST -> "First name";
            case LAST -> "Last name";
            case MIDDLE -> "Middle name";
        };
        
        try {
            if(name.equals("")){
                return true;
            }
            if(name.length() > 64) // не больше 63 символов
                throw new NameValidationException(" - length is too long. Please, use shortened name", typeString);
            for(char c : name.toCharArray()) {
                if(!Character.isLetter(c) && !Character.isSpaceChar(c) && !(c == '.')) // только буквы, точки и пробелы
                    throw new NameValidationException(" - this character is not acceptable" + '\n' +
                        "Name shouldn't contain symbols or digits", typeString);
            }
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            Logger.write(e.getMessage(), Logger.Status.ERROR);
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
            e.printStackTrace();
            Logger.write(e.getMessage(), Logger.Status.ERROR);
            return false;
        }
    }
    
    /**
     * Валидация ID
     */
    public static boolean validateId(String id) {

        try {
            if(id.length() != 12) throw new IdValidationException("Wrong id length or type"); // должно быть 16 символов в ID
            for(char c : id.toCharArray()){
                if(!Character.isDigit(c)) throw new IdValidationException("ID should consist of digits"); // только цифры
            }
            return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            Logger.write(e.getMessage(), Logger.Status.ERROR);
            return false;
        }
    }
}
