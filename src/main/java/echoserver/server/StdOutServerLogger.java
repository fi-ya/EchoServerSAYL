package echoserver.server;
import java.net.Socket;
public class StdOutServerLogger implements ServerLogger {
    public void successfulConnection() {
        System.out.println("[+] Connection successful: New client connected ");
    }
    public void failedConnection() {
        System.out.println("[-] Connection unsuccessful: Server failed to accept client connection");
    }
    public void listeningForConnection(String portNumber) {
        System.out.println("[+] Listening for client connection on port " + portNumber);
    }
    public void cannotListenForConnection(String portNumber) {
        System.out.println( "[-] Cannot listen on port "  + portNumber);
    }
    public void listeningForClientInput() {
        System.out.println("[+] Listening for client server input");
    }
}
