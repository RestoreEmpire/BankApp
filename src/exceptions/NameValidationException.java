package exceptions;

public class NameValidationException extends Exception {
    private String nameType;
    
    public NameValidationException(String message, String type){
        super(message);
        nameType = type;
    }

    @Override
    public String getMessage() {
        return String.format("%s %s", nameType, super.getMessage());
    }


}