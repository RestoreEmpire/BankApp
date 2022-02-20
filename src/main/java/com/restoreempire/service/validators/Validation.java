package com.restoreempire.service.validators;
import java.math.BigDecimal;

import com.restoreempire.exceptions.AccountFundsValidationException;
import com.restoreempire.exceptions.IdValidationException;
import com.restoreempire.exceptions.NameValidationException;




public class Validation  {

    public enum nameType {FIRST, LAST, MIDDLE};

    public static boolean validateName(String name, nameType nameType) throws NameValidationException {

        String typeString = switch(nameType){
            case FIRST -> "First name";
            case LAST -> "Last name";
            case MIDDLE -> "Middle name";
        };
        
            if (nameType == Validation.nameType.MIDDLE && name == null) {
                return true; // Отчество(Среднее имя) может отсутствовать 
            } 
            else if (Validation.isNullOrEmpty(name)) {
                throw new NameValidationException(" cannot be null", typeString);
            }
            if(name.length() > 64) // не больше 63 символов
                throw new NameValidationException(" - length is too long. Please, use shortened name", typeString);
            for(char c : name.toCharArray()) {
                if(!Character.isLetter(c) && !Character.isSpaceChar(c) && !(c == '.')){ // только буквы, точки и пробелы
                    throw new NameValidationException(
                        " - this character is not acceptable" + '\n' +
                        "Name shouldn't contain symbols or digits", typeString);
                    }
            }
            return true;
        }

    

    /**
     * Валидация банковского счёта
     */
    public static boolean validateAccountFunds(BigDecimal money) throws AccountFundsValidationException{

            if(money.compareTo(BigDecimal.ZERO) == -1) 
                throw new AccountFundsValidationException("Not enough money");  // не может быть отрицательной суммы на счету
            else return true;

    }
    
    /**
     * Валидация ID
     */
    public static boolean validateId(String id, int size) throws IdValidationException {

            if(id.length() != size) throw new IdValidationException("Wrong id length or type"); // должно быть 16 символов в ID
            for(char c : id.toCharArray()){
                if(!Character.isDigit(c)) throw new IdValidationException("ID should consist of digits"); // только цифры
            }
            return true;
    }
    
    public static boolean isNullOrEmpty(String string) {
        if(string == null)
            return true;
        else if(string.isEmpty())
            return true;
        else
            return false;
    }
}
