import java.math.BigDecimal;



public class App {
// банк
// расчётные счета
// клиенты

    public static void main(String[] args) throws Exception {
        BigDecimal bd = new BigDecimal("12.12");
        String s = String.format(bd.toString());
        System.out.println(s);
    }
}   
