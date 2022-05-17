package echoserver.server;

import java.net.Socket;


public interface ServerLogger {
    void successfulConnection(Socket clientSocket);
    void failedConnection();
    void listeningForConnection(String portNumber);
    void cannotListenForConnection(String portNumber);
    void listeningForClientInput();
    void closedClientConnection(Socket clientSocket);
    void numberOfClientsConnected(int clientConnectionCounter);
}
