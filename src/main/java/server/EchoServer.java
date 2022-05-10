package server;

import java.net.*;
import java.io.*;


public class EchoServer {
    public static void start() throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(1234);
        } catch(IOException ie){
            System.out.println(("[-] Cannot listen on port number 1234"));
            System.exit(1);
        }

        Socket clientSocket = null;
        System.out.println("[+] Listening for connection...");

        try {
            clientSocket = serverSocket.accept();
        } catch(IOException ie) {
            System.out.println("[-] Accept failed");
            System.exit(1);
        }

        System.out.println("[+] Connection is successful");
        System.out.println("[+] Listening for an input");

        PrintWriter serverOutput = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String clientInputLine;

        while((clientInputLine = clientInput.readLine()) != null) {
            System.out.println("Server: " + clientInputLine);
            serverOutput.println(clientInputLine);

            if (clientInputLine.equals(("bye"))) {
                break;
            }
        }

        serverOutput.close();
        clientInput.close();
        clientSocket.close();
        serverSocket.close();

        }
}
