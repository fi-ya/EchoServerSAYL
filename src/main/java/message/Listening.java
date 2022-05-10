package message;

import server.EchoServer;

public class Listening {
    public static String listeningForConnection() {
        return "[+] Listening for connection on port " + EchoServer.PORT;
    }

    public static String cannotListenForConnection() {
        return "[-] Cannot listen on port " + EchoServer.PORT;
    }

    public static String listeningForClientInput() {
        return "[+] Listening for client server input";
    }
}
