package echoserver.server;
import java.net.Socket;
public class StdOutServerLogger implements ServerLogger {
    public void successfulConnection(Socket clientSocket) {
        System.out.println("[+] Connection successful: New client connected " + clientSocket.getInetAddress().getHostAddress());
    }
    public void failedConnection(Socket clientSocket) {
        System.out.println("[-] Connection failed: Client " + clientSocket.getInetAddress().getHostAddress());
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
