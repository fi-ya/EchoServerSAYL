package echoserver.server;

public interface ServerLogger {

    void successfulConnection();
    
    void failedConnection();

    void listeningForConnection(String portNumber);
    
    void cannotListenForConnection(String portNumber);
   
    void listeningForClientInput();
}
