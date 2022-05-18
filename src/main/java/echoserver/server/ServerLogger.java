package echoserver.server;

import java.net.Socket;


public interface ServerLogger {
    void successfulConnection(Socket clientSocket);
    void failedConnection();
    void listeningForConnection(int portNumber);
    void cannotListenForConnection(int portNumber);
    void listeningForClientInput();
    void closedClientConnection(Socket clientSocket);
    void numberOfClientsConnected(int clientConnectionCounter);
}
