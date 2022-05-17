package echoserver.server;
import java.net.Socket;
public class StdOutServerLogger implements ServerLogger {
    public void successfulConnection(Socket clientSocket) {
        System.out.println("[+] Connection successful: New client socket port number is " + clientSocket.getPort());
    }
    public void failedConnection() {
        System.out.println("[-] Connection unsuccessful: Server failed to accept client connection");
    }
    public void listeningForConnection(String portNumber) {
        System.out.println("[+] Listening for client connection on server socket port number: " + portNumber);
    }
    public void cannotListenForConnection(String portNumber) {
        System.out.println( "[-] Cannot listen on server socket port number: "  + portNumber);
    }
    public void listeningForClientInput() {
        System.out.println("[+] Listening for client server input");
    }

    public void closedClientConnection( Socket clientSocket ){ System.out.println("[+] Client socket closed on port number: " + clientSocket.getPort());}
}
