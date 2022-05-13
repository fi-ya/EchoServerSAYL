package message;

public class Listening {
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
