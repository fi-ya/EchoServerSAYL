package echoserver.iostream;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadTextAsString {
    public static String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }
}
