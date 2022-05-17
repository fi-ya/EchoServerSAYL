package echoserver.server;


public interface ServerLogger {
    void successfulConnection();
    void failedConnection();
    void listeningForConnection(int portNumber);
    void cannotListenForConnection(int portNumber);
    void listeningForClientInput();
}
