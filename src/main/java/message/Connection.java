package message;

import server.EchoServer;

public class Connection {

    public static String successfulConnection() {
        return "[+] Connection successful";
    }

    public static String failedConnection() {
        return "[-] Connection accept failed";
    }
}
