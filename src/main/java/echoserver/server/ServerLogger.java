package echoserver.server;


import java.net.Socket;

public interface ServerLogger {
    void successfulConnection(Socket clientSocket);
    void failedConnection(Socket clientSocket);
    void listeningForConnection(String portNumber);
    void cannotListenForConnection(String portNumber);
    void listeningForClientInput();
}
