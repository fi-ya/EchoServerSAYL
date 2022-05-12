import server.EchoServer;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException{
        var echoServer = new EchoServer();
        echoServer.start();
    }
}