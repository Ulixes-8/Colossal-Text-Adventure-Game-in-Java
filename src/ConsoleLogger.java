import java.io.FileWriter;

public class ConsoleLogger implements Logger {

    @Override
    public void log(String message) {


         System.out.print(message);

    }
}
