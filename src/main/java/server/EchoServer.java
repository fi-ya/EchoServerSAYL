package server;

import java.net.*;
import java.io.*;


public class EchoServer {

    public static ServerSocket serverSocket = null;
    public static Socket clientSocket = null;
    public static void start() throws IOException {

        openConnection();
        connectClientSocket();

        PrintWriter serverOutput = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ioStream(serverOutput, clientInput);

        clientSocket.close();
        serverSocket.close();
    }

    public static void openConnection() throws IOException {

        try {
            serverSocket = new ServerSocket(1234);
            System.out.println("[+] Listening for connection on port number 1234...");
        } catch(IOException ie){
            System.out.println(("[-] Cannot listen on port number 1234"));
            System.exit(1);
        }
    }

    public static void connectClientSocket() throws IOException {
        try {
            clientSocket = serverSocket.accept();
            System.out.println("[+] Connection is successful");
        } catch(IOException ie) {
            System.out.println("[-] Accept failed");
            System.exit(1);
        }
    }

    public static void ioStream(PrintWriter serverOutput, BufferedReader clientInput) throws IOException {
        System.out.println("[+] Listening for an input");

        String clientInputLine;

        while((clientInputLine = clientInput.readLine()) != null) {
            System.out.println("Server will echo this back to the client: " + clientInputLine);
            serverOutput.println(clientInputLine);

            if (clientInputLine.equals(("bye"))) {
                break;
            }
        }

        serverOutput.close();
        clientInput.close();
    }
    }

