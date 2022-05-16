package echoserver.message;

public class Message {

    public static String successfulConnection() {
        return "[+] Connection successful";
    }

    public static String failedConnection() {
        return "[-] Connection accept failed";
    }

    public static String listeningForConnection(String portNumber) {
        return "[+] Listening for connection on port " + portNumber;
    }
    public static String cannotListenForConnection(String portNumber) {
        return "[-] Cannot listen on port "  + portNumber;
    }
    public static String listeningForClientInput() {
        return "[+] Listening for client server input";
    }
}
