package echoserver.server;

public class StdOutServerLogger implements ServerLogger {

    public void successfulConnection() {
        System.out.println("[+] Connection successful");
    }
    public void failedConnection() {
        System.out.println("[-] Connection accept failed");
    }
    public void listeningForConnection(int portNumber) {
        System.out.println("[+] Listening for connection on port " + portNumber);
    }
    public void cannotListenForConnection(int portNumber) {
        System.out.println( "[-] Cannot listen on port "  + portNumber);
    }
    public void listeningForClientInput() {
        System.out.println("[+] Listening for client server input");
    }
}
