package echoserver;

import echoserver.server.EchoServer;
import echoserver.server.ServerLogger;
import echoserver.server.StdOutServerLogger;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException{
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var echoServer = new EchoServer(serverLogger);
        echoServer.start();
    }
}
