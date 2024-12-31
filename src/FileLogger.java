import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//This class will write all outputs to the StudentFileOutput
public class FileLogger implements Logger {
    private static final String FILE_LOGGER_NAME = "StudentFileOutput.txt";

    static {

        File file = new File(FILE_LOGGER_NAME);

        try {
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(String message) {

        //FileWriter in append mode
        try (FileWriter fw = new FileWriter(FILE_LOGGER_NAME, true)) {
            fw.append(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
