package echoserver;

import echoserver.iostream.IOSocketHandler;
import echoserver.server.EchoServer;
import echoserver.server.StdOutServerLogger;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException{
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var ioSocketHandler = new IOSocketHandler();
        var echoServer = new EchoServer(serverLogger, ioSocketHandler);
        echoServer.start();
    }
}
