package echoserver.server;
import java.net.Socket;
public class StdOutServerLogger implements ServerLogger {
    public void successfulConnection(int clientPortNumber) {
        System.out.println("[+] Connection successful: New client socket port number is " + clientPortNumber);
    }
    public void failedConnection() {
        System.out.println("[-] Connection unsuccessful: Server failed to accept client connection");
    }
    public void listeningForConnection(int portNumber) {
        System.out.println("[+] Listening for client connection on server socket port number: " + portNumber);
    }
    public void cannotListenForConnection(int portNumber) {
        System.out.println( "[-] Cannot listen on server socket port number: "  + portNumber);
    }
    public void listeningForClientInput() {
        System.out.println("[+] Listening for client server input");
    }
    public void closedClientConnection(int clientPortNumber){ System.out.println("[+] Client socket closed on port number: " + clientPortNumber);}
    public void numberOfClientsConnected(int clientConnectionCounter){System.out.println("Number of clients connected: "+ clientConnectionCounter);}
}
