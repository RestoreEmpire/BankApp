package data.processing.generators;

public class ClientIdGenerator extends DataGenerator {

    public String generate() {
        return buildRandomDigits(12).toString();
    }
}
