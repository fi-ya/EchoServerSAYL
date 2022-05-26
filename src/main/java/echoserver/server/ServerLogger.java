package echoserver.server;

import java.net.Socket;


public interface ServerLogger {
    void successfulConnection(int clientPortNumber);
    void failedConnection();
    void listeningForConnection(int portNumber);
    void cannotListenForConnection(int portNumber);
    void listeningForClientInput();
    void closedClientConnection(int clientPortNumber);
    void numberOfClientsConnected(int clientConnectionCounter);
}
