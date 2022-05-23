package echoserver.server;

public class MockServerLogger {
    public static ServerLogger mockServerLogger() {
        return new StdOutServerLogger();
    }
}
